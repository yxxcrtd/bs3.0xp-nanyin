package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ValuationType;
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

@Service("TranJobBondBuy")
public class TranJobBondBuy extends TranJobBasic{
	
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
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		double totalFeeFunc=params.getTotalFeeFunc();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		IssueInfo sec=tranBasic.getCusip();
		
		//摊余成本计算
		if(tranBasic.getValuationType()==ValuationType.AMORTIZE){
			double bookvalue=RoundUtil.roundMoney2Dec(sec.getContractSize()*tran.getAmount());//债券成本
			double accrued=tranBasic.getIntatmat();//债券应收利息
			double interestDiff=RoundUtil.roundMoney2Dec(tran.getgCost()-bookvalue);// 摊余成本和面值的差额=principal-成本
			double amortize=RoundUtil.roundMoney2Dec(bookvalue+interestDiff+accrued);	//证券清算款,付钱部分
			// Set BOOK_VALUE 成本
			double funcBookvalue=RoundUtil.roundMoney2Dec(bookvalue*tran.getPrinFxrate());
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					funcBookvalue,
					bookvalue,
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_STOCK_PAR));
			
			double funcAccrued=RoundUtil.roundMoney2Dec(accrued*tran.getPrinFxrate());
			// Set ACCRUED_INC 应收利息
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					funcAccrued, 
					accrued,
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
					
			double funcAmortize=RoundUtil.roundMoney2Dec(amortize*tran.getPrinFxrate());
			// Set PAY_SECUR_PURCH 证券清算款
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					funcAmortize, 
					amortize, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			
			double funcInterestDiff=RoundUtil.roundMoney2Dec(interestDiff*tran.getPrinFxrate());
			// Set ACCRUED_INT_EXP_SHORT 利息差额调整
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					funcInterestDiff, 
					RoundUtil.roundMoney2Dec(interestDiff), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BookValue_Dis));//TODO 利息调整暂时放在该科目下
			
			double diff=RoundUtil.roundMoney2Dec(funcBookvalue+funcAccrued+funcInterestDiff-funcAmortize);
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					diff, 
					0, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
			
			
		}else{
			//获取总账组信息
			// Set BOOK_VALUE 成本
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					tran.getgCostFunc(),
					tran.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
				
			// Set PAY_SECUR_PURCH 证券清算款(买)
			double funcCash=RoundUtil.roundMoney2Dec(tranBasic.getCash()* tranBasic.getFxrate());
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tran.getgCostFunc(),
					tran.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
					
			//交易接手过来的利息
			double intpsFunc=RoundUtil.roundMoney2Dec(tran.getIntps()* tranBasic.getFxrate());
			if(tran.getIntps()!=0){
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						intpsFunc, 
						tran.getIntps(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
				
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						intpsFunc, 
						tran.getIntps(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
			}
			
			/***本币借贷不平调整到费用(强制调账到交易所费用)**/
			double diff = RoundUtil.roundMoney2Dec(funcCash-totalFeeFunc-tran.getgCostFunc()-intpsFunc);
			
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
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
