package com.motiondesign.validator;

import java.util.Arrays;

import com.motiondesign.exception.InvalidInputException;
import com.motiondesign.model.Constraint;
import com.motiondesign.util.Constants;
import com.motiondesign.util.SortOrder;

/**
 * @author Rasanka Jayawardana
 * Validating the first row of the user input It has specific format [Sorting
 * Order],[Max Items per Pack] [Max Weight per pack]
 *
 */
public class ConstraintValidator implements IValidator<Constraint> {

	public Constraint validate(String data) throws InvalidInputException {

		Constraint constraint = new Constraint();
		if (data != null) {
			String[] values = data.split(Constants.COMMA);
			if (values != null && values.length == 3) {
				String sortOrderStr = values[0];
				String maxItemsStr = values[1];
				String maxWeightStr = values[2];

				if (isValidSortOrder(sortOrderStr)) {
					constraint.setSortOrder(SortOrder.getByValue(sortOrderStr));
				} else {
					throw new InvalidInputException(
							"Invalid Sort Order! Valid options are  [NATURAL,SHORT_TO_LONG,LONG_TO_SHORT] ");
				}

				constraint.setMaxItems(validateMaxItems(maxItemsStr));
				constraint.setMaxWeight(validateMaxWeight(maxWeightStr));

				return constraint;
			}
		}

		throw new InvalidInputException(
				"Invalid Input! The accepted values are [Sort Order, Max items per pack, Max weight per pack] ");
	}

	private boolean isValidSortOrder(String sortOrder) {
		return Arrays.stream(SortOrder.values()).anyMatch(e -> e.name().equals(sortOrder));
	}

	private int validateMaxItems(String items) throws InvalidInputException {
		int result = 0;
		try {
			result = Integer.parseInt(items);
		} catch (NumberFormatException e) {
			throw new InvalidInputException("Invalid maximum items value");
		}
		return result;
	}

	private float validateMaxWeight(String weight) throws InvalidInputException {
		float result = 0;
		try {
			result = Float.parseFloat(weight);
		} catch (NumberFormatException e) {
			throw new InvalidInputException("Invalid maximum weight value");
		}
		return result;
	}
}
