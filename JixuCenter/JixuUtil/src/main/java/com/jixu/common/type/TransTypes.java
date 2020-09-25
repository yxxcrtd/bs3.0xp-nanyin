package com.jixu.common.type;

import java.util.ArrayList;
import java.util.List;

public class TransTypes {
	public final static int PURCHASE = 100;
	public final static int StockDividend=101;
	public final static int SecContribution = 102;
	public final static int CashDividend=103;
	public final static int NewStock_Sub=104;		//网上申购
	public final static int NewStock_Receive=105;	//新股中签
	public final static int NewStock_sub_under=106;	//网下申购
	public final static int NewStock_Onboard=106;	//新股上市
	public final static int TENDER_OFFER=107;	//要约收购
	public final static int ALLOT_SHARES=108;	//配股
	public final static int SALE = 200;
	public final static int CASHOFF = 700;
	public final static int CASHMOVE = 711;
	public final static int CashContribution = 712;
	public final static int STOCKSPLIT = 150;
	public final static int REVSTOCKSPLIT = 251;
	public final static int SUB=500;
	public final static int Share_SwitchIn=501;
	public final static int Share_init=502;
	public final static int RED=600;
	public final static int Share_SwitchOut=601;
	public final static int Share_Divident=602;
	public final static int Share_Split=603;
	public final static int Share_Div2Share=604;
	public final static int REPO_LEND=131;
	public final static int REPO_BORROW=132;	//正回购
	public final static int REPO_LEND_Contribution=133;
	public final static int ShareBankZG=134;	//银行资管的申购交易
	public final static int ShareBankZGMaturity=135;	//银行资管的申购到期
	public final static int ShareBankZGPayment=136;	//银行资管的支付费用
	public final static int ShareBankZGRed=137;	//银行资管的申购交易
	public final static int ShareBankZGPayFee=138;	//银行资管的费用支付
	public final static int ShareBankZGDiv2Share=139;	//银行资管的红利转份额
	public final static int REPO_LEND_MATURITY=233;
	public final static int REPO_BORROW_MATURITY=234;
	public final static int FX_OPENING=300;			//Opening Transaction
	public final static int FX_CLOSING=400;			//Closing Transaction
	public final static int FX_DELIVERY=405;		//Delivery Transaction
	public final static int FORWORD_CURRENCY=406;//远期换汇
	public final static int FORWORD_CURRENCY_OPEN=407;//远期换汇开仓交易
	public final static int FORWORD_CURRENCY_CLOSE=408;//远期换汇平仓交易
	public final static int FORWORD_CURRENCY_Cutting=409;//远期换汇轧差交割
	public final static int FORWORD_CURRENCY_Maturity=410;//远期换汇正常到期
	
	public final static int SWAP_OPEN_Interest=750;	//利率互换
	public final static int SWAP_Maturity_Interest=751;	//利率到期
	public final static int SWAP_Pay_Interest=752;	//利率互换付息
	public final static int SWAP_AutoRec_Interest=753;	//利率中间收息
	public final static int SWAP_AutoPay_Interest=754;	//利率中间付息
	
	public final static int SWAP_FX_OPEN=760;	//外汇掉期开仓
	public final static int SWAP_FX_MATURITY=761;	//外汇掉期到期
	
	public final static int FXOption_Buy=780;         //货币期权-买入
	public final static int FXOption_Sale=781;        //货币期权—卖出
	public final static int FXOption_FullExercise=782;    //货币期权-全额行权
	public final static int FXOption_NetExercise=783;   //货币期权-净额行权
	public final static int FXOption_AbandonExercise=784; //货币期权-放弃行权
	
	
	public final static int MMFUND_SUB = 800; //基金申购
	public final static int MMFUND_RED = 801; //基金赎回
	public final static int MMFUND_CONVERT = 802; //基金收益转份额
	
	public final static int TimeDep_Buy=810;	//买入交易
	public final static int TimeDep_sale=811;	//提前预支
	
	public final static int Option_OpenBuy=820;  //期权开仓买入
	public final static int Option_CloseSale=821; //期权平仓卖出
	public final static int Option_Receive=822;	//获赠
	public final static int Option_StockExercise=823;	//股票行权
	public final static int Option_CashExercise=824;	//现金行权
	public final static int Option_AbandonExercise=825;	//放弃行权
	public final static int Option_OpenSale=826;	//期权开仓卖出
	public final static int Option_CloseBuy=827;	//期权平仓买入
	
	public final static int Futures_LongBuy=830;	//开仓买入
	public final static int Futures_LongSale=831;	//平仓卖出
	public final static int Futures_ShortSale=832;	//平仓买入
	public final static int Futures_ShortBuy=833;	//开仓卖出
	public final static int Futures_LongtBuy_Contri=834;	//开仓买入期初
	public final static int Futures_ShortBuy_Contri=835;	//开仓卖出期初
	public final static int Futures_Delivery=836;	//实物交割
	public final static int Futures_DefaultDelivery=837;	//交割违约
	
	public final static int Bond2Stock=840;	//	债转股
	
	public final static int JoyinBond2Stock=841;	//兆尹债转股
	
	public final static int Bond_Buy=850;		//买入债券
	public final static int Bond_Sale=851;		//卖出债券
	public final static int Bond_WriteOff=852; //债券减值处理
	public final static int Bond_WriteOff_Prepare=853; //债券减值准备
	public final static int Bond_Reverse=854;//债券减值回转
	public final static int Bond_Hbfx=855;//债券按面额还本付息
	public final static int Bond_Hbfx_Shares=856;//债券按数量还本付息
	
