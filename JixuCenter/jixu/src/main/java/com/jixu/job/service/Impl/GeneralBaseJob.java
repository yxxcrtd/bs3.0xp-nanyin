package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.LANGUAGE;
import com.jixu.common.types.SecCashGeneralType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.BankRefAcctNumObj;
import com.jixu.journal.obj.CashBalanceWrapObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.GeneralCooa;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.journal.obj.SecCashWrapObj;
import com.jixu.journal.service.GeneralJNLService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashClo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

public abstract class GeneralBaseJob extends JNLJob {
	
	@Resource(name="genjnlService")
	protected GeneralJNLService generalservice;

	/********证券清算款估值**********/
	protected void secCashVJNL(JobParam jobParam, List<GLCOOABase> genJnlList, PortfolioHdr portfolio) throws JNLException{
		// FX_VALUATION_LOSS, FX_VALUATION_GAIN, FX_RESERVE_CAP(在中国这个科目会转为证券清算款)
		
		GeneralCooa generalCooa = null;

		List<SecCashWrapObj> seccashWrapList = generalservice.loadSecCashJNLRecord(portfolio,jobParam.getDate());
		String currcode = "";
		int groupnum = 0;
		String chartnum = "";
		double asofdayFxrate = 0.0, priordayFxrate = 0.0;
		
		if (seccashWrapList != null) {
			PortfolioHdr porthdr=commfuncService.loadPortfolio(jobParam.getPortfolio());
			chartnum=porthdr.getCoaahdr();
			
			Voucher voucher=null;
			
			for (SecCashWrapObj secashwrapObj : seccashWrapList) {
				//secashwrapObj.get
				currcode = secashwrapObj.getCurrcode();
				groupnum = secashwrapObj.getGroupnum();
				asofdayFxrate = commfuncService.loadFxrate(porthdr.getId(),porthdr.getCurID(), currcode, jobParam.getDate());
				priordayFxrate = commfuncService.loadFxrate(porthdr.getId(),porthdr.getCurID(), currcode, DateUtil.getLastDay(jobParam.getDate()));

				generalCooa = new GeneralCooa();
				String desc=ReturnResult.getDicHM().get(ReturnResult.secCash_chinese) + "/" 
						+ SecurityCoreFactory.getAllTranTypeHM().get(secashwrapObj.getTranType()) 
						+"-" + secashwrapObj.getCurrShortCode()+ "-"
						+ ReturnResult.getDicHM().get(ReturnResult.fx_gainloss_chinese);
				
				voucher=jobCom.genVoucher(jobParam.getDate(), porthdr, JournalTypes.GENERAL, secashwrapObj.getTanum(), desc);
				
				if (LANGUAGE.LANG == LANGUAGE.ENGLISH) {
					generateENGJNL(genJnlList, secashwrapObj.getCashbalanceList(),chartnum, groupnum,asofdayFxrate, priordayFxrate);
				} else if (LANGUAGE.LANG == LANGUAGE.CHINESE) {
					double gainAmount = calcuateValutionCHS(secashwrapObj.getCashbalanceList(), asofdayFxrate, priordayFxrate);
					
					SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(secashwrapObj.getTranType());
					
					//转到不同交易类型的class去分别处理
					TranJobService jobService=secBasic.createJobService();
					if(jobService!=null){
						jobService.exeGeneralJob(generalCooa, secashwrapObj.getCashbalanceList(), gainAmount, chartnum, groupnum, secashwrapObj.getTanum());

						if(JixuUserUtil.isJoyinTablesFlag()){
							String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.GENERAL+","+secashwrapObj.getIssueType());
							generalCooa.setTableID(tableID);
							List<VoucherDetails> voucherDetailsList=generalCooa.getVoucherDetails();
							for (VoucherDetails voucherDetails : voucherDetailsList) {
								//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
								if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
									continue;
								}else{
									voucherDetails.setBusiID(secashwrapObj.getSecid());
									voucherDetails.setBusiDetailType(secashwrapObj.getIssueType());
								}
							}
//							generalCooa.setBusiDetailType(secashwrapObj.getIssueType());
//							generalCooa.setBusiId(secashwrapObj.getSecid());
						}
						
						generalCooa.setVoucher(voucher);
						jobCom.genVoucherDetails(generalCooa, porthdr, secashwrapObj, asofdayFxrate);
						genJnlList.add(generalCooa);
					}
				}
			}
		}
	}

	
	/*
	 * 平均汇率
	 * double asofdayFxrate = 0.0, priordayFxrate = 0.0;
	int currcode = 0; //币种
	double p_val = 0.0, asofday_val = 0.0;
	double diff = 0.0;
	
	// 加载银行存款记录
	List<CashBalanceWrapObj> cashbalanceWrapList = getGeneralservice().loadCashBalanceGenJNLRecord(jobParam.getDate());
	BankRefAcctNumObj bckGLObj = null;
	if (cashbalanceWrapList != null) {
		PortfolioHdr porthdr=commFuncDAO.loadPorfolioInfo(jobParam.getPortfolio());
		GenerateVouchers genVouchers=new GenerateVouchers();
		Voucher voucher=null;
		
		for (CashBalanceWrapObj cashBalancewrap : cashbalanceWrapList) {
			currcode = cashBalancewrap.getBkCashAcct().getFxcurrency().getId();
			asofdayFxrate = commfuncService.loadFxrate(porthdr.getFxcurrency().getId(), currcode, jobParam.getDate());
			priordayFxrate = commfuncService.loadFxrate(porthdr.getFxcurrency().getId(), currcode, DateUtil.getPriorDay(jobParam.getDate()));
			
			p_val = RoundUtil.roundMoney(cashBalancewrap
					.getPriorDayBalance().getBalance()
					* cashBalancewrap.getPriorDayBalance().getAvgrate(), 2)
					- RoundUtil.roundMoney(cashBalancewrap.getPriorDayBalance()
							.getBalance()
							* priordayFxrate, 2);
			asofday_val = RoundUtil.roundMoney(cashBalancewrap
					.getAsofdayBalance().getBalance()
					* cashBalancewrap.getAsofdayBalance().getAvgrate(), 2)
					- RoundUtil.roundMoney(cashBalancewrap.getAsofdayBalance()
							.getBalance()
							* asofdayFxrate, 2);*/
	
	
	/********银行存款估值**********/
	/**金额： 当日的银行现金余额本币（CFG-bank balance表BALANCE_FUNCTIONAL字段）-[前一日的银行现金余额本币
	(CFG-bank balance表BALANCE_FUNCTIONAL字段）+当日所有的收入原币*汇率（Info-Receipt& Disbursement表amount和FX rate字段)-
	当日所有的支出原币*汇率(Info-Receipt& Disbursement表amount和FX rate字段)]**/
	protected void bankBalanceVJNL(JobParam jobParam, List<GLCOOABase> genJnlList, PortfolioHdr portfolio, Date lastNavDate) throws JNLException {
		GeneralCooa generalCooa = null;

		double p_val = 0.0, asofday_val = 0.0, todayIn_val=0, todayOut_val=0;
		double diff = 0.0;
		// 加载银行存款记录
		List<CashBalanceWrapObj> cashbalanceWrapList = generalservice.loadCashBalanceGenJNLRecord(portfolio, jobParam.getDate(), lastNavDate);
		BankRefAcctNumObj bckGLObj = null;
		if (cashbalanceWrapList != null) {
			Voucher voucher=null;
			
			for (CashBalanceWrapObj cashBalancewrap : cashbalanceWrapList) {
				String currcode = cashBalancewrap.getBkCashAcct().getFxcurrency();
				double preFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), currcode, lastNavDate);
				double todayFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), currcode, jobParam.getDate());
				
				p_val = RoundUtil.roundMoney2Dec(cashBalancewrap.getPriorDayBalance().getBalance() * preFxRate);
				asofday_val = RoundUtil.roundMoney2Dec(cashBalancewrap.getAsofdayBalance().getBalance() * todayFxRate);
				todayIn_val=RoundUtil.roundMoney2Dec(cashBalancewrap.getInCash() * todayFxRate);
				todayOut_val=RoundUtil.roundMoney2Dec(cashBalancewrap.getOutCash() * todayFxRate);
				
				diff = RoundUtil.roundMoney2Dec(asofday_val - p_val - todayIn_val + todayOut_val);
				
				generalCooa = new GeneralCooa();
				
				String fxCode=commfuncService.loadFxCurrencyCode(cashBalancewrap.getBkCashAcct().getFxcurrency());
				String desc=ReturnResult.getDicHM().get(ReturnResult.bankCash_chinese)+"-"+
						fxCode+"-"+ReturnResult.getDicHM().get(ReturnResult.fx_gainloss_chinese);
				
				voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.GENERAL, "", desc);
				
				bckGLObj = loadBKRefAcct(cashBalancewrap.getBkCashAcct());
				
				if (diff < 0) { //Loss
					jobCom.saveGL(generalCooa, 
							DBCR.DEBIT,
							RoundUtil.roundMoney2Dec(Math.abs(diff)),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashFXUnRealNO());
					
					jobCom.saveGL(generalCooa,
							DBCR.CREDIT,
							RoundUtil.roundMoney2Dec(Math.abs(diff)),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashAcctNO());
					
				} else {
					jobCom.saveGL(generalCooa,
							DBCR.DEBIT,
							RoundUtil.roundMoney2Dec(diff),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashAcctNO());
					
					jobCom.saveGL(generalCooa,
							DBCR.CREDIT,
							RoundUtil.roundMoney2Dec(diff),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashFXUnRealNO());
				}

				generalCooa.setVoucher(voucher);
				jobCom.genVoucherDetails(generalCooa, portfolio, cashBalancewrap, todayFxRate);
				genJnlList.add(generalCooa);
			}
		}
	}
	
	
	/********bkCashClo银行存款估值**********/
	protected void bkBalanceVJNL(JobParam jobParam, List<GLCOOABase> genJnlList, PortfolioHdr portfolio, Date lastNavDate) throws JNLException {
		GeneralCooa generalCooa = null;

		double pre_val = 0.0, asofday_val = 0.0, todayBalanceAmt=0;
		double diff = 0.0;
		// 加载每日现金账户记录
		List<BkCashClo> bkList=bkCashCloDao.loadBkCashClo(portfolio.getId(), jobParam.getDate());
		BankRefAcctNumObj bckGLObj = null;
		if (bkList != null) {
			Voucher voucher=null;
			
			for (BkCashClo bcc : bkList) {
				BkCashAcct bcAcct=bkCashAcctDao.loadBankCash(bcc.getBkCashAcctID());
				//前一天的汇率
				double preFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),  bcAcct.getFxcurrency(), lastNavDate);
				//当天汇率
				double todayFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),  bcAcct.getFxcurrency(), jobParam.getDate());
				
				//截至到当天的本币余额=截至到当天的余额*当日汇率
				asofday_val = RoundUtil.roundMoney2Dec(bcc.getBalanceAmt() * todayFxRate);
				//截至到前一天的本币余额=截至到前一天的余额*前一天的汇率
				pre_val = RoundUtil.roundMoney2Dec((bcc.getBalanceAmt()-bcc.getTodayBalanceAmt()) * preFxRate);
				//当天本币发生额=(当天流入额—当天流出额)*当天汇率
				todayBalanceAmt=RoundUtil.roundMoney2Dec(bcc.getTodayBalanceAmt()*todayFxRate);
				//当日估增=截至到当天的本币余额-截至到前一天的本币余额-当天本币发生额
				diff = RoundUtil.roundMoney2Dec(asofday_val - pre_val - todayBalanceAmt);
				
				generalCooa = new GeneralCooa();
				//hibernate缓存
				String curName=commfuncService.loadFxCurrencyName(bcAcct.getFxcurrency());
				String desc=ReturnResult.getDicHM().get(ReturnResult.bankCash_chinese)+"-"+curName+"-"+
						ReturnResult.getDicHM().get(ReturnResult.fx_gainloss_chinese);
				voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.GENERAL, "", desc);
				
				bckGLObj = loadBKRefAcct(bcAcct);
				
				if (diff < 0) { //Loss
					jobCom.saveGL(generalCooa, 
							DBCR.DEBIT,
							RoundUtil.roundMoney2Dec(Math.abs(diff)),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashFXUnRealNO());
					
					jobCom.saveGL(generalCooa,
							DBCR.CREDIT,
							RoundUtil.roundMoney2Dec(Math.abs(diff)),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashAcctNO());
					
				} else {
					jobCom.saveGL(generalCooa,
							DBCR.DEBIT,
							RoundUtil.roundMoney2Dec(diff),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashAcctNO());
					
					jobCom.saveGL(generalCooa,
							DBCR.CREDIT,
							RoundUtil.roundMoney2Dec(diff),
							0.0,//银行存款估值没有原币估增。
							bckGLObj.getCashFXUnRealNO());
				}
				generalCooa.setVoucher(voucher);
				jobCom.genVoucherDetails(generalCooa, portfolio, bcAcct, todayFxRate);
				genJnlList.add(generalCooa);
			}
		}
	}
	
	
	private void generateENGJNL(List<GLCOOABase> genJnlList, List<SecCashObj> seccashList,
			String chartnum, int groupnum, double asofdayFxrate, 
			double priordayFxrate)  throws JNLException{
		
		GeneralCooa generalCooa = null;
		int glcolumn = 0;
		String dbcr1 = null;
		String dbcr2 = null;
		double[] gainAmounts;
		List<SecCashObj> newSecCashList = null;
		SecCashObj cashObj = null;
		
		String cusip = "-1";
		
		Collections.sort(seccashList, new SortByCusip());
		for (int i = 0; i < seccashList.size(); i++) {
			cashObj = seccashList.get(i);
			if (cusip.equals("-1")) {
				cusip = cashObj.getCusip();
				newSecCashList = new ArrayList<SecCashObj>();
				newSecCashList.add(cashObj);
				if (i<seccashList.size()-1) {
					continue;
				}
			} else if (newSecCashList != null && cusip == cashObj.getCusip()) {
				newSecCashList.add(cashObj);
				if (i<seccashList.size()-1) {
					continue;
				}
			}
			
			gainAmounts = calcuateValutionENG(newSecCashList, asofdayFxrate, priordayFxrate);
			cusip = cashObj.getCusip();
			if (newSecCashList != null){
				newSecCashList.clear();
				newSecCashList.add(cashObj);
			}
			generalCooa = new GeneralCooa();
			if (gainAmounts[0] * gainAmounts[1] < 0) { //说明今天和冲销符号相反
				jobCom.saveGL(generalCooa, 
						RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[1])),0,RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),0,
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
				
				if (gainAmounts[0] > 0) {
					glcolumn = GLACCT.FX_VALUATION_GAIN;
				} else {
					glcolumn = GLACCT.FX_VALUATION_LOSS;
				}
				
				jobCom.saveGL(generalCooa, 
						RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),0,RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[1])),0,
						jobCom.loadGLAcctnum(chartnum, groupnum, glcolumn));
			} else if (gainAmounts[0] * gainAmounts[1] > 0) { //说明今天和冲销符号一致
				if (gainAmounts[0] > 0) {
					jobCom.saveGL(generalCooa, 
							RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0]) + Math.abs(gainAmounts[1])),0,0,0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
					jobCom.saveGL(generalCooa, 
							0,0,RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_GAIN));
					jobCom.saveGL(generalCooa, 
							0,0,RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[1])),0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_LOSS));
				} else {
					jobCom.saveGL(generalCooa, 
							0,0,RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0]) + Math.abs(gainAmounts[1])),0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
					jobCom.saveGL(generalCooa, 
							RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),0,0,0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_GAIN));
					jobCom.saveGL(generalCooa, 
							RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[1])),0,0,0,
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_LOSS));
				}
				
			} else { //前一天没有估值
				if (gainAmounts[0] > 0) {
					glcolumn = GLACCT.FX_VALUATION_GAIN;
					dbcr1 = DBCR.DEBIT;
					dbcr2 = DBCR.CREDIT;
				} else {
					glcolumn = GLACCT.FX_VALUATION_LOSS;
					dbcr1 = DBCR.CREDIT;
					dbcr2 = DBCR.DEBIT;
				}
				
				jobCom.saveGL(generalCooa,
						dbcr1,
						RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),
						0.0,// 证券清算款估值没有原币估增。
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
				
				jobCom.saveGL(generalCooa,
						dbcr2,
						RoundUtil.roundMoney2Dec(Math.abs(gainAmounts[0])),
						0.0,// 证券清算款估值没有原币估增。
						jobCom.loadGLAcctnum(chartnum, groupnum, glcolumn));
			}
			
			genJnlList.add(generalCooa);
		}
	}
	
	private double calcuateValutionCHS(List<SecCashObj> seccashList, double asofdayFxrate, double priordayFxrate) {
		double valuation_C = 0.0, valuation_D = 0.0,gainAmount=0.0;
		double valuationCX_C = 0.0, valuationCX_D = 0.0;
		double valuationToday_C = 0.0, valuationToday_D = 0.0;
		double tempValue = 0.0;
		
		for (SecCashObj secashObj : seccashList) {
			if (secashObj.getType() == SecCashGeneralType.secCashGen) {
				// (RD(Cash*当天汇率)-RD(Cash*前一天汇率).
				tempValue = RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(secashObj.getCash()*asofdayFxrate) - RoundUtil.roundMoney2Dec(secashObj.getCash()*secashObj.getFxrate()))
				-RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(secashObj.getCash()*priordayFxrate) - RoundUtil.roundMoney2Dec(secashObj.getCash()*secashObj.getFxrate()));
				if (secashObj.getDbcr().equals(DBCR.CREDIT)) {// 售出
					valuation_C += tempValue;
				} else if (secashObj.getDbcr().equals(DBCR.DEBIT)) {// 购买
					valuation_D += tempValue;
				}
			} else if (secashObj.getType() == SecCashGeneralType.secCash_off_pre) {
				// RD(Cash*前一天汇率)-RD(Cash*交易时的汇率)。由于冲销前面的估值，所以借转为贷，贷转为借
				tempValue = RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(secashObj.getCash()*priordayFxrate) - RoundUtil.roundMoney2Dec(secashObj.getFuncCash()));
				if (secashObj.getDbcr().equals(DBCR.CREDIT)) {
					valuationCX_C += tempValue;
				} else {
					valuationCX_D += tempValue;
				}
			} else if (secashObj.getType() == SecCashGeneralType.secCashGen_todayTran) {
				// 产生凭证当天的交易,用当天汇率-交易汇率
				tempValue = RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(secashObj.getCash()*asofdayFxrate) - RoundUtil.roundMoney2Dec(secashObj.getCash()*secashObj.getFxrate()));
				if (secashObj.getDbcr().equals(DBCR.CREDIT)) {
					valuationToday_C += tempValue;
				} else {
					valuationToday_D += tempValue;
				}
			}
		}
		
		gainAmount = RoundUtil.roundMoney2Dec(valuation_D - valuation_C +valuationToday_D-valuationToday_C- valuationCX_D + valuationCX_C);
		
		return gainAmount;
		
	}
	
	/**返回前一日的估值和当天估值***/
	private double[] calcuateValutionENG(List<SecCashObj> seccashList, double asofdayFxrate, double priordayFxrate) {
		double valuation_C = 0.0, valuation_D = 0.0;
		double valuationCX_C = 0.0, valuationCX_D = 0.0;
		double tempValue = 0.0;
		double[] gainAmount = {0.0,0.0};
		
		for (SecCashObj secashObj : seccashList) {
			if (secashObj.getType() == 0) {
				// (RD(Cash*当天汇率)-RD(Cash*前一天汇率).
				tempValue = RoundUtil.roundMoney2Dec(secashObj.getCash()*asofdayFxrate - secashObj.getCash()*secashObj.getFxrate())
				-RoundUtil.roundMoney2Dec(secashObj.getCash()*priordayFxrate - secashObj.getCash()*secashObj.getFxrate());
				if (secashObj.getDbcr().equals(DBCR.CREDIT)) {// 售出
					valuation_D += tempValue;
				} else if (secashObj.getDbcr().equals(DBCR.DEBIT)) {// 购买
					valuation_C += tempValue;
				}
			} else if (secashObj.getType() == 1) {
					// RD(Cash*前一天汇率)-RD(Cash*交易时的汇率)。由于冲销前面的估值，所以借转为贷，贷转为借
				tempValue = RoundUtil.roundMoney2Dec(secashObj.getCash()*priordayFxrate - secashObj.getCash()*secashObj.getFxrate());
				if (secashObj.getDbcr().equals(DBCR.CREDIT)) {
					valuationCX_C += tempValue;
				} else {
					valuationCX_D += tempValue;
				}
			}
		}
		
		gainAmount[0] = RoundUtil.roundMoney2Dec(valuation_D - valuation_C);
		gainAmount[1] = RoundUtil.roundMoney2Dec(valuationCX_D - valuationCX_C);
		
		return gainAmount;
	}
	
	private BankRefAcctNumObj loadBKRefAcct(BkCashAcct cashAcct) {
		BankRefAcctNumObj acctnumObj = new BankRefAcctNumObj();
		if (cashAcct != null) {
			acctnumObj.setCashAcctNO(cashAcct.getCashglcode());
			acctnumObj.setCashFXRealAcctNO(cashAcct.getCashrealfxgnls());
			acctnumObj.setCashFXUnRealNO(cashAcct.getCashunrealfxgnls());
			acctnumObj.setCashFXUnRealNO_CX(cashAcct.getCashunrealfxoffset());
			acctnumObj.setInterestAcctNO(cashAcct.getIntrecvgl());
			acctnumObj.setInterestRecNO(cashAcct.getIntrcvpaygl());
			acctnumObj.setInterestFXrealNO(cashAcct.getIntrealfxgnls());
			acctnumObj.setInteFXUnrealNO(cashAcct.getIntunrealfxgnls());
			acctnumObj.setInteFXUnrealNO_CX(cashAcct.getIntunrealfxoffset());
			acctnumObj.setInteCalAcctNO(cashAcct.getInterestaccrued());
			acctnumObj.setInteCalAcctNO_CX(cashAcct.getInterestaccoffset());
		}
		return acctnumObj;
	}
}
