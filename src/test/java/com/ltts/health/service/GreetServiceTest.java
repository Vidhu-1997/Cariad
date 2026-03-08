package com.ltts.health.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

@DisplayName("GreetingService Tests")
class GreetingServiceTest {

	private GreetingService greetingService;

	@BeforeAll
	static void beforeAll() {
		System.out.println(">>> @BeforeAll: Starting GreetingService test suite");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("<<< @AfterAll: Finished GreetingService test suite");
	}

	@BeforeEach
	void setUp() {
		greetingService = new GreetingService();
		System.out.println("-- @BeforeEach: New GreetingService instance created");
	}

	@AfterEach
	void tearDown() {
		System.out.println("-- @AfterEach: Test finished");
	}

	@Test
	@DisplayName("returns default greeting when name is null")
	void greet_returnsDefault_whenNameNull() {
		String result = greetingService.greet(null);
		assertEquals("Hello Guest", result);
	}

	@Test
	@DisplayName("returns default greeting when name is blank")
	void greet_returnsDefault_whenNameBlank() {
		String result = greetingService.greet("   ");
		assertEquals("Hello Guest", result);
	}
}