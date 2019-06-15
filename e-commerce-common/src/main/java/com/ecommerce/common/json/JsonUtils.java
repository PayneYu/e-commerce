package com.ecommerce.common.json;

import com.ecommerce.common.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-05-09 15:37
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String objToString(T obj) {
        if (obj != null) {
            try {
                return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public static <T> T stringToObj(String str, Class<T> clazz) {
        if (StringUtils.isNotBlank(str) && clazz != null) {
            try {
                return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str, clazz);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

}
