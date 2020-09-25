package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyPurchaseService")
public class RCPYPurchaseService extends RCPYServiceImpl {

	public String dbcr=DBCR.DEBIT;

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getCash());
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;

		/*
		 * Posttype 1 Transaction 2 Income 3 Contribution/Withdrawal 4
		 * Withholding Tax Reclaimable 5 Inteportfolio Transfer 6 Forward
		 * Currency Contract 7 Bank Account Interest or Overdraft Fees 8 Fees 9
		 * Withholding Tax Not Reclaimable 10 Withholding Tax Relief 11 Fees -
		 * Income 12 Reclaimable Withholding Tax - Income 13 Non-Reclaimable
		 * Withholding Tax - Income 14 Withholding Tax Relief - Income
		 */

		BkCashRcpy bcr = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		if(fxremainbal>=0){
			bcr.setDbcr(dbcr);
		}else{
			bcr.setDbcr(DBCR.CREDIT);
		}
		bcr.setPosttype(PostType.Transaction);
		bcr.setTanum(tranAdvice);
		bcr.setCusip(tranAdvice.getCusip());
		bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr.setLedger(tranAdvice.getLedgerdate());
		bcr.setOrigbal(Math.abs(origbal));
		bcr.setRemainbal(Math.abs(remainbal));
		bcr.setFxcurrency(tranAdvice.getCusip().getPriCur());
		bcr.setFxorigbal(Math.abs(fxorigbal));
		bcr.setFxremainbal(Math.abs(fxremainbal));
		bcr.setUorigbal(Math.abs(tranAdvice.getAmount()));
		bcr.setUremainbal(Math.abs(tranAdvice.getAmount()));
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
}
