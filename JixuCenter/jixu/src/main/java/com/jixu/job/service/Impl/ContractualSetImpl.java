package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.job.service.ContractualSetService;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;

@Service("contractualSetService")
public class ContractualSetImpl implements ContractualSetService {
	private static final Logger logger = LoggerFactory.getLogger(ContractualSetImpl.class);
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	private int jobtype=JobsType.ContractCash;

	// 把应收应付的钱全部结算到已收已付
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer settleToCashByRCPYID(int userID, int rcpyID, Date asOfDate) {
		BkCashRcpy bkCashRcpy = bkCashRcpyDao.loadEntity(BkCashRcpy.class, rcpyID);
		ResultContainer container=new ResultContainer();
		if(bkCashRcpy!=null){
			container.setEntityObj(bkCashRcpy.getPortfolio());
			try{
				saveToCash(bkCashRcpy, bkCashRcpy.getFxremainbal(), asOfDate);
			}catch(Exception e){
				logger.error("Error", e);
				container.setCorrectFlag(false);
				container.setErrorMsg(e.getMessage());
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.id_not_exist);
			container.setErrorMsg(rcpyID+":" + ReturnResult.getResultHM().get(ReturnResult.id_not_exist));
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer settleToCash(int userID, String[] portnums, Date endDate) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=settleToCash(userID, portnum, endDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}

	// 结转部分应收应付到已收已付
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer settleToCash(int userID, int rcpyID, double cashamt, Date ledgerDate) {
		BkCashRcpy bkCashRcpy = bkCashRcpyDao.loadEntity(BkCashRcpy.class, rcpyID);
		ResultContainer container=new ResultContainer();
		if(bkCashRcpy!=null && bkCashRcpy.getId()>0){
			container.setEntityObj(bkCashRcpy.getPortfolio());
			try{
				saveToCash(bkCashRcpy, cashamt, ledgerDate);
			}catch(Exception e){
				logger.error("Error", e);
				container.setCorrectFlag(false);
				container.setErrorMsg(e.getMessage());
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.id_not_exist);
			container.setErrorMsg(rcpyID+":" + ReturnResult.getResultHM().get(ReturnResult.id_not_exist));
		}
		return container;
	}

	// 结转enddate以前的应收应付到已收已付
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer settleToCash(int userID, String portnum, Date endDate) {
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		container.setHeader(JobsType.jobsTypeHM.get(jobtype));
		container.setEntityObj(portfolio);
		List<BkCashRcpy> rcpyList = bkCashRcpyDao.loadRCPYByEndDate(portnum, endDate);
		Date ledgerDate=endDate;
		try{
			for (BkCashRcpy rcpy:rcpyList) {
				saveToCash(rcpy, rcpy.getFxremainbal(), ledgerDate);
			}
		}catch(Exception e){
			logger.error("Error", e);
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage());
		}
		
		commfuncService.updateJobsHis(userID, portfolio, endDate, jobtype, container);
		return container;
	}

	/**
	 * @param bkCashRcpy
	 * @param setCash	需要结转多少钱
	 * @throws Exception
	 */
	private void saveToCash(BkCashRcpy bkCashRcpy, double setCash, Date ledgerDate) throws Exception{
		double remainLocal = bkCashRcpy.getFxremainbal();
		double remainFunc = bkCashRcpy.getRemainbal();
		double remainUnits=bkCashRcpy.getUremainbal();
		
		double setCashFunc=RoundUtil.roundMoney2Dec(remainFunc*setCash/remainLocal);
		double setUnits=RoundUtil.roundMoney2Dec(remainUnits*setCash/remainLocal);
		
		remainLocal=RoundUtil.roundMoney2Dec(remainLocal-setCash);
		remainFunc=RoundUtil.roundMoney2Dec(remainFunc-setCashFunc);
		remainUnits=RoundUtil.roundMoney2Dec(remainUnits-setUnits);
		
		String dbcr = bkCashRcpy.getDbcr();
		if(dbcr.equals(DBCR.CREDIT)){
			dbcr=DBCR.DEBIT;
		}else{
			dbcr=DBCR.CREDIT;
		}
		
		String cashDesc=SecurityCoreFactory.getAllTranTypeHM().get(bkCashRcpy.getTrantype());
		if(bkCashRcpy.getTrantype()==TransTypes.PURCHASE || bkCashRcpy.getTrantype()==TransTypes.SALE){
			cashDesc=cashDesc+": " + bkCashRcpy.getCusip().getSecurityid();
		}
		
		
		// 把RCPY表里面的钱结转到CASH表
		BkCashFlow bkCashFlow = new BkCashFlow();
		bkCashFlow.setRcpyid(bkCashRcpy);
		bkCashFlow.setPortfolio(bkCashRcpy.getPortfolio());
		bkCashFlow.setZero(1);
		bkCashFlow.setDbcr(dbcr);
		bkCashFlow.setBkCashAcct(bkCashRcpy.getBkCashAcct());
		bkCashFlow.setBankcashdate(ledgerDate);
		bkCashFlow.setOrigbal(setCashFunc);
		bkCashFlow.setRemainbal(remainFunc);
		bkCashFlow.setFxcurrency(bkCashRcpy.getFxcurrency());
		bkCashFlow.setFxorigbal(setCash);
		bkCashFlow.setFxremainbal(remainLocal);
		bkCashFlow.setUorigbal(setUnits);
		bkCashFlow.setUremainbal(remainUnits);
		// 目前没有给该字段设值
		bkCashFlow.setCusip(bkCashRcpy.getCusip());
		bkCashFlow.setFxrate(bkCashRcpy.getFxrate());
		// 目前没有给该字段设值
		bkCashFlow.setCashdesc(cashDesc);
		bkCashFlow.setLastmatch(ledgerDate);
		bkCashFlow.setLedger(ledgerDate);
		// 目前没有给该字段设值
		bkCashFlow.setOrigpaydate(DateUtil.convertStr2Date("20000101"));
		// 目前没有给该字段设值
		bkCashFlow.setBankcashdateRev("20000101");

		bkCashFlowDao.saveEntity(bkCashFlow);

		bkCashRcpy.setRemainbal(remainFunc);
		bkCashRcpy.setFxremainbal(remainLocal);
		bkCashRcpy.setUremainbal(remainUnits);
		bkCashRcpy.setLastmatch(bkCashFlow.getBankcashdate());
		bkCashRcpyDao.updateEntity(bkCashRcpy);

		// 更新银行账户的余额
		int cashacctid=bkCashRcpy.getBkCashAcct().getId();
		BkCashAcct bkCashAcct = bkCashAcctDao.loadEntity(BkCashAcct.class, cashacctid);
		double preBal = bkCashAcct.getLastBalance();
		if (dbcr.equals(DBCR.DEBIT))
			preBal = RoundUtil.roundMoney2Dec(preBal + bkCashFlow.getFxorigbal());
		else if (dbcr.equals(DBCR.CREDIT))
			preBal = RoundUtil.roundMoney2Dec(preBal - bkCashFlow.getFxorigbal());
		bkCashAcct.setLastBalance(preBal);

		Date lastBalDate = bkCashAcct.getLastBalDate();
		Date cashSettleDate = bkCashRcpy.getPaydate();
		if (lastBalDate==null || cashSettleDate.compareTo(lastBalDate) > 0)
			bkCashAcct.setLastBalDate(cashSettleDate);
		bkCashAcctDao.updateEntity(bkCashAcct);
	}
	
	public ResultContainer deleteCashFlow(BkCashFlow cashflow) {
		BkCashRcpy cashrcpy=cashflow.getRcpyid();
		return deleteCashFlow(cashflow, cashrcpy);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteCashFlow(BkCashFlow cashflow, BkCashRcpy cashrcpy) {
		ResultContainer container=new ResultContainer();
		container.setEntityID(cashflow.getPortfolio().getId());
		if(cashrcpy==null){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.unpost_rcpy_cash_unmatch);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.unpost_rcpy_cash_unmatch) + ": 实收实付编号: " + cashflow.getId());
			return container;
		}
		
//		PortfolioHdr portfolio=commfuncService.loadPortfolio(cashflow.getPortfolio().getId());
//		Date cashDate=cashflow.getBankcashdate();
//		if(!portfolio.getLastTranClose().before(cashDate)){
//			container.setCorrectFlag(false);
//			container.setErrorCode(ReturnResult.ledger_before_close_failed);
//			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.ledger_before_close_failed));
//			return container;
//		}
		
