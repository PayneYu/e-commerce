package com.ecommerce.framework.cache.util;


import com.ecommerce.common.page.PageDomain;
import com.ecommerce.common.page.TableSupport;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-05-17 15:19
 */
public class KeyGeneratorUtil {

    public static String generateKey(Method method, Object[] args){
        StringBuilder sb = new StringBuilder();
        sb.append(method.getName());
        for (Object obj : args) {
            sb.append(":" + String.valueOf(obj));
        }
        return String.valueOf(sb);
    }

    public static String generatePageKey(Object[] args){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        StringBuilder sb = new StringBuilder();
        sb.append("pageNum:"+pageDomain.getPageNum());
        sb.append("pageSize:"+pageDomain.getPageSize());
        sb.append("orderBy:"+pageDomain.getOrderBy());
        sb.append("orderByColumn:"+pageDomain.getOrderByColumn());
        sb.append("isAsc:"+pageDomain.getIsAsc());
        for (Object obj : args) {
            sb.append(":" + String.valueOf(obj));
        }
        return String.valueOf(sb);
    }
}
