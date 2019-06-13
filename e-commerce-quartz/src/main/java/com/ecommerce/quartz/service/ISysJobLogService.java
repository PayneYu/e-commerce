package com.ecommerce.quartz.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.quartz.entity.SysJobLog;

import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 * 
 * @author huizhe yu
 */
public interface ISysJobLogService extends BaseService<SysJobLog> {
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog
     *            调度日志信息
     * @return 调度任务日志集合
     */
    List<SysJobLog> selectJobLogList(SysJobLog jobLog);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
