package com.motiondesign.util;

import com.motiondesign.exception.InvalidInputException;

/**
 * @author Rasanka Jayawardana
 * Utility class to maintain Sorting order
 *
 */
public enum SortOrder {

	NATURAL("NATURAL"), 
	SHORT_TO_LONG("SHORT_TO_LONG"), 
	LONG_TO_SHORT("LONG_TO_SHORT");

	private String value;
	
	SortOrder(String value) {
		this.value = value;
	}

	/**
	 * Return the sort order based in the string
	 *
	 * @param value
	 *            sort order as a string value
	 * @return SortOrder enum value
	 * @throws InvalidInputException
	 */
	public static SortOrder getByValue(String value) throws InvalidInputException {
		for (SortOrder sortOrder : values()) {
			if (sortOrder.getValue().equalsIgnoreCase(value)) {
				return sortOrder;
			}
		}
		throw new InvalidInputException(" Invalid sort order! "
				+ "Please enter one of the following options [NATURAL,SHORT_TO_LONG,LONG_TO_SHORT] ");
	}

	private String getValue() {
		return this.value;
	}
}
