package com.ecommerce.framework.cache.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CacheGet {

    /**
     * cacheName
     */
    String cacheName() default "";
    /**
     * key
     */
    String key() default "";

    /**
     * expire time
     */
    int expire() default 0;


}
