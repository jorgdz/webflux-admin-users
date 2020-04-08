package com.github.com.jorgdz.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictException extends ResponseStatusException {

	private static final long serialVersionUID = 3102454306822171794L;
	
	public ConflictException(String message) 
	{
		super(HttpStatus.CONFLICT, message);
	}
	
	public ConflictException(String msg, HttpStatus status) {
		super(status, msg);
	}
	
	public ConflictException(HttpStatus status) {
		super(status);
	}
	
	public ConflictException(String msg, HttpStatus status, Throwable cause) {
		super(status, msg, cause);
	}
}
