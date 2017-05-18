package com.motiondesign.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Rasanka Jayawardana
 * Model class to store user input data
 *
 */
public class InputData {

	private Constraint constraint;
	private List<Item> itemList = Lists.newArrayList();
	
	public void addItem(Item item) {
		getItemList().add(item);
	}

	/**
	 * @return the constraint
	 */
	public Constraint getConstraint() {
		return constraint;
	}

	/**
	 * @param constraint the constraint to set
	 */
	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
	
	
	
}
