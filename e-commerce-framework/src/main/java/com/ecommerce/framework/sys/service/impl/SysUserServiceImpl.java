package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.common.enums.UserStatus;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.support.Convert;
import com.ecommerce.common.utils.CollectionUtils;
import com.ecommerce.common.utils.Md5Utils;
import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysRole;
import com.ecommerce.framework.sys.entity.SysUser;
import com.ecommerce.framework.sys.entity.SysUserRole;
import com.ecommerce.framework.sys.mapper.SysRoleMapper;
import com.ecommerce.framework.sys.mapper.SysUserMapper;
import com.ecommerce.framework.sys.mapper.SysUserRoleMapper;
import com.ecommerce.framework.sys.service.ISysConfigService;
import com.ecommerce.framework.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 业务层处理
 * 
 * @author huizhe yu
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper> implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;


    @Autowired
    private ISysConfigService configService;

    @Override
    public SysUser selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional
    public SysUser insert(SysUser sysUser) {
        sysUser.setDelFlag(UserStatus.OK.getCode());
        super.insert(sysUser);
        // 新增用户与角色管理
        insertUserRole(sysUser);
        return sysUser;
    }


    @Override
    @Transactional
    public int update(SysUser sysUser) {
        // 删除用户与角色关联
        userRoleMapper.deleteByCriteria(SysUserRole.PROPERTY_USER_ID,sysUser.getId());
        // 新增用户与角色管理
        insertUserRole(sysUser);
        return super.update(sysUser);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (Long roleId : roles) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if(CollectionUtils.isNotEmpty(list)){
                userRoleMapper.insertList(list);
            }
        }
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user) {
        return userMapper.updateUser(user);
    }

    @Override
    public SysUser selectUserByLoginName(String loginName) {
        return userMapper.selectOneByCriteria("loginName", loginName);
    }

    /**
     * 根据条件分页查询用户对象
     *
     * @param user
     *            用户信息
     *
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 导入用户数据
     *
     * @param userList
     *            用户数据列表
     * @param isUpdateSupport
     *            是否更新支持，如果已存在，则进行更新数据
     * @param operName
     *            操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigValueByKey("sys.user.initPassword");
        for (SysUser user : userList) {
            try {
                // 验证是否存在这个用户
                SysUser u = selectUserByLoginName(user.getLoginName());
                if (StringUtils.isNull(u)) {
                    user.setPassword(Md5Utils.hash(user.getLoginName() + password));
                    user.setCreateBy(operName);
                    this.insert(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 导入成功");
                } else if (isUpdateSupport) {
                    user.setUpdateBy(operName);
                    this.update(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getLoginName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 批量删除用户信息
     *
     * @param ids
     *            需要删除的数据ID
     * @return 结果
     */
    @Override
    public void deleteUserByIds(String ids) throws BusinessException {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            if (SysUser.isAdmin(userId)) {
                throw new BusinessException("不允许删除超级管理员用户");
            }
        }
        userMapper.deleteByIds(userIds);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId
     *            用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<SysRole> list = roleMapper.selectRolesByUserId(userId);
        List<String> roleNames = list.stream().map(SysRole::getRoleName).collect(Collectors.toList());
        return StringUtils.join(roleNames, ",");
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName
     *            用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        return checkPropertyUnique("loginName", loginName);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user
     *            用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        return checkPropertyUnique(user.getId(), "phoneNumber", user.getPhoneNumber());
    }

    /**
     * 校验email是否唯一
     *
     * @param user
     *            用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        return checkPropertyUnique(user.getId(), "email", user.getEmail());
    }

    /**
     * 用户状态修改
     *
     * @param user
     *            用户信息
     * @return 结果
     */
    @Override
    public void changeStatus(SysUser user) {
        if (SysUser.isAdmin(user.getId())) {
            throw new BusinessException("不允许修改超级管理员用户");
        }
        this.update(user);
    }

}
