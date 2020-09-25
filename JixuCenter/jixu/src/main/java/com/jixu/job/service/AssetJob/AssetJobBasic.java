package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.GLACCT;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.ParChange;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.GlCloseDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.job.service.AssetJobService;
import com.jixu.job.service.ImportManualService;
import com.jixu.job.service.Impl.JobCommonImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 下午11:42:42 	
 * @Description		[]  
 */

public abstract class AssetJobBasic implements AssetJobService{
	@Resource(name="jobCommonImpl")
	protected JobCommonImpl jobCom;
	
	@Autowired
	protected TranDao tranDao;
	
	@Autowired
	protected TranCloDao tranCloDao;
	
	@Autowired
	protected TranBasicDao tranBasicDao;
	
	@Autowired
	protected GlCloseDao glCloseDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	protected BkCashAcctDao bkCashAcctDao;
	
//	@Resource(name="jixuSmfDivDao")
//	protected SmfDivDao smfDivDao;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Resource(name="msgAssemble")
	protected MsgAssemble msgAssemble;
	
	@Resource(name="postIssues")
	protected PostIssueService postIssue;
	
	@Resource(name="msgPackage")
	protected MsgPackage msgPackage;
	
	@Resource(name="tradeService")
	protected TradeService tradeService;
	
	@Resource(name="incomeExpenseCal")
	protected IncomeExpenseCal incomeCal;
	
	@Resource(name="jxImportManual")
	protected ImportManualService manualService;
	
	@Autowired
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired
	protected SecPositionCenter secPositionCenter;
	
	public TranClo setCloInterest(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo oldClo, List<Tran> tranList, List<Tran> settleTranList) {
		String cashflowID=sec.getCashFlowCfgID();
		if(sec.getIssueType().equals(AssetTypes.SWAP_INTEREST) && clo.getLsindicator()==LongShortType.ShortTerm){
			cashflowID=sec.getCashFlowCfgID2();
		}
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowID);
		if(cfc==null) {
			return clo;
		}
		
		boolean setFlag=incomeCal.checkGenSetInterestVoucher(cfc, portfolio.getLastGLClose(), clo.getCloseDate());
		
		List<Tran> calTrans=null;
		List<Date> holidayList=commfuncService.getHolidays(clo.getPortfolio());
		//获取当天做账日，应该获取哪天的交易来带入今天的利息
		Date basicStartDate=incomeCal.getStartBasicAmountDate(cfc, clo.getCloseDate(), holidayList);
		if(basicStartDate.compareTo(clo.getCloseDate())==0){
			if(cfc.getStartNDaysBase()==TradeSettleLedger.TradeDate){
				calTrans=tranList;
			}else{
				calTrans=settleTranList;
			}
		}else{
			//如果不是T+0开始计息，则重新取交易数据
			if(cfc.getStartNDaysBase()==TradeSettleLedger.TradeDate){
				calTrans=tranDao.loadCusipTrans(portfolio.getId(), clo.getCusip(), portfolio.getLastGLClose(), basicStartDate);
			}else{
				calTrans=tranDao.loadCusipTransSettle(portfolio.getId(), clo.getCusip(), portfolio.getLastGLClose(), basicStartDate);
			}
		}
		
		double todayInterest=incomeCal.getInterestForAll(clo);
		//设值当天利息
		clo.setTodayInterest(todayInterest);
		
		//获取上一个tranclo,查询历史利息总数
		double incoms=0;
		
