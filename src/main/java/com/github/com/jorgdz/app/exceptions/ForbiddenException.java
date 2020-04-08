package com.github.com.jorgdz.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException {
	
	private static final long serialVersionUID = -3738009364322486603L;

	public ForbiddenException(String message) 
	{
		super(HttpStatus.FORBIDDEN, message);
	}
	
	public ForbiddenException(HttpStatus status) {
		super(status);
	}
	
	public ForbiddenException(String message, HttpStatus status) {
		super(status, message);
	}
	
	public ForbiddenException(String msg, HttpStatus status, Throwable cause) {
		super(status, msg, cause);
	}
}
