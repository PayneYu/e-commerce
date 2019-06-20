package com.ecommerce.framework.cache.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-19 20:48
 */
@Getter
@Setter
@ToString
public class CacheBean {

    private String cacheName;

    private String key;

    private String value;
}
