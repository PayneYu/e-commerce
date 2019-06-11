package com.ecommerce.framework.sys.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysRole;
import com.ecommerce.framework.sys.mapper.SysRoleMapper;
import com.ecommerce.framework.sys.service.ISysRoleService;

/**
 * 角色 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

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
