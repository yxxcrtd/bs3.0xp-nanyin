package com.jixu.post.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostShareBankZGPayFee extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taprincipal = tranAdvice.getPrincipal();
		double tafxrate = tranAdvice.getFxrate();
		//double intps=tranAdvice.getIntatmat();
		IssueInfo sec =tranAdvice.getCusip();
		String secacct=tranAdvice.getSecacct();
		String portnum=tranAdvice.getPortfolio();
		int lotnum=tranAdvice.getSpecificLotID();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		Tran tran = new Tran();
		TranSum tranSum=tranSumDao.loadTSumByCusipLot(portnum, sec.getId(), lotnum);
		int trannum=tranSum.getLstTrannum()+1;
		//ttranum 记录总的第几笔交易
		double tranG_CostFunc = RoundUtil.roundMoney(taprincipal * tafxrate, 2);
		
		tran.setCusip(sec);
		tran.setPortfolio(portfolio.getId());
		tran.setLotnum(lotnum);
		tran.setTrannum(trannum);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setProceeds(0);
		tran.setPrinFxrate(tafxrate);
		tran.setIncFxrate(tafxrate);
		tran.setgCost(taprincipal);
		tran.setgCostFunc(tranG_CostFunc);
		tran.setAmount(tranAdvice.getAmount());
		tran.setgOriginalamt(tranAdvice.getAmount());
		tran.setSecacct(secacct);
		tran.setManagerID(tranAdvice.getManager());
			
		tranSum=CaltSum(tran, tranSum);
		
		tranDao.saveEntity(tran);
		tranSumDao.updateEntity(tranSum);
		return null;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum.setLstTrannum(tran.getTrannum());
		return tSum;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String portfolio = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		String tanum=tranAdvice.getId();
		
		List<Tran> trans=tranDao.loadTransByTanum(tanum);
		
		for(Tran tran:trans){
			int lotnum=tran.getLotnum();
	
			TranSum tSum = tranSumDao.loadTSumByLot(portfolio, cusip, lotnum);
			tSum=unpostCaltsum(tran, tSum);
				
			if(tSum.getLstTrannum()==0){//撤账时如果上次交易次数是0,说明之前不存在该交易所以删除tsum中该笔交易
				tranSumDao.deleteEntity(tSum);
			}else{
				tranSumDao.updateEntity(tSum);//如果不为0,说明之前存在该交易  则更改tsum表中的交易次数和金额
			}
			tranDao.deleteEntity(tran);//删除tran中对应的该笔交易
		}
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		if(tSum==null){
			tSum=new TranSum();
		}
		
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		return tSum;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
