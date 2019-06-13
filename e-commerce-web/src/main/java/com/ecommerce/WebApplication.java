package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-03 22:58
 */
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.ecommerce.web.*.mapper", "com.ecommerce.quartz.mapper","com.ecommerce.framework.sys.mapper"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
