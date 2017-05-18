package com.motiondesign.model;

import com.motiondesign.util.SortOrder;

/**
 * @author Rasanka Jayawardana
 *	Model class to store the program constraints
 */
public class Constraint {

	private float maxWeight;
	private int maxItems;
	private SortOrder sortOrder;

	/**
	 * @return the maxWeight
	 */
	public float getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight
	 *            the maxWeight to set
	 */
	public void setMaxWeight(float maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the maxItems
	 */
	public int getMaxItems() {
		return maxItems;
	}

	/**
	 * @param maxItems
	 *            the maxItems to set
	 */
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	/**
	 * @return the sortOrder
	 */
	public SortOrder getSortOrder() {
		if (sortOrder == null) {
			return SortOrder.NATURAL;
		}
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

}
