package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysRole;
import com.ecommerce.framework.sys.entity.SysRoleMenu;
import com.ecommerce.framework.sys.mapper.SysRoleMapper;
import com.ecommerce.framework.sys.mapper.SysRoleMenuMapper;
import com.ecommerce.framework.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional
    public SysRole insert(SysRole sysRole) {
        super.insert(sysRole);
        insertRoleMenu(sysRole.getId(),sysRole.getMenuIds());
        return sysRole;
    }

    private void insertRoleMenu(Long roleId,Long[] menuIds){
        if(menuIds!=null&&menuIds.length>0){
            List<SysRoleMenu> list = new ArrayList<>();
            for (Long menuId : menuIds) {
                SysRoleMenu menu = new SysRoleMenu();
                menu.setRoleId(roleId);
                menu.setMenuId(menuId);
                list.add(menu);
            }
            sysRoleMenuMapper.insertList(list);
        }
    }

    @Override
    public int deleteById(Long id) {
        sysRoleMenuMapper.deleteByCriteria(SysRoleMenu.PROPERTY_ROLE_ID,id);
        return super.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        sysRoleMenuMapper.deleteByProperties(SysRoleMenu.PROPERTY_ROLE_ID,ids);
        super.deleteByIds(ids);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        sysRoleMenuMapper.deleteByProperties(SysRoleMenu.PROPERTY_ROLE_ID,Arrays.asList(ids));
        super.deleteByIds(ids);
    }

    @Override
    public int update(SysRole sysRole) {
        int i = super.update(sysRole);
        if(i>0){
            // 删除角色与菜单关联
            sysRoleMenuMapper.deleteByCriteria(SysRoleMenu.PROPERTY_ROLE_ID,sysRole.getId());
            insertRoleMenu(sysRole.getId(),sysRole.getMenuIds());
        }
        return i;
    }

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return sysRoleMapper.selectRoleList(role);
    }
    /**
     * 根据用户ID查询权限
     * 
     * @param userId
     *            用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId) {
        List<SysRole> roles = sysRoleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        roles.stream().filter(role -> StringUtils.isNotBlank(role.getRoleKey())).forEach(role -> {
            permsSet.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
        });
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     *            用户ID
     * @return 角色列表
     */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = sysRoleMapper.selectByCriteria("delFlag", "0");
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getId().equals(userRole.getId())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

}
