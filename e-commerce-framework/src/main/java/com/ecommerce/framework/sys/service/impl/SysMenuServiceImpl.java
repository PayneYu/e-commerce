package com.ecommerce.framework.sys.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysMenu;
import com.ecommerce.framework.sys.entity.SysUser;
import com.ecommerce.framework.sys.mapper.SysMenuMapper;
import com.ecommerce.framework.sys.service.ISysMenuService;

/**
 * 菜单 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuMapper> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

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

}
