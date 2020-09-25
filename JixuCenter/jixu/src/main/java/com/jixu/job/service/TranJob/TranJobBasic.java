package com.jixu.job.service.TranJob;

 /** 
  * @ClassName 		AssetJobBasic
  * @author  		Pan Wei 
  * @Date 			2017年1月19日 上午11:03:54 	
  * @Description		[]  
  */
 
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.job.service.TranJobService;
import com.jixu.job.service.Impl.JobCommonImpl;

public abstract class TranJobBasic implements TranJobService{
	@Resource(name="jobCommonImpl")
	protected JobCommonImpl jobCom;
	
	@Autowired
	protected TranDao tranDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected TranCloDao tranCloDao;
	
	@Autowired
	protected BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	protected CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	protected BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	protected TranFeeDefDao tranFeeDefDao;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Resource(name="incomeExpenseCal")
	protected IncomeExpenseCal incomeCal;
	
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
