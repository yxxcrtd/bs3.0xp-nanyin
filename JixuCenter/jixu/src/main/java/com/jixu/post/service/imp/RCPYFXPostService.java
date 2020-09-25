package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyFXPost")
public class RCPYFXPostService extends RCPYServiceImpl {
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		
		BkCashRcpy bcr_long = new BkCashRcpy();
	
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		//实际post的时候，再获取最新的估值汇率
		double fxrate_short=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tranAdvice.getFxcurrency2(), tranAdvice.getTradedate());
		double fxrate_long=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tranAdvice.getFxcurrency(), tranAdvice.getTradedate());
			
		double fxorigbal_long = tranAdvice.getAmount();
		double fxremainbal_long = fxorigbal_long;
		double origbal_long = RoundUtil.roundMoney2Dec(fxorigbal_long * fxrate_long);
		double amount_long = tranAdvice.getAmount();;
		double remainbal_long = origbal_long;
		
		bcr_long.setPortfolio(portfolio);
		bcr_long.setZero(0);
		bcr_long.setDbcr(DBCR.CREDIT);
		bcr_long.setPosttype(PostType.ForwardCurrencyContract);
		bcr_long.setTanum(tranAdvice);
		bcr_long.setCusip(tranAdvice.getCusip());
		bcr_long.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr_long.setLedger(tranAdvice.getLedgerdate());
		bcr_long.setOrigbal(origbal_long);
		bcr_long.setRemainbal(remainbal_long);
		bcr_long.setFxcurrency(tranAdvice.getFxcurrency());
		bcr_long.setFxorigbal(fxorigbal_long);
		bcr_long.setFxremainbal(fxremainbal_long);
		bcr_long.setUorigbal(amount_long);
		bcr_long.setUremainbal(amount_long);
		bcr_long.setFxrate(fxrate_long);
		bcr_long.setTrantype(tranAdvice.getTrantype());
		bcr_long.setPaydate(tranAdvice.getSettledate());
		bcr_long.setExpdcashdate(tranAdvice.getSettledate());
		bcr_long.setBlockid(0);
		bcr_long.setMiscorigbal(0);
		bcr_long.setMiscremainbal(0);
		bcr_long.setRcpyDesc(tranAdvice.getTranDesc());
		
		BkCashRcpy bcr_short = new BkCashRcpy();
		double fxorigbal_short = tranAdvice.getAmount2();
		double fxremainbal_short = fxorigbal_short;
		double origbal_short = RoundUtil.roundMoney2Dec(fxorigbal_short * fxrate_short);
		double amount_short = tranAdvice.getAmount2();;
		double remainbal_short = origbal_short;
		
		bcr_short.setPortfolio(portfolio);
		bcr_short.setZero(0);
		bcr_short.setDbcr(DBCR.DEBIT);
		bcr_short.setPosttype(PostType.ForwardCurrencyContract);
		bcr_short.setTanum(tranAdvice);
		bcr_short.setCusip(tranAdvice.getCusip());
		bcr_short.setBkCashAcct(tranAdvice.getBkCashacctNo2());
		bcr_short.setLedger(tranAdvice.getLedgerdate());
		bcr_short.setOrigbal(origbal_short);
		bcr_short.setRemainbal(remainbal_short);
		bcr_short.setFxcurrency(tranAdvice.getFxcurrency2());
		bcr_short.setFxorigbal(fxorigbal_short);
		bcr_short.setFxremainbal(fxremainbal_short);
		bcr_short.setUorigbal(amount_short);
		bcr_short.setUremainbal(amount_short);
		bcr_short.setFxrate(fxrate_short);
		bcr_short.setTrantype(tranAdvice.getTrantype());
		bcr_short.setPaydate(tranAdvice.getSettledate());
		bcr_short.setExpdcashdate(tranAdvice.getSettledate());
		bcr_short.setBlockid(0);
		bcr_short.setMiscorigbal(0);
		bcr_short.setMiscremainbal(0);
		bcr_short.setRcpyDesc(tranAdvice.getTranDesc());
		
		bkCashRcpyDao.saveEntity(bcr_long);
		bkCashRcpyDao.saveEntity(bcr_short);
	}
}
