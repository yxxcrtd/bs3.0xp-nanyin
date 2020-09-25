package com.jixu.common.types;

import java.util.HashMap;

/**
 * @ClassName FundType
 * @author Pan Wei
 * @Date 2016年12月24日 下午11:50:43
 * @Description [基金类型]
 */

public class CompanyType {
	public static int PrivateFund = 1;
	public static int PublicFund = 2;
	public static int Broker = 3;
	public static int TrustCOM = 4;
	public static int Insurance = 5;
	public static int CustodyBank = 6;

	public final static HashMap<Integer, String> optionHM = new HashMap<Integer, String>();
	static {
		optionHM.put(PrivateFund, "私募基金");
		optionHM.put(PublicFund, "公募基金");
		optionHM.put(Broker, "券商");
		optionHM.put(TrustCOM, "信托公司");
		optionHM.put(Insurance, "保险公司");
		optionHM.put(CustodyBank, "托管银行");
	}
}
