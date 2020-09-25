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

@Service("TranJobBondWriteOff")
public class TranJobBondWriteOff extends TranJobBasic{
	
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//获取总账组信息
		// Set AMORT_DEFERRED_CAP_LOSS 资产减值损失
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.AMORT_DEFERRED_CAP_LOSS));
		
		// Set AMORT_DEFERRED_CAP_GAIN 资产减值处理
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.AMORT_DEFERRED_CAP_GAIN));
	}
	
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList,
			TranBasic tranbasic, PortfolioHdr portfolio, String desc,
			Date ledgerDate) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
