package com.ecommerce.generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 *
 * @author huizhe.yu
 */
@Getter
@Setter
@Component
public class GeneratorConfig {

    /**
     * 获取作者
     */
    public final static String AUTHOR = "huizhe yu";

    /**
     * 获取版权年份
     */
    public final static String MODULE_NAME = "sys";

    /**
     * 获取版权年份
     */
    public final static String PACKAGE_NAME = "com.ecommerce.framework";
    /**
     * 是否自动去除表前缀
     */
    public final static Boolean AUTORE_MOVE_PRE = false;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public final static String TABLE_PREFIX = "";

}
