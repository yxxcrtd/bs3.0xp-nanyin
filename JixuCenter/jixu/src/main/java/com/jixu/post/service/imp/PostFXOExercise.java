package com.jixu.post.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.SecContract;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostFXOExercise extends PostServiceImpl{
	
	@Resource(name = "fxTranPost")
	private PostService postFXTran;
	
	@Autowired
	private SecContractDao secContractDao;
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		String secacct=tranAdvice.getSecacct();
		
		SecContract sc=secContractDao.loadSecContract(tranAdvice.getCusip().getId());
		double amount_long = sc.getLongCash();
		double amount_short = sc.getShortCash();
		double fxrate_long = tranAdvice.getFxrate();
		double fxrate_short = tranAdvice.getFxrate2();
		
		IssueInfo issueInfo =tranAdvice.getCusip();
		
		//行权      通过lotnum取到需要行权的那笔交易的头寸
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
		tran1.setSecacct(secacct);
		tran1.setIntps(tranAdvice.getIntatmat());
		tran1.setManagerID(tranAdvice.getManager());
		tsumExercise=CaltSum(tran1, tsumExercise);
		tranDao.saveEntity(tran1);
		tranSumDao.updateEntity(tsumExercise);
		
		
		//换汇
		Tran tran2 = new Tran();
		TranSum tranSum = new TranSum();
		double cost_func_long = RoundUtil.roundMoney(amount_long * fxrate_long, 2);
		double cost_func_short = RoundUtil.roundMoney(amount_short * fxrate_short, 2);
		int lotnum=tranSumDao.findMaxLotNumByFxTranFlag(tranAdvice.getPortfolio(),1);
		tran2.setPortfolio(tranAdvice.getPortfolio());
		tran2.setLotnum(lotnum+1);
		tran2.setTrannum(1);
		tran2.setTrantype(TransTypes.FORWORD_CURRENCY);
		tran2.setTanum(tranAdvice);
		tran2.setTradeDate(tranAdvice.getTradedate());
		tran2.setSettleDate(tranAdvice.getSettledate());
		tran2.setLedgerDate(tranAdvice.getLedgerdate());
		tran2.setProceeds(0);
		//卖出就相当于买入一笔负头寸    交易界面上的多方和空方是相反的
		if(tsumExercise.getAmount()<0){
			tran2.setAmount(amount_short);
			tran2.setgCost(amount_short);
			tran2.setgCostFunc(cost_func_short);
			tran2.setgOriginalamt(amount_short);
			tran2.setPrinFxrate(fxrate_short);
			tran2.setIncFxrate(fxrate_short);
			tran2.setgCost2(amount_long);
			tran2.setgCostFunc2(cost_func_long);
			tran2.setAmount2(amount_long);
			tran2.setgOriginalamt2(amount_long);
			tran2.setPrinFxrate2(fxrate_long);
			tran2.setIncFxrate2(fxrate_long);
		}else{
			tran2.setAmount(amount_long);
			tran2.setgCost(amount_long);
			tran2.setgCostFunc(cost_func_long);
			tran2.setgOriginalamt(amount_long);
			tran2.setPrinFxrate(fxrate_long);
			tran2.setIncFxrate(fxrate_long);
			tran2.setgCost2(amount_short);
			tran2.setgCostFunc2(cost_func_short);
			tran2.setAmount2(amount_short);
			tran2.setgOriginalamt2(amount_short);
			tran2.setPrinFxrate2(fxrate_short);
			tran2.setIncFxrate2(fxrate_short);
		}
		tranSum=postFXTran.CaltSum(tran2, tranSum);
		tranDao.saveEntity(tran2);
		tranSumDao.saveEntity(tranSum);
		
		return null;
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
		String tanum = tranAdvice.getId();
		String portfolio=tranAdvice.getPortfolio();
		List<Tran> transList = tranDao.loadTransByTanum(tanum);
		for (Tran tran : transList) {
			if(tran.getTrantype()==TransTypes.FORWORD_CURRENCY){
				TranSum tSum = tranSumDao.loadFxTranTSum(portfolio, tran.getAmount(), tran.getAmount2(), tran.getTradeDate(), tran.getSettleDate());
				if(tSum!=null){
					tranSumDao.deleteEntity(tSum);
				}
			}else{
				String portnum = tran.getPortfolio();
				String cusip = tran.getCusip().getId();
				int lotnum = tran.getLotnum();
				TranSum tSum = tranSumDao.loadTSumByLot(portnum, cusip, lotnum);
				tSum = unpostCaltsum(tran, tSum);
				if (tSum.getLstTrannum() == 0) {
					tranSumDao.deleteEntity(tSum);
				} else {
					tranSumDao.updateEntity(tSum);
				}
			}
			if (tran != null) {
				tranDao.deleteEntity(tran);
			}
		}
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) { 
		double tsumAmount = tran.getAmount();
		double tsumG_Cost = tran.getgCost();
		double tsumG_CostFunc = tran.getgCostFunc();

		if (tran.getTrannum()>0) {
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
