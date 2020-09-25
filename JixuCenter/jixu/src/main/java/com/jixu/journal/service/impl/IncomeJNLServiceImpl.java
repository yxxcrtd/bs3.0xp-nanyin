package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.types.CashFlowCfgType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.InterestCycleDate;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.journal.obj.BankBalanceObj;
import com.jixu.journal.obj.BankBalanceWrapObj;
import com.jixu.journal.obj.IncomeWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.journal.service.IncomeJNLService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkInteAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

@Service("incomejnlService")
public class IncomeJNLServiceImpl implements IncomeJNLService {
	
	private static final Logger logger = LoggerFactory.getLogger(IncomeJNLServiceImpl.class);
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Autowired
	private IssueInfoDao issueInfoDao;

	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeCal;
	
	@Autowired
	private SecPositionCenter secPositionCenter;
	
	public List<SecIncomeWrapObj> loadSecIncomeData(String portnum, Date date) throws JNLException {
		Date ledgerDate=date;
		List<SecIncomeWrapObj> secIncomeWrapObjList=new ArrayList<SecIncomeWrapObj>();

		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		List<TranClo> cloList=secPositionCenter.getSecClo(portnum, ledgerDate, false);
		List<TranClo> oldcloList=secPositionCenter.getSecClo(portnum, portfolio.getLastGLClose(), false);
		List<Tran> incomeUpdateList=tranDao.loadIncomeUpdateTrans(portnum, ledgerDate);
		
		HashMap<String, TranClo> oldCloMap=new HashMap<String, TranClo>();
		for(TranClo oldClo:oldcloList){
			oldCloMap.put(oldClo.getCusip()+"_"+oldClo.getLotnum(), oldClo);
		}
		
		//放入利息调整交易
		HashMap<String, List<Tran>> incomeupdateMap=new HashMap<String, List<Tran>>();
		for(Tran tran:incomeUpdateList){
			String key=tran.getCusip().getId()+"_"+tran.getLotnum();
			if(incomeupdateMap.containsKey(key)){
				incomeupdateMap.get(key).add(tran);
			}else{
				List<Tran> tranList=new ArrayList<Tran>();
				tranList.add(tran);
				incomeupdateMap.put(key, tranList);
			}
		}
		
		
		/*if(!portfolio.getLastTranClose().after(ledgerDate)){
			cloList=incomejnldao.loadTranClo(portnum, ledgerDate);
		}else{
			//获取了做账日当天股数可能不为0的证券
			List<TranSum> tranSumList=incomejnldao.loadSecIncome(date, portnum);
			for(TranSum tsum1:tranSumList){
				TranClo clo=secPositionCenter.getSecPosition(portnum, tsum1.getCusip(), tsum1.getLotnum(), ledgerDate);
				Date activeDate=DateUtil.convertStr2Date(tsum1.getLastActivetyDate());
				if(clo.getAmount()<=0 && clo.getAccrued()==0 && !ledgerDate.before(activeDate)){
					//目前仅支持股数>0的收入计提
					continue;
				}
			}
		}*/
		
		for(TranClo clo:cloList){
			if(clo!=null){
				SecIncomeWrapObj secIncomeObj=new SecIncomeWrapObj();
				IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
				
				String key=clo.getCusip()+"_"+clo.getLotnum();
				if(oldCloMap.containsKey(key)){
					secIncomeObj.setLastClo(oldCloMap.get(key));
				}else{
					secIncomeObj.setLastClo(null);
				}
				
				if(incomeupdateMap.containsKey(key)){
					secIncomeObj.setIncomeUpdateTrans(incomeupdateMap.get(key));
				}
				
				secIncomeObj.setSecurity(sec);
				secIncomeObj.setClo(clo);
				
				secIncomeWrapObjList.add(secIncomeObj);
			}
		}
		
		return secIncomeWrapObjList;
	}

