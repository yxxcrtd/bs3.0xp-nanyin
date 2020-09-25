package com.jixu.common.types;

import java.util.HashMap;

public class BankAcctType {
	public final static int priTrustBank = 1;
	public final static int assistTrustBank = 2;
	public final static HashMap<Integer, String> acctTypeHM=new HashMap<Integer, String>();
	static{
		acctTypeHM.put(priTrustBank, "主托管账户");
		acctTypeHM.put(assistTrustBank, "次托管账户");
	}
}
