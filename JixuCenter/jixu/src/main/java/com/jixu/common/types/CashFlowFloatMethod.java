package com.jixu.common.types;

import java.util.HashMap;

public class CashFlowFloatMethod {
	public final static int PlusCal = 1; // 相加基数
	public final static int MulCal = 2; // 相乘系数
	public final static int CICal = 3; // 复利

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(PlusCal, "相加基数");
		optionHM.put(MulCal, "相乘系数");
		optionHM.put(CICal, "复利计算");
	}
}
