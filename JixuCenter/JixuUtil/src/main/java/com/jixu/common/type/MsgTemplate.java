package com.jixu.common.type;

import java.util.HashMap;

public class MsgTemplate {
	public final static String SECID_Header="SECID";
	public final static String SECID_Fields="SecurityID";

	public final static String PRICINGDATA_Header="PRICINGDATA";
	public final static String PRICINGDATA_Fields="Operation,PriceID,SECID(),PriceDate,Price,PriceTo,Yield,Source()";
	
	public final static String SOURCE_Header="SOURCE";
	public final static String SOURCE_Fields="SourceID";
	
	public final static String FXRATE_Header="FXRATE";
	public final static String FXRATE_Fields="Operation,FXID,Cur(),Cur(),FxRate,RateDate,Source()";
	
	public final static String CURRENCY_Header="CUR";
	public final static String CURRENCY_Fields="CurrencyCode";
	
	public final static String INCOMECUR_Header="INCOMECUR";
	public final static String INCOMECUR_Fields="CurrencyCode";
	
	public final static String PRICUR_Header="PRICUR";
	public final static String PRICUR_Fields="CurrencyCode";
	
	public final static String SMFISSUER_Header="SMFISSUER";
	public final static String SMFISSUER_Fields="Operation,IssuerCode,IssuerName,IssuerShortName,Country(),IssuerType";
	
	public final static String Country_Header="COUNTRY";
	public final static String Country_Fields="CountryCode";
	
	public final static String IssuerType_Header="ISSUERTYPE";
	public final static String IssuerType_Fields="IssuerTypeCode";
	
	public final static String StockType_Header="STOCKTYPE";
	public final static String StockType_Fields="StockTypeCode";	
	
	public final static String SMFStock_Header="SMFSTOCK";
	public final static String SMFStock_Fields="Operation,Cusip,IssuerCode,SecID(),SecName,SmfType,StockType(),PriCur(),IncomeCur(),Country(),Exchange(),COAGROUP(),Source()";
	
	public final static String SMFMMFund_Header = "SMFMMFUND";
	public final static String SMFMMFund_Fields="Operation,Cusip,IssuerCode,SecID(),SecName,SmfType,StockType(),PriCur(),IncomeCur(),Country(),Exchange(),COAGROUP(),Source(),CashFlowCfgID()";
	
	public final static String TimeDeposit_Header = "SMFTIMEDEP";
	public final static String TimeDeposit_Fields="Operation,Cusip,IssuerCode,SecID(),SecName,SmfType,StockType(),PriCur(),IncomeCur(),Country(),COAGROUP(),Source(),CashFlowCfgID()";
	
	public final static String CashFlowCfgID_Header="CASHFLOWCFGID";
	public final static String CashFlowCfgID_Fields="CashFlowCfgID";
	
	public final static String EXCHANGE_Header="EXCHANGE";
	public final static String EXCHANGE_Fields="ExchangeCode";
	
	public final static String COAGROUP_Header="COAGROUP";
	public final static String COAGROUP_Fields="CoaGroupCode";
	
	public final static String StockTran_Header="TranStock";
	public final static String StockTran_Fields="Operation,TaNum,TranType,SecID(),TradeDate,SetDate,TradeMarket,Broker(),SecAcct(),BNK(),MANAGER(),PORTFOLIO(),UNITES,PRINCIPAL,ACCRUED,TRANFEE(),CASH,FXRate,INTATMAT,SETTLECASH()";
	
	public final static String CostUpdateTran_Header="TranCostUpdate";
	public final static String IncomeUpdateTran_Header="TranIncomeUpdate";
	
	public final static String TranMMFund_Header="TranMMFund";
	public final static String FundTran_Fields="Operation,TaNum,TranType,SecID(),TradeDate,SetDate,TradeMarket,Broker(),SecAcct(),BNK(),MANAGER(),PORTFOLIO(),UNITES,PRINCIPAL,TRANFEE(),CASH,FXRate,INTATMAT,SETTLECASH()";
	
	public final static String TranComFund_Header="TranComFund";
	public final static String TranComFundSwitch_Header="TranComFundSwitch";
	
	public final static String BNK_Header="BNK";
	public final static String BNK_Fields="BankAcctID";
	
	public final static String LongBNK_Header="LONGBNK";
	public final static String LongBNK_Fields="BankAcctID";
	
	public final static String ShortBNK_Header="SHORTBNK";
	public final static String ShortBNK_Fields="BankAcctID";
	
	public final static String BROKER_Header="BROKER";
	public final static String BROKER_Fields="BrokerCode";
	
	public final static String Portfolio_Header="PORTFOLIO";
	public final static String Portfolio_Fields="PortfolioCode";
	
	public final static String Manager_Header="MANAGER";
	public final static String Manager_Fields="ManagerCode";
	
	public final static String Settle_Header="SETTLECASH";
	public final static String Settle_Fields="Cur(),FXRate,SettlementCash";
	
	public final static String TranFee_Header="TRANFEE";
	public final static String TranFee_Fields="TRANFEESUB()";
	
	public final static String TranFeeSub_Header="TRANFEESUB";
	public final static String TranFeeSub_Fields="(Operation,FeeType,RecOrPay,SettleDate,BNK(),Cur(),CapitalisedFeeamt,ExpensedFeeamt,TotalFeeamt,AMTEXCLFROMCASH,DEFERREDFEEAMT,LocalFXRate)";
	
