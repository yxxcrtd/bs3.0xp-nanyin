package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jixu.common.type.TransTypes;
import com.jixu.dao.intf.TranDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Tran;

/**
 * @ClassName 		TranDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranDao")
public class TranDaoImpl extends CommFuncDAOImpl implements TranDao{

	@Override
	public List<Tran> loadTrans(String portnum, Date ledgerDate, String cusip, int lotnum) {
		String hql="from Tran where portfolio=:portnum and ledgerDate=:ledgerDate and cusip.id=:cusip and lotnum=:lotnum";
		String[] params={"portnum","ledgerDate","cusip","lotnum"};
		Object[] values={portnum,ledgerDate,cusip,lotnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadTransToday(String portfolio, String cusip, int lotnum,Date ledgerDate) {
		String hql="from Tran where portfolio=:portfolio and cusip.id=:cusip and lotnum=:lotnum and ledgerDate=:ledgerDate order by id asc";
		String[] params={"portfolio","cusip","ledgerDate","lotnum"};
		Object[] values = { portfolio, cusip, ledgerDate,lotnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> getSaleUnits(String portnum, Date asOfDate) {
		String hql="from Tran where portfolio=:portnum and ledgerDate=:asOfDate and proceeds>0";
		String[] params={"portnum","asOfDate"};
		Object[] values={portnum,asOfDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> getSaleUnitsUnInternalTran(String portnum, Date asOfDate) {
		String hql="select tr from Tran tr, TranBasic tb where tr.tanum.id=tb.id and tr.portfolio=:portnum and tr.ledgerDate=:asOfDate "
				+ "and tr.proceeds>0 and tb.trantype!=:internalTran";
		String[] params={"portnum","asOfDate","internalTran"};
		Object[] values={portnum,asOfDate,TransTypes.Portfolio_InternalTran};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadIncomeUpdateTrans(String portnum, Date tradeDate) {
		String hql="from Tran where portfolio=:portnum and tradeDate=:tradeDate and trantype=:incomeUpdate";
		String[] params = {"portnum", "tradeDate","incomeUpdate"};
		Object[] values = {portnum, tradeDate, TransTypes.IncomeUpdate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadPurSaleTrans(Date tradedate, String portfolio) {
		String hql = "from Tran where ledgerDate=:tradedate and portfolio=:portfolio order by trannum asc";
		String[] params={"tradedate","portfolio"};
		Object[] values = {tradedate, portfolio};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadPurSaleTransWithCusip(Date tradedate, String portfolio, String cusip) {
		String hql = "from Tran where ledgerDate=:tradedate and portfolio=:portfolio and Cusip.id=:cusip order by id asc";
		String[] params = {"tradedate","portfolio","cusip"};
		Object[] values = {tradedate,portfolio,cusip};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadTranListByTranType(String portfolio, String cusip, Date tradeDate, int tradeType) {
		String hql="from Tran tb where tb.portfolio=:portfolio and tb.cusip.id=:cusip "
				+ "and tb.tradeDate=:tradeDate and tb.trantype=:tradeType";
		String[] params={"portfolio","cusip","tradeDate","tradeType"};
		Object[] values={portfolio,cusip,tradeDate,tradeType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Tran loadTran(String portnum, String cusip, int lotnum, int tranNum) {
		String hql="from Tran where portfolio=:portnum and cusip.id=:cusip and lotnum=:lotnum and trannum=:tranNum";
		String[] params={"portnum","cusip","lotnum","tranNum"};
		Object[] values={portnum,cusip,lotnum,tranNum};
		return (Tran)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public Tran loadTranByTanum(String tanum) {
		String hql="from Tran where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum}; 
		List<Tran> tranList=this.executeHQL(hql, params, values);
		if(tranList.size()>0){
			return tranList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public int getTranNumByCusipDate(String portnum, String cusip, Date tradeDate,String tanum) {
		String hql="select count(*) from Tran where portfolio=:portnum and cusip.id=:cusip and tradeDate>:tradeDate and tanum.id!=:tanum";
		String[] params={"portnum","cusip","tradeDate","tanum"};
		Object[] values={portnum, cusip, tradeDate,tanum};
		return this.exeFetchCount(hql, params, values);
	}
	
	@Override
	public List<Tran> loadtranList(List<String> portList, String secid) {
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		hql.append("from Tran where (");
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
		hql.append( " and cusip.securityid=:secid order by id desc");
		paramsList.add("secid");
		valuesList.add(secid);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public List<Tran> loadCusipTrans(String portfolio, String cusip, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		String hql="from Tran where portfolio=:portfolio and cusip.id=:cusip and ledgerDate>:startDate and ledgerDate<=:endDate order by id asc";
		String[] params={"portfolio","cusip","startDate","endDate"};
		Object[] values = { portfolio, cusip, startDate,endDate };
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadPositionTransBySetDate(String portnum, String cusip, int lotnum, Date endDate) {
		String hql="from Tran where portfolio=:portnum and cusip.id=:cusip and lotnum=:lotnum and settleDate<=:endDate order by id asc";
		String[] params={"portnum","cusip","lotnum","endDate"};
		Object[] values={portnum, cusip, lotnum, endDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadTransByTanum(String tanum) {
		String hql="from Tran where tanum.id=:tanum";
		String[] params={"tanum"};
		Object[] values={tanum}; 
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Tran loadPreMaturityDepositTran(String portnum, String cusip, int lotnum){
		String hql="from Tran where portfolio=:portnum and cusip.id=:cusip and lotnum=:lotnum and trannum=1";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum,cusip,lotnum};
		return (Tran)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<Tran> loadTransInPeriod(String portnum,List<String> portList, Date startDate, Date endDate, boolean classFetchFlag){
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		if(!classFetchFlag){
			hql.append("from Tran where portfolio=:portnum and ledgerDate>:startDate and ledgerDate<=:endDate and cusip is not null order by id asc");
			paramsList.add("portnum");
			valuesList.add(portnum);
		}else{
			hql.append("from Tran where (");
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
			hql.append(" and ledgerDate>:startDate and ledgerDate<=:endDate and cusip is not null order by id asc");
		}
		paramsList.add("startDate");
		valuesList.add(startDate);
		paramsList.add("endDate");
		valuesList.add(endDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public List<Tran> loadTransInPeriodWithAssetType(String portnum, Date startDate, Date endDate, String assetType){
		String hql="select tran from Tran tran, IssueInfo ii where tran.cusip.id=ii.id and ii.issueType=:assetType and tran.portfolio=:portnum and tran.ledgerDate>:startDate "
				+ "and tran.ledgerDate<=:endDate order by tran.id asc";;
		String[] params={"portnum","startDate","endDate","assetType"};
		Object[] values={portnum,startDate,endDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public Tran loadTranLot1(String portnum, String cusip, int lotnum) {
		String hql="from Tran where portfolio=:portnum and cusip.id=:cusip and lotnum=:lotnum and trannum=1";
		String[] params={"portnum", "cusip", "lotnum"};
		Object[] values={portnum, cusip, lotnum};
		return (Tran)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<Tran> loadCusipTransSettle(String portfolio, String cusip, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		String hql="from Tran where portfolio=:portfolio and cusip.id=:cusip and settleDate>:startDate and settleDate<=:endDate order by id asc";
		String[] params={"portfolio","cusip","startDate","endDate"};
		Object[] values = { portfolio, cusip, startDate,endDate };
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<Tran> loadTransInPeriodSettle(String portnum, List<String> portnums, Date startDate, Date endDate, boolean classFetchFlag){
		
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		StringBuilder hql=new StringBuilder();
		if(!classFetchFlag){
			hql.append("from Tran where portfolio=:portnum and greatest(settleDate,ledgerDate)>:startDate and greatest(settleDate,ledgerDate)<=:endDate and cusip is not null order by id asc");
			paramsList.add("portnum");
			valuesList.add(portnum);
		}else {
			hql.append("from Tran where (");
			for(int i=0;i<portnums.size();i++){
				hql.append("portfolio=:portNum"+i);
				paramsList.add("portNum"+i);
				valuesList.add(portnums.get(i));
				if(i<portnums.size()-1){
					hql.append(" or ");
				}
			}
			hql.append(" )");		
			
			hql.append("and greatest(settleDate,ledgerDate)>:startDate and greatest(settleDate,ledgerDate)<=:endDate and cusip is not null order by id asc");
		}
		
		paramsList.add("startDate");
		valuesList.add(startDate);
		paramsList.add("endDate");
		valuesList.add(endDate);
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(hql.toString(), params, values);
	}
	
	@Override
	public List<Tran> loadTransInPeriodSettleWithAssetType(String portnum, Date startDate, Date endDate, String assetType){
		String hql="select tran from Tran tran, IssueInfo ii where tran.cusip.id=ii.id and ii.issueType=:assetType and tran.portfolio=:portnum and greatest(tran.settleDate,tran.ledgerDate)>:startDate "
				+ "and greatest(tran.settleDate,tran.ledgerDate)<=:endDate order by tran.id asc";;
		String[] params={"portnum","startDate","endDate","assetType"};
		Object[] values={portnum,startDate,endDate,assetType};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteTranAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="delete from Tran where portfolio=:portnum and ledgerDate>=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum,ledgerDate};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<Tran> loadReopenRecalPostionTrans(String portnum, Date ledgerDate) {
		String hql="from Tran where portfolio=:portnum and ledgerDate<:ledgerDate order by cusip.id, lotnum, trannum asc";
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum,ledgerDate};
		return this.executeHQL(hql, params, values);
	}
}
