package com.jixu.common.types;

import java.util.HashMap;

/**
 * A code indicating the type of interest period convention in effect, e.g., stated period.
 * @author Pan Wei
 *
 */

public class IntPeriodConv {
	public final static int Actual_Day=1;
	public final static int Following_Business_Day=2;
	public final static int Preceding_Business_Day=3;
	
	public final static HashMap<Integer, String> intPeriodConvHM=new HashMap<Integer, String>();
	static{
		intPeriodConvHM.put(Actual_Day, "规定时期");
		intPeriodConvHM.put(Following_Business_Day, "期间上一工作日");
		intPeriodConvHM.put(Preceding_Business_Day, "上一工作日的前一日");
	}
}
