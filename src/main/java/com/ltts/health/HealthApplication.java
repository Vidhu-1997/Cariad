package com.ltts.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "LTTS API", 
description = "LTTS API for greeting and health checks"))

@SpringBootApplication
public class HealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealthApplication.class, args);
	}
}