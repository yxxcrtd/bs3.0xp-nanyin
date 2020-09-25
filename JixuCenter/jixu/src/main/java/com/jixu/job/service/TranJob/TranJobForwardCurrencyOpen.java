package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		TranJobForwardCurrencyOpen
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobForwardCurrencyOpen")
public class TranJobForwardCurrencyOpen extends TranJobBasic{
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return null;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException {
		
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranBasic.getPortfolio());
		IssueInfo issueInfo =tranBasic.getCusip();
		//多空方即期汇率
		double priFxRate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur(), tran.getTradeDate());
		double priFxRate2 = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(),issueInfo.getIncomeCur2(), tran.getTradeDate());
		
		//表外账
		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				RoundUtil.roundMoney2Dec(Math.abs(tran.getAmount()*priFxRate)), 
				Math.abs(tran.getAmount()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
				tranBasic.getFxcurrency());
		
		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				RoundUtil.roundMoney2Dec(Math.abs(tran.getAmount2()*priFxRate2)), 
				Math.abs(tran.getAmount2()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),
				tranBasic.getFxcurrency2());
		
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		return null;
	}
}
