package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysDictData;

/**
 * 操作日志 数据层
 * 
 * @author huizhe yu
 */
public interface SysOperLogMapper extends BaseMapper<SysDictData> {
    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
