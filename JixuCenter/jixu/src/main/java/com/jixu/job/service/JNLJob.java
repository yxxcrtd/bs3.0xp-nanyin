package com.jixu.job.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.LANGUAGE;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashCloDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.PortExpenseCfgDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.Impl.JobCommonImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.JobsHis;
import com.orm.obj.PortfolioHdr;

public abstract class JNLJob {
	
	@Resource(name="printJNLFactory")
	private PrintJNLFactory printJNLFactory;

	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Resource(name="secCoreFactory")
	protected SecurityCoreFactory secCoreFactory;
	
	@Resource(name="jobCommonImpl")
	protected JobCommonImpl jobCom;
	
	@Autowired
	protected BkCashCloDao bkCashCloDao;
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	public PrintJNL getPrintjnl() {
		return printJNLFactory.getPringInstance(LANGUAGE.LANG);
	}
	
	protected static final Logger logger = LoggerFactory.getLogger(JNLJob.class);
	
	@Autowired
	protected VoucherDao voucherDao;
	
	@Autowired
	protected BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected VoucherDetailsDao voucherDetailsDao;
	
	@Autowired
	protected PortExpenseCfgDao portExpenseCfgDao;
	
	protected int jobType;
	protected int journalType;

	protected PrintJNL printjnl;
	
	protected abstract List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException;
	
	protected PortfolioHdr getPortfolio(String portnum){
		return commfuncService.loadPortfolio(portnum);
	}
	
	public void removeJob(String portNum, Date ledgerDate, ResultContainer container){
		container.setHeader(JobsType.jobsTypeHM.get(jobType));
		container.setEntityObj(getPortfolio(portNum));
		try{
			voucherDao.deleteVouchers(portNum, ledgerDate, journalType);
			jobsHisDao.deleteJobsHist(portNum, ledgerDate, jobType);
		}catch(Exception e){
			logger.error("Error", e);
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage());
		}
	}
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer execute(JobParam jobParam){
		ResultContainer container=new ResultContainer();

		//多线程并发的时候,删除的时候有死锁状态
		//removeJob(jobParam.getPortfolio(), DateUtil.convertStr2Date(jobParam.getDate()), container);
		if(container.isCorrectFlag()){
			JobsHis jobshis=new JobsHis();
  			jobshis.setJobID(jobType);
			jobshis.setLedgerDate(jobParam.getDate());
			jobshis.setPortfolio(getPortfolio(jobParam.getPortfolio()));
			jobshis.setUserID(jobParam.getUserID());
			
			try {
				List<GLCOOABase> jnlList=run(jobParam, container);
				container=getPrintjnl().printJNL(jnlList);
				
				jobshis.setStatus(container.isCorrectFlag());
				jobshis.setFailedDesc(container.getErrorMsg());
			}catch (Exception e) {
				jobshis.setStatus(false);
				jobshis.setFailedDesc(e.getMessage());
				
				container.setCorrectFlag(false);
				container.setErrorCode(ReturnResult.error_exception);
				container.setErrorMsg(e.getMessage());
				logger.error("Error", e);
			}
			
			if(!JixuUserUtil.isJoyinTablesFlag())
				jobsHisDao.saveEntity(jobshis);
			
			//防止container在传入的过程中被改变
			container.setHeader(JobsType.jobsTypeHM.get(jobType));
			container.setEntityObj(getPortfolio(jobParam.getPortfolio()));
		}
		
		return container;
	}
	
	/*
	 * protected Date getLastNavDate(String portnum, Date ledgerDate){ return
	 * commfuncService.loadLatestNavDate(portnum, ledgerDate); }
	 */
	
	protected String getSingleLotFlag(String portfolio) {
		String flag = "N";
		flag = loadSingleLotFlag(portfolio);
		return flag;
	}
	
	private String loadSingleLotFlag(String portfolio) {
		String flag = "N";
		try {
			PortfolioHdr hdr = commfuncService.loadPortfolio(portfolio);
			flag = hdr.getSinglelot();
		} catch (Exception e) {
			logger.error("Fail to load porfolio " + portfolio + " info");
		}
		return flag;
	}
	
	/**
	 * 
	 * @param glacct	获取的科目号
	 * @param bankdesc
	 * @param accttype
	 * @throws JNLException
	 */
	protected void checkCashAcctValiable(String glacct, String bankdesc, int accttype) throws JNLException{
		if(glacct==null || glacct.trim().equals("")){
			StringBuilder errmsg=new StringBuilder();
			errmsg.append(ReturnResult.getResultHM().get(ReturnResult.journal_account_unmap));
			errmsg.append(": ");
			errmsg.append(bankdesc);
			errmsg.append(": ");
			errmsg.append(BankAccountType.optionHM.get(accttype));
			throw new JNLException(errmsg.toString());
		}
	}
}
