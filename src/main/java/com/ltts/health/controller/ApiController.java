package com.ltts.health.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.health.service.GreetingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(name = "API", description = "Greeting & health operations")
@Validated
@RestController
public class ApiController {

	private static final String PARAM_NAME = "name";
	private static final String KEY_STATUS = "status";
	private static final String VALUE_UP = "UP";

	private final GreetingService service;

	public ApiController(GreetingService service) {
		this.service = service;
	}

	@Operation(summary = "Greet a user", description = "Returns a greeting for the supplied name")
	@ApiResponse(responseCode = "200", description = "Greeting returned")
	@ApiResponse(responseCode = "400", description = "Missing or invalid parameter")
	@GetMapping("/greet")
	public String greet(
			@RequestParam(name = PARAM_NAME, required = true) 
			@Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only letters") String name) {
		return service.greet(name);
	}

	@Operation(summary = "Health", description = "Basic health status")
	@ApiResponse(responseCode = "200", description = "Service is healthy")
	@GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> health() {
		return Map.of(KEY_STATUS, VALUE_UP);
	}
}