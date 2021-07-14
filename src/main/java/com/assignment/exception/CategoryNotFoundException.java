package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
