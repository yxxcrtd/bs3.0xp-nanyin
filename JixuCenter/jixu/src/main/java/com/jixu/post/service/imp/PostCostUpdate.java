package com.jixu.post.service.imp;


import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostCostUpdate extends PostServiceImpl{
		
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taprincipal = tranAdvice.getPrincipal();
		IssueInfo issueInfo =tranAdvice.getCusip();
		
		//如果tranbasic没给投资组合 可以这样直接获取投资组合
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		//获取汇率
		double tafxrate = tranAdvice.getFxrate();
		
		//获取lotnum  通过lotnum取到tsum
		int lotnum=tranAdvice.getSpecificLotID();
		TranSum tsum=tranSumDao.loadTSumByCusipLot(portfolio.getId(), issueInfo.getId(), lotnum);
		double tranG_CostFunc = RoundUtil.roundMoney(taprincipal * tafxrate, 2);
		
		
		Tran tran = new Tran();
		tran.setCusip(issueInfo);
		tran.setPortfolio(portfolio.getId());
		tran.setLotnum(lotnum);
		tran.setTrannum(tsum.getLstTrannum()+1);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(tranAdvice.getAmount());
		tran.setProceeds(0);
		tran.setPrinFxrate(tafxrate);
		tran.setIncFxrate(tafxrate);
		tran.setgCost(RoundUtil.roundMoney2Dec(tsum.getgCost()-taprincipal));
		tran.setgCostFunc(RoundUtil.roundMoney2Dec(tsum.getgCostfunc()-tranG_CostFunc));
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
		tSum.setLotnum(tran.getLotnum());
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setgCost(RoundUtil.roundMoney2Dec(tSum.getgCost()-tran.getgCost()));
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tSum.getgCostfunc()-tran.getgCostFunc()));
		return tSum;
	}
	
	//删除交易
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) { 
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		//成本调整   撤销
		tSum.setLstTrannum(tran.getTrannum()-1);
		tSum.setLsttradeDate(tran.getTradeDate()); 
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setgCost(RoundUtil.roundMoney2Dec(tSum.getgCost()+tran.getgCost())); 
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tSum.getgCostfunc()+tran.getgCostFunc()));
		return tSum;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
