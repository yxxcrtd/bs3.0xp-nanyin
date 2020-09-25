package com.jixu.post.service.imp;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.FxTranType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostForwardCurrencyClose extends PostServiceImpl{
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		IssueInfo cusip=tran.getCusip();
		double tsumAmount_long = tran.getAmount();
		double tsumAmount_short = tran.getAmount2();
		
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setFxTranFlg(FxTranType.fxTran);
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumClosed);
		tSum.setCusip(tran.getCusip().getId());
		//空方估值
		if(cusip.getSwapExchangeInd()==ValuationType.VALSIDE_SHORT) {
			tSum.setAmount(tSum.getAmount()+tsumAmount_long);
			tSum.setAmount2(tSum.getAmount2()-tsumAmount_short);
		}else {
			tSum.setAmount(tSum.getAmount()-tsumAmount_short);
			tSum.setAmount2(tSum.getAmount2()-tsumAmount_long);
		}

		return tSum;
	}
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		Date tatradeDate = tranAdvice.getTradedate();
		Date tasettleDate = tranAdvice.getSettledate();
		Date taledgerdate = tranAdvice.getLedgerdate();
		
		
		double amount_long = tranAdvice.getAmount();
		double amount_short = tranAdvice.getAmount2();
		double fxrate_long = tranAdvice.getFxrate();
		double fxrate_short = tranAdvice.getFxrate2();
		
		Tran tran = new Tran();
		String portfolio=tranAdvice.getPortfolio();
		String cusip=tranAdvice.getCusip().getId();
		
		TranSum tSum = tranSumDao.loadTranSumByCusip(portfolio, cusip);
		
		// Set Transaction data
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setLotnum(tSum.getLotnum());
		tran.setTrannum(tSum.getLstTrannum()+1);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tatradeDate);
		tran.setSettleDate(tasettleDate);
		tran.setLedgerDate(taledgerdate);
		tran.setAmount(amount_long);
		tran.setProceeds(0);
		tran.setPrinFxrate(fxrate_long);
		tran.setIncFxrate(fxrate_long);
		
		tran.setAmount2(amount_short);
		tran.setProceeds2(0);
		tran.setPrinFxrate2(fxrate_short);
		tran.setIncFxrate2(fxrate_short);
		tran.setCusip(tranAdvice.getCusip());
		
		tSum=CaltSum(tran, tSum);

		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tSum);
		
		return psm;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String portfolio = tranAdvice.getPortfolio();
		String tanum=tranAdvice.getId();
		String cusip=tranAdvice.getCusip().getId();
		
		Tran tran=tranDao.loadTranByTanum(tanum);
		if(tran!=null){
			TranSum tSum = tranSumDao.loadTranSumByCusip(portfolio, cusip);
			PostServiceModel psm=new PostServiceModel();
			tSum=unpostCaltsum(tran, tSum);
			psm.setTran(tran);
			psm.setTranSum(tSum);
			return psm;
		}else{
			return null;
		}
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		IssueInfo cusip=tran.getCusip();
		double tsumAmount = 0;
		double tsumAmount2 = 0;
		//空方估值
		if(cusip.getSwapExchangeInd()==ValuationType.VALSIDE_SHORT) {
			tsumAmount=	RoundUtil.roundMoney2Dec(tSum.getAmount()-tran.getAmount());
			tsumAmount2 = RoundUtil.roundMoney2Dec(tSum.getAmount2()+tran.getAmount2());
		}else {
			tsumAmount=	RoundUtil.roundMoney2Dec(tSum.getAmount()+tran.getAmount2());
			tsumAmount2 = RoundUtil.roundMoney2Dec(tSum.getAmount2()+tran.getAmount());
		}
		
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setAmount2(tsumAmount2);
		return tSum;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		String portfolio=tranBasic.getPortfolio();
		MsgContainer msgcontainer=(MsgContainer) container;
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=(String) fieldsHM.get(MsgFields.operation);
		
		String cusip=tranBasic.getCusip().getId();
		TranSum tSum = tranSumDao.loadTranSumByCusip(portfolio, cusip);
		//开仓交易平仓后不可以被再次交易    但可以修改
		if(tSum.getLstTrannum()>1 && operation.equalsIgnoreCase(MsgFields.add)){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.contract_already_deal);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.contract_already_deal));
		}
	}
}
