package com.ecommerce.framework.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.common.annotation.Excel.Type;
import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.entity.UpdateEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户对象 sys_user
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_user")
public class SysUser extends UpdateEntity {

    /** 登录名称 */
    @Excel(name = "登录名称")
    private String loginName;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 用户头像 */
    private String avatar = StringUtils.EMPTY;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    @Excel(name = "最后登陆IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登陆时间 */
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    public boolean isAdmin() {
        return isAdmin(this.getId());
    }

}
