package com.github.com.jorgdz.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(HttpStatus status) {
		super(status);
	}
	
	public NotFoundException(String msg, HttpStatus status) {
		super(status, msg);
	}
	
	public NotFoundException(String msg, HttpStatus status,Throwable cause) {
		super(status, msg, cause);
	}
	
	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
