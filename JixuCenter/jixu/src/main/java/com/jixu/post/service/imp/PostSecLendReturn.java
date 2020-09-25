package com.jixu.post.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranBasicDao;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostSecLendReturn extends PostServiceImpl {

	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {

		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		String cusip_rq=tranAdvice.getCusip().getId();
		double amount=tranAdvice.getAmount();
		
		//处理融券协议smf
		//原始的卖券协议
		TranBasic initTranBasic=tranBasicDao.getMapedContractTranBasic(portnum, cusip_rq, tranAdvice.getSpecificLotID());
		
		if(initTranBasic==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_maturity_unmatched);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_maturity_unmatched));
			return null;
		}
		
		postTranSaleUtil(tranAdvice, LongShortType.longTerm, container);
		
		//原始的卖券协议
		String secacctShort=tranAdvice.getSecacct();
		String secacctLong=tranAdvice.getSecacct2();
		String cusip2=initTranBasic.getCusip2().getId();
		
		IssueInfo sec2=issueInfoDao.loadIssueByCusip(cusip2);
		double fxrate_pri=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec2.getPriCur(), tranAdvice.getTradedate());
		double fxrate_inc=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec2.getIncomeCur(), tranAdvice.getTradedate());
		
		//查询空方头寸为多少,融券卖出的股票
		TranSum tSumShort=tranSumDao.loadTSum(portnum, cusip2, secacctShort,LongShortType.ShortTerm);
		//查询多方头寸,正常买入的头寸, 然后多方头寸转入到空方头寸
		TranSum tSumLong=tranSumDao.loadTSum(portnum, cusip2, secacctLong,LongShortType.longTerm);
		
		int lastTranNumShort=0;
		int lastTranNumLong=0;
		if (tSumShort == null) {
			//头寸不存在
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_sale_noSum);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_noSum));
			return null;
		} else if (tSumLong == null) {
			//头寸不存在
			container.setCorrectFlag(false);
			container.setErrorMsg("没有头寸用于还券-"+sec2.getSecurityid());
			return null;
		} else if(amount>tSumLong.getAmount()){
			container.setCorrectFlag(false);
			container.setErrorMsg("还券数量大于持有头寸-"+sec2.getSecurityid()+": "+amount+"/"+tSumLong.getAmount());
			return null;
		} else if(amount>tSumShort.getAmount()){
			container.setCorrectFlag(false);
			container.setErrorMsg("还券数量大于融券数量-"+sec2.getSecurityid()+": "+amount+"/"+tSumShort.getAmount());
			return null;
		} else {
			lastTranNumShort=tSumShort.getLstTrannum();
			lastTranNumLong=tSumLong.getLstTrannum();
			
			lastTranNumShort=lastTranNumShort+1;
			lastTranNumLong=lastTranNumLong+1;
		}
		
		
		Tran tranLong = new Tran();
		double tsumG_Cost_long = tSumLong.getgCost();
		double tsumG_CostFunc_long = tSumLong.getgCostfunc();
		double tsumAmount_long = tSumLong.getAmount();
		double tranG_Cost_long = RoundUtil.roundMoney(tsumG_Cost_long* (amount/tsumAmount_long), 2);
		double tranG_CostFunc_long = RoundUtil.roundMoney(tsumG_CostFunc_long*(amount/tsumAmount_long), 2);
		tranLong.setCusip(sec2);
		tranLong.setPortfolio(portnum);
		tranLong.setLotnum(tSumLong.getLotnum());
		tranLong.setTrannum(lastTranNumLong);
		tranLong.setTrantype(tranAdvice.getTrantype());
		tranLong.setTanum(tranAdvice);
		tranLong.setTradeDate(tranAdvice.getTradedate());
		tranLong.setSettleDate(tranAdvice.getSettledate());
		tranLong.setLedgerDate(tranAdvice.getLedgerdate());
		tranLong.setAmount(amount);
		tranLong.setProceeds(tranG_Cost_long);
		tranLong.setPrinFxrate(fxrate_pri);
		tranLong.setIncFxrate(fxrate_inc);
		tranLong.setgCost(tranG_Cost_long);
		tranLong.setgCostFunc(tranG_CostFunc_long);
		tranLong.setgOriginalamt(amount);
		tranLong.setSecacct(secacctLong);
		tranLong.setManagerID(tranAdvice.getManager());
			
		tSumLong=CaltSum(tranLong, tSumLong);

		tranDao.saveEntity(tranLong);
		tranSumDao.saveEntity(tSumLong);
		

		// 先处理股票的增加---------------------
		Tran tranShort = new Tran();
		double tsumG_Cost_short = tSumShort.getgCost();
		double tsumG_CostFunc_short = tSumShort.getgCostfunc();
		double tsumAmount_short = tSumShort.getAmount();
		double tranG_Cost_short = RoundUtil.roundMoney(tsumG_Cost_short* (amount/tsumAmount_short), 2);
		double tranG_CostFunc_short = RoundUtil.roundMoney(tsumG_CostFunc_short*(amount/tsumAmount_short), 2);

		tranShort.setCusip(sec2);
		tranShort.setPortfolio(portnum);
		tranShort.setLotnum(tSumShort.getLotnum());
		tranShort.setTrannum(lastTranNumShort);
		tranShort.setTrantype(tranAdvice.getTrantype());
		tranShort.setTanum(tranAdvice);
		tranShort.setTradeDate(tranAdvice.getTradedate());
		tranShort.setSettleDate(tranAdvice.getSettledate());
		tranShort.setLedgerDate(tranAdvice.getLedgerdate());
		tranShort.setAmount(amount);
		tranShort.setProceeds(tranG_Cost_short);
		tranShort.setPrinFxrate(fxrate_pri);
		tranShort.setIncFxrate(fxrate_inc);
		tranShort.setgCost(tranG_Cost_short);
		tranShort.setgCostFunc(tranG_CostFunc_short);
		tranShort.setgOriginalamt(amount);
		tranShort.setSecacct(secacctShort);
		tranShort.setLsindicator(LongShortType.ShortTerm);
		tranShort.setManagerID(tranAdvice.getManager());
			
		tSumShort=CaltSum(tranShort, tSumShort);

		tranDao.saveEntity(tranShort);
		tranSumDao.saveEntity(tSumShort);
		
		return null;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return unpostCaltsumSaleUtil(tran, tSum);
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return caltSumSaleUtil(tran, tSum);
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
