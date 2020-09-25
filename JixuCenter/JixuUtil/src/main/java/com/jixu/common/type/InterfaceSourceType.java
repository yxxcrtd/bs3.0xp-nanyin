package com.jixu.common.type;

import java.util.HashMap;

public class InterfaceSourceType {
	public final static int smf=1;
	public final static int price=2;
	public final static int fxRate=3;
	public final static int tran=4;
	public final static int corpAction=5;
	public final static int fxTrade = 6;
	public final static int ta = 7;
	public final static int cash = 8;
	public final static int manual = 9;
	
	/**
	 * 为导出模块设定一个type
	 */
	public final static int exportType=0;
	
	public static HashMap<Integer, String> interfaceImporttypeHM=new HashMap<Integer, String>();
	static{
		interfaceImporttypeHM.put(tran, "投资组合交易");
		interfaceImporttypeHM.put(corpAction, "公司行为");
		interfaceImporttypeHM.put(price, "价格");
		interfaceImporttypeHM.put(smf, "证券主文件");
		interfaceImporttypeHM.put(fxRate, "汇率");
		interfaceImporttypeHM.put(fxTrade, "换汇交易");
		interfaceImporttypeHM.put(ta, "申购赎回交易");
		interfaceImporttypeHM.put(cash, "现金交易");
		interfaceImporttypeHM.put(manual, "手工分录");
	}
}
