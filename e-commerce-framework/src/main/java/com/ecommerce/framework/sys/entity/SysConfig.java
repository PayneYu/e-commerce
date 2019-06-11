package com.ecommerce.framework.sys.entity;

import javax.persistence.Table;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.UpdateEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数配置表 sys_config
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_config")
public class SysConfig extends UpdateEntity {

    public final static String PROPERTY_CONFIG_KEY = "configKey";
    /** 参数名称 */
    @Excel(name = "参数名称")
    private String configName;
    /** 参数键名 */
    @Excel(name = "参数键名")
    private String configKey;
    /** 参数键值 */
    @Excel(name = "参数键值")
    private String configValue;
    /** 系统内置（Y是 N否） */
    @Excel(name = "系统内置", readConverterExp = "Y=是,N=否")
    private String configType;

}
