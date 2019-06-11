package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysConfig;

/**
 * 参数配置 服务层
 * 
 * @author huizhe yu
 */
public interface ISysConfigService extends BaseService<SysConfig> {

    String selectConfigValueByKey(String configKey);

    SysConfig selectConfigByKey(String configKey);

    String checkConfigKeyUnique(SysConfig config);
}