		String dbcr = cashrcpy.getDbcr();
		
		// 更新银行账户的余额
		BkCashAcct bkCashAcct = cashrcpy.getBkCashAcct();
		double preBal = bkCashAcct.getLastBalance();
		if (dbcr.equals(DBCR.DEBIT))
			preBal = RoundUtil.roundMoney2Dec(preBal + cashflow.getFxorigbal());
		else if (dbcr.equals(DBCR.CREDIT))
			preBal = RoundUtil.roundMoney2Dec(preBal - cashflow.getFxorigbal());
		bkCashAcct.setLastBalance(preBal);

		bkCashAcctDao.updateEntity(bkCashAcct);
		
		double cashFunc=cashflow.getOrigbal();
		double cashLocal=cashflow.getFxorigbal();
		double amount=cashflow.getUorigbal();
		
		cashrcpy.setRemainbal(RoundUtil.roundMoney2Dec(cashrcpy.getRemainbal()+cashFunc));
		cashrcpy.setFxremainbal(RoundUtil.roundMoney2Dec(cashrcpy.getFxremainbal()+cashLocal));
		cashrcpy.setUremainbal(RoundUtil.roundMoney2Dec(cashrcpy.getUremainbal()+amount));
		
		if(cashrcpy.getRemainbal()>cashrcpy.getOrigbal() || cashrcpy.getFxremainbal()>cashrcpy.getFxorigbal() || cashrcpy.getUremainbal()>cashrcpy.getUorigbal()){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.unpost_rcpy_cash_unmatch);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.unpost_rcpy_cash_unmatch) + ": 应收应付编号: " + cashrcpy.getId());
			return container;
		}
		
		bkCashFlowDao.deleteEntity(cashflow);
		bkCashRcpyDao.updateEntity(cashrcpy);
		return container;
	}
	
	public ResultContainer deleteCashFlow(int cashID) {
		BkCashFlow cashflow=(BkCashFlow)bkCashFlowDao.loadEntity(BkCashFlow.class, cashID);
		return deleteCashFlow(cashflow);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteCashFlow(String portnum, Date ledgerDate) {
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		
		container.setHeader(JobsType.jobsTypeHM.get(jobtype));
		container.setEntityID(portnum);
		container.setEntityObj(portfolio);
		
		List<BkCashFlow> cashflowList=bkCashFlowDao.loadCashFlowList(portnum, ledgerDate);
		for(BkCashFlow cashflow:cashflowList){
			ResultContainer innercontainer=deleteCashFlow(cashflow);
			if(!innercontainer.isCorrectFlag()){
				container.setCorrectFlag(innercontainer.isCorrectFlag());
				container.setErrorCode(innercontainer.getErrorCode());
				container.setErrorMsg(innercontainer.getErrorMsg());
				return container;
			}
		}
		
		if(container.isCorrectFlag())
			jobsHisDao.deleteJobsHist(portnum, ledgerDate, jobtype);
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteCash(int userID, String[] portnums, Date endDate) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for(String portnum:portnums){
			ResultContainer container=deleteCashFlow(portnum, endDate);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container); 
			
			//to-do自动作业撤销记录删除
			List<String> tanumlist=tranBasicDao.loadAutoTranBasicList(portnum,endDate);
			for (String tanum : tanumlist) {
				ResultContainer innercontainer=tradeService.deleteTran(tanum,false);
			}
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
}
