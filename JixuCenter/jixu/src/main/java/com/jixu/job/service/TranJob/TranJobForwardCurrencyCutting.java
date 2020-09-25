package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.BankAccountType;
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

@Service("tranJobForwardCurrencyCutting")
public class TranJobForwardCurrencyCutting extends TranJobBasic{
	
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
		
		Tran t = tranDao.loadPreMaturityDepositTran(tran.getPortfolio(), tran.getCusip().getId(),tran.getLotnum());
		TranBasic tb=t.getTanum();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranBasic.getPortfolio());
		IssueInfo issueInfo =tranBasic.getCusip();
		//表外账开仓交易当天多空方即期汇率
		double priFxRate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur(), t.getTradeDate());
		double priFxRate2 = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur2(), t.getTradeDate());
		
//		double diff=RoundUtil.roundMoney2Dec(tran.getAmount()*tran.getIncFxrate())-RoundUtil.roundMoney2Dec(tran.getAmount2()*tran.getIncFxrate2());
		double diffcash=tranBasic.getCash();
		String cashglcode=tranBasic.getBkCashacctNo().getCashglcode();
		
		checkCashAcctValiable(cashglcode, tranBasic.getBkCashacctNo().getBankdesc(), BankAccountType.Cash_Account);
		
		//反冲表外账
		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT,
				Math.abs(RoundUtil.roundMoney2Dec(t.getAmount2() * priFxRate2)), 
				Math.abs(t.getAmount2()),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),
				tb.getFxcurrency2());

		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT,
				Math.abs(RoundUtil.roundMoney2Dec(t.getAmount() * priFxRate)), 
				Math.abs(t.getAmount()),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
				tb.getFxcurrency());
		
		if(diffcash>0){
			//借
			jobCom.saveZJZHGL(pursaleCooa,
				portfolio,
				tranBasic.getBkCashacctNo(),
				DBCR.DEBIT, 
				diffcash, 
				diffcash, 
				cashglcode,
				portfolio.getCurID());
			//贷
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				diffcash, 
				diffcash, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN),
				portfolio.getCurID());
		}else{
			//借
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				Math.abs(diffcash),
				Math.abs(diffcash),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS),
				portfolio.getCurID());
			//贷
			jobCom.saveZJZHGL(pursaleCooa,
				portfolio,
				tranBasic.getBkCashacctNo(),
				DBCR.CREDIT, 
				Math.abs(diffcash),
				Math.abs(diffcash), 
				cashglcode,
				portfolio.getCurID());
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		return null;
	}
}
