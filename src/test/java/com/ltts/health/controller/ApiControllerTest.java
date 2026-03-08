package com.ltts.health.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ltts.health.service.GreetingService;

@WebMvcTest(controllers = ApiController.class)
class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private GreetingService greetingService;

	private static final String PARAM_NAME = "name";
	private static final String KEY_STATUS = "status";
	private static final String VALUE_UP = "UP";

	@BeforeAll
	static void beforeAll() {
		System.out.println(">>> @BeforeAll: Test suite starting");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("<<< @AfterAll: Test suite finished");
	}

	@BeforeEach
	void setUp() {
		System.out.println("-- @BeforeEach: Starting a test");
	}

	@AfterEach
	void tearDown() {
		System.out.println("-- @AfterEach: Finished a test");
		Mockito.reset(greetingService);
	}

	@Test
	@DisplayName("GET /greet returns greeting from service when name is provided")
	void greet_shouldReturnGreetingFromService() throws Exception {
		final String name = "Rehan";
		final String expected = "Hello, " + name + "!";
		Mockito.when(greetingService.greet(eq(name))).thenReturn(expected);

		mockMvc.perform(get("/greet").queryParam(PARAM_NAME, name)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(equalTo(expected)));

		verify(greetingService, times(1)).greet(eq(name));
	}

	@Test
	@DisplayName("GET /greet returns 400 when name param is missing")
	void greet_shouldFailWhenNameMissing() throws Exception {
		mockMvc.perform(get("/greet")).andExpect(status().isBadRequest());
		verify(greetingService, times(0)).greet(Mockito.anyString());
	}

	@Test
	@DisplayName("GET /health returns {\"status\":\"UP\"} with application/json")
	void health_shouldReturnUpJson() throws Exception {
		mockMvc.perform(get("/health").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$." + KEY_STATUS, is(VALUE_UP)));

		verify(greetingService, times(0)).greet(Mockito.anyString());
	}
}