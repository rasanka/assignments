package com.motiondesign.exception;

/**
 * @author Rasanka Jayawardana
 * Checked exception class for Pack Planner
 *
 */
public class InvalidPlanException extends PackPlannerException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPlanException() {
		super();
	}

	public InvalidPlanException(String message) {
		super(message);
	}

	public InvalidPlanException(Throwable throwable) {
		super(throwable);
	}

	public InvalidPlanException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
