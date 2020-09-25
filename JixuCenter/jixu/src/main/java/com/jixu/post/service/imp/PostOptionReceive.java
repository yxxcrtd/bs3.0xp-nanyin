package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostOptionReceive")
public class PostOptionReceive extends PostServiceImpl {
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//一次股利的公司行为,会影响所有证券账户下的头寸
		
		String portnum = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		String secacct=tranAdvice.getSecacct();

		PostServiceLotTranNumModel pltm=genLotTranNum(portnum, cusip, secacct, LongShortType.longTerm, true);
		TranSum tSum=pltm.getTsum();
		
		//查出所有证券账户下的头寸
		Tran tran = new Tran();
		tran.setCusip(tranAdvice.getCusip());
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setLotnum(pltm.getLotnum());
		tran.setTrannum(pltm.getTrannum());
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(tranAdvice.getAmount());
		tran.setSecacct(secacct);
		tran.setManagerID(tranAdvice.getManager());
		tSum=CaltSum(tran, tSum);
		
		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tSum);
		return psm;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		double tsumAmount = tran.getAmount();

		tsumAmount = RoundUtil.roundMoney2Dec(tSum.getAmount()-tsumAmount);
	
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		
		return tSum;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum){
		double tsumAmount = tran.getAmount();

		tSum.setId(tSum.getId());
		tsumAmount = tsumAmount + tSum.getAmount();

		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		
		return tSum;
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
