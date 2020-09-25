package com.jixu.common.type;

import java.util.HashMap;

public class BankTypes {
	public final static int ALL_Bank=0;		//全部
	public final static int BOC_Bank=1;	//上交所
	public final static int PBOC_Bank=2;		//深交所
	public static HashMap<Integer, String> typesHM=new HashMap<Integer, String>();
	
	public final static HashMap<Integer, String> getBankTypes (boolean all) {
		if(all){
			typesHM.put(ALL_Bank, "全部");
		}
		typesHM.put(BOC_Bank, "中国银行");
		typesHM.put(PBOC_Bank, "中国人民银行");
		return typesHM;
	}
}
