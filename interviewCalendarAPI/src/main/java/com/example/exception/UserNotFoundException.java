package com.example.exception;

public class UserNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1560583025866818456L;

	public UserNotFoundException() {
		super("User not Found");
	}

}
