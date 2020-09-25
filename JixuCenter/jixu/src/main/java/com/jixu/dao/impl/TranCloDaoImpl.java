package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.data.pojo.SecPosition;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.job.obj.IntDoubleObj;
import com.orm.obj.TranClo;

/**
 * @ClassName 		TranCloDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranCloDao")
public class TranCloDaoImpl extends CommFuncDAOImpl implements TranCloDao{

	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public void deleteTranClose(String portnum, Date closeDate) {
		String hql="delete from TranClo where portfolio=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,closeDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public int getTranCloseTotalRecords(String portnum, Date closeDate) {
		String hql="select count(*) from TranClo where portfolio=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum, closeDate};
		return this.exeFetchCount(hql, params, values);
	}
	
	@Override
	public List<TranClo> getLastTranCloAccrued(String portnum, String cusip, int lotnum, Date closeDate) {
		String hql="from TranClo where portfolio=:portnum and cusip=:cusip and lotnum=:lotnum and closeDate=:closeDate";
		String[] params={"portnum","cusip","lotnum","closeDate"};
		Object[] values={portnum,cusip,lotnum,closeDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public boolean checkAccrued(TranClo clo) {
		String hql="select count(*) from TranAccruedRec where cusip=:cusip and lotnum=:lotnum and lastTranNum=:lastTranNum and basedDate=:basedDate and portnum=:portnum";
		String[] params={"cusip","lotnum","lastTranNum","basedDate", "portnum"};
		Object[] values={clo.getCusip(),clo.getLotnum(),clo.getLstTrannum(),clo.getCloseDate(), clo.getPortfolio()};
		List secList = (List) this.executeHQL(hql, params, values);
		int countNum=Integer.valueOf(secList.get(0).toString());
		if(countNum<=0)
			return false;
		else 
			return true;
	}
	
	@Override
	public List<TranClo> loadTranCloWithAssetType(String portnum, Date closeDate, String assetType) {
		String hql="select cl from TranClo cl, IssueInfo ii where cl.cusip=ii.id and portfolio=:portnum and closeDate=:closeDate and ii.issueType=:assetType and accrued<>0";
		String[] params={"portnum","closeDate","assetType"};
		Object[] values={portnum,closeDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranClo> loadTranCloWithAssetTypeNoAccrued(String portnum, Date closeDate, String assetType) {
		String hql="select cl from TranClo cl, IssueInfo ii where cl.cusip=ii.id and portfolio=:portnum and closeDate=:closeDate and ii.issueType=:assetType";
		String[] params={"portnum","closeDate","assetType"};
		Object[] values={portnum,closeDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	//查询需要结转的债券
	@Override
	public List<TranClo> loadBondTranClo(String portnum, Date closeDate, String assetType) {
		String hql="select cl from TranClo cl, IssueInfo ii where cl.cusip=ii.id and portfolio=:portnum and closeDate=:closeDate and ii.issueType=:assetType";
		String[] params={"portnum","closeDate","assetType"};
		Object[] values={portnum,closeDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<IntDoubleObj> getPositionAmountNoLot(String portnum, Date asOfDate) {
		String sql="select cusip as objID, sum(amount) as amount from JXT_TRAN_CLO where portfolio=:portnum and closeDate=:asOfDate group by cusip";
		String[] params={"portnum","asOfDate"};
		Object[] values={portnum,asOfDate};
		return this.executeSQLReturnBean(sql, params, values, IntDoubleObj.class);
	}
	
	@Override
	public TranClo loadTCloByLot(String portnum, String cusip, int lotnum){
		String hql="from TranClo where portfolio=:portnum and cusip=:cusip and lotnum=:lotnum";
		String[] params={"portnum", "cusip", "lotnum"};
		Object[] values={portnum, cusip, lotnum}; 
		return (TranClo)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public TranClo getLastTranClo(String portnum, Date closeDate, String cusip, int lotnum) {
		String hql="from TranClo where portfolio=:portnum and closeDate<:closeDate and cusip=:cusip"
				+ " and lotnum=:lotnum order by closeDate desc";
		String[] params={"portnum","closeDate","cusip","lotnum"};
		Object[] values={portnum,closeDate,cusip,lotnum};
		//可以优化，只查询一条
		TranClo lastClo=(TranClo)this.executeHQLNo1Record(hql, params, values);
		return lastClo;
	}
	
	@Override
	public List<TranClo> loadTranCloList(String portfolio,List<String> portList, Date closeDate, boolean classFetchFlag) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		if(!classFetchFlag){
			hql.append("from TranClo where portfolio=:portfolio and closeDate=:closeDate");
			paramsList.add("portfolio");
			valuesList.add(portfolio);
		}else{
			hql.append("from TranClo where (");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				hql.append("portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");		
			hql.append(" and closeDate=:closeDate");
		}
		paramsList.add("closeDate");
		valuesList.add(closeDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public List<TranClo> loadTranCloListByAssetType(String portfolio, Date closeDate, String assetType) {
		String hql="select clo from TranClo clo, IssueInfo ii where clo.cusip=ii.id and portfolio=:portfolio and closeDate=:closeDate and ii.issueType=:assetType";
		String[] params={"portfolio","closeDate","assetType"};
		Object[] values = { portfolio, closeDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranClo> loadTranCloListBeforeCloseDateWithClassFlag(String portnum,List<String> portList, Date closeDate, String cusip, boolean classFetchFlag) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		if(!classFetchFlag){
			hql.append("from TranClo clo1 where clo1.id in (select max(clo.id) from TranClo clo where clo.portfolio=:portnum and clo.cusip=:cusip "
					+ "and clo.closeDate<=:closeDate group by clo.lotnum)");
			paramsList.add("portnum");
			valuesList.add(portnum);
		}else{
			hql.append("from TranClo clo1 where clo1.id in (select max(clo.id) from TranClo clo where (");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				hql.append("clo.portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");		
			hql.append(" and clo.cusip=:cusip and clo.closeDate<=:closeDate group by clo.lotnum)");
		}
		paramsList.add("closeDate");
		valuesList.add(closeDate);
		paramsList.add("cusip");
		valuesList.add(cusip);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	
	@Override
	public List<TranClo> loadTranCloListBeforeCloseDate(List<String> portList, Date closeDate, String cusip) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		hql.append("from TranClo clo where (");
		//子投资组合
		for(int i=0;i<portList.size();i++){
			hql.append("clo.portfolio=:portNum"+i);
			paramsList.add("portNum"+i);
			valuesList.add(portList.get(i));
			if(i<portList.size()-1){
				hql.append(" or ");
			}
		}
		hql.append(" )");		
		hql.append(" and clo.cusip=:cusip and clo.closeDate<=:closeDate and clo.altRate!=0 order by clo.closeDate asc");
		paramsList.add("closeDate");
		valuesList.add(closeDate);
		paramsList.add("cusip");
		valuesList.add(cusip);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public TranClo loadTranClo(String portfolio, Date closeDate, String cusip, int lotnum) {
		String hql="from TranClo where portfolio=:portfolio and closeDate<=:closeDate and cusip=:cusip and lotnum=:lotnum order by closeDate desc";
		String[] params={"portfolio","closeDate","cusip","lotnum"};
		Object[] values={portfolio,closeDate,cusip,lotnum};
		List<TranClo> tranCloList=this.executeHQL(hql, params, values);
		if(tranCloList.size()==0)
			return null;
		else 
			return tranCloList.get(0);
	}
	
	@Override
	public List<TranClo> loadTranCloListWithCusip(String portnum,List<String> portList, String cusip, Date closeDate, boolean classFetchFlag) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		if(!classFetchFlag){
			hql.append("from TranClo where portfolio=:portnum and cusip=:cusip and closeDate=:closeDate");
			paramsList.add("portnum");
			valuesList.add(portnum);
		}else{
			hql.append("from TranClo where (");
			//子投资组合
			for(int i=0;i<portList.size();i++){
				hql.append("portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portList.get(i));
				if(i<portList.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");		
			hql.append(" and cusip=:cusip and closeDate=:closeDate");
		}
		paramsList.add("cusip");
		valuesList.add(cusip);
		paramsList.add("closeDate");
		valuesList.add(closeDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}

	@Override
	public List<SecPosition> genResults(List<String> portList, Date closeDate) {
		return null;
	}

	@Override
	public List<TranClo> loadTranClose(String portnum, Date closeDate) {
		String hql="from TranClo where portfolio=:portnum and closeDate=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,closeDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteTranCloAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="delete from TranClo where portfolio=:portnum and closeDate>=:closeDate";
		String[] params={"portnum","closeDate"};
		Object[] values={portnum,ledgerDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public void deleteTranClose(String portnum, String cusip, int lotnum, Date closeDate) {
		String hql="delete from TranClo where portfolio=:portnum and cusip=:cusip and lotnum=:lotnum and closeDate>=:closeDate";
		String[] params={"portnum","cusip", "lotnum", "closeDate"};
		Object[] values={portnum,cusip, lotnum, closeDate};
		this.deleteHql(hql, params, values);
		
	}
}