	public final static String ShareTran_Header="TranShare";
	public final static String ShareTran_Fields ="Operation,Tanum,TranType,TradeDate,SettleDate,PORTFOLIO(),SecAcct(),BNK(),NAV,Shares,Cash,TRANFEE(),SETTLECASH()";
	
	public final static String CashMove_Header="TranCashMove";
	public final static String CashMove_Fields="Operation,Tanum,PORTFOLIO(),TradeDate,SettleDate,ShortBNK(),ShortCash,LongBNK(),LongCash";
	
	public final static String TARepoLend_Header="TranRepoLend";
	public final static String TARepoLend_Fields="Operation,Tanum,SecID(),TradeType,Portfolio(),TradeMarket,Manager(),TradeDate,SettleDate,Broker(),SecAcct(),BNK(),Amount,Income,ALDate,ALRate,Intatmat,MaturityAmount,FXRate,SETTLECASH(),Collat()";
	
	public final static String TARepoLend_Maturity_Header="TranRepoLendMat";
	public final static String TARepoBorrow_Maturity_Header="TranRepoBorrowMat";
	public final static String TARepoLend_Maturity_Fields="Operation,Tanum,SecID(),TradeType,Portfolio(),TradeDate,SettleDate,SecAcct(),BNK(),Amount,ALDate,ALRate,Intatmat,FXRate,SETTLECASH(),Collat()";
	
	public final static String Collat_Header="COLLAT";
	public final static String Collat_Fields="COLLATSUB()";
	
	public final static String CollatSub_Header="COLLATSUB";
	public final static String CollatSub_Fields="SecAcct(),SecID(),Units";
	
	public final static String SmfBond_Header="SMFBOND";
	public final static String SmfBond_Fields="Operation,Cusip,IssuerCode,SecID(),SecName,SmfType,StockType(),PriCur(),IncomeCur(),Country(),Exchange(),COAGROUP(),Source(),CouponData()";
	
	public final static String CouponData_Header="COUPONDATA";
	public final static String CouponData_Fields="CouponType,CouponRate,CouponFrequency,DayCount,CouponDelay()";
	
	public final static String CouponDelay_Header="COUPONDELAY";
	public final static String CouponDelay_Fields="DelayFlag,DelayType,Holiday(),DelayNum";
	
	public final static String HolidaySD_Header="HOLIDAY";
	public final static String HolidaySD_Fields="HolidaySD";
	
	public final static String SecAcct_Header="SECACCT";
	public final static String SecAcct_Fields="SecAcctCode";
	
	public final static String CCID_Header="CCID";
	public final static String CCID_Fields="User,Time";
	
	public final static String CashOff_Header="TranCashOff";
	public final static String CashOff_Fields="Operation,OFFID,PORTFOLIO(),TradeDate,SettleDate,BNK(),FuncAmount,LocalAmount,DBCR,Glacct,Desc";
	
	public final static String CashContrib_Header="CASHCONTRIB";
	public final static String CashContrib_Fields="Operation,TANUM,PORTFOLIO(),TradeDate,SettleDate,BNK(),FuncAmount,LocalAmount,DBCR";
	
	public final static String FxOpenTran_Header="TranFxOpen";
	public final static String FxOpenTran_Fields="Operation,Tanum,PORTFOLIO(),TradeDate,SettleDate,ShortBNK(),ShortCash,LongBNK(),LongCash,Broker(),Intent,Analogue,GlGroup";

	public final static String JournalEntry_Header="JOURNALENTRY";
	public final static String JournalEntry_Fields="OPERATION,VOUCHERNUM,PORTFOLIO(),LedgerDate,JournalSourceCode,LEDGERENTRY(),Description";
	
	public final static String Journal_Header="Journal";
	
	public final static String LedgerEntry_Header="LEDGERENTRY";
	public final static String LedgerEntry_Fields="LedgerEntrySub()";
	
	public final static String CoaHdr_Header="COAHDR";
	public final static String CoaHdr_Fields="CoaHdrNum";
	
	public final static String Coa_Header="COA";
	public final static String Coa_Fields="OPERATION,AcctNum,AcctName,COAHDR(),Category,CloseAcctNum";
	
	public final static String LedgerEntrySub_Header="LEDGERENTRYSUB";
	public final static String LedgerEntrySub_Fields="Glacct,DrFunc,CrFunc,Cur(),DrLocal,CrLocal,FXRate";
	
	public final static String FutureTranBuy_Header="TranFutureBuy";
	public final static String FutureTranSale_Header="TranFutureSale";
	
	public final static String TranBond_Header="TranBond";
	public final static String SecContribution_Header="SecContribution";
	
	public final static String HolidaysOperate_Header="HolidaysOperate";
	
	public static HashMap<String, String> fieldsTemplateHM=null;
	
	public static HashMap<String, String> msgTemplateHM=null;
	
	public static HashMap<String, String> subListMsgHM=null;
	
	public static HashMap<String, String> getSubListMsgHM(){
		if(subListMsgHM==null){
			subListMsgHM=new HashMap<String, String>();
			subListMsgHM.put(TranFee_Header, TranFee_Header);
			subListMsgHM.put(Collat_Header, Collat_Header);
			subListMsgHM.put(LedgerEntry_Header, LedgerEntry_Header);
		}
		return subListMsgHM;
	}
}
