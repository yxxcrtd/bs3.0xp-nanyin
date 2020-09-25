package com.jixu.common.types;
/** 
 * @ClassName 		FeePostType
 * @author  		Pan Wei 
 * @Date 			2016年10月13日 下午3:50:36 	
 * @Description		[]  
 */

public class PostType {
	public final static int Transaction=1;			//证券清算款
	public final static int Income=2;				//利息收入
	public final static int ContributionWithdrawal=3;
	public final static int WithholdingTaxReclaimable=4;
	public final static int InteportfolioTransfer=5;
	public final static int ForwardCurrencyContract=6;		//换汇
	public final static int BankAccountInterestorOverdraftFees=7;
	public final static int Fees=8;					//费用
	public final static int WithholdingTaxNotReclaimable=9;
	public final static int WithholdingTaxRelief=10;
	public final static int FeesIncome=11;			//费用收入
	public final static int ReclaimableWithholdingTaxIncome=12;	
	public final static int NonReclaimableWithholdingTaxIncome=13;
	public final static int WithholdingTaxReliefIncome=14;
}
