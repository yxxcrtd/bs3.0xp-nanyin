package com.jixu.common.func;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CalInterestModel;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.types.FxTranType;
import com.jixu.common.types.LocalFuncResult;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.TranCloSumLot;
import com.jixu.common.types.TranCloType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.dao.intf.BkInteRatioDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.job.obj.FollowFeeBean;
import com.jixu.job.service.AssetJobService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityAssetService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInteAcct;
import com.orm.obj.BkInteRatio;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranFeeFollowClo;
import com.orm.obj.TranSum;

/**
 * @ClassName CalSecPosition
 * @author Pan Wei
 * @Date 2018年5月29日 上午10:10:32
 * @Description []
 */

@Service
public class SecPositionCenter {
	@Autowired
	private CommFuncService commfuncService;

	@Autowired
	private PostIssueService postIssues;

	@Autowired
	protected SecurityCoreFactory secCoreFactory;

	@Autowired
	private IncomeExpenseCal incomeExpenseCal;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
	@Autowired
	private BkInteRatioDao bkInteRatioDao;
	
	@Autowired
	private PerformanceFeeCfgDao performanceFeeCfgDao;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;

	/**
	 * 获取该证券所有lot头寸
	 * 
	 * @param tranCloType
	 *            自定义的一个包含获取头寸参数的类
	 * @return 某个证券的份lot头寸集
	 */
	public List<TranClo> getSecClo(TranCloType tranCloType, boolean classFetchFlag) {
		List<TranSum> tsumList = new ArrayList<TranSum>();
		String portnum = tranCloType.getPortnum();
		String cusip = tranCloType.getCusip();

		Date endDate = tranCloType.getCloseDate();

		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date lastCloDate = portfolio.getLastTranClose();
		// if(portfolio.getLastTranClose().after(endDate)){
		List<TranClo> lastCloList = tranCloType.getLastClo();
		List<Tran> transList = tranCloType.getPeriodTrans();
		List<Tran> transSettleList=tranCloType.getPeriodSettleTrans();
		
		if (!lastCloDate.before(endDate)) {
			if (!tranCloType.isCloJobFlag()){
				List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
				return tranCloDao.loadTranCloListWithCusip(portnum,portList, cusip, endDate, classFetchFlag);
			}else{
				return tranCloType.getLastClo();
			}
		} else {
			if (!tranCloType.isCloJobFlag()) {
				transList = tranDao.loadCusipTrans(portnum, cusip, lastCloDate, endDate);
				List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
				lastCloList = tranCloDao.loadTranCloListBeforeCloseDateWithClassFlag(portnum,portList, lastCloDate, cusip, classFetchFlag);
				transSettleList=tranDao.loadCusipTransSettle(portnum, cusip, lastCloDate, endDate);
			}

			HashMap<Integer, TranCloSumLot> tranLotHM = new HashMap<Integer, TranCloSumLot>();
			if (transList.size() > 0 || lastCloList.size() > 0) {
				// 把股票按照lot分组，
				// 1) 以后可能会支持多lot计算
				// 2) 逆回购必须以lot来区分
				// 把所有的上一个关帐日--本期间的交易，按照lot放入到tranLotHM
				for (Tran tran : transList) {
					int lotnum = tran.getLotnum();
					if (tranLotHM.containsKey(lotnum)) {
						tranLotHM.get(lotnum).getTranList().add(tran);
					} else {
						TranCloSumLot tranCloSumlLot = new TranCloSumLot(cusip, lotnum);
						tranCloSumlLot.getTranList().add(tran);
						tranLotHM.put(lotnum, tranCloSumlLot);
					}
				}
				
				for (Tran tran : transSettleList) {
					if(tran.getTrantype()==TransTypes.SecContribution)
						continue;
					int lotnum = tran.getLotnum();
					if (tranLotHM.containsKey(lotnum)) {
						tranLotHM.get(lotnum).getTranSettleList().add(tran);
					} else {
						TranCloSumLot tranCloSumlLot = new TranCloSumLot(cusip, lotnum);
						tranCloSumlLot.getTranSettleList().add(tran);
						tranLotHM.put(lotnum, tranCloSumlLot);
					}
				}

				// 把上一个关帐日的clo记录按照lot放入到tranLotHM
				for (TranClo lastClo : lastCloList) {
					int lotnum = lastClo.getLotnum();
					if (tranLotHM.containsKey(lotnum)) {
						tranLotHM.get(lotnum).setTclo(lastClo);
					} else {
						TranCloSumLot tranCloSumlLot = new TranCloSumLot(cusip, lotnum);
						tranCloSumlLot.setTclo(lastClo);
						tranLotHM.put(lotnum, tranCloSumlLot);
					}
				}

				// 遍历所有的lot,再次计算
				for (Map.Entry<Integer, TranCloSumLot> entry : tranLotHM.entrySet()) {
					// 相同lot的tsum计算
					TranCloSumLot tranCloSumLot = entry.getValue();
					TranSum tSum = new TranSum(tranCloSumLot.getCusip());
					if (tranCloSumLot.getTclo() != null) {
						tSum = copyTClo2TSum(tranCloSumLot.getTclo());
					}

					List<Tran> tranList = tranCloSumLot.getTranList();
					for (int i = 0; i < tranList.size(); i++) {
						Tran trandata = tranList.get(i);
						tSum = postIssues.calPosition(trandata, tSum);
					}
					tsumList.add(tSum);
				}
			}

			List<TranClo> cloList = new ArrayList<TranClo>();
			for (TranSum tsum : tsumList) {
				Date lastivetyDate = tsum.getLastActivetyDate();
				if (tsum.getPosclosed().equals(PostStatusType.sumOpen)
						|| (tsum.getPosclosed().equals(PostStatusType.sumClosed) && !endDate.after(lastivetyDate))) {
					// 和估增日记账对应匹配
					// loadSecGenerelJNLRecord--tsum.getPosclosed().equals(PostStatusType.sumClosed)
					// && date.compareTo(tsum.getLastActivetyDate())>0
					TranCloSumLot tranCloSumLot=tranLotHM.get(tsum.getLotnum());
					TranClo clo = copyTSum2TClo(tsum, endDate, tranCloSumLot.getTclo(), tranCloSumLot.getTranList(), tranCloSumLot.getTranSettleList());

					if(clo!=null){
						if(!JixuUserUtil.isJoyinTablesFlag()) {
							//生成每天的浮动费率，只有在费用模块配置，并指定某个资产类型，才会正常工作。不要过多配置，会影响性能
							//目前主要用于银行某个产品，作为批次管理，每天计提收益使用
							genFeeFollowClo(clo, tranCloSumLot.getTranList());
							cloList.add(clo);
						}else {
							cloList.add(clo);
						}
					}
				}
			}

			return cloList;
		}
	}

