package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostBondWriteOffPrepare")
public class PostBondWriteOffPrepare extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taprincipal = tranAdvice.getPrincipal();
		IssueInfo issueInfo =tranAdvice.getCusip();
		//如果tranbasic没给投资组合 可以这样直接获取投资组合
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		//获取利率
		double tafxrate =tranAdvice.getFxrate() ;		
		
		//获取lotnum  通过lotnum取到tsum
		int lotnum=tranAdvice.getSpecificLotID();
		TranSum tsum=tranSumDao.loadTSumByCusipLot(portfolio.getId(), issueInfo.getId(), lotnum);
		
		Tran tran = new Tran();
		double tranG_CostFunc = RoundUtil.roundMoney(taprincipal * tafxrate, 2);
		tran.setCusip(issueInfo);
		tran.setPortfolio(portfolio.getId());
		tran.setLotnum(lotnum);//减值准备部分lot 
		tran.setTrannum(tsum.getLstTrannum()+1);//交易次数加1
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(0);
		tran.setProceeds(0);
		tran.setPrinFxrate(tafxrate);
		tran.setIncFxrate(tafxrate);
		tran.setgCost(taprincipal);
		tran.setgCostFunc(tranG_CostFunc);
		tran.setgOriginalamt(0);
		tran.setManagerID(tsum.getManagerID());
		tran.setSecacct(tsum.getSecacct());
		
		tsum=CaltSum(tran, tsum);

		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tsum);
		
		return psm;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum.setLstTrannum(tran.getTrannum());//减值准备  只是增加了一条tran记录   所以相应的tsum中交易次数也要增加1
		return tSum;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		tSum.setLstTrannum(tran.getTrannum()-1);//撤账  tsum中的交易次数减1.tran表的记录删除
		return tSum;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
