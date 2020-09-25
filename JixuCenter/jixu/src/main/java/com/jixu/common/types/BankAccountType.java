package com.jixu.common.types;

import java.util.HashMap;

/** 
 * @ClassName 		BankAccountType
 * @author  		Pan Wei 
 * @Date 			2016年9月9日 下午1:21:05 	
 * @Description		[]  
 */

public class BankAccountType {
	public final static int Cash_Account=1;		//现金账户
	public final static int Cash_DefaultOffet=2;	//默认冲销
	public final static int Cash_RealizedFxGainLoss=3;	//已实现外汇损益
	public final static int Cash_Tolerance=4;				//限额
	public final static int Cash_UnRealizedFxGainLoss=5;	//未实现外汇损益
	public final static int Cash_UnRealizedFxGainLossOffset=6;	//未实现外汇损益冲销
	public final static int Cash_TruncationGain=7;		//截尾收益
	public final static int Cash_TruncationLoss=8;		//截尾损益
	
	public final static int Int_InterestReceived=9;		//已收利息
	public final static int Int_Rcpy=10;			//应收应付利息
	public final static int Int_RealizedFxGainLoss=11;	//已实现外汇损益
	public final static int Int_Tolerance=12;		//限度
	public final static int Int_UnRealizedFxGainLoss=13;	//未实现外汇损益
	public final static int Int_UnRealizedFxGainLossOffset=14;	//未实现外汇损益冲销
	public final static int Int_InterestAccrued=15;	//应计利息
	public final static int Int_InterestAccruedOffset=16;	//应计利息冲销
	
	public final static int OD_OverDraftPaid=17;
	public final static int OD_Rcpy=18;
	public final static int OD_RealizedFxGainLoss=19;
	public final static int OD_Tolerance=20;
	public final static int OD_UnRealizedFxGainLoss=21;
	public final static int OD_UnRealizedFxGainLossOffset=22;
	public final static int OD_InterestAccrued=23;
	public final static int OD_InterestAccruedOffset=24;
	
	public final static HashMap<Integer,String> optionHM=new HashMap<Integer, String>();
	static{
		optionHM.put(Cash_Account, "现金(现金账户)");
		optionHM.put(Cash_DefaultOffet, "现金(默认冲销)");
		optionHM.put(Cash_RealizedFxGainLoss, "现金(已实现外汇损益)");
		optionHM.put(Cash_Tolerance, "现金(限额)");
		optionHM.put(Cash_UnRealizedFxGainLoss, "现金(未实现外汇损益)");
		optionHM.put(Cash_UnRealizedFxGainLossOffset, "现金(未实现外汇损益冲销)");
		optionHM.put(Cash_TruncationGain, "现金(截尾收益)");
		optionHM.put(Cash_TruncationLoss, "现金(截尾损益)");
		
		optionHM.put(Int_InterestReceived, "利息(已收利息)");
		optionHM.put(Int_Rcpy, "利息(应收应付)");
		optionHM.put(Int_RealizedFxGainLoss, "利息(已实现外汇损益)");
		optionHM.put(Int_Tolerance, "利息(限度)");
		optionHM.put(Int_UnRealizedFxGainLoss, "利息(未实现外汇损益)");
		optionHM.put(Int_UnRealizedFxGainLossOffset, "利息(未实现外汇损益冲销)");
		optionHM.put(Int_InterestAccrued, "利息(应计利息)");
		optionHM.put(Int_InterestAccruedOffset, "利息(应计利息冲销)");
		
		optionHM.put(OD_OverDraftPaid, "透支(已付透支)");
		optionHM.put(OD_Rcpy, "透支(应收应付)");
		optionHM.put(OD_RealizedFxGainLoss, "透支(已实现外汇损益)");
		optionHM.put(OD_Tolerance, "透支(限度)");
		optionHM.put(OD_UnRealizedFxGainLoss, "透支(未实现外汇损益)");
		optionHM.put(OD_UnRealizedFxGainLossOffset, "透支(未实现外汇损益冲销)");
		optionHM.put(OD_InterestAccrued, "透支(应计利息)");
		optionHM.put(OD_InterestAccruedOffset, "透支(应计利息冲销)");
	}
}
