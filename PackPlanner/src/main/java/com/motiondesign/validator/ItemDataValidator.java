package com.motiondesign.validator;

import com.motiondesign.exception.InvalidInputException;
import com.motiondesign.model.Item;
import com.motiondesign.util.Constants;
/**
 * @author Rasanka Jayawardana
 *	Validating item data 
 * [Item ID, Item Length, Item Quantity, Item Weight]
 */
public class ItemDataValidator implements IValidator<Item> {

	@Override
	public Item validate(String data) throws InvalidInputException {
		Item result = new Item();
		if (data != null) {
			String[] itemValues = data.split(Constants.COMMA);
			if (itemValues != null && itemValues.length == 4) {
				result.setId(validateIntValue(itemValues[0], "Invalid item id!"));
				result.setLength(validateIntValue(itemValues[1],
						"Invalid item length value for item id :" + result.getId() + "!"));
				result.setQuantity(validateIntValue(itemValues[2],
						"Invalid item quantity value for item id :" + result.getId() + "!"));
				result.setWeight(validateFloatValue(itemValues[3],
						"Invalid item weight value for item id: " + result.getId() + " !"));
			} else {
				throw new InvalidInputException("Invalid item format,"
						+ " correct format is '[item id],[item length],[item quantity],[piece weight]'");
			}

		}
		return result;
	}

	private static int validateIntValue(String value, String errorMsg) throws InvalidInputException {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new InvalidInputException(errorMsg, e);
		}
		return result;
	}

	private static Float validateFloatValue(String value, String errorMsg) throws InvalidInputException {
		Float result = 0.0f;
		try {
			result = Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new InvalidInputException(errorMsg, e);
		}
		return result;
	}
}
