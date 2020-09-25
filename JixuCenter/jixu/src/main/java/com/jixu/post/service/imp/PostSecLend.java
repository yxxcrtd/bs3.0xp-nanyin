package com.jixu.post.service.imp;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostSecLend extends PostServiceImpl {

	@Resource(name = "repoLendPost")
	private PostService postService1;

	@Resource(name = "slStockPurPostImp")
	private PostService postService2;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//融券的处理
		//每次融券，都生成一个新的lot  
		PostServiceModel model=postTranUtil(tranAdvice, LongShortType.longTerm, false, container);

		tranDao.saveEntity(model.getTran());
		tranSumDao.saveEntity(model.getTranSum());
		

		// 先处理股票的增加---------------------
		String portnum2 = tranAdvice.getPortfolio2();
		double principal2 = tranAdvice.getPrincipal2();
		double tafxrate2 = tranAdvice.getFxrate2();
		String cusip2 = tranAdvice.getCusip2().getId();
		String secacct2=tranAdvice.getSecacct2();

		PostServiceLotTranNumModel pstnm=genLotTranNum(portnum2, cusip2, secacct2, LongShortType.ShortTerm, true);
		TranSum tSum2=pstnm.getTsum();
		
		double tranG_CostFunc2 = RoundUtil.roundMoney2Dec(principal2 * tafxrate2);

		Tran tran2 = new Tran();
		tran2.setCusip(tranAdvice.getCusip2());
		tran2.setPortfolio(portnum2);
		tran2.setLotnum(pstnm.getLotnum());
		tran2.setTrannum(pstnm.getTrannum());
		tran2.setTrantype(tranAdvice.getTrantype());
		tran2.setTanum(tranAdvice);
		tran2.setTradeDate(tranAdvice.getTradedate());
		tran2.setSettleDate(tranAdvice.getSettledate());
		tran2.setLedgerDate(tranAdvice.getLedgerdate());
		tran2.setAmount(tranAdvice.getAmount2());
		tran2.setPrinFxrate(tafxrate2);
		tran2.setIncFxrate(tafxrate2);
		tran2.setgCost(principal2);
		tran2.setgCostFunc(tranG_CostFunc2);
		tran2.setgOriginalamt(tranAdvice.getAmount2());
		tran2.setSecacct(secacct2);
		tran2.setLsindicator(LongShortType.ShortTerm);
		tran2.setManagerID(tranAdvice.getManager());

		tSum2=CaltSum(tran2, tSum2);
		tranDao.saveEntity(tran2);
		tranSumDao.saveEntity(tSum2);
		
		return null;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		IssueInfo sec = tran.getCusip();
		if (sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
			return postService2.unpostCaltsum(tran, tSum);
		} else{
			return null;
		}
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		IssueInfo sec = tran.getCusip();
		if (sec.getIssueType().equals(AssetTypes.SEC_LENDING)) {
			return postService1.CaltSum(tran, tSum);
		} else {
			tSum=postService2.CaltSum(tran, tSum);
			return tSum;
		}
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		Iterator<Tran> tranIter=tranAdvice.getTran().iterator();
		while(tranIter.hasNext()){
			Tran tran=tranIter.next();
			int lotnum=tran.getLotnum();
			String cusip = tran.getCusip().getId();
			
			TranSum tSum=null;
			IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
			if (sec.getIssueType().equals(AssetTypes.SEC_LENDING)) {
				tSum=tranSumDao.getMapedRepoTranSum(tranAdvice.getPortfolio(), tranAdvice.getCusip().getId(),lotnum);
				tranSumDao.deleteEntity(tSum);
			}else{
				tSum = tranSumDao.loadTSumByLot(tranAdvice.getPortfolio(), tranAdvice.getCusip2().getId(), lotnum);
				tSum=unpostCaltsum(tran, tSum);
				
				if(tSum.getLstTrannum()==0){//撤账时如果上次交易次数是0,说明之前不存在该交易所以删除tsum中该笔交易
					tranSumDao.deleteEntity(tSum);
				}else{
					tranSumDao.updateEntity(tSum);//如果不为0,说明之前存在该交易  则更改tsum表中的交易次数和金额
				}
			}
			tranDao.deleteEntity(tran);
		}
		
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
