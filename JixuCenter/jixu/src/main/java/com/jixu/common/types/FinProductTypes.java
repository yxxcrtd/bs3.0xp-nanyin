package com.jixu.common.types;

import java.util.HashMap;

public class FinProductTypes {
	
	public final static int COMMSTOCK_SH_RPSECTYPE = 101; //上交所普通股对应资产ID
	public final static int COMMSTOCK_SZ_RPSECTYPE = 102; //深交所普通股对应资产ID
	public final static int COMMSTOCK_HGT_RPSECTYPE = 103; //沪港通股票对应资产ID
	public final static int COMMSTOCK_SGT_RPSECTYPE = 104; //深港通股票对应资产ID
	public final static int FORWARD_CURRENCY_RPSECTYPE = 107; //外汇远期对应资产ID
	public final static int SWAP_INTEREST_RPSECTYPE = 108; //利率互换对应资产ID
	public final static int FX_RPSECTYPE = 114; //外汇即期对应资产ID
	public final static int SWAP_FX_RPSECTYPE = 115; //外汇掉期对应资产ID
	
	public final static HashMap<Integer, String> finProductType_3HM=new HashMap<Integer, String>();
	static{
		finProductType_3HM.put(COMMSTOCK_SH_RPSECTYPE, "L01");
		finProductType_3HM.put(COMMSTOCK_SZ_RPSECTYPE, "L02");
		finProductType_3HM.put(COMMSTOCK_HGT_RPSECTYPE, "L03");
		finProductType_3HM.put(COMMSTOCK_SGT_RPSECTYPE, "L04");
		finProductType_3HM.put(SWAP_INTEREST_RPSECTYPE, "K01");
		finProductType_3HM.put(FX_RPSECTYPE, "M01");
		finProductType_3HM.put(FORWARD_CURRENCY_RPSECTYPE, "N01");
		finProductType_3HM.put(SWAP_FX_RPSECTYPE, "001");
	}
	
	public final static HashMap<Integer, String> finProductType_2HM=new HashMap<Integer, String>();
	static{
		finProductType_2HM.put(COMMSTOCK_SH_RPSECTYPE, "101");
		finProductType_2HM.put(COMMSTOCK_SZ_RPSECTYPE, "102");
		finProductType_2HM.put(COMMSTOCK_HGT_RPSECTYPE, "103");
		finProductType_2HM.put(COMMSTOCK_SGT_RPSECTYPE, "104");
	}
}
