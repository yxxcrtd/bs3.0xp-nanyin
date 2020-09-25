package com.jixu.common.types;

import java.util.HashMap;

public class PortfolioTypes {
	public final static int all_fund = 0; // 全部
	public final static int public_fund = 1; // 公募基金
	public final static int private_fund = 2; // 私募基金
	public final static int special_fund = 3; // 专户
	public final static int trust_plan = 4; // 信托计划
	public final static int bank_finance = 5; // 银行理财
	public final static int Insurance_finance = 6; // 保险理财
	public final static int public_qdii_fund = 7; // 公募QDII

	public final static HashMap<Integer, String> portfolioTypesHM = new HashMap<Integer, String>();
	static {
		portfolioTypesHM.put(all_fund, "全部");
		portfolioTypesHM.put(public_fund, "公募基金");
		portfolioTypesHM.put(private_fund, "私募基金");
		portfolioTypesHM.put(special_fund, "专户");
		portfolioTypesHM.put(trust_plan, "信托计划");
		portfolioTypesHM.put(bank_finance, "银行理财");
		portfolioTypesHM.put(Insurance_finance, "保险理财");
		portfolioTypesHM.put(public_qdii_fund, "QDII");
	}
}
