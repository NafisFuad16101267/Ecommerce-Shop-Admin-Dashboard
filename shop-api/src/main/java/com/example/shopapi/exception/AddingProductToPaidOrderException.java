package com.example.shopapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class AddingProductToPaidOrderException extends RuntimeException {
    private static String errorName = "Cannot add new Product to paid Order. Create a new order";
    
    public AddingProductToPaidOrderException() {
    	super(String.format("%s",errorName));
    }

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
   
}