	public IncomeWrapObj loadIncomeData(String portnum, Date date) throws JNLException {
		//返回每天的现金流总额
		IncomeWrapObj incomeWrapObj = new IncomeWrapObj();
		
		try {
			List<BkCashAcct> cashAcctList = bkCashAcctDao.loadInteBankCashAcctInfo(portnum);//加载现金账户list.(1)
			Date lastcalInteDate = DateUtil.getInitDate();			//上一轮计息的最后一天
			Date inteStartDate = DateUtil.getNextDay(lastcalInteDate); 	//上次结息后一天
			Date inteEndDate=DateUtil.getInitDate();		//这一轮的最后一个计息日
			for (BkCashAcct cashAcct : cashAcctList) {
				BkInteAcct bkInteAcct=bkInteAcctDao.loadBkInteAcct(cashAcct.getIntCshflwMasterRec());
				InterestCycleDate inteDates = incomeCal.getPeriodCycleDate(date, bkInteAcct.getCashFlowCfg(), CashFlowCfgType.Coupon);
				if(inteDates!=null){
					lastcalInteDate=inteDates.getLastInteEndDate();
					inteStartDate=inteDates.getStartInteDate();
					inteEndDate=inteDates.getEndIInteDate();
				}else{
					return null;
				}
				
				BankBalanceWrapObj bkbalanceWrap=loadBkBalanceWrap(cashAcct, portnum, lastcalInteDate, inteStartDate, inteEndDate, date);
				if(bkbalanceWrap!=null){
					incomeWrapObj.getBankBalanceWrapList().add(bkbalanceWrap);
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}
		
		return incomeWrapObj;
	}
	
	public BankBalanceWrapObj loadBkBalanceWrap(BkCashAcct cashAcct, String portnum, Date lastcalInteDate, Date inteStartDate, Date inteEndDate, Date ledger){
		double lastinteBalance = 0.0; 	//上次结息后一天的银行余额
		BankBalanceWrapObj bkbalanceWrap = null;
		Date ledgerDate = null;
		double tempfxrate = 0.0;
		String currcode = "";
		int daycomp = -1;
		
		boolean setlstBalace=false;
		
		//获取做账日之前的所有现金流
		List<BkCashFlow> bcflowList = bkCashFlowDao.loadCashFlowByCashAcct(ledger, cashAcct.getId(), portnum);
		double temp_cash = 0.0;
		List<BankBalanceObj> bkbalanceList = new ArrayList<BankBalanceObj>();
		
		if (bcflowList != null && bcflowList.size() > 0) {
			currcode = cashAcct.getFxcurrency();
			ledgerDate = bcflowList.get(0).getLedger();
			if(ledgerDate.compareTo(inteStartDate)==1){
				//如果第一笔交易的日期就在上一计息日后
				setlstBalace=true;
			}
		} else {
			return null;
		}

		for (BkCashFlow cashflowobj : bcflowList) {
			daycomp = cashflowobj.getLedger().compareTo(lastcalInteDate);
			if (daycomp > 0) {
				//如果现金流的日期在上一个计息日之后
				if (!cashflowobj.getLedger().equals(ledgerDate)) {
					//当这条现金流的日期和上一条现金流的日期不同
					if (ledgerDate.compareTo(inteStartDate) >= 0) {
						//记录前一天的记录
						setBalacedata(new BankBalanceObj(), temp_cash, ledgerDate, tempfxrate, bkbalanceList);
						if (setlstBalace == false && cashflowobj.getLedger().compareTo(inteStartDate) > 0) {
							lastinteBalance = temp_cash;
							setlstBalace = true;
						}
					}
					ledgerDate = cashflowobj.getLedger();
				}
			}
			tempfxrate = cashflowobj.getFxrate();
			if (cashflowobj.getDbcr().equals(DBCR.CREDIT)) {
				temp_cash = temp_cash - RoundUtil.roundMoney2Dec(cashflowobj.getFxorigbal());
			} else {
				temp_cash = temp_cash + RoundUtil.roundMoney2Dec(cashflowobj.getFxorigbal());
			}
		}
		
		if(setlstBalace == false && ledger.compareTo(inteStartDate)>=0 && ledgerDate.compareTo(lastcalInteDate)<=0){
			lastinteBalance = temp_cash;
		}
		
		if (temp_cash > DBCR.ZERO) {
			//把最后一天的现金流加载进去
			setBalacedata(new BankBalanceObj(), temp_cash, ledgerDate, tempfxrate, bkbalanceList);
			
			bkbalanceWrap = new BankBalanceWrapObj(cashAcct);
			bkbalanceWrap.setBankBalanceList(bkbalanceList);
			bkbalanceWrap.setCurrcode(currcode);
			bkbalanceWrap.setInteLastCycleEndDate(lastcalInteDate);
			bkbalanceWrap.setInteStartDate(inteStartDate);
			bkbalanceWrap.setLstcalInteBalance(lastinteBalance);
			bkbalanceWrap.setInteEndDate(inteEndDate);
			
			return bkbalanceWrap;
		}
		return null;
	}
	
	private void setBalacedata(BankBalanceObj bkbalance, double balance, Date ledgdate, double fxrate, List<BankBalanceObj> bkbalanceList) {
		bkbalance.setBkbalance(balance);
		bkbalance.setLedgerdate(ledgdate);
		bkbalance.setFxrate(fxrate);
		bkbalanceList.add(bkbalance);
	}
}
