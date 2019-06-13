package com.ecommerce.quartz.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.quartz.entity.SysJob;

import java.util.List;

/**
 * 定时任务调度信息信息 服务层
 * 
 * @author huizhe yu
 */
public interface ISysJobService extends BaseService<SysJob> {
    /**
     * 获取quartz调度器的计划任务
     * 
     * @param job
     *            调度信息
     * @return 调度任务集合
     */
    List<SysJob> selectJobList(SysJob job);

    /**
     * 暂停任务
     * 
     * @param job
     *            调度信息
     * @return 结果
     */
    int pauseJob(SysJob job);

    /**
     * 恢复任务
     * 
     * @param job
     *            调度信息
     * @return 结果
     */
    int resumeJob(SysJob job);

    /**
     * 任务调度状态修改
     * 
     * @param job
     *            调度信息
     * @return 结果
     */
     int changeStatus(SysJob job);

    /**
     * 立即运行任务
     * 
     * @param job
     *            调度信息
     * @return 结果
     */
    int run(SysJob job);

    /**
     * 更新任务的时间表达式
     *
     * @param job
     *            调度信息
     */
    int updateJobCron(SysJob job);

    /**
     * 校验cron表达式是否有效
     * 
     * @param cronExpression
     *            表达式
     * @return 结果
     */
     boolean checkCronExpressionIsValid(String cronExpression);
}