		if(oldClo!=null){
			double oldLeftAmortValue=oldClo.getAmrValue();
			
			if(calTrans.size()>0 && sec.getIssueType().equals(AssetTypes.Bond)){
				//计算摊余成本value  前一天存在clo
				double hbPrincipal=0;
				double oldLeftAmount=oldClo.getAmount();
				if(cfc.getStartNDaysBase()==TradeSettleLedger.SettleDate){
					//如果是结算日计息，前一天的总数量得重新计算
					TranSum tmptsum=secPositionCenter.getSecPositionBySetDate(clo.getPortfolio(), clo.getCusip(), clo.getLotnum(), portfolio.getLastGLClose());
					oldLeftAmount=tmptsum.getAmount();
				}
				
				for(Tran tran:calTrans){
					if(tran.getTrantype()==TransTypes.Bond_Sale){
						//判断  使用摊余成本法的债券存在卖出情况
						double amountPercent=tran.getAmount()/oldLeftAmount;
						double saledAmrValue=RoundUtil.roundMoney2Dec(oldClo.getAmrValue()*amountPercent);
						oldLeftAmortValue=RoundUtil.roundMoney2Dec(oldClo.getAmrValue()-saledAmrValue);
						oldLeftAmount=RoundUtil.roundMoney2Dec(oldLeftAmount-tran.getAmount());
					}
					
					ParChange parchange=incomeCal.getPayParChange(sec.getContractSize(), cfc, clo.getCloseDate());
					double todayPar=parchange.getTodayParValue();
					if(tran.getTrantype()==TransTypes.Bond_HBFX_Shares){
						double oldPar=parchange.getYesterdayParValue();
						double parPar=parchange.getPayParToday();
						double parPercent=parPar/oldPar;
						double saledAmrValue=RoundUtil.roundMoney2Dec(oldClo.getAmrValue()*parPercent);
						oldLeftAmortValue=RoundUtil.roundMoney2Dec(oldClo.getAmrValue()-saledAmrValue);
						double newyxRate=incomeCal.calAmortRateByTrade(cfc, tran.getTradeDate(), tran.getSettleDate(), 
								sec.getExpireDate(), oldLeftAmortValue, oldLeftAmount*todayPar, holidayList);
						clo.setAltRate(newyxRate);
					}
					
					//归还面额，需要重新计算有效利率
					if(tran.getTrantype()==TransTypes.Bond_HBFX_Principal){
						hbPrincipal=tran.getProceeds();
						oldLeftAmortValue=RoundUtil.roundMoney2Dec(oldLeftAmortValue-hbPrincipal);
						double newyxRate=incomeCal.calAmortRateByTrade(cfc, tran.getTradeDate(), tran.getSettleDate(), 
								sec.getExpireDate(), oldLeftAmortValue, oldLeftAmount*todayPar, holidayList);
						clo.setAltRate(newyxRate);
						//接下去如果改好的话，oldLeftAmount*sec.getContractSize()应该是原来的面值，tran.getProceeds()是减多少面值
					}
					
					if(tran.getTrantype()==TransTypes.Bond_Buy){
						oldLeftAmortValue=RoundUtil.roundMoney2Dec(oldLeftAmortValue+tran.getgCost());
						oldLeftAmount=RoundUtil.roundMoney2Dec(oldLeftAmount+tran.getAmount());
						double newyxRate=incomeCal.calAmortRateByTrade(cfc, tran.getTradeDate(), tran.getSettleDate(), 
								sec.getExpireDate(), oldLeftAmortValue, oldLeftAmount*todayPar, holidayList);
						clo.setAltRate(newyxRate);
					}
				}
			}
			
			//利息收入  日利率
			incoms=RoundUtil.roundMoney2Dec(oldLeftAmortValue*clo.getAltRate());
			if(oldClo.getAltRate()==0){
				//如果发现摊余成本和成本值相同，则当天的利息收入=应收利息
				incoms=todayInterest;
			}else if(todayInterest==0&&clo.getAmount()==0){   //如果只是todayInterest=0该情况不满足贴现债    因为贴现债本身就没有应收利息
				//如果应收利息为0，则利息收入为0
				incoms=0;
				clo.setAltRate(0);
			}
			clo.setCycleStartAmrValue(oldLeftAmortValue);
			clo.setAmrValue(RoundUtil.roundMoney2Dec(oldLeftAmortValue-todayInterest+incoms));
			
			//if(setFlag){ //利息由交易来驱动减少 2019-09-17 潘炜
			//	clo.setAccrued(todayInterest);
			//}else{
				clo.setAccrued(RoundUtil.roundMoney2Dec(todayInterest+oldClo.getAccrued()));
			//}
		}else{
			double gCost=RoundUtil.roundMoney2Dec(clo.getgCost());
			for(Tran tran:calTrans){
				if(tran.getTrantype()==TransTypes.Bond_Buy){
					double newyxRate=incomeCal.calAmortRateByTrade(cfc, tran.getTradeDate(), tran.getSettleDate(), 
							sec.getExpireDate(), gCost, tran.getAmount()*sec.getContractSize(), holidayList);
					clo.setAltRate(newyxRate);
					//接下去如果改好的话，oldLeftAmount*sec.getContractSize()应该是原来的面值，tran.getProceeds()是减多少面值
				}
			}
			
			//利息收入   日利率
			incoms=RoundUtil.roundMoney2Dec(gCost*clo.getAltRate());
			if(clo.getAltRate()==0){
				//如果发现摊余成本和成本值相同，则当天的利息收入=应收利息
				incoms=todayInterest;
			}else if(todayInterest==0&&clo.getAmount()==0){   //如果只是todayInterest=0该情况不满足贴现债    因为贴现债本身就没有应收利息
				//如果当天没有应收利息，则利息收入为0。可能由于结算日开始计息，交易日和结算日不在同一天
				incoms=0;
				clo.setAltRate(0);
			}
			
			//计算摊余成本value  前一天不存在clo  amrValue=gCost-应收利息+利息收入
			clo.setCycleStartAmrValue(gCost);
			clo.setAmrValue(RoundUtil.roundMoney2Dec(gCost-todayInterest+incoms));
			
			clo.setAccrued(todayInterest);
		}
		
