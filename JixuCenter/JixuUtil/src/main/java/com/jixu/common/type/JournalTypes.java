package com.jixu.common.type;

import java.util.HashMap;

public class JournalTypes {
	public final static int GENERAL = 7;
	public final static int EXPEND = 8;
	public final static int SUBRED = 9;
	public final static int CASH = 14;
	public final static int PURSALEJNL = 15;
	public final static int INCOME = 17;
	public final static int SETDOWN = 18;
	public final static int CASHDIVIDENT = 19;
	public final static int OFFBALANCESHEET = 20;
	public final static int MANNUAL = 99;
	public static HashMap<Integer, String> journalHM;
	
	public final static HashMap<Integer, String> getJournalTypeHM() {
		if(journalHM==null){
			journalHM=new HashMap<Integer, String>();
			journalHM.put(GENERAL, "估值增值日记账");
			journalHM.put(PURSALEJNL, "交易日记账");
			journalHM.put(SUBRED, "申赎日记账");
			journalHM.put(INCOME, "收入日记账" );
			journalHM.put(EXPEND, "开支日记账" );
			journalHM.put(CASH, "现金活动日记账");
			journalHM.put(CASHDIVIDENT, "分红日记账");
			journalHM.put(SETDOWN, "结算日记账");
			journalHM.put(MANNUAL, "手工日记账");
			journalHM.put(OFFBALANCESHEET, "表外账");
		}
		return journalHM;
	}
}
