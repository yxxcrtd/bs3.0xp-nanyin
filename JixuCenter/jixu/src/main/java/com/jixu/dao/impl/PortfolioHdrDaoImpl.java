package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.PortfolioHdr;

/**
 * @ClassName PortfolioHdrDaoImpl
 * @author Pan Wei
 * @Date 2018年10月10日 下午23:29:03
 * @Description []
 */

@Repository("portfolioHdrDao")
public class PortfolioHdrDaoImpl extends CommFuncDAOImpl implements PortfolioHdrDao {

	@Override
	public PortfolioHdr loadPortfolioByPortCode(String portCode) {
		String hql = "from PortfolioHdr where shortCode=:portCode";
		String[] params = { "portCode" };
		Object[] values = { portCode };
		List<PortfolioHdr> portList = this.executeHQL(hql, params, values);
		if (portList.size() > 0) {
			return portList.get(0);
		} else {
			return null;
		}
	}

	// 根据证券账户获取投资组合
	@Override
	public PortfolioHdr loadPortByAcctID(int AcctID) {
		String hql = "select por from PortfolioHdr por,SecAcct sec where por.id=sec.portfolio and sec.acctID=:AcctID";
		String[] params = { "AcctID" };
		Object[] values = { AcctID };
		return (PortfolioHdr) this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public List<PortfolioHdr> getPortfoliosByClientNum(int clientnum) {
		List<PortfolioHdr> portfolios=null;
		if(clientnum==0){
			String hql="from PortfolioHdr";
			portfolios=this.executeHQL(hql);
		}else{
			String hql="from PortfolioHdr where portfolioClient=:clientnum";
			String[] params={"clientnum"};
			Object[] values={clientnum};
			portfolios=this.executeHQL(hql, params, values);
		}
		return portfolios;
	}
	
	@Override
	public List<PortfolioHdr> getPortfoliosByClientAndUser(int clientID, int userID) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("select po from PortfolioHdr po, UserPortfolioMap up where po.id=up.portfolio and up.users.userid=:userID");
		paramsList.add("userID");
		valuesList.add(userID);
		
		if(clientID>0){
			strBuilder.append(" and po.portfolioClient=:clientID");
			paramsList.add("clientID");
			valuesList.add(clientID);
		}
		
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<PortfolioHdr> portfolios=this.executeHQL(strBuilder.toString(), params, values);
		return portfolios;
	}
	
	@Override
	public void updatePortflio(PortfolioHdr portfolio) {
		updateEntity(portfolio);
	}
	
	@Override
	public List<PortfolioHdr> getPortfoliosByUserID(int userID, int clientCode, int portType, String portName) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("select po from PortfolioHdr po, UserPortfolioMap up where po.id=up.portfolio and po.portfolioClient=:clientCode and up.users.userid=:userID");
		paramsList.add("clientCode");
		valuesList.add(clientCode);
		
		paramsList.add("userID");
		valuesList.add(userID);
		
		if(portType>0){
			strBuilder.append(" and po.portType=:portType");
			paramsList.add("portType");
			valuesList.add(portType);
		}
		
		if(!portName.equals("")){
			strBuilder.append(" and (po.portName=:portName or po.shortName=:portName or po.shortCode=:portName)");
			paramsList.add("portName");
			valuesList.add(portName);
			
			paramsList.add("portName");
			valuesList.add(portName);
			
			paramsList.add("portName");
			valuesList.add(portName);
		}
		
		strBuilder.append(" order by po.id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<PortfolioHdr> portfolios=this.executeHQL(strBuilder.toString(), params, values);
		return portfolios;
	}
	
	@Override
	public List<PortfolioHdr> getPortfolios(int clientCode, int portType, String portName) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from PortfolioHdr where portfolioClient=:clientCode");
		paramsList.add("clientCode");
		valuesList.add(clientCode);
		
		if(portType>0){
			strBuilder.append(" and portfolioType=:portType");
			paramsList.add("portType");
			valuesList.add(portType);
		}
		
