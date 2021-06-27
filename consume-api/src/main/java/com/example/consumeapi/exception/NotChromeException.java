package com.example.consumeapi.exception;

public class NotChromeException extends Exception{
	
	private String userAgent;
	
	public NotChromeException(String userAgent) {
		super(String.format("This application only works for Chrome. Unfortunately you are using: %s",userAgent));
	}
}
