package com.jixu.mvc.control.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.types.NavAssetObj;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.mvc.control.service.OutInterfaceService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Coaa;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;


@Service("outInterfaceServiceImpl")
public class OutInterfaceServiceImpl implements OutInterfaceService{
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Override
	public double getLatestNav(String portnum, Date navDate) {
		NavAssetObj navobj=navDao.getLatestNAV(portnum, navDate);
		if(navobj!=null) {
			return navobj.getNavPerShare();
		}else
			return 0;
	}
	
	@Override
	public String getPortnumByCode(String portcode) {
		PortfolioHdr portfolio=portfolioHdrDao.loadPortfolioByPortCode(portcode);
		if(portfolio!=null) {
			return portfolio.getId();
		}else {
			return "";
		}
	}
	
	@Override
	public int getBkAccIDByExtacct(String extacctcode) {
		BkCashAcct acct=bkCashAcctDao.loadBankCashByBkCode(extacctcode);
		if(acct!=null) {
			return acct.getId();
		}else {
			return 0;
		}
	}
	
	@Override
	public String getSecAcctByBkAcct(int bkacctid) {
		BkCashAcct acct=bkCashAcctDao.loadBankAcct(bkacctid);
		if(acct!=null) {
			return acct.getSecAcctCode();
		}else {
			return "";
		}
	}
	
	@Override
	public String getCoaacctByPortnumAcctName(String portnum, String acctname) {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		Coaa coaa=coaaDao.loadCoaByChartAndAcctname(portfolio.getCoaahdr(), acctname);
		if(coaa!=null) {
			return coaa.getAccntnum();
		}else {
			return "";
		}
	}
	
	@Override
	public String getCusipByName(String securityname) {
		IssueInfo sec=issueInfoDao.loadIssueBySecurityname(securityname);
		if(sec!=null)
			return sec.getId();
		else
			return "";
	}
	
	public String getCusipBySecid(String secid) {
		return issueInfoDao.getCusipBySecid(secid);
	}
}
