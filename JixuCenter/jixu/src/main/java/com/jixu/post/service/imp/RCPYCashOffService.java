package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("rcpyCashOff")
public class RCPYCashOffService extends RCPYServiceImpl {
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container){
		String dbcr="";
		
		BkCashRcpy bcr = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		if(tranAdvice.getAmount()>0)
			dbcr=DBCR.CREDIT;
		else
			dbcr=DBCR.DEBIT;
		
		bcr.setDbcr(dbcr);
		bcr.setPosttype(tranAdvice.getTrantype());
		bcr.setTanum(tranAdvice);
		bcr.setLedger(tranAdvice.getLedgerdate());
		if(tranAdvice.getAmount()>0){
			double funcAmount=RoundUtil.roundMoney2Dec(tranAdvice.getCash()*tranAdvice.getFxrate());
			bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
			bcr.setOrigbal(funcAmount);
			bcr.setRemainbal(funcAmount);
			bcr.setFxcurrency(tranAdvice.getFxcurrency());
			bcr.setFxorigbal(tranAdvice.getCash());
			bcr.setFxremainbal(tranAdvice.getCash());
			bcr.setUorigbal(tranAdvice.getAmount());
			bcr.setUremainbal(tranAdvice.getAmount());
			bcr.setFxrate(tranAdvice.getFxrate());
		}else{
			double funcAmount2=RoundUtil.roundMoney2Dec(tranAdvice.getCash2()*tranAdvice.getFxrate2());
			bcr.setBkCashAcct(tranAdvice.getBkCashacctNo2());
			bcr.setOrigbal(funcAmount2);
			bcr.setRemainbal(funcAmount2);
			bcr.setFxcurrency(tranAdvice.getFxcurrency2());
			bcr.setFxorigbal(tranAdvice.getCash2());
			bcr.setFxremainbal(tranAdvice.getCash2());
			bcr.setUorigbal(tranAdvice.getAmount2());
			bcr.setUremainbal(tranAdvice.getAmount2());
			bcr.setFxrate(tranAdvice.getFxrate2());
		}
		bcr.setTrantype(tranAdvice.getTrantype());
		bcr.setPaydate(tranAdvice.getSettledate());
		bcr.setExpdcashdate(tranAdvice.getSettledate());
		bcr.setBlockid(0);
		bcr.setMiscorigbal(0);
		bcr.setMiscremainbal(0);
		bcr.setRcpyDesc(tranAdvice.getTranDesc());
		
		if(tranAdvice.getCusip()!=null) {
			bcr.setCusip(tranAdvice.getCusip());
		}

		bkCashRcpyDao.saveEntity(bcr);
	}
}
