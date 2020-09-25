package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.SecCashGeneralType;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.journal.obj.CashBalanceObj;
import com.jixu.journal.obj.CashBalanceWrapObj;
import com.jixu.journal.obj.STCostClo;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.journal.obj.SecCashWrapObj;
import com.jixu.journal.service.GeneralJNLService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

@Service("genjnlService")
public class GeneralJNLServiceImpl implements GeneralJNLService {

	private static final Logger logger = LoggerFactory.getLogger(GeneralJNLServiceImpl.class);

	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Autowired
	private BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCore;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	@Autowired
	private TranDao tranDao;

	public double loadCusipPrice(String cusip) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<STCostWrapObj> loadSecGenerelJNLRecord(PortfolioHdr portfolio, Date asOfDate, String assetType) throws JNLException {
		List<STCostWrapObj> genwrapList = new ArrayList<STCostWrapObj>();
		STCostWrapObj stockCostWrap = null;
		TranClo[] stockCosts = null;
		
		Date closeDate=asOfDate;
		Date priorDayDate=portfolio.getLastGLClose();
		
		///////////////调用getSecClo方法获取tranclo
		
		try {
			//TODO 这里可以极大的优化，不需要把所有的tsum记录查询出来
			/*List<TranSum> secPositionList = null;
			if(assetType==0){
				secPositionList = genjnldao.loadSecPosition(portfolio.getId());
			}else{
				secPositionList = genjnldao.loadSecPosition(portfolio.getId(), assetType);
			}*/
			
			HashMap<String, STCostClo> posHM=new HashMap<String, STCostClo>();
			//把前一天和当天的clo取出来,放到hashmap,接下去计算使用
			List<TranClo> lastCloList=null;
			List<TranClo> todayCloList=null;
			String portnum=portfolio.getId();
			
			if(assetType.equals("0"))
				lastCloList=secPositionCenter.getSecClo(portnum, priorDayDate, false);
			else	
				lastCloList=secPositionCenter.getSecClo(portnum, priorDayDate, assetType, false);
				
			if(lastCloList!=null){
				for(TranClo clo:lastCloList){
					STCostClo poi=new STCostClo();
					poi.setCusip(clo.getCusip());
					poi.setLastClo(clo);
					posHM.put(clo.getCusip()+"-"+clo.getLotnum(), poi);
					
				}
			}
				
			if(assetType.equals("0"))
				todayCloList=secPositionCenter.getSecClo(portnum, closeDate, false);
			else
				todayCloList=secPositionCenter.getSecClo(portnum, closeDate, assetType, false);
			
			if(todayCloList!=null){
				for(TranClo clo:todayCloList){
					if(posHM.containsKey(clo.getCusip()+"-"+clo.getLotnum())){
						posHM.get(clo.getCusip()+"-"+clo.getLotnum()).setTodayClo(clo);
					}else{
						STCostClo poi=new STCostClo();
						poi.setCusip(clo.getCusip());
						poi.setTodayClo(clo);
						posHM.put(clo.getCusip()+"-"+clo.getLotnum(), poi);
					}
				}
			}
			
			//用于反算抵消的估增
			List<Tran> todayTranList = tranDao.loadTransInPeriod(portnum, null, priorDayDate, closeDate, false);
			HashMap<String, List<Tran>> tranHM=new HashMap<String, List<Tran>>();
			for(Tran tran:todayTranList){
				if(tranHM.containsKey(tran.getCusip().getId()+"-"+tran.getLotnum())){
					tranHM.get(tran.getCusip().getId()+"-"+tran.getLotnum()).add(tran);
				}else{
					List<Tran> cusipTranList=new ArrayList<Tran>();
					cusipTranList.add(tran);
					tranHM.put(tran.getCusip().getId()+"-"+tran.getLotnum(), cusipTranList);
				}
			}
					
			
			for (Map.Entry<String, STCostClo> entry: posHM.entrySet()) {
				/*cusip = tsum.getCusip();
				if(cusip==0){
					//排除类似于换汇一类的交易,应收应付款出估增
					continue;
				}else if(tsum.getPosclosed().equals(PostStatusType.sumClosed) 
						&& date.compareTo(tsum.getLastActivetyDate())>0){
					//这里关帐对应,CommFuncService-getSecClo-tsum.getPosclosed().equals(PostStatusType.sumClosed) && !endDate.after(lastivetyDate)
					//排除已经关帐 并且 当天日期已经lastactivetydate之后
					//比如债券,如果是结算日开始计息,卖空之后,还会继续计息
					//排除当天,是因为还要出一笔反冲凭证
					continue;
				}*/
				STCostClo stCostClo=entry.getValue();
				
				IssueInfo issue=issueInfoDao.loadIssueByCusip(stCostClo.getCusip());
				stockCostWrap = new STCostWrapObj();
				stockCostWrap.setCusip(stCostClo.getCusip());
				stockCostWrap.setGlgroupnum(issue.getCoaaGroup());
				stockCostWrap.setSec(issue);
				
				TranClo lastClo=stCostClo.getLastClo();
				TranClo todayClo=stCostClo.getTodayClo();
				//获取前天和今天的成本
				stockCosts = loadCusipCosts(portfolio, lastClo, todayClo, asOfDate);
				
				if(stockCosts != null){
					//设置前天和今天的成本
					stockCostWrap.getPriordayCost().add(stockCosts[0]);
					stockCostWrap.getAsofdayCost().add(stockCosts[1]);
					
					if(!stockCosts[1].getPortfolio().equals(""))
						stockCostWrap.setTranClo(stockCosts[1]);
					else
						stockCostWrap.setTranClo(stockCosts[0]);

					genwrapList.add(stockCostWrap);
				}
				
				if(todayClo!=null) {
					String key=todayClo.getCusip()+"-"+todayClo.getLotnum();
					if(tranHM.containsKey(key))
						stockCostWrap.setTranList(tranHM.get(key));
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}
		return genwrapList;
	}
	public List<CashBalanceWrapObj> loadCashBalanceGenJNLRecord(PortfolioHdr portfolio,Date ledgerDate, Date lastDate) throws JNLException {
		List<CashBalanceWrapObj> cashbalanceList = new ArrayList<CashBalanceWrapObj>();
		
		CashBalanceWrapObj cashBalanceWrapobj = null;
		
		try {
		
			List<BkCashAcct> bcList = bkCashAcctDao.loadBankCashAccts(portfolio.getId());
			if (bcList != null && bcList.size() > 0) {
				double temp_balance = 0.0; //余额
				for (BkCashAcct cashAcct : bcList) {
					//排除银行现金账户和投资组合的货币一致的银行，因为银行估增只有因汇率引起的估增
					if(cashAcct.getFxcurrency().equals(portfolio.getCurID())){
						continue;
					}
					
					temp_balance = 0.0;
					List<BkCashFlow> bcflowList = bkCashFlowDao.loadBankCashFlow(cashAcct.getId(), portfolio.getId(), ledgerDate);
					
					cashBalanceWrapobj = new CashBalanceWrapObj();
					//设置现金账号ID
					cashBalanceWrapobj.setBkCashAcct(cashAcct);
					
					//上一次计算净值之后的现金流
					double todayInCash=0;
					double todayOutCash=0;
					
					if (bcflowList != null && bcflowList.size() > 0) {
						CashBalanceObj priordayBalance = null;
						CashBalanceObj todayBalance = new CashBalanceObj();
						
						for (BkCashFlow cashflow : bcflowList) {
							if(cashflow.getLedger().compareTo(lastDate)>0){
								//当现金流日期>前一天日期，把现金余额放入到前一天的总金额里
								if(priordayBalance==null){
									priordayBalance=new CashBalanceObj();
									priordayBalance.setAvgrate(0);
									priordayBalance.setBalance(temp_balance);
								}
								if (cashflow.getDbcr().equals(DBCR.DEBIT)) { //收入
									todayInCash=todayInCash+cashflow.getFxorigbal();
								} else if (cashflow.getDbcr().equals(DBCR.CREDIT)) { //支出
									todayOutCash=todayOutCash+cashflow.getFxorigbal();
								}
							}
							if (cashflow.getDbcr().equals(DBCR.DEBIT)) { //收入
								temp_balance = RoundUtil.roundMoney2Dec(temp_balance + cashflow.getFxorigbal());
								
							} else if (cashflow.getDbcr().equals(DBCR.CREDIT)) { //支出
								temp_balance = RoundUtil.roundMoney2Dec(temp_balance - cashflow.getFxorigbal());
							}	
						}
						
						if(priordayBalance==null){
							priordayBalance=new CashBalanceObj();
							priordayBalance.setAvgrate(0);
							priordayBalance.setBalance(temp_balance);
						}
						
						todayBalance.setAvgrate(0);
						todayBalance.setBalance(temp_balance);
						
						cashBalanceWrapobj.setAsofdayBalance(todayBalance);
						cashBalanceWrapobj.setPriorDayBalance(priordayBalance);
						cashBalanceWrapobj.setInCash(todayInCash);
						cashBalanceWrapobj.setOutCash(todayOutCash);
						if(todayBalance.getBalance()!=0 || priordayBalance.getBalance()!=0){
							cashbalanceList.add(cashBalanceWrapobj);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}
		return cashbalanceList;
	}
	
	public List<SecCashWrapObj> loadSecCashJNLRecord(PortfolioHdr portfolio, Date date) throws JNLException {
		List<SecCashWrapObj> seccashWrapList = new ArrayList<SecCashWrapObj>();
		List<SecCashWrapObj> secashWrapCXList = new ArrayList<SecCashWrapObj>();
		try {
			//清算款结果集已经按照银行,币种排序
			//先获取尚未结转的现金,计算估增
			List<BkCashRcpy> objlist = bkCashRcpyDao.loadSecCashList(portfolio.getId(), date);
			//临时注销 20190614
			//wrapSecCashObj(objlist,seccashWrapList,SecCashGeneralType.secCashGen,date, portfolio);
			wrapFXTranCashObj(objlist,seccashWrapList,SecCashGeneralType.secCashGen,date, portfolio);
			
			//冲销的清算款值。
			//获取做账当天结转的现金,生成一笔反向冲销估增凭证
			objlist = bkCashRcpyDao.loadSecCashListCX(portfolio.getId(),date);
			//临时注销 20190614
			//wrapSecCashObj(objlist,secashWrapCXList,SecCashGeneralType.secCash_off_pre,date, portfolio);
			wrapFXTranCashObj(objlist,secashWrapCXList,SecCashGeneralType.secCash_off_pre,date, portfolio);
			
			mergetSeCash(seccashWrapList, secashWrapCXList);
			
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}
		
		return seccashWrapList;
	}
	
	private void setSecCashObj(SecCashObj cashobj, BkCashRcpy rcpyobj, String cusip, int cxFlag, Date ledgerDate) {
		if(cxFlag==SecCashGeneralType.secCashGen_todayTran){
			cashobj.setCash(rcpyobj.getFxremainbal());
		}else if(cxFlag==SecCashGeneralType.secCash_off_pre){
			cashobj.setCash(rcpyobj.getFxorigbal()-rcpyobj.getFxremainbal());
			cashobj.setFuncCash(rcpyobj.getOrigbal()-rcpyobj.getRemainbal());
		}else{
			cashobj.setCash(rcpyobj.getFxremainbal());
		}
		cashobj.setFxrate(rcpyobj.getFxrate());
		cashobj.setDbcr(rcpyobj.getDbcr());
		cashobj.setLedgerDate(rcpyobj.getLedger());
		cashobj.setCusip(cusip);
		if(ledgerDate.compareTo(rcpyobj.getLedger())==0){
			cashobj.setType(SecCashGeneralType.secCashGen_todayTran);
		}else{
			cashobj.setType(cxFlag);
		}
	}
	
	private void mergetSeCash(List<SecCashWrapObj> seccashWrapList, List<SecCashWrapObj> secashWrapCXList) {
		int index = -1;
		
		for (SecCashWrapObj wrapobj : secashWrapCXList) {
			index = getexistSecCashWrap(wrapobj.getBankCashNo(), wrapobj.getCurrcode(),wrapobj.getTranType(), seccashWrapList);
			//判断相同货币的凭证是否需要合并
			if (index == -1 || !wrapobj.isMergeFlag()) {
				seccashWrapList.add(wrapobj);
			} else {
				seccashWrapList.get(index).getCashbalanceList().addAll(wrapobj.getCashbalanceList());
			}

		}
	}
	
	private int getexistSecCashWrap(int bkcashNo, String currcode, int trantype, List<SecCashWrapObj> seccashWrapList) {
		int index = -1;
		int searchIndex = -1;
		for (searchIndex = 0; searchIndex < seccashWrapList.size(); searchIndex++) {
			
			if (seccashWrapList.get(searchIndex).getBankCashNo() == bkcashNo 
					&& seccashWrapList.get(searchIndex).getCurrcode().equals(currcode)
					&& seccashWrapList.get(searchIndex).getTranType() == trantype) {
				index = searchIndex;
				break;
			}
		}
		
		return index;
	}
	
	private void wrapFXTranCashObj(List<BkCashRcpy> objlist, List<SecCashWrapObj> seccashWrapList, int cxFlag, Date ledgerDate, PortfolioHdr portfolio){
		SecCashObj cashobj = null;
		String currcode = "";
		int bankCashNO = 0;
		String currShortCode="";
		
		
		SecCashWrapObj secashWrap = null;
		List<SecCashObj> seccashList = null;
		int gpnum = 0;
		String cusip = "";
		String issuetype="";
		String secid="";
		IssueInfo sec=null;
		
		if (objlist != null) {
			for (BkCashRcpy rcpyobj : objlist) {
//				if(rcpyobj.getCusip()!=null){
//					continue;
//				}
				
				if(rcpyobj.getCusip()!=null) {
					//临时用于给joyin传入数 20190614
					cusip=rcpyobj.getCusip().getId();
					sec=commService.loadSecByCusip(cusip);
					issuetype=sec.getIssueType();
					secid=sec.getSecurityid();
				}
				
				cashobj = new SecCashObj();
				seccashList=new ArrayList<SecCashObj>();
				
				currcode = rcpyobj.getFxcurrency();
				
				if(currcode.equals(portfolio.getCurID())){
					//排除证券货币和投资组合货币一致的应收应付款
					continue;
				}
				
				bankCashNO = rcpyobj.getBkCashAcct().getId();
				currShortCode=commService.loadFxCurrencyCode(rcpyobj.getFxcurrency());
				cusip = "";
					
				setSecCashObj(cashobj,rcpyobj,cusip, cxFlag, ledgerDate);
				seccashList.add(cashobj);
				
				if(sec==null) {
					gpnum = rcpyobj.getTanum().getGlGroup();
				}else {
					gpnum = sec.getCoaaGroup();
				}
				secashWrap = new SecCashWrapObj();
				secashWrap.setCurrcode(currcode);
				secashWrap.setBankCashNo(bankCashNO);
				secashWrap.setCashbalanceList(seccashList);
				secashWrap.setGroupnum(gpnum);
				secashWrap.setCurrShortCode(currShortCode);
				secashWrap.setTranType(rcpyobj.getTrantype());
				secashWrap.setTanum(rcpyobj.getTanum().getId());
				secashWrap.setIssueType(issuetype);
				secashWrap.setSecid(secid);
				secashWrap.setMergeFlag(false);
				seccashWrapList.add(secashWrap);
			}
		}
	}
	
	private void wrapSecCashObj(List<BkCashRcpy> objlist, List<SecCashWrapObj> seccashWrapList, int cxFlag, Date ledgerDate, PortfolioHdr portfolio) {
		SecCashObj cashobj = null;
		String currcode = "", origcurrcode = "";
		int bankCashNO = 0, origbankCashNO = 0;
		int trantype=0, origtrantype=0;
		String currShortCode="", origCurrShoartCode="";
		
		String tempCashNow = "";
		String tempCashPre = null;
		
		SecCashWrapObj secashWrap = null;
		List<SecCashObj> seccashList = null;
		int gpnum = 0;
		String cusip = ""; 
		String origcusip = "";
		
		if (objlist != null) {
			for (BkCashRcpy rcpyobj : objlist) {
				if(rcpyobj.getCusip()==null){
					//排除换汇等现金交易
					continue;
				}
				
				cashobj = new SecCashObj();
				
				currcode = rcpyobj.getFxcurrency();
				
				if(currcode.equals(portfolio.getCurID())){
					//排除证券货币和投资组合货币一致的应收应付款
					continue;
				}
				
				bankCashNO = rcpyobj.getBkCashAcct().getId();
				tempCashNow = currcode + "_" + bankCashNO;
				currShortCode=commService.loadFxCurrencyCode(rcpyobj.getFxcurrency());
				cusip = rcpyobj.getCusip().getId();
				trantype=rcpyobj.getTrantype();
				setSecCashObj(cashobj,rcpyobj,cusip,cxFlag,ledgerDate);
				
				if (tempCashPre == null) {
					tempCashPre = tempCashNow;
					seccashList = new ArrayList<SecCashObj>();
					seccashList.add(cashobj);
					
					origcurrcode = currcode;
					origbankCashNO = bankCashNO;
					origcusip = cusip;
					origCurrShoartCode=currShortCode;
					origtrantype=trantype;
				} else if (!tempCashPre.equals(tempCashNow)) {//另外的银行或者币种
					gpnum = loadCusipGLGroupNum(origcusip);
					secashWrap = new SecCashWrapObj();
					secashWrap.setCurrcode(origcurrcode);
					secashWrap.setBankCashNo(origbankCashNO);
					secashWrap.setCashbalanceList(seccashList);
					secashWrap.setGroupnum(gpnum);
					secashWrap.setCurrShortCode(origCurrShoartCode);
					secashWrap.setTranType(origtrantype);
					seccashWrapList.add(secashWrap);
					
					origcurrcode = currcode;
					origbankCashNO = bankCashNO;
					origcusip = cusip;
					origCurrShoartCode=currShortCode;
					origtrantype=trantype;
					
					seccashList = new ArrayList<SecCashObj>();
					seccashList.add(cashobj);
					tempCashPre = tempCashNow;
				} else {//同一银行和币种
					seccashList.add(cashobj);
				}
			}
			
			if (seccashList != null && seccashList.size() > 0) {
				secashWrap = new SecCashWrapObj();
				secashWrap.setCurrcode(currcode);
				secashWrap.setCurrShortCode(currShortCode);
				secashWrap.setBankCashNo(bankCashNO);
				secashWrap.setCashbalanceList(seccashList);
				secashWrap.setTranType(trantype);
				secashWrap.setGroupnum(loadCusipGLGroupNum(cusip));
				secashWrap.setMergeFlag(true);
				seccashWrapList.add(secashWrap);
			}
			
		} 
	}
	
	//获取股票的成本
	private TranClo[] loadCusipCosts(PortfolioHdr portfolio, TranClo lastClo, TranClo todayClo, Date asOfDate) {
		//if(todayDate.after(portfolio.getLastTranClose()) || lastClo==null || todayClo==null || lastClo.getLstTrannum()<todayClo.getLstTrannum()){
		//1.运行以后某一天的估增日记账  2.当天是该股票的第一笔交易 3.当天已经结转完成，没有股数了 4.今天该股票存在交易
		//以上是之前的逻辑，没有关帐逻辑之前
		//目前的逻辑是如果关帐了,就else。 没有关帐,走if逻辑
		if(lastClo==null && todayClo==null)
			return null;
		else if(lastClo==null){
			lastClo=new TranClo();
			BeanUtils.copyProperties(todayClo, lastClo);
			lastClo.setAccrued(0);
			lastClo.setAmount(0);
			lastClo.setgCost(0);
			lastClo.setgCostFunc(0);
			lastClo.setgMkValue(0);
			lastClo.setPrice(0);
		}
		else if(todayClo==null){
			todayClo=new TranClo();
			BeanUtils.copyProperties(lastClo, todayClo);
			todayClo.setAccrued(0);
			todayClo.setAmount(0);
			todayClo.setgCost(0);
			todayClo.setgCostFunc(0);
			todayClo.setgMkValue(0);
			todayClo.setPrice(0);
		}
			
		return new TranClo[] { lastClo, todayClo };
	}
	
	//修改凭证框架,注释目前的多lot功能, pan wei, 2017/01/22
	/*@SuppressWarnings("unchecked")
	private List<STCostObj>[] loadMultiLotCusipCosts(String portfolio, String cusip, String date) {
		List<STCostObj>[] costsList = new ArrayList[2];
		List<STCostObj> priordayCostList = new ArrayList<STCostObj>();
		List<STCostObj> asofdayCostList = new ArrayList<STCostObj>();

		String priorDate = DateUtil.getPriorDay(date);

		double temp_g_cost = 0.0;
		double temp_func_cost = 0.0;
		double temp_amount = 0.0;

		STCostObj cost = null;
		List<Tran> transList = genjnldao.loadCusipTrans(
				portfolio, cusip, date);
		if (transList != null) {
			for (Tran trandata : transList) {

				if (trandata.getTradeDate().compareTo(priorDate) > 0) {
					cost = new STCostObj();
					cost.setAmount(RoundUtil.roundMoney2Dec(temp_amount));
					cost.setGcost(RoundUtil.roundMoney2Dec(temp_g_cost));
					cost.setGcostFunc(RoundUtil.roundMoney2Dec(temp_func_cost));
				} else if (trandata.getTradeDate().compareTo(date) > 0) {
					cost = new STCostObj();
					cost.setAmount(RoundUtil.roundMoney2Dec(temp_amount));
					cost.setGcost(RoundUtil.roundMoney2Dec(temp_g_cost));
					cost.setGcostFunc(RoundUtil.roundMoney2Dec(temp_func_cost));
					cost.setFxrate(trandata.getPrinFxrate());
					break;
				}

				if (trandata.getTrantype() == TransTypes.PURCHASE) {
					temp_g_cost += trandata.getgCost();
					temp_func_cost += trandata.getgCostFunc();
					temp_amount += trandata.getAmount();
				} else if (trandata.getTrantype() == TransTypes.SALE) {
					temp_g_cost -= trandata.getgCost();
					temp_func_cost -= trandata.getgCostFunc();
					temp_amount -= trandata.getAmount();
				}
			}
		}

		if (cost == null) {
			cost = new STCostObj();
			cost.setAmount(RoundUtil.roundMoney2Dec(temp_amount));
			cost.setGcost(RoundUtil.roundMoney2Dec(temp_g_cost));
			cost.setGcostFunc(RoundUtil.roundMoney2Dec(temp_func_cost));
		}

		if (cost == null) {
			cost = new STCostObj();
			cost.setAmount(RoundUtil.roundMoney2Dec(temp_amount));
			cost.setGcost(RoundUtil.roundMoney2Dec(temp_g_cost));
			cost.setGcostFunc(RoundUtil.roundMoney2Dec(temp_func_cost));
		}

		costsList[0] = priordayCostList;
		costsList[1] = asofdayCostList;
		return costsList;
	}*/
	
	private int loadCusipGLGroupNum(String cusip) {
		int gpnum = 0;
		try {
			IssueInfo issueinfo = (IssueInfo)issueInfoDao.loadEntity(IssueInfo.class, cusip);
			gpnum = issueinfo.getCoaaGroup();
		} catch (Exception e) {
			logger.error("Fail to load cusip " + cusip + " glgroup num");
		}
		return gpnum;
	}
}
