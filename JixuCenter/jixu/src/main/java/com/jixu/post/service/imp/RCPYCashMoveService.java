package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		RCPYCashMovePostService
 * @author  		Pan Wei 
 * @Date 			2016年10月2日 下午11:23:57 	
 * @Description		[]  
 */

@Service("rcpyCashmoveImp")
public class RCPYCashMoveService extends RCPYServiceImpl{
	
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double longFxOrigbal = tranAdvice.getAmount();
		double longFxRemainbal = longFxOrigbal;
		double longFxRate = tranAdvice.getFxrate();
		double longOrigbal = RoundUtil.roundMoney2Dec(longFxOrigbal * longFxRate);
		double longRemainbal = longOrigbal;

		String longDbcr = DBCR.CREDIT;

		BkCashRcpy longrcpy = new BkCashRcpy();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		longrcpy.setPortfolio(portfolio);
		longrcpy.setZero(0);
		longrcpy.setDbcr(longDbcr);
		longrcpy.setPosttype(PostType.Transaction);
		longrcpy.setTanum(tranAdvice);
		longrcpy.setBkCashAcct(tranAdvice.getBkCashacctNo());
		longrcpy.setLedger(tranAdvice.getLedgerdate());
		longrcpy.setOrigbal(longOrigbal);
		longrcpy.setRemainbal(longRemainbal);
		longrcpy.setFxcurrency(tranAdvice.getFxcurrency());
		longrcpy.setFxorigbal(longFxOrigbal);
		longrcpy.setFxremainbal(longFxRemainbal);
		longrcpy.setUorigbal(tranAdvice.getAmount());
		longrcpy.setUremainbal(tranAdvice.getAmount());
		longrcpy.setFxrate(longFxRate);
		longrcpy.setTrantype(tranAdvice.getTrantype());
		longrcpy.setPaydate(tranAdvice.getSettledate());
		longrcpy.setExpdcashdate(tranAdvice.getSettledate());
		longrcpy.setBlockid(0);
		longrcpy.setMiscorigbal(0);
		longrcpy.setMiscremainbal(0);
		longrcpy.setRcpyDesc(tranAdvice.getTranDesc());

		bkCashRcpyDao.saveEntity(longrcpy);
		
		
		double shortFxOrigbal = tranAdvice.getAmount2();
		double shortFxRemainbal = shortFxOrigbal;
		double shortFxRate = tranAdvice.getFxrate2();
		double shortOrigbal = RoundUtil.roundMoney2Dec(shortFxOrigbal * shortFxRate);
		double shortRemainbal = shortOrigbal;

		String shortDbcr = DBCR.DEBIT;

		BkCashRcpy shortrcpy = new BkCashRcpy();
		shortrcpy.setPortfolio(portfolio);
		shortrcpy.setZero(0);
		shortrcpy.setDbcr(shortDbcr);
		shortrcpy.setPosttype(PostType.Transaction);
		shortrcpy.setTanum(tranAdvice);
		shortrcpy.setBkCashAcct(tranAdvice.getBkCashacctNo2());
		shortrcpy.setLedger(tranAdvice.getLedgerdate());
		shortrcpy.setOrigbal(shortOrigbal);
		shortrcpy.setRemainbal(shortRemainbal);
		shortrcpy.setFxcurrency(tranAdvice.getFxcurrency2());
		shortrcpy.setFxorigbal(shortFxOrigbal);
		shortrcpy.setFxremainbal(shortFxRemainbal);
		shortrcpy.setUorigbal(tranAdvice.getAmount2());
		shortrcpy.setUremainbal(tranAdvice.getAmount2());
		shortrcpy.setFxrate(shortFxRate);
		shortrcpy.setTrantype(tranAdvice.getTrantype());
		shortrcpy.setPaydate(tranAdvice.getSettledate());
		shortrcpy.setExpdcashdate(tranAdvice.getSettledate());
		shortrcpy.setBlockid(0);
		shortrcpy.setMiscorigbal(0);
		shortrcpy.setMiscremainbal(0);
		shortrcpy.setRcpyDesc(tranAdvice.getTranDesc());

		bkCashRcpyDao.saveEntity(shortrcpy);
	}
}
