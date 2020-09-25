package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.func.SystemSelect;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.CashPositionModel;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.types.TranCloType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.mvc.control.model.SecPositionModel;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.mvc.control.service.SecCashService;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashClo;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.Nav;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

@Service("SecCashService")
public class SecCashServiceImpl implements SecCashService{
	
	@Resource(name="postIssues")
	private PostIssueService postIssue;
	
	@Resource(name="commfuncService")
	private CommFuncService comService;
	
	@Autowired
	private BkCashCloDao bkCashCloDao;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	@Autowired
	private BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	
	public Pages<BkCashFlow> getCashFlow(Pages<BkCashFlow> pages, String portNum, Date startDate, Date endDate) {
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<BkCashFlow> page=bkCashFlowDao.getPageHelperCashFlow(PageHelper.startPage(pages.getCurrentPage(), pages.getPageMaxRecords()), portNum, startDate, endDate);
			pages.setResultList(page.getResult());
			pages.setTotalPages(page.getPages());
			return pages;
		}else{
			return bkCashFlowDao.getCashFlow(pages, portNum, startDate, endDate);
		}
	}
	
	public Pages<BkCashRcpy> getCashRcpy(Pages<BkCashRcpy> pages,String portNum, Date startDate, Date endDate) {
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<BkCashRcpy> page=bkCashRcpyDao.getPageHelperCashRcpy(PageHelper.startPage(pages.getCurrentPage(), pages.getPageMaxRecords()), portNum, startDate, endDate);
			pages.setTotalPages(page.getPages());
			pages.setResultList(page.getResult());
			return pages;
		}else{
			return bkCashRcpyDao.getCashRcpy(pages,portNum, startDate, endDate);
		}
	}
	
	public Page<BkCashRcpy> getPageHelperCashRcpy(Page<BkCashRcpy> pages,String portNum, Date startDate, Date endDate) {
		return bkCashRcpyDao.getPageHelperCashRcpy(pages,portNum, startDate, endDate);
	}
	
	public List<CashPositionModel> getCashPosition(String portNum, Date endDate) {
		List<CashPositionModel> cashPositionList=new ArrayList<CashPositionModel>();
		
		PortfolioHdr portfolio=comService.loadPortfolio(portNum);
		if(endDate==null || endDate.after(portfolio.getLastTranClose())){
			//假设没有给日期，就用最新的银行头寸
			if(endDate==null){
				endDate=DateUtil.convertStr2Date("20991231");
			}
			List<CashPositionModel> results=bkCashFlowDao.getCashPosition(portNum, endDate);
			
			for(CashPositionModel cp:results){
				double fxRaterate=comService.loadFxrate(portfolio.getId(),portfolio.getCurID(),cp.getCurCode(), endDate);
				cp.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
				cp.setFuncBalance(RoundUtil.roundMoney2Dec(cp.getLocalBalance()*fxRaterate));
				cp.setCurCode(portfolio.getCurID());
				cp.setFxRate(fxRaterate);
				cp.setPortname(portfolio.getShortName());
				cp.setAsOfDate(DateUtil.convertD2YYYYMMDD(endDate));
				
				cashPositionList.add(cp);
			}
		}else{
			//List<Object[]> results=cashDAO.getCashPosition(portNum, endDate);
			List<BkCashClo> bkList=bkCashCloDao.loadBkCashClo(portNum, endDate);
			
			for(BkCashClo bcc:bkList){
				CashPositionModel cp=new CashPositionModel();
				BkCashAcct bcAcct=bkCashAcctDao.loadBankCash(bcc.getBkCashAcctID());
				cp.setCashacctID(bcc.getBkCashAcctID());
				cp.setBank(bcAcct.getBkinfo().getBankCode());
				cp.setBankAcct(bcAcct.getBkinfo().getBankDesc());
				cp.setBankAcctName(bcAcct.getBankdesc());
				cp.setLocalCurCode(comService.loadFxCurrencyCode(bcAcct.getFxcurrency()));
				cp.setLocalBalance(bcc.getBalanceAmt());
				
				double fxRaterate=comService.loadFxrate(portfolio.getId(),portfolio.getCurID(),bcAcct.getFxcurrency(), endDate);
				cp.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
				cp.setFuncBalance(RoundUtil.roundMoney2Dec(cp.getLocalBalance()*fxRaterate));
				cp.setCurCode(portfolio.getCurID());
				cp.setFxRate(fxRaterate);
				cp.setPortname(portfolio.getShortName());
				cp.setAsOfDate(DateUtil.convertD2YYYYMMDD(endDate));
				cp.setInterestIncome(bcc.getInterestIncome());
				
				cashPositionList.add(cp);
			}
		}
		
		return cashPositionList;
	}
	
	@Override
	public List<SecPositionModel> getSecPositionByCloID(String tranCloID) {
		List<SecPositionModel> spmList=new ArrayList<SecPositionModel>();
		TranClo tranclo=tranCloDao.loadEntity(TranClo.class, tranCloID);	//获取当天的关账头寸
		//获取前一天的关账头寸
		TranClo lastClo=tranCloDao.getLastTranClo(tranclo.getPortfolio(), tranclo.getCloseDate(), 
				tranclo.getCusip(), tranclo.getLotnum());
		
		PortfolioHdr portfolio=comService.loadPortfolio(tranclo.getPortfolio());
		
		SecPositionModel spm=genPositionModel(tranclo, portfolio);
		spmList.add(spm);
		
		if(lastClo!=null){
			SecPositionModel spm1=genPositionModel(lastClo, portfolio);
			spmList.add(spm1);
		}
		
		return spmList;
	}
	
	
	@Override
	public Pages<SecPositionModel> getSecPositions(Pages<SecPositionModel> pages,String portNum, Date endDate) {
		List<SecPositionModel> secpositionList=new ArrayList<SecPositionModel>();
		PortfolioHdr portfolio=comService.loadPortfolio(portNum);
		List<String> cusipList=new ArrayList<String>();
		int totalPags=0;
		//pageHelper分页
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<String> Integerpages=tranSumDao.loadPageHelperTranedSecCusip(PageHelper.startPage(pages.getCurrentPage(), pages.getPageMaxRecords()),portNum);
			cusipList=Integerpages.getResult();
			totalPags=Integerpages.getPages();
		}else{
			Pages<String> Integerpages=tranSumDao.loadTranedSecCusip(pages,portNum);
			cusipList=Integerpages.getResultList();
			totalPags=Integerpages.getTotalPages();
		}
		
		for(String cusip:cusipList){
			SecPositionModel secPosition=getSecPosition(portfolio, cusip, endDate);
			if(secPosition != null){
				secpositionList.add(secPosition);
			}
		}
		pages.setResultList(secpositionList);
		pages.setTotalPages(totalPags);
		return pages;
	}
	
	@Override
	public List<SecPositionModel> getSecPositions(String portnum, Date endDate) {
		List<SecPositionModel> secpositionList=new ArrayList<SecPositionModel>();
		List<TranClo> cloList=secPositionCenter.getSecClo(portnum, endDate, true);
		PortfolioHdr portfolio=comService.loadPortfolio(portnum);
		//查询投资组合下的子投资组合
		//HashMap<Integer, String> portsMap=portCfgDAO.loadPortfolioClassIDMap(portnum);
		for(TranClo clo:cloList){
			SecPositionModel secPosition=genPositionModel(clo, portfolio);
			secpositionList.add(secPosition);
		}
		return secpositionList;
	}
	
	private SecPositionModel genPositionModel(TranClo clo, PortfolioHdr portfolio){
		SecPositionModel secPosition=new SecPositionModel();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
		secPosition.setSecID(sec.getSecurityid());
		secPosition.setMarketDesc(""); //to-do-pending
		secPosition.setSecType(SecurityCoreFactory.getAllAssetTypeHM().get(sec.getIssueType())); 
		secPosition.setSecDesc(sec.getSecurityname());
		secPosition.setRatio(0);
		secPosition.setExpDate("-"); //to-do-pending
		secPosition.setAmount(clo.getAmount());
		secPosition.setCurCode(comService.loadFxCurrencyCode(sec.getPriCur()));
		secPosition.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
		secPosition.setAccrued(clo.getAccrued());
		secPosition.setCost(clo.getgCost());
		secPosition.setFuncCost(clo.getgCostFunc());
		secPosition.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
		secPosition.setFxRate(clo.getPrinFxRate());
		secPosition.setCusip(sec.getId());
		secPosition.setPortname(comService.loadPortfolio(clo.getPortfolio()).getShortName());
		secPosition.setAsOfDate(DateUtil.convertD2YYYYMMDD(clo.getCloseDate()));
		secPosition.setAmortValue(clo.getAmrValue());
		secPosition.setLsindicator(clo.getLsindicator());
		if(Math.abs(clo.getAmount())<0.0001){
			secPosition.setMkValue(0);
			secPosition.setFunMkValue(0);
			secPosition.setAccruedFunc(0);
		}else{
			double mkValue=0;
			double fmkValue=0;
			mkValue=RoundUtil.roundMoney2Dec(clo.getgMkValue());
			fmkValue=RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(clo.getgMkValue()*clo.getPrinFxRate()));
			
			secPosition.setMkValue(mkValue);
			secPosition.setFunMkValue(fmkValue);
			secPosition.setAccruedFunc(RoundUtil.roundMoney2Dec(clo.getAccrued()*clo.getPrinFxRate()));
		}
		
		if(clo.getValuationType()==ValuationType.AMORTIZE){
			if(secPosition.getAmortValue()==secPosition.getCost() && clo.getAltRate()==0){
				secPosition.setMkValue(secPosition.getAmortValue());
				secPosition.setFunMkValue(RoundUtil.roundMoney2Dec(secPosition.getMkValue()*clo.getPrinFxRate()));
			}
			secPosition.setAccrued(RoundUtil.roundMoney2Dec(clo.getAccrued()*clo.getPrinFxRate()));
		}
		
		return secPosition;
	}
	
	// 债券摊销计划
	public List<Object> positionAmortDetail(String secID, String portID,Date endDate) {
		List<Object> tData = new ArrayList<Object>();

		IssueInfo sec = issueInfoDao.loadSecurity(secID); // 查询IssueInfo  取票面价值sec.getContractSize()
		String cfcID = sec.getCashFlowCfgID();
		// 如果cfcID为0则表示该证券没有现金流设置，不必产生摊余计划表, return空列表
		if (cfcID.equals("0")) {
			return tData;
		}
		
		CashFlowCfg cfc = cashFlowCfgDao.loadCashFlowCfg(cfcID); // 查询CashFlowCfg  取票面利率cfc.getCoupon()
		// 查询开始日期和结束日期间摊余成本的债券
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portID);
		List<TranClo> tranCloList = tranCloDao.loadTranCloListBeforeCloseDate(portList, endDate, sec.getId());
		if (tranCloList.size() > 0) {
			double amrValue = 0;
			double preAmrValue=0;
			double interstIncome = 0;
			double interestAccured = 0;
			double mkValue = 0;
			double totalAmount = 0;
			double alrate = 0;
			Date ledgerDate = null;
			List<TranClo> list = new ArrayList<TranClo>();

			for (TranClo tc : tranCloList) { // 对查询到的债券进行处理显示
				
				if (ledgerDate != null&&tc.getCloseDate().compareTo(ledgerDate)!=0) {
					// 每天的合计
					tData = dailyAmort(ledgerDate, alrate, sec, totalAmount,amrValue,preAmrValue, list, interstIncome, cfc,interestAccured, mkValue, tData);
					//期初摊余成本
					preAmrValue=tc.getCycleStartAmrValue();
					//期末摊余成本
					amrValue=tc.getAmrValue();
					// 应收利息
					interestAccured = tc.getTodayInterest();
					// 利息收入  原来是直接查数据库     现在 :利息收入=期末-期初+应收
					interstIncome =RoundUtil.roundMoney2Dec(amrValue-preAmrValue+tc.getTodayInterest());
					mkValue = tc.getgMkValue();
					totalAmount = tc.getAmount();
					alrate = tc.getAltRate();
					// 清空list并存入下一天的数据
					list.removeAll(list);
					list.add(tc);

				} else {
					//期初摊余成本
					preAmrValue=preAmrValue+tc.getCycleStartAmrValue();
					//期末摊余成本
					amrValue=amrValue+tc.getAmrValue();
					// 应收利息 
					interestAccured = RoundUtil.roundMoney2Dec(interestAccured+ tc.getTodayInterest());
					//利息收入
					interstIncome =interstIncome+RoundUtil.roundMoney2Dec(tc.getAmrValue()-tc.getCycleStartAmrValue()+tc.getTodayInterest());
					mkValue = mkValue + tc.getgMkValue();
					totalAmount = totalAmount + tc.getAmount();
					alrate = tc.getAltRate();
					list.add(tc);
				}
				ledgerDate = tc.getCloseDate();
			}
			// 最后一天的合计与分批
			tData = dailyAmort(ledgerDate, alrate, sec, totalAmount, amrValue,preAmrValue,list, interstIncome, cfc, interestAccured, mkValue, tData);
		}
		return tData;
	}
		
		private List<Object> dailyAmort(Date ledgerDate,double alrate,IssueInfo sec,double totalAmount,double amrValue,double preAmrValue,List<TranClo> list,
				double interstIncome,CashFlowCfg cfc,double interestAccured,double funMkValue,List<Object> tData){
			
			//将每天合计的结果存入到tData
			List<Object> item = new ArrayList<Object>();
			item.add(DateUtil.convertD2YYYY_MM_DD(ledgerDate));
			item.add("合计");
			//票面价值
			item.add(sec.getContractSize()*totalAmount);
			//期初摊余成本
			item.add(preAmrValue);
			//有效利率
			if(list.size()>1){
				item.add("-");
			}else{
				item.add(alrate);
			}
			//利息收入
			item.add(interstIncome);
			//息票率
			item.add(cfc.getCoupon());
			//应收利息
			item.add(interestAccured);
			//期末摊余成本
			item.add(amrValue);
			//市值
			item.add(funMkValue);
			//偏离度
			item.add(RoundUtil.roundMoney2Dec((funMkValue-amrValue)/amrValue/100));
			tData.add(item);
			
			//遍历每天的批次存入到tData
			for (TranClo tc : list) {
				List<Object> item1 = new ArrayList<Object>();
				item1.add("");
				item1.add(tc.getLotnum());
				item1.add(sec.getContractSize()*tc.getAmount());
				//期初摊余成本
				item1.add(tc.getCycleStartAmrValue());
				//有效利率
				item1.add(tc.getAltRate());
				//利息收入  =期末-期初+应收利息
				item1.add(RoundUtil.roundMoney2Dec(tc.getAmrValue()-tc.getCycleStartAmrValue()+tc.getTodayInterest()));
				//息票率
				item1.add(cfc.getCoupon());
				//应收利息
				item1.add(tc.getTodayInterest());
				//期末摊余成本
				item1.add(tc.getAmrValue());
				//市值
				item1.add(tc.getgMkValue());
				//偏离度
				item1.add(RoundUtil.roundMoney2Dec((tc.getgMkValue()-tc.getAmrValue())/tc.getAmrValue()/100));
				tData.add(item1);
			}
			return tData;
		}
	
	
	public List<SecPositionModel> getSecPosition(String portNum, String secid, Date endDate){
		List<SecPositionModel> secpositionList=new ArrayList<SecPositionModel>();
		PortfolioHdr portfolio=comService.loadPortfolio(portNum);
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		if(sec==null){
			return secpositionList;
		}
		SecPositionModel secPosition=getSecPosition(portfolio, sec.getId(), endDate);
		if(secPosition != null){
			secpositionList.add(secPosition);
		}
		return secpositionList;
	}
	
	
	
	private SecPositionModel getSecPosition(PortfolioHdr portfolio, String cusip, Date endDate){
		TranCloType tranCloType=new TranCloType(portfolio.getId(), cusip, endDate);
		List<TranClo> cloList=secPositionCenter.getSecClo(tranCloType, true);
		
		SecPositionModel secPosition=null;
		if(cloList.size()>0){
			secPosition=new SecPositionModel();
			
			double totalamount=0;
			double totalcost=0;
			double totalcost_func=0;
			double totalaltamount=0;
			double amortValue=0;
			String portNum="";
			for(TranClo clo:cloList){
				totalamount=totalamount+clo.getAmount();
				totalcost=totalcost+clo.getgCost();
				totalcost_func=totalcost_func+clo.getgCostFunc();
				amortValue=RoundUtil.roundMoney2Dec(amortValue+clo.getAmrValue());
				totalaltamount=clo.getAccrued();
				portNum=clo.getPortfolio();
			}
			
			IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
			secPosition.setSecID(sec.getSecurityid());
			secPosition.setMarketDesc(""); //to-do-pending
			secPosition.setSecType(SecurityCoreFactory.getAllAssetTypeHM().get(sec.getIssueType())); //to-do-pending
			secPosition.setSecDesc(sec.getSecurityid()); //to-do-pending
			secPosition.setRatio(0);
			secPosition.setExpDate("-"); //to-do-pending
			secPosition.setAmount(totalamount);
			secPosition.setCurCode(comService.loadFxCurrencyCode(sec.getPriCur()));
			secPosition.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
			secPosition.setAccrued(totalaltamount);
			secPosition.setCost(totalcost);
			secPosition.setFuncCost(totalcost_func);
			secPosition.setFuncCurCode(comService.loadFxCurrencyCode(portfolio.getCurID()));
			secPosition.setCusip(sec.getId());
			secPosition.setPortname(comService.loadPortfolio(portNum).getShortName());
			secPosition.setAmortValue(amortValue);
			/*if(sec.getIssueType().equals(AssetTypes.TERM_REPO){
				secPosition.setAltDate(DateUtil.convertD2YYYY_MM_DD(DateUtil.convertStr2Date(tsumList.get(0).getAltDate())));
				secPosition.setAltRate(String.valueOf(tsumList.get(0).getAltRate()));
			}else{
				secPosition.setAltDate("-");
				secPosition.setAltRate("-");
			}*/
			
			double fxrateRate=comService.loadFxrate(portfolio.getId(),portfolio.getCurID(),sec.getPriCur(), endDate);
			secPosition.setFxRate(fxrateRate);
			
			if(totalamount<0.0001){
				secPosition.setMkValue(0);
				secPosition.setFunMkValue(0);
				secPosition.setAccruedFunc(0);
			}else{
				double mkValue=0;
				double fmkValue=0;
				if(sec.getIssueType().equals(AssetTypes.TERM_REPO)){
					//逆回购没有价格
					mkValue=RoundUtil.roundMoney2Dec(totalcost+totalaltamount);
				}else{
					double latestPrice=comService.loadCusipPrice(portfolio.getId(), cusip, endDate);
					mkValue=RoundUtil.roundMoney2Dec(totalamount*latestPrice);
				}
				fmkValue=RoundUtil.roundMoney2Dec(mkValue*fxrateRate);
				
				secPosition.setMkValue(mkValue);
				secPosition.setFunMkValue(fmkValue);
				secPosition.setAccruedFunc(RoundUtil.roundMoney2Dec(totalaltamount*fxrateRate));
			}
		}
			
		return secPosition;
	}
	
	@Override
	public Pages<NavAssetObj> getNavObjList(Pages<NavAssetObj> pages,String portnum, Date endDate) {
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<NavAssetObj> page=navDao.getPageHelperNavList(PageHelper.startPage(pages.getCurrentPage(), pages.getPageMaxRecords()),portnum, endDate);
			pages.setResultList(page.getResult());
			pages.setTotalPages(page.getPages());
			return pages;
		}else{
			return navDao.getNavList(pages,portnum, endDate);
		}
	}
	
	public Page<NavAssetObj> getPageHelperNavList(Page<NavAssetObj> pages,String portnum, Date endDate) {
		return navDao.getPageHelperNavList(pages,portnum, endDate);
	}

	public BkCashRcpy loadCashRcpy(int rcpyNo) {
		BkCashRcpy rcpy=(BkCashRcpy)bkCashRcpyDao.loadEntity(BkCashRcpy.class, rcpyNo);
		return rcpy;
	}
	
	@Override
	public String loadFxCurrCode(String fxCurNum) {
		return comService.loadFxCurrencyCode(fxCurNum);
	}
	
	@Override
	public List<Nav> getNavDetail(String portnum, Date navDate) {
		return navDao.getNavDetails(portnum, navDate);
	}
	
	@Override
	public List<List<String>> loadTranHisSummary(String portnum, String secID) {
		
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		List<Tran> tranList=tranDao.loadtranList(portList, secID);
		
		List<List<String>> resultList=new ArrayList<List<String>>();
		
		for(Tran tran:tranList){
			List<String> sumList=new ArrayList<String>();
			sumList.add(DateUtil.convertD2YYYY_MM_DD(tran.getTradeDate()));
			sumList.add(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
			sumList.add(DataConvert.convertAmount2US(tran.getAmount()));
			
			String tranVoucherNum=voucherDao.getVoucherNumByTaNumAndType(tran.getTanum().getId(), JournalTypes.PURSALEJNL);
			sumList.add(tranVoucherNum);
			
			String cashVoucherNum=voucherDao.getVoucherNumByTaNumAndType(tran.getTanum().getId(), JournalTypes.CASH);
			sumList.add(cashVoucherNum);
			resultList.add(sumList);
		}
		return resultList;
	}
	
	@Override
	public List<List<String>> loadCashflowHisSummary(String portnum, int cashacctnum) {
		List<BkCashFlow> cashFlowList=bkCashFlowDao.loadCashFlowListByCashAcct(portnum, cashacctnum);
		List<List<String>> resultList=new ArrayList<List<String>>();
		
		for(BkCashFlow cash:cashFlowList){
			List<String> sumList=new ArrayList<String>();
			sumList.add(DateUtil.convertD2YYYY_MM_DD(cash.getBankcashdate()));
			sumList.add(DBCR.ysyfOptionsHM.get(cash.getDbcr()));
			sumList.add(DataConvert.convertCash2US(cash.getFxorigbal()));
			
			String tranVoucherNum=voucherDao.getVoucherNumByTaNumAndType(cash.getRcpyid().getTanum().getId(),JournalTypes.PURSALEJNL);
			sumList.add(tranVoucherNum);
			resultList.add(sumList);
		}
		return resultList;
	}
	
	@Override
	public List<CashPositionModel> getUseageCashPosition(String portNum, java.sql.Date asOfDate) {
		List<CashPositionModel> bkCashList=getCashPosition(portNum, asOfDate);
		
		Pages<BkCashRcpy> pages=new Pages<BkCashRcpy>();
		pages.setPageMaxRecords(2000);
		Pages<BkCashRcpy> rcpyList=getCashRcpy(pages, portNum, asOfDate, asOfDate);
		
		//把今天即将结转的现金,合并到当日的现金头寸
		for(BkCashRcpy rcpy:rcpyList.getResultList()){
			int rcpyCashID=rcpy.getBkCashAcct().getId();
			for(CashPositionModel cash:bkCashList){
				if(rcpyCashID==cash.getCashacctID()){
					if(rcpy.getDbcr().equals(DBCR.DEBIT)){
						cash.setLocalBalance(RoundUtil.roundMoney2Dec(cash.getLocalBalance()+rcpy.getRemainbal()));
						cash.setFuncBalance(RoundUtil.roundMoney2Dec(cash.getFuncBalance()+
								RoundUtil.roundMoney2Dec(rcpy.getRemainbal()*cash.getFxRate())));
					}else{
						cash.setLocalBalance(RoundUtil.roundMoney2Dec(cash.getLocalBalance()-rcpy.getRemainbal()));
						cash.setFuncBalance(RoundUtil.roundMoney2Dec(cash.getFuncBalance()-
								RoundUtil.roundMoney2Dec(rcpy.getRemainbal()*cash.getFxRate())));
					}
					break;
				}
			}
		}
		return bkCashList;
	}
}
