package com.jixu.post.service.imp;

import java.util.Iterator;

import org.springframework.stereotype.Service;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostSwapPayInterest extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//利率互换付息
		double principal = tranAdvice.getPrincipal();
		double principal2 = tranAdvice.getPrincipal2();
		IssueInfo issueInfo =tranAdvice.getCusip();
		String portnum=tranAdvice.getPortfolio();
		
		double fxrate1=tranAdvice.getFxrate();
		double fxrate2=tranAdvice.getFxrate2();
		
		
		Tran tran = new Tran();
		TranSum tSum=tranSumDao.loadTranSumByPortCusip(portnum, issueInfo.getId(), LongShortType.longTerm);
		double tranG_CostFunc = RoundUtil.roundMoney(principal * fxrate1, 2);
		tran.setCusip(issueInfo);
		tran.setPortfolio(portnum);
		tran.setLotnum(tSum.getLotnum());
		tran.setTrannum(tSum.getLstTrannum()+1);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(tranAdvice.getAmount());
		tran.setProceeds(0);
		tran.setPrinFxrate(fxrate1);
		tran.setIncFxrate(fxrate1);
		tran.setgCost(principal);
		tran.setgCostFunc(tranG_CostFunc);
		tran.setgOriginalamt(tranAdvice.getAmount());
		tran.setIntps(tranAdvice.getIntatmat());
		tran.setLsindicator(tSum.getLsindicator());
		tran.setManagerID(tranAdvice.getManager());
		
		tSum=CaltSum(tran, tSum);

		tranDao.saveEntity(tran);
		tranSumDao.updateEntity(tSum);
		

		// 先处理股票的增加---------------------
		double tranG_CostFunc2 = RoundUtil.roundMoney2Dec(principal2 * fxrate2);

		Tran tran2 = new Tran();
		TranSum tSum2=tranSumDao.loadTranSumByPortCusip(portnum, issueInfo.getId(), LongShortType.ShortTerm);;
		tran2.setCusip(issueInfo);
		tran2.setPortfolio(portnum);
		tran2.setLotnum(tSum2.getLotnum());
		tran2.setTrannum(tSum2.getLstTrannum()+1);
		tran2.setTrantype(tranAdvice.getTrantype());
		tran2.setTanum(tranAdvice);
		tran2.setTradeDate(tranAdvice.getTradedate());
		tran2.setSettleDate(tranAdvice.getSettledate());
		tran2.setLedgerDate(tranAdvice.getLedgerdate());
		tran2.setAmount(tranAdvice.getAmount2());
		tran2.setProceeds(0);
		tran2.setPrinFxrate(fxrate2);
		tran2.setIncFxrate(fxrate2);
		tran2.setgCost(principal2);
		tran2.setgCostFunc(tranG_CostFunc2);
		tran2.setgOriginalamt(tranAdvice.getAmount2());
		tran2.setIntps(tranAdvice.getIntatmat2());
		tran2.setLsindicator(tSum2.getLsindicator());
		tran2.setManagerID(tranAdvice.getManager());
		tSum2=CaltSum(tran2, tSum2);

		tranDao.saveEntity(tran2);
		tranSumDao.updateEntity(tSum2);
		
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		return tSum;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum.setLstTrannum(tran.getTrannum());
		return tSum;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		Iterator<Tran> tranIter=tranAdvice.getTran().iterator();
		while(tranIter.hasNext()){
			Tran tran=tranIter.next();
			TranSum tSum=tranSumDao.loadTranSumByPortCusip(tranAdvice.getPortfolio(), tranAdvice.getCusip().getId(), tran.getLsindicator());
			tSum=unpostCaltsum(tran,tSum);
			tranDao.deleteEntity(tran);
			tranSumDao.updateEntity(tSum);
		}
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
