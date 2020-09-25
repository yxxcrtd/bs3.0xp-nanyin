package com.jixu.post.service.imp;

import java.util.Iterator;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSubRed;
import com.orm.obj.TranSum;

@Service
public class PostShareBankZG extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taprincipal = tranAdvice.getPrincipal();
		double tafxrate = tranAdvice.getFxrate();
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);

		double tranG_CostFunc = RoundUtil.roundMoney(taprincipal * tafxrate, 2);
		
		//以下是获取份额
		NavAssetObj navObj=navDao.getLatestNAV(portnum, tranAdvice.getTradedate());
		double subredCash=tranG_CostFunc;
		double shares=0;
		if(navObj.getShares()==0){
			shares=Math.abs(tranAdvice.getAmount());
		}else{
			shares=RoundUtil.roundMoney2Dec(subredCash/navObj.getNavPerShare());
		}
		
		PostServiceModel psm=postTranUtil(tranAdvice, LongShortType.longTerm, false, container);
		Tran tran=psm.getTran();
		TranSum tranSum=psm.getTranSum();
		
		tran.setAmount(shares);						//资管申赎的内部数量，是批次占产品内部的数量
		tran.setAmount2(tranAdvice.getAmount());	//资管申赎的外部数量，给银行资管系统看的
		//公用方法已经计算了一遍头寸   这个地方重复计算了  要注释掉
		//tranSum=CaltSum(tran, tranSum);
		
		tranDao.saveEntity(tran);
		tranSumDao.saveEntity(tranSum);
		
		//-----------------------------申购部分

		TranSubRed tranSubRed=new TranSubRed();
		tranSubRed.setPortfolio(portfolio);
		tranSubRed.setTanum(tranAdvice);
		tranSubRed.setTradeDate(tranAdvice.getTradedate());
		tranSubRed.setSettlementDate(tranAdvice.getSettledate());
		tranSubRed.setLedgerin(tranAdvice.getLedgerdate());
		tranSubRed.setEffectiveDate(tranAdvice.getSettledate());
		tranSubRed.setLedgerReversed(99999999);
		tranSubRed.setTranType(TransTypes.SUB);
		tranSubRed.setAmount(shares);
		tranSubRed.setgCost(Math.abs(taprincipal));
		tranSubRed.setCash(Math.abs(tranAdvice.getCash()));
		tranSubRed.setNav(RoundUtil.roundMoney(subredCash/shares,portfolio.getNavPrecision()));
		tranSubRed.setFxrate(tafxrate);
		
		tranSubRedDao.saveEntity(tranSubRed);
		return null;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum=caltSumUtil(tran, tSum);

		TranBasic tranBasic=tran.getTanum();
		tSum.setAltRate(tranBasic.getAltRate());
		tSum.setAltDate(tranBasic.getAltDate());
		
		return tSum;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		reverseTranUtil(tranAdvice, container);
		
		Iterator<TranSubRed> tranSRIter=tranAdvice.getTranSubRed().iterator();
		while(tranSRIter.hasNext()){
			TranSubRed tran=tranSRIter.next();
			tranDao.deleteEntity(tran);
		}
		
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return unpostCaltsumUtil(tran, tSum);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
