package com.ecommerce.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: supplycenter
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-03-06 11:02
 */
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages = {"com.thermofisher.dsc.generator.mapper"})
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
