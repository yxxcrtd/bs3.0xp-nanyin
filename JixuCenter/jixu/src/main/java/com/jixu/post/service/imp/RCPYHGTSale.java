package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("RCPYHGTSale")
public class RCPYHGTSale extends RCPYServiceImpl {

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getSettleCash());
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;

		BkCashRcpy bcr = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
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
		bcr.setFxcurrency(tranAdvice.getBkCashacctNo().getFxcurrency());
		bcr.setFxorigbal(fxorigbal);
		bcr.setFxremainbal(fxremainbal);
		bcr.setUorigbal(tranAdvice.getAmount());
		bcr.setUremainbal(tranAdvice.getAmount());
		bcr.setFxrate(fxrate);
		bcr.setTrantype(tranAdvice.getTrantype());
		bcr.setPaydate(tranAdvice.getSettledate());
		bcr.setExpdcashdate(tranAdvice.getSettledate());
		bcr.setBlockid(0);
		bcr.setMiscorigbal(0);
		bcr.setMiscremainbal(0);
		bcr.setRcpyDesc("沪港通一级结算");

		bkCashRcpyDao.saveEntity(bcr);
		
		
		
		
		
		BkCashRcpy bcr2 = new BkCashRcpy();
		bcr2.setPortfolio(portfolio);
		bcr2.setZero(0);
		bcr2.setDbcr(DBCR.DEBIT);
		bcr2.setPosttype(PostType.Transaction);
		bcr2.setTanum(tranAdvice);
		bcr2.setCusip(tranAdvice.getCusip());
		bcr2.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr2.setLedger(tranAdvice.getLedgerdate());
		bcr2.setOrigbal(origbal);
		bcr2.setRemainbal(remainbal);
		bcr2.setFxcurrency(tranAdvice.getBkCashacctNo().getFxcurrency());
		bcr2.setFxorigbal(fxorigbal);
		bcr2.setFxremainbal(fxremainbal);
		bcr2.setUorigbal(tranAdvice.getAmount());
		bcr2.setUremainbal(tranAdvice.getAmount());
		bcr2.setFxrate(fxrate);
		bcr2.setTrantype(tranAdvice.getTrantype());
		bcr2.setPaydate(tranAdvice.getSettledate2());
		bcr2.setExpdcashdate(tranAdvice.getSettledate2());
		bcr2.setBlockid(0);
		bcr2.setMiscorigbal(0);
		bcr2.setMiscremainbal(0);
		bcr2.setRcpyDesc(tranAdvice.getTranDesc());
		
		
		BkCashRcpy bcr3 = new BkCashRcpy();
		bcr3.setPortfolio(portfolio);
		bcr3.setZero(0);
		bcr3.setDbcr(DBCR.CREDIT);
		bcr3.setPosttype(PostType.Transaction);
		bcr3.setTanum(tranAdvice);
		bcr3.setCusip(tranAdvice.getCusip());
		bcr3.setBkCashAcct(tranAdvice.getBkCashacctNo2());
		bcr3.setLedger(tranAdvice.getLedgerdate());
		bcr3.setOrigbal(origbal);
		bcr3.setRemainbal(remainbal);
		bcr3.setFxcurrency(tranAdvice.getBkCashacctNo2().getFxcurrency());
		bcr3.setFxorigbal(fxorigbal);
		bcr3.setFxremainbal(fxremainbal);
		bcr3.setUorigbal(tranAdvice.getAmount());
		bcr3.setUremainbal(tranAdvice.getAmount());
		bcr3.setFxrate(fxrate);
		bcr3.setTrantype(tranAdvice.getTrantype());
		bcr3.setPaydate(tranAdvice.getSettledate2());
		bcr3.setExpdcashdate(tranAdvice.getSettledate2());
		bcr3.setBlockid(0);
		bcr3.setMiscorigbal(0);
		bcr3.setMiscremainbal(0);
		bcr3.setRcpyDesc("沪港通二级结算");

		bkCashRcpyDao.saveEntity(bcr2);
		
		bkCashRcpyDao.saveEntity(bcr3);
	}
}
