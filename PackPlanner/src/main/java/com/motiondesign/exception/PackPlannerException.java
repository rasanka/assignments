package com.motiondesign.exception;

/**
 * @author Rasanka Jayawardana
 * Main exception class for pack planner
 * 
 */
public class PackPlannerException extends Exception {

    /**Fs
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PackPlannerException() {
        super();
    }

    public PackPlannerException(String message) {
        super(message);
    }

    public PackPlannerException(Throwable throwable) {
        super(throwable);
    }

    public PackPlannerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
