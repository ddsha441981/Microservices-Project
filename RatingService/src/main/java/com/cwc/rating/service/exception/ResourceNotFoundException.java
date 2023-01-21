package com.cwc.rating.service.exception;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource not found");
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}

}
