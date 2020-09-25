package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobCommonStock
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description []
 */

@Service
public class AssetJobTermRepo extends AssetJobBasic {

	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio,
			STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException {
		GLCOOABase generalCooa = new GLCOOABase();

		String chartnum = portfolio.getCoaahdr();

		double l_diff = 0.0, f_diff = 0.0; // 估值变量。l_diff为local。f_diff为functional.
		double l_priordayValuation = 0.0; // 前一天原币估增
		double l_asofdayValuation = 0.0; // 当天原币估增
		double f_priordayValuation = 0.0; // 前一天本币估增
		double f_asofdayValuation = 0.0; // 当天本币估增
		// double l_p_valuation = 0.0; // 因价格引起的原币(估值=价格损益+汇率损益)
		// double f_p_valuation = 0.0; // 因价格引起的本币(估值=价格损益+汇率损益)

		TranClo priorlotCost = null;
		TranClo asofdayCost = null;
		// VALUATION_LOSS, CARRY_VAL_ADJ, VALUATION_GAIN,
		// 外汇引起的(FX_VALUATION_GAIN,FX_VALUATION_LOSS),FX_RESERVE_CAP

		priorlotCost = stcostWrap.getPriordayCost().get(0);
		asofdayCost = stcostWrap.getAsofdayCost().get(0);
		int groupnum = stcostWrap.getGlgroupnum();

		l_priordayValuation = RoundUtil.roundMoney2Dec(priorlotCost.getAmount() - priorlotCost.getgCost());
		f_priordayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(priorlotCost.getAmount() * priorlotCost.getPrinFxRate())
				- priorlotCost.getgCostFunc());

