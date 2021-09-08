package com.example.registerbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RegisterBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterBeanApplication.class, args);
	}
}
