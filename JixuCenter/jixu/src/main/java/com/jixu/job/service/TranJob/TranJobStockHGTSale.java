package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobStockHGTSale")
public class TranJobStockHGTSale extends TranJobBasic{
	
	@Resource(name="tranJobStockSale")
	private TranJobService tranJob;
	
	@Resource(name="tranJobCashMove")
	private TranJobService cashMoveJob;
	
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		Date level1SetDate=tranbasic.getSettledate();
		Date level2SetDate=tranbasic.getSettledate2();
		
		if(level1SetDate.compareTo(ledgerDate)==0){
			return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
		}else if(level2SetDate.compareTo(ledgerDate)==0){
			return cashMoveJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
		}else{
			return null;
		}
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		tranJob.exeTranJob(params);
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		return tranJob.exeTranJob(portfolio, tranBasic);
	}
}
