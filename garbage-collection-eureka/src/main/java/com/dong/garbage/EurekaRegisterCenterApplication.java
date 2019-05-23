package com.dong.garbage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisterCenterApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EurekaRegisterCenterApplication.class, args);
		System.out.println("项目启动完成");
	}

}