package com.jixu.post.service.imp;

import java.util.Date;
import java.util.Iterator;
import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.FxTranType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostForwardCurrency extends PostServiceImpl{
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		TranBasic tranBasic=tran.getTanum();
		double tsumAmount_long = tran.getAmount();
		double tsumAmount_short = tran.getAmount2();
		
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setLotnum(tran.getLotnum());
		tSum.setFxTranFlg(FxTranType.fxTran);
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setAmount(tsumAmount_long);
		
		tSum.setAmount2(tsumAmount_short);
		tSum.setAltRate(tranBasic.getAltRate());
		tSum.setAltDate(tranBasic.getAltDate());
		tSum.setCusip(tran.getCusip().getId());

		return tSum;
	}
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		IssueInfo issueInfo =tranAdvice.getCusip();
		Date tatradeDate = tranAdvice.getTradedate();
		Date tasettleDate = tranAdvice.getSettledate();
		Date taledgerdate = tranAdvice.getLedgerdate();
		
		double amount_long = tranAdvice.getAmount();
		double amount_short = tranAdvice.getAmount2();
		double fxrate_long = tranAdvice.getFxrate();
		double fxrate_short = tranAdvice.getFxrate2();
		
		Tran tran = new Tran();
		TranSum tranSum=null;
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		//多空方即期汇率
		double priFxRate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur(), tranAdvice.getTradedate());
		double priFxRate2 = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getPriCur(), tranAdvice.getTradedate());
		// Set Transaction data
		//ttranum 记录总的第几笔交易
		int lotnum=tranSumDao.findMaxLotNumByFxTranFlag(tranAdvice.getPortfolio(),1);
		TranSum tsum=tranSumDao.loadTranSumByCusip(tranAdvice.getPortfolio(), issueInfo.getId());
		if(tsum==null) {
			tranSum = new TranSum();
			tran.setLotnum(lotnum+1);
			tran.setTrannum(1);
		}else {
			tranSum=tsum;
			tran.setLotnum(lotnum);
			tran.setTrannum(tsum.getLstTrannum()+1);
		}
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tatradeDate);
		tran.setSettleDate(tasettleDate);
		tran.setLedgerDate(taledgerdate);
		tran.setProceeds(0);
		tran.setProceeds2(0);
		tran.setCusip(tranAdvice.getCusip());
		if(amount_short<0){
			tran.setAmount(amount_short);
			tran.setPrinFxrate(priFxRate2);
			tran.setIncFxrate(fxrate_short);
			tran.setAmount2(amount_long);
			tran.setPrinFxrate2(priFxRate);
			tran.setIncFxrate2(fxrate_long);
		}else{
			tran.setAmount(amount_long);
			tran.setPrinFxrate(priFxRate);
			tran.setIncFxrate(fxrate_long);
			tran.setAmount2(amount_short);
			tran.setPrinFxrate2(priFxRate2);
			tran.setIncFxrate2(fxrate_short);
		}
		tranSum=CaltSum(tran, tranSum);

		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tranSum);
		
		return psm;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String taportfolio = tranAdvice.getPortfolio();
		
		Iterator<Tran> tranIter=tranAdvice.getTran().iterator();
		
		while(tranIter.hasNext()){
			Tran tran=tranIter.next();
			TranSum tSum=tranSumDao.loadTranSumByCusip(taportfolio, tranAdvice.getCusip().getId());
			if(tSum.getLstTrannum()==tran.getTrannum()) {
				tranSumDao.deleteEntity(tSum);
				tranDao.deleteEntity(tran);
			}else {
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.tranfw_exist_after_tranwfw);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.tranfw_exist_after_tranwfw));
				return null;
			}
		}
		
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
