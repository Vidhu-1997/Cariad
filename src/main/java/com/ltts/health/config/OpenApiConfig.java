package com.ltts.health.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI demoOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("LTTS API").
						description("LTTS API for greeting and health checks"));
	}
}