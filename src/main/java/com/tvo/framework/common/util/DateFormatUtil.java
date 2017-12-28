/**
 * Copyright(C) 2014
 * NEC Corporation All rights reserved.
 * 
 * No permission to use, copy, modify and distribute this software
 * and its documentation for any purpose is granted.
 * This software is provided under applicable license agreement only.
 */
package com.tvo.framework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date and time formatting utilities and constants. Formatting is performed
 * using the thread-safe DateFormat class.
 * 
 * @author sondn
 * 
 */
public final class DateFormatUtil {
	
	/**
	 * DateFormat is a fast and thread-safe version of SimpleDateFormat. This
	 * class can be used as a direct replacement to SimpleDateFormat in most
	 * formatting situations.
	 * 
	 * @author sondn
	 * 
	 */
	public enum DateFormat {
		DATE_WITH_HOURS, 
		
		DATE_WITHOUT_DAY,
		DATE_WITHOUT_HOURS,
		DATE_LONG_WITHOUT_DAY,
		
		DATE_YEAR,

		DATE_SHORT_DAY,
		
		DEFAULT;

		public String getFormatPattern() {
			if (this == DATE_WITHOUT_DAY) {
				return "yyyyMM";
			} else if (this == DATE_WITHOUT_HOURS) {
				return "yyyyMMdd";
			} else if (this == DATE_LONG_WITHOUT_DAY) {
				return "yyyy年MM月";
			} else if (this == DATE_YEAR) {
				return "yyyy";
			} else if (this == DATE_SHORT_DAY) {
				return "d";
			} else {
				return "yyyy/mm/dd hh:mm:ss a";
			}
		}
	}
	
	private DateFormatUtil() {
		//
	}

	/**
	 * Format a date for the given pattern
	 * 
	 * @param date
	 *            A date
	 * @param pattern
	 *            Format pattern
	 * @return A string of date after format
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static String format(Date date, String pattern) throws NullPointerException, IllegalArgumentException {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("")) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * Format a date for the given format mode
	 * 
	 * @param date
	 *            A date
	 * @param mode
	 *            Format pattern
	 * @return A string of date after format
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static String format(Date date, DateFormat mode) throws NullPointerException, IllegalArgumentException {
		if (date == null) {
			return null;
		}
		if (mode == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(mode.getFormatPattern());
		return format.format(date);
	}

	/**
	 * Parse a date from string for the given pattern
	 * 
	 * @param date
	 *            A string of date
	 * @param pattern
	 *            Format pattern
	 * @return A date after parse
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public static Date parse(String date, String pattern) throws NullPointerException, IllegalArgumentException, ParseException {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("")) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(date);
	}

	/**
	 * Parse a date from string for the given format mode
	 * 
	 * @param date
	 *            A string of date
	 * @param mode
	 *            Format pattern
	 * @return A date after parse
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public static Date parse(String date, DateFormat mode) throws NullPointerException, IllegalArgumentException, ParseException {
		if (date == null) {
			return null;
		}
		if (mode == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(mode.getFormatPattern());
		return format.parse(date);
	}
	
}
