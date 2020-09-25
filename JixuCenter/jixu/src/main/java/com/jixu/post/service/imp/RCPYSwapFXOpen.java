package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpySwapFXOpen")
public class RCPYSwapFXOpen extends RCPYServiceImpl {

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getPrincipal());
		double fxremainbal = fxorigbal;
		
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		String funcCurrency=portfolio.getCurID();
		
		if(fxremainbal!=0) {
			String currency=tranAdvice.getSettleCurrency();
			double fxrate = commfuncService.loadFxrate(portnum, funcCurrency, currency, tranAdvice.getTradedate());
			double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
			double remainbal = origbal;
			
			BkCashRcpy bcr = new BkCashRcpy();
			bcr.setPortfolio(portfolio);
			bcr.setZero(0);
			bcr.setDbcr(DBCR.CREDIT);
			bcr.setPosttype(PostType.Transaction);
			bcr.setTanum(tranAdvice);
			bcr.setCusip(tranAdvice.getCusip());
			bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr.setLedger(tranAdvice.getLedgerdate());
			bcr.setOrigbal(origbal);
			bcr.setRemainbal(remainbal);
			bcr.setFxcurrency(currency);
			bcr.setFxorigbal(fxorigbal);
			bcr.setFxremainbal(fxremainbal);
			bcr.setUorigbal(fxorigbal);
			bcr.setUremainbal(fxremainbal);
			bcr.setFxrate(fxrate);
			bcr.setTrantype(tranAdvice.getTrantype());
			bcr.setPaydate(tranAdvice.getSettledate());
			bcr.setExpdcashdate(tranAdvice.getSettledate());
			bcr.setBlockid(0);
			bcr.setMiscorigbal(0);
			bcr.setMiscremainbal(0);
			bcr.setRcpyDesc(tranAdvice.getTranDesc());
	
			bkCashRcpyDao.saveEntity(bcr);
		}
		
		BkCashRcpy bcr2 = new BkCashRcpy();
		
		double fxorigbal2 = RoundUtil.roundMoney2Dec(tranAdvice.getPrincipal2());
		double fxremainbal2 = fxorigbal2;
		String currency2=tranAdvice.getSettleCurrency2();
		double fxrate2 = commfuncService.loadFxrate(portnum, funcCurrency, currency2, tranAdvice.getTradedate());
		double origbal2 = RoundUtil.roundMoney2Dec(fxorigbal2 * fxrate2);
		double remainbal2 = origbal2;
		
		if(fxremainbal2!=0) {
			bcr2.setPortfolio(portfolio);
			bcr2.setZero(0);
			bcr2.setDbcr(DBCR.DEBIT);
			bcr2.setPosttype(PostType.Transaction);
			bcr2.setTanum(tranAdvice);
			bcr2.setCusip(tranAdvice.getCusip());
			bcr2.setBkCashAcct(tranAdvice.getBkCashacctNo2());
			bcr2.setLedger(tranAdvice.getLedgerdate());
			bcr2.setOrigbal(Math.abs(origbal2));
			bcr2.setRemainbal(Math.abs(remainbal2));
			bcr2.setFxcurrency(currency2);
			bcr2.setFxorigbal(Math.abs(fxorigbal2));
			bcr2.setFxremainbal(Math.abs(fxremainbal2));
			bcr2.setUorigbal(fxorigbal2);
			bcr2.setUremainbal(fxremainbal2);
			bcr2.setFxrate(fxrate2);
			bcr2.setTrantype(tranAdvice.getTrantype());
			bcr2.setPaydate(tranAdvice.getSettledate());
			bcr2.setExpdcashdate(tranAdvice.getSettledate());
			bcr2.setBlockid(0);
			bcr2.setMiscorigbal(0);
			bcr2.setMiscremainbal(0);
			bcr2.setRcpyDesc(tranAdvice.getTranDesc());
	
			bkCashRcpyDao.saveEntity(bcr2);
		}
	}
}
