package com.ecommerce.quartz.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.quartz.entity.SysJob;

import java.util.List;

/**
 * 调度任务信息 数据层
 * 
 * @author huizhe yu
 */
public interface SysJobMapper extends BaseMapper<SysJob> {
    /**
     * 查询调度任务日志集合
     * 
     * @param job
     *            调度信息
     * @return 操作日志集合
     */
    List<SysJob> selectJobList(SysJob job);

}
