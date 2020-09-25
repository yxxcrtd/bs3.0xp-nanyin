package com.jixu.post.service.imp;


import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.ParChange;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

/**
 * 还面额，减cost，其它没有操作,数量不变
 * @author "taotaizhu"
 *
 */

@Service
public class PostBondHBFX extends PostServiceImpl {

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		String portnum = tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		String cusip = tranAdvice.getCusip().getId();
		
		//获取需要卖出的tsum头寸
		List<TranSum> tSumList = tranSumDao.loadTranSumByPortCusipForSale(portnum, cusip, LongShortType.longTerm);
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		double perCutPar=RoundUtil.roundMoney2Dec(sec.getContractSize()*tranAdvice.getAltRate());
		
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
		ParChange parchange=incomeCal.getPayParChange(sec.getContractSize(), cfc, tranAdvice.getTradedate());
		double oldPar=parchange.getYesterdayParValue();
		
		double parPercent=perCutPar/oldPar;

		double fxrate=commfuncService.loadFxrate(portnum, portfolio.getCurID(), sec.getPriCur(), tranAdvice.getTradedate());
		
		for (int i = 0; i < tSumList.size(); i++) {
			TranSum tSum = tSumList.get(i);
				
			double amount=tSum.getAmount();
			//减去的面值
			double principal=RoundUtil.roundMoney2Dec(amount*perCutPar);
			//减去买入的成本
			double cost=RoundUtil.roundMoney2Dec(tSum.getgCost()*parPercent);
			double funcCost=RoundUtil.roundMoney2Dec(tSum.getgCostfunc()*parPercent);

			Tran tran = new Tran();
			IssueInfo issueInfo = new IssueInfo();
			issueInfo.setId(cusip);
			tran.setCusip(issueInfo);
			tran.setPortfolio(portnum);
			tran.setLotnum(tSum.getLotnum());
			tran.setTrannum(tSum.getLstTrannum()+1);
			tran.setTrantype(tranAdvice.getTrantype());
			tran.setTanum(tranAdvice);
			tran.setTradeDate(tranAdvice.getTradedate());
			tran.setSettleDate(tranAdvice.getSettledate());
			tran.setLedgerDate(tranAdvice.getLedgerdate());
			tran.setAmount(0);
			tran.setProceeds(principal);
			tran.setPrinFxrate(fxrate);
			tran.setIncFxrate(fxrate);
			tran.setgCost(cost);
			tran.setgCostFunc(funcCost);
			tran.setSecacct(tSum.getSecacct());

			tSum=CaltSum(tran, tSum);
			
			tranDao.saveEntity(tran);
			tranSumDao.updateEntity(tSum);
		}
		
		return null;
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}

	@Override
	public void delete(TranBasic tranAdvice, ResultContainer container) {
		// TODO Auto-generated method stub
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return caltSumSaleUtil(tran, tSum);
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return unpostCaltsumSaleUtil(tran, tSum);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
