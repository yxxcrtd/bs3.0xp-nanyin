package com.jixu.job.service.TaxFeeJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.dao.intf.YGZTaiZhangDao;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.service.TaxFeeJobService;
import com.jixu.job.service.Impl.JobCommonImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.YGZTaiZhang;

/** 
 * @ClassName 		AfterJobAbstract
 * @author  		Pan Wei 
 * @Date 			2018年5月9日 下午2:10:23 	
 * @Description		[]  
 */

public abstract class AfterJobAbstract implements TaxFeeJobService{
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected PortfolioHdrDao portfolioHdrDao;
	
	@Resource(name="jobCommonImpl")
	protected JobCommonImpl jobCom;
	
	@Autowired
	protected VoucherDetailsDao voucherDetailDao;
	
	@Autowired
	protected YGZTaiZhangDao ygzTaiZhangDao;
	
	@Autowired
	protected TranDao tranDao;
	
	@Autowired
	protected TranCloDao tranCloDao;
	
	@Autowired
	protected IncomeExpenseCal incomeExpenseCal;
	
	protected static final Logger logger = LoggerFactory.getLogger(AfterJobAbstract.class);
	
	protected abstract GLCOOABase exeAfterJob(int userID, PortExpenseCfg cfg, List<AcctDCBalance> balList, Date asOfDate) throws JNLException;
	
	protected String getKey(int taxfeetype, String issuetype, String cusip, boolean ysflag){
		return taxfeetype+"-"+issuetype+"-"+cusip+"-"+ysflag;
	}
	
	@Override
	public GLCOOABase executeJob(int userID, List<AcctDCBalance> balList, PortExpenseCfg cfg, Date asOfDate) throws JNLException {
		return exeAfterJob(userID, cfg, balList, asOfDate);
	}
	
	protected void deleteJobResult(PortExpenseCfg cfg, Date asOfDate) {
		String portnum=cfg.getPortfolio().getId();
		int taxfeetype=cfg.getPortFeeID();
		ygzTaiZhangDao.deleteYGZTaiZhang(portnum, DateUtil.ConvertUtil2Sql(asOfDate), taxfeetype);
	}
	
	/**
	 * 返回前一天的相关台账信息
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	protected HashMap<String, YGZTaiZhang> getYGZTaiZhang(String portnum, java.sql.Date asOfDate){
		List<YGZTaiZhang> lastYGZTaiZhangList=ygzTaiZhangDao.loadYGZTaiZhang(portnum, asOfDate);
		HashMap<String, YGZTaiZhang> lasttMap=new HashMap<String, YGZTaiZhang>();
		for(YGZTaiZhang lasttz:lastYGZTaiZhangList){
			lasttMap.put(getKey(lasttz.getTaxFeeType(),lasttz.getIssueType(),lasttz.getCusip(),lasttz.isYsfs()), lasttz);
		}
		return lasttMap;
	}
	
	/**
	 * 返回应税的编号, 普通股，优先股这类的选择
	 * @return
	 */
	protected List<String> getYingShuiItemList(PortExpenseCfg cfg){
		String[] itemsArr=cfg.getActionItems().split(",");
		List<String> yinshuiList=new ArrayList<String>();
		for(String item:itemsArr){
			yinshuiList.add(item);
		}
		return yinshuiList;
	}
}
