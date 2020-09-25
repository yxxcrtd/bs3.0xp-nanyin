package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.types.CashFlowCfgType;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.InterestCycleDate;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.dao.intf.BkInteSetHisDao;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.job.obj.BankInterestType;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.journal.obj.BankBalanceWrapObj;
import com.jixu.journal.service.IncomeJNLService;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.post.service.PostIssueService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInteAcct;
import com.orm.obj.BkInteSetHis;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		InterestMaturity
 * @author  		Pan Wei 
 * @Date 			2016年11月22日 上午10:41:53 	
 * @Description		[]  
 */

@Service("inteSetService")
public class InterestMaturity implements AutoMaturityService{
	 
	@Resource(name="incomejnlService")
	private IncomeJNLService incomejnlservice;
	
	@Resource(name="incomejnlJob")
	private IncomeJob incomejnlJob;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkInteSetHisDao bkInteSetHisDao;
	
	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="postIssues")
	private PostIssueService postIssue;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Resource(name = "incomeExpenseCal")
	private IncomeExpenseCal incomeCal;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer autoMaturity(int userID, String[] portnums, Date ledgerDate, ResultContainer mainContainer) {
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=autoMaturity(userID, portnum, ledgerDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
			}
			container.setEntityID(portnum);
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	 
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer autoMaturity(int userID, String portnum, Date ledgerDate) {
		 ResultContainer container=new ResultContainer();
		 PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		 List<BkCashAcct> cashAcctList = bkCashAcctDao.loadInteBankCashAcctInfo(portfolio.getId());//加载计息账户
		 container.setEntityObj(portfolio);
		 container.setHeader(JobsType.jobsTypeHM.get(JobsType.interestMaturity));
		 
		 for(BkCashAcct bkcashacct:cashAcctList){
			BkInteAcct bkInteAcct=bkInteAcctDao.loadBkInteAcct(bkcashacct.getIntCshflwMasterRec());
			BkInteSetHis latestSetInte=bkInteSetHisDao.loadLatestInterestSet(portfolio.getId(), bkcashacct.getId());
			CashFlowCfg cashcfg=bkInteAcct.getCashFlowCfg();
			InterestCycleDate inteDates=incomeCal.getPeriodCycleDate(ledgerDate, cashcfg, CashFlowCfgType.Coupon);
			Date lastInteDate=inteDates.getLastInteEndDate();
			Date startInteDate=inteDates.getStartInteDate();
			Date endInteDate=inteDates.getEndIInteDate();
			
			//做账日就是本轮计息的最后一天  && 银行的上一轮结息日<今天
			boolean interestFlag=false;
			Date calInteDate=null;
			if(latestSetInte==null){
				if(ledgerDate.compareTo(endInteDate)==0){
					interestFlag=true;
					calInteDate=ledgerDate;
				}
			}else{
				if(ledgerDate.compareTo(endInteDate)==0 && latestSetInte.getInteDate().before(endInteDate)){
					interestFlag=true;
					calInteDate=ledgerDate;
				}else if(ledgerDate.after(lastInteDate) && latestSetInte.getInteDate().before(lastInteDate)){
					interestFlag=true;
					calInteDate=lastInteDate;
				}
			}
				
			if(interestFlag){
				BankBalanceWrapObj bkObj=incomejnlservice.loadBkBalanceWrap(bkcashacct, portfolio.getId(), lastInteDate, 
						startInteDate, endInteDate, calInteDate);
				if(bkObj==null){
					continue;
				}
				
				CashFlowCfg intcfg=bkInteAcct.getCashFlowCfg();
				int delaynum=intcfg.getCpnDelayDateNum();
				String holidaySD=intcfg.getHolidaySD();
				if(holidaySD==null || holidaySD.equals("")){
					holidaySD=portfolio.getHolidaySD();
				}
				
				List<Date> lsList=holidayLSDao.loadHolidayList(holidaySD);
				Date setDate=null;
				if(lsList.size()==0){
					setDate=DateUtil.getNextNDays(calInteDate, delaynum);
				}else{
					setDate=DateUtil.getNextNBusinessDays(calInteDate, delaynum, lsList);
				}
				
				BankInterestType bkint=incomejnlJob.loadBankInterest(calInteDate, bkObj, portfolio);
				String portcode=String.valueOf(portfolio.getId());
				String cashacct=String.valueOf(bkcashacct.getId());
				//在后台计算的逻辑,fxorig是原币
				String funccash=String.valueOf(bkint.getTotalInterestOrig());
				String localcash=String.valueOf(bkint.getTotalInterestFxOrig());
				String dbcr=String.valueOf(IncomeExpenseType.income);
				String glacct=bkcashacct.getIntrcvpaygl();
				String desc=ReturnResult.getDicHM().get(ReturnResult.setInterest_chinese)+"-"+bkcashacct.getBankdesc();
				
				
				HashMap<String, Object> msgHM=msgPackage.cashOff("ADD","",portcode, calInteDate, setDate, cashacct, funccash, localcash, dbcr, glacct, desc, null); 
						
				MsgContainer msgcontainer=new MsgContainer();
				msgcontainer.setLedgerDate(ledgerDate);
				msgcontainer.setOperatoionStep(WorkFlowSteps.autojob);
				msgcontainer.setFieldsHM(msgHM);
				msgcontainer.setUserID(userID);
				msgcontainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				msgcontainer=tradeService.operateTran(msgcontainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, msgcontainer);
				
				if(!msgcontainer.isCorrectFlag()){
					container.setCorrectFlag(msgcontainer.isCorrectFlag());
					container.setErrorMsg(msgcontainer.getErrorMsg());
					return container;
				}
				
				ResultContainer resultcontainer=new ResultContainer();
				resultcontainer.setEntityID(msgcontainer.getEntityID());
				
				if(msgcontainer.isCorrectFlag()){
					TranBasic tran=(TranBasic)msgcontainer.getEntityObj();
					if(resultcontainer.isCorrectFlag()){
						BkInteSetHis intetestSet=new BkInteSetHis();
						intetestSet.setBankacct(bkcashacct);
						intetestSet.setInteDate(calInteDate);
						intetestSet.setPortfolio(portfolio);
						intetestSet.setTanum(tran);
						bkInteSetHisDao.saveEntity(intetestSet);
					}
				}else{
					container.setCorrectFlag(msgcontainer.isCorrectFlag());
					container.setErrorMsg(msgcontainer.getErrorMsg());
					return container;
				}
			}
		}
		container.setEntityObj(portfolio);
		return container;
	}
}
