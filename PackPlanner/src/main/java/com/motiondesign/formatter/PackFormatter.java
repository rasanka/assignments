package com.motiondesign.formatter;

import com.motiondesign.model.Item;
import com.motiondesign.model.Pack;
import com.motiondesign.util.Constants;
/**
 * @author Rasanka Jayawardana
 * Implementation class for pack formatter
 */
public final class PackFormatter implements Formattable<Pack> {

	private static final Formattable<Pack> ITEM_FORMATTER = new PackFormatter();
	private Formattable<Item> itemFormatter;

	public static Formattable<Pack> getInstance() {
		return ITEM_FORMATTER;
	}

	private PackFormatter() {
		itemFormatter = ItemFormatter.getInstance();
	}

	/**
	 * Formatting Pack details
	 */
	public String formatData(final Pack data) {
		if (data == null) {
			return Constants.NEW_LINE;
		}
		StringBuilder result = new StringBuilder();
		result.append(Constants.PACK_NUMBER);
		result.append(data.getPackId());
		result.append(Constants.NEW_LINE);
		for (Item item : data.getItemStack()) {
			result.append(itemFormatter.formatData(item));
			result.append(Constants.NEW_LINE);
		}
		result.append(Constants.PACK_LENGTH);
		result.append(data.getTotalPackLength());
		result.append(Constants.UNIT_METER);
		result.append(Constants.COMMA);
		result.append(Constants.EMPTY_STRING);
		result.append(Constants.PACK_WEIGHT);
		result.append(data.getTotalPackWeight());
		result.append(Constants.UNIT_KILOGRAM);
		result.append(Constants.NEW_LINE);
		result.append(Constants.SEPARATOR);
		return result.toString();
	}

}
