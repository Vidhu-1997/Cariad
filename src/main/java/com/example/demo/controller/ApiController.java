package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.GreetingService;

@RestController
public class ApiController {
	private final GreetingService service;

	public ApiController(GreetingService service) {
		this.service = service;
	}

	@GetMapping("/greet")
	public String greet(@RequestParam String name) {
		return service.greet(name);
	}

	@GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> health() {
		return Map.of("status", "UP");
	}
}