		if(!portName.equals("")){
			strBuilder.append(" and (portName=:portName or shortName=:portName or shortCode=:portName)");
			paramsList.add("portName");
			paramsList.add("portName");
			paramsList.add("portName");
			
			valuesList.add(portName);
			valuesList.add(portName);
			valuesList.add(portName);
		}
		
		strBuilder.append(" order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<PortfolioHdr> portfolios=this.executeHQL(strBuilder.toString(), params, values);
		return portfolios;
	}
	
	@Override
	public List<PortfolioHdr> loadPortfoliosByIds(String[] portnums) {
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append("from PortfolioHdr where ");
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		for(int i=0;i<portnums.length;i++){
			strBuilder.append("id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portnums[i]);
			if(i<portnums.length-1){
				strBuilder.append(" or ");
			}
		}
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		List<PortfolioHdr> portfolioList=this.executeHQL(strBuilder.toString(), params, values);
		return portfolioList;
	}
	
	@Override
	public List<PortfolioHdr> loadPortfolios(String portID) {
		String hql="from PortfolioHdr where portfolioClient=(select portfolioClient from PortfolioHdr where id=:portID)";
		String[] params={"portID"};
		Object[] values={portID};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public PortfolioHdr loadPortfolio(String portfolio) {
		PortfolioHdr portfoliohdr=(PortfolioHdr) this.loadEntity(PortfolioHdr.class, portfolio);
		//logger.info("Load Portfolio: "+portfolio+": " + portfoliohdr.getShortName());
		if(portfoliohdr!=null) {
			portfoliohdr.getShortName();
		}
		return portfoliohdr;
	}
	
	@Override
	public void updatePortfolio(PortfolioHdr portfolio){
		mergeEntity(portfolio);
	}
	
	@Override
	public boolean checkSamePortNameExist(String portCode) {
		String hql="from PortfolioHdr where shortCode=:portCode";
		String[] params={"portCode"};
		Object[] values={portCode};
		List<PortfolioHdr> portList=this.executeHQL(hql, params, values);
		if(portList.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	//查询该投资组合下子组合(包括该投资组合)
	@Override
	public List<String> loadPortfolioClassIDMap(String classid){
		String hql="select id from PortfolioHdr where ((id in (select portID from PortfolioClassIDMap where classID=:classid) or id=:classid))";
		String[] params={"classid"};
		Object[] values={classid};
		return this.executeHQL(hql, params, values);
	}
	
	
	@Override
	public List<String> loadPortfolioCIDRecursion(String classid){
		//查询子组合下的子投资组合
		String hql="select id from PortfolioHdr where (id in (select portID from PortfolioClassIDMap where classID=:classid))";
		String[] params={"classid"};
		Object[] values={classid};
		return this.executeHQL(hql, params, values);
	}
	
	// 查询该投资组合下子组合(不包括该投资组合,主要用于subPorts.do方法的子组合查询)
	@Override
	public List<PortfolioHdr> loadPortfolioByClassID(String classid){
		String hql="from PortfolioHdr where (id in (select portID from PortfolioClassIDMap where classID=:classid))";
		String[] params={"classid"};
		Object[] values={classid};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void deletePortfolioMap(String portnum) {
		String hql="delete from TranClo where portfolio=:portnum;";
		hql=hql+"delete from TranSum where portfolio=:portnum;";
		hql=hql+"delete from Tran where portfolio=:portnum;";
		hql=hql+"delete from BkCashFlow where portfolio.id=:portnum;";
		hql=hql+"delete from BkCashRcpy where portfolio.id=:portnum;";
		hql=hql+"delete from TranBasicFee where tanum.id in(select id from TranBasic where portfolio=:portnum);";
		hql=hql+"delete from TranBasic where portfolio=:portnum;";
		hql=hql+"delete from BkCashAcct where portfolio.id=:portnum;";
		hql=hql+"delete from SecAcct where portfolio=:portnum;";
		hql=hql+"delete from WorkFlowFactory where portfolio.id=:portnum;";
		hql=hql+"delete from PortfolioInterfaceMap where portfolio.id=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		String[] hqls=hql.split(";");
		for (String porthql : hqls) {
			this.deleteHql(porthql, params, values);
		}
	}
}
