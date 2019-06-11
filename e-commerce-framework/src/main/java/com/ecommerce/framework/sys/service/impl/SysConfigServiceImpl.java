package com.ecommerce.framework.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysConfig;
import com.ecommerce.framework.sys.mapper.SysConfigMapper;
import com.ecommerce.framework.sys.service.ISysConfigService;

/**
 * 参数配置 服务层实现
 * 
 * @author huizhe yu
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, SysConfigMapper> implements ISysConfigService {

    @Autowired
    private SysConfigMapper configMapper;

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey
     *            参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigValueByKey(String configKey) {
        SysConfig config = selectConfigByKey(configKey);
        return config == null ? StringUtils.EMPTY : config.getConfigValue();
    }

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey
     *            参数key
     * @return 参数键值
     */
    @Override
    public SysConfig selectConfigByKey(String configKey) {
        return configMapper.selectOneByCriteria(SysConfig.PROPERTY_CONFIG_KEY, configKey);
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config
     *            参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        return this.checkPropertyUnique(config.getId(), SysConfig.PROPERTY_CONFIG_KEY, config.getConfigKey());
    }
}
