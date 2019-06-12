package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysLoginInfo;

import java.util.List;

/**
 * 系统访问日志情况信息 数据层
 * 
 * @author huizhe yu
 */
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfo> {
    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLoginInfo();
}
