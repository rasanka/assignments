package com.motiondesign.formatter;

/**
 * @author Rasanka Jayawardana
 * Common Interface to format pack planner out put
 * @param <E>
 */
public interface Formattable<E> {
	String formatData(E data);
}
