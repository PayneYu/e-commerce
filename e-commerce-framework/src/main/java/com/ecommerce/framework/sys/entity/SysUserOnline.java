package com.ecommerce.framework.sys.entity;

import com.ecommerce.common.enums.OnlineStatus;
import com.ecommerce.framework.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * 当前在线会话 sys_user_online
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_user_online")
public class SysUserOnline extends BaseEntity {

    public static final String PROPERTY_LOGIN_NAME = "loginName";

    public static final String PROPERTY_STATUS = "status";

    /** 用户会话id */
    private String sessionId;

    /** 登录名称 */
    private String loginName;

    /** 登录IP地址 */
    private String ipAddress;

    /** 登录地址 */
    private String loginLocation;

    /** 浏览器类型 */
    private String browser;

    /** 操作系统 */
    private String os;

    /** session创建时间 */
    private Date startTime;

    /** session最后访问时间 */
    private Date lastAccessTime;

    /** 超时时间，单位为分钟 */
    private Long expireTime;

    /** 在线状态 */
    private String status = OnlineStatus.on_line.getCode();

}
