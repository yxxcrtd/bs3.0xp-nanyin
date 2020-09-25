package com.jixu.post.service.imp;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranSubRedDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.post.service.PostService;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		PostServiceImpl
 * @author  		Pan Wei 
 * @Date 			2016年10月20日 下午9:43:40 	
 * @Description		[]  
 */

@Service("postService")
public abstract class PostServiceImpl implements PostService{
	
	@Autowired
	protected TranDao tranDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	private BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	protected TranSumDao tranSumDao;
	
	@Autowired
	protected TranSubRedDao tranSubRedDao;
	
	@Autowired
	protected CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	protected TranBasicDao tranBasicDao;
	
	@Autowired
	protected NavDao navDao;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService; 
	
	@Resource(name="incomeExpenseCal")
	protected IncomeExpenseCal incomeCal;
	
	@Autowired
	protected SecPositionCenter secPositionCenter;
	
	/**
	 * TranBasic是数据库中的基础交易数据，从接口导入。该方法需要把tranbasic计算成tran表的记录，主要是计算交易的成本
	 * 比如卖出交易，需要在计算这部分买入股数在买入时的成本
	 * 正常情况下，会调用CalSum方法，用以计算tsum表的记录。tsum记录的计算是整个系统的最核心
	 * 
	 * @param tranAdvice	通过外部接口录入的基础交易数据
	 * @param container		把需要返回的内容放到container参数，并标记true或者false
	 */
	public abstract PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container);
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void post(TranBasic tranAdvice, ResultContainer container) {
		//查看是否已经post
		if (tranAdvice.getPostStatus().equals(PostStatusType.post)) {
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_already_failed);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_already_failed));
			return;
		}
		if(container.isCorrectFlag()){	
			PostServiceModel psm=postTran(tranAdvice, container);
			if(container.isCorrectFlag()){
				if(psm !=null){
					if(psm.getTranSum()!=null){
						if(psm.getTranSum().getId()==0)
							tranSumDao.saveEntity(psm.getTranSum());
						else
							tranSumDao.updateEntity(psm.getTranSum());
					}
					tranDao.saveEntity(psm.getTran());
				}
			}
			/*TranBasic tb=postDAO.loadEntity(TranBasic.class, tranAdvice.getId());
			if(tb!=null){
				postDAO.updateEntity(tb);
			}*/
		}
	}
	
	/**
	 * 撤销交易
	 */
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void reverse(TranBasic tranAdvice, ResultContainer container) {
		List<BkCashRcpy> rcpyList=bkCashRcpyDao.loadRCPYByTanum(tranAdvice.getId());
		if(rcpyList.size()>0){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.unpost_rcpy_cashexist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.unpost_rcpy_cashexist));
			return;
		}
		
		PostServiceModel psm=reverseTran(tranAdvice, container);
		
		if(container.isCorrectFlag()){
			if(psm !=null){
				if(psm.getTranSum()!=null){
					if(psm.getTranSum().getLstTrannum()==0){//撤账时如果上次交易次数是0,说明之前不存在该交易所以删除tsum中该笔交易
						tranSumDao.deleteEntity(psm.getTranSum());
					}else{
						tranSumDao.updateEntity(psm.getTranSum());//如果不为0,说明之前存在该交易  则更改tsum表中的交易次数和金额
					}
				}
				if(psm.getTran()!=null){
					tranDao.deleteEntity(psm.getTran());//删除tran中对应的该笔交易
				}
			}
			tranBasicDao.updateEntity(tranAdvice);
		}
	}
	
	/**
	 * 回撤PostTran过程，需要最终把transum的记录算回来，并删除tran记录
	 * @param tranAdvice
	 * @param container
	 */
	public abstract PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container);

	/**
	 * 查看是否需要关闭tsum
	 * 除了在这里关闭tsum，还有可能在CloseControl--copyTsum2Tclo,发现不再计息,则关闭tsum
	 * @param tSum
	 * @return
	 */
	protected TranSum closeTSum(TranSum tSum){
		if(tSum.getAmount()==0){
			tSum.setPosclosed(PostStatusType.sumClosed);
			IssueInfo sec=issueInfoDao.loadIssueByCusip(tSum.getCusip());
			String cashflowid=sec.getCashFlowCfgID();
			if(!cashflowid.equals("0")){
				CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowid);
				List<Date> holidayList=commfuncService.getHolidays(tSum.getPortfolio());
				Date tradeDate=tSum.getLsttradeDate();
				Date setDate=tSum.getLstSettledate();
				Date lastActivetyDate=incomeCal.getEndInterestDate(cfc, tradeDate, setDate, holidayList);
				tSum.setLastActivetyDate(lastActivetyDate);
			}else{
				tSum.setLastActivetyDate(tSum.getLstSettledate());
			}
		}
		return tSum;
	}
	
	@Override
	public void delete(TranBasic tranAdvice, ResultContainer container) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 获取买入交易的lotnum, trannum
	 * @param portnum
	 * @param cusip
	 * @param secacct
	 * @param longShort
	 * @param singleLotFlag
	 * @return
	 */
	protected PostServiceLotTranNumModel genLotTranNum(String portnum, String cusip, String secacct, int longShort, boolean singleLotFlag){
		int lotnum=0;
		int ttrannum=0;
		TranSum tSum=new TranSum();
		
		if(singleLotFlag){
			//单lot的，比如股票等
			//目前根据 投资组合+证券代码+券商帐号 几个key作为一个区分lot的key
			tSum = tranSumDao.loadTSum(portnum, cusip, secacct,longShort);
			if (tSum == null) {
				int maxlotnum=tranSumDao.findMaxLotNumByCusip(portnum, cusip);
				lotnum=maxlotnum+1;
				ttrannum=1;
				tSum = new TranSum();
			} else {
				lotnum=tSum.getLotnum();
				ttrannum=tSum.getLstTrannum();
				ttrannum=ttrannum + 1;
			}
		}else{
			//多lot,类似逆回购，定存等交易
			lotnum=tranSumDao.findMaxLotNumByCusip(portnum, cusip);
			lotnum=lotnum+1;
			ttrannum=1;
		}
		
		PostServiceLotTranNumModel pltmm=new PostServiceLotTranNumModel();
		pltmm.setLotnum(lotnum);
		pltmm.setTrannum(ttrannum);
		pltmm.setTsum(tSum);
		
		return pltmm;
		
	}
	
	/**
	 * 
	 * @param tranAdvice
	 * @param longShort	做多做空
	 * @param singleLotFlag	true single-lot, false multi-lot
	 * @return
	 */
	protected PostServiceModel postTranUtil(TranBasic tranAdvice, int longShort, boolean singleLotFlag, ResultContainer container){
		double tafxrate = tranAdvice.getFxrate();
		String secacct=tranAdvice.getSecacct();
		
		PostServiceLotTranNumModel pltmm=genLotTranNum(tranAdvice.getPortfolio(), tranAdvice.getCusip().getId(), secacct, longShort, singleLotFlag);
		TranSum tSum=pltmm.getTsum();
		if(tSum.getValuationType()!=tranAdvice.getValuationType() && tSum.getLstTrannum()>1){
			container.setCorrectFlag(false);
			container.setErrorMsg("系统暂不支持同一个组合下的同一个证券，存在不同估值方法的批次");
			return null;
		}
		
		int lotnum=pltmm.getLotnum();
		int ttrannum=pltmm.getTrannum();
		
		double cost= tranAdvice.getPrincipal();
		double costFunc = RoundUtil.roundMoney2Dec(cost * tafxrate);

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
		tran.setLsindicator(longShort);
		tran.setIntps(tranAdvice.getIntatmat());
		tran.setValuationType(tranAdvice.getValuationType());
		tran.setManagerID(tranAdvice.getManager());
		
		tSum=CaltSum(tran, tSum);
		
		PostServiceModel psm=new PostServiceModel();
		psm.setTran(tran);
		psm.setTranSum(tSum);
		return psm;
	}
	
	/**
	 * 获取待卖出的头寸，并检查是否有足够的头寸待交易
	 * @param tranAdvice
	 * @param longShort
	 * @param container
	 * @return
	 */
	public List<TranSum> getPresaledTSums(TranBasic tranAdvice, int longShort, ResultContainer container){
		String portfolio = tranAdvice.getPortfolio();
		double taamount = tranAdvice.getAmount();
		String cusip = tranAdvice.getCusip().getId();
		
		//获取需要卖出的tsum头寸
		List<TranSum> preOrderS = null;
		if(tranAdvice.getSecacct()==null) {
			//swap等交易，不需要证券账户，直接现金账户交易
			preOrderS=tranSumDao.loadTranSumByPortCusipForSale(portfolio, cusip, longShort);
		}else {
			String secacct=tranAdvice.getSecacct();
			preOrderS = tranSumDao.loadTSums(portfolio, cusip, secacct, longShort);
		}
		if (preOrderS == null || preOrderS.size()==0) {
			//头寸不存在
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.post_sale_noSum);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.post_sale_noSum));
			return null;
		}
		
		if(tranAdvice.getHiloCode()==LotSelectionType.FIFO){
			//先进先出
			preOrderS.sort(Comparator.comparingInt(TranSum::getLotnum));
		}else if(tranAdvice.getHiloCode()==LotSelectionType.LIFO){
			//后进先出
			preOrderS.sort(Comparator.comparingInt(TranSum::getLotnum).reversed());
		}else if(tranAdvice.getHiloCode()==LotSelectionType.LowestCost){
			//最低成本排序
			preOrderS.sort(Comparator.comparingDouble(TranSum::getgCost));
		}else if(tranAdvice.getHiloCode()==LotSelectionType.HighestCost){
			//最高成本排序
			preOrderS.sort(Comparator.comparingDouble(TranSum::getgCost).reversed());
		}else if(tranAdvice.getHiloCode()==LotSelectionType.MoveAvgStatCost){
			//加权平均
			preOrderS.sort(Comparator.comparingInt(TranSum::getLotnum));
		}else if(tranAdvice.getHiloCode()==LotSelectionType.SpecificLot){
			//特定
			for(TranSum tsum:preOrderS){
				if(tsum.getLotnum()==tranAdvice.getSpecificLotID()){
					preOrderS.clear();
					preOrderS.add(tsum);
					break;
				}
			}
		}else{
			//默认用加权平均
			preOrderS.sort(Comparator.comparingInt(TranSum::getLotnum));
		}
		
		//头寸可以交易的数量
		double totalExistAmt=0;
		for(TranSum tsum:preOrderS){
			if(tsum.getValuationType()!=tranAdvice.getValuationType()){
				container.setCorrectFlag(false);
				container.setErrorMsg("系统暂不支持同一个组合下的同一个证券，存在不同估值方法的批次");
				return null;
			}
			totalExistAmt=RoundUtil.roundMoney2Dec(totalExistAmt+tsum.getAmount());
		}
		
		if (Math.abs(totalExistAmt) < Math.abs(taamount)) {
			//现存的股数小于售出的股数
			container.setErrorCode(ReturnResult.post_sale_zero);
			container.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.post_sale_zero)+"-"+tranAdvice.getCusip().getId());
			return null;
		} else if (totalExistAmt*taamount<0) {
			//现存的股数小于售出的股数
			container.setErrorCode(ReturnResult.post_zf_unmatch);
			container.setErrorMsg(false, ReturnResult.getResultHM().get(ReturnResult.post_zf_unmatch)+"-"+tranAdvice.getCusip().getId());
			return null;
		}
		return preOrderS;
	}
	
	/**
	 * 卖证券的post
	 * @param tranAdvice
	 * @param longShort
	 * @param container
	 * @return
	 */
	protected PostServiceModel postTranSaleUtil(TranBasic tranAdvice, int longShort, ResultContainer container) {
		List<TranSum> preOrderS=getPresaledTSums(tranAdvice, longShort, container);
		if(!container.isCorrectFlag())
			return null;
		
		double totalExistAmt=0;
		for(TranSum tsum:preOrderS){
			totalExistAmt=RoundUtil.roundMoney2Dec(totalExistAmt+tsum.getAmount());
		}
		//交易的总数量
		double leftAmount=tranAdvice.getAmount();
		double leftProceeds=tranAdvice.getPrincipal();
		double leftAccrued=tranAdvice.getIntatmat();
		
		for(TranSum tSum:preOrderS){
			double tranAmount=0;
			double tranProceed=0;//本头寸占据的交易金额
			double tranAccrued=0;//本头寸占据的利息
			double tsumAmount = tSum.getAmount();
			
			//不是移动加权平均的，都是按照顺序把最前的lot卖光，再买后面的
			if(tranAdvice.getHiloCode()!=LotSelectionType.MoveAvgStatCost){
				if(tsumAmount!=0){
					if(Math.abs(leftAmount)<=Math.abs(tsumAmount)){
						tranAmount=leftAmount;
						tranProceed=leftProceeds;
						tranAccrued=leftAccrued;
						leftAmount=0;
					}else{
						tranAmount=tsumAmount;
						
						tranProceed=RoundUtil.roundMoney2Dec(leftProceeds*tranAmount/leftAmount);
						leftProceeds=RoundUtil.roundMoney2Dec(leftProceeds-tranProceed);
						
						tranAccrued=RoundUtil.roundMoney2Dec(leftAccrued*tranAmount/leftAmount);
						leftAccrued=RoundUtil.roundMoney2Dec(leftAccrued-tranAccrued);
						
						leftAmount=RoundUtil.roundMoney2Dec(leftAmount-tsumAmount);
					}
				}else{
					continue;
				}
			}else{
				//剩余的数量*剩余的比例
				tranAmount=RoundUtil.roundMoney2Dec(leftAmount*tsumAmount/totalExistAmt);
				
				tranProceed=RoundUtil.roundMoney2Dec(leftProceeds*tsumAmount/totalExistAmt);
				leftProceeds=RoundUtil.roundMoney2Dec(leftProceeds-tranProceed);
				
				tranAccrued=RoundUtil.roundMoney2Dec(leftAccrued*tsumAmount/totalExistAmt);
				leftAccrued=RoundUtil.roundMoney2Dec(leftAccrued-tranAccrued);
				
				leftAmount=RoundUtil.roundMoney2Dec(leftAmount-tranAmount);
				totalExistAmt=RoundUtil.roundMoney2Dec(totalExistAmt-tsumAmount);
			}
			
			double tsumG_Cost = tSum.getgCost();
			double tsumG_CostFunc = tSum.getgCostfunc();
			double tranG_Cost=0;
			double tranG_CostFunc=0;
			if(tranAmount!=0){
				tranG_Cost= RoundUtil.roundMoney(tsumG_Cost* (tranAmount/tsumAmount), 2);
				tranG_CostFunc = RoundUtil.roundMoney(tsumG_CostFunc*(tranAmount/tsumAmount), 2);
			}
			Tran tran = new Tran();
			tran.setCusip(tranAdvice.getCusip());
			tran.setPortfolio(tranAdvice.getPortfolio());
			tran.setLotnum(tSum.getLotnum());
			tran.setTrannum(tSum.getLstTrannum()+1);
			tran.setTrantype(tranAdvice.getTrantype());
			tran.setTanum(tranAdvice);
			tran.setTradeDate(tranAdvice.getTradedate());
			tran.setSettleDate(tranAdvice.getSettledate());
			tran.setLedgerDate(tranAdvice.getLedgerdate());
			tran.setAmount(tranAmount);
			tran.setProceeds(tranProceed);
			tran.setPrinFxrate(tranAdvice.getFxrate());
			tran.setIncFxrate(tranAdvice.getFxrate());
			tran.setgCost(tranG_Cost);
			tran.setgCostFunc(tranG_CostFunc);
			tran.setIntps(tranAccrued);
			tran.setLsindicator(tSum.getLsindicator());
			if(tranAdvice.getSecacct()!=null)
				tran.setSecacct(tranAdvice.getSecacct());
			tran.setValuationType(tranAdvice.getValuationType());
			tran.setManagerID(tranAdvice.getManager());
			
			tSum=CaltSum(tran, tSum);
			
			tranDao.saveEntity(tran);
			tranSumDao.updateEntity(tSum);

			if(leftAmount==0)
				break;
		}
		
		return null;
	}
	
	protected TranSum caltSumUtil(Tran tran, TranSum tSum){
		if(tSum==null){
			tSum=new TranSum();
		}
		//trannum=1,说明是该lot的第一笔交易,需要初始化tsum
		//上一条注释是过去的需求,现在无论tranum为多少，都设值基本信息
		tSum.setPortfolio(tran.getPortfolio());
		tSum.setSecacct(tran.getSecacct());
		tSum.setManagerID(tran.getManagerID());
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
	
	protected TranSum caltSumSaleUtil(Tran tran, TranSum tSum){
		if(tSum==null){
			tSum=new TranSum();
		}
		
		tSum.setLstTrannum(tran.getTrannum());
		tSum.setAmount(RoundUtil.roundMoney2Dec(tSum.getAmount() - tran.getAmount()));
		tSum.setAmount2(RoundUtil.roundMoney2Dec(tSum.getAmount2() - tran.getAmount2()));
		tSum.setLsttradeDate(tran.getTradeDate());
		tSum.setLstSettledate(tran.getSettleDate());
		tSum.setLstLedgerdate(tran.getLedgerDate());
		tSum.setLsindicator(tran.getLsindicator());;
		tSum.setgCost(RoundUtil.roundMoney2Dec(tSum.getgCost() - tran.getgCost()));
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tSum.getgCostfunc() - tran.getgCostFunc()));
		tSum=closeTSum(tSum);
		
		return tSum;
	}
	
	protected TranSum unpostCaltsumUtil(Tran tran, TranSum tSum){
		double tsumAmount = RoundUtil.roundMoney2Dec(tSum.getAmount()-tran.getAmount());
		double tsumG_Cost = RoundUtil.roundMoney2Dec(tSum.getgCost()-tran.getgCost());
		double tsumG_CostFunc = RoundUtil.roundMoney2Dec(tSum.getgCostfunc()-tran.getgCostFunc());
	
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(tsumAmount);
		tSum.setgCost(tsumG_Cost);
		tSum.setgCostfunc(tsumG_CostFunc);
		setTSumDate(tSum);
		
		return tSum;
	}
	
	protected TranSum unpostCaltsumSaleUtil(Tran tran, TranSum tSum){
		tSum.setLstTrannum(tSum.getLstTrannum()-1);
		tSum.setPosclosed(PostStatusType.sumOpen);
		tSum.setAmount(RoundUtil.roundMoney2Dec(tSum.getAmount()+tran.getAmount()));
		tSum.setAmount2(RoundUtil.roundMoney2Dec(tSum.getAmount2()+tran.getAmount2()));
		tSum.setgCost(RoundUtil.roundMoney2Dec(tSum.getgCost()+tran.getgCost()));
		tSum.setgCostfunc(RoundUtil.roundMoney2Dec(tSum.getgCostfunc()+tran.getgCostFunc()));
		setTSumDate(tSum);
		
		return tSum;
	}
	
	private void setTSumDate(TranSum tSum){
		if(tSum.getLstTrannum()>0){
			//撤账后，该头寸之前还有交易的
			Tran lastTran=tranDao.loadTran(tSum.getPortfolio(), tSum.getCusip(), tSum.getLotnum(), tSum.getLstTrannum());
			if(lastTran!=null){
				tSum.setLsttradeDate(lastTran.getTradeDate());
				tSum.setLstSettledate(lastTran.getSettleDate());
				tSum.setLstLedgerdate(lastTran.getLedgerDate());
			}
		}
	}
	
	protected PostServiceModel reverseTranUtil(TranBasic tranAdvice, ResultContainer container) {
		String tanum=tranAdvice.getId();
		
		//默认状态，一条tranbasic生成一条tran记录
		List<Tran> transList = tranDao.loadTransByTanum(tanum);
		for (Tran tran : transList) {
			String portnum = tranAdvice.getPortfolio();
			String cusip = tran.getCusip().getId();
			int lotnum = tran.getLotnum();

			TranSum tSum = tranSumDao.loadTSumByLot(portnum, cusip, lotnum);
			tSum = unpostCaltsum(tran, tSum);

			if (tSum.getLstTrannum() == 0) {
				tranSumDao.deleteEntity(tSum);
			} else {
				tranSumDao.updateEntity(tSum);
			}
			if (tran != null) {
				tranDao.deleteEntity(tran);
			}
		}
		return null;
	}
}
