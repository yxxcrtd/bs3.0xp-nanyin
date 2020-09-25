package com.jixu.common.type;

import java.util.HashMap;

public class PortfolioTypes {
	public final static int all_fund=0;		//全部
	public final static int public_fund=1;		//公募基金
	public final static int private_fund=2;		//私募基金
	public final static int special_fund=3;		//专户
	public final static int trust_plan=4;		//信托计划
	public final static int bank_finance=5;		//银行理财
	public final static int Insurance_finance=6;		//保险理财
	public final static int public_qdii_fund=7;		//公募QDII
	public static HashMap<Integer, String> typesHM=new HashMap<Integer, String>();
	
	public final static HashMap<Integer, String> getPortfolioTypes() {
		typesHM.put(all_fund, "全部");
		typesHM.put(public_fund, "公募基金");
		typesHM.put(private_fund, "私募基金");
		typesHM.put(special_fund, "专户");
		typesHM.put(trust_plan, "信托计划");
		typesHM.put(bank_finance, "银行理财");
		typesHM.put(Insurance_finance, "保险理财");
		typesHM.put(public_qdii_fund, "QDII");
		
		return typesHM;
	}
}
