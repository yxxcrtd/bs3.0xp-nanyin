package com.jixu.post.service.imp;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.post.service.PostService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

@Service
public class PostSecContribution extends PostServiceImpl{
	
	@Resource(name="slStockPurPostImp")
	private  PostService postSLStockPur;
	
	@Resource(name="repoLendPost")
	private  PostService postRepoLend;
	
	@Autowired
	private TranCloDao tranCloDao;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		double taprincipal = tranAdvice.getPrincipal();
		double tafxrate = tranAdvice.getFxrate();
		String secacct=tranAdvice.getSecacct();

		//目前根据 投资组合+证券代码+券商帐号 几个key作为一个区分lot的key
		TranSum tSum = new TranSum();	

		double tranG_CostFunc = RoundUtil.roundMoney2Dec(taprincipal * tafxrate);

		Tran tran = new Tran();
		tran.setCusip(tranAdvice.getCusip());
		tran.setPortfolio(tranAdvice.getPortfolio());
		tran.setLotnum(1);
		tran.setTrannum(1);
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
		tran.setSecacct(secacct);
		tran.setIntps(tranAdvice.getIntatmat());
		tran.setValuationType(tranAdvice.getValuationType());
		tran.setManagerID(tranAdvice.getManager());

		tSum=CaltSum(tran, tSum);
		
		TranClo clo=copyTSum2TClo(tranAdvice, tSum);
		tranCloDao.saveEntity(clo);
		
		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tSum);
		return psm;
	}
	
	private TranClo copyTSum2TClo(TranBasic tran, TranSum tsum){
		TranClo clo=new TranClo();
		Date dateStr=tran.getLedgerdate();
		double price=commfuncService.loadCusipPrice(tsum.getPortfolio(), tsum.getCusip(), dateStr);
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tsum.getPortfolio());
		IssueInfo sec=issueInfoDao.loadIssueByCusip(tsum.getCusip());
		double prinFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getPriCur(), dateStr);
		double incFxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getIncomeCur(), dateStr);
		
		clo.setAmount(tsum.getAmount());
		clo.setCusip(tsum.getCusip());
		clo.setgCost(tsum.getgCost());
		clo.setgCostFunc(tsum.getgCostfunc());
		
		double mkvalue=tsum.getgCost();
		if(price!=0){
			mkvalue=RoundUtil.roundMoney2Dec(tsum.getAmount()*price);
		}else{
			price=1;
		}
		clo.setPrice(price);
		clo.setgMkValue(mkvalue);
		clo.setIncFxRate(incFxRate);
		clo.setLotnum(tsum.getLotnum());
		clo.setLstTrannum(tsum.getLstTrannum());
		clo.setPortfolio(tsum.getPortfolio());
		clo.setPrinFxRate(prinFxRate);
		clo.setCloseDate(tran.getLedgerdate());
		clo.setAltRate(tsum.getAltRate());
		clo.setAltDate(DateUtil.ConvertUtil2Sql(tsum.getAltDate()));
		clo.setLsttradeDate(tsum.getLsttradeDate());
		clo.setSecacct(tsum.getSecacct());
		clo.setManagerID(tsum.getManagerID());
		clo.setLsindicator(tsum.getLsindicator());
		clo.setLastActivetyDate(tsum.getLastActivetyDate());
		clo.setAmrValue(tsum.getgCost());	//摊余成本
		clo.setCycleStartAmrValue(tsum.getgCost());
		clo.setAccrued(tran.getIntatmat());//应收利息
		clo.setValuationType(tsum.getValuationType());
		clo.setLstSettledate(tsum.getLstSettledate());
		
		return clo;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		double tsumAmount = tran.getAmount();
		double tsumG_Cost = tran.getgCost();
		double tsumG_CostFunc = tran.getgCostFunc();

		tsumAmount = RoundUtil.roundMoney2Dec(tSum.getAmount()-tsumAmount);
		tsumG_Cost = RoundUtil.roundMoney2Dec(tSum.getgCost()-tsumG_Cost);
		tsumG_CostFunc = RoundUtil.roundMoney2Dec(tSum.getgCostfunc()-tsumG_CostFunc);
	
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setgCost(tsumG_Cost);
		tSum.setgCostfunc(tsumG_CostFunc);
		
		return tSum;
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum){
		if(tSum==null){
			tSum=new TranSum();
		}
		
		TranBasic tranBasic=tran.getTanum();
		
		double tsumAmount = tran.getAmount();
		double tsumG_Cost = tran.getgCost();
		double tsumG_CostFunc = tran.getgCostFunc();

		//trannum=1,说明是该lot的第一笔交易,需要初始化tsum
		//上一条注释是过去的需求,现在无论tranum为多少，都设值基本信息
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setSecacct(tran.getSecacct());
		tSum.setManagerID(tran.getManagerID());
		tSum.setCusip(tran.getCusip().getId());
		tSum.setLotnum(tran.getLotnum());
		tSum.setLsindicator(LongShortType.longTerm);
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setgCost(tsumG_Cost);
		tSum.setgCostfunc(tsumG_CostFunc);
		tSum.setAltDate(tranBasic.getAltDate());
		tSum.setAltRate(tranBasic.getAltRate());
		
		if(tranBasic.getValuationType()!=ValuationType.AMORTIZE){
			tSum=postSLStockPur.CaltSum(tran, tSum);
		}else{
			tSum=postRepoLend.CaltSum(tran, tSum);
			tSum.setValuationType(tran.getValuationType());
		}
		
		return tSum;
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String portfolio = tranAdvice.getPortfolio();
		String cusip = tranAdvice.getCusip().getId();
		String tanum=tranAdvice.getId();
		
		Tran tran=tranDao.loadTranByTanum(tanum);
		if(tran!=null){
			int lotnum=tran.getLotnum();
			
			TranSum tSum = tranSumDao.loadTSumByLot(portfolio, cusip, lotnum);
			PostServiceModel psm=new PostServiceModel();
			tSum=unpostCaltsum(tran, tSum);
			psm.setTran(tran);
			psm.setTranSum(tSum);
			
			TranClo tclo=tranCloDao.loadTCloByLot(portfolio, cusip, lotnum);
			if(tclo!=null){
				//如果已经跑账并撤账，clo已经在撤销关账的时候被删除
				tranCloDao.deleteEntity(tclo);
			}
			return psm;
		}else{
			return null;
		}
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
