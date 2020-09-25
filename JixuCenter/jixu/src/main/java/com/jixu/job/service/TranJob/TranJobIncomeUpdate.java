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
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobIncomeUpdate")
public class TranJobIncomeUpdate extends TranJobBasic{
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		String offsetacct=tranBasic.getGlacct();
		
		double diffAccrued=RoundUtil.roundMoney2Dec(tran.getIntps());
		double diffAccFunc=RoundUtil.roundMoney2Dec(diffAccrued*tran.getIncFxrate());
		//收益减少
			// offsetacct 前端传来的冲销科目
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				diffAccrued,
				diffAccFunc, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
			
		// Set ACCRUED_INC 利息收益
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				diffAccrued,
				diffAccFunc, 
				offsetacct);
		//收益增加	
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