	public final static int CommonFund_Sub=860;	//申购
	public final static int CommonFund_Red=861;	//赎回
	public final static int CommonFund_SwitchIn=862;	//转入
	public final static int CommonFund_SwitchOut=863;	//转出
	public final static int CommonFund_Divident=864;	//分红
	public final static int CommonFund_Divident2Share=865;	//红利再投
	public final static int CommonFund_Contribution=866;	//期初
	
	public final static int Borrow_Buy = 870; //融资交易
	public final static int Borrow_Repayment = 871; //融资偿还
	public final static int Sec_Lend = 880; //融券交易
	public final static int Sec_Return = 881; //融券偿还
	public final static int CostUpdate = 882; //成本调整
	public final static int IncomeUpdate = 883; //收益调整
	public final static int ValMannulPositionUpdate = 884; //收益调整
	
	public final static int Bond_HBFX_Principal=890;	//本金，还本付息
	public final static int Bond_HBFX_Shares=891;	//数量，还本付息
	
	public final static int HK_HGT_Buy=110;	//港股通买入
	public final static int HK_HGT_Sale=210;	//港股通买入
	
	public final static int Portfolio_InternalTran=900;//投资组合间交易
	
	public final static int ALL = 0;
	
	/*public final static HashMap<Integer, String> getTranTypes(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(PURCHASE, "股票买入");
		optionHM.put(SALE, "股票卖出");
		optionHM.put(CASHOFF, "现金冲销");
		optionHM.put(CASHMOVE, "现金划转");
		optionHM.put(STOCKSPLIT, "股票分拆");
		optionHM.put(REVSTOCKSPLIT, "股票分拆");
		optionHM.put(REPO_LEND, "逆回购");
		optionHM.put(REPO_BORROW, "正回购");
		optionHM.put(REPO_LEND_MATURITY, "逆回购到期");
		optionHM.put(REPO_BORROW_MATURITY, "正回购到期");
		optionHM.put(FX_OPENING, "开仓换汇");
		optionHM.put(FX_CLOSING, "平仓换汇");
		optionHM.put(FX_DELIVERY, "交付换汇");
		optionHM.put(SUB, "申购");
		optionHM.put(RED, "赎回");
		return optionHM;
	}
	
	public final static HashMap<Integer, String> getRepoOption(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(REPO_LEND, "逆回购");
		optionHM.put(REPO_BORROW, "正回购");
		return optionHM;
	}
	
	public final static HashMap<Integer, String> getSubRedOption(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(SUB, "申购");
		optionHM.put(RED, "赎回");
		return optionHM;
	}
	
	public final static HashMap<Integer, String> getStockOption(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(PURCHASE, "买入");
		optionHM.put(SALE, "卖出");
		return optionHM;
	}
	
	*//**
	 * 
	 * @param assetType
	 * @return
	 *//*
	public final static HashMap<Integer, String> getTranTypes(int assetType, boolean allFlag){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		if(allFlag){
			optionHM.put(ALL, "全部");
		}
		
		switch(assetType){
		case AssetTypes.COMMON_STOCK:
			optionHM.put(PURCHASE, "买入");
			optionHM.put(SALE, "卖出");
			break;
		case AssetTypes.CASH:
			optionHM.put(CASHOFF, "现金冲销");
			optionHM.put(CASHMOVE, "现金划转");
			break;
		case AssetTypes.CAPITAL:
			optionHM.put(SUB, "申购");
			optionHM.put(RED, "赎回");
			break;
		case AssetTypes.TERM_REPO:
			optionHM.put(REPO_LEND, "逆回购");
			optionHM.put(REPO_LEND_MATURITY, "逆回购到期");
			optionHM.put(REPO_BORROW, "正回购");
			optionHM.put(REPO_BORROW_MATURITY, "正回购到期");
			break;
		case AssetTypes.OPTION:
		case AssetTypes.FUTURES:
		case AssetTypes.PREFERRED_STOCK:
		case AssetTypes.FIXED_INCOME:
		case AssetTypes.FORWARD_CURRENCY:
			optionHM.put(FX_OPENING, "开仓交易");
			optionHM.put(FX_CLOSING, "平仓交易");
			optionHM.put(FX_DELIVERY, "交付交易");
			break;
		case AssetTypes.SPOT_CURRENCY:
			optionHM.put(FX_OPENING, "开仓交易");
			optionHM.put(FX_CLOSING, "平仓交易");
			optionHM.put(FX_DELIVERY, "交付交易");
			break;
		}
		
		return optionHM;
	}*/
	
	public final static List<Integer> getTranTypes(String assetType){
		List<Integer> tranTypeList=new ArrayList<Integer>();
		switch(assetType){
		case AssetTypes.COMMON_STOCK:
			tranTypeList.add(PURCHASE);
			tranTypeList.add(SALE);
			break;
		case AssetTypes.CAPITAL:
			tranTypeList.add(SUB);
			tranTypeList.add(RED);
			break;
		case AssetTypes.Bond:
			tranTypeList.add(Bond_Buy);
			tranTypeList.add(Bond_Sale);
			break;
		}
		return tranTypeList;
	}
}
