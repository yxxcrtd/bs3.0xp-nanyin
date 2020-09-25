package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpySwapPayInterest")
public class RCPYSwapPayInterest extends RCPYServiceImpl {

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		double fxorigbal3 = RoundUtil.roundMoney2Dec(tranAdvice.getIntatmat());
		double fxremainbal3 = fxorigbal3;
		double fxrate3 = tranAdvice.getSettleFxRate();
		double origbal3 = RoundUtil.roundMoney2Dec(fxorigbal3 * fxrate3);
		double remainbal3 = origbal3;
		//收入端利息
		if(fxremainbal3!=0) {
			BkCashRcpy bcr3 = new BkCashRcpy();
			bcr3.setZero(0);
			if(fxremainbal3>0){
				bcr3.setDbcr(DBCR.CREDIT);
			}else{
				bcr3.setDbcr(DBCR.DEBIT);
			}
			bcr3.setPosttype(PostType.Income);
			bcr3.setTanum(tranAdvice);
			bcr3.setCusip(tranAdvice.getCusip());
			bcr3.setPortfolio(portfolio);
			bcr3.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr3.setLedger(tranAdvice.getLedgerdate());
			bcr3.setOrigbal(Math.abs(origbal3));
			bcr3.setRemainbal(Math.abs(remainbal3));
			bcr3.setFxcurrency(tranAdvice.getSettleCurrency());
			bcr3.setFxorigbal(Math.abs(fxorigbal3));
			bcr3.setFxremainbal(Math.abs(fxremainbal3));
			bcr3.setUorigbal(Math.abs(tranAdvice.getAmount()));
			bcr3.setUremainbal(Math.abs(tranAdvice.getAmount()));
			bcr3.setFxrate(fxrate3);
			bcr3.setTrantype(tranAdvice.getTrantype());
			bcr3.setPaydate(tranAdvice.getSettledate());
			bcr3.setExpdcashdate(tranAdvice.getSettledate());
			bcr3.setBlockid(0);
			bcr3.setMiscorigbal(0);
			bcr3.setMiscremainbal(0);
			bcr3.setRcpyDesc(tranAdvice.getTranDesc());
	
			bkCashRcpyDao.saveEntity(bcr3);
		}
		
		double fxorigbal4 = RoundUtil.roundMoney2Dec(tranAdvice.getIntatmat2());
		double fxremainbal4 = fxorigbal4;
		double fxrate4 = tranAdvice.getSettleFxRate2();
		double origbal4 = RoundUtil.roundMoney2Dec(fxorigbal4 * fxrate4);
		double remainbal4 = origbal4;
		//收入端利息
		if(fxremainbal4!=0) {
			BkCashRcpy bcr4 = new BkCashRcpy();
			bcr4.setZero(0);
			if(fxremainbal4>0){
				bcr4.setDbcr(DBCR.DEBIT);
			}else{
				bcr4.setDbcr(DBCR.CREDIT);
			}
			bcr4.setPosttype(PostType.Income);
			bcr4.setTanum(tranAdvice);
			bcr4.setCusip(tranAdvice.getCusip());
			bcr4.setPortfolio(portfolio);
			bcr4.setBkCashAcct(tranAdvice.getBkCashacctNo2());
			bcr4.setLedger(tranAdvice.getLedgerdate());
			bcr4.setOrigbal(Math.abs(origbal4));
			bcr4.setRemainbal(Math.abs(remainbal4));
			bcr4.setFxcurrency(tranAdvice.getSettleCurrency());
			bcr4.setFxorigbal(Math.abs(fxorigbal4));
			bcr4.setFxremainbal(Math.abs(fxremainbal4));
			bcr4.setUorigbal(Math.abs(tranAdvice.getAmount2()));
			bcr4.setUremainbal(Math.abs(tranAdvice.getAmount2()));
			bcr4.setFxrate(fxrate4);
			bcr4.setTrantype(tranAdvice.getTrantype());
			bcr4.setPaydate(tranAdvice.getSettledate());
			bcr4.setExpdcashdate(tranAdvice.getSettledate());
			bcr4.setBlockid(0);
			bcr4.setMiscorigbal(0);
			bcr4.setMiscremainbal(0);
			bcr4.setRcpyDesc(tranAdvice.getTranDesc());
	
			bkCashRcpyDao.saveEntity(bcr4);
		}
	}
}
