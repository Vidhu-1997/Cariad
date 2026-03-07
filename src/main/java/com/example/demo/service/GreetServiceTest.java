package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreetServiceTest {
	public GreetingService service = new GreetingService();

	@Test
	void testGreeting() {
		String result = service.greet("Alex");
		assertEquals("Hello Alex", result);
	}

	@Test
	void testEmptyName() {
		String result = service.greet("");
		assertEquals("Hello Guest", result);
	}
}
