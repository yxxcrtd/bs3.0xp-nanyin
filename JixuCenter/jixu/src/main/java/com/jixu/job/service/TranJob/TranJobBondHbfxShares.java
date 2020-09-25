package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ParChange;
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

@Service
public class TranJobBondHbfxShares extends TranJobBasic{
	
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
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
		
		Date asOfDate=tran.getTradeDate();
		ParChange parchange=incomeCal.getPayParChange(sec.getContractSize(), cfc, asOfDate);
		double oldParValue=parchange.getYesterdayParValue();
		double payParValue=parchange.getPayParToday();
		
		
		String priDay=DateUtil.getPriorDay(tran.getLedgerDate());
		TranClo oldclo=tranCloDao.loadTranClo(tran.getPortfolio(),DateUtil.convertStr2Date(priDay), tran.getCusip().getId(), tran.getLotnum());
		
		double percent=payParValue/oldParValue;//减面值比率
		double saledbookvalue=RoundUtil.roundMoney2Dec(payParValue*oldclo.getAmount());//债券成本
		
		double priceDiff=RoundUtil.roundMoney2Dec((oldParValue-oldclo.getAmrValue())*oldclo.getAmount());// 债券折溢价
		double priceDiffReduced=RoundUtil.roundMoney2Dec(priceDiff*percent);//债券折溢价按比例减少
		
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				saledbookvalue, 
				RoundUtil.roundMoney2Dec(saledbookvalue*tran.getPrinFxrate()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
		
		// Set BOOK_VALUE 债券成本
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				saledbookvalue, 
				RoundUtil.roundMoney2Dec(saledbookvalue*tran.getPrinFxrate()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
		
		//折价发行   债券折溢价成本
		jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					priceDiffReduced, 
					RoundUtil.roundMoney2Dec(priceDiffReduced*tran.getPrinFxrate()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BookValue_Dis));
		
		
		jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					priceDiffReduced, 
					RoundUtil.roundMoney2Dec(priceDiffReduced*tran.getPrinFxrate()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN));  
		
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
