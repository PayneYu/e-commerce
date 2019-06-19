package com.ecommerce.framework.cache.annotation;

import java.lang.annotation.*;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-18 23:34
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CachePage {
    /**
     * cacheName
     */
    String cacheName() default "";;
}
