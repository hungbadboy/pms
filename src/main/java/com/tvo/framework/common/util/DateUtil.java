/*
 * Copyright(C) 2014
 * NEC Corporation All rights reserved.
 * 
 * No permission to use, copy, modify and distribute this software
 * and its documentation for any purpose is granted.
 * This software is provided under applicable license agreement only.
 */
package com.tvo.framework.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tvo.framework.common.util.DateFormatUtil.DateFormat;

/**
 * A suite of utilities surrounding the use of the Calendar and Date object.
 * DateUtil contains a lot of common methods considering manipulations of Dates
 * or Calendars.
 * 
 * @author hungpd
 * 
 */
public class DateUtil {
	
	public static final int INVALID = -1;

	private DateUtil() {
		//
	}
	
	/**
	 * Get next month API from string date
	 * 
	 * @param date
	 *            A date
	 * @return A string of next month
	 */
	public static String nextMonth(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		String nextMonth = DateFormatUtil.format(calendar.getTime(), DateFormat.DATE_WITHOUT_DAY);
		return nextMonth;
	}
	
	/**
	 * Get previous month API from string date
	 * 
	 * @param date
	 *            A date
	 * @return A string of previous month
	 */
	public static String previousMonth(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		String previousMonth = DateFormatUtil.format(calendar.getTime(), DateFormat.DATE_WITHOUT_DAY);
		return previousMonth;
	}
	
