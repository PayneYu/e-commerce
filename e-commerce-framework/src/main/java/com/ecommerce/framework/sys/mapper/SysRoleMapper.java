package com.ecommerce.framework.sys.mapper;

import java.util.List;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysRole;

/**
 * 角色表 数据层
 * 
 * @author huizhe yu
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     *            用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 根据条件分页查询角色数据
     *
     * @param role
     *            角色信息
     * @return 角色数据集合信息
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName
     *            角色名称
     * @return 角色信息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey
     *            角色权限
     * @return 角色信息
     */
    SysRole checkRoleKeyUnique(String roleKey);
}
