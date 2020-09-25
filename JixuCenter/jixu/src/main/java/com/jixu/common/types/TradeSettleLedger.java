package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName TradeSettleLedger
 * @author Pan Wei
 * @Date 2016年8月18日 上午9:31:38
 * @Description []
 */

public class TradeSettleLedger {
	public final static int TradeDate = 1;
	public final static int SettleDate = 2;
	public final static int StockT_BondS = 3;
	public final static int TradeDate_1 = 4;
	//不需要倒起息，直接以交易日作为出账日
	public final static int NO_AFTER_CLOSE_LEDGER = 0;

	public final static HashMap<Integer, String> accruedBaseDateOption = new HashMap<Integer, String>();
	static {
		accruedBaseDateOption.put(TradeDate, "交易日");
		accruedBaseDateOption.put(SettleDate, "结算日");
	}
}
