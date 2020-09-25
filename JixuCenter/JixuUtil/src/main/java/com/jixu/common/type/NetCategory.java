package com.jixu.common.type;

import java.util.HashMap;

public class NetCategory {
	public final static int Assets = 1;
	public final static int Liabilitis = 2;
	public final static int Income= 3;
	public final static int Expenses = 4;
	public final static int Capital = 5;
	public final static int CapitalRG = 7;
	public final static int CapitalUnReal = 8;
	public final static int OUT_OFF_TABLE = 9;
	public final static HashMap<Integer, String> categoryHM=new HashMap<Integer, String>();
	
	public final static HashMap<Integer, String> getNetCategory() {
		categoryHM.put(Assets, "资产");
		categoryHM.put(Liabilitis, "负债");
		categoryHM.put(Income, "收入");
		categoryHM.put(Expenses, "开支");
		categoryHM.put(Capital, "资本");
		categoryHM.put(CapitalRG, "资本-已实现");
		categoryHM.put(CapitalUnReal, "资本-未实现");
		categoryHM.put(OUT_OFF_TABLE, "表外账");
		
		return categoryHM;
	}
}
