package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyShareBankZGMaturityService")
public class RCPYShareBankZGMaturityService extends RCPYServiceImpl {
	
	@Override
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = tranAdvice.getPrincipal();
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		if(fxorigbal!=0){
			//本金结转
			BkCashRcpy bcr = new BkCashRcpy();
			bcr.setPortfolio(portfolio);
			bcr.setZero(0);
			if(fxremainbal>0){
				bcr.setDbcr(DBCR.DEBIT);
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
			bcr.setTrantype(TransTypes.RED);
			bcr.setPaydate(tranAdvice.getSettledate());
			bcr.setExpdcashdate(tranAdvice.getSettledate());
			bcr.setBlockid(0);
			bcr.setMiscorigbal(0);
			bcr.setMiscremainbal(0);
			bcr.setRcpyDesc("资管应付本金");
	
			bkCashRcpyDao.saveEntity(bcr);
		}
		
		//利息结转
		double fxorigbal2 = tranAdvice.getIntatmat();
		double fxremainbal2 = fxorigbal2;
		double origbal2 = RoundUtil.roundMoney2Dec(fxorigbal2 * fxrate);
		double remainbal2 = origbal2;
		
		if(fxorigbal2!=0){
			BkCashRcpy bcr2 = new BkCashRcpy();
			bcr2.setPortfolio(portfolio);
			bcr2.setZero(0);
			if(fxremainbal>0){
				bcr2.setDbcr(DBCR.DEBIT);
			}else{
				bcr2.setDbcr(DBCR.CREDIT);
			}
			bcr2.setPosttype(PostType.Transaction);
			bcr2.setTanum(tranAdvice);
			bcr2.setCusip(tranAdvice.getCusip());
			bcr2.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr2.setLedger(tranAdvice.getLedgerdate());
			bcr2.setOrigbal(Math.abs(origbal2));
			bcr2.setRemainbal(Math.abs(remainbal2));
			bcr2.setFxcurrency(tranAdvice.getCusip().getPriCur());
			bcr2.setFxorigbal(Math.abs(fxorigbal2));
			bcr2.setFxremainbal(Math.abs(fxremainbal2));
			bcr2.setUorigbal(Math.abs(fxorigbal2));
			bcr2.setUremainbal(Math.abs(fxorigbal2));
			bcr2.setFxrate(fxrate);
			bcr2.setTrantype(tranAdvice.getTrantype());
			bcr2.setPaydate(tranAdvice.getSettledate());
			bcr2.setExpdcashdate(tranAdvice.getSettledate());
			bcr2.setBlockid(0);
			bcr2.setMiscorigbal(0);
			bcr2.setMiscremainbal(0);
			bcr2.setRcpyDesc("资管应付收益");
			bkCashRcpyDao.saveEntity(bcr2);
		}
		
		//利息结转
		double fxorigbal3 = tranAdvice.getPrincipal2();
		double fxremainbal3 = fxorigbal3;
		double origbal3 = RoundUtil.roundMoney2Dec(fxorigbal3 * fxrate);
		double remainbal3 = origbal3;
		
		if(fxorigbal3>0){
			BkCashRcpy bcr3 = new BkCashRcpy();
			bcr3.setPortfolio(portfolio);
			bcr3.setZero(0);
			if(fxremainbal>0){
				bcr3.setDbcr(DBCR.DEBIT);
			}else{
				bcr3.setDbcr(DBCR.CREDIT);
			}
			bcr3.setPosttype(PostType.Transaction);
			bcr3.setTanum(tranAdvice);
			bcr3.setCusip(tranAdvice.getCusip());
			bcr3.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr3.setLedger(tranAdvice.getLedgerdate());
			bcr3.setOrigbal(Math.abs(origbal3));
			bcr3.setRemainbal(Math.abs(remainbal3));
			bcr3.setFxcurrency(tranAdvice.getCusip().getPriCur());
			bcr3.setFxorigbal(Math.abs(fxorigbal3));
			bcr3.setFxremainbal(Math.abs(fxremainbal3));
			bcr3.setUorigbal(Math.abs(fxorigbal3));
			bcr3.setUremainbal(Math.abs(fxorigbal3));
			bcr3.setFxrate(fxrate);
			bcr3.setTrantype(tranAdvice.getTrantype());
			bcr3.setPaydate(tranAdvice.getSettledate());
			bcr3.setExpdcashdate(tranAdvice.getSettledate());
			bcr3.setBlockid(0);
			bcr3.setMiscorigbal(0);
			bcr3.setMiscremainbal(0);
			bcr3.setRcpyDesc("资管绩效费");
			bkCashRcpyDao.saveEntity(bcr3);
		}
	}
}
