package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.OptionType;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		RCPYCashMovePostService
 * @author  		Pan Wei 
 * @Date 			2016年10月2日 下午11:23:57 	
 * @Description		[]  
 */

@Service("RCPYBond2StockService")
public class RCPYBond2StockService extends RCPYServiceImpl{
	
	private String dbcr=DBCR.CREDIT;
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		IssueInfo sec=tranAdvice.getCusip();
		double fxorigbal = RoundUtil.roundMoney2Dec(tranAdvice.getCash());
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getFxrate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;

		BkCashRcpy bcr = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		bcr.setPortfolio(portfolio);
		bcr.setZero(0);
		if(sec!=null&&sec.getOptionType()==OptionType.put)
			bcr.setDbcr(DBCR.DEBIT);
		else
			bcr.setDbcr(dbcr);
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
		bcr.setFxrate(tranAdvice.getFxrate());
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
