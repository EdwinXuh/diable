package com.ds.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author raptor
 * @description SystemApplication
 * @date 2021/8/8 15:30
 */
@SpringBootApplication
@MapperScan("com.ds.system.mapper")
@ComponentScan(value = "com.ds")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }
}
