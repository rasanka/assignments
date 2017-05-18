package com.motiondesign.model;

/**
 * @author Rasanka Jayawardana
 * Model class to store item data
 *
 */
public class Item implements Comparable<Item>{

	private int id;
	private float length;
	private int quantity;
	private float weight;
	
	public float getTotalWeight() {
		return getWeight() * getQuantity();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Item item) {
        if (!(item instanceof Item)) {
            return -1;
        }
        if (this == item) {
            return 0;
        }
        if (this.getLength() < item.getLength()) {
            return -1;
        } else {
            return 1;
        }
	}

}
