package com.jixu.common.type;

import java.util.HashMap;

public class AssetTypes {
	public final static String FIXED_INCOME="10";		//固定收益
	public final static String OPEN_REPO="11";			//不定期回购协议
	public final static String MONEY_MARKET="15";		//货币市场
	public final static String TERM_REPO="16";			//定期回购协议
	public final static String MONEY_MARKET_FUND = "17"; //货币基金
	public final static String MBS="20";
	public final static String TBA="21";					//TBA
	public final static String CMO="25";
	public final static String ABS="26";	
	public final static String PREFERRED_STOCK="30";	//优先股
	public final static String COMMON_STOCK="F10";		//普通股
	public final static String PARTNERSHIPS="45";		//合股
	public final static String OPTION="60";				//期权
	public final static String FUTURES="F30";			//期货
	public final static String SWAP_CURRENCY="F34";			//外汇掉期
	public final static String SWAP_FX="F13";			//外汇掉期
	public final static String SWAP_INTEREST="F14";				//互换
	public final static String CREDIT_SELF="90";
	public final static String CASH="100";
	public final static String FORWARD_CURRENCY="F12";	//远期换汇
	public final static String MIXED_HEDGE="102";
	public final static String CAPITAL="103";			//股本，对应申赎
	public final static String SPOT_CURRENCY="F11";		//即期换汇
	public final static String TimeDeposit="105";		//定期存款
	//public final static String Convertible_bond=106;	//可转债
	public final static String COMMON_FUND="107";		//普通基金
	public final static String Bond="108";				//债券
	public final static String BORROW = "109";           //融资协议
	public final static String SEC_LENDING = "110";      //融券协议
	public final static String INDEX="111";				//指数,目前用于数据中心
	public final static String BankZiGuan="112";				//银行资管产品，可以转换成申赎
	
	public final static String BasePoint="113";				//基准指数
	public final static String FXOption="114";	      //货币期权
	public final static String ALL="0";

	public final static HashMap<String, String> getPortAsset(boolean all){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		if (all){
			optionHM.put(ALL, "全部");
		}
		optionHM.put(TERM_REPO, "定期回购协议");
		optionHM.put(COMMON_STOCK, "普通股");
		optionHM.put(MONEY_MARKET_FUND, "货币基金");
		optionHM.put(Bond, "债券");
		optionHM.put(COMMON_FUND, "普通基金");
		//optionHM.put(Convertible_bond, "可转债");
		optionHM.put(TimeDeposit, "定期存款");
		optionHM.put(OPTION, "期权");
		optionHM.put(FUTURES, "期货");
		optionHM.put(BORROW, "融资协议");
		optionHM.put(SEC_LENDING, "融券协议");
		optionHM.put(SWAP_INTEREST, "利率互换");
		optionHM.put(SWAP_FX, "外汇掉期");
		optionHM.put(BankZiGuan, "组合批次");
		optionHM.put(BasePoint, "基准指数");
		return optionHM;
	}
	
	public final static HashMap<String, String> getFxAsset(boolean all){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		if (all){
			optionHM.put(ALL, "全部");
		}
		optionHM.put(SPOT_CURRENCY, "即期换汇");
		optionHM.put(FORWARD_CURRENCY, "远期换汇");
		optionHM.put(FXOption, "货币期权");
		return optionHM;
	}
	
	public final static HashMap<String, String> getFxAsset(){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		optionHM.put(FORWARD_CURRENCY, "远期换汇");
		optionHM.put(SWAP_FX, "外汇掉期");
		optionHM.put(SWAP_INTEREST, "利率互换");
		optionHM.put(SPOT_CURRENCY, "即期换汇");
		return optionHM;
	}
	
	public final static HashMap<String, String> getIntAsset(){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		optionHM.put(MONEY_MARKET_FUND, "货币基金");
		optionHM.put(TERM_REPO, "回购");
		return optionHM;
	}
	
	public final static HashMap<String, String> getAllAssets(boolean all){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		if (all){
			optionHM.put(ALL, "全部");
		}
		optionHM.put(TERM_REPO, "定期回购协议");
		optionHM.put(COMMON_STOCK, "普通股");
		optionHM.put(MONEY_MARKET_FUND, "货币基金");
		optionHM.put(Bond, "债券");
		optionHM.put(COMMON_FUND, "普通基金");
		//optionHM.put(Convertible_bond, "可转债");
		optionHM.put(TimeDeposit, "定期存款");
		optionHM.put(OPTION, "期权");
		optionHM.put(FUTURES, "期货");
		optionHM.put(BORROW, "融资协议");
		optionHM.put(SEC_LENDING, "融券协议");
		optionHM.put(SWAP_INTEREST, "利率互换");
		optionHM.put(SWAP_CURRENCY, "外汇掉期");
		optionHM.put(BankZiGuan, "组合批次");
		optionHM.put(BasePoint, "基准指数");
		optionHM.put(FXOption, "货币期权");
		optionHM.put(FORWARD_CURRENCY, "远期换汇");
		optionHM.put(SWAP_FX, "外汇掉期");
		return optionHM;
	}
	
	public final static HashMap<String, String> getBusiType(){
		HashMap<String, String> optionHM=new HashMap<String, String>();
		optionHM.put(COMMON_STOCK, "32");
		optionHM.put(SPOT_CURRENCY, "10");
		optionHM.put(FORWARD_CURRENCY, "11");
		optionHM.put(SWAP_FX, "12");
		optionHM.put(SWAP_INTEREST, "13");
		return optionHM;
	}
}
