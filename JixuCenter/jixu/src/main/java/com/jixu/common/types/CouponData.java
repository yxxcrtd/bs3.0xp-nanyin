package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName CouponData
 * @author Pan Wei
 * @Date 2016年9月26日 下午10:48:43
 * @Description []
 */

public class CouponData {
	public final static int SemiAnnual = 83;
	public final static int Quarterly = 81;
	public final static int Monthly = 77;
	public final static int Annual = 65;
	public final static int Scheduled = 84;
	public final static int AtMaturity = 66;
	public final static int EveryNDays = 79;
	public final static int Weekly = 82;

	public final static int FollowPayDate = 88;

	public final static HashMap<Integer, String> intCpnfReqTypeHM = new HashMap<Integer, String>();
	static {
		intCpnfReqTypeHM.put(Monthly, "每月");
		intCpnfReqTypeHM.put(Quarterly, "季度");
		intCpnfReqTypeHM.put(SemiAnnual, "半年度");
		intCpnfReqTypeHM.put(Annual, "年度");
		intCpnfReqTypeHM.put(Scheduled, "计划");
		intCpnfReqTypeHM.put(EveryNDays, "每N天");
		intCpnfReqTypeHM.put(AtMaturity, "到期");
	}
	
	public final static HashMap<Integer, String> newCpnfReqTypeHM = new HashMap<Integer, String>();
	static {
		newCpnfReqTypeHM.put(Monthly, "每月");
		newCpnfReqTypeHM.put(Quarterly, "季度");
		newCpnfReqTypeHM.put(SemiAnnual, "半年度");
		newCpnfReqTypeHM.put(Annual, "年度");
		newCpnfReqTypeHM.put(AtMaturity, "到期");
	}

	public final static HashMap<Integer, String> cashFlowFloatReqTypeHM = new HashMap<Integer, String>();
	static {
		cashFlowFloatReqTypeHM.put(Monthly, "每月");
		cashFlowFloatReqTypeHM.put(Quarterly, "季度");
		cashFlowFloatReqTypeHM.put(SemiAnnual, "半年度");
		cashFlowFloatReqTypeHM.put(Annual, "年度");
		cashFlowFloatReqTypeHM.put(EveryNDays, "每N天");
		cashFlowFloatReqTypeHM.put(Weekly, "每周");
		cashFlowFloatReqTypeHM.put(FollowPayDate, "跟随付息日");
	}

	public final static HashMap<Integer, String> cpnBankfreqTypeHM = new HashMap<Integer, String>();
	static {
		cpnBankfreqTypeHM.put(Monthly, "每月");
		cpnBankfreqTypeHM.put(Quarterly, "季度");
		cpnBankfreqTypeHM.put(SemiAnnual, "半年度");
		cpnBankfreqTypeHM.put(Annual, "年度");
	}

	public final static int BusinessDay = 1; // 以工作日计算
	public final static int NaturalDay = 2; // 自然日

	public final static HashMap<Integer, String> delayDateTypeOption = new HashMap<Integer, String>();
	static {
		delayDateTypeOption.put(BusinessDay, "工作日");
		delayDateTypeOption.put(NaturalDay, "自然日");
	}

	public final static int cpn_fixed = 70;
	public final static int cpn_float = 86;
	public final static int cpn_adjust = 85;
	public final static int cpn_step = 84;

	public final static HashMap<Integer, String> cpnInterestTypeOption = new HashMap<Integer, String>();
	static {
		cpnInterestTypeOption.put(cpn_fixed, "固定");
		cpnInterestTypeOption.put(cpn_float, "浮动");
		cpnInterestTypeOption.put(cpn_adjust, "重置");
		cpnInterestTypeOption.put(cpn_step, "按阶");
	}
	
	public final static HashMap<Integer, String> cfSwapTypeOption = new HashMap<Integer, String>();
	static {
		cfSwapTypeOption.put(cpn_fixed, "固定");
		cfSwapTypeOption.put(cpn_float, "浮动");
	}
}
