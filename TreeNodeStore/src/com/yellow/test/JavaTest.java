package com.yellow.test;

import java.util.HashSet;
import java.util.Set;

public class JavaTest {

	public static void main(String args[]) {

		Set<TreeNode> dataSet = new HashSet<>();
		dataSet.add(new TreeNode(1L, null, 1.50));
		dataSet.add(new TreeNode(2L, null, 2.50));
		dataSet.add(new TreeNode(3L, null, 3.50));
		dataSet.add(new TreeNode(4L, 1L, 4.50));
		dataSet.add(new TreeNode(5L, 1L, 5.50));
		dataSet.add(new TreeNode(6L, 2L, 6.50));
		dataSet.add(new TreeNode(7L, 2L, 7.50));
		dataSet.add(new TreeNode(8L, 2L, 1.50));
		dataSet.add(new TreeNode(9L, 3L, 2.50));
		dataSet.add(new TreeNode(10L, 3L, 13.50));
		dataSet.add(new TreeNode(11L, 3L, 4.50));
		dataSet.add(new TreeNode(12L, 4L, 6.50));
		dataSet.add(new TreeNode(13L, 4L, 7.50));
		dataSet.add(new TreeNode(14L, 5L, 1.50));
		dataSet.add(new TreeNode(15L, 6L, 1.50));
		dataSet.add(new TreeNode(16L, 7L, 12.50));
		dataSet.add(new TreeNode(17L, 7L, 6.50));
		dataSet.add(new TreeNode(18L, 8L, 2.50));
		dataSet.add(new TreeNode(19L, 9L, 3.50));
		dataSet.add(new TreeNode(20L, 10L, 1.50));
		dataSet.add(new TreeNode(21L, 11L, 1.50));
		dataSet.add(new TreeNode(22L, 11L, 2.50));
		dataSet.add(new TreeNode(23L, 13L, 4.50));
		dataSet.add(new TreeNode(24L, 14L, 0.50));

		Solution solution = new Solution();

		double result = solution.calculateAverage(dataSet);

		System.out.println(result);
	}

}
