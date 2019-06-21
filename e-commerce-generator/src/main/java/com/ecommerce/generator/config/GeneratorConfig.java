package com.ecommerce.generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${generate.comment.author}")
    public String author;

    /**
     * 获取版权moduleName
     */
    @Value("${generate.moduleName}")
    public String moduleName;

    /**
     * 获取版packageName
     */
    @Value("${generate.package.name}")
    public String packageName;
    /**
     * 是否自动去除表前缀
     */
    public final static Boolean AUTORE_MOVE_PRE = false;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public final static String TABLE_PREFIX = "";

}
