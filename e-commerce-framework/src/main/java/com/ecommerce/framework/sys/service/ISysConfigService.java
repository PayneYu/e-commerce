package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysConfig;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author huizhe yu
 */
public interface ISysConfigService extends BaseService<SysConfig> {

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfig> selectConfigList(SysConfig config);;

    String selectConfigValueByKey(String configKey);

    SysConfig selectConfigByKey(String configKey);

    String checkConfigKeyUnique(SysConfig config);
}
