package com.ecommerce.framework.sys.mapper;

import java.util.List;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysMenu;

/**
 * 菜单表 数据层
 * 
 * @author huizhe yu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户ID查询权限
     *
     * @param userId
     *            用户ID
     * @return 权限列表
     */
    List<String> selectPermsByUserId(Long userId);

    /**
     * 查询系统正常显示菜单（不含按钮）
     * 
     * @return 菜单列表
     */
    List<SysMenu> selectMenuNormalAll();

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId
     *            用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId
     *            角色ID
     * @return 菜单列表
     */
    List<String> selectMenuTree(Long roleId);

    /**
     * 查询系统菜单列表
     * 
     * @param menu
     *            菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId
     *            菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);
}
