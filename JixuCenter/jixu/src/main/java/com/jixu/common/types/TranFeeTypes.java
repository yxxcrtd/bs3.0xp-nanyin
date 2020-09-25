package com.jixu.common.types;

import java.util.HashMap;

public class TranFeeTypes {
	public final static int COMMISION_FEE = 1;
	public final static int EXPENSE_FEE = 2;
	
	public final static int PAY_WITH_TRADE=0;
	public final static int PAY_WITHOUT_TRADE=1;
	
	
	public final static HashMap<Integer, String> getPayTradeType(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(PAY_WITH_TRADE, "是");
		optionHM.put(PAY_WITHOUT_TRADE, "否");
		return optionHM;
	}
}
