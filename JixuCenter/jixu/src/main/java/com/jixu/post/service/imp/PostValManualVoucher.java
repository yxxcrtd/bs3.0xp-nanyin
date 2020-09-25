package com.jixu.post.service.imp;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.post.service.PostService;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostValManualVoucher extends PostServiceImpl {

	@Resource(name = "repoLendPost")
	private PostService postService1;

	@Resource(name="slStockPurPostImp")
	private  PostService postSLStockPur;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double tafxrate = tranAdvice.getFxrate();
		String secacct=tranAdvice.getSecacct();
		
		PostServiceLotTranNumModel pltmm=genLotTranNum(tranAdvice.getPortfolio(), tranAdvice.getCusip().getId(), secacct, LongShortType.longTerm, true);
		TranSum tSum=pltmm.getTsum();
		if(tSum.getValuationType()!=tranAdvice.getValuationType() && tSum.getLstTrannum()>1){
			container.setCorrectFlag(false);
			container.setErrorMsg("系统暂不支持同一个组合下的同一个证券，存在不同估值方法的批次");
			return null;
		}
		
		int lotnum=pltmm.getLotnum();
		int ttrannum=pltmm.getTrannum();
		
		double cost= tranAdvice.getPrincipal();
		double costFunc = tranAdvice.getPrincipal2();

		Tran tran = new Tran();
		tran.setCusip(tranAdvice.getCusip());
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setLotnum(lotnum);
		tran.setTrannum(ttrannum);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(tranAdvice.getAmount());
		tran.setPrinFxrate(tafxrate);
		tran.setIncFxrate(tafxrate);
		tran.setgCost(cost);
		tran.setgCostFunc(costFunc);
		tran.setSecacct(secacct);
		tran.setLsindicator(LongShortType.longTerm);
		tran.setIntps(tranAdvice.getIntatmat());
		tran.setValuationType(tranAdvice.getValuationType());
		tran.setManagerID(tranAdvice.getManager());
		
		tSum=CaltSum(tran, tSum);
		
		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tSum);
		return psm;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return null;
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		if(tSum==null){
			tSum=new TranSum();
		}
		//上一条注释是过去的需求,现在无论tranum为多少，都设值基本信息
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setSecacct(tran.getSecacct());		//TODO 证券账户
		tSum.setCusip(tran.getCusip().getId());
		tSum.setLotnum(tran.getLotnum());
		tSum.setLsindicator(tran.getLsindicator());
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setAmount(RoundUtil.roundMoney2Dec(tran.getAmount() + tSum.getAmount()));
		tSum.setgCost(RoundUtil.roundMoney2Dec(tran.getgCost() + tSum.getgCost()));
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tran.getgCostFunc() + tSum.getgCostfunc()));
		tSum.setAmount2(RoundUtil.roundMoney2Dec(tran.getAmount2() + tSum.getAmount2()));
		tSum.setValuationType(tran.getValuationType());
		
		return tSum;
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		Iterator<Tran> tranIter=tranAdvice.getTran().iterator();
		while(tranIter.hasNext()){
			Tran tran=tranIter.next();
			int lotnum=tran.getLotnum();
			String cusip = tran.getCusip().getId();
			TranSum tSum=tranSumDao.loadTSumByLot(tranAdvice.getPortfolio(), cusip, lotnum);
			if(tSum.getLstTrannum()==tran.getTrannum()) {
				tranSumDao.deleteEntity(tSum);
				tranDao.deleteEntity(tran);
			}else {
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.tran_exist_after_tran);
				container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.tran_exist_after_tran));
				return null;
			}
			
		}
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
