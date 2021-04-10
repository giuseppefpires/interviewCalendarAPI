package com.example.exception;

public class InvalidAvailabilityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3117438102395685091L;

	public InvalidAvailabilityException() {
		super("Invalid availability input");
	}
}
