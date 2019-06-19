package com.ecommerce.framework.cache.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CacheClean {

    /**
     * @return
     */
    String key() default "";

    /**
     * cacheName
     */
    String cacheName() default "";;
}
