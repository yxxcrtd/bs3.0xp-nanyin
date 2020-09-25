package com.jixu.post.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.OptionType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostOption2Stock")
public class PostOption2Stock extends PostServiceImpl {

	@Resource(name = "slStockPurPostImp")
	private PostService postService1;

	@Resource(name = "slStockSalePostImp")
	private PostService postService2;

	/**
	 * 目前兴权,实际上是做了一笔卖期权卖出和一笔买股票买入,但是使用的是期权行权的交易类型
	 * tranAdvice交易基础表中,amount1是股票,作为long. amount2是债券,作为short
	 */
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {

		// 处理债券卖出----------------------------------------------------------
		String portnum1 = tranAdvice.getPortfolio();

		double taamount1 = tranAdvice.getAmount();
		String cusip1 = tranAdvice.getCusip().getId();
		String secacct1 = tranAdvice.getSecacct();
		
		int ttrannum1 = 0;
		// 获取需要卖出的tsum头寸
		TranSum tSum1 = tranSumDao.loadTSum(portnum1, cusip1, secacct1,LongShortType.longTerm);
		if (tSum1 == null) {
			// 头寸不存在
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_sale_noSum);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_noSum));
			return null;
		} else if (tSum1.getAmount() < taamount1) {
			// 该证券持仓数量小于卖出数量
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_sale_zero);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_zero)+"-"+tranAdvice.getCusip().getId());
			return null;
		} else {
			ttrannum1 = tSum1.getLstTrannum();
			ttrannum1 = ttrannum1 + 1;
		}
		double tsumG_Cost1 = tSum1.getgCost();
		double tsumG_CostFunc1 = tSum1.getgCostfunc();
		double tsumAmount1 = tSum1.getAmount();
		//这里是否需要这么计算？目前是把所有的买入时成本都结转掉
		double tranG_Cost1 = RoundUtil.roundMoney(tsumG_Cost1* (taamount1 / tsumAmount1), 2);
		double tranG_CostFunc1 = RoundUtil.roundMoney(tsumG_CostFunc1* (taamount1 / tsumAmount1), 2);

		Tran tran1 = new Tran();
		tran1.setCusip(tranAdvice.getCusip());
		tran1.setPortfolio(tranAdvice.getPortfolio());
		tran1.setLotnum(tSum1.getLotnum());
		tran1.setTrannum(ttrannum1);
		tran1.setTrantype(tranAdvice.getTrantype());
		tran1.setTanum(tranAdvice);
		tran1.setTradeDate(tranAdvice.getTradedate());
		tran1.setSettleDate(tranAdvice.getSettledate());
		tran1.setLedgerDate(tranAdvice.getLedgerdate());
		tran1.setAmount(taamount1);
		tran1.setProceeds(tranAdvice.getPrincipal());
		tran1.setPrinFxrate(tranAdvice.getFxrate()); //Todo
		tran1.setIncFxrate(tranAdvice.getFxrate());
		tran1.setgCost(tranG_Cost1);
		tran1.setgCostFunc(tranG_CostFunc1);
		tran1.setgOriginalamt(0);
		tran1.setSecacct(secacct1);

		tSum1 = CaltSum(tran1, tSum1);
		if(tSum1.getAmount()==0){
			tSum1.setPosclosed(PostStatusType.sumClosed);
		}
		
		tranDao.saveEntity(tran1);
		tranSumDao.updateEntity(tSum1);

		// 先处理股票的增加---------------------
		//--------------------------------------------------
		//--------------------------------------------------
		String portnum2="";
		if(!tranAdvice.getPortfolio2().equals("")){
			portnum2=tranAdvice.getPortfolio2();
		}else{
			portnum2=portnum1;
		}
		
		double tafxrate2 = tranAdvice.getFxrate();
		
		IssueInfo sec1=issueInfoDao.loadIssueByCusip(cusip1);
		String cusip2 = sec1.getUnderCusip();
		if(cusip2==null || cusip2.equals("")){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.targetSecid_notexist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.targetSecid_notexist));
			return null;
		}
		IssueInfo sec2=issueInfoDao.loadIssueByCusip(cusip2);
		sec2.getSecurityid();
		double price2=commfuncService.loadCusipPrice(portnum2, cusip2, tranAdvice.getTradedate());
		double amount2=taamount1;
		//股票正本
		double taprincipal2 = RoundUtil.roundMoney2Dec(price2*amount2);
		
		//同一个证券账户行权
		String secacct2 = secacct1;

		int ttrannum2 = 0;
		int lotnum2 = 0;
		double tranG_CostFunc2=0;
		double tranG_Cost2=0;
		TranSum tSum2 = tranSumDao.loadTSum(portnum1, cusip2, secacct2,LongShortType.longTerm);
		Tran tran2 = new Tran();
		
		if(sec1.getOptionType()==OptionType.put){
			//认购期权
			int maxlotnum2 = tranSumDao.findMaxLotNumByCusip(portnum2, cusip2);
			
			if (tSum2 == null) {
				lotnum2 = maxlotnum2 + 1;
				ttrannum2 = 1;
				tSum2 = new TranSum();
			} else {
				lotnum2 = tSum2.getLotnum();
				ttrannum2 = tSum2.getLstTrannum();
				ttrannum2 = ttrannum2 + 1;
			}
			
			tranG_CostFunc2 = RoundUtil.roundMoney2Dec(taprincipal2* tafxrate2);
			
			tran2.setCusip(sec2);
			tran2.setPortfolio(portnum1);
			tran2.setLotnum(lotnum2);
			tran2.setTrannum(ttrannum2);
			tran2.setTrantype(tranAdvice.getTrantype());
			tran2.setTanum(tranAdvice);
			tran2.setTradeDate(tranAdvice.getTradedate());
			tran2.setSettleDate(tranAdvice.getSettledate());
			tran2.setLedgerDate(tranAdvice.getLedgerdate());
			tran2.setAmount(amount2);
			tran2.setProceeds(0);
			tran2.setPrinFxrate(tafxrate2);
			tran2.setIncFxrate(tafxrate2);
			tran2.setgCost(taprincipal2);
			tran2.setgCostFunc(tranG_CostFunc2);
			tran2.setgOriginalamt(0);
			tran2.setSecacct(secacct2);
			tran2.setManagerID(tranAdvice.getManager());
			
		}else if(sec1.getOptionType()==OptionType.call){
			//认沽期权
			//获取需要卖出的tsum头寸
			if (tSum2 == null) {
				//头寸不存在
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.post_sale_noSum);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_noSum));
				return null;
			} else if (tSum2.getAmount() < amount2) {
				//现存的股数小于售出的股数
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.post_sale_zero);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_zero)+"-"+tranAdvice.getCusip().getId());
				return null;
			} else {
				ttrannum2 = tSum2.getLstTrannum();
				ttrannum2 = ttrannum2 + 1;
			}
			lotnum2=tSum2.getLotnum();
			double tsumG_Cost2 = tSum2.getgCost();
			double tsumG_CostFunc2 = tSum2.getgCostfunc();
			double tsumAmount2 = tSum2.getAmount();
			tranG_Cost2 = RoundUtil.roundMoney(tsumG_Cost2* (amount2/tsumAmount2), 2);
			tranG_CostFunc2 = RoundUtil.roundMoney(tsumG_CostFunc2*(amount2/tsumAmount2), 2);
			
			tran2.setCusip(sec2);
			tran2.setPortfolio(tranAdvice.getPortfolio());
			tran2.setLotnum(lotnum2);
			tran2.setTrannum(ttrannum2);
			tran2.setTrantype(tranAdvice.getTrantype());
			tran2.setTanum(tranAdvice);
			tran2.setTradeDate(tranAdvice.getTradedate());
			tran2.setSettleDate(tranAdvice.getSettledate());
			tran2.setLedgerDate(tranAdvice.getLedgerdate());
			tran2.setAmount(amount2);
			tran2.setProceeds(tranAdvice.getPrincipal());
			tran2.setPrinFxrate(tranAdvice.getFxrate());	//todo-pending
			tran2.setIncFxrate(tranAdvice.getFxrate());
			tran2.setgCost(tranG_Cost2);
			tran2.setgCostFunc(tranG_CostFunc2);
			tran2.setgOriginalamt(0);
			tran2.setIntps(tranAdvice.getIntatmat());
			tran2.setSecacct(secacct2);
		}else{
			return null;
		}
		
		tSum2 = CaltSum(tran2, tSum2);
		tranDao.saveEntity(tran2);
		
		if(tSum2.getId()==0)
			tranSumDao.saveEntity(tSum2);
		else
			tranSumDao.updateEntity(tSum2);

		return null;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		String cusip=tran.getCusip().getId();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		if (sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
			if(sec.getOptionType()==OptionType.put){
				return postService1.unpostCaltsum(tran, tSum);
			}else{
				return postService2.CaltSum(tran, tSum);
			}
		} else {
			return postService2.unpostCaltsum(tran, tSum);
		}
	}

	public TranSum CaltSum(Tran tran, TranSum tSum) {
		String cusip=tran.getCusip().getId();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		if (sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
			if(sec.getOptionType()==OptionType.put){
				return postService1.CaltSum(tran, tSum);
			}else{
				return postService2.CaltSum(tran, tSum);
			}
		} else {
			return postService2.CaltSum(tran, tSum);
		}
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}

	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
