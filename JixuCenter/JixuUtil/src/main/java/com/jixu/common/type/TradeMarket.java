package com.jixu.common.type;

import java.util.HashMap;

/** 
 * @ClassName 		TradeMarket
 * @author  		Pan Wei 
 * @Date 			2016年10月4日 下午10:40:41 	
 * @Description		[]  
 * @return  
 */

public class TradeMarket {
	public final static int level1 = 1;
	public final static int level2 = 2;
	public static HashMap<Integer, String> tradeMarketHM=null;
	
	public final static HashMap<Integer, String> getBankAcctTypeHM() {
		if(tradeMarketHM==null){
			tradeMarketHM=new HashMap<Integer, String>();
			tradeMarketHM.put(level1, "一级市场");
			tradeMarketHM.put(level2, "二级市场");
		}
		return tradeMarketHM;
	}
	
	public final static int PT_BOND=0;
	public final static int TX_BOND=1;
	public final static int HBFX_BOND=2;
	public final static int DK=3;
	public static HashMap<Integer, String> fixIncomeTypeHM=null;
	
	public final static HashMap<Integer, String> getFixIncomeTypeHM() {
		if(fixIncomeTypeHM==null){
			fixIncomeTypeHM=new HashMap<Integer, String>();
			fixIncomeTypeHM.put(PT_BOND, "普通付息债");
			fixIncomeTypeHM.put(TX_BOND, "贴现债");
			fixIncomeTypeHM.put(HBFX_BOND, "一次性还本付息债");
			fixIncomeTypeHM.put(DK, "贷款");
		}
		return fixIncomeTypeHM;
	}
	
	
}
