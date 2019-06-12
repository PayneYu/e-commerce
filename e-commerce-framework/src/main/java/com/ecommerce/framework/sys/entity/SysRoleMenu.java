package com.ecommerce.framework.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author Huizhe Yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_role_menu")
public class SysRoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    public final static String PROPERTY_MENU_ID = "menuId";

    public final static String PROPERTY_ROLE_ID = "roleId";

}
