package com.cwc.user.service.exception;

//import org.json.JSONObject;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cwc.user.service.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
		String message = resourceNotFoundException.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(FeignException.BadRequest.class)
//	public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
//        response.setStatus(e.status());
//        return new JSONObject(e.contentUTF8()).toMap();
//	}
}
