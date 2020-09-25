package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.SecTradeSummaryModel;
import com.orm.obj.BkCashAcct;
import com.orm.obj.TranBasic;

/**
 * @ClassName 		TranBasicDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("tranBasicDao")
public class TranBasicDaoImpl extends CommFuncDAOImpl implements TranBasicDao{
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Override
	public List<TranBasic> loadTranBasicList(String portnum, String cusip, int lotnum) {
		String hql="select tb from TranBasic tb, Tran tr where tb.id=tr.tanum.id and tr.lotnum=:lotnum and tb.portfolio=:portnum and tb.cusip.id=:cusip order by tr.id asc";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum,cusip,lotnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadTranBasic(String portnum, Date ledgerDate) {
		String hql="from TranBasic where (portfolio=:portnum or portfolio2=:portnum) and ledgerdate=:ledgerDate and postStatus=:posted";
		String[] params={"portnum","ledgerDate","posted"};
		Object[] values={portnum,ledgerDate,PostStatusType.post};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadTranBasicAfterLedgerDate(String portnum, Date ledgerDate) {
		String hql="from TranBasic where (portfolio=:portnum or portfolio2=:portnum) and ledgerdate>=:ledgerDate and postStatus!=:posted";
		String[] params={"portnum","ledgerDate","posted"};
		Object[] values={portnum,ledgerDate,PostStatusType.delete};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadTranBasicByCusip(String portnum, String cusip, Date ledgerDate) {
		String hql="from TranBasic where (portfolio=:portnum or portfolio2=:portnum) and ledgerdate=:ledgerDate and cusip.id=:cusip and postStatus=:posted";
		String[] params={"portnum","ledgerDate","cusip","posted"};
		Object[] values={portnum,ledgerDate,cusip,PostStatusType.post};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public int getTranTotalNum(String cusip) {
		String hql="select count(*) from TranBasic where cusip.id=:cusip and postStatus=:posted";
		String[] params={"cusip","posted"};
		Object[] values={cusip,PostStatusType.post};
		List<Object> resultList=this.executeHQL(hql, params, values);
		int count=Integer.valueOf(resultList.get(0).toString());
		return count;
	}
	
	@Override
	public TranBasic getMapedContractTranBasic(String portnum, String cusip, int lotnum) {
		String hql="select tb from TranBasic tb, Tran tr where tb.id=tr.tanum.id and tr.portfolio=:portnum "
				+ "and tr.cusip.id=:cusip and tr.lotnum=:lotnum order by tr.trannum asc";
		String[] params={"portnum","cusip","lotnum"};
		Object[] values={portnum, cusip, lotnum};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public TranBasic getSwapFxTranBasic(String portnum, String cusip) {
		String hql="select tb from TranBasic tb where tb.portfolio=:portnum and tb.cusip.id=:cusip and postStatus=:postStatus";
		String[] params={"portnum","cusip","postStatus"};
		Object[] values={portnum, cusip,PostStatusType.post};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public int getTranBasicCountByPortnum(String portnum) {
		String hql="select count(tanum) from TranBasic where portfolio=:portnum and postStatus=:postStatus";
		String[] params={"portnum","postStatus"};
		Object[] values={portnum, PostStatusType.post};
		List<Object> resultList=this.executeHQL(hql, params, values);
		int count=Integer.valueOf(resultList.get(0).toString());
		return count;
	}
	
	@Override
	public Pages<TranBasic> getTranBasic(Pages<SecTradeSummaryModel> pages,String secType, String secID, 
			List<String> portList, Date startDate, Date endDate,Boolean displayDelete) {
		
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from TranBasic where cusip is not null ");
		strBuilder.append(" and (");
		//子投资组合
		for(int i=0;i<portList.size();i++){
			strBuilder.append("portfolio=:portNum"+i);
			paramsList.add("portNum"+i);
			valuesList.add(portList.get(i));
			if(i<portList.size()-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(" )");
		
		if(startDate!=null){
			strBuilder.append(" and tradedate>=:startDate");
			paramsList.add("startDate");
			valuesList.add(startDate);
		}
		
		if(endDate!=null){
			strBuilder.append(" and tradedate<=:endDate");
			paramsList.add("endDate");
			valuesList.add(endDate);
		}
		
		if(!secType.equals("0")){
			strBuilder.append(" and cusip.issueType=:secType");
			paramsList.add("secType");
			valuesList.add(secType);
		}
		
		if(!secID.equals("")){
			strBuilder.append(" and cusip.securityid=:secID");
			paramsList.add("secID");
			valuesList.add(secID);
		}
		
		if(!displayDelete){
			strBuilder.append(" and postStatus<>:PostStatusType");
			paramsList.add("PostStatusType");
			valuesList.add(PostStatusType.delete);
		}
		
		strBuilder.append(" order by tradedate desc, tanum desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<TranBasic> getPageHelperTranBasic(Page<TranBasic> page,String secType, String secID, 
			List<String> portList, Date startDate, Date endDate,Boolean displayDelete) {
		return page;
	}
	
	
	@Override
	public TranBasic loadTranBasicByID(String tanum) {
		return this.loadEntity(TranBasic.class, tanum);
	}
	
	@Override
	public Pages<TranBasic> getTranBasicByTranType(List<String> portList, int[] tranTypes,
			Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete) {
		
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from TranBasic where ");
		strBuilder.append("(");
		//子投资组合
		for(int i=0;i<portList.size();i++){
			strBuilder.append("portfolio=:portNum"+i);
			paramsList.add("portNum"+i);
			valuesList.add(portList.get(i));
			if(i<portList.size()-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(" )");
		
		if(startDate!=null){
			strBuilder.append(" and tradedate>=:startDate");
			paramsList.add("startDate");
			valuesList.add(startDate);
		}
		
		if(endDate!=null){
			strBuilder.append(" and tradedate<=:endDate");
			paramsList.add("endDate");
			valuesList.add(endDate);
		}
		
		if(tranTypes.length>0){
			strBuilder.append(" and (");
			for(int i=0;i<tranTypes.length;i++){
				strBuilder.append("trantype=:trantype"+i);
				paramsList.add("trantype"+i);
				valuesList.add(tranTypes[i]);
				if(i<tranTypes.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		
		if(!displayDelete){
			strBuilder.append(" and postStatus<>:PostStatusType");
			paramsList.add("PostStatusType");
			valuesList.add(PostStatusType.delete);
		}
		
		strBuilder.append(" order by tradedate desc");

		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<TranBasic> getPageHelperTranBasicByTranType(List<String> portList, int[] tranTypes,
			Date startDate, Date endDate,Page<TranBasic> page,Boolean displayDelete) {
		return page;
	}
	
	@Override
	public TranBasic loadTranBasicLot1(String portnum, String cusip, int lotnum) {
		String hql="select tb from TranBasic tb, Tran tr where tr.portfolio=:portnum and tr.cusip.id=:cusip and tr.lotnum=:lotnum and tr.trannum=1 and tb.id=tr.tanum.id";
		String[] params={"portnum", "cusip", "lotnum"};
		Object[] values={portnum, cusip, lotnum};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public TranBasic getFCOpenTranBasic(String portnum, String cusip,int tranType){
		String hql="from TranBasic tb where portfolio=:portnum and cusip.id=:cusip and trantype=:tranType and postStatus!=:posted";
		String[] params={"portnum", "cusip","tranType","posted"};
		Object[] values={portnum, cusip,tranType,PostStatusType.delete};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<TranBasic> getForwardSmfTranBasic(String cusip,int tranType){
		String hql="from TranBasic tb where cusip.id=:cusip and trantype!=:tranType and postStatus!=:posted";
		String[] params={"cusip","tranType","posted"};
		Object[] values={cusip,tranType,PostStatusType.delete};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public TranBasic getForwardSmf(String cusip, int tranType){
		String hql="from TranBasic tb where cusip.id=:cusip and trantype=:tranType and postStatus!=:posted";
		String[] params={"cusip","tranType","posted"};
		Object[] values={cusip,tranType,PostStatusType.delete};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public TranBasic loadForwardListByTranType(String portfolio, String cusip, int tradeType) {
		String hql="from TranBasic tb where tb.portfolio=:portfolio and tb.cusip.id=:cusip "
				+ " and tb.trantype!=:tradeType and postStatus!=:posted";
		String[] params={"portfolio","cusip","tradeType","posted"};
		Object[] values={portfolio,cusip,tradeType,PostStatusType.delete};
		return (TranBasic)this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadUndeletedTranBasicByCusip(String portfolio, String cusip,Date ledgerDate, int tradeType) {
		String hql="from TranBasic where portfolio=:portfolio and cusip.id=:cusip "
				+ " and trantype=:tradeType and postStatus!=:posted and ledgerdate=:ledgerDate";
		String[] params={"portfolio","cusip","tradeType","ledgerDate","posted"};
		Object[] values={portfolio,cusip,tradeType,ledgerDate,PostStatusType.delete};
		return this.executeHQL(hql, params, values);
	}

	@Override
	public List<TranBasic> loadTranBasicList(String assetType, Date tradeDate) {
		String hql="select tb from TranBasic tb,IssueInfo ii where tb.cusip.id=ii.id and tb.tradedate=:tradeDate and ii.issueType=:assetType order by tb.id desc";
		String[] params={"assetType","tradeDate"};
		Object[] values={assetType,tradeDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<String> loadAutoTranBasicList(String portnum, Date ledgerDate) {
		String hql="select id from TranBasic where portfolio=:portnum and IMPORTTYPE=:IMPORTTYPE and ledgerdate=:ledgerDate and postStatus=:posted";
		String[] params= {"portnum", "IMPORTTYPE", "ledgerDate", "posted"};
		Object[] values= {portnum, JixuDictCode.IMP_TRADE_TYPE_AUTO, ledgerDate, PostStatusType.post};
		return this.executeHQL(hql, params, values);
	}

	@Override
	public List<TranBasic> loadTranBasicByContract(String portnum,String cusip,String contract) {
		String hql="from TranBasic where portfolio=:portnum and cusip.id=:cusip and contract=:contract and postStatus=:posted";
		String[] params={"portnum","cusip","posted","contract"};
		Object[] values={portnum,cusip,PostStatusType.post,contract};
		return this.executeHQL(hql, params, values);
	}

	@Override
	public List<TranBasic> loadTranBasicCusipPost(String portnum, String cusip) {
		String hql="from TranBasic where portfolio=:portnum and cusip.id=:cusip and postStatus=:posted";
		String[] params={"portnum","cusip","posted"};
		Object[] values={portnum,cusip,PostStatusType.post};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void updateTranBasicPostStatusLedgerDate(String portnum, Date ledgerDate) {
		String hql="update TranBasic set postStatus=:unpostStatus where portfolio=:portnum and postStatus=:postStatus and ledgerdate>=:ledgerDate";
		String[] params={"portnum","ledgerDate", "unpostStatus", "postStatus"};
		Object[] values={portnum,ledgerDate, PostStatusType.unpost, PostStatusType.post};
		this.deleteHql(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadUnpostedTranBasic(String portnum, Date ledgerDate) {
		String hql="from TranBasic where portfolio=:portnum and ledgerdate=:ledgerDate and postStatus=:postStatus order by id asc";
		String[] params={"portnum","ledgerDate", "postStatus"};
		Object[] values={portnum,ledgerDate, PostStatusType.unpost};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadForwrdUnpostedTranBasic(String portnum, Date ledgerDate,String assetType) {
		String hql="select tb from TranBasic tb,IssueInfo ii where tb.cusip.id=ii.id and ii.issueType=:assetType and tb.portfolio=:portnum and tb.ledgerdate=:ledgerDate and tb.postStatus=:postStatus order by tb.id asc";
		String[] params={"portnum","ledgerDate","assetType","postStatus"};
		Object[] values={portnum,ledgerDate,assetType,PostStatusType.unpost};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deleteAutoTranBasic(String portnum, Date ledgerDate) {
		String hql="update TranBasic set postStatus=:deleteStatus where portfolio=:portnum and IMPORTTYPE=:IMPORTTYPE and ledgerdate>=:ledgerDate and postStatus=:unpostStatus";
		String[] params={"portnum","ledgerDate", "IMPORTTYPE", "deleteStatus", "unpostStatus"};
		Object[] values={portnum,ledgerDate, JixuDictCode.IMP_TRADE_TYPE_AUTO, PostStatusType.delete, PostStatusType.unpost};
		this.deleteHql(hql, params, values);
	}

	@Override
	public List<TranBasic> loadTranBasicByBankAcct(String portnum, int acctNo) {
		String hql="from TranBasic where (bkCashacctNo.id=:acctNo or bkCashacctNo2.id=:acctNo) and portfolio=:portnum and postStatus!=:deleteStatus";
		String[] params={"acctNo", "portnum","deleteStatus"};
		Object[] values={acctNo, portnum,PostStatusType.delete};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<TranBasic> loadManualValTran(String portnum, Date startDate, Date endDate) {
		String hql="from TranBasic where portfolio=:portnum and trantype=:mannualType and ledgerdate>:startDate and ledgerdate<=:endDate order by id asc";
		String[] params={"portnum", "portnum","mannualType", "startDate", "endDate"};
		Object[] values={portnum, portnum, TransTypes.ValMannulPositionUpdate, startDate, endDate};
		return this.executeHQL(hql, params, values);
	}
}
