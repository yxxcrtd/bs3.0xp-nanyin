package com.jixu.post.service.imp;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSubRed;
import com.orm.obj.TranSum;

@Service("subRedPostImp")
public class PostSubRed extends PostServiceImpl {

	/**
	 * 申购赎回不进入tran和sum表
	 */
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum){
		return null;
	}
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		PortfolioHdr portfolio = commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		double taamount = tranAdvice.getAmount();
		double taprincipal = tranAdvice.getPrincipal();
		double tacash=tranAdvice.getCash();

		TranSubRed tranSubRed=new TranSubRed();
		tranSubRed.setPortfolio(portfolio);
		tranSubRed.setTanum(tranAdvice);
		tranSubRed.setTradeDate(tranAdvice.getTradedate());
		tranSubRed.setSettlementDate(tranAdvice.getSettledate());
		tranSubRed.setLedgerin(tranAdvice.getLedgerdate());
		tranSubRed.setEffectiveDate(tranAdvice.getSettledate());
		tranSubRed.setLedgerReversed(99999999);
		tranSubRed.setTranType(tranAdvice.getTrantype());
		tranSubRed.setAmount(taamount);
		tranSubRed.setgCost(taprincipal);
		tranSubRed.setCash(tacash);
		tranSubRed.setNav(RoundUtil.roundMoney(tacash/taamount,portfolio.getNavPrecision()));
		tranSubRed.setFxrate(tranAdvice.getFxrate());
		
		tranSubRedDao.saveEntity(tranSubRed);
		
		return null;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		Iterator<TranSubRed> subredIter=tranAdvice.getTranSubRed().iterator();
		while(subredIter.hasNext()){
			TranSubRed subred=subredIter.next();
			tranSubRedDao.deleteEntity(subred);
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
