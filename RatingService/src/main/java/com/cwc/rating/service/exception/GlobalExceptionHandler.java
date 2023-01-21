package com.cwc.rating.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> resourceNotFoundHandler(ResourceNotFoundException resourceNotFoundException){
		
		Map map = new HashMap<>();
		map.put("success", false);
		map.put("message", resourceNotFoundException.getMessage());
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}
}
