package com.motiondesign.util;

import java.util.List;

import com.motiondesign.exception.InvalidInputException;
import com.motiondesign.model.InputData;
import com.motiondesign.model.Item;
import com.motiondesign.model.Constraint;
import com.motiondesign.validator.IValidator;
import com.motiondesign.validator.ItemDataValidator;
import com.motiondesign.validator.ConstraintValidator;

/**
 * @author Rasanka Jayawardana
 * Utility class to parse the user input
 *
 */
public class InputParser {

	public static InputData parse(List<String> inputData) throws InvalidInputException {
		
		InputData validInput = new InputData();
		int i = 0;
		IValidator<Item> itemDataValidator = new ItemDataValidator();
		for(String data : inputData) {
			
			if(i == 0) { // Validating the first line of user input
				IValidator<Constraint> constraintValidator = new ConstraintValidator();
				validInput.setConstraint(constraintValidator.validate(data));
			} else { // Validating the item data
				validInput.addItem(itemDataValidator.validate(data));
			}
			i++;
		}
		
		return validInput;
	}
}
