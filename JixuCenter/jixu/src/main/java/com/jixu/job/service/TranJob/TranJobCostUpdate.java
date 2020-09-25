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

@Service("TranJobCostUpdate")
public class TranJobCostUpdate extends TranJobBasic{
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		
		String offsetacct=tranBasic.getGlacct();
		//成本减少
		if(tran.getgCost()>DBCR.ZERO){   
			// offsetacct 前端传来的冲销科目
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					tran.getgCostFunc(),
					tran.getgCost(), 
					offsetacct);
			
			// Set BOOK_VALUE 成本
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tran.getgCostFunc(),
					tran.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
			
		//成本增加			
		}else{
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(Math.abs(tran.getgCostFunc())),
						RoundUtil.roundMoney2Dec(Math.abs(tran.getgCost())), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
				
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(Math.abs(tran.getgCostFunc())),
						RoundUtil.roundMoney2Dec(Math.abs(tran.getgCost())), 
						offsetacct);
		}
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
