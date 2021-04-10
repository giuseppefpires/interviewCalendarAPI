package com.xgeeks.exception;

public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3508507288461585731L;

	
	public InvalidUserException() {
		super("User with empty(s) input");
	}
}
