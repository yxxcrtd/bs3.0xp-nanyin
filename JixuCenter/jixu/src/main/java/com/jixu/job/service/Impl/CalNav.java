package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.NetCategory;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.NavBankZiGuanDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.dao.intf.ValuationReportDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.data.service.OutDataInterface;
import com.jixu.job.obj.NavAssetModel;
import com.jixu.job.service.CalNavService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.Nav;
import com.orm.obj.NavBankZiGuan;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;
import com.orm.obj.TranFeeFollowClo;

@Service("calNavService")
public class CalNav implements CalNavService{
	
	private static final Logger logger = LoggerFactory.getLogger(CalNav.class);
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private ValuationReportDao valuationReportDao;
	
	@Autowired
	private NavBankZiGuanDao navBankZiGuanDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="JixuTbValuationReport")
	private OutDataInterface outDate;
	
	@Resource(name="secPositionCenter")
	private SecPositionCenter secPosition;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;
	
	private int jobtype=JobsType.calNav;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createNav(int userID, String[] portnums, Date calDate){
		ResultContainer mainContainer=new ResultContainer(); 
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		
		for(String portnum:portnums){
			ResultContainer container=createNav(userID, portnum, calDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createNav(int userID, String portnum, Date calDate){
		logger.info(portnum + ": 计算净值...");
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		Date latestNavDate=commfuncService.loadLatestNavDate(portnum, calDate);
		if(latestNavDate.compareTo(calDate)==0){
			container.setCorrectFlag(true);
			container.setEntityObj(portfolio);
			container.setErrorCode(ReturnResult.nav_alread_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.nav_alread_exist));
			return container;
		}
		
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		List<NavAssetModel> netAssetList=voucherDetailsDao.getNetAsset(portList, calDate);
		ConcurrentHashMap<Integer, Double> hashMap=new ConcurrentHashMap<Integer, Double>();
		for (NavAssetModel navAssetModel : netAssetList) {
			hashMap.put(navAssetModel.getCategoryType(), navAssetModel.getCategoryAmount());
		}
		container.setEntityObj(portfolio);
		container.setHeader(JobsType.jobsTypeHM.get(jobtype));

		// || calDate.compareTo(portfolio.getLastGLClose())==0 && portfolio.getLastGLClose().compareTo(portfolio.getNextGLClose())==0)
		if(portfolio.getLastTranClose().compareTo(calDate)==0 && calDate.after(portfolio.getLastGLClose())){
			double assets=hashMap.get(NetCategory.Assets)==null?0:hashMap.get(NetCategory.Assets);
			double liabilitis=hashMap.get(NetCategory.Liabilitis)==null?0:hashMap.get(NetCategory.Liabilitis);
			double income=hashMap.get(NetCategory.Income)==null?0:hashMap.get(NetCategory.Income);
			double expenses=hashMap.get(NetCategory.Expenses)==null?0:hashMap.get(NetCategory.Expenses);
			double capital=hashMap.get(NetCategory.Capital)==null?0:hashMap.get(NetCategory.Capital);
			double capitalRG=hashMap.get(NetCategory.CapitalRG)==null?0:hashMap.get(NetCategory.CapitalRG);
			double capitalUnReal=hashMap.get(NetCategory.CapitalUnReal)==null?0:hashMap.get(NetCategory.CapitalUnReal);
			
			
			
			Nav nav1=new Nav();
			nav1.setPortfolio(portfolio);
			nav1.setNavDate(calDate);
			nav1.setClassID(portfolio.getClassid());
			nav1.setAsset(assets);
			nav1.setLiabilities(-1*liabilitis);
			
			
			Nav nav2=new Nav();
			capital=-1*capital;
			nav2.setPortfolio(portfolio);
			nav2.setNavDate(calDate);
			nav2.setClassID(portfolio.getClassid());
			nav2.setIncome(-1*income);
			nav2.setExpenses(expenses);
			nav2.setCapital(capital);
			nav2.setCapitalRG(-1*capitalRG);
			nav2.setCapitalUnreal(capitalUnReal);
			
			double sharesOutStanding=commfuncService.calFundShares(portnum, calDate);
			
			double sharesSettled=sharesOutStanding;
			
			nav2.setSharesOutStanding(sharesOutStanding);
			nav2.setSharesSettled(sharesSettled);
			
			
			//净资产=资产-负债
			double netAssets1=RoundUtil.roundMoney2Dec(nav1.getAsset()-nav1.getLiabilities());
			
			//净资产=资本+已实现收益+未实现收益+收入-开支
			double netAssets2=RoundUtil.roundMoney2Dec(nav2.getCapital()+nav2.getCapitalRG()-nav2.getCapitalUnreal()+nav2.getIncome()-nav2.getExpenses());
			
			if(netAssets1!=netAssets2){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.cal_nav_unmatched_falied);

				StringBuilder errmsg=new StringBuilder();
				errmsg.append(ReturnResult.getResultHM().get(ReturnResult.cal_nav_unmatched_falied));
				errmsg.append("\r\n");
				errmsg.append("(资产-负债): ");
				errmsg.append(DataConvert.convertCash2US(netAssets1));
				errmsg.append("\r\n");
				errmsg.append("(资本+已实现收益+未实现收益+收入-开支): ");
				errmsg.append(DataConvert.convertCash2US(netAssets2));
				container.setErrorMsg(errmsg.toString());
			}/*else if( nav2.getSharesOutStanding()<=0){
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.cal_nav_no_capital);
	
				StringBuilder errmsg=new StringBuilder();
				errmsg.append(ReturnResult.getResultHM().get(ReturnResult.cal_nav_no_capital));
				container.setErrorMsg(errmsg.toString());
			}*/else{
				double netAssetPerShare=0;
				if(nav2.getSharesOutStanding()>0)
					netAssetPerShare=RoundUtil.roundMoney(netAssets1/sharesOutStanding,portfolio.getNavPrecision());
				
				nav2.setNavAmount(netAssetPerShare);
				
				navDao.saveEntity(nav1);
				navDao.saveEntity(nav2);
				
				container.setErrorMsg("单位净值: " + netAssetPerShare + "   |   " +  "净资产: " + DataConvert.convertCash2US(netAssets1));
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.cal_nav_unmatchedDate_falied);
			StringBuilder errmsg=new StringBuilder();
			errmsg.append(ReturnResult.cal_nav_unmatchedDate_falied);
			
			errmsg.append("\r\n");
			errmsg.append("交易关帐日: ");
			errmsg.append(portfolio.getLastTranClose());
			errmsg.append("\r\n");
			errmsg.append("总账关帐日: ");
			errmsg.append(portfolio.getLastGLClose());
			errmsg.append("\r\n");
			errmsg.append("净值计算日: ");
			errmsg.append(calDate);
			container.setErrorMsg(errmsg.toString());
		}
		
		commfuncService.updateJobsHis(userID, portfolio, calDate, jobtype, container);
		
		logger.info(portnum + ": 计算净值结束...");
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteNav(int userID, String[] portnums, Date navDate){
		ResultContainer mainContainer=new ResultContainer(); 
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		
		for(String portnum:portnums){
			ResultContainer container=deleteNav(userID, portnum, navDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteNav(int userID, String portnum, Date navDate){
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		container.setEntityObj(portfolio);
		container.setHeader(ReturnResult.getDicHM().get(ReturnResult.delete_chinese) + JobsType.jobsTypeHM.get(jobtype));
		
		if(!navDate.after(portfolio.getLastGLClose())){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.del_nav_GlClose);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.del_nav_GlClose));
		}else{
			navDao.deleteNav(portnum, navDate);
			navBankZiGuanDao.deleteNavBankZG(portnum, navDate);
			jobsHisDao.deleteJobsHist(portnum, navDate, jobtype);
		}
		
		return container;
	} 
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void genValuationReport(String[] portnums, Date navDate) {
		for(String portnum:portnums){
			//生成估值表
			List<?> results=outDate.genResults(portnum, DateUtil.ConvertUtil2Sql(navDate));
			//valuationReportDao.saveAllEntity(results);
			for (Object object : results) {
				valuationReportDao.saveEntityBean(object);
			}
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void genValuationReport(String portnum, Date navDate) {
		//生成估值表
		List<?> results=outDate.genResults(portnum, DateUtil.ConvertUtil2Sql(navDate));
		//valuationReportDao.saveAllEntity(results);
		for (Object object : results) {
			valuationReportDao.saveEntityBean(object);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void delValuationReport(String[] portnums, Date navDate) {
		for(String portnum:portnums){
			valuationReportDao.deleteValuationReport(portnum, DateUtil.ConvertUtil2Sql(navDate));
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void calBankZiGuanNav(String portnum, Date asOfDate) throws JNLException{
		//查询当天组合下所有的资管批次头寸
		List<TranClo> cloList=secPosition.getSecClo(portnum, asOfDate, AssetTypes.BankZiGuan, false);
		HashMap<String, Double> feeHM=new HashMap<String, Double>(); 	//存放每个批次当天的费用开销
		if(cloList.size()>0){
			//查询当天资管批次费用
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			List<TranFeeFollowClo> todayfeeList=tranFeeFollowCloDao.getFollowFeeList(portnum, asOfDate);
			List<NavBankZiGuan> oldNavZiguanList=navBankZiGuanDao.loadNavBankZiGuan(portnum, portfolio.getLastGLClose());
			
			//存放前一个做账日的资管批次净值信息
			double oldTotalNavAsset=0;
			HashMap<String, NavBankZiGuan> oldNavZGHM=new HashMap<String, NavBankZiGuan>();
			for(NavBankZiGuan oldZGNav:oldNavZiguanList){
				oldNavZGHM.put(oldZGNav.getCusip()+"-"+oldZGNav.getLotnum(), oldZGNav);
				oldTotalNavAsset=oldTotalNavAsset+oldZGNav.getNetAsset();
			}
			
			//获取上一个做账日~本做账日期间内的交易 
			List<Tran> todayZiguanTrans=tranDao.loadTransInPeriodWithAssetType(portnum, portfolio.getLastGLClose(), asOfDate, AssetTypes.BankZiGuan);	
			double todayTotalSubRed=0;
			HashMap<String, List<Tran>> zgTransHM=new HashMap<String, List<Tran>>();
			for(Tran tran:todayZiguanTrans){
				String key=tran.getCusip().getId()+"-"+tran.getLotnum();
				if(zgTransHM.containsKey(key)){
					zgTransHM.get(key).add(tran);
				}else{
					List<Tran> transList=new ArrayList<Tran>();
					transList.add(tran);
					zgTransHM.put(key, transList);
				}
				
				//获取当天总的申购和赎回对应的金额
				SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
				boolean buysalefalg=secBasic.buySaleFlag();
				if(buysalefalg){
					todayTotalSubRed=todayTotalSubRed+tran.getgCost();
				}else{
					todayTotalSubRed=todayTotalSubRed-tran.getProceeds();
				}
			}
			
			double totalZiGuanFee=0;//当天所有的资管费用加总
			for(TranFeeFollowClo feeclo:todayfeeList){
				totalZiGuanFee=totalZiGuanFee+feeclo.getFeeamounttoday();
				String key=feeclo.getCusip()+"-"+feeclo.getLotnum();
				if(feeHM.containsKey(key)){
					feeHM.put(key, RoundUtil.roundMoney2Dec(feeHM.get(key)+feeclo.getFeeamounttoday()));
				}else{
					feeHM.put(key, feeclo.getFeeamounttoday());
				}
			}
			
			//把固定收益也给
			for(TranClo clo:cloList){
				totalZiGuanFee=totalZiGuanFee+clo.getTodayInterest()*-1;
				String key=clo.getCusip()+"-"+clo.getLotnum();
				if(feeHM.containsKey(key)){
					feeHM.put(key, RoundUtil.roundMoney2Dec(feeHM.get(key)+clo.getTodayInterest()*-1));
				}else{
					feeHM.put(key, clo.getTodayInterest()*-1);
				}
			}
			
			
			
			NavAssetObj todayNav=navDao.getNav(portnum, asOfDate);
			NavAssetObj lastNav=navDao.getNav(portnum, portfolio.getLastGLClose());
			double todayExpense=RoundUtil.roundMoney2Dec(todayNav.getExpense()-lastNav.getExpense());	//组合当天的开支
			double todayIncomes=RoundUtil.roundMoney2Dec(todayNav.getIncomes()-lastNav.getIncomes());	//组合当天的收入
			/*if(portfolio.isJzgyFlag()){
				//如果是货币型基金，把当天货币基金净值归一而降低的净值加回来
				String chartnum=portfolio.getCoaahdr().getId();
				int groupnum=portfolio.getCoaaGroup().getGroupNum();
				String acct=jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.share_Div2Share);
				double diff=-1*jobDAO.getCloseBalance(portnum, portfolio.getLastTranClose(), acct);
				todayNetAsset=todayNetAsset+diff;
			}*/
			
			//当天组合范围内的损益	产品当天总的收入-产品当天总的开支+该批次当天
			double portExpInc=todayIncomes-(todayExpense-totalZiGuanFee);
			for(TranClo clo:cloList){
				double oldZGNetAsset=0;	//前一天的净资产
				double zgTotalShare=0;
				double todaysubred=0;	//当天申赎的金额
				double externalAmount=0;	//外部数量
				String key=clo.getCusip()+"-"+clo.getLotnum();
				if(oldNavZGHM.containsKey(key)){
					oldZGNetAsset=oldNavZGHM.get(key).getNetAsset();
					zgTotalShare=oldNavZGHM.get(key).getSharesInternal();
					externalAmount=oldNavZGHM.get(key).getSharesEnternal();//以前一天的外部数量作为初始值，加减今天该批次的申赎交易
				}
				
				if(zgTransHM.containsKey(key)){
					List<Tran> tranList=zgTransHM.get(key);
					for(Tran tran:tranList){
						SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
						boolean buysalefalg=secBasic.buySaleFlag();
						if(buysalefalg){
							todaysubred=todaysubred+tran.getgCost();
							zgTotalShare=zgTotalShare+tran.getAmount();
							externalAmount=externalAmount+tran.getAmount2();
						}else{
							todaysubred=todaysubred-tran.getProceeds();
							zgTotalShare=zgTotalShare-tran.getAmount();
							externalAmount=externalAmount-tran.getAmount2();
						}
					}
				}
				
				//暂时用各批次的资产净值比率来计算每天新的资产。也可以用份额，但是如果用份额，在货币基金里，会涉及到如何分红利再投进来的份额
				//数量是负数
				double newZGNetAsset=oldZGNetAsset+todaysubred+RoundUtil.roundMoney2Dec(portExpInc*zgTotalShare/todayNav.getShares())-feeHM.get(key);

				NavBankZiGuan navbank=new NavBankZiGuan();
				navbank.setPortnum(portnum);
				navbank.setCusip(clo.getCusip());
				navbank.setLotnum(clo.getLotnum());
				navbank.setTranCloID(clo.getId());
				navbank.setNavDate(asOfDate);
				navbank.setNetAsset(RoundUtil.roundMoney2Dec(newZGNetAsset));
				navbank.setSharesInternal(clo.getAmount());
				navbank.setSharesEnternal(externalAmount);
				navbank.setGcost(clo.getgCost());

				double navIncrease=0;
				double wfsy=0;
				double lastNAV=0;
				if(oldZGNetAsset!=0){
					NavBankZiGuan oldNavZiguan=oldNavZGHM.get(key);
					lastNAV=RoundUtil.round(oldNavZiguan.getNetAsset()/oldNavZiguan.getSharesEnternal(),5);
					wfsy=RoundUtil.roundMoney(100*(newZGNetAsset-oldZGNetAsset)/oldZGNetAsset*10000,8);
				}else{
					lastNAV=RoundUtil.round(clo.getgCost()/externalAmount,5);
					wfsy=RoundUtil.roundMoney(100*(newZGNetAsset-clo.getgCost())/clo.getgCost()*10000,8);
				}
				
				if(navbank.getSharesEnternal()!=0){
					double todayNAV=RoundUtil.round(navbank.getNetAsset()/navbank.getSharesEnternal(),5);
					navIncrease=RoundUtil.round(100*(todayNAV-lastNAV)/lastNAV,8);
				
					//总的净资产增长率
					double navTotalIncrease=RoundUtil.round(100*(navbank.getNetAsset()-navbank.getGcost())/navbank.getGcost(),3);
				
					navbank.setNavTodayIncrease(navIncrease);
					navbank.setIncomeWf(wfsy);
					navbank.setNavIncrease(navTotalIncrease);
				}
				
				navBankZiGuanDao.saveEntity(navbank);
			}
		}
	}
}
