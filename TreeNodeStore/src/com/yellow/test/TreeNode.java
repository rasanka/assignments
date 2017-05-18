package com.yellow.test;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private Long id;
	private Long parentId;
	private double value;
	private List<TreeNode> childrenList;

	public TreeNode(Long id, Long parentId, double value) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.value = value;
		this.childrenList = new ArrayList<>();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the childrenList
	 */
	public List<TreeNode> getChildrenList() {
		return childrenList;
	}

	/**
	 * @param childrenList
	 *            the childrenList to set
	 */
	public void setChildrenList(List<TreeNode> childrenList) {
		this.childrenList = childrenList;
	}

	public String toString() {
		return "ID -" + this.getId() + " PARENT -" + this.getParentId() + " VALUE-" + this.getValue() + " CHILD COUNT -"
				+ this.getChildrenList().size();
	}

}
