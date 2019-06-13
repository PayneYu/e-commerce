package com.ecommerce.quartz.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.quartz.entity.SysJobLog;
import com.ecommerce.quartz.mapper.SysJobLogMapper;
import com.ecommerce.quartz.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author huizhe yu
 */
@Service
public class SysJobLogServiceImpl extends BaseServiceImpl<SysJobLog, SysJobLogMapper> implements ISysJobLogService {
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog
     *            调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog) {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
