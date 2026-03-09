package com.ltts.health.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Map<String, Object> handleMissingParam(MissingServletRequestParameterException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "Missing required parameter");
		body.put("param", ex.getParameterName());
		body.put("message", "Parameter '" + ex.getParameterName() + "' is required");
		return body;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, Object> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "Validation failed");
		body.put("details",
				ex.getConstraintViolations().stream().map(this::formatViolation).collect(Collectors.toList()));
		return body;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Map<String, Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "Type mismatch");
		body.put("param", ex.getName());
		body.put("message", "Parameter '" + ex.getName() + "' has invalid type/value");
		return body;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> handleNotReadable(HttpMessageNotReadableException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("error", "Malformed request");
		body.put("message", "Request body or parameters could not be parsed");
		return body;
	}

	private String formatViolation(ConstraintViolation<?> v) {
		String path = v.getPropertyPath() != null ? v.getPropertyPath().toString() : "parameter";
		return path + ": " + v.getMessage();
	}
}