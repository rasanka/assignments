package com.motiondesign.validator;

import com.motiondesign.exception.InvalidInputException;

/**
 * @author Rasanka Jayawardana
 * Common interface to validate Items and  Constraints
 */
public interface IValidator<T> {

	T validate(String data) throws InvalidInputException;
}
