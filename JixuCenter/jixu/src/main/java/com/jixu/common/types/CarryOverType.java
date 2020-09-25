package com.jixu.common.types;

import java.util.HashMap;

public class CarryOverType {
	
	public final static int CASH = 1; //现金
	public final static int SHARES= 2;	//份额再投
	
	public final static HashMap<Integer, String> methodsHM=new HashMap<Integer, String>();
	static{
		methodsHM.put(SHARES, "份额再投");
		methodsHM.put(CASH, "现金");
	}
}
