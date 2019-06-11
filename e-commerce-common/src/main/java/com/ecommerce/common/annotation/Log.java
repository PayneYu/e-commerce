package com.ecommerce.common.annotation;

import java.lang.annotation.*;

import com.ecommerce.common.enums.BusinessType;
import com.ecommerce.common.enums.OperatorType;

/**
 * 自定义操作日志记录注解
 *
 * @author huizhe yu
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
