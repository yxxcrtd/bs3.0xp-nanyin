package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyForwardMaturityPost")
public class RCPYForwardMaturityPostService extends RCPYServiceImpl {
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		
		BkCashRcpy rcpy_long = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
//		double longFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tranAdvice.getFxcurrency(), tranAdvice.getTradedate());
		double longFxrate=tranAdvice.getFxrate();
		rcpy_long.setDbcr(DBCR.CREDIT);
		rcpy_long.setUorigbal(tranAdvice.getAmount());
		rcpy_long.setUremainbal(tranAdvice.getAmount());
		rcpy_long.setOrigbal(RoundUtil.roundMoney2Dec(tranAdvice.getAmount()*longFxrate));
		rcpy_long.setRemainbal(RoundUtil.roundMoney2Dec(tranAdvice.getAmount()*longFxrate));
		rcpy_long.setFxorigbal(tranAdvice.getAmount());
		rcpy_long.setFxremainbal(tranAdvice.getAmount());
		
		rcpy_long.setPortfolio(portfolio);
		rcpy_long.setZero(0);
		rcpy_long.setPosttype(PostType.ForwardCurrencyContract);
		rcpy_long.setTanum(tranAdvice);
		rcpy_long.setBkCashAcct(tranAdvice.getBkCashacctNo());
		rcpy_long.setLedger(tranAdvice.getLedgerdate());
		rcpy_long.setFxcurrency(tranAdvice.getFxcurrency());
		rcpy_long.setFxrate(longFxrate);
		rcpy_long.setTrantype(tranAdvice.getTrantype());
		rcpy_long.setPaydate(tranAdvice.getSettledate());
		rcpy_long.setExpdcashdate(tranAdvice.getSettledate());
		rcpy_long.setBlockid(0);
		rcpy_long.setMiscorigbal(0);
		rcpy_long.setMiscremainbal(0);
		rcpy_long.setRcpyDesc(tranAdvice.getTranDesc());
		rcpy_long.setCusip(tranAdvice.getCusip());
		
		bkCashRcpyDao.saveEntity(rcpy_long);
		
		
		BkCashRcpy rcpy_short = new BkCashRcpy();
//		double shortFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tranAdvice.getFxcurrency2(), tranAdvice.getTradedate());
		double shortFxrate=tranAdvice.getFxrate2();
		rcpy_short.setDbcr(DBCR.DEBIT);
		rcpy_short.setUorigbal(tranAdvice.getAmount2());
		rcpy_short.setUremainbal(tranAdvice.getAmount2());
		//本币原始   剩余金额
		rcpy_short.setOrigbal(RoundUtil.roundMoney2Dec(tranAdvice.getAmount2()*shortFxrate));
		rcpy_short.setRemainbal(RoundUtil.roundMoney2Dec(tranAdvice.getAmount2()*shortFxrate));
		//原币原始   剩余金额
		rcpy_short.setFxorigbal(tranAdvice.getAmount2());
		rcpy_short.setFxremainbal(tranAdvice.getAmount2());
		
		rcpy_short.setPortfolio(portfolio);
		rcpy_short.setZero(0);
		rcpy_short.setPosttype(PostType.ForwardCurrencyContract);
		rcpy_short.setTanum(tranAdvice);
		rcpy_short.setBkCashAcct(tranAdvice.getBkCashacctNo2());
		rcpy_short.setLedger(tranAdvice.getLedgerdate());
		rcpy_short.setFxcurrency(tranAdvice.getFxcurrency2());
		rcpy_short.setFxrate(shortFxrate);
		rcpy_short.setTrantype(tranAdvice.getTrantype());
		rcpy_short.setPaydate(tranAdvice.getSettledate());
		rcpy_short.setExpdcashdate(tranAdvice.getSettledate());
		rcpy_short.setBlockid(0);
		rcpy_short.setMiscorigbal(0);
		rcpy_short.setMiscremainbal(0);
		rcpy_short.setRcpyDesc(tranAdvice.getTranDesc());
		rcpy_short.setCusip(tranAdvice.getCusip());
		bkCashRcpyDao.saveEntity(rcpy_short);
		
	}
}
