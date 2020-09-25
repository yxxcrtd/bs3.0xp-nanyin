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

@Service
public class TranJobShareBankZGSub extends TranJobBasic{
	
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
		// TODO Auto-generated method stub
		//tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		/*//获取总账组信息
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
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
		
		*//***本币借贷不平调整到费用(强制调账到交易所费用)**//*
		double diff = RoundUtil.roundMoney2Dec(funcCash-totalFeeFunc-tran.getgCostFunc());
		
		//(强制调账到交易所费用)
		if (Math.abs(diff) > DBCR.ZERO) {
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(diff), 
					0, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE));
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					RoundUtil.roundMoney2Dec(diff), 
					0, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
		}
		
		
		String portnum=tran.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		int portCoaGroup=portfolio.getCoaaGroup().getGroupNum();
		double capital=0;			//资本
		double capitalRG=0;			//已实现收益
		double caoitalUnReal=0;		//未实现收益
		double income=0;			//收入
		double expenses=0;			//开支
		
		Date ledgerDate=DateUtil.convertStr2Date(tran.getLedgerDate());
		Nav nav=comDAO.loadLatestNAV(portnum, ledgerDate);
		//净资产=资本+已实现收益+未实现收益+收入-开支
		
		if(nav != null){
			//第一天做账，没有nav信息
			capital=nav.getCapital();			//资本
			capitalRG=nav.getCapitalRG();		//已实现收益
			caoitalUnReal=nav.getCapitalUnreal();//未实现收益
			income=nav.getIncome();				//收入
			expenses=nav.getExpenses();			//开支
		}
		
		double netAsset=capital+capitalRG-caoitalUnReal+income-expenses;	//净资产
		//double shares=nav.getSharesOutStanding();							//份额
		TranSubRed subRedTran=comDAO.getTranSubReadRecordByTanum(tranBasic.getId());
			
			
		if(subRedTran.getTranType()==TransTypes.ShareBankZG){
			double subAsset=subRedTran.getgCost();	//申购款
			//double subCash=subRedTran.getCash();
			double subShares=subRedTran.getAmount();//实收基金
				
			double ch_unreal_equal_sub=0;
			if(netAsset!=0){
				ch_unreal_equal_sub=RoundUtil.roundMoney2Dec(subAsset*(-1*caoitalUnReal/netAsset));	//损益平准金未实现
			}
			double ch_real_equal_sub=RoundUtil.roundMoney2Dec(subAsset-subShares-ch_unreal_equal_sub);						//损益平准金已实现
			
			int glacct=GLACCT.RECEIV_FUND_SHARES_SOLD;
			if(subRedTran.getTranType()==TransTypes.Share_SwitchIn){
				glacct=GLACCT.share_RECEIV_FUND_SHARES_switch_in;
			}
				
			jobCom.saveGL(pursaleCooa, 
				DBCR.DEBIT, 
				subAsset,
				subAsset, 
				jobCom.loadGLAcctnum(chartnum, portCoaGroup, glacct));
				
			jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				subShares, 
				subShares, 
				jobCom.loadGLAcctnum(chartnum, portCoaGroup, GLACCT.CAPITAL_STOCK_PAR));
				
			jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				ch_real_equal_sub, 
				ch_real_equal_sub, 
				jobCom.loadGLAcctnum(chartnum, portCoaGroup, GLACCT.share_real_equal_sub));
				
			jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT, 
				ch_unreal_equal_sub, 
				ch_unreal_equal_sub, 
				jobCom.loadGLAcctnum(chartnum, portCoaGroup, GLACCT.share_unreal_equal_sub));
		}*/
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
