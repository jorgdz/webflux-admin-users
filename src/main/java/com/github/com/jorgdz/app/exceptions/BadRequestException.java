package com.github.com.jorgdz.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
	
	private static final long serialVersionUID = 8731055296010452074L;
		
	public BadRequestException(HttpStatus status) {
		super(status);
	}
	
	public BadRequestException(String msg, HttpStatus status) {
		super(status, msg);
	}
	
	public BadRequestException(String msg, HttpStatus status, Throwable cause) {
		super(status, msg, cause);
	}
	
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
