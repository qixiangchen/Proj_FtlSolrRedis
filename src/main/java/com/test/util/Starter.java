package com.test.util;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //SpringBoot程序固定注解
@MapperScan("com.test.mapper") //扫描Mybatis定义Mapper接口
@ComponentScan("com.test.ctrl,com.test.service.impl,com.test.util") //扫描Spring包路径
@EnableScheduling
public class Starter {

	public static void main(String[] args) {
		SpringApplication.run(Starter.class,args);
	}

}
