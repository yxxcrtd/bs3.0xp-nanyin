package com.jixu.post.service.imp;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSubRed;
import com.orm.obj.TranSum;

@Service
public class PostShareBankZGRed extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taamount = tranAdvice.getAmount();
		double taprincipal = tranAdvice.getPrincipal();
		double tafxrate = tranAdvice.getFxrate();
		double intps=tranAdvice.getIntatmat();
		IssueInfo issueInfo =tranAdvice.getCusip();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());

		Tran tran = new Tran();
		TranSum tranSum=null;
		
		int lotnum=tranAdvice.getSpecificLotID();
		tranSum=tranSumDao.getMapedRepoTranSum(portfolio.getId(), issueInfo.getId(), lotnum);
		if(tranSum!=null){
			int trannum=tranSum.getLstTrannum();
			
			double tranG_Cost = 0;
			double tranG_CostFunc =0;
			
			if (tranSum.getAmount2() < taamount) {
				//现存的股数小于售出的股数
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.post_sale_zero);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_zero)+"-"+tranAdvice.getCusip().getId());
				return null;
			}
			
			double redRate=taamount/tranSum.getAmount2();
			//在资管的头寸里，amount是申购时占有的组合的份额
			double amount=RoundUtil.roundMoney2Dec(tranSum.getAmount()*redRate);
			tranG_Cost = RoundUtil.roundMoney2Dec(tranSum.getgCost()*redRate);
			tranG_CostFunc = RoundUtil.roundMoney(tranG_Cost*tranAdvice.getFxrate(), 2);
			
			tran.setCusip(issueInfo);
			tran.setPortfolio(portfolio.getId());
			tran.setLotnum(lotnum);
			tran.setTrannum(trannum+1);
			tran.setTrantype(tranAdvice.getTrantype());
			tran.setTanum(tranAdvice);
			tran.setTradeDate(tranAdvice.getTradedate());
			tran.setSettleDate(tranAdvice.getSettledate());
			tran.setLedgerDate(tranAdvice.getLedgerdate());
			tran.setAmount(amount);
			tran.setPrinFxrate(tafxrate);
			tran.setIncFxrate(tafxrate);
			tran.setgCost(tranG_Cost);
			tran.setgCostFunc(tranG_CostFunc);
			tran.setgOriginalamt(amount);
			tran.setAmount2(taamount);
			tran.setProceeds(taprincipal);
			tran.setProceeds2(tranAdvice.getPrincipal2());//用来存放绩效费
			tran.setIntps(intps);
				
			tranSum=CaltSum(tran, tranSum);
	
			PostServiceModel psm=new PostServiceModel();
			psm.setTran(tran);
			psm.setTranSum(tranSum);
			
			double redcash=Math.abs(taprincipal);			
			TranSubRed tranSubRed=new TranSubRed();
			tranSubRed.setPortfolio(portfolio);
			tranSubRed.setTanum(tranAdvice);
			tranSubRed.setTradeDate(tranAdvice.getTradedate());
			tranSubRed.setSettlementDate(tranAdvice.getSettledate());
			tranSubRed.setLedgerin(tranAdvice.getLedgerdate());
			tranSubRed.setEffectiveDate(tranAdvice.getSettledate());
			tranSubRed.setLedgerReversed(99999999);
			tranSubRed.setTranType(TransTypes.RED);
			tranSubRed.setAmount(Math.abs(amount));
			tranSubRed.setgCost(redcash);
			tranSubRed.setCash(redcash);
			tranSubRed.setNav(RoundUtil.roundMoney(redcash/amount,portfolio.getNavPrecision()));
			tranSubRed.setFxrate(tranAdvice.getFxrate());
			
			tranSubRedDao.saveEntity(tranSubRed);
			
			
			return psm;
		}else{
			return null;
		}
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		return unpostCaltsumSaleUtil(tran, tSum);
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return caltSumSaleUtil(tran, tSum);
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		reverseTranUtil(tranAdvice, container);
		
		Iterator<TranSubRed> tranSRIter=tranAdvice.getTranSubRed().iterator();
		while(tranSRIter.hasNext()){
			TranSubRed tran=tranSRIter.next();
			tranSubRedDao.deleteEntity(tran);
		}
		
		return null;	
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
