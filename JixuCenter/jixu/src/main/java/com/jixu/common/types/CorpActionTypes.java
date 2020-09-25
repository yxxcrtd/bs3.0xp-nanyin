package com.jixu.common.types;

import java.util.HashMap;

public class CorpActionTypes {
	public final static int CASHPAYMENTSONLY = 1;
	public final static int STOCKDIVIDEND = 8;
	public final static int STOCKSPLIT = 9;
	public final static int STOCK_RESVERSE_SPLIT = 10;
	public final static int SPINOFF = 15;
	public final static int ALL = 0;
	
	public final static HashMap<Integer, String> caOption = new HashMap<Integer, String>();
	static {
		caOption.put(CASHPAYMENTSONLY, "现金股利");
		caOption.put(STOCKDIVIDEND, "股票股利");
		caOption.put(STOCKSPLIT,"股票分割");
		caOption.put(STOCK_RESVERSE_SPLIT,"股票合并");
	}
	
	public final static HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
	
	public final static HashMap<Integer, String> getCATypes(boolean allFlag) {
		if (allFlag){
			optionHM.put(ALL, "全部");
		}
		optionHM.putAll(caOption);
		return optionHM;
	}
}
