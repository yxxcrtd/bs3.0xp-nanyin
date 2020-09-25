package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyForwardCuttingPost")
public class RCPYForwardCuttingPostService extends RCPYServiceImpl {
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		
		BkCashRcpy rcpy = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		double diff=tranAdvice.getCash();

		double curFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), portfolio.getCurID(), tranAdvice.getTradedate());
		if(diff>0)
			rcpy.setDbcr(DBCR.CREDIT);
		else
			rcpy.setDbcr(DBCR.DEBIT);
		
		rcpy.setUorigbal(Math.abs(diff));
		rcpy.setUremainbal(Math.abs(diff));
		rcpy.setOrigbal(Math.abs(diff));
		rcpy.setRemainbal(Math.abs(diff));
		rcpy.setFxorigbal(Math.abs(diff));
		rcpy.setFxremainbal(Math.abs(diff));
		rcpy.setPortfolio(portfolio);
		rcpy.setZero(0);
		rcpy.setPosttype(PostType.ForwardCurrencyContract);
		rcpy.setTanum(tranAdvice);
		rcpy.setBkCashAcct(tranAdvice.getBkCashacctNo());
		rcpy.setLedger(tranAdvice.getLedgerdate());
		rcpy.setFxcurrency(portfolio.getCurID());
		rcpy.setFxrate(curFxrate);
		rcpy.setTrantype(tranAdvice.getTrantype());
		rcpy.setPaydate(tranAdvice.getSettledate());
		rcpy.setExpdcashdate(tranAdvice.getSettledate());
		rcpy.setBlockid(0);
		rcpy.setMiscorigbal(0);
		rcpy.setMiscremainbal(0);
		rcpy.setRcpyDesc(tranAdvice.getTranDesc());
		rcpy.setCusip(tranAdvice.getCusip());
		
		bkCashRcpyDao.saveEntity(rcpy);
	}
}
