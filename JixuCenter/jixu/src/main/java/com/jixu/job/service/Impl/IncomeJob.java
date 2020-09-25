package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.BookNDaysType;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.dao.intf.BkInteRatioDao;
import com.jixu.job.obj.BankInterestType;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.AssetJobService;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.BankBalanceObj;
import com.jixu.journal.obj.BankBalanceWrapObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.IncomeCooa;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.journal.service.IncomeJNLService;
import com.jixu.secTran.SecurityAssetService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashClo;
import com.orm.obj.BkInteAcct;
import com.orm.obj.BkInteRatio;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("incomejnlJob")
public class IncomeJob extends JNLJob {
	
	//private static final Logger logger = LoggerFactory.getLogger(IncomeJob.class);

	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
	@Autowired
	private BkInteRatioDao bkInteRatioDao;
	
	@Resource(name = "incomejnlService")
	private IncomeJNLService incomejnlservice;

	@Resource(name = "incomeExpenseCal")
	private IncomeExpenseCal incomeCal;

	public IncomeJob() {
		jobType = JobsType.INCOME;
		journalType = JournalTypes.INCOME;
	}

	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container)
			throws JNLException {
		List<GLCOOABase> incomeJNLList = new ArrayList<GLCOOABase>();
		PortfolioHdr portfolio=getPortfolio(jobParam.getPortfolio());
		// 证券利息
		secInterestJNL(jobParam, incomeJNLList, portfolio);
		
		//IncomeWrapObj incomeWrapObj = incomejnlservice.loadIncomeData(jobParam.getPortfolio(), jobParam.getDate());
		// 银行利息
		//bankInterestJNL(jobParam, incomeWrapObj, incomeJNLList, portfolio);
		if(!JixuUserUtil.isJoyinTablesFlag())
			bankInterestJNL(jobParam,portfolio,incomeJNLList);
		return incomeJNLList;
	}

	private void secInterestJNL(JobParam jobParam, List<GLCOOABase> incomeJNLList, PortfolioHdr portfolio) throws JNLException {
		// 获取当日头寸不为0的证券
		List<SecIncomeWrapObj> secIncomeWrapList = incomejnlservice.loadSecIncomeData(jobParam.getPortfolio(), jobParam.getDate());
		Voucher voucher = null;
		for (SecIncomeWrapObj secIncomeWrpObj : secIncomeWrapList) {
			GLCOOABase incomeCooa = null;
			Date curDate = jobParam.getDate();

			secIncomeWrpObj.setPortfolio(portfolio);
			secIncomeWrpObj.setLedgerDate(curDate);

			SecurityAssetService assetService = secCoreFactory.createSecAssetTypeBean(secIncomeWrpObj.getSecurity().getIssueType());
			AssetJobService jobService = assetService.createJobService();
			if (jobService != null) {
				incomeCooa = jobService.exeIncomeJob(secIncomeWrpObj);
			}

			if (incomeCooa != null) {
				String clonum="";
				String secid="";
				if(secIncomeWrpObj.getClo()!=null){
					clonum=secIncomeWrpObj.getClo().getId();
					secid=secIncomeWrpObj.getSecurity().getSecurityid();
				}
				String dsec="利息收入--"+secid;
				voucher = jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.INCOME, String.valueOf(clonum), dsec);
				incomeCooa.setVoucher(voucher);
				
				if(JixuUserUtil.isJoyinTablesFlag()){
					IssueInfo sec=secIncomeWrpObj.getSecurity();
					//secIncomeWrpObj.getClo().getLsindicator()是利息的多空方向
					String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.INCOME+","+sec.getIssueType()+","+secIncomeWrpObj.getClo().getLsindicator());
					incomeCooa.setTableID(tableID);
					
					List<VoucherDetails> voucherDetailsList=incomeCooa.getVoucherDetails();
					for (VoucherDetails voucherDetails : voucherDetailsList) {
						//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
						if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
							continue;
						}else{
							voucherDetails.setBusiID(sec.getSecurityid());
							voucherDetails.setBusiDetailType(sec.getIssueType());
						}
					}
					
				}
				
				incomeJNLList.add(incomeCooa);
			}
		}
	}

	/******************* 利息凭证 ************//*
	private ResultContainer bankInterestJNL(JobParam jobParam,
			IncomeWrapObj incomeWrapObj, List<GLCOOABase> incomeJNLList,
			PortfolioHdr portfolio) {
		ResultContainer container = new ResultContainer();

		List<BankBalanceWrapObj> bkbalanceList = incomeWrapObj.getBankBalanceWrapList();

		try {
			GLCOOABase incomeCooa = null;
			// BankRefAcctNumObj bkrefAcctNumObj = null;
			Date tmpDate = jobParam.getDate();

			List<BankInterestType> bkIntList = loadBankInterestList(tmpDate, bkbalanceList, portfolio);
			String acctnum = GLACCT.UNKNOWN;
			BkCashAcct bkcashacct = null;
			double totalInterestOrig = 0;
			double priordayInterestOrig = 0;
			double totalInterestFxOrig = 0;
			double priordayInterestFxOrig = 0;

			for (BankInterestType bkIntType : bkIntList) {
				incomeCooa = new IncomeCooa();
				bkcashacct = bkIntType.getBkObj().getBkcashAcct();
				totalInterestOrig = bkIntType.getTotalInterestOrig();
				priordayInterestOrig = bkIntType.getPriordayInterestOrig();
				totalInterestFxOrig = bkIntType.getTotalInterestFxOrig();
				priordayInterestFxOrig = bkIntType.getPriordayInterestFxOrig();
				String bkdesc = bkcashacct.getBankdesc();

				acctnum = bkcashacct.getIntrcvpaygl();
				checkCashAcctValiable(acctnum, bkdesc, BankAccountType.Int_Rcpy);

				jobCom.saveGL(
						incomeCooa,
						DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(totalInterestOrig-priordayInterestOrig),
						RoundUtil.roundMoney2Dec(totalInterestFxOrig-priordayInterestFxOrig), acctnum);

				acctnum = bkcashacct.getIntrecvgl();
				checkCashAcctValiable(acctnum, bkdesc,
						BankAccountType.Int_InterestReceived);
				jobCom.saveGL(
						incomeCooa,
						DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(totalInterestOrig-priordayInterestOrig),
						RoundUtil.roundMoney2Dec(totalInterestFxOrig-priordayInterestFxOrig), acctnum);

				Voucher voucher = jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.INCOME, 0);
				incomeCooa.setVoucher(voucher);
				jobCom.genVoucherDetails(incomeCooa, portfolio, bkcashacct,
						bkIntType.getTotalBalance(), bkIntType.getTodayFxRate());
				incomeJNLList.add(incomeCooa);
			}
		} catch (Exception e) {
			logger.error("Error", e);
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage());
		}

		return container;
	}
	*/
	
	/******************* bkCashClo利息凭证 ************/
	private ResultContainer bankInterestJNL(JobParam jobParam,PortfolioHdr portfolio,List<GLCOOABase> incomeJNLList) throws JNLException{
		ResultContainer container = new ResultContainer();
		GLCOOABase incomeCooa = null;
		List<BkCashClo> bkList=bkCashCloDao.loadBkCashClo(portfolio.getId(), jobParam.getDate());
		String acctnum = GLACCT.UNKNOWN;
		BkCashAcct bkcashacct = null;
		double totalTodayInterest=0;
		double totalPriInterest=0;

		for (BkCashClo bcc : bkList) {
			incomeCooa = new IncomeCooa();
			BkCashAcct bcAcct=bkCashAcctDao.loadBankCash(bcc.getBkCashAcctID());
			if(bcAcct.getIntCshflwMasterRec()==0){
				//如果没有配置银行利息，则过滤
				continue;
			}
			double preFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),  bcAcct.getFxcurrency(), portfolio.getLastGLClose());
			bkcashacct = bkCashAcctDao.loadBankCash(bcc.getBkCashAcctID());
			String bkdesc = bkcashacct.getBankdesc();
			acctnum = bkcashacct.getIntrcvpaygl();
			checkCashAcctValiable(acctnum, bkdesc, BankAccountType.Int_Rcpy);
			//当天利息总额*当天汇率
			totalTodayInterest=RoundUtil.roundMoney2Dec(bcc.getInterestIncome()*bcc.getFxRate());
			//前一天利息总额*前一天汇率
			totalPriInterest=RoundUtil.roundMoney2Dec((bcc.getInterestIncome()-bcc.getTodayIncome())*preFxRate);
				jobCom.saveGL(
					incomeCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(totalTodayInterest-totalPriInterest),
					bcc.getTodayIncome(), acctnum);
				acctnum = bkcashacct.getIntrecvgl();
			checkCashAcctValiable(acctnum, bkdesc,
					BankAccountType.Int_InterestReceived);
			jobCom.saveGL(
					incomeCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(totalTodayInterest-totalPriInterest),
					bcc.getTodayIncome(), acctnum);
			
			String desc="现金利息: " + bkcashacct.getExternalacctnumber();
			Voucher voucher = jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.INCOME, "", desc);
			incomeCooa.setVoucher(voucher);
			jobCom.genVoucherDetails(incomeCooa, portfolio, bkcashacct, bcc.getBalanceAmt(),bcc.getFxRate());
			incomeJNLList.add(incomeCooa);
		}
		

		return container;
	}

	public BankInterestType loadBankInterest(Date ledgerDate,
			BankBalanceWrapObj bkbalanceWrap, PortfolioHdr portfolio) {
		Date startC = null;
		Date lastWorkDayC = null;
		Date endC = null;

		double tempBKBalance = 0.0;
		double totalInterestOrig = 0.0; // 当天为止的利息(本币全精度)。
		double priordayInterestOrig = 0.0; // 前一天的累计利息(本币ROUND后)
		double totalInterestFxOrig = 0.0; // 当天为止的利息(原币全精度)。
		double priordayInterestFxOrig = 0.0; // 前一天的累计利息(原币ROUND后)

		double ratio = 0.0, asofdayRate = 0.0, priodayfxrate = 0.0;

		List<BankBalanceObj> bkbalanceobjList = null;

		int frontback = portfolio.getBookNBDays();
		BankInterestType bkIntType = new BankInterestType();
		BkCashAcct bkcashacct = bkbalanceWrap.getBkcashAcct();

		bkbalanceobjList = bkbalanceWrap.getBankBalanceList();

		tempBKBalance = bkbalanceWrap.getLstcalInteBalance();

		if (bkbalanceobjList != null && bkbalanceobjList.size() > 0) {
			startC = bkbalanceWrap.getInteStartDate();
			endC = ledgerDate;
		} else {
			return null;
		}

		totalInterestOrig = 0.0;
		totalInterestFxOrig = 0.0;
		priordayInterestOrig = 0.0;
		priordayInterestFxOrig = 0.0;

		BkInteAcct bkInteAcct = bkInteAcctDao.loadBkInteAcct(bkcashacct.getIntCshflwMasterRec());
		asofdayRate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),bkbalanceWrap.getCurrcode(), ledgerDate);
		priodayfxrate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),bkbalanceWrap.getCurrcode(), portfolio.getLastGLClose());

		int j = 0;
		int daylag = startC.compareTo(endC);

		List<BkInteRatio> ratioList = bkInteRatioDao.loadBkInteRatio(bkInteAcct.getId());
		if (ratioList == null || ratioList.size() == 0)
			ratio = 0;

		if (frontback == BookNDaysType.back) {
			// 计算每个自然日的利息
			lastWorkDayC = DateUtil.getLastDay(endC);
			int lastWorkdayFlag = -1;
			while (daylag <= 0) {
				// 从上一个计息结束日到现在，计算每天的利息
				if (j < bkbalanceobjList.size()) {
					//如果当前日期没有余额  则使用前一天的余额
					if (bkbalanceobjList.get(j).getLedgerdate().compareTo(startC)==0) {
						tempBKBalance = bkbalanceobjList.get(j).getBkbalance();
						j++;
					}
				}

				for (int k = 0; k < ratioList.size(); k++) {
					BkInteRatio bnInteRatio = ratioList.get(k);
					if (!startC.before(bnInteRatio.getStartdate())) {
						if (ratioList.size() == 1) {
							ratio = bnInteRatio.getPrice();
						} else if (k == ratioList.size() - 1) {
							ratio = bnInteRatio.getPrice();
						} else if (startC.before(ratioList.get(k + 1).getStartdate())) {
							ratio = bnInteRatio.getPrice();
						}
						if (ratio > 0) {
							break;
						}
					}
				}

				double tmpInterest = incomeCal.incomeCal(bkInteAcct.getCashFlowCfg().getIntCode(), startC, startC, ratio,tempBKBalance);
				totalInterestFxOrig += tmpInterest;
				if (lastWorkdayFlag <= 0 && endC.compareTo(bkbalanceWrap.getInteStartDate()) > 0) {
					priordayInterestFxOrig += tmpInterest;
				}
				startC = DateUtil.getNextDay(startC);
				daylag = startC.compareTo(endC);
				lastWorkdayFlag = startC.compareTo(lastWorkDayC);
			}
		}

		totalInterestOrig = RoundUtil.roundMoney2Dec(totalInterestFxOrig * asofdayRate);
		totalInterestFxOrig = RoundUtil.roundMoney2Dec(totalInterestFxOrig);

		priordayInterestOrig = RoundUtil.roundMoney2Dec(priordayInterestFxOrig * priodayfxrate);
		priordayInterestFxOrig = RoundUtil.roundMoney2Dec(priordayInterestFxOrig);

		bkIntType.setTotalInterestOrig(totalInterestOrig);
		bkIntType.setTotalInterestFxOrig(totalInterestFxOrig);
		bkIntType.setPriordayInterestOrig(priordayInterestOrig);
		bkIntType.setPriordayInterestFxOrig(priordayInterestFxOrig);
		bkIntType.setBkObj(bkbalanceWrap);
		bkIntType.setTotalBalance(tempBKBalance);
		bkIntType.setTodayFxRate(asofdayRate);

		return bkIntType;
	}

	public List<BankInterestType> loadBankInterestList(Date ledgerDate,
			List<BankBalanceWrapObj> bkbalanceList, PortfolioHdr portfolio) {

		List<BankInterestType> bkIntTypeList = new ArrayList<BankInterestType>();
		// 公式.当天应收利息 = RD(当天为止的利息(全精度)) - 前一天的累计利息(ROUND后)
		for (BankBalanceWrapObj bkbalanceWrap : bkbalanceList) {
			BankInterestType interesttype = loadBankInterest(ledgerDate,bkbalanceWrap, portfolio);
			if (interesttype != null) {
				bkIntTypeList.add(interesttype);
			}
		}
		return bkIntTypeList;
	}
}
