package com.jixu.common.types;

import java.util.HashMap;

public class BankAcctSetCurrency {
	public final static int bankCur = 0;
	public final static int SecCur = 1;
	public final static HashMap<Integer, String> setCurrencyHM=new HashMap<Integer, String>();
	static{
		setCurrencyHM.put(bankCur, "银行账户货币");
		setCurrencyHM.put(SecCur, "证券货币");
	}
}
