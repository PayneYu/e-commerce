package com.ecommerce.quartz.service.impl;

import com.ecommerce.common.constant.ScheduleConstants;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.quartz.entity.SysJob;
import com.ecommerce.quartz.mapper.SysJobMapper;
import com.ecommerce.quartz.service.ISysJobService;
import com.ecommerce.quartz.util.CronUtils;
import com.ecommerce.quartz.util.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 * 
 * @author huizhe yu
 */
@Service
public class SysJobServiceImpl extends BaseServiceImpl<SysJob, SysJobMapper> implements ISysJobService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<SysJob> jobList = jobMapper.selectAll();
        for (SysJob job : jobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     * 
     * @param job
     *            调度信息
     * @return
     */
    @Override
    public List<SysJob> selectJobList(SysJob job) {
        return jobMapper.selectJobList(job);
    }

    /**
     * 暂停任务
     * 
     * @param job
     *            调度信息
     */
    @Override
    public int pauseJob(SysJob job) {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = update(job);
        if (rows > 0) {
            ScheduleUtils.pauseJob(scheduler, job.getId());
        }
        return rows;
    }

    /**
     * 恢复任务
     * 
     * @param job
     *            调度信息
     */
    @Override
    public int resumeJob(SysJob job) {
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = update(job);
        if (rows > 0) {
            ScheduleUtils.resumeJob(scheduler, job.getId());
        }
        return rows;
    }

    @Override
    public int deleteById(Long id) {
        int rows = deleteById(id);
        if (rows > 0) {
            ScheduleUtils.deleteScheduleJob(scheduler, id);
        }
        return rows;
    }

    /**
     * 任务调度状态修改
     * 
     * @param job
     *            调度信息
     */
    @Override
    public int changeStatus(SysJob job) {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务
     * 
     * @param job
     *            调度信息
     */
    @Override
    public int run(SysJob job) {
        return ScheduleUtils.run(scheduler, selectById(job.getId()));
    }

    @Override
    public SysJob insert(SysJob job) {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        super.insert(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
        return job;
    }

    /**
     * 更新任务的时间表达式
     * 
     * @param job
     *            调度信息
     */
    @Override
    public int updateJobCron(SysJob job) {
        int rows = update(job);
        if (rows > 0) {
            ScheduleUtils.updateScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 校验cron表达式是否有效
     * 
     * @param cronExpression
     *            表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}