	/**
	 * Number of month want to add
	 * 
	 * @param date
	 * @param monthsToAdd
	 * @return
	 */
	public static Date monthsToAdd(final Date date, int monthsToAdd) {
		if (date == null) {
			return null;
		}
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthsToAdd);
		return calendar.getTime();
	}
	
	/**
	 * Number of month want to subtract
	 * 
	 * @param date
	 * @param monthsToSubtract
	 * @return
	 */
	public static Date monthsToSubtract(final Date date, int monthsToSubtract) {
		if (date == null) {
			return null;
		}
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -monthsToSubtract);
		return calendar.getTime();
	}
	
	/**
	 * Get month of date
	 * 
	 * @param date
	 * 		Date input
	 * @return
	 * 		String Month return
	 */
	public static int getMonth(final Date date) {
		try {
			if (date == null) {
				return INVALID;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return (calendar.get(Calendar.MONTH) + 1);
		} catch (Exception ex) {
			return INVALID;
		}
	}
	
	/**
	 * Get number of days in particular month
	 * 
	 * @param date
	 * 			The month get days
	 * @return
	 * 			Number of days in month
	 */
	public static int getActualMaximumOfMonth(final Date date) {
		if (date == null) {
			return INVALID;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Get next month API from string date
	 * 
	 * @param date
	 *            A date
	 * @return A string of next quarter
	 */
	public static String nextQuarter(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;
		if (quarter == 1) {
			return (calendar.get(Calendar.YEAR) -1) + "/" + 4;
		} else {
			return calendar.get(Calendar.YEAR) + "/" + (quarter - 1);
		}
	}
	
	/**
	 * Get next month API from string date
	 * 
	 * @param date
	 *            A date
	 * @return A string of previous quarter
	 */
	public static String previousQuarter(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -3);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;
		return calendar.get(Calendar.YEAR) + "/" + quarter;		
	}
	
	/**
	 * Get next month API from string date
	 * 
	 * @param date
	 *            A date
	 * @return A string of previous quarter
	 */
	public static String getCurrentQuarter(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;
		return calendar.get(Calendar.YEAR) + "/" + (quarter - 1);
	}
	
	/**
	 * Get quarter
	 * 
	 * @param date
	 *            A date
	 * @return A string of previous quarter
	 */
	public static String getQuarter(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Date must not be null");
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return String.valueOf((calendar.get(Calendar.MONTH) / 3) + 1);
	}
	
	/**
	 * Number days of business day
	 * 
	 * @param monthly
	 *            A date
	 * @param businessDay
	 *            A business day
	 * @return A int number days
	 */
	public static int getNumberOfDays(Date monthly, Date businessDay) {
		Calendar mCalendar = Calendar.getInstance();
		Calendar bCalendar = Calendar.getInstance();

		mCalendar.setTime(monthly);
		bCalendar.setTime(businessDay);

		if (mCalendar.get(Calendar.YEAR) != bCalendar.get(Calendar.YEAR)) {
			return -1;
		}

		if (mCalendar.get(Calendar.MONTH) != bCalendar.get(Calendar.MONTH)) {
			return -1;
		} else {
			return bCalendar.get(Calendar.DAY_OF_MONTH) - 1;
		} 
	}
	
	/**
	 * Get list month of quarter business
	 * 
	 * @param date
	 *            A date
	 * @param quarter
	 *            A number quarter 1 - 4
	 * @return array string three month of quarter
	 */
	public static String[] getMonthliesOfQuarter(Date date, int quarter) {
		String[] monthlies = new String[3];
		for (int i = 0; i < monthlies.length; i++) {
			int month = (quarter * 3) + i;
			Date yearMonth = DateUtil.monthsToAdd(date, month);
			monthlies[i] = DateFormatUtil.format(yearMonth, DateFormat.DATE_WITHOUT_DAY);
		}
		return  monthlies;
	}
	
	/**
	 * Get months two date
	 * 
	 * @param startDate
	 *            A date start
	 * @param endDate
	 *            A date end
	 * @return
	 */
	public static int monthsBetween(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int startMonth = cal.get(Calendar.MONTH);
		int startYear = cal.get(Calendar.YEAR);
		
		// 
		cal.setTime(endDate);
		int endMonth = cal.get(Calendar.MONTH);
		int endYear = cal.get(Calendar.YEAR);		
		int months = ((endYear - startYear) * (cal.getMaximum(Calendar.MONTH) + 1)) + (endMonth - startMonth);
		return months;
	}
	

	    public static Long DateToLong(Date date) {
	        if (date == null) {
	            return null;
	        } else {
	            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
	            String strDateString;
	            strDateString = fm.format(date);
	            return new Long(strDateString);
	        }
	    }
	    public static String LongToStrDateDDMMYYYY(Long nYYYYMMDD){
	        if(nYYYYMMDD == null)
	            return "";
	        String strDDMMYYYY = nYYYYMMDD.toString();
	        if (strDDMMYYYY == null || strDDMMYYYY.equalsIgnoreCase("") || strDDMMYYYY.length() != 8) {
	            return "";
	        } else {
	          String strDate, strMonth, strYear;
	          strYear = strDDMMYYYY.substring(0, 4);
	          strMonth = strDDMMYYYY.substring(4, 6);
	          strDate = strDDMMYYYY.substring(6, 8);
	          strDDMMYYYY = strDate + "/" + strMonth + "/" + strYear;
	            return strDDMMYYYY;
	        }
	    }
	    
	    public static Long DateToLong(String strDDMMYYYY) {
	        if (strDDMMYYYY == null || strDDMMYYYY.equalsIgnoreCase("")) {
	            return null;
	        } else {
	          String strDate, strMonth, strYear;
	          strDate = strDDMMYYYY.substring(0, 2);
	          strMonth = strDDMMYYYY.substring(3, 5);
	          strYear = strDDMMYYYY.substring(6, 10);
	          strDDMMYYYY = strYear+strMonth+strDate;
	            return new Long(strDDMMYYYY);
	        }
	    }

	    public static Date LongToDate(Long number) {
	        if (number == null) {
	            return null;
	        } else {
	            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
	            ParsePosition pos = new ParsePosition(0);
	            Date d = fm.parse(String.valueOf(number), pos);
	            return d;
	        }
	    }

	    public static Long getCurrentDate() {
	        Date date = new Date();
	        return DateToLong(date);
	    }
	    
	    public static String getCurrentTime(){
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
	    }
	    public static int getHour(){
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.HOUR_OF_DAY);
	    }
	    public static int getMinute(){
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.MINUTE);
	    }
	    public static int getSecond(){
	        Calendar calendar = Calendar.getInstance();
	        return calendar.get(Calendar.SECOND);
	    }
	}

