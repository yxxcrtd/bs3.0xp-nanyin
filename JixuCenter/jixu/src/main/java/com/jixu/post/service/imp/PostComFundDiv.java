package com.jixu.post.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.TranCloType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

@Service("PostComFundDiv")
public class PostComFundDiv extends PostServiceImpl {

	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		// 一次股利的公司行为,会影响所有证券账户下的头寸

		String portnum = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		double divRate = tranAdvice.getAltRate();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);

		// 查出所有证券账户下的头寸
		List<TranClo> tCloList = new ArrayList<TranClo>();
		Date tradeDate=tranAdvice.getTradedate();
		if(tradeDate.compareTo(tranAdvice.getDivBasicDate())==0){
			TranCloType tranCloType=new TranCloType(portnum, cusip, tranAdvice.getDivBasicDate());
			tCloList=secPositionCenter.getSecClo(tranCloType, false);
		}else{
			List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
			tCloList=tranCloDao.loadTranCloListWithCusip(portnum,portList, cusip, tranAdvice.getDivBasicDate(), false);
		}
		double price=commfuncService.loadCusipPrice(portnum, cusip, tranAdvice.getTradedate());
		if(price==0){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.price_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.price_not_exist));
			return null;
		}
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		double fxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getIncomeCur(), tranAdvice.getTradedate());

		List<TranSum> tsumList = tranSumDao.loadTSumByCusip(portnum, cusip);
		for (TranClo clo : tCloList) {
			if (clo.getAmount() == 0) {
				continue;
			}
			double gCost = RoundUtil.roundMoney2Dec(divRate * clo.getAmount());
			double gCost_Func=RoundUtil.roundMoney2Dec(gCost * fxRate);
			double units=RoundUtil.roundMoney(gCost/price, 0);

			for (TranSum tSum : tsumList) {
				if (tSum.getLotnum() == clo.getLotnum()) {
					Tran tran = new Tran();
					tran.setCusip(tranAdvice.getCusip());
					tran.setPortfolio(tranAdvice.getPortfolio());
					tran.setLotnum(tSum.getLotnum());
					tran.setTrannum(tSum.getLstTrannum() + 1);
					tran.setTrantype(tranAdvice.getTrantype());
					tran.setTanum(tranAdvice);
					tran.setTradeDate(tranAdvice.getTradedate());
					tran.setSettleDate(tranAdvice.getSettledate());
					tran.setLedgerDate(tranAdvice.getLedgerdate());
					tran.setgCost(gCost);
					tran.setgCostFunc(gCost_Func);
					tran.setAmount(units);
					tran.setSecacct(tSum.getSecacct());
					tran.setManagerID(tSum.getManagerID());
					tSum = CaltSum(tran, tSum);

					tranSumDao.updateEntity(tSum);
					tranDao.saveEntity(tran);
				}
			}
		}

		return null;
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return unpostCaltsumUtil(tran, tSum);
	}

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return caltSumUtil(tran, tSum);
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
