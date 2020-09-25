package com.jixu.common.types;

import java.util.HashMap;

public class CpnDateDelayType {
	public final static int none=0;
	public final static int frn_Eurodollar=1;
	public final static int Following_Business_Day_Or_Adjust_Accrual =2;
	public final static int Modified_Following_Or_Adjust_Accrual=3;
	public final static int Preceding_Business_Day_Or_Adjust_Accrual=4;
	public final static int Following_Business_Day_Or_No_Accrual_Adjust=6;
	public final static int Modified_Following_Or_No_Accrual_Adjust=7;
	public final static int Preceding_Business_Day_Or_No_Accrual_Adjust=8;
	
	public final static HashMap<Integer, String> cpnDateDelayHM=new HashMap<Integer, String>();
	static{
		cpnDateDelayHM.put(frn_Eurodollar, "实际日");
		cpnDateDelayHM.put(Following_Business_Day_Or_Adjust_Accrual, "下一工作日");
	}
	
	
	/**
	 * A code indicating the type of payment date convention code to be used in conjunction with a holiday schedule
	 * 0 None  
	* 1 FRN/Eurodollar  
	* 2 Following Business Day/Adjust Accrual  
	* 6 Following Business Day/No Accrual Adjust  
	* 3 Modified Following/Adjust Accrual  
	* 7 Modified Following/No Accrual Adjust  
	* 4 Preceding Business Day/Adjust Accrual  
	* 8 Preceding Business Day/No Accrual Adjust 
	 */
}
