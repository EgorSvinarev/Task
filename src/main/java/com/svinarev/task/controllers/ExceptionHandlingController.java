package com.svinarev.task.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;


@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
		String message = "Invalid values in these fields: ";
		
		for (FieldError err: e.getFieldErrors()) {
			message += "\n";
			message += err.getField() + ": " + err.getRejectedValue();
		}
		
		return ResponseEntity.ok(message);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpMethodException(HttpRequestMethodNotSupportedException e) {
		String message = "Incorrect HTTP method:" + e.getMethod();
		
		return ResponseEntity.ok(message);
	}
	
}
