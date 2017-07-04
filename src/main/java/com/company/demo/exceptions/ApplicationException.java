package com.company.demo.exceptions;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = -4328520454202288652L;
	
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
