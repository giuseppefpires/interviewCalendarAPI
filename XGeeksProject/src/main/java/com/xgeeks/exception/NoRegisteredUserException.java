package com.xgeeks.exception;


public class NoRegisteredUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1810855857567598911L;

	public NoRegisteredUserException() {
		super("No registred users");
	}
}
