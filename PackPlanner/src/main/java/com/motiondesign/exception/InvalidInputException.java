package com.motiondesign.exception;

/**
 * @author Rasanka Jayawardana
 * Checked exception class for Pack Planner
 *
 */
public class InvalidInputException extends PackPlannerException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(Throwable throwable) {
		super(throwable);
	}

	public InvalidInputException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
