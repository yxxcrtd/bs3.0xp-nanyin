package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ParChange;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobBondSale")
public class TranJobBondSale extends TranJobBasic{
	
	@Resource(name="tranJobStockSale")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(tranBasic.getCusip().getId());
		//	摊余成本计算
		if(tranBasic.getValuationType()==ValuationType.AMORTIZE){
			CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
			
			ParChange parchange=incomeCal.getPayParChange(sec.getContractSize(), cfc, tran.getTradeDate());
			double parValue=parchange.getTodayParValue();
			String priDay=DateUtil.getPriorDay(tran.getLedgerDate());
			TranClo oldclo=tranCloDao.loadTranClo(tran.getPortfolio(),DateUtil.convertStr2Date(priDay), tran.getCusip().getId(), tran.getLotnum());
			double amountPercent=tranBasic.getAmount()/oldclo.getAmount();//卖出比例
			double bookvalue=RoundUtil.roundMoney2Dec(parValue*oldclo.getAmount());//债券成本
			double bookvalueReduced=RoundUtil.roundMoney2Dec(bookvalue*amountPercent);//债券成本按比例减少
			
			double singleAmrValue=RoundUtil.roundMoney2Dec(oldclo.getAmrValue()/oldclo.getAmount());//单个债券的摊余成本
			double priceDiff=RoundUtil.roundMoney2Dec((parValue-singleAmrValue)*tran.getAmount());// 债券折溢价
			double priceDiffReduced=RoundUtil.roundMoney2Dec(priceDiff*amountPercent);//债券折溢价按比例减少
			
			double accrued=tran.getIntps();//债券应收利息
			double secash=RoundUtil.roundMoney2Dec(tran.getProceeds()+tran.getIntps()); //债券清算款
			
			double incomeDiff=0; //投资收益
			//if(priceDiff>0){
			incomeDiff=RoundUtil.roundMoney2Dec(secash+priceDiffReduced-bookvalueReduced-accrued);
			//}else{
			//	incomeDiff=RoundUtil.roundMoney2Dec(secash-priceDiffReduced-bookvalueReduced-accrued);
			//}
			// Set PAY_SECUR_PURCH 证券清算款
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					secash, 
					RoundUtil.roundMoney2Dec(secash*tran.getPrinFxrate()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			
			// Set ACCRUED_INC 应收利息
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					accrued,
					RoundUtil.roundMoney2Dec(accrued*tran.getPrinFxrate()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
			
			// Set BOOK_VALUE 债券成本
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					bookvalueReduced, 
					RoundUtil.roundMoney2Dec(bookvalueReduced*tran.getPrinFxrate()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_STOCK_PAR));
			
			//折价发行   债券折溢价成本
			jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						priceDiffReduced, 
						RoundUtil.roundMoney2Dec(priceDiffReduced*tran.getPrinFxrate()), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BookValue_Dis));
			
			
			jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						incomeDiff, 
						RoundUtil.roundMoney2Dec(incomeDiff*tran.getPrinFxrate()), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN));  
			
		}else{
			//获取总账组信息
			// Set RECEIV_SECUR_SOLD
			double secash=RoundUtil.roundMoney2Dec(tran.getProceeds()+tran.getIntps());
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					RoundUtil.roundMoney2Dec(secash*tranBasic.getFxrate()), 
					secash, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
			
			// Set BOOK_VALUE 成本
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tran.getgCostFunc(), 
					tran.getgCost(),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
			
			double gainORloss = RoundUtil.roundMoney2Dec(tran.getProceeds()*tranBasic.getFxrate()-tran.getgCostFunc());

			if (gainORloss > 0) { // 赚了
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(gainORloss),
						RoundUtil.roundMoney2Dec(tran.getProceeds()-tran.getgCost()), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN));
			} else { // 亏了
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(Math.abs(gainORloss)),
						RoundUtil.roundMoney2Dec(Math.abs(tran.getProceeds()-tran.getgCost())), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS));
			}
			
			if(tran.getIntps()!=0){
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getIncFxrate()), 
						tran.getIntps(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
			}
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
