package com.order.item.exceptions;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
        super(message);
    }
	
	public OrderNotFoundException(String id, String description) {
        super(description + id);
    }
}
