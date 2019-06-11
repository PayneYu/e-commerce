package com.ecommerce.common.utils;

import java.io.IOException;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author: Huizhe Yu
 * @create: 2019-06-10 22:16
 */
public class PropertiesUtil {

    public static String getValue(String fileName, String key) {
        String value = null;
        try {
            value = PropertiesLoaderUtils.loadAllProperties(fileName).getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
