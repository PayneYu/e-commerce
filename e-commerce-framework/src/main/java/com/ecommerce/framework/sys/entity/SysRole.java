package com.ecommerce.framework.sys.entity;

import javax.persistence.Table;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.UpdateEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色表 sys_role
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_role")
public class SysRole extends UpdateEntity {

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色权限 */
    @Excel(name = "角色权限")
    private String roleKey;

    /** 角色排序 */
    @Excel(name = "角色排序")
    private String roleSort;

    /** 数据范围（1：所有数据权限；2：自定数据权限） */
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限")
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 备注 */
    private String remark;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private Long[] menuIds;

}
