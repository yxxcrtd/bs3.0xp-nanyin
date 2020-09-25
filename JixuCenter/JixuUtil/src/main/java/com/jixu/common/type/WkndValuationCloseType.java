package com.jixu.common.type;

import java.util.HashMap;

public class WkndValuationCloseType {
	public final static int None = 0;					//None
	public final static int MonthEnd = 1;				//FIFO
	public final static int QuarterEnd = 2;				//Quarter End Only
	public final static int UserDefinedWeekends = 3;	//User Defined Weekends
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(None, "无");
		optionHM.put(MonthEnd, "仅限周末");
		optionHM.put(QuarterEnd, "仅限季末");
		optionHM.put(UserDefinedWeekends, "用户定义周末");
	}
}
