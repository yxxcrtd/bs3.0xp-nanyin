package com.jixu.common.types;

import java.util.HashMap;

public class DBCR {
	public final static String DEBIT = "D";		//借
	public final static String CREDIT = "C";	//贷
	public final static double ZERO = 0.00001;
	
	public final static int receive=0;
	public final static int pay=1;
	
	public final static HashMap<Integer, String> optionsHM=new HashMap<Integer, String>();
	static{
		optionsHM.put(pay, "应付");
		optionsHM.put(receive, "应收");
	}
	
	public final static HashMap<Integer, String> jdOptionsHM=new HashMap<Integer, String>();
	static{
		jdOptionsHM.put(pay, "贷方");
		jdOptionsHM.put(receive, "借方");
	}
	
	public final static HashMap<String, String> dcOptionsHM=new HashMap<String, String>();
	static{
		dcOptionsHM.put(CREDIT, "应收");
		dcOptionsHM.put(DEBIT, "应付");
	}
	
	public final static HashMap<String, String> ysyfOptionsHM=new HashMap<String, String>();
	static{
		ysyfOptionsHM.put(CREDIT, "支出");
		ysyfOptionsHM.put(DEBIT, "收入");
	}
}
