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

@Service
public class TranJobHbfxPrincipal extends TranJobBasic{
	
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
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//	摊余成本计算
		double reducebookvalue=tran.getProceeds();
			
		// Set PAY_SECUR_PURCH 证券清算款
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				reducebookvalue, 
				RoundUtil.roundMoney2Dec(reducebookvalue*tran.getPrinFxrate()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			
		// Set BOOK_VALUE 债券成本
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				reducebookvalue, 
				RoundUtil.roundMoney2Dec(reducebookvalue*tran.getPrinFxrate()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
