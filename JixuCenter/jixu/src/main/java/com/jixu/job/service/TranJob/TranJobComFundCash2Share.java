package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;



import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.job.obj.TranJobParams;
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

@Service("TranJobComFundCash2Share")
public class TranJobComFundCash2Share extends TranJobBasic{
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return null;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//获取总账组信息
		// Set BOOK_VALUE 成本
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
		
		// Set PAY_SECUR_PURCH 证券清算款(买)
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INC_ACCRUAL));
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