	/**
	 * 把tsum头寸复制到tranclo记录
	 * 
	 * @param tsum
	 *            endDate当天的头寸(tsum)
	 * @param endDate
	 *            截至日期
	 * @return endDate当天的头寸tranClo
	 */
	private TranClo copyTSum2TClo(TranSum tsum, Date endDate, TranClo oldClo, List<Tran> tranList, List<Tran> settleTranList) {
		TranClo clo = new TranClo();
		double price = commfuncService.loadCusipPrice(tsum.getPortfolio(), tsum.getCusip(), endDate);
		PortfolioHdr portfolio = commfuncService.loadPortfolio(tsum.getPortfolio());
		IssueInfo sec = issueInfoDao.loadIssueByCusip(tsum.getCusip());
		double prinFxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getPriCur(), endDate);
		
		double incFxRate=0;
		if(sec.getIssueType()==AssetTypes.SWAP_INTEREST && tsum.getLsindicator()==LongShortType.ShortTerm)
			//利率互换，空方用2相关的空方字段
			incFxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getIncomeCur2(), endDate);
		else
			incFxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getIncomeCur(), endDate);
			
		clo.setAmount(tsum.getAmount());
		clo.setCusip(tsum.getCusip());
		/*
		 * if(tsum.getLsindicator()==LongShortType.ShortTerm){
		 * clo.setgCost(-1*tsum.getgCost());
		 * clo.setgCostFunc(-1*tsum.getgCostfunc()); }else{
		 */
		clo.setgCost(tsum.getgCost());
		clo.setgCostFunc(tsum.getgCostfunc());
		
		if(sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
			//如果股票当天没有维护行情，默认用交易的价格作为行情	20191118
			//(1) 新股中签时，没有行情维护
			//(2) 股票上市后，每天都有行情，不会存在没有股票行情的状况
			if(price==0) {
				RoundUtil.round(price=tsum.getgCost()/tsum.getAmount(),16);
			}
		}
		
		clo.setPrice(price);
		clo.setgMkValue(RoundUtil.roundMoney2Dec(tsum.getAmount() * price));
		clo.setIncFxRate(incFxRate);
		clo.setLotnum(tsum.getLotnum());
		clo.setLstTrannum(tsum.getLstTrannum());
		clo.setPortfolio(tsum.getPortfolio());
		clo.setPrinFxRate(prinFxRate);
		clo.setCloseDate(endDate);
		clo.setAltRate(tsum.getAltRate());
		clo.setLsttradeDate(tsum.getLsttradeDate());
		clo.setLstSettledate(tsum.getLstSettledate());
		clo.setAltDate(DateUtil.ConvertUtil2Sql(tsum.getAltDate()));
		clo.setSecacct(tsum.getSecacct());
		clo.setManagerID(tsum.getManagerID());
		clo.setLsindicator(tsum.getLsindicator());
		clo.setLastActivetyDate(tsum.getLastActivetyDate());
		// 摊余成本 标记位
		clo.setValuationType(tsum.getValuationType());
		
		SecurityAssetService assetService = secCoreFactory.createSecAssetTypeBean(sec.getIssueType());
		AssetJobService jobService = assetService.createJobService();
		clo = jobService.closeTsum2TcloJob(portfolio, sec, clo, oldClo, tranList, settleTranList);
		
		if(clo!=null){
			if (tsum.getValuationType()==ValuationType.FV) {
				clo.setAmrValue(clo.getgCost());
				clo.setCycleStartAmrValue(clo.getgCost());
			}
		}
		
		return clo;
	}

	/**
	 * 把tranclo头寸复制到transum记录
	 * 
	 * @param clo
	 *            关帐头寸
	 * @return tsum头寸
	 */
	private TranSum copyTClo2TSum(TranClo clo) {
		TranSum tsum = new TranSum();
		tsum.setAltDate(clo.getAltDate());
		tsum.setAltRate(clo.getAltRate());
		tsum.setAmount(clo.getAmount());
		tsum.setCusip(clo.getCusip());
		tsum.setFxTranFlg(FxTranType.commonTran); // 非fxtran，如果是，就需要设值amount2等值
		tsum.setgCost(clo.getgCost());
		tsum.setgCostfunc(clo.getgCostFunc());
		tsum.setLotnum(clo.getLotnum());
		tsum.setLastActivetyDate(clo.getLastActivetyDate());
		tsum.setLsindicator(clo.getLsindicator());
		tsum.setLsttradeDate(clo.getLsttradeDate());
		tsum.setLstSettledate(clo.getLstSettledate());
		// tsum.setLstSettledate(lstSettledate);
		tsum.setLstTrannum(clo.getLstTrannum());
		tsum.setManagerID(clo.getManagerID());
		tsum.setPortfolio(clo.getPortfolio());
		if (clo.getAmount() == 0) {
			tsum.setPosclosed(PostStatusType.sumClosed);
		} else {
			tsum.setPosclosed(PostStatusType.sumOpen);
		}
		tsum.setSecacct(clo.getSecacct());
		tsum.setValuationType(clo.getValuationType());

		return tsum;
	}

	/**
	 * 获取查询当日的该股票的头寸数据
	 * 
	 * @param portnum
	 *            投资组合
	 * @param cusip
	 *            证券
	 * @param lotnum
	 *            lot编号
	 * @param endDate
	 *            截至日
	 * @return 当天头寸
	 */
	public TranClo getSecPosition(String portnum, String cusip, int lotnum,
			Date endDate, boolean classFetchFlag) {
		TranCloType tranCloType = new TranCloType(portnum, cusip, endDate);
		List<TranClo> cloList = getSecClo(tranCloType, classFetchFlag);
		for (TranClo clo : cloList) {
			if (clo.getLotnum() == lotnum) {
				return clo;
			}
		}
		return null;
	}

	private List<TranClo> getPositions(String portnum, Date endDate,
			List<TranClo> lastDayCloList, List<Tran> todayTranList, List<Tran> todaySettleTranList, boolean classFetchFlag) {
		HashMap<String, TranCloType> cusipHM = new HashMap<String, TranCloType>();
		// 把已有的头寸放入到cusipHM
		for (TranClo lastclo : lastDayCloList) {
			if (cusipHM.containsKey(lastclo.getCusip())) {
				cusipHM.get(lastclo.getCusip()).getLastClo().add(lastclo);
			} else {
				TranCloType tranclotype = new TranCloType(portnum, lastclo.getCusip(), endDate);
				tranclotype.getLastClo().add(lastclo);
				cusipHM.put(lastclo.getCusip(), tranclotype);
			}
		}

		// 把上一个关帐日到这个做账日之间的交易放入到cusipHM
		for (Tran todayTran : todayTranList) {
			if (cusipHM.containsKey(todayTran.getCusip().getId())) {
				cusipHM.get(todayTran.getCusip().getId()).getPeriodTrans().add(todayTran);
			} else {
				TranCloType tranclotype = new TranCloType(portnum, todayTran.getCusip().getId(), endDate);
				tranclotype.getPeriodTrans().add(todayTran);
				cusipHM.put(todayTran.getCusip().getId(), tranclotype);
			}
		}
		
		for (Tran settleTran : todaySettleTranList) {
			if(settleTran.getTrantype()==TransTypes.SecContribution)
				continue;
			if (cusipHM.containsKey(settleTran.getCusip().getId())) {
				cusipHM.get(settleTran.getCusip().getId()).getPeriodSettleTrans().add(settleTran);
			} else {
				TranCloType tranclotype = new TranCloType(portnum, settleTran.getCusip().getId(), endDate);
				tranclotype.getPeriodSettleTrans().add(settleTran);
				cusipHM.put(settleTran.getCusip().getId(), tranclotype);
			}
		}

		List<TranClo> totalCloList = new ArrayList<TranClo>();
		for (Map.Entry<String, TranCloType> entry : cusipHM.entrySet()) {
			TranCloType tranCloType = entry.getValue();
			tranCloType.setCloJobFlag(true);
			List<TranClo> tmpTsumList = getSecClo(tranCloType, classFetchFlag);
			if (tmpTsumList.size() > 0) {
				totalCloList.addAll(tmpTsumList);
			}
		}
		return totalCloList;
	}

	/**
	 * 获取某投资组合下，某种证券类型当天的所有头寸
	 * 
	 * @param portnum 投资组合
	 * @param endDate 出账日
	 * @param assetType 资产类型
	 * @param classFetchFlag 是否获取子组合的头寸    
	 * @return 当天所有的头寸
	 */
	public List<TranClo> getSecClo(String portnum, Date endDate, String assetType, boolean classFetchFlag) {
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		List<TranClo> todayCloList = tranCloDao.loadTranCloListByAssetType(portnum, endDate, assetType);

		// 如果当天就有头寸,则直接返回
		if (todayCloList.size() > 0) {
			return todayCloList;
		}

		// 获取当天需要关帐的证券
		// 1.先查昨天关帐表里的证券 2.查当天有交易的证券 汇总在一起
		List<TranClo> lastDayCloList = new ArrayList<TranClo>();
		if (portfolio.getLastTranClose().compareTo(endDate) == -1) {
			lastDayCloList = tranCloDao.loadTranCloListByAssetType(portnum, portfolio.getLastTranClose(), assetType);
		}

		// 查询当天有交易的证券
		Date lastCloseDate = portfolio.getLastTranClose();
		List<Tran> todayTranList = tranDao.loadTransInPeriodWithAssetType(portnum, lastCloseDate, endDate, assetType);
		List<Tran> todaySettleTranList = tranDao.loadTransInPeriodSettleWithAssetType(portnum, lastCloseDate, endDate, assetType);

		return getPositions(portnum, endDate, lastDayCloList, todayTranList, todaySettleTranList, classFetchFlag);
	}

	/**
	 * 获取某投资组合下，当天所有的头寸
	 * 
	 * @param portnum
	 *            投资组合
	 * @param endDate
	 *            出账日
	 * @return 当天所有的头寸
	 */
	public List<TranClo> getSecClo(String portnum, Date endDate, boolean classFetchFlag) {
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		
		List<TranClo> todayCloList = tranCloDao.loadTranCloList(portnum,portList, endDate, classFetchFlag);
		// 如果当天就有头寸,则直接返回
		if (todayCloList.size() > 0) {
			return todayCloList;
		}

		// 获取当天需要关帐的证券
		// 1.先查昨天关帐表里的证券 2.查当天有交易的证券 汇总在一起
		List<TranClo> lastDayCloList = new ArrayList<TranClo>();
		if (portfolio.getLastTranClose().compareTo(endDate) == -1) {
			lastDayCloList = tranCloDao.loadTranCloList(portnum,portList, portfolio.getLastTranClose(), classFetchFlag);
		}

		// 查询当天有交易的证券
		Date lastCloseDate = portfolio.getLastTranClose();
		List<Tran> todayTranList = tranDao.loadTransInPeriod(portnum, portList, lastCloseDate, endDate, classFetchFlag);
		List<Tran> todaySettleTranList = tranDao.loadTransInPeriodSettle(portnum, portList, lastCloseDate, endDate, classFetchFlag);
		
		return getPositions(portnum, endDate, lastDayCloList, todayTranList, todaySettleTranList, classFetchFlag);
	}

	/**
	 * 如果以结转日为基,获取头寸。 比如cashflowcfg里配置以结转日为T+N开始计息
	 * 
	 * @param portnum
	 *            投资组合
	 * @param cusip
	 *            系统证券编号
	 * @param lotnum
	 *            lotnum
	 * @param endDate
	 *            截止日
	 * @return 头寸
	 */
	public TranSum getSecPositionBySetDate(String portnum, String cusip, int lotnum, Date endDate) {
		List<Tran> tranList = tranDao.loadPositionTransBySetDate(portnum, cusip, lotnum, endDate);
		TranSum tSum = new TranSum();

		for (int i = 0; i < tranList.size(); i++) {
			Tran trandata = tranList.get(i);
			if (i == 0) {
				tSum.setCusip(trandata.getCusip().getId());
			}

			tSum = postIssues.calPosition(trandata, tSum);
		}
		return tSum;
	}
	
	/**
	 * 根据每天的头寸，生成配置的动态费用
	 * @param clo
	 */
	private void genFeeFollowClo(TranClo clo, List<Tran> transList){
		String cusip=clo.getCusip();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
		List<Integer> assetfeeFollowList=tranFeeDefDao.getAssetFeeFollowList();
		if(assetfeeFollowList.contains(sec.getIssueType())){
			int lotnum=clo.getLotnum();
			String portnum=clo.getPortfolio();
			PortfolioHdr portfolio=commfuncService.loadPortfolio(clo.getPortfolio());
			List<FollowFeeBean> feeBeans=tranFeeFollowCloDao.getFollowFeeListWithCusip(portnum, cusip, lotnum);
			
			double lastFxRate=commfuncService.loadFxrate(portnum, portfolio.getCurID(), sec.getPriCur(), portfolio.getLastGLClose());
			double todayFxRate=commfuncService.loadFxrate(portnum, portfolio.getCurID(), sec.getPriCur(), clo.getCloseDate());
			List<Date> holidays=commfuncService.getHolidays(portnum);

			Set<TranFeeFollowClo> feeSet=new HashSet<TranFeeFollowClo>();
			List<TranFeeFollowClo> oldFees=tranFeeFollowCloDao.getTranFeeFollowClos(portnum, portfolio.getLastGLClose(), cusip, lotnum);
			
			//费用交易
			List<Tran> feeTrans=new ArrayList<Tran>();
			for(Tran feetran:transList){
				if(feetran.getTrantype()==TransTypes.ShareBankZGPayFee)
					feeTrans.add(feetran);
				else if(feetran.getTrantype()==TransTypes.ShareBankZGMaturity && feetran.getProceeds2()<0){
					//赎回的时候有回拨款  Proceeds2为正的时候，为绩效费，负的时候会回拨款
					feeTrans.add(feetran);
				}
			}
			
			for(FollowFeeBean feeban:feeBeans){
				double rate=feeban.getFeeAmount();
				CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(feeban.getCfcID());
				//获取建立头寸的交易
				Tran tran=tranDao.loadTranLot1(portnum, cusip, lotnum);
				Date tradeDate=tran.getTradeDate();
				if(feeban.getFixFlag()==ExpenseBaseType.FloatExpense){
					//计算当天的费用
					CalInterestModel intModel = new CalInterestModel();
					intModel.setCashFlowCfg(cfc);
					intModel.setBookNBDays(portfolio.getBookNBDays());
					intModel.setAcuDate(clo.getAltDate());
					// 以定期存款为例,如果是交易日开始计息,有一笔卖,正好以卖出日剩余的股数开始计息
					// 有潜在问题,如果是T+1计息,卖出部分当天就不会计息了 to-do pending
					intModel.setTradeDate(tradeDate);
					intModel.setLastCalDate(portfolio.getLastGLClose());
					intModel.setCurDate(clo.getCloseDate());
					intModel.setAcRate(rate);
					intModel.setAmount(clo.getgCost());
					intModel.setLastFxRate(lastFxRate);
					intModel.setTodayFxRate(todayFxRate);
					intModel.setHolidayList(holidays);

					LocalFuncResult intResult = incomeExpenseCal.getInterest(intModel);
					double todayfee=intResult.getLocal()*-1;
					double fee=todayfee;
					//获取前一天的费用，两者相加，是截至今天总的费用
					for(TranFeeFollowClo oldFeeClo:oldFees){
						if(oldFeeClo.getTranFeeDefID()==feeban.getFeeCfgID()){
							fee=fee+oldFeeClo.getFeeamount();
						}
					}
					
					//费用的clo记录，如果当天存在付费交易，则在费用头寸里减去
					for(int i=0;i<feeTrans.size();i++){
						Tran feetran=feeTrans.get(i);
						if(feetran.getTrantype()==TransTypes.ShareBankZGPayFee){
							TranBasic tranBasic=feetran.getTanum();
							int feeType=Integer.valueOf(tranBasic.getContract());
							if(feeType==feeban.getFeeCfgID()){
								fee=RoundUtil.roundMoney2Dec(fee-feetran.getAmount());
								feeTrans.remove(i);
								i=i-1;
								continue;
							}
						}else if(feetran.getTrantype()==TransTypes.ShareBankZGMaturity){
							//这里增加缓存
							PerformanceFeeCfg pfc=performanceFeeCfgDao.loadActivePerformanceFeeCfg(portnum, cusip, lotnum);
							if(pfc!=null){
								int feeType=pfc.getClawBackExpenseType();
								if(feeType==feeban.getFeeCfgID()){
									fee=RoundUtil.roundMoney2Dec(fee+feetran.getProceeds2());
									feeTrans.remove(i);
									i=i-1;
									continue;
								}
							}
						}
					}
					
					if(fee!=0){
						TranFeeFollowClo feefolclo=new TranFeeFollowClo();
						feefolclo.setPortnum(portnum);
						feefolclo.setCusip(cusip);
						feefolclo.setLotnum(lotnum);
						feefolclo.setTranClo(clo);
						feefolclo.setTranFeeDefID(feeban.getFeeCfgID());
						feefolclo.setCloseDate(clo.getCloseDate());
						feefolclo.setAmount(clo.getAmount());
						feefolclo.setFeeamount(RoundUtil.roundMoney2Dec(fee));
						feefolclo.setFeeamounttoday(todayfee);
						feefolclo.setFeeRate(rate);
						feeSet.add(feefolclo);
					}
				}
			}
			clo.setTranFeeFollowCloSet(feeSet);
		}
	}
	
	//计算当天应收利息
	public double incomeCal(int bkCashAcctID, Date startDate, Date ledgerDate,double balanceAmt) {
		if(JixuUserUtil.isJoyinTablesFlag())
			return 0;
		
		BkCashAcct bcAcct=bkCashAcctDao.loadBankCash(bkCashAcctID);
		BkInteAcct bkInteAcct = bkInteAcctDao.loadBkInteAcct(bcAcct.getIntCshflwMasterRec());
		if(bkInteAcct!=null){
			List<BkInteRatio> ratioList = bkInteRatioDao.loadBkInteRatio(bkInteAcct.getId());
			double ratio=0;
			if (ratioList == null || ratioList.size() == 0)
				ratio = 0;
			for (int k = 0; k < ratioList.size(); k++) {
				BkInteRatio bnInteRatio = ratioList.get(k);
				if (!startDate.before(bnInteRatio.getStartdate())) {
					if (ratioList.size() == 1) {
						ratio = bnInteRatio.getPrice();
					} else if (k == ratioList.size() - 1) {
						ratio = bnInteRatio.getPrice();
					} else if (startDate.before(ratioList.get(k + 1).getStartdate())) {
						ratio = bnInteRatio.getPrice();
					}
					if (ratio > 0) {
						break;
					}
				}
			}
			return incomeExpenseCal.incomeCal(bkInteAcct.getCashFlowCfg().getIntCode(), startDate, ledgerDate, ratio, balanceAmt);
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 	根据joyin手工凭证更新当天头寸
	 * @param tran
	 * @return
	 */
	public void genCloByManualVoucher(TranBasic tranbasic, ResultContainer container) {
		postIssues.postIssue(tranbasic, container);
		if(container.isCorrectFlag()) {
			//获取post的交易，得到lotnum，查询当天是否有tranclo
			Tran tran=tranDao.loadTranByTanum(tranbasic.getId());
			TranClo clo=tranCloDao.loadTranClo(tranbasic.getPortfolio(), tran.getLedgerDate(), tran.getCusip().getId(), tran.getLotnum());
			if(clo!=null) {
				//如果已有头寸存在，在原头寸上做修改
				clo.setAmount(RoundUtil.roundMoney2Dec(clo.getAmount()+tran.getAmount()));
				clo.setgCost(RoundUtil.roundMoney2Dec(clo.getgCost()+tran.getgCost()));
				clo.setgCostFunc(RoundUtil.roundMoney2Dec(clo.getgCostFunc()+tran.getgCostFunc()));
				clo.setgMkValue(RoundUtil.roundMoney2Dec(clo.getgMkValue()+tranbasic.getCash()));
				clo.setPrinFxRate((RoundUtil.roundMoney2Dec(clo.getgMkValue()*clo.getPrinFxRate())+tranbasic.getCash2())/clo.getgMkValue());
				tranCloDao.updateEntity(clo);
			}else{
				List<Tran> tranlist=new ArrayList<Tran>();
				tranlist.add(tran);
				List<Tran> settranlist=new ArrayList<Tran>();
				TranSum tsum=tranSumDao.loadTSumByCusipLot(tranbasic.getPortfolio(), tran.getCusip().getId(), tran.getLotnum());
				clo=copyTSum2TClo(tsum, tran.getLedgerDate(), null, tranlist, settranlist);
				if(clo!=null) {
					//如果已有头寸存在，在原头寸上做修改
					clo.setAmount(tran.getAmount());
					clo.setgCost(tran.getgCost());
					clo.setgCostFunc(tran.getgCostFunc());
					clo.setgMkValue(tran.getgCost());
						//第一次建头寸，市值即成本
					if(clo.getgCost()>0)
						clo.setPrinFxRate(clo.getgCostFunc()/clo.getgCost());
					
					if(tranbasic.getCash()>0) {
						//估增加上成本
						clo.setgMkValue(RoundUtil.roundMoney2Dec(clo.getgMkValue()+tranbasic.getCash()));
						clo.setPrinFxRate((RoundUtil.roundMoney2Dec(clo.getgMkValue()*clo.getPrinFxRate())+tranbasic.getCash2())/clo.getgMkValue());
					}
					
					tranCloDao.saveEntity(clo);
				}
			}
		}
	}
}
