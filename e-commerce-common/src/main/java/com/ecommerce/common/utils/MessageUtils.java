package com.ecommerce.common.utils;

import org.springframework.context.MessageSource;

import com.ecommerce.common.utils.spring.SpringContextHolder;

/**
 * 获取i18n资源文件
 *
 * @author huizhe yu
 */
public class MessageUtils {
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code
     *            消息键
     * @param args
     *            参数
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringContextHolder.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, null);
    }
}
