package com.ecommerce.framework.sys.service;

import java.util.List;
import java.util.Set;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysMenu;
import com.ecommerce.framework.sys.entity.SysUser;

/**
 * 菜单 业务层
 * 
 * @author huizhe yu
 */
public interface ISysMenuService extends BaseService<SysMenu> {

    Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @param user
     *            用户信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenusByUser(SysUser user);

}
