package com.example.shopapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class PaidOrderException extends RuntimeException {
    private static String errorName = "Cannot Cancel Paid Order.";
    
    public PaidOrderException() {
    	super(String.format("%s",errorName));
    }

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
   
}