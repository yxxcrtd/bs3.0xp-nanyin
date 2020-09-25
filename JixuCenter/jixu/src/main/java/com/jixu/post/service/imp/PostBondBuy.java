package com.jixu.post.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.post.service.PostService;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostBondBuy extends PostServiceImpl{
	
	@Resource(name="slStockPurPostImp")
	private  PostService postSLStockPur;
	
	@Resource(name="repoLendPost")
	private  PostService postRepoLend;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		PostServiceModel psm=postTranUtil(tranAdvice, LongShortType.longTerm, true, container);
		return psm;
		//这里是以前买入公允价值法用单lot，摊余成本法用多lot的过程
		//先暂时采用全部单lot的做法
		
		/*PostServiceModel psm=new PostServiceModel();
		double taprincipal = tranAdvice.getPrincipal(); //该笔交易花费的成本
		double tafxrate = tranAdvice.getFxrate();
		String secacct=tranAdvice.getSecacct().getAcctID();
		String portnum = tranAdvice.getPortfolio();
		int cusip = tranAdvice.getCusip().getId();//获取issueinfo的id
		
		if(tranAdvice.getIntent()!=ValuationType.AMORTIZE){//标记  如果intent不是摊余成本，则使用股票的post
			int ttrannum = 0;
			//目前根据 投资组合+证券代码+券商帐号 几个key作为一个区分lot的key
			TranSum tSum = postDAO.loadTSumBondValuation(portnum, cusip, secacct);
			int maxlotnum=postDAO.findMaxLotNumByCusip(portnum, cusip);
			
			int lotnum=0;
			if (tSum == null) {
				lotnum=maxlotnum+1;
				ttrannum=1;
				tSum = new TranSum();
			} else {
				lotnum=tSum.getLotnum();
				ttrannum=tSum.getLstTrannum();
				ttrannum=ttrannum + 1;
			}

			double tranG_CostFunc = RoundUtil.roundMoney2Dec(taprincipal * tafxrate);

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
			tran.setgCost(taprincipal);
			tran.setgCostFunc(tranG_CostFunc);
			tran.setgOriginalamt(0);
			tran.setSecacct(secacct);
			tran.setIntps(tranAdvice.getIntatmat());
			if(tranAdvice.getManager()!=null){
				tran.setManagerID(tranAdvice.getManager().getId());
			}
			tSum=CaltSum(tran, tSum);
			psm.setTran(tran);
			psm.setTranSum(tSum);
			return psm;
		}else{
			//摊余成本债券买入 分多lot 每次买入都是一笔新的交易 trannum=1,lotnum+1
			psm=postTranUtil(tranAdvice, LongShortType.longTerm, false);
			psm.getTran().setAmorFlag(true);
			return psm;
		}
		*/
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum=caltSumUtil(tran, tSum);
		return tSum;
	}
	
	//因为每次买入都是一笔新的交易   所以撤账时需要删除tran和tsum中的该笔交易就行了
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
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
