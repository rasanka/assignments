package com.yellow.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Rasanka Jayawardana
 * @version 1.0
 */
public class Solution {

	public double calculateAverage(Set<TreeNode> nodes) {

		Map<Long, TreeNode> parentMap = generateParentMap(nodes);

		Map<Long, List<TreeNode>> nodeMap = generateNodeMap(nodes);

		fillFirstLevelChildNodes(parentMap, nodeMap);

		fillChildNodes(parentMap, nodeMap);

		printTree(parentMap);

		return 0.0;
	}

	/**
	 * Filling out the second level child nodes
	 * @param parentMap
	 * @param nodeMap
	 */
	private void fillChildNodes(Map<Long, TreeNode> parentMap, Map<Long, List<TreeNode>> nodeMap) {
		for (Map.Entry<Long, TreeNode> pair : parentMap.entrySet()) {
			populateChildrenList(nodeMap, pair.getValue().getChildrenList());
		}
	}

	/**
	 * Populating the children list in the each child node recursively
	 * @param nodeMap
	 * @param childList
	 */
	private void populateChildrenList(Map<Long, List<TreeNode>> nodeMap, List<TreeNode> childList) {
		for (TreeNode child : childList) {
			if (nodeMap.containsKey(child.getId())) {
				child.getChildrenList().addAll(nodeMap.get(child.getId()));

				if (recursiveCheck(nodeMap.keySet(), child.getChildrenList())) {
					populateChildrenList(nodeMap, child.getChildrenList());
				}
			}
		}
	}

	/**
	 * Checking the node map key set whether it contains the parent ID for recursive call
	 * @param keySet
	 * @param childList
	 * @return
	 */
	private boolean recursiveCheck(Set<Long> keySet, List<TreeNode> childList) {
		boolean status = false;
		for (TreeNode node : childList) {
			if (keySet.contains(node.getId())) {
				status = true;
				break;
			}
		}
		return status;
	}

	/**
	 * Filling the parent map with first level child nodes
	 * @param parentMap
	 * @param nodeMap
	 */
	private void fillFirstLevelChildNodes(Map<Long, TreeNode> parentMap, Map<Long, List<TreeNode>> nodeMap) {
		for (Map.Entry<Long, TreeNode> pair : parentMap.entrySet()) {
			for (Map.Entry<Long, List<TreeNode>> nodePair : nodeMap.entrySet()) {
				if (pair.getKey() == nodePair.getKey()) {
					pair.getValue().getChildrenList().addAll(nodePair.getValue());
					break;
				}
			}
		}
	}

	/**
	 * Generating a map of parent nodes
	 * @param nodes
	 * @return Map of parent nodes with paretnId and the node
	 */
	private Map<Long, TreeNode> generateParentMap(Set<TreeNode> nodes) {
		Map<Long, TreeNode> parentMap = new HashMap<>();
		for (TreeNode node : nodes) {
			if (node.getParentId() == null) {
				parentMap.put(node.getId(), node);
			}
		}
		return parentMap;
	}

	/**
	 * Generating a map of nodes other than the root elements
	 * @param nodes
	 * @return Map of parent id and associated child node list
	 */
	private Map<Long, List<TreeNode>> generateNodeMap(Set<TreeNode> nodes) {
		Map<Long, List<TreeNode>> nodeMap = new HashMap<>();
		for (TreeNode node : nodes) {
			if (node.getParentId() != null) {
				if (nodeMap.containsKey(node.getParentId())) {
					List<TreeNode> tempList = new ArrayList<>();
					tempList.addAll(nodeMap.get(node.getParentId()));
					tempList.add(node);
					nodeMap.remove(node.getParentId());
					nodeMap.put(node.getParentId(), tempList);
				} else {
					nodeMap.put(node.getParentId(), Arrays.asList(node));
				}
			}
		}
		return nodeMap;
	}
	
	/**
	 * Printing the parent map
	 * @param parentMap
	 */
	private void printTree(Map<Long, TreeNode> parentMap) {
		for (TreeNode node : parentMap.values()) {
			System.out.println("<<PARENT NODE >>" + node.toString() + " SIZE -" + node.getChildrenList().size());
			printChildNodes(node.getChildrenList());
			System.out.println("-----------------------------------------");
		}
	}

	/**
	 * Printing the child nodes recursively
	 * @param childrenList
	 */
	private void printChildNodes(List<TreeNode> childrenList) {
		for (TreeNode child : childrenList) {
			System.out.println("<<CHILD NODE >>" + child);
			if (child.getChildrenList().size() > 0) {
				printChildNodes(child.getChildrenList());
			}
		}
	}

}
