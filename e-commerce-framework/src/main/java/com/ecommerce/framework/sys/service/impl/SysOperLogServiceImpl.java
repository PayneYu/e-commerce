package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysOperLog;
import com.ecommerce.framework.sys.mapper.SysOperLogMapper;
import com.ecommerce.framework.sys.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志 服务层处理
 * 
 * @author huzihe yu
 */
@Service
public class SysOperLogServiceImpl extends BaseServiceImpl<SysOperLog, SysOperLogMapper> implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }
    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }

    @Override
    public void deleteBeforeDays(Integer days) {
        operLogMapper.deleteBeforeDays(days);
    }
}
