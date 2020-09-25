package com.jixu.common.types;

import java.util.HashMap;

public class ExpenseBaseType {
	public final static int FixExpense = 0;
	public final static int FloatExpense = 1;

	public final static int NewAssetExpense = 0;
	public final static int UnNewAssetExpense = 1;

	public final static int onetimeFee = 0;
	public final static int followFee = 1;

	public final static HashMap<Integer, String> expenseTypeHM = new HashMap<Integer, String>();
	static {
		expenseTypeHM.put(FixExpense, "固定");
		expenseTypeHM.put(FloatExpense, "浮动");
	}

	public final static HashMap<Integer, String> expenseBaseHM = new HashMap<Integer, String>();
	static {
		expenseBaseHM.put(NewAssetExpense, "当天净资产");
		expenseBaseHM.put(UnNewAssetExpense, "前一天净资产");
	}

	public final static HashMap<Integer, String> expenseType2HM = new HashMap<Integer, String>();
	static {
		expenseType2HM.put(onetimeFee, "一次计提");
		expenseType2HM.put(followFee, "每日计提");
	}
}