		l_asofdayValuation = RoundUtil.roundMoney2Dec(asofdayCost.getAmount() - asofdayCost.getgCost());
		f_asofdayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(asofdayCost.getAmount()* asofdayCost.getPrinFxRate())
				- asofdayCost.getgCostFunc());
		/****** start 计算因价格引起的估值 *****/
		/*
		 * l_p_valuation = RoundUtil.roundMoney2Dec(asofdayCost.getAmount()
		 * costWrap.getAsofdayPrice()) -
		 * RoundUtil.roundMoney2Dec(asofdayCost.getAmount()
		 * costWrap.getPriordayPrice());
		 * 
		 * f_p_valuation =
		 * RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(asofdayCost
		 * .getAmount() costWrap.getAsofdayPrice())*costWrap.getPriordayRate())
		 * -
		 * RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(asofdayCost.getAmount
		 * () costWrap.getPriordayPrice())*costWrap.getPriordayRate());
		 */
		/****** end 计算因价格引起的估值 *****/

		l_diff = l_asofdayValuation - l_priordayValuation;

		f_diff = f_asofdayValuation - f_priordayValuation;

		// l_diff - l_p_valuation; //外汇损益=估值-价格损益
		// f_diff - f_p_valuation; //外汇损益=估值-价格损益

		if (asofdayCost.getLsindicator() == LongShortType.longTerm) {
			if (f_diff > 0) { // 赚了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.CARRY_VAL_ADJ));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FX_VALUATION_GAIN));

			} else if (f_diff < 0) { // 亏了或平了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FX_VALUATION_LOSS));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.CARRY_VAL_ADJ));
			}
		} else {
			if (f_diff < 0) { // 赚了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.BOOK_VALUE_ADJ_HEDGE));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FOREX_UNREAL_GL_SHORT));

			} else if (f_diff > 0) { // 亏了。
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.FOREX_UNREAL_GL_SHORT));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(f_diff), RoundUtil
								.roundMoney2Dec(Math.abs(l_diff)), jobCom
								.loadGLAcctnum(chartnum, groupnum,
										GLACCT.BOOK_VALUE_ADJ_HEDGE));
			}
		}
		if (f_diff != 0) {
			jobCom.genVoucherDetails(generalCooa, stcostWrap.getTranClo(),
					asofdayCost.getPrice(), asofdayCost.getPrinFxRate());
		}
		
		//增加利息的估增
		generalCooa=exeIncomeSingleGeneralJob(generalCooa, portfolio, stcostWrap, ledgerDate);

		return generalCooa;
	}

	// @Override
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
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec,
			TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		clo.setgMkValue(clo.getAmount());
		return setCloInterest(portfolio, sec, clo, lastClo, periodTrans, settleTranList);
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		// 查询前一天的clo表，有哪些需要结转的
		ResultContainer resultcontainer=new ResultContainer();
				
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date tradeDate=ledgerDate;
		List<TranClo> cloList=tranCloDao.loadTranCloWithAssetType(portnum, portfolio.getLastGLClose(), AssetTypes.TERM_REPO);

		for (TranClo clo : cloList) {
			String cusip=clo.getCusip();
			IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
			CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
			
			if(ledgerDate.before(clo.getAltDate())){
				if(!cfc.isAutoInterestFlag())
					continue;
				//1. 判断是否结息
				boolean setFlag=incomeCal.checkGenSetInterestVoucher(cfc, portfolio.getLastGLClose(), ledgerDate);
				
				if(setFlag){
					//获取那天现金结转
					Date cashSetDate=incomeCal.getCpnSetDate(cfc, ledgerDate);
					//post的时候根据现金账户区分的lot
					TranBasic tranbasic=tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
					double fxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getIncomeCur(), ledgerDate);
					
					String desc=ReturnResult.getDicHM().get(ReturnResult.incomeSet_chinese) + "-" + AssetTypes.getPortAsset(false).get(sec.getIssueType()) + "-"+sec.getSecurityid();
					String dbcr=String.valueOf(IncomeExpenseType.income);
					double funccash=RoundUtil.roundMoney2Dec(clo.getAccrued()*fxRate);
					String glacct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
					HashMap<String, Object> msgHM=msgPackage.cashOff("ADD","",String.valueOf(portnum), ledgerDate, 
							cashSetDate, String.valueOf(tranbasic.getBkCashacctNo().getId()), String.valueOf(funccash), 
							String.valueOf(clo.getAccrued()), dbcr, glacct, desc, sec.getSecurityid()); 
				
					MsgContainer msgContainer=new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
					ResultContainer container=tradeService.operateTran(msgContainer);
					commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
					
					if(!container.isCorrectFlag()){
						return container;
					}
				}
			}else{
				if(!cfc.isAutoSetFlag() && !cfc.isAutoInterestFlag())
					continue;
				
				double amount=0;
				if(cfc.isAutoSetFlag())
					amount=clo.getAmount();
				
				if(amount==0) {
					//如果当天数量已经为0，说明已经卖出，已完成了自动到期交易
					continue;
				}

				Date setDate=incomeCal.getCpnMaturitySetDate(cfc, tradeDate);
				double accrued=0;
				if(cfc.isAutoInterestFlag()){
					accrued=clo.getAccrued();
					if(setDate.after(ledgerDate) && cfc.getEndNDaysBase()==TradeSettleLedger.SettleDate){
						TranClo endClo=new TranClo();
						BeanUtils.copyProperties(clo, endClo);
						//最后一天计算利息是结算日的前一个自然日
						endClo.setCloseDate(DateUtil.getLastDay(setDate));
						//把结算日前一天的clo传入计算当天应记利息。因为投资组合的关帐日在上一天，所以会计算出今天到结算日之间总的利息
						double todayInterest=incomeCal.getInterestForAll(endClo);
						accrued=RoundUtil.roundMoney2Dec(accrued+todayInterest);
					}
				}
				
				if(amount==0 && accrued==0)
					continue;
				
				// 查找相同cusip,lot的交易 trannum=1的那笔记录
				TranBasic tb=tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
				//如果目前的做账日在截至结息日之前
				String secID = sec.getSecurityid();
				//String managerID = String.valueOf(clo.getManagerID());
				String secacct = clo.getSecacct();
				int bankAcct = tb.getBkCashacctNo().getId();
				//lot作为寻找上一笔交易的记录
				double fxRate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tb.getBkCashacctNo().getFxcurrency(), tradeDate);
				double cash = RoundUtil.roundMoney2Dec(amount+ accrued);
				String setCurCode = tb.getSettleCurrency();
				double setfxRate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tb.getSettleCurrency(),tradeDate);
				double setCash = RoundUtil.roundMoney2Dec(cash * setfxRate);
					
				String header=MsgTemplate.TARepoLend_Maturity_Header;
				if(amount<0){
					header=MsgTemplate.TARepoBorrow_Maturity_Header;
				}
				
				HashMap<String, Object> msgHM=msgPackage.repoLendMaturity(header, "ADD", "", secID, portnum, tradeDate, 
						setDate, secacct, bankAcct, amount,  accrued, fxRate, setCurCode, setfxRate, 
						setCash, null, String.valueOf(clo.getLotnum()));
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setUserID(userID);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);				
				ResultContainer container=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
				
				if(!container.isCorrectFlag()){
					return container;
				}
			}
		}
			
		return resultcontainer;
	}
}
