package com.motiondesign.service;

import com.motiondesign.exception.InvalidPlanException;
import com.motiondesign.model.InputData;
import com.motiondesign.model.PackStack;

/**
 * @author Rasanka Jayawardana
 * Planner interface which defines the activities involved in planning
 *
 */
public interface IPackPlanner {

	public PackStack plan(InputData data) throws InvalidPlanException;
	
	 void printPlan(PackStack packStack) throws InvalidPlanException;
}
