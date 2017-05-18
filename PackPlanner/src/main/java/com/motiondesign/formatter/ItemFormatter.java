package com.motiondesign.formatter;

import com.motiondesign.model.Item;
import com.motiondesign.util.Constants;
/**
 * @author Rasanka Jayawardana
 * Implementation class for Item formatter
 */
public final class ItemFormatter implements Formattable<Item> {

	private static final Formattable<Item> ITEM_FORMATTER = new ItemFormatter();

	public static Formattable<Item> getInstance() {
		return ITEM_FORMATTER;
	}

	/**
	 * Format given item data
	 */
	public String formatData(Item data) {
		if (data == null) {
			return Constants.NEW_LINE;
		}
		StringBuilder result = new StringBuilder();
		result.append(data.getId());
		result.append(Constants.COMMA);
		result.append(data.getLength());
		result.append(Constants.COMMA);
		result.append(data.getQuantity());
		result.append(Constants.COMMA);
		result.append(data.getWeight());
		result.append(Constants.NEW_LINE);
		return result.toString();
	}
}
