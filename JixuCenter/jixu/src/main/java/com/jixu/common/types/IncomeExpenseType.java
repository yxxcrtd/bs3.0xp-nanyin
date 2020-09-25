package com.jixu.common.types;

import java.util.HashMap;

public class IncomeExpenseType {
	public final static int income = 0;
	public final static int expense = 1;

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(income, "收入");
		optionHM.put(expense, "支出");
	}

	public final static HashMap<Integer, String> dbcrOptionHM = new HashMap<Integer, String>();
	static {
		dbcrOptionHM.put(income, "D");
		dbcrOptionHM.put(expense, "C");
	}
}
