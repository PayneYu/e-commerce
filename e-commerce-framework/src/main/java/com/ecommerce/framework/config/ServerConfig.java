package com.ecommerce.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ecommerce.common.utils.ServletUtils;

/**
 * 服务相关配置
 * 
 * @author huizhe yu
 *
 */
@Component
public class ServerConfig {
    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     *
     * @return 服务地址
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }
}
