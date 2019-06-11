package com.ecommerce.framework.sys.service;

import java.util.List;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysUser;

/**
 * 用户 业务层
 * 
 * @author huizhe yu
 */
public interface ISysUserService extends BaseService<SysUser> {

    /**
     * 根据条件分页查询用户对象
     *
     * @param user
     *            用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName
     *            用户名
     * @return 用户对象信息
     */
    SysUser selectUserByLoginName(String userName);

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
    String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    /**
     * 批量删除用户信息
     *
     * @param ids
     *            需要删除的数据ID
     * @return 结果
     * @throws Exception
     *             异常
     */
    void deleteUserByIds(String ids);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userId
     *            用户ID
     * @return 结果
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName
     *            登录名称
     * @return 结果
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user
     *            手机号码
     * @return 结果
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user
     * @return 结果
     */
    String checkEmailUnique(SysUser user);

    /**
     * 用户状态修改
     *
     * @param user
     *            用户信息
     * @return 结果
     */
    void changeStatus(SysUser user);

}