		//目前的有效利率采用二分法计算，有可能会出现最后一天.99或者.01的状况，暂时摊销到最后一天。如果有更好的公式修改有效利率的算法，则删除当前值
		if(DateUtil.getNextDay(clo.getCloseDate()).compareTo(sec.getExpireDate())==0){
			ParChange parchange=incomeCal.getPayParChange(sec.getContractSize(), cfc, clo.getCloseDate());
			double todayParValue=parchange.getTodayParValue();
			if(clo.getAmrValue()!=todayParValue && Math.abs(clo.getAmrValue()-todayParValue)<=0.01){
				clo.setAmrValue(todayParValue);
			}
		}
	
		//当天买卖带入带出的利息，加入到accrued里
		//如果是计息第一天，不会带入任何利息
		//if(!setFlag){ //利息由交易来驱动减少 2019-09-17 潘炜
			//债券是带入或者带出利息的。其它类似回购等，则是标记了结束时一共会多少利息
			Date ledgerDate=clo.getCloseDate();
			for(Tran tran:calTrans){
				setAfterPayInterestClo(clo, tran, cfc);
			}
			
			//检查晚到的交易
			for(Tran tran:tranList){
				double tmpAccrued=0;
				if(tran.getTradeDate().compareTo(ledgerDate)==-1 
						&& tran.getLedgerDate().compareTo(ledgerDate)==0){
					//倒起息,当期间内有晚到交易，前几天的交易录入在当天的，得把几天的应计利息补回来
					double amortValue=tran.getgCost();
					Date tmpTradeDate=tran.getTradeDate();
					double tmpAcc=0;
					while(!tmpTradeDate.after(clo.getCloseDate())){
						tmpAcc=incomeCal.getInterestForAll(tran, tmpTradeDate);
						if(tmpTradeDate.before(clo.getCloseDate())){
							tmpAccrued=RoundUtil.roundMoney2Dec(tmpAccrued+tmpAcc);
						}
						amortValue=RoundUtil.roundMoney2Dec(amortValue+RoundUtil.roundMoney2Dec(amortValue*clo.getAltRate())-tmpAcc);
						tmpTradeDate=DateUtil.getNextDay(tmpTradeDate);
					}
					clo.setAmrValue(amortValue);
				}
				
				clo.setTodayInterest(RoundUtil.roundMoney2Dec(clo.getTodayInterest()+tmpAccrued));
				if(tran.getProceeds()==0){
					if(clo.getLsindicator()==LongShortType.longTerm){
						if(cfc.getRecOrPay()==IncomeExpenseType.income){
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tmpAccrued));
						}else{
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tmpAccrued));
						}
					}else{
						if(cfc.getRecOrPay()==IncomeExpenseType.income){
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tmpAccrued));
						}else{
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tmpAccrued));
						}
					}
				}else{
					if(clo.getLsindicator()==LongShortType.longTerm){
						if(cfc.getRecOrPay()==IncomeExpenseType.income){
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tmpAccrued));
						}else{
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tmpAccrued));
						}
					}else{
						if(cfc.getRecOrPay()==IncomeExpenseType.income){
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tmpAccrued));
						}else{
							clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tmpAccrued));
						}
					}
				}
			}
		//}
		
		//利息调整
		for(Tran tran:tranList){
			if(tran.getTrantype()==TransTypes.IncomeUpdate){
				clo.setAccrued(RoundUtil.roundMoney2Dec(tran.getIntps()));//收益调整替换原先的accrued
			}
		}
		
		//clo.setAltDate(DateUtil.ConvertUtil2Sql(endDate));
		return clo;
	}
	
	/**
	 * 根据交易带进或者带出的利息来计算当天的利息头寸
	 * @param clo
	 * @param tran
	 * @param cfc
	 */
	private void setAfterPayInterestClo(TranClo clo, Tran tran, CashFlowCfg cfc) {
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
		if(secBasic.buySaleFlag()){
			//如果利息增加，直接在前一天的头寸上累加
			if(clo.getLsindicator()==LongShortType.longTerm){
				if(cfc.getRecOrPay()==IncomeExpenseType.income){
					clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tran.getIntps()));
				}else{
					clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tran.getIntps()));
				}
			}else{
				if(cfc.getRecOrPay()==IncomeExpenseType.income){
					clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-tran.getIntps()));
				}else{
					clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+tran.getIntps()));
				}
			}
		}else{
			if(tran.getIntps()!=0) {
				//如果是付出利息、带出利息的交易，需要判断结转哪天的利息，是否完全结清
				TranBasic oldtranBasic=tranBasicDao.loadTranBasicByID(tran.getTanum().getId());
				//1.获取结转哪天的利息
				Date baseDivDate=oldtranBasic.getDivBasicDate();
				//2.获取基准日期的头寸
				TranClo baseClo=secPositionCenter.getSecPosition(tran.getPortfolio(), tran.getCusip().getId(), tran.getLotnum(), baseDivDate, false);
				
				//3.查看是否为完全结清，如果是，则扣减的利息=截止当天的利息余额
				double setinterest=tran.getIntps();
				if(tran.getLsindicator()==LongShortType.longTerm && oldtranBasic.isSetAllIntatmatFlag()) {
					setinterest=baseClo.getAccrued();
				}else if(tran.getLsindicator()==LongShortType.ShortTerm && oldtranBasic.isSetAllIntatmatFlag2()) {
					setinterest=baseClo.getAccrued();
				}
				
				if(clo.getLsindicator()==LongShortType.longTerm){
					if(cfc.getRecOrPay()==IncomeExpenseType.income){
						clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-setinterest));
					}else{
						clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+setinterest));
					}
				}else{
					if(cfc.getRecOrPay()==IncomeExpenseType.income){
						clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()+setinterest));
					}else{
						clo.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()-setinterest));
					}
				}
			}
		}
	}
	
	/**
	 * 利息的估增,如果在利息的计算不是全冲全提
	 * @param generalCooa
	 * @param portfolio
	 * @param stcostWrap
	 * @param ledgerDate
	 * @return
	 * @throws JNLException
	 */
	public GLCOOABase exeIncomeSingleGeneralJob(GLCOOABase generalCooa, PortfolioHdr portfolio,
			STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException{
		String chartnum = portfolio.getCoaahdr();
		// 如果是多lot,分两张不同的凭证出。因为是两个证券账号买入
		//Date lastLedgerDate=portfolio.getLastGLClose();
		TranClo lastDayClo=stcostWrap.getPriordayCost().get(0);
		TranClo todayClo=stcostWrap.getAsofdayCost().get(0);
		
		double lj_income_local_lastday=0;
		//以下两个参数为了识别当天有没有交易,因为利息参数每天在变化,所以不能判断有无交易
		double lastGFuncCost=0;
		double todayGFuncCost=0;
		
		IssueInfo sec = issueInfoDao.loadIssueByCusip(stcostWrap.getCusip());
		int coagroup=sec.getCoaaGroup();
		double lj_income_func_lastday=0;
		
		if(lastDayClo!=null){
			lj_income_local_lastday=lastDayClo.getAccrued(); // 累计到上一个做账日的收益(原币)
			lastGFuncCost=lastDayClo.getgCostFunc();
			lj_income_func_lastday = RoundUtil.roundMoney2Dec(lj_income_local_lastday * lastDayClo.getPrinFxRate()); // 累计到上一个做账日的收益(本币)
		}
	
		double lj_income_local_today=0;
		double lj_income_func_today=0;
		double today_income=0;
		double todayFxRate=1;
		if(todayClo!=null){
			lj_income_local_today=todayClo.getAccrued(); ; // 昨天的份额累计到今天的收益(原币)
			todayGFuncCost=todayClo.getgCostFunc();
			todayFxRate=todayClo.getPrinFxRate();
			lj_income_func_today = RoundUtil.roundMoney2Dec(lj_income_local_today * todayClo.getPrinFxRate()); // 累计到今天的收益(本币)
			today_income=RoundUtil.roundMoney2Dec(lj_income_local_today-lj_income_local_lastday);
		}
		
		//这个参数用于计算今天交易的估增,如果出现交易汇率和当天计算汇率不一样，则有估增产生
		double todayTranFuncDiff=0;
		//如果昨天和今天的债券成本部一致,则说明当天存在交易。可能会存在交易汇率和当天汇率不想等的情况,产生估增
		if(lastGFuncCost!=todayGFuncCost && todayFxRate!=1){
			List<Tran> tranList=tranDao.loadTrans(portfolio.getId(), ledgerDate, sec.getId(), lastDayClo.getLotnum());
			double buysaleFuncGain=0;
			double buysaleGain=0;
			for(Tran tran:tranList){
				if(tran.getProceeds()>0){
					buysaleGain=buysaleGain-tran.getIntps();
					buysaleFuncGain=buysaleFuncGain-(RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getPrinFxrate()));
				}else{
					buysaleGain=buysaleGain+tran.getIntps();
					buysaleFuncGain=buysaleFuncGain+(RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getPrinFxrate()));
				}
				todayTranFuncDiff=RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(buysaleGain*todayFxRate)-buysaleFuncGain);
			}
			
		}
		double today_income_func=RoundUtil.roundMoney2Dec(today_income*todayFxRate)-todayTranFuncDiff;
		
		double gain_loss_local = 0;
		double gain_loss_func = RoundUtil.roundMoney2Dec(lj_income_func_today - lj_income_func_lastday-today_income_func); // 每日估值万份收益金额

		if (gain_loss_local != 0 || gain_loss_func != 0) {
			if (gain_loss_func > 0) {
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						gain_loss_func, gain_loss_local, jobCom.loadGLAcctnum(
								chartnum, coagroup,
								GLACCT.ACCRUED_INC));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						gain_loss_func, gain_loss_local, jobCom.loadGLAcctnum(
								chartnum, coagroup,
								GLACCT.FX_GAIN_INCOME_UNREAL));
			} else {
				jobCom.saveGL(generalCooa, DBCR.DEBIT,
						gain_loss_func, gain_loss_local, jobCom.loadGLAcctnum(
								chartnum, coagroup,
								GLACCT.ACCRUED_INC));

				jobCom.saveGL(generalCooa, DBCR.CREDIT,
						gain_loss_func, gain_loss_local, jobCom.loadGLAcctnum(
								chartnum, coagroup,
								GLACCT.FX_LOSS_INCOME_UNREAL));
			}
			jobCom.genVoucherDetails(generalCooa, stcostWrap.getTranClo(), todayClo.getPrice(), todayFxRate);
		}
		
		return generalCooa;
	}
}
