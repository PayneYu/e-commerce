package com.ecommerce.quartz.entity;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.common.constant.ScheduleConstants;
import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.entity.UpdateEntity;
import com.ecommerce.quartz.util.CronUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 定时任务调度表 sys_job
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
public class SysJob extends UpdateEntity {

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

    /** cron执行表达式 */
    @Excel(name = "执行表达式")
    private String cronExpression;

    /** cron计划策略 */
    @Excel(name = "计划策略 ")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 任务状态（0正常 1暂停） */
    @Excel(name = "任务状态", readConverterExp = "0=正常,1=暂停")
    private String status;
    @Excel(name = "备注")
    private String remark;

    public Date getNextValidTime() {
        if (StringUtils.isNotBlank(cronExpression)) {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }

}
