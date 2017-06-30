package com.company.demo.exceptions;

public class ApplicationException extends Exception {

	public ApplicationException(){
		super();
	}
	public ApplicationException(String message) {
		super(message);
	}
	public ApplicationException(Throwable cause) {
		super(cause);
	}
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
