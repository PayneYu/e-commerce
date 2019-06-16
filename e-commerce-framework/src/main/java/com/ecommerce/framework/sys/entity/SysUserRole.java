package com.ecommerce.framework.sys.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 用户和角色关联 sys_user_role
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_user_role")
public class SysUserRole {
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

    public final static String PROPERTY_USER_ID = "userId";

}
