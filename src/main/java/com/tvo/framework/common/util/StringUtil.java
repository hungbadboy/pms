/*
 * Copyright(C) 2014
 * Corporation All rights reserved.
 * 
 * No permission to use, copy, modify and distribute this software
 * and its documentation for any purpose is granted.
 * This software is provided under applicable license agreement only.
 */
package com.tvo.framework.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * To format string by user style 
 * 
 * @author huonghv
 *
 */
public class StringUtil<T> {
	
	/**
	 * Change a number to string and format it with style by user define
	 * 
	 * @param fill
	 *            Character to fill up
	 * @param total
	 *            Length of string return
	 * @param number
	 *            Number to convert
	 * @return String return
	 */
	public static <T> String numberToStringWithUserFillUp(String fill, int total, T number) {
		if (StringUtils.isEmpty(fill)) {
			throw new IllegalArgumentException("Number to string fill character must not be null or empty");
		}
		
		// nts mean that number to string
		String nts = String.valueOf(number);
		int fillUp = total - nts.length();
		if (fillUp > 0) {
			nts = StringUtils.repeat(fill, fillUp) + nts;
		}
		return nts;
	}
	
	/**
	 * Check a string is empty or null
	 * 
	 * @param text
	 * @return
	 */
	public static String value(String text) {
		return StringUtils.isEmpty(text) ? StringUtils.EMPTY : text;
	}
	
	/**
	 * Remove comma character in a string
	 * 
	 * @param text
	 * @return
	 */
	public static String removeCommaChar(String text) {
		if (StringUtils.isEmpty(text)) {
			return StringUtils.EMPTY;
		}
		
		return text.replaceAll(",", StringUtils.EMPTY);
	}
}
