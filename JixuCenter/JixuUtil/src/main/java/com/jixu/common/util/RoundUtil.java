package com.jixu.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUtil {
	
	private final static double DOUBLE_OFF = 0.5000001;
	
	public static double roundMoney(double value, int r) {
		if(value > 0){
			return Math.floor(value * Math.pow(10, r) + DOUBLE_OFF)/Math.pow(10, r);
		}else{
			return Math.ceil(value * Math.pow(10, r) - DOUBLE_OFF)/Math.pow(10, r);
		}
	}
	
	public static double roundMoney2Dec(double value) {
		if (value > 0) {
			return Math.floor(value * 100 + DOUBLE_OFF)/100;
		} else if(value<0){
			return Math.ceil(value * 100 - DOUBLE_OFF)/100;
		}else{
			return 0;
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
