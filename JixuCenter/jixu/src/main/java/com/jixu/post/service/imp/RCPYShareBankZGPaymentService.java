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

/** 
 * @ClassName 		RCPYCashMovePostService
 * @author  		Pan Wei 
 * @Date 			2016年10月2日 下午11:23:57 	
 * @Description		[]  
 */

@Service("rcpyShareBankZGPaymentService")
public class RCPYShareBankZGPaymentService extends RCPYServiceImpl{
	public String dbcr=DBCR.CREDIT;
	
	@Override
	public void postToRcpy(TranBasic tranAdvice, ResultContainer container) {
		double fxorigbal = tranAdvice.getPrincipal();
		double fxremainbal = fxorigbal;
		double fxrate = tranAdvice.getSettleFxRate();
		double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
		double remainbal = origbal;
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		
		if(fxorigbal!=0){
			//资管费用结转
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
			bcr.setFxcurrency(tranAdvice.getCusip().getPriCur());
			bcr.setFxorigbal(Math.abs(fxorigbal));
			bcr.setFxremainbal(Math.abs(fxremainbal));
			bcr.setUorigbal(Math.abs(tranAdvice.getAmount()));
			bcr.setUremainbal(Math.abs(tranAdvice.getAmount()));
			bcr.setFxrate(fxrate);
			bcr.setTrantype(TransTypes.ShareBankZGPayment);
			bcr.setPaydate(tranAdvice.getSettledate());
			bcr.setExpdcashdate(tranAdvice.getSettledate());
			bcr.setBlockid(0);
			bcr.setMiscorigbal(0);
			bcr.setMiscremainbal(0);
			bcr.setRcpyDesc("资管应付费用");
	
			bkCashRcpyDao.saveEntity(bcr);
		}
	}
}
