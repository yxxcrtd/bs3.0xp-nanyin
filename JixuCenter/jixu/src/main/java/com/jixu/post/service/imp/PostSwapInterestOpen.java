package com.jixu.post.service.imp;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostSwapInterestOpen extends PostServiceImpl {

	@Resource(name = "repoLendPost")
	private PostService postService1;

	@Resource(name="slStockPurPostImp")
	private  PostService postSLStockPur;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {

		//每次融券，都生成一个新的lot
		double principal = tranAdvice.getPrincipal();
		double principal2 = tranAdvice.getPrincipal2();
		IssueInfo issueInfo =tranAdvice.getCusip();
		String portnum=tranAdvice.getPortfolio();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		double fxrate1=tranAdvice.getFxrate();
		double fxrate2=tranAdvice.getFxrate2();
		
		int maxlotnum=tranSumDao.findMaxLotNumByCusip(portnum, issueInfo.getId());
		
		int lotnum=maxlotnum+1;
		int lotnum2=maxlotnum+2;
		
		Tran tran = new Tran();
		TranSum tranSum=null;
		//ttranum 记录总的第几笔交易
		double tranG_CostFunc = RoundUtil.roundMoney(principal * fxrate1, 2);
		tran.setCusip(issueInfo);
		tran.setPortfolio(portfolio.getId());
		tran.setLotnum(lotnum);
		tran.setTrannum(1);
		tran.setTrantype(tranAdvice.getTrantype());
		tran.setTanum(tranAdvice);
		tran.setTradeDate(tranAdvice.getTradedate());
		tran.setSettleDate(tranAdvice.getSettledate());
		tran.setLedgerDate(tranAdvice.getLedgerdate());
		tran.setAmount(tranAdvice.getAmount());
		tran.setProceeds(0);
		tran.setPrinFxrate(fxrate1);
		tran.setIncFxrate(fxrate1);
		tran.setgCost(principal);
		tran.setgCostFunc(tranG_CostFunc);
		tran.setgOriginalamt(tranAdvice.getAmount());
		tran.setIntps(tranAdvice.getIntatmat());
		tran.setLsindicator(LongShortType.longTerm);
		tran.setManagerID(tranAdvice.getManager());
			
		tranSum=CaltSum(tran, tranSum);

		tranDao.saveEntity(tran);
		tranSumDao.saveEntity(tranSum);
		

		// 先处理股票的增加---------------------
		//目前根据 投资组合+证券代码+券商帐号 几个key作为一个区分lot的key
		
		TranSum tSum2=null;

		double tranG_CostFunc2 = RoundUtil.roundMoney2Dec(principal2 * fxrate2);

		Tran tran2 = new Tran();
		tran2.setCusip(issueInfo);
		tran2.setPortfolio(portnum);
		tran2.setLotnum(lotnum2);
		tran2.setTrannum(1);
		tran2.setTrantype(tranAdvice.getTrantype());
		tran2.setTanum(tranAdvice);
		tran2.setTradeDate(tranAdvice.getTradedate());
		tran2.setSettleDate(tranAdvice.getSettledate());
		tran2.setLedgerDate(tranAdvice.getLedgerdate());
		tran2.setAmount(tranAdvice.getAmount2());
		tran2.setProceeds(0);
		tran2.setPrinFxrate(fxrate2);
		tran2.setIncFxrate(fxrate2);
		tran2.setgCost(principal2);
		tran2.setgCostFunc(tranG_CostFunc2);
		tran2.setgOriginalamt(tranAdvice.getAmount2());
		tran2.setIntps(tranAdvice.getIntatmat2());
		tran2.setLsindicator(LongShortType.ShortTerm);
		tran2.setManagerID(tranAdvice.getManager());

		tSum2=CaltSum(tran2, tSum2);
		tranDao.saveEntity(tran2);
		tranSumDao.saveEntity(tSum2);
		
		return null;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return null;
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum=postSLStockPur.CaltSum(tran, tSum);
		TranBasic tranBasic=tran.getTanum();
		tSum.setAltDate(tranBasic.getAltDate());
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
