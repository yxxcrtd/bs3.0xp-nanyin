package com.jixu.common.types;

import java.util.HashMap;

public class CashTradeType {
	public final static int cashMove = 1;
	public final static int cashOff = 2;
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(cashMove, "现金划拨");
		optionHM.put(cashOff, "冲销模板");
	}
}
