package com.jixu.post.service.imp;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("RCPYSecLendReturn")
public class RCPYSecLendReturn extends RCPYServiceImpl {
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = tranAdvice.getIntatmat();
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;
		
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		String cusip=tranAdvice.getCusip().getId();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		CashFlowCfg cashcfg=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
		Date setDate=incomeExpenseCal.getCpnMaturitySetDate(cashcfg, tranAdvice.getTradedate());

		/*
		 * Posttype 1 Transaction 2 Income 3 Contribution/Withdrawal 4
		 * Withholding Tax Reclaimable 5 Inteportfolio Transfer 6 Forward
		 * Currency Contract 7 Bank Account Interest or Overdraft Fees 8 Fees 9
		 * Withholding Tax Not Reclaimable 10 Withholding Tax Relief 11 Fees -
		 * Income 12 Reclaimable Withholding Tax - Income 13 Non-Reclaimable
		 * Withholding Tax - Income 14 Withholding Tax Relief - Income
		 */

		BkCashRcpy bcr = new BkCashRcpy();
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		bcr.setDbcr(DBCR.DEBIT);
		bcr.setPosttype(PostType.Transaction);
		bcr.setTanum(tranAdvice);
		bcr.setCusip(tranAdvice.getCusip());
		bcr.setBkCashAcct(tranAdvice.getBkCashacctNo());
		bcr.setLedger(tranAdvice.getLedgerdate());
		bcr.setOrigbal(origbal);
		bcr.setRemainbal(remainbal);
		bcr.setFxcurrency(tranAdvice.getCusip().getPriCur());
		bcr.setFxorigbal(fxorigbal);
		bcr.setFxremainbal(fxremainbal);
		bcr.setUorigbal(tranAdvice.getAmount());
		bcr.setUremainbal(tranAdvice.getAmount());
		bcr.setFxrate(fxrate);
		bcr.setTrantype(tranAdvice.getTrantype());
		bcr.setPaydate(setDate);
		bcr.setExpdcashdate(tranAdvice.getSettledate());
		bcr.setBlockid(0);
		bcr.setMiscorigbal(0);
		bcr.setMiscremainbal(0);
		bcr.setRcpyDesc("利息-"+sec.getSecurityid()+"-"+tranAdvice.getContract());

		bkCashRcpyDao.saveEntity(bcr);
	}
}
