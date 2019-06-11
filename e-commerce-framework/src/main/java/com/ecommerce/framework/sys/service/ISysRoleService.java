package com.ecommerce.framework.sys.service;

import java.util.List;
import java.util.Set;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysRole;

/**
 * 角色业务层
 * 
 * @author huizhe yu
 */
public interface ISysRoleService extends BaseService<SysRole> {
    /**
     * 根据用户ID查询角色
     *
     * @param userId
     *            用户ID
     * @return 权限列表
     */
    Set<String> selectRoleKeys(Long userId);

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     *            用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);
}
