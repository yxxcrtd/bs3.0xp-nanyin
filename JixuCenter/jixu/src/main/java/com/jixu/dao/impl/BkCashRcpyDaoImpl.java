package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkCashRcpy;

/**
 * @ClassName 		BkCashRcpyDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bkCashRcpyDao")
public class BkCashRcpyDaoImpl extends CommFuncDAOImpl implements BkCashRcpyDao{
	
	@Override
	public List<BkCashRcpy> loadRCPYByEndDate(String portfolio, Date endDate) {
		String query = "from BkCashRcpy where PORTFOLIO = :portfolio and payDate <=:endDate and Remainbal >=0.01 order by Paydate asc";
		String[] params={"portfolio","endDate"};
		Object[] values = { portfolio, endDate };
		return this.executeHQL(query, params, values);
	}
	
	@Override
	public int loadRcpyNumByTanum(String tanum) {
		String hql="from BkCashRcpy where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum};
		List<BkCashRcpy> rcpyList=this.executeHQL(hql, params, values);
		if(rcpyList.size()>0){
			return rcpyList.get(0).getId();
		}else{
			return 0;
		}
	}
	
	@Override
	public List<BkCashRcpy> loadRcpyList(String tanum) {
		String hql="from BkCashRcpy where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<BkCashRcpy> loadSecCashList(String portfolio, Date compareDate) {
		String hql="from BkCashRcpy where portfolio.id=:portfolio and ledger<=:compareDate and paydate>:compareDate order by bkCashAcct.id,fxcurrency asc";
		String[] params={"portfolio","compareDate"};
		Object[] values = {portfolio,compareDate};
		return this.executeHQL(hql, params, values);
	}
	
	/*
	 * 
	 * 证券清算款的汇兑差额的冲销list.
	 */
	@Override
	public List<BkCashRcpy> loadSecCashListCX(String portfolio, Date ledgerDate) {
		String hql="from BkCashRcpy where portfolio.id=:portfolio and ledger<:ledgerDate and paydate=:ledgerDate order by bkCashAcct.id,fxcurrency asc";
		String[] params={"portfolio","ledgerDate"};
		Object[] values = {portfolio,ledgerDate};
		return this.executeHQL(hql, params, values);
	}
	
	public List<BkCashRcpy> loadRCPYByTanum(String tanum){
		String hql="from BkCashRcpy where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Pages<BkCashRcpy> getCashRcpy(Pages<BkCashRcpy> pages,String portNum, Date startDate, Date endDate) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append(" from BkCashRcpy where portfolio.id=:portNum");
		paramsList.add("portNum");
		valuesList.add(portNum);
		
		if(startDate!=null){
			strBuilder.append(" and paydate>=:startDate");
			paramsList.add("startDate");
			valuesList.add(startDate);
		}
		
		if(endDate!=null){
			strBuilder.append(" and paydate<=:endDate");
			paramsList.add("endDate");
			valuesList.add(endDate);
		}
		
		strBuilder.append(" order by id desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<BkCashRcpy> getPageHelperCashRcpy(Page<BkCashRcpy> page,String portNum, Date startDate, Date endDate) {
		return page;
	}
	
	@Override
	public void deleteRcpyAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="delete from BkCashRcpy where portfolio.id=:portnum and ledger>=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values = {portnum,ledgerDate};
		this.deleteHql(hql, params, values);
	}
}
