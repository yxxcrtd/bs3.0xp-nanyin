package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("RCPYSahreCashDivPost")
public class RCPYSahreCashDivPost extends RCPYServiceImpl {

	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		String dbcr = "";
		String portnum = tranAdvice.getPortfolio();
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		IssueInfo sec = tranAdvice.getCusip();

		// 留接口,如果以后可以强制输入分红金额,则可以直接使用。目前是0，需要用比例计算
		double intatmat = tranAdvice.getIntatmat();
		double shares = tranAdvice.getAmount();
		double alcash = 0;
		
		if (intatmat > 0) {
			alcash=intatmat;
		} else {
			alcash = RoundUtil.roundMoney2Dec(shares* tranAdvice.getAltRate());
		}

		BkCashRcpy bcr = new BkCashRcpy();
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		if (tranAdvice.getAmount() > 0)
			dbcr=DBCR.CREDIT;
		else
			dbcr=DBCR.DEBIT;

		double funcCash = RoundUtil.roundMoney2Dec(alcash* tranAdvice.getFxrate());

		String secacctnum = portfolio.getDefaultSecAcct();
		if (secacctnum.equals("0")) {
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.default_cash_sec_notexist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_port_sec_notexist));
			return;
		}
		BkCashAcct cashacct = bkCashAcctDao.loadCashAcctBySecAcct(secacctnum);
		if(cashacct==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.default_cash_sec_notexist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_cash_sec_notexist));
			return;
		}

		bcr.setDbcr(dbcr);
		bcr.setPosttype(tranAdvice.getTrantype());
		bcr.setTanum(tranAdvice);
		bcr.setCusip(sec);
		bcr.setLedger(tranAdvice.getLedgerdate());
		bcr.setBkCashAcct(cashacct);
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

		String desc = SecurityCoreFactory.getAllTranTypeHM().get(tranAdvice.getTrantype()) + ": " + portfolio.getShortName();
		bcr.setRcpyDesc(desc);

		bkCashRcpyDao.saveEntity(bcr);

	}
}
