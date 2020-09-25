package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobSecLend")
public class TranJobSecLend extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		double totalFeeFunc=params.getTotalFeeFunc();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
		if(sec.getIssueType().equals(AssetTypes.COMMON_STOCK)){
			//获取总账组信息
			// Set BOOK_VALUE 成本
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tran.getgCostFunc(),
					tran.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT));
			
			// Set PAY_SECUR_PURCH 证券清算款(买)
			double funcCash=RoundUtil.roundMoney2Dec(tranBasic.getCash()* tranBasic.getFxrate());
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT,
					tran.getgCostFunc(),
					tran.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			
			/***本币借贷不平调整到费用(强制调账到交易所费用)**/
			double diff = RoundUtil.roundMoney2Dec(funcCash-totalFeeFunc-tran.getgCostFunc());
			
			//(强制调账到交易所费用)
			if (Math.abs(diff) > DBCR.ZERO) {
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(diff), 
						0, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE));
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(diff), 
						0, 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			}
		}else{
			return;
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		return null;
	}
}
