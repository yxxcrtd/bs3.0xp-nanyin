package com.jixu.common.types;

import java.util.HashMap;

public class PerformanceFeeType {
	public final static int returnRate1 = 0; // 持有期收益率

	public final static int expenseBasis1 = 0; // 剩余金额
	public final static int expenseBasis2 = 1; // 原始金额

	public final static HashMap<Integer, String> returnRateOption = new HashMap<Integer, String>();
	static {
		returnRateOption.put(returnRate1, "持有期收益率");
	}

	public final static HashMap<Integer, String> expenseBasisOption = new HashMap<Integer, String>();
	static {
		expenseBasisOption.put(expenseBasis1, "原始金额");
		expenseBasisOption.put(expenseBasis2, "剩余金额");
	}
}
