package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysOperLog;

import java.util.List;

/**
 * 操作日志 服务层
 * 
 * @author huizhe yu
 */
public interface ISysOperLogService extends BaseService<SysOperLog> {

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog
     *            操作日志对象
     * @return 操作日志集合
     */
    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
