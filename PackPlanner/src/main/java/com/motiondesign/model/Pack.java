package com.motiondesign.model;

import java.util.Stack;

/**
 * @author Rasanka Jayawardana
 * Model class to store pack data
 *
 */
public class Pack {

	private int packId;
	private Stack<Item> itemStack = new Stack<Item>();

	public void addItem(Item item) {
		if (item != null) {
			getItemStack().push(item);
		}
	}

	public int getPackSize() {
		return getItemStack().size();
	}

	public double getTotalPackLength() {
		double totalLength = 0;
		for (Item item : getItemStack()) {
			if (item != null) {
				totalLength += item.getLength();
			}
		}
		return totalLength;
	}

	public float getTotalPackWeight() {
		float totalWeight = 0.0f;
		for (Item item : getItemStack()) {
			if (item != null) {
				totalWeight += (item.getQuantity() * item.getWeight());
			}
		}
		return totalWeight;
	}

	public int getTotalPackQty() {
		int totalQty = 0;
		for (Item item : getItemStack()) {
			if (item != null) {
				totalQty += item.getQuantity();
			}
		}
		return totalQty;
	}

	/**
	 * @return the packId
	 */
	public int getPackId() {
		return packId;
	}

	/**
	 * @param packId
	 *            the packId to set
	 */
	public void setPackId(int packId) {
		this.packId = packId;
	}

	/**
	 * @return the items
	 */
	public Stack<Item> getItemStack() {
		return itemStack;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItemStack(Stack<Item> itemStack) {
		this.itemStack = itemStack;
	}

}
