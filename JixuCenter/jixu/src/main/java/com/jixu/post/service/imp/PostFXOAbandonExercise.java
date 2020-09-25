package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostFXOAbandonExercise extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		
		IssueInfo issueInfo =tranAdvice.getCusip();
		//放弃行权      通过lotnum取到需要放弃行权的那笔交易的头寸
		int lot=tranAdvice.getSpecificLotID();
		TranSum tsumExercise=tranSumDao.loadTSumByCusipLot(tranAdvice.getPortfolio(), issueInfo.getId(),lot);
		if (tsumExercise == null) {
			//头寸不存在
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_sale_noSum);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_noSum));
			return null;
		}
		Tran  tran1=new Tran();
		tran1.setCusip(tranAdvice.getCusip());
		tran1.setPortfolio(tranAdvice.getPortfolio());
		tran1.setLotnum(lot);
		tran1.setTrannum(tsumExercise.getLstTrannum()+1);
		tran1.setTrantype(tranAdvice.getTrantype());
		tran1.setTanum(tranAdvice);
		tran1.setTradeDate(tranAdvice.getTradedate());
		tran1.setSettleDate(tranAdvice.getSettledate());
		tran1.setLedgerDate(tranAdvice.getLedgerdate());
		tran1.setAmount(tsumExercise.getAmount());
		tran1.setgCost(tsumExercise.getgCost());
		tran1.setgCostFunc(tsumExercise.getgCostfunc());
		tran1.setgOriginalamt(0);
		tran1.setManagerID(tranAdvice.getManager());
		tsumExercise=CaltSum(tran1, tsumExercise);
		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran1);
		psm.setTranSum(tsumExercise);
		return psm;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum.setLotnum(tran.getLotnum());
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setAmount(tSum.getAmount()-tran.getAmount());
		tSum.setgCost(RoundUtil.roundMoney2Dec(tSum.getgCost()-tran.getgCost()));
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tSum.getgCostfunc()-tran.getgCostFunc()));
		tSum=closeTSum(tSum);
		return tSum;
	}
	
	//删除交易
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) { 
		String portfolio = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		String tanum=tranAdvice.getId();
		
		Tran tran=tranDao.loadTranByTanum(tanum);
		if(tran!=null){
			int lotnum=tran.getLotnum();
			TranSum tSum = tranSumDao.loadTSumByLot(portfolio, cusip, lotnum);
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
		double tsumAmount = tran.getAmount();
		double tsumG_Cost = tran.getgCost();
		double tsumG_CostFunc = tran.getgCostFunc();

		if (tran.getTrannum()>1) {
			tsumAmount = tsumAmount + tSum.getAmount();
			tsumG_Cost = tsumG_Cost + tSum.getgCost();
			tsumG_CostFunc = tsumG_CostFunc + tSum.getgCostfunc();
		}
	
		tSum.setLstTrannum(tran.getTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setgCost(tsumG_Cost);
		tSum.setgCostfunc(tsumG_CostFunc);
		
		return tSum;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
