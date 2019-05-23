package com.dong.garbage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//开启Eureka配置，将此服务注册到eureka注册中心
@EnableEurekaClient
public class MallApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(MallApplication.class, args);
	}
}