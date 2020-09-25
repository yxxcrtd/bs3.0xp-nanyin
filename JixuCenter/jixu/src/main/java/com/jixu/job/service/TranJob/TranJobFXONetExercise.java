package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.VoucherDetails;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service
public class TranJobFXONetExercise extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
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
		
		if(tran.getTrantype()!=TransTypes.FXOption_NetExercise){
			return;
		}
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());
		String localCur=commfuncService.loadFxCurrencyCode(portfolio.getCurID());
		double settleCash=tranBasic.getSettleCash();
		String tanum=tranBasic.getId();
		List<Tran> transList=tranDao.loadTransByTanum(tanum);
		Tran tranFXExercise=null; //行权
		if(transList.get(0).getTrantype()==TransTypes.FXOption_NetExercise){
			tranFXExercise=transList.get(0);
		}
		
		double fxGcost=tranFXExercise.getgCost();
		double fxGcostFunc=tranFXExercise.getgCostFunc();
		double diff_func=RoundUtil.roundMoney2Dec(fxGcostFunc-settleCash);
		
		//外汇期权-成本
		if(tranFXExercise.getgCost()>0){
			saveGL(pursaleCooa,
					DBCR.CREDIT, 
					fxGcostFunc,
					fxGcost, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INT_EXP_PAY_SHORT),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}else{
			saveGL(pursaleCooa,
					DBCR.DEBIT, 
					Math.abs(fxGcostFunc),
					Math.abs(fxGcost), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INT_EXP_PAY_SHORT),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}
		
		if(settleCash>0){  
			// 应收款|应付款
			saveGL(pursaleCooa,
					DBCR.DEBIT, 
					settleCash,
					settleCash, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INVEST_REC_BANK),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}else{
			saveGL(pursaleCooa,
					DBCR.CREDIT, 
					Math.abs(settleCash),
					Math.abs(settleCash), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INVEST_REC_BANK),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}
		if(diff_func>0){
			saveGL(pursaleCooa,
					DBCR.DEBIT, 
					diff_func,
					diff_func, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}else{
			saveGL(pursaleCooa,
					DBCR.CREDIT, 
					Math.abs(diff_func),
					Math.abs(diff_func), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS),
					commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2()),
					localCur,
					tranBasic.getFxrate2());
		}
		
	}
	
	
	private void saveGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
