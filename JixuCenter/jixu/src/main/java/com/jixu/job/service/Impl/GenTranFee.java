package com.jixu.job.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.FeeTypesAmount;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranFeeDef;

/** 
 * @ClassName 		GenTranFee
 * @author  		Pan Wei 
 * @Date 			2016年10月11日 下午2:18:24 	
 * @Description		[]  
 */

@Service("genTranFee")
public class GenTranFee {
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;
	
	public double genTrannFeeVoucherDetails(GLCOOABase subredCooa, List<TranBasicFee> tranFeeList, String chartnum, int groupnum) throws JNLException{
		double totalFeeFunc=0;
		for(TranBasicFee tranfee:tranFeeList){
			TranBasic tb=tranfee.getTanum();
			TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(tranfee.getFeeTypeID().getId());
			
			//根据费用货币和投资组合货币获取最新的汇率
			PortfolioHdr portfolio=commfuncService.loadPortfolio(tb.getPortfolio());
			double fxrate=commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tranfee.getCurrID(), tb.getLedgerdate());

			FeeTypesAmount feeModel=commfuncService.genFeeTypesAmount(tranfee);
			
			double cashCapitalFee=feeModel.getCashCapitalFee();
			double rcpyCapitalFee=feeModel.getRcpyCapitalFee();
			
			double cashExpensedFee=feeModel.getCashExpensedFee();
			double rcpyExpensedFee=feeModel.getRcpyExpensedFee(); 
			
			double unitTotalFee=RoundUtil.roundMoney2Dec(cashCapitalFee+cashExpensedFee);
			totalFeeFunc=RoundUtil.roundMoney2Dec(totalFeeFunc+RoundUtil.roundMoney2Dec(unitTotalFee*fxrate));
			
			//资本化现金流凭证
			if(cashCapitalFee!=0){
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(cashCapitalFee*fxrate), 
						cashCapitalFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getCostGlCol()),
						tranfee.getCurrID());
				
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(cashCapitalFee*fxrate), 
						cashCapitalFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getSecPayGlCol()),
						tranfee.getCurrID());
			}
			
			//费用化现金流凭证
			if(cashExpensedFee!=0){
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashExpensedFee*fxrate), 
						cashExpensedFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getFeeGlCol()),
						tranfee.getCurrID());
				
				jobCom.saveSecGL(subredCooa,
						tb.getCusip().getId(),
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashExpensedFee*fxrate), 
						cashExpensedFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getSecPayGlCol()),
						tranfee.getCurrID());
			}
			
			//资本化非现金流凭证
			if(rcpyCapitalFee!=0){
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(rcpyCapitalFee*fxrate), 
						rcpyCapitalFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getCostGlCol()),
						tranfee.getCurrID());
				
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(rcpyCapitalFee*fxrate), 
						rcpyCapitalFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getRcpyGlCol()),
						tranfee.getCurrID());
			}
			
			//费用化现金流凭证
			if(rcpyExpensedFee!=0){
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(rcpyExpensedFee*fxrate), 
						rcpyExpensedFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getFeeGlCol()),
						tranfee.getCurrID());
				
				jobCom.savePortGL(subredCooa,
						tb.getPortfolio(),
						DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(rcpyExpensedFee*fxrate), 
						rcpyExpensedFee, 
						jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getRcpyGlCol()),
						tranfee.getCurrID());
			}
		}
		
		return totalFeeFunc;
	}
}
