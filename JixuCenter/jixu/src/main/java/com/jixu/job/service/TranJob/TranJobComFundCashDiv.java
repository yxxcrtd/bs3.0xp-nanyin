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

@Service("TranJobComFundCashDiv")
public class TranJobComFundCashDiv extends TranJobBasic{
	
	@Resource(name="tranJobStockCashDividend")
	private TranJobService jobService;
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		return jobService.exeTranJob(portfolio, tranBasic);
	}
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc,
			Date ledgerDate) throws JNLException {
		return jobService.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
}
