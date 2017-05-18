package com.motiondesign.main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motiondesign.exception.InvalidInputException;
import com.motiondesign.exception.PackPlannerException;
import com.motiondesign.model.InputData;
import com.motiondesign.model.Item;
import com.motiondesign.model.PackStack;
import com.motiondesign.service.IPackPlanner;
import com.motiondesign.service.PackPlannerImpl;
import com.motiondesign.util.InputParser;
import com.motiondesign.util.InputReader;

/**
 * @author Rasanka Jayawardana
 * Main class for Pack Planner
 * Reading user input and generate pack's according to the 
 * User defined constraints
 */
public class PackPlannerMain {

	private static final Logger logger = LoggerFactory.getLogger(PackPlannerMain.class);

	public static void main(String... args) {
		// Reading user input line by line and constructing a list
		List<String> userInput = InputReader.read();
		IPackPlanner planner = PackPlannerImpl.getInstancec();

		try {
			// Parsing the input data and storing in an object
			InputData inputData = InputParser.parse(userInput);
			// Validating the user input
			validateUserData(inputData);
			// Starting the planner
			PackStack packs = planner.plan(inputData);
			// Print the plan
			planner.printPlan(packs);
		} catch (PackPlannerException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}

	}

	public static void validateUserData(InputData inputData) throws InvalidInputException {
		logger.info("Validating user input data!");
		if (inputData == null) {
			throw new InvalidInputException("Invalid data!");
		}

		if (inputData.getConstraint().getMaxItems() < 1) {
			throw new InvalidInputException("Invalid data: max pieces per pack should be greater than 0");
		}

		if (inputData.getConstraint().getMaxWeight() < 1) {
			throw new InvalidInputException("Invalid data: max weight per pack should be greater than 0");
		}

		for (Item item : inputData.getItemList()) {
			if (item.getWeight() > inputData.getConstraint().getMaxWeight()) {
				throw new InvalidInputException("Invalid data: max weight per pack "
						+ inputData.getConstraint().getMaxWeight() + " should be greater than item weight "
						+ item.getWeight() + " of item " + item.getId());
			}
		}
	}
}
