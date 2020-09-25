package com.jixu.post.service.imp;

import java.util.Date;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.FxTranType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("fxTranPost")
public class PostFXTran extends PostServiceImpl{
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		TranBasic tranBasic=tran.getTanum();
		double tsumAmount_long = tran.getAmount();
		double tsumAmount_short = tran.getAmount2();
		double tsumG_Cost_long = tran.getgCost();
		double tsumG_Cost_short = tran.getgCost2();
		double tsumG_CostFunc_long = tran.getgCostFunc();
		double tsumG_CostFunc_short = tran.getgCostFunc2();
		
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setCusip(tran.getCusip().getId());
		tSum.setLotnum(tran.getLotnum());
		tSum.setFxTranFlg(FxTranType.fxTran);
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setAmount(tsumAmount_long);
		tSum.setgCost(tsumG_Cost_long);
		tSum.setgCostfunc(tsumG_CostFunc_long);
		
		tSum.setAmount2(tsumAmount_short);
		tSum.setgCost2(tsumG_Cost_short);
		tSum.setgCostfunc2(tsumG_CostFunc_short);
		
		tSum.setAltRate(tranBasic.getAltRate());
		tSum.setAltDate(tranBasic.getAltDate());
		//tSum.setCusip(tran.getCusip().getId());

		return tSum;
	}
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		Date tatradeDate = tranAdvice.getTradedate();
		Date tasettleDate = tranAdvice.getSettledate();
		Date taledgerdate = tranAdvice.getLedgerdate();
		
		double amount_long = tranAdvice.getAmount();
		double amount_short = tranAdvice.getAmount2();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		//实际post的时候，再获取最新的估值汇率
		double fxrate_short=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tranAdvice.getFxcurrency2(), tatradeDate);
		double fxrate_long=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tranAdvice.getFxcurrency(), tatradeDate);
		
		Tran tran = new Tran();
		TranSum tranSum=null;
		
		// Set Transaction data
		//ttranum 记录总的第几笔交易
		tranSum = new TranSum();
		double cost_func_long = RoundUtil.roundMoney(amount_long * fxrate_long, 2);
		double cost_func_short = RoundUtil.roundMoney(amount_short * fxrate_short, 2);
		int lotnum=tranSumDao.findMaxLotNumByFxTranFlag(tranAdvice.getPortfolio(),1);
		lotnum=lotnum+1;	
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setLotnum(lotnum);
		tran.setTrannum(1);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tatradeDate);
		tran.setSettleDate(tasettleDate);
		tran.setLedgerDate(taledgerdate);
		tran.setAmount(amount_long);
		tran.setProceeds(0);
		tran.setPrinFxrate(fxrate_long);
		tran.setIncFxrate(fxrate_long);
		tran.setgCost(amount_long);
		tran.setgCostFunc(cost_func_long);
		tran.setgOriginalamt(amount_long);
		
		tran.setAmount2(amount_short);
		tran.setProceeds2(0);
		tran.setPrinFxrate2(fxrate_short);
		tran.setIncFxrate2(fxrate_short);
		tran.setgCost2(amount_short);
		tran.setgCostFunc2(cost_func_short);
		tran.setgOriginalamt2(amount_short);
		tran.setCusip(tranAdvice.getCusip());
		
		tranSum=CaltSum(tran, tranSum);

		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tranSum);
		
		return psm;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String taportfolio = tranAdvice.getPortfolio();
		
		Iterator<Tran> tranIter=tranAdvice.getTran().iterator();
		
		while(tranIter.hasNext()){
			Tran tran=tranIter.next();
			TranSum tSum = tranSumDao.loadFxTranTSum(taportfolio, tran.getAmount(), tran.getAmount2(), tran.getTradeDate(), tran.getSettleDate());
			if(tSum!=null){
				tranSumDao.deleteEntity(tSum);
			}
			tranDao.deleteEntity(tran);
		}
		
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
