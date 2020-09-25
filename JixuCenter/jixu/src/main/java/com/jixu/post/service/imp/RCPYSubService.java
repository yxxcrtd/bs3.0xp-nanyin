package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpySubService")
public class RCPYSubService extends RCPYServiceImpl {
	
	public String dbcr=DBCR.CREDIT;

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getCash());
		double fxrate = tranAdvice.getFxrate();
		double origbal=RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double fxremainbal = fxorigbal;
		double remainbal = origbal;
		
		BkCashRcpy bcr = new BkCashRcpy();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		bcr.setDbcr(dbcr);
		bcr.setPosttype(PostType.Transaction);
		bcr.setTanum(tranAdvice);
		bcr.setCusip(tranAdvice.getCusip());
		bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr.setLedger(tranAdvice.getLedgerdate());
		bcr.setOrigbal(origbal);
		bcr.setRemainbal(remainbal);
		bcr.setFxcurrency(tranAdvice.getFxcurrency());
		bcr.setFxorigbal(fxorigbal);
		bcr.setFxremainbal(fxremainbal);
		bcr.setUorigbal(0);
		bcr.setUremainbal(0);
		bcr.setFxrate(tranAdvice.getFxrate());
		bcr.setTrantype(tranAdvice.getTrantype());
		bcr.setPaydate(tranAdvice.getSettledate());
		bcr.setExpdcashdate(tranAdvice.getSettledate());
		bcr.setBlockid(0);
		bcr.setMiscorigbal(0);
		bcr.setMiscremainbal(0);
		bcr.setRcpyDesc(tranAdvice.getTranDesc());

		bkCashRcpyDao.saveEntity(bcr);
	}
}
