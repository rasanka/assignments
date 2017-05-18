package com.motiondesign.util;

import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

/**
 * @author Rasanka Jayawardana
 * Utility class to read the user input
 *
 */
public class InputReader {

	public static final String EMPTY_STRING = "";

	public static List<String> read() {

		System.out.println(Constants.INPUT_FORMAT);
		Scanner input = new Scanner(System.in);

		List<String> dataList = Lists.newArrayList();

		while (input.hasNextLine()) {
			String nextLine = input.nextLine();
			if (EMPTY_STRING.equals(nextLine)) {
				break;
			} else {
				dataList.add(nextLine);
			}
		}
		input.close();
		return dataList;
	}
}
