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

@Service("TranJobTimeDepositSale")
public class TranJobTimeDepositSale extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Resource(name="tranJobRepoLendMaturity")
	private TranJobService saleJob;
	
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
		saleJob.exeTranJob(params);
		
		//原来以为利息需要完全冲销
		/*TranSum tsum=comDAO.loadTSumByCusipLot(portfolio.getId(), tran.getCusip().getId(), tran.getLotnum());
		if(tsum.getAmount()==0){
			double accruedAmt=comDAO.getLastTranCloAccrued(portfolio.getId(), tran.getCusip().getId(), tran.getLotnum(), DateUtil.convertStr2Date(tran.getLedgerDate()));
			double diffAcc=RoundUtil.roundMoney2Dec(accruedAmt-tran.getIntps());
			double diffAccFunc=RoundUtil.roundMoney2Dec(diffAcc*tran.getIncFxrate());
			if(diffAcc!=0 || diffAccFunc!=0){
				jobCom.saveGL(pursaleCooa, new VoucherDetails(), 
						DBCR.CREDIT,
						diffAcc, 
						diffAccFunc,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr().getId(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC));
				
				jobCom.saveGL(pursaleCooa,new VoucherDetails(), 
						DBCR.DEBIT,
						diffAcc, 
						diffAccFunc,
						jobCom.loadGLAcctnum(portfolio.getCoaahdr().getId(), sec.getCoaaGroup(), GLACCT.CAPITAL_GAIN));
				
			}
		}*/
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
