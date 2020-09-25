package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.TransactionWrapObj;
import com.jixu.journal.service.PurSaleService;
import com.orm.obj.PortfolioHdr;

@Service("pursaleJnlMLjob")
public class PurSaleMLJob extends JNLJob {

	@Resource(name="purSaleServiceML")
	private PurSaleService purSaleServiceML;

	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		PurSaleService psservice = purSaleServiceML;

		List<GLCOOABase> pursaleJnlList = new ArrayList<GLCOOABase>();
		List<TransactionWrapObj> transWrapList = null;
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(jobParam.getPortfolio());
		container.setEntityObj(portfolio);
		container.setHeader(JournalTypes.getJournalTypeHM().get(JournalTypes.PURSALEJNL));
		
		
		transWrapList = psservice.loadTransData(jobParam.getDate(), portfolio, jobParam.getCusip());
		//股票买卖
		processPurSaleSec(transWrapList, pursaleJnlList);
			

		getPrintjnl().printJNL(pursaleJnlList);
		return null;
	}

	/*************** Start 股票**************************/
	private void processPurSaleSec(List<TransactionWrapObj> transWrapList, List<GLCOOABase> pursaleJnlList) throws JNLException{
		//A1*P2*R2 - A1*P1*R1 (总的损益)= (A1*P2*R2 - A1*P1*R2) (价格引起)+ (A1*P1*R2 - A1*P1*R1) (汇率变化引起)
		//FX_GAIN_CAPITAL_UNREAL,FX_LOSS_CAPITAL_UNREAL (汇率损益总账栏)
		//CAPITAL_GAIN,CAPITAL_LOSS (价格损益总账栏)
		//BOOK_VALUE(成本) RECEIV_SECUR_SOLD 证券清算款(卖) PAY_SECUR_PURCH 证券清算款(买)
		
		/**double l_bookvalue = 0.0;
		double l_bookfvalue = 0.0;
		double temp_sec_fvalue = 0.0;
		double diff = 0.0;
		Map<Integer, TranFeeObj> mapMiniFee = null;//
		Iterator<Integer> itfee = null;//
		TranFeeObj miniFee = null;

		TransactionWrapObj tranwrap = null;
		GLCOOABase pursaleCooa = null;
		Iterator<TransactionWrapObj> ittranwrap = transWrapList.iterator();
		
		double gcost = 0.0;
		double gcostfunc = 0.0;
		double proceeds = 0.0;
		double amount = 0.0;
		double fxrate = 0.0;
		double totalFees = 0.0;
		double totalFeesFunc = 0.0;
		int cusip = 0;
		int trantype = 0;
		int tanum = 0;
		int chartnum = 1; // For testing
		int groupnum = 1;
		
		boolean feeadjust = false;*/
		
		/**损益变量**/
		/**double pricegain = 0.0;
		double pricegainfunc = 0.0;
		double priceloss = 0.0;
		double pricelossfunc = 0.0;
		double rategainfunc = 0.0;
		double ratelossfunc = 0.0;
		
		double gap = 0.0;
		
		while (ittranwrap.hasNext()) {
			tranwrap = ittranwrap.next();*/
			/*****start 初始化数据*********/
		/**	pricegain = 0.0;
			pricegainfunc = 0.0;
			priceloss = 0.0;
			pricelossfunc = 0.0;
			rategainfunc = 0.0;
			ratelossfunc = 0.0;
			gcost = 0.0;
			gcostfunc = 0.0;
			amount = 0.0;
			proceeds = 0.0;*/
			/*****end 初始化数据*********/
			
		/**	if (tranwrap.getTrandataList().size() > 0) {
				tanum = tranwrap.getTrandataList().get(0).getTanum().getId();
				trantype = tranwrap.getTrandataList().get(0).getTrantype();
				groupnum = tranwrap.getTrandataList().get(0).getCusip().getCoaaGroup();
				cusip = tranwrap.getTrandataList().get(0).getCusip().getId();
				fxrate = tranwrap.getTrandataList().get(0).getTanum().getFxrate();
			}
			
			for (Tran trandata : tranwrap.getTrandataList()) {
				gcost += trandata.getgCost();
				gcostfunc += trandata.getgCostFunc();
				amount += trandata.getAmount();
				proceeds += trandata.getProceeds();
				
				if (trantype == TransTypes.SALE) {*/
					/***价格引起的损益******/
		/**			gap = trandata.getProceeds() - trandata.getgCost();
					if (gap > 0) {
						pricegain += gap;
					} else {
						priceloss += Math.abs(gap);
					}
					gap = RoundUtil.roundMoney2Dec(trandata.getProceeds()*tranwrap.getFxrate())
						-RoundUtil.roundMoney2Dec(trandata.getgCost()*tranwrap.getFxrate());
					if (gap > 0) {
						pricegainfunc += gap;
					} else {
						pricelossfunc += Math.abs(gap);
					}*/
					
					/***汇率引起的损益******/
		/**			gap = RoundUtil.roundMoney2Dec(trandata.getgCost()*tranwrap.getFxrate())
						-RoundUtil.roundMoney2Dec(trandata.getgCostFunc());
					if (gap > 0) {
						rategainfunc += gap;
					} else {
						ratelossfunc += Math.abs(gap);
					}
				}
			}
			
			pursaleCooa = new GLCOOABase();*/
			
			/*((StockGLCooa)pursaleCooa).setAmount(amount);
			((StockGLCooa)pursaleCooa).setTanum(tanum);
			((StockGLCooa)pursaleCooa).setCusip(cusip);*/
			
			//统计费用
			//mapMiniFee = JOBUtil.convertFees(tranwrap.getTransFeeList());
			//获取总费用
			//totalFees = JOBUtil.getTotalFees(tranwrap.getTransFeeList());
		/**	totalFeesFunc = RoundUtil.roundMoney2Dec(totalFees*fxrate);
			
			
			if (trantype == TransTypes.PURCHASE) {
				// Set BOOK_VALUE 成本
				//pursaleJNLobj = new VoucherDetails();
				
				jobCom.saveGL(pursaleCooa, 
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(gcostfunc),
						RoundUtil.roundMoney2Dec(gcost), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
				
				//l_bookvalue = pursaleJNLobj.getDr_local();
				//l_bookfvalue = pursaleJNLobj.getDr_func();
				
				// Set PAY_SECUR_PURCH 证券清算款(买)
	
				jobCom.saveGL(pursaleCooa, 
						//pursaleJNLobj, 
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(l_bookvalue + totalFees)* fxrate), 
						RoundUtil.roundMoney2Dec(l_bookvalue + totalFees), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
				
				//temp_sec_fvalue = pursaleJNLobj.getCr_func();*/
				
				/***本币借贷不平调整到费用**/
		//		diff = RoundUtil.roundMoney2Dec(temp_sec_fvalue-totalFeesFunc-l_bookfvalue);
				
				/***0002 费用设置********/
		//		itfee = mapMiniFee.keySet().iterator();
				
		/**		while (itfee.hasNext()) {
					miniFee = mapMiniFee.get(itfee.next());
					temp_sec_fvalue = RoundUtil.roundMoney2Dec(miniFee.getFxfeeAmount()*miniFee.getFxrate());
					
					if (miniFee.getGlcolumn() == GLACCT.FEE_EXPENSE) {
						temp_sec_fvalue+=diff; //(强制调账到交易所费用)
						feeadjust = true;
					}
					
					if (Math.abs(temp_sec_fvalue) < DBCR.ZERO) {
						continue;
					}
					jobCom.saveGL(pursaleCooa, 
							DBCR.DEBIT, 
							temp_sec_fvalue, 
							RoundUtil.roundMoney2Dec(miniFee.getFxfeeAmount()), 
							jobCom.loadGLAcctnum(chartnum, groupnum, miniFee.getGlcolumn()));
				}
				
				//(强制调账到交易所费用)
				if (Math.abs(diff) > DBCR.ZERO && feeadjust == false) {
					jobCom.saveGL(pursaleCooa, 
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(diff), 
							0, 
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE));
				}*/
				
				/***0002 费用设置********/
				
		/**		pursaleJnlList.add(pursaleCooa);
			} else if (trantype == TransTypes.SALE) {*/

				/***0002费用设置********/
		/**		itfee = mapMiniFee.keySet().iterator();
				while (itfee.hasNext()) {
					miniFee = mapMiniFee.get(itfee.next());
					
					jobCom.saveGL(pursaleCooa, 
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(miniFee.getFxfeeAmount()*miniFee.getFxrate()), 
							RoundUtil.roundMoney2Dec(miniFee.getFxfeeAmount()), 
							jobCom.loadGLAcctnum(chartnum, groupnum, miniFee.getGlcolumn()));
				}
				/***0002费用设置********/
				
		/**		// Set RECEIV_SECUR_SOLD
				//pursaleJNLobj = new VoucherDetails();
				jobCom.saveGL(pursaleCooa, 
						//pursaleJNLobj, 
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(proceeds-totalFees)*fxrate), 
						RoundUtil.roundMoney2Dec(proceeds-totalFees), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD));
				
				//temp_sec_fvalue = pursaleJNLobj.getDr_func();

				// Set BOOK_VALUE 成本
				jobCom.saveGL(pursaleCooa, 
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(gcostfunc), 
						RoundUtil.roundMoney2Dec(gcost),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
				//收益
				
				if (pricegainfunc > 0) {
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(pricegainfunc), 
							RoundUtil.roundMoney2Dec(pricegain),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN));
				}
				
				if (pricelossfunc > 0) {
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(pricelossfunc), 
							RoundUtil.roundMoney2Dec(priceloss), 
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS));
				}
				
				if (rategainfunc > 0) {
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(rategainfunc), 
							RoundUtil.roundMoney2Dec(0), 
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_GAIN_CAPITAL_UNREAL));
				}
				
				if (ratelossfunc > 0) {
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(ratelossfunc), 
							RoundUtil.roundMoney2Dec(0), 
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_LOSS_CAPITAL_UNREAL));
				}
				// 添加到买卖日记账List里面。
				pursaleJnlList.add(pursaleCooa);
				/*************** END 股票 **************************/
			//}
		//}
	}
}
