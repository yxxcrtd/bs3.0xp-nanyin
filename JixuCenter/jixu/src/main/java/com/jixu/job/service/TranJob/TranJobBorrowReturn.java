package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
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

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobBorrowReturn")
public class TranJobBorrowReturn extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		// TODO Auto-generated method stub
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//获取总账组信息
		// Set RECEIV_SECUR_SOLD
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				tran.getgCostFunc(), 
				tran.getgCost(),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
		
		// Set BOOK_VALUE 成本
		jobCom.saveGL(pursaleCooa, 
				DBCR.CREDIT, 
				tran.getgCostFunc(), 
				tran.getgCost(),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
		
		if(tran.getIntps()!=0){
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getIncFxrate()), 
					tran.getIntps(),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getIncFxrate()), 
					tran.getIntps(),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
