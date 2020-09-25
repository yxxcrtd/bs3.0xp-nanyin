package com.jixu.common.util;


import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.TransTypes;

/** 
 * @ClassName 		MsgPackage
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 下午2:20:22 	
 * @Description		[]  
 * @return  
 */

@Service("msgPackage")
public class MsgPackage {
	///StockTran(Operation,TaNum,SecID(),TradeDate,SetDate,Broker(),BNK(),MANAGER,PORTFOLIO(),UNITES,PRINCIPAL,
	//ACCRUED,TRANFEE(),FXRate,INTATMAT,SETTLECASH())
	
	/**
	 * SecID()
	 * 证券
	 * @param sourceID
	 * @param secID
	 * @msg SECID(SourceID,SecurityID)
	 * @return
	 */
	public String secID(String secID){
		//SecID()
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.SECID_Header);
		msg.append("(");
		msg.append(secID);
		msg.append(")");
		return msg.toString();
	}
	
	/**
	 * BNK()
	 * 银行账户
	 * @param bankCode
	 * @param bankAccount
	 * @Msg	BNK(BankCode,BankAccount)
	 * @return
	 */
	public String bnk(String bankAccount){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.BNK_Header);
		msg.append("(");
		msg.append(bankAccount);
		msg.append(")");
		return msg.toString();
	}
	
	/**
	 * SECACCT()
	 * 证券账户
	 * @param SecAcctCode
	 * @param SecAcctCode
	 * @Msg	SecAcct(SecAcctCode)
	 * @return
	 */
	public String secAcct(String SecAcctCode){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.SecAcct_Header);
		msg.append("(");
		msg.append(SecAcctCode);
		msg.append(")");
		return msg.toString();
	}
	
	/**
	 * PORTFOLIO()
	 * 投资组合
	 * @param portCode
	 * @return
	 */
	public String portfolio(String portCode){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.Portfolio_Header);
		msg.append("(");
		msg.append(portCode);
		msg.append(")");
		return msg.toString();
	}
	
	/**
	 * CUR()
	 * @param currencyCode
	 * @Msg CUR(CurrencyCode)
	 * @return
	 */
	public String cur(String currencyCode){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.CURRENCY_Header);
		msg.append("(");
		msg.append(currencyCode);
		msg.append(")");
		return msg.toString();
	}
	
	/**
	 * 现金冲销
	 * @param operation
	 * @param offID
	 * @param portCode
	 * @param tradeDate
	 * @param settleDate
	 * @param cashacct
	 * @param funccash
	 * @param localcash
	 * @param dbcr
	 * @param glacct
	 * @param desc
	 * @return
	 */
	public HashMap<String, Object> cashOff(String operation,String offID,String portCode,Date tradeDate,
			Date settleDate,String cashacct,String funccash,String localcash,
			String dbcr,String glacct,String desc, String secid){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.CashOff_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, offID);
		hm.put(MsgFields.portnum, portCode);
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(settleDate));
		hm.put(MsgFields.bankAcct, cashacct);
		hm.put(MsgFields.tradeCash, localcash);
		hm.put(MsgFields.settleCash, funccash);
		hm.put(MsgFields.glacct, glacct);
		hm.put(MsgFields.tranDesc, desc);
		hm.put(MsgFields.offsetType, dbcr);
		hm.put(MsgFields.secid, secid);
		return hm;
	}
	
	/**
	 * 债券还面额，按照比例还
	 * @param operation
	 * @param tanum
	 * @param portnum
	 * @param tradeDate
	 * @param settleDate
	 * @param rate
	 * @return
	 */
	public HashMap<String, Object> payBondPrincipal(String operation,String tanum, int tranType, String portnum, String secid, Date tradeDate,
			Date settleDate, double rate){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header, "tranBondHbfxPrincipalMsg");
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.portnum, String.valueOf(portnum));
		hm.put(MsgFields.secid, secid);
		hm.put(MsgFields.tradeType, String.valueOf(tranType));
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(settleDate));
		hm.put(MsgFields.alRate, String.valueOf(rate));
		return hm;
	}
	
	/**
	 * 操作凭证,逐渐改到该消息
	 * @param operation
	 * @param vouchernum
	 * @return
	 */
	public HashMap<String, Object> journal(String operation,int vouchernum){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.Journal_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.vouchernum, String.valueOf(vouchernum));
		return hm;
	}
	
	public String cashContrib(String operation,String tanum,String portnum,String tradeDate,
			String settleDate,String cashacct,String funccash,String localcash,
			String dbcr){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.CashContrib_Header);
		msg.append("(");
		msg.append(operation);
		msg.append(",");
		msg.append(tanum);
		msg.append(",");
		msg.append(portfolio(portnum));
		msg.append(",");
		msg.append(tradeDate);
		msg.append(",");
		msg.append(settleDate);
		msg.append(",");
		msg.append(bnk(cashacct));
		msg.append(",");
		msg.append(funccash);
		msg.append(",");
		msg.append(localcash);
		msg.append(",");
		msg.append(dbcr);
		msg.append(")");
		return msg.toString();
	}
	
	public HashMap<String, Object> shareTran(String operation,String tradeType,String port,String tradeDate,String settleDate,
			String secAcct,String bankAcct,String tradeCurrency,String price,String shares,String principal, String totalExpense,
			String tradeLedgerCur,String settleFxCur,String settleCash,String settleFxRate,String feeMsg,String collatMsg){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.ShareTran_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tradeType, tradeType);
		hm.put(MsgFields.portnum, port);
		hm.put(MsgFields.tradeDate, tradeDate);
		hm.put(MsgFields.settleDate, settleDate);
		hm.put(MsgFields.secAcct, secAcct);
		hm.put(MsgFields.bankAcct, bankAcct);
		hm.put(MsgFields.fxCur, tradeCurrency);
		hm.put(MsgFields.price, price);
		hm.put(MsgFields.shares, shares);
		hm.put(MsgFields.principal, principal);
		hm.put(MsgFields.totalExpense, totalExpense);
		hm.put(MsgFields.tradeLedgerCur, tradeLedgerCur);
		hm.put(MsgFields.settleFxCur, settleFxCur);
		hm.put(MsgFields.settleCash, settleCash);
		hm.put(MsgFields.settleFxRate, settleFxRate);
		hm.put(MsgFields.feeMsg, feeMsg);
		hm.put(MsgFields.collat, collatMsg);
		
		return hm;
	}	
		
	public String repoLendMaturityDelete(String tanum){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.TARepoLend_Maturity_Header);
		msg.append("(");
		msg.append("DELETE");
		msg.append(",");
		msg.append(tanum);
		msg.append(")");
		return msg.toString();
	}
	
	public String cashMoveDelete(String tanum){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.CashMove_Header);
		msg.append("(");
		msg.append("DELETE");
		msg.append(",");
		msg.append(tanum);
		msg.append(")");
		return msg.toString();
	}
	
	public HashMap<String, Object> secContribution(String operation, String tanum, Date tradeDate, String portnum, String secID, int market, String brokerID, String secacct, 
			int bankacct, int manager, double units, double principal, double accrued, double alrate, Date alDate, int valueMathod){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.SecContribution_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.tradeType, String.valueOf(TransTypes.SecContribution));
		hm.put(MsgFields.portnum, String.valueOf(portnum));
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.broker, brokerID);
		hm.put(MsgFields.secAcct, String.valueOf(secacct));
		hm.put(MsgFields.bankAcct, String.valueOf(bankacct));
		hm.put(MsgFields.manager, String.valueOf(manager));
		hm.put(MsgFields.shares, String.valueOf(units));
		hm.put(MsgFields.principal, String.valueOf(principal));
		hm.put(MsgFields.accrued, String.valueOf(accrued));
		hm.put(MsgFields.alRate, String.valueOf(alrate));
		hm.put(MsgFields.tradeMarket, String.valueOf(market));
		hm.put(MsgFields.maturityDate, DateUtil.convertD2YYYYMMDD(alDate));
		hm.put(MsgFields.valuationMethod, String.valueOf(valueMathod));	//估值方法，摊余成本法还是公允价值法
		return hm;
	}
	
	public HashMap<String, Object> tranBondMaturity(String operation,String tanum, String secID, Date tradeDate,
			Date setDate,int tradeMarket,String brokerID,String secacct,int bankAcct,int managerID,String portnum,
			double units,double principal,double accrued,double cash,String feeMsg,String fxCur, double fxRate,
			String setCurCode,double setfxRate,double setCash, int valuationType, int hiloCode, int lotnum){
		return tranBond(operation, tanum, TransTypes.Bond_Hbfx, secID, tradeDate, setDate, tradeMarket, brokerID, secacct, bankAcct, managerID, 
				portnum, units, principal, accrued, cash, feeMsg, fxCur, fxRate, setCurCode, setfxRate, setCash, valuationType, hiloCode, lotnum);
	}
	
	public HashMap<String, Object> tranSaleBond(String operation,String tanum, String secID, Date tradeDate,
			Date setDate,int tradeMarket,String brokerID,String secacct,int bankAcct,int managerID,String portnum,
			double units,double principal,double accrued,double cash,String feeMsg,String fxCur, double fxRate,
			String setCurCode,double setfxRate,double setCash, int valuationType, int hiloCode, int lotnum){
		return tranBond(operation, tanum, TransTypes.Bond_Sale, secID, tradeDate, setDate, tradeMarket, brokerID, secacct, bankAcct, managerID, 
				portnum, units, principal, accrued, cash, feeMsg, fxCur, fxRate, setCurCode, setfxRate, setCash, valuationType, hiloCode, lotnum);
	}
	
	private HashMap<String, Object> tranBond(String operation,String tanum, int tranType, String secID, Date tradeDate,
			Date setDate,int tradeMarket,String brokerID,String secacct,int bankAcct,int managerID,String portnum,
			double units,double principal,double accrued,double cash,String feeMsg,String fxCur, double fxRate,
			String setCurCode,double setfxRate,double setCash, int valuationType, int hiloCode, int lotnum){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.TranBond_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.assetType, String.valueOf(AssetTypes.Bond));
		hm.put(MsgFields.tradeType, String.valueOf(tranType));
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.portnum, String.valueOf(portnum));
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(setDate));
		hm.put(MsgFields.tradeMarket, String.valueOf(tradeMarket));
		hm.put(MsgFields.manager, String.valueOf(managerID));
		hm.put(MsgFields.broker, brokerID);
		hm.put(MsgFields.secAcct, String.valueOf(secacct));
		hm.put(MsgFields.bankAcct, String.valueOf(bankAcct));
		hm.put(MsgFields.shares, String.valueOf(units));
		hm.put(MsgFields.principal, String.valueOf(principal));
		hm.put(MsgFields.accrued, String.valueOf(accrued));
		hm.put(MsgFields.feeMsg, feeMsg);
		hm.put(MsgFields.fxCur, String.valueOf(fxCur));
		hm.put(MsgFields.tradeCash, String.valueOf(cash));
		hm.put(MsgFields.tradeFX, String.valueOf(fxRate));
		hm.put(MsgFields.settleFxCur, String.valueOf(setCurCode));
		hm.put(MsgFields.settleFxRate, String.valueOf(setfxRate));
		hm.put(MsgFields.settleCash, String.valueOf(setCash));
		hm.put(MsgFields.valuationMethod, String.valueOf(valuationType));
		hm.put(MsgFields.lotMethod, String.valueOf(hiloCode));
		hm.put(MsgFields.contract, String.valueOf(lotnum));
		return hm;
	}
	
	public HashMap<String, Object> fundTran(String operation,String tanum,int tranType,String secID,Date tradeDate,
			Date setDate,int tradeMarket,String brokerID,String secacct,int bankAcct,int managerID,String portCode,
			double units,double principal,double cash,String feeMsg,String fxCur, double fxRate,
			String setCurCode,double setfxRate,double setCash){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,MsgTemplate.TranMMFund_Header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.tradeType, String.valueOf(tranType));
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(setDate));
		hm.put(MsgFields.tradeMarket, String.valueOf(tradeMarket));
		hm.put(MsgFields.broker, brokerID);
		hm.put(MsgFields.secAcct, secacct);
		hm.put(MsgFields.bankAcct, String.valueOf(bankAcct));
		hm.put(MsgFields.manager, String.valueOf(managerID));
		hm.put(MsgFields.portnum, portCode);
		hm.put(MsgFields.shares, String.valueOf(units));
		hm.put(MsgFields.principal, String.valueOf(principal));
		hm.put(MsgFields.feeMsg, feeMsg);
		hm.put(MsgFields.tradeCash, String.valueOf(cash));
		hm.put(MsgFields.fxCur, String.valueOf(fxCur));
		hm.put(MsgFields.tradeFX, String.valueOf(fxRate));
		hm.put(MsgFields.settleFxCur, String.valueOf(setCurCode));
		hm.put(MsgFields.settleFxRate, String.valueOf(setfxRate));
		hm.put(MsgFields.settleCash, String.valueOf(setCash));
		return hm;
	}
	
	public HashMap<String, Object> repoLendMaturity(String header, String operation,String tanum,String secID, String portCode,Date tradeDate,Date setDate,
			String secacct,int bankAcct,double amount,double intatmat,double fxRate,
			String setCurCode,double setfxRate,double setCash,String collatMsg, String lotnum){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.portnum, portCode);
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(setDate));
		hm.put(MsgFields.secAcct, secacct);
		hm.put(MsgFields.bankAcct, String.valueOf(bankAcct));
		hm.put(MsgFields.shares, String.valueOf(amount));
		hm.put(MsgFields.intatmat, String.valueOf(intatmat));
		hm.put(MsgFields.tradeFX, String.valueOf(fxRate));
		hm.put(MsgFields.settleFxCur, String.valueOf(setCurCode));
		hm.put(MsgFields.settleFxRate, String.valueOf(setfxRate));
		hm.put(MsgFields.settleCash, String.valueOf(setCash));
		hm.put(MsgFields.collat, collatMsg);
		hm.put(MsgFields.contract, lotnum);
		return hm;
	}
	
	public HashMap<String, Object> swapInterestMaturity(String operation,String tanum,String secID, String portCode,Date tradeDate,Date settleDate,
			int bankAcct,double principal, double accrued, String tradeCurrency, double fxRate, String setCurCode,double setfxRate, double setCash, int lotnum){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header, "tranSwapInterestMaturityMsg");
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.portnum, portCode);
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(settleDate));
		hm.put(MsgFields.bankAcct, String.valueOf(bankAcct));
		hm.put(MsgFields.principal, String.valueOf(principal));
		hm.put(MsgFields.fxCur, String.valueOf(tradeCurrency));
		hm.put(MsgFields.tradeFX, String.valueOf(fxRate));
		hm.put(MsgFields.settleFxCur, String.valueOf(setCurCode));
		hm.put(MsgFields.settleFxRate, String.valueOf(setfxRate));
		hm.put(MsgFields.settleCash, String.valueOf(setCash));
		hm.put(MsgFields.contract, String.valueOf(lotnum));
		hm.put(MsgFields.accrued, String.valueOf(accrued));
		return hm;
	}

	public HashMap<String, Object> timeDepositMaturity(String header, String operation,String tanum,String secID,String portCode,int managerID, 
			Date tradeDate, Date settleDate, String secacct,int bankAcct,double amount,double intatmat,String tradeCur, double fxRate, double tradeCash,
			String setCurCode,double setfxRate,double setCash, int lotnum, String feeMsg){
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put(MsgFields.header,header);
		hm.put(MsgFields.operation, operation);
		hm.put(MsgFields.tanum, tanum);
		hm.put(MsgFields.secid, secID);
		hm.put(MsgFields.contract, String.valueOf(lotnum));
		hm.put(MsgFields.portnum, portCode);
		hm.put(MsgFields.manager, String.valueOf(managerID));
		hm.put(MsgFields.tradeDate, DateUtil.convertD2YYYYMMDD(tradeDate));
		hm.put(MsgFields.settleDate, DateUtil.convertD2YYYYMMDD(settleDate));
		hm.put(MsgFields.secAcct, secacct);
		hm.put(MsgFields.bankAcct, String.valueOf(bankAcct));
		hm.put(MsgFields.shares, String.valueOf(amount));
		hm.put(MsgFields.principal, String.valueOf(amount));
		hm.put(MsgFields.accrued, String.valueOf(intatmat));
		hm.put(MsgFields.fxCur, String.valueOf(tradeCur));
		hm.put(MsgFields.tradeFX, String.valueOf(fxRate));
		hm.put(MsgFields.tradeCash, String.valueOf(tradeCash));
		hm.put(MsgFields.settleFxCur, String.valueOf(setCurCode));
		hm.put(MsgFields.settleFxRate, String.valueOf(setfxRate));
		hm.put(MsgFields.settleCash, String.valueOf(setCash));
		hm.put(MsgFields.feeMsg, feeMsg);
		return hm;
	}
	
	public String collatMsgSub(String secacct,String secid,String units){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.CollatSub_Header);
		msg.append("(");
		msg.append(secAcct(secacct));
		msg.append(",");
		msg.append(secID(secid));
		msg.append(",");
		msg.append(units);
		msg.append(")");
		return msg.toString();
	}
	
	public String manualVoucher(String operation,String vouchernum,String portCode,String ledgerDate, String ledgerMsg,String desc){
		//OPERATION,VOUCHERNUM,PORTFOLIO(),LedgerDate,JournalSourceCode,LEDGERENTRY(),Description
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.JournalEntry_Header);
		msg.append("(");
		msg.append(operation);
		msg.append(",");
		msg.append(vouchernum);
		msg.append(",");
		msg.append(portfolio(String.valueOf(portCode)));
		msg.append(",");
		msg.append(ledgerDate);
		msg.append(",");
		msg.append(JournalTypes.MANNUAL);
		msg.append(",");
		msg.append(ledgerMsg);
		msg.append(",");
		msg.append(desc);
		msg.append(")");
		return msg.toString();
	}
	
	public String ledgerSubManual(String glacct,String drFunc, String crFunc, String curCode, String drLocal, String crLocal, String FXRate){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.LedgerEntrySub_Header);
		msg.append("(");
		msg.append(glacct);
		msg.append(",");
		msg.append(drFunc);
		msg.append(",");
		msg.append(crFunc);
		msg.append(",");
		msg.append(cur(curCode));
		msg.append(",");
		msg.append(drLocal);
		msg.append(",");
		msg.append(crLocal);
		msg.append(",");
		msg.append(FXRate);
		msg.append(")");
		return msg.toString();
	}
	
	//,ActionType,AcctNum,AcctName,COAHDR(),Category,CloseAcctNum
	public String coaMsg(String operation, String coanum, String coaname, String coahdr, String category, String closeacctnum){
		StringBuilder msg=new StringBuilder();
		msg.append(MsgTemplate.Coa_Header);
		msg.append("(");
		msg.append(operation);
		msg.append(",");
		msg.append(coanum);
		msg.append(",");
		msg.append(coaname);
		msg.append(",");
		msg.append("COAHDR(");
		msg.append(coahdr);
		msg.append("),");
		msg.append(category);
		msg.append(",");
		msg.append(closeacctnum);
		msg.append(")");
		return msg.toString();
	}
}
