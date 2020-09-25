package com.jixu.common.types;

public class JixuDictCode {
	//手动导入交易
	public static int IMP_TRADE_TYPE_MANNUAL=1;
	
	//接口导入交易
	public static int IMP_TRADE_TYPE_INTERFACE=2;
	
	//自动生成的交易,债券自动付息,期货自动结转保证金等交易
	public static int IMP_TRADE_TYPE_AUTO=3;
	
	//默认国家代码
	public static String DEFAULT_COUNTRY_CODE="1";
	//默认省份代码
	public static int DEFAULT_PROVINCE_ID=1;
	//默认投资经理组
	public static int DEFAULT_MANAGERGROUP=1;
	//默认投资经理
	public static int DEFAULT_MANAGER=1;
	//默认客户编号
	public static int DEFAULT_CLIENT_ID=1;
	//默认券商
	public static String DEFAULT_BROKER_CODE="5002";
	
	//自定义类型 即期换汇
	public static int DEFAULT_RPSECTYPE_FXSPOT=114;
	
	//手工创建源
	public static int INTERFACE_SOURCE_MANNUAL=0;
	
	public static String JOYIN_SUCCESS_STATUS="0000";
	
	//存储银行现金账户
	public static String JX_JOYIN_MAP_BANK_CASH_ACCT="JXBankCashAcct";
	
	//存储证券账户关键字
	public static String JX_JOYIN_MAP_SEC_ACCT="JXSecAcct";
	
	//普通估增凭证
	public static String JX_VOUCHER_GL="GL_V";
	
	//反冲估增凭证
	public static String JX_VOUCHER_OFF_GL="OFF_GL_V";
}
