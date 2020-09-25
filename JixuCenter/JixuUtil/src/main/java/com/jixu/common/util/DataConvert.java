package com.jixu.common.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DataConvert {
	public static double[] convertStr2doubleArr(String str, String spilitStr) {
		String guidePriceLine = str;
		double[] guidesPrices = null;
		if (guidePriceLine != null) {
			String[] guideStrArr = guidePriceLine.split(spilitStr);
			guidesPrices = new double[guideStrArr.length];
			for (int j = 0; j < guideStrArr.length; j++) {
				guidesPrices[j] = Double.parseDouble(guideStrArr[j]);
			}
		} else
			guidesPrices = new double[0];
		Arrays.sort(guidesPrices);
		return guidesPrices;
	}
	
	public static int[] convertStr2IntArr(String str, String spilitStr) {
		String guidePriceLine = str;
		int[] guidesPrices = null;
		if (guidePriceLine != null) {
			String[] guideStrArr = guidePriceLine.split(spilitStr);
			guidesPrices = new int[guideStrArr.length];
			for (int j = 0; j < guideStrArr.length; j++) {
				guidesPrices[j] = Integer.parseInt(guideStrArr[j]);
			}
		} else
			guidesPrices = new int[0];
		Arrays.sort(guidesPrices);
		return guidesPrices;
	}

	/**
	 * 获取数组中最小值
	 * 
	 * @param dataArray
	 * @return
	 */
	public static double getMinNumber(double[] dataArray) {
		double minResult = 999999999;
		for (int i = 0; i < dataArray.length; i++) {
			minResult = Math.min(minResult, dataArray[i]);
		}
		return minResult;
	}

	/**
	 * 获取数组中最大值
	 * 
	 * @param dataArray
	 * @return
	 */
	public static double getMaxNumber(double[] dataArray) {
		double maxResult = 999999999;
		for (int i = 0; i < dataArray.length; i++) {
			maxResult = Math.max(maxResult, dataArray[i]);
		}
		return maxResult;
	}

	/**
	 * 获取两个数值中的较小值
	 * 
	 * @param data1
	 * @param data2
	 * @return
	 */
	public double getMinNumber(double data1, double data2) {
		return Math.min(data1, data2);
	}

	/**
	 * 获取两个数值中的较大值
	 * 
	 * @param data1
	 * @param data2
	 * @return
	 */
	public double getMaxNumber(double data1, double data2) {
		return Math.max(data1, data2);
	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 转int List
	 * @param objList
	 * @return
	 */
	public static List<Integer> convert2IntList(List<Object> objList){
		List<Integer> intList=new ArrayList<Integer>();
		for(Object obj:objList){
			intList.add(Integer.valueOf(obj.toString()));
		}
		return intList;
	}
	
	public static List<String> convert2StringList(List<Object> objList){
		List<String> stringList=new ArrayList<String>();
		for(Object obj:objList){
			stringList.add(obj.toString());
		}
		return stringList;
	}
	
	private static ThreadLocal<DecimalFormat> cashNoCalFormat = new ThreadLocal<DecimalFormat>() {
		@Override
		protected synchronized DecimalFormat initialValue() {
			return new DecimalFormat("##0.00");
		}
	};
	
	private static ThreadLocal<DecimalFormat> amountUSFormat = new ThreadLocal<DecimalFormat>() {
		@Override
		protected synchronized DecimalFormat initialValue() {
			return new DecimalFormat("#,##0");
		}
	};
	
	private static ThreadLocal<DecimalFormat> moneyUSFormat = new ThreadLocal<DecimalFormat>() {
		@Override
		protected synchronized DecimalFormat initialValue() {
			return new DecimalFormat("#,##0.00");
		}
	};
	
	private static ThreadLocal<DecimalFormat> moneyCHFormat = new ThreadLocal<DecimalFormat>() {
		@Override
		protected synchronized DecimalFormat initialValue() {
			return new DecimalFormat("#,###0.00");
		}
	};
	
	/**
	 * 转成格式: #,##0.00
	 * @param amount
	 * @return
	 */
	public static String convertCash2US(double amount){
		return moneyUSFormat.get().format(amount);
	}
	
	/**
	 * 转成格式: #,###0.00
	 * @param amount
	 * @return
	 */
	public static String convertCash2CH(double amount){
		return moneyCHFormat.get().format(amount);
	}
	
	/**
	 * 转成格式: #,##0
	 * @param amount
	 * @return
	 */
	public static String convertAmount2US(double amount){
		return amountUSFormat.get().format(amount);
	}
	
	/**
	 * 转成格式: ##0.00
	 * @param amount
	 * @return
	 */
	public static String convertCashNoCalFormat(double amount){
		return cashNoCalFormat.get().format(amount);
	}
	
	/**
	 * 判断是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
		if(str==null || str.equals("")){
			return false;
		}else{
			Pattern pattern=Pattern.compile("^[-\\+]?[\\d]*$");
			return pattern.matcher(str).matches();
		}
	}
	
	/**
	 * 数组转list
	 * @param args
	 * @return
	 */
	public static List<String> convertArr2List(String[] args){
		List<String> result=new ArrayList<String>();
		for(String arg: args) {
			result.add(arg);
		}
		return result;
	}
	
	/**
	 * 数组转list
	 * @param args
	 * @return
	 */
	public static String[] convertList2Arr(List<String> sourceList){
		int size=sourceList.size();
		String[] args=new String[size];
		for(int i=0;i<size;i++) {
			args[i]=sourceList.get(i);
		}
		return args;
	}
}
