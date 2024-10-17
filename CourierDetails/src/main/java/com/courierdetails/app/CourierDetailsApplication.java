package com.courierdetails.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJms
@ComponentScan
public class CourierDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourierDetailsApplication.class, args);
	}
}
