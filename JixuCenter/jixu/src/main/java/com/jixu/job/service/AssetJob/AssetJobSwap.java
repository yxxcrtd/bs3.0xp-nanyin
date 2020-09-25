package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.VoucherDetails;

/**
 * @ClassName AssetJobSwap
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description [利率互换]
 */

@Service
public class AssetJobSwap extends AssetJobBasic {

	@Autowired
	private AssetJobService assetJobForwardCurrency;


	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap, Date ledgerDate)
			throws JNLException {
		/*
		 * GLCOOABase generalCooa = new GLCOOABase(); generalCooa =
		 * exeIncomeSingleGeneralJob(generalCooa, portfolio, stcostWrap, ledgerDate);
		 * return generalCooa;
		 */
		return assetJobForwardCurrency.exeGeneralJob(portfolio, stcostWrap, ledgerDate);
	}

	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException {
		GLCOOABase incomeCooa = null;

		PortfolioHdr portfolio = sourceData.getPortfolio();
		TranClo clo = sourceData.getClo();
		IssueInfo sec=sourceData.getSecurity();
		double interestLocal=clo.getTodayInterest();
		
		List<Tran> incomeUpdateList=sourceData.getIncomeUpdateTrans();
		for(Tran tran:incomeUpdateList){
			interestLocal=RoundUtil.roundMoney2Dec(interestLocal-tran.getIntps());
		}
		
		double interestFunc=RoundUtil.roundMoney2Dec(clo.getIncFxRate()*interestLocal);

		if (interestLocal != 0 && interestFunc != 0) {
			incomeCooa = new GLCOOABase();
			if (clo.getLsindicator() == LongShortType.longTerm) {
				jobCom.saveGL(
						incomeCooa,
						DBCR.DEBIT,
						interestFunc,
						interestLocal,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(),
								sec.getCoaaGroup(), GLACCT.ACCRUED_INC));
	
				jobCom.saveGL(
						incomeCooa,
						DBCR.CREDIT,
						interestFunc,
						interestLocal,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(),
								sec.getCoaaGroup(), GLACCT.INC_ACCRUAL));
			}else{
				jobCom.saveGL(
						incomeCooa,
						DBCR.DEBIT,
						interestFunc,
						interestLocal,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(),
								sec.getCoaaGroup(), GLACCT.ACCRUED_INT_EXP_SHORT));
	
				jobCom.saveGL(
						incomeCooa,
						DBCR.CREDIT,
						interestFunc,
						interestLocal,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(),
								sec.getCoaaGroup(), GLACCT.ACCRUED_INT_EXP_PAY_SHORT));
			}
			jobCom.genVoucherDetails(incomeCooa, portfolio, sourceData,
					clo.getPrinFxRate());
		}

		return incomeCooa;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo,
			List<Tran> periodTrans, List<Tran> settleTranList) {
		clo=setCloInterest(portfolio, sec, clo, lastClo, periodTrans, settleTranList);
		if(clo.getLsindicator()==LongShortType.longTerm) 
			if(clo.getAmount()!=0) {
				clo.setgMkValue(clo.getPrice());
			}else {
				clo.setgMkValue(0);
			}
		else
			clo.setgMkValue(0);
		
		return clo;
	}
	
	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		ResultContainer resultcontainer = new ResultContainer();
		// 查询前一天的clo表，有哪些需要结转的
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date lastGlCloseDate = portfolio.getLastGLClose();
		List<TranClo> cloList = tranCloDao.loadTranCloWithAssetType(portnum, lastGlCloseDate, AssetTypes.SWAP_INTEREST);
		for (TranClo clo : cloList) {
			ResultContainer container=new ResultContainer();
			IssueInfo sec = issueInfoDao.loadIssueByCusip(clo.getCusip());
			
			if(ledgerDate.before(sec.getExpireDate())){
				// 如果当天是非工作日,不结转
				String cashflowID = sec.getCashFlowCfgID();
				if (clo.getLsindicator() == LongShortType.ShortTerm)
					cashflowID = sec.getCashFlowCfgID2();
				CashFlowCfg cashcfg = cashFlowCfgDao.loadCashFlowCfg(cashflowID);
				
				// 1. 判断是否结息
				boolean setFlag = incomeCal.checkGenSetInterestVoucher(cashcfg, lastGlCloseDate, ledgerDate);
				if(!setFlag)
					continue;
				
				double fxRate = commfuncService.loadFxrate(portnum, portfolio.getCurID(), sec.getIncomeCur(), ledgerDate);
				
				VoucherDetails vd=new VoucherDetails();
				vd.setAmount(clo.getAmount());
				vd.setChartNum(portfolio.getCoaahdr());
				vd.setCurFxRate(fxRate);
				
				double funcValue=RoundUtil.roundMoney2Dec(clo.getAccrued()*fxRate);
				String drAcct="";
				String crAcct="";
				int trantype=0;
				if(clo.getLsindicator()==LongShortType.longTerm) {
					drAcct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.INC_REC);
					crAcct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
					trantype=TransTypes.SWAP_AutoRec_Interest;
				}else {
					drAcct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INT_EXP_PAY_SHORT);
					crAcct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.INC_PAID);
					trantype=TransTypes.SWAP_AutoPay_Interest;
				}
				
				GLCOOABase manualCooa=new GLCOOABase();
				
				jobCom.saveGL(
						manualCooa,
						DBCR.DEBIT,
						funcValue,
						clo.getAccrued(),
						drAcct);
				jobCom.saveGL(
						manualCooa,
						DBCR.CREDIT,
						funcValue,
						clo.getAccrued(),
						crAcct);
				jobCom.genVoucherDetails(manualCooa, clo, 0, fxRate);
				container=manualService.importManualVoucher(portnum, ledgerDate, sec, trantype, manualCooa);
				
				if (!container.isCorrectFlag()) {
					return container;
				}
			}
		}
		return resultcontainer;
	}

	/**
	 * 原方法先临时注销
	 * @param userID
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 * @throws JNLException
	 */
	//@Override
	public ResultContainer autoJob1(int userID, String portnum, Date ledgerDate) throws JNLException {
		ResultContainer resultcontainer = new ResultContainer();
		// 查询前一天的clo表，有哪些需要结转的
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date lastGlCloseDate = portfolio.getLastGLClose();
		List<TranClo> cloList = tranCloDao.loadTranCloWithAssetType(portnum, lastGlCloseDate, AssetTypes.SWAP_INTEREST);

		for (TranClo clo : cloList) {
			IssueInfo sec = issueInfoDao.loadIssueByCusip(clo.getCusip());
			CashFlowCfg cfc = cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());

			if (!ledgerDate.before(clo.getAltDate()) && clo.getgCost() != 0 && cfc.isAutoInterestFlag()) {
				// 自动到期
				TranBasic tb = tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
				Date tradeDate = clo.getAltDate();
				// 如果目前的做账日在截至结息日之前
				String secID = sec.getSecurityid();
				// String managerID = String.valueOf(clo.getManagerID());
				// lot作为寻找上一笔交易的记录
				BkCashAcct cashacct = null;
				String fxcurrency = "";

				Date setDate = incomeCal.getCpnMaturitySetDate(cfc, tradeDate);

				
				  if (clo.getLsindicator() == LongShortType.longTerm) { 
					  fxcurrency =tb.getFxcurrency(); 
					  cashacct = tb.getBkCashacctNo(); 
				  } else { 
					  fxcurrency =tb.getFxcurrency2(); 
					  cashacct = tb.getBkCashacctNo2(); }
				 
				double principal = clo.getgCost();
				double accrued=clo.getAccrued();
				int bankAcct = cashacct.getId(); // 结算银行
				String setCurrency = cashacct.getFxcurrency(); // 结算货币
				// 结算汇率是结算货币 vs 交易证券货币的汇率
				double fxRate = commfuncService.loadFxrate(portnum, portfolio.getCurID(), fxcurrency, tradeDate);
				double setFxRate = commfuncService.loadFxrate(portnum, fxcurrency, setCurrency, tradeDate);
				double setCash = RoundUtil.roundMoney2Dec(principal * setFxRate);

				HashMap<String, Object> msgHM = msgPackage.swapInterestMaturity("ADD", "", secID, portnum, tradeDate,
						setDate, bankAcct, principal,accrued, fxcurrency, fxRate, setCurrency, setFxRate, setCash,
						clo.getLotnum());
				MsgContainer msgContainer = new MsgContainer();
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setUserID(userID);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				ResultContainer container = tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);

				if (!container.isCorrectFlag()) {
					return container;
				}
			}
			// 自动结息
			String cashflowID = sec.getCashFlowCfgID();
			if (clo.getLsindicator() == LongShortType.ShortTerm)
				cashflowID = sec.getCashFlowCfgID2();
			CashFlowCfg cashcfg = cashFlowCfgDao.loadCashFlowCfg(cashflowID);

			if(ledgerDate.before(sec.getExpireDate())){
				// 如果当天是非工作日,不结转
				if (!cashcfg.isAutoInterestFlag())
					continue;

				// 1. 判断是否结息
				boolean setFlag = incomeCal.checkGenSetInterestVoucher(cashcfg, lastGlCloseDate, ledgerDate);

				if (setFlag) {
					// 获取那天现金结转
					Date cashSetDate = incomeCal.getCpnSetDate(cashcfg, ledgerDate);
					// post的时候根据现金账户区分的lot
					TranBasic tranbasic = tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
					double fxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getIncomeCur(),
							ledgerDate);

					String desc = ReturnResult.getDicHM().get(ReturnResult.incomeSet_chinese) + "-" + sec.getSecurityid();
					String dbcr = String.valueOf(IncomeExpenseType.income);
					double funccash = RoundUtil.roundMoney2Dec(clo.getAccrued() * fxRate); // to-do pending 汇率取什么？
					String glacct = "";
					int cashAcctID = 0;

					if (clo.getLsindicator() == LongShortType.longTerm) {
						cashAcctID = tranbasic.getBkCashacctNo().getId();
						glacct = jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
						desc = desc + "-收入";
					} else {
						cashAcctID = tranbasic.getBkCashacctNo2().getId();
						dbcr = String.valueOf(IncomeExpenseType.expense);
						glacct = jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(),
								GLACCT.ACCRUED_INT_EXP_PAY_SHORT);
						desc = desc + "-支出";
					}

					HashMap<String, Object> msgHM = msgPackage.cashOff("ADD", "", String.valueOf(portnum), ledgerDate,
							cashSetDate, String.valueOf(cashAcctID), String.valueOf(funccash),
							String.valueOf(clo.getAccrued()), dbcr, glacct, desc, sec.getSecurityid());

					MsgContainer msgContainer = new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
					ResultContainer container = tradeService.operateTran(msgContainer);
					commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);

					if (!container.isCorrectFlag()) {
						return container;
					}
				}
			}else if(ledgerDate.compareTo(sec.getExpireDate())==0) {
				// 获取那天现金结转
				Date cashSetDate = incomeCal.getCpnSetDate(cashcfg, ledgerDate);
				// post的时候根据现金账户区分的lot
				TranBasic tranbasic = tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
				double fxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getIncomeCur(),
						ledgerDate);

				String desc = ReturnResult.getDicHM().get(ReturnResult.incomeSet_chinese) + "-" + sec.getSecurityid();
				String dbcr = String.valueOf(IncomeExpenseType.income);
				double funccash = RoundUtil.roundMoney2Dec(clo.getAccrued() * fxRate); // to-do pending 汇率取什么？
				String glacct = "";
				int cashAcctID = 0;

				if (clo.getLsindicator() == LongShortType.longTerm) {
					cashAcctID = tranbasic.getBkCashacctNo().getId();
					glacct = jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
					desc = desc + "-收入";
				} else {
					cashAcctID = tranbasic.getBkCashacctNo2().getId();
					dbcr = String.valueOf(IncomeExpenseType.expense);
					glacct = jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(),
							GLACCT.ACCRUED_INT_EXP_PAY_SHORT);
					desc = desc + "-支出";
				}

				HashMap<String, Object> msgHM = msgPackage.cashOff("ADD", "", String.valueOf(portnum), ledgerDate,
						cashSetDate, String.valueOf(cashAcctID), String.valueOf(funccash),
						String.valueOf(clo.getAccrued()), dbcr, glacct, desc, sec.getId());

				MsgContainer msgContainer = new MsgContainer();
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setUserID(userID);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				ResultContainer container = tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);

				if (!container.isCorrectFlag()) {
					return container;
				}
			}
			
		}

		return resultcontainer;
	}
}
