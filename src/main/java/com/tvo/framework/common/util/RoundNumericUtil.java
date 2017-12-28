/*
 * Copyright(C) 2014
 * NEC Corporation All rights reserved.
 * 
 * No permission to use, copy, modify and distribute this software
 * and its documentation for any purpose is granted.
 * This software is provided under applicable license agreement only.
 */
package com.tvo.framework.common.util;

public final class RoundNumericUtil {
	
	private RoundNumericUtil() {
		// 
	}

	public static double round(double value, int precision) {
		int scale = (int) Math.pow(10, precision);
		return (double) Math.round(value * scale) / scale;
	}
	
	public static double roundSonekiSuii(double amount1, double amount2) {
		return round(((amount1 / amount2) * 100), 1);
	}
	
}
