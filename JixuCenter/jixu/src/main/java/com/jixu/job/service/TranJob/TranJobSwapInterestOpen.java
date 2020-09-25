package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.LongShortType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.SwapSetPrincipalType;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service
public class TranJobSwapInterestOpen extends TranJobBasic {

	@Resource(name = "tranJobStockPurchase")
	private TranJobService tranJob;

	@Resource(name = "tranJobCashMove")
	private TranJobService cashMoveJob;

	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio,
			String desc, Date ledgerDate) throws JNLException {
		IssueInfo sec = tranbasic.getCusip();
		if (sec.getSwapExchangeInd() == SwapSetPrincipalType.principalExchange)
			return cashMoveJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
		else
			return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		double totalFeeFunc=params.getTotalFeeFunc();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//新需求不需要交易日记账
		IssueInfo sec = tranBasic.getCusip();

		if (sec.getSwapExchangeInd() == SwapSetPrincipalType.FrontSwap
				|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
			if (tran.getLsindicator() == LongShortType.longTerm) {
				// 收入端 //获取总账组信息 // SetBOOK_VALUE 成本
				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(), DBCR.DEBIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));

				// Set PAY_SECUR_PURCH 证券清算款(买)
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.CREDIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),tranBasic.getFxcurrency());
			} else {
				// 支出端 空方成本
				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(),DBCR.CREDIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT));

				// Set PAY_SECUR_PURCH 证券清算款(卖)
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.DEBIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD),tranBasic.getFxcurrency());
			}
		}else {
			//表外账
			if (tran.getLsindicator() == LongShortType.longTerm) {
				jobCom.saveOffGL(pursaleCooa,
						sec.getId(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(tran.getAmount()*tran.getPrinFxrate()), 
						tran.getAmount(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
						tranBasic.getFxcurrency());
			}else {
				jobCom.saveOffGL(pursaleCooa,
						sec.getId(),
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(tran.getAmount()*tran.getPrinFxrate()), 
						tran.getAmount(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),
						tranBasic.getFxcurrency2());
			}
		}

		// 交易接手过来的利息
		double intpsFunc = RoundUtil.roundMoney2Dec(tran.getIntps() * tranBasic.getFxrate());

		if (tran.getIntps() != 0) {
			if (tran.getLsindicator() == LongShortType.longTerm) {
				// 收入端
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.CREDIT, intpsFunc, tran.getIntps(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),tranBasic.getFxcurrency());

				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(), DBCR.DEBIT, intpsFunc, tran.getIntps(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC),tranBasic.getFxcurrency());
			} else {
				// 支出端
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.DEBIT, intpsFunc, tran.getIntps(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),tranBasic.getFxcurrency());

				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(), DBCR.CREDIT, intpsFunc, tran.getIntps(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC),tranBasic.getFxcurrency());
			}
		}

		/*** 本币借贷不平调整到费用(强制调账到交易所费用) **/
		double diff = 0;
		if (tran.getLsindicator() == LongShortType.longTerm) {
			double funcCash = RoundUtil.roundMoney2Dec(tranBasic.getCash() * tranBasic.getFxrate());
			if (sec.getSwapExchangeInd() == SwapSetPrincipalType.FrontSwap
					|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
				diff = RoundUtil.roundMoney2Dec(funcCash - totalFeeFunc - tran.getgCostFunc() - intpsFunc);
			} else {
				diff = RoundUtil.roundMoney2Dec(funcCash - totalFeeFunc - intpsFunc);
			}
		} else {
			double funcCash = RoundUtil.roundMoney2Dec(tranBasic.getCash2() * tranBasic.getFxrate());
			if (sec.getSwapExchangeInd() == SwapSetPrincipalType.FrontSwap
					|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
				diff = RoundUtil.roundMoney2Dec(funcCash + totalFeeFunc - tran.getgCostFunc() - intpsFunc);
			} else {
				diff = RoundUtil.roundMoney2Dec(funcCash + totalFeeFunc - intpsFunc);
			}
		}

		// (强制调账到交易所费用)
		if (Math.abs(diff) > DBCR.ZERO) {
			jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(),DBCR.DEBIT, RoundUtil.roundMoney2Dec(diff), 0,
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE),tranBasic.getFxcurrency());
			jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.CREDIT, RoundUtil.roundMoney2Dec(diff), 0,
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),tranBasic.getFxcurrency());
		}
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
