package com.taras.exceptions;

public class ServiceNotExistsException extends IllegalArgumentException {
	
	public ServiceNotExistsException(String msg) {
		super(msg); 
	}

}
