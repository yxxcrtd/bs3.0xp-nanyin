package com.jixu.post.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.jixu.post.service.PostToRCPYService;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpySwapFXMaturity")
public class RCPYSwapFXMaturity extends RCPYServiceImpl {
	
	@Resource(name="rcpySwapPayInterest")
	private PostToRCPYService postService;

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getCash());
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		//收入端本金
		if(fxremainbal!=0) {
			BkCashRcpy bcr = new BkCashRcpy();
			bcr.setPortfolio(portfolio);
			bcr.setZero(0);
			bcr.setDbcr(DBCR.DEBIT);
			bcr.setPosttype(PostType.Transaction);
			bcr.setTanum(tranAdvice);
			bcr.setCusip(tranAdvice.getCusip());
			bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr.setLedger(tranAdvice.getLedgerdate());
			bcr.setOrigbal(Math.abs(origbal));
			bcr.setRemainbal(Math.abs(remainbal));
			bcr.setFxcurrency(tranAdvice.getSettleCurrency());
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
		
		//支出端本金
		BkCashRcpy bcr2 = new BkCashRcpy();
		
		double fxorigbal2 = RoundUtil.roundMoney2Dec(tranAdvice.getCash2());
		double fxremainbal2 = fxorigbal2;
		double fxrate2 = tranAdvice.getSettleFxRate2();
		double origbal2 = RoundUtil.roundMoney2Dec(fxorigbal2 * fxrate2);
		double remainbal2 = origbal2;
		String currency2=tranAdvice.getSettleCurrency2();
		
		if(fxremainbal2!=0) {
			bcr2.setPortfolio(portfolio);
			bcr2.setZero(0);
			bcr2.setDbcr(DBCR.CREDIT);
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
			bcr2.setUorigbal(Math.abs(tranAdvice.getAmount2()));
			bcr2.setUremainbal(Math.abs(tranAdvice.getAmount2()));
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
		
		postService.postRcpy(tranAdvice, container);
	}
}
