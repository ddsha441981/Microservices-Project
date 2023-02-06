package com.cwc.hotel.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<Object, Object>> handlerGlobalExceptionHandler(ResourceNotFoundException resourceNotFoundException){
		
		Map<Object, Object> map = new HashMap<>();
		map.put("message", resourceNotFoundException.getMessage());
		map.put("success", "false");
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		/*
		 * String message = resourceNotFoundException.getMessage(); ApiResponse
		 * apiResponse =
		 * ApiResponse.builder().message(message).success(true).status(HttpStatus.
		 * NOT_FOUND).build(); return new
		 * ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		 */
		
	}

}
