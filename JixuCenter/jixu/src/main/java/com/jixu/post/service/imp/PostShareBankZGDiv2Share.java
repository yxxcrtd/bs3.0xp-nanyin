package com.jixu.post.service.imp;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.NavBankZiGuanDao;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.NavBankZiGuan;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSubRed;
import com.orm.obj.TranSum;

@Service
public class PostShareBankZGDiv2Share extends PostServiceImpl{
	
	@Resource(name="subRedPostImp")
	private PostService subRedPost;
	
	@Autowired
	private NavBankZiGuanDao navBankZiGuanDao;
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {

		subRedPost.post(tranAdvice, container);
		if(!container.isCorrectFlag())
			return null;
		
		double amount = tranAdvice.getAmount();
		String portnum=tranAdvice.getPortfolio();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		//获取前一天的银行资管批次头寸
		List<NavBankZiGuan> navZGList=navBankZiGuanDao.getNavBankZiGuanList(portnum, portfolio.getLastGLClose());
		
		//获取总的份额，用每个批次占总份额的比率，来计算红利转份额的数量中的占比
		double totalShares=0;
		for(NavBankZiGuan pc:navZGList){
			totalShares=RoundUtil.roundMoney2Dec(totalShares+pc.getSharesInternal());
		}
		
		for(NavBankZiGuan pc:navZGList){
			Tran tran = new Tran();
			IssueInfo sec=issueInfoDao.loadIssueByCusip(pc.getCusip());
			TranSum tranSum=tranSumDao.getMapedRepoTranSum(portnum, pc.getCusip(), pc.getLotnum());
			
			//用剩余的占比来计算批次分得的数量
			double pcAmount=RoundUtil.roundMoney2Dec(amount*pc.getSharesInternal()/totalShares);
			
			tran.setCusip(sec);
			tran.setPortfolio(pc.getPortnum());
			tran.setLotnum(pc.getLotnum());
			tran.setTrannum(tranSum.getLstTrannum()+1);
			tran.setTrantype(TransTypes.Share_Div2Share);
			tran.setTanum(tranAdvice);
			tran.setTradeDate(tranAdvice.getTradedate());
			tran.setSettleDate(tranAdvice.getSettledate());
			tran.setLedgerDate(tranAdvice.getLedgerdate());
			
			tran.setAmount(pcAmount);
			tran.setPrinFxrate(0);
			tran.setIncFxrate(0);
			tran.setgCost(0);
			tran.setgCostFunc(0);
			tran.setgOriginalamt(0);
			tran.setProceeds(0);
			tran.setIntps(0);
			
			amount=RoundUtil.roundMoney2Dec(amount-pcAmount);
			totalShares=RoundUtil.roundMoney2Dec(totalShares-pc.getSharesInternal());
			
			tranSum=CaltSum(tran, tranSum);
			tranDao.saveEntity(tran);
			tranSumDao.updateEntity(tranSum);
		}

		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setAmount(RoundUtil.roundMoney2Dec(tSum.getAmount()-tran.getAmount()));
		tSum.setPosclosed(PostStatusType.sumOpen);
		
		return tSum;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setAmount(RoundUtil.roundMoney2Dec(tSum.getAmount()+tran.getAmount()));
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum=closeTSum(tSum);
		return tSum;
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
