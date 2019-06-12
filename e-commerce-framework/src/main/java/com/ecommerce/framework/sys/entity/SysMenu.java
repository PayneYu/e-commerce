package com.ecommerce.framework.sys.entity;

import com.ecommerce.framework.base.entity.UpdateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_menu")
public class SysMenu extends UpdateEntity {

    /** 菜单名称 */
    private String menuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private String orderNum;

    /** 菜单URL */
    private String url;

    /** 类型:0目录,1菜单,2按钮 */
    private String menuType;

    /** 菜单状态:0显示,1隐藏 */
    private String visible;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();


    public final static String PROPERTY_PARENT_ID = "parentId";


    public final static String PROPERTY_MENU_NAME = "menuName";

}
