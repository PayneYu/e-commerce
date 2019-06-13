package com.ecommerce.quartz.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.quartz.entity.SysJobLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 调度任务日志信息 数据层
 * 
 * @author huizhe yu
 */
@Mapper
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {
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
