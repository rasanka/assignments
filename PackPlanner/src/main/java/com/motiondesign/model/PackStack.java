package com.motiondesign.model;

import java.util.Stack;

/**
 * @author Rasanka Jayawardana
 * Model class to store pack stack data
 *
 */
public class PackStack {

	private Stack<Pack> packStack = new Stack<Pack>();

	/**
	 * @return the packStack
	 */
	public Stack<Pack> getPackStack() {
		return packStack;
	}

    public boolean isEmpty() {
        return getPackStack().isEmpty();
    }

    public int getTopPackId() {
        if (getPackStack().isEmpty()) {
            return getPackStack().size();
        }
        return getTopPack().getPackId();
    }

    public Pack getTopPack() {
        return getPackStack().peek();
    }

    public void addPack(Pack pack) {
        getPackStack().push(pack);
    }

}
