package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysOperLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志 数据层
 * 
 * @author huizhe yu
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 清空操作日志
     */
    void cleanOperLog();

    void deleteBeforeDays(@Param("days") Integer days);
}
