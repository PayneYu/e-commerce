package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysLoginInfo;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 * 
 * @author huizhe yu
 */
public interface ISysLoginInfoService extends BaseService<SysLoginInfo> {

    /**
     * 查询系统登录日志集合
     * 
     * @param loginInfo
     *            访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo);

    /**
     * 清空系统登录日志
     */
     void cleanLoginInfo();
}
