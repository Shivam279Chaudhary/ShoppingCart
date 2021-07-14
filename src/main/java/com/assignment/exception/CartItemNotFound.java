package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFound extends RuntimeException {
	public CartItemNotFound(String message ) {
		super(message);
	}
}
