package com.ecommerce.quartz.util;

import org.quartz.*;

import com.ecommerce.common.constant.ScheduleConstants;
import com.ecommerce.common.exception.job.TaskException;
import com.ecommerce.quartz.entity.SysJob;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务工具类
 * 
 * @author huizhe yu
 *
 */
@Slf4j
public class ScheduleUtils {

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger)scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("getCronTrigger 异常：", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, SysJob job) {
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(job.getId())).build();

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(job.getId()))
                .withSchedule(cronScheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);

            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
                pauseJob(scheduler, job.getId());
            }
        } catch (SchedulerException e) {
            log.error("createScheduleJob 异常：", e);
        } catch (TaskException e) {
            log.error("createScheduleJob 异常：", e);
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, SysJob job) {
        try {
            TriggerKey triggerKey = getTriggerKey(job.getId());

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(job, cronScheduleBuilder);

            CronTrigger trigger = getCronTrigger(scheduler, job.getId());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

            // 参数
            trigger.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, job);

            scheduler.rescheduleJob(triggerKey, trigger);

            // 暂停任务
            if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
                pauseJob(scheduler, job.getId());
            }

        } catch (SchedulerException e) {
            log.error("SchedulerException 异常：", e);
        } catch (TaskException e) {
            log.error("SchedulerException 异常：", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static int run(Scheduler scheduler, SysJob job) {
        int rows = 0;
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleConstants.TASK_PROPERTIES, job);

            scheduler.triggerJob(getJobKey(job.getId()), dataMap);
            rows = 1;
        } catch (SchedulerException e) {
            log.error("run 异常：", e);
        }
        return rows;
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("pauseJob 异常：", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("resumeJob 异常：", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("deleteScheduleJob 异常：", e);
        }
    }

    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job, CronScheduleBuilder cb)
        throws TaskException {
        switch (job.getMisfirePolicy()) {
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cb;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException(
                    "The task misfire policy '" + job.getMisfirePolicy() + "' cannot be used in cron schedule tasks",
                    TaskException.Code.CONFIG_ERROR);
        }
    }
}
