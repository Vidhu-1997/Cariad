package com.ltts.health.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
	private static final String DEFAULT_GREETING = "Hello Guest";
	private static final String GREETING_PREFIX = "Hello ";

	public String greet(String name) {
		if (name == null || name.isBlank()) {
			return DEFAULT_GREETING;
		}
		return GREETING_PREFIX + name;
	}
}