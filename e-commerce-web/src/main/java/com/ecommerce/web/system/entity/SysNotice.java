package com.ecommerce.web.system.entity;


import com.ecommerce.framework.base.entity.UpdateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;

/**
 * 通知公告表 sys_notice
 *
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_notice1")
public class SysNotice extends UpdateEntity {

    /** 公告标题 */
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    private String noticeType;

    /** 公告内容 */
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    private String status;
}
