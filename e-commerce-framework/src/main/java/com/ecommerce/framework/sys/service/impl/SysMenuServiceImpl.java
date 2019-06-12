package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysMenu;
import com.ecommerce.framework.sys.entity.SysRoleMenu;
import com.ecommerce.framework.sys.entity.SysUser;
import com.ecommerce.framework.sys.mapper.SysMenuMapper;
import com.ecommerce.framework.sys.mapper.SysRoleMenuMapper;
import com.ecommerce.framework.sys.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuMapper> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(Long menuId){
        return sysRoleMenuMapper.selectCountByCriteria(SysRoleMenu.PROPERTY_MENU_ID,menuId);
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu) {
        return sysMenuMapper.selectMenuList(menu);
    }
    /**
     * 根据用户ID查询权限
     *
     * @param userId
     *            用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> perms = sysMenuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        perms.stream().filter(perm -> StringUtils.isNotBlank(perm)).forEach(perm -> {
            permsSet.addAll(Arrays.asList(perm.trim().trim().split(",")));
        });
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenusByUser(SysUser user) {
        List<SysMenu> menus = new LinkedList<SysMenu>();
        // 管理员显示所有菜单信息
        if (user.isAdmin()) {
            menus = sysMenuMapper.selectMenuNormalAll();
        } else {
            menus = sysMenuMapper.selectMenusByUserId(user.getId());
        }
        return getChildPerms(menus, null);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list
     *            分类表
     * @param parentId
     *            传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, Long parentId) {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        list.forEach(sysMenu -> {
            if ((sysMenu.getParentId() == null && parentId == null) || sysMenu.getParentId().equals(parentId)) {
                recursionFn(list, sysMenu);
                returnList.add(sysMenu);
            }
        });
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenu n = (SysMenu)it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        return list.stream().filter(sysMenu -> t.getId().equals(sysMenu.getParentId())).collect(Collectors.toList());
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param roleId
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> roleMenuTreeData(Long roleId) {
        List<Map<String, Object>> trees = new ArrayList<>();
        List<SysMenu> menuList = sysMenuMapper.selectMenuAll();
        if (StringUtils.isNotNull(roleId)) {
            List<String> roleMenuList = sysMenuMapper.selectMenuTree(roleId);
            trees = getTrees(menuList, true, roleMenuList, true);
        } else {
            trees = getTrees(menuList, false, null, true);
        }
        return trees;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<SysMenu> menuList, boolean isCheck, List<String> roleMenuList,
        boolean permsFlag) {
        List<Map<String, Object>> trees = new ArrayList<>();
        for (SysMenu menu : menuList) {
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("id", menu.getId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck) {
                deptMap.put("checked", roleMenuList.contains(menu.getId() + menu.getPerms()));
            } else {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    public String transMenuName(SysMenu menu, List<String> roleMenuList, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> menuTreeData() {
        List<SysMenu> menuList = sysMenuMapper.selectMenuAll();
        List<Map<String, Object>> trees  = getTrees(menuList, false, null, false);
        return trees;
    }
}
