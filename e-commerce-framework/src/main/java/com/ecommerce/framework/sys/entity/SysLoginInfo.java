package com.ecommerce.framework.sys.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统访问记录表 sys_login_info
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_login_info")
public class SysLoginInfo extends BaseEntity {

    /** ID */
    @Excel(name = "序号")
    private Long infoId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String loginName;

    /** 登录状态 0成功 1失败 */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String status;

    /** 登录IP地址 */
    @Excel(name = "登录地址")
    private String ipaddr;

    /** 登录地点 */
    @Excel(name = "登录地点")
    private String loginLocation;

    /** 浏览器类型 */
    @Excel(name = "浏览器")
    private String browser;

    /** 操作系统 */
    @Excel(name = "操作系统 ")
    private String os;

    /** 提示消息 */
    @Excel(name = "提示消息")
    private String msg;

    /** 访问时间 */
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}