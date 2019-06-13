package com.ecommerce.quartz.entity;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

/**
 * 定时任务调度日志表 sys_job_log
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
public class SysJobLog extends BaseEntity {

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String jobName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    private String jobGroup;

    /** 任务方法 */
    @Excel(name = "任务方法")
    private String methodName;

    /** 方法参数 */
    @Excel(name = "方法参数")
    private String methodParams;

    /** 日志信息 */
    @Excel(name = "日志信息")
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
    private String status;

    /** 异常信息 */
    @Excel(name = "异常信息")
    private String exceptionInfo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

}
