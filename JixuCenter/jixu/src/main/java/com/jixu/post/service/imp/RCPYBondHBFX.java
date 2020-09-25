package com.jixu.post.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranDao;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

@Service("rcpyBondHBFX")
public class RCPYBondHBFX extends RCPYServiceImpl {
	
	@Autowired
	private TranDao tranDao;
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		//还本付息是证券的一个公司行为，tranbasic只是一个总的比例，tran是根据每个头寸计算还本的金额
		//一条tranbasic可能对应了N条tran记录
		String dbcr="";
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		IssueInfo sec=tranAdvice.getCusip();
		
		List<Tran> tranList=tranDao.loadTransByTanum(tranAdvice.getId());
		
		for(Tran tran:tranList){
			BkCashRcpy bcr = new BkCashRcpy();
			bcr.setPortfolio(portfolio);
			bcr.setZero(0);

			dbcr=DBCR.CREDIT;
			
			double localCash=tran.getProceeds();
			double funcCash=RoundUtil.roundMoney2Dec(tran.getPrinFxrate()*tran.getProceeds());
			
			String secacctnum=tran.getSecacct();
			if(secacctnum.equals("0")){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.default_port_sec_notexist);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_port_sec_notexist));
				return;
			}
			BkCashAcct cashacct=bkCashAcctDao.loadCashAcctBySecAcct(secacctnum);
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
			bcr.setLedger(tran.getLedgerDate());
			bcr.setBkCashAcct(cashacct);
			bcr.setOrigbal(funcCash);
			bcr.setRemainbal(funcCash);
			bcr.setFxcurrency(tranAdvice.getFxcurrency());
			bcr.setFxorigbal(localCash);
			bcr.setFxremainbal(localCash);
			bcr.setUorigbal(tran.getAmount());
			bcr.setUremainbal(tran.getAmount());
			bcr.setFxrate(tran.getPrinFxrate());
			bcr.setTrantype(tran.getTrantype());
			bcr.setPaydate(tran.getSettleDate());
			bcr.setExpdcashdate(tran.getSettleDate());
			bcr.setBlockid(0);
			bcr.setMiscorigbal(0);
			bcr.setMiscremainbal(0);
			bcr.setRcpyDesc(tranAdvice.getTranDesc());
			
			String desc=SecurityCoreFactory.getAllTranTypeHM().get(tranAdvice.getTrantype()) + ": " + sec.getSecurityid();
			bcr.setRcpyDesc(desc);
			
			bkCashRcpyDao.saveEntity(bcr);
		}
	}
}
