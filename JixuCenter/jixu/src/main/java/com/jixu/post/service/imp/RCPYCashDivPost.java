package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("RCPYCashDividendPost")
public class RCPYCashDivPost extends RCPYServiceImpl {
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		
		String dbcr="";
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		IssueInfo sec=tranAdvice.getCusip();
		
		//通过投资组合,证券信息反查交易   获取股票交易的现金账户作为分红账户
		BkCashRcpy bcr = new BkCashRcpy();
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		dbcr=DBCR.CREDIT;
		double alcash=tranAdvice.getCash();
		double funcCash=RoundUtil.roundMoney2Dec(alcash*tranAdvice.getFxrate());
		
		bcr.setDbcr(dbcr);
		bcr.setPosttype(tranAdvice.getTrantype());
		bcr.setTanum(tranAdvice);
		bcr.setCusip(sec);
		bcr.setLedger(tranAdvice.getLedgerdate());
		bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr.setOrigbal(funcCash);
		bcr.setRemainbal(funcCash);
		bcr.setFxcurrency(tranAdvice.getFxcurrency());
		bcr.setFxorigbal(alcash);
		bcr.setFxremainbal(alcash);
		bcr.setUorigbal(alcash);
		bcr.setUremainbal(alcash);
		bcr.setFxrate(tranAdvice.getFxrate());
		bcr.setTrantype(tranAdvice.getTrantype());
		bcr.setPaydate(tranAdvice.getSettledate());
		bcr.setExpdcashdate(tranAdvice.getSettledate());
		bcr.setBlockid(0);
		bcr.setMiscorigbal(0);
		bcr.setMiscremainbal(0);
		bcr.setRcpyDesc(tranAdvice.getTranDesc());
		
		String desc=SecurityCoreFactory.getAllTranTypeHM().get(tranAdvice.getTrantype()) + ": " + sec.getSecurityid();
		bcr.setRcpyDesc(desc);
		
		bkCashRcpyDao.saveEntity(bcr);
	}
}
