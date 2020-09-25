package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.MsgLogDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.WfFailedData;
import com.jixu.mvc.control.model.WfStatus;

/**
 * @ClassName 		MsgLogDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("msgLogDao")
public class MsgLogDaoImpl extends CommFuncDAOImpl implements MsgLogDao{

	@Override
	public Pages<MsgLog> loadMsgLog(Pages<MsgLog> pages, String[] ports, Date ledgerDate, int operationType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from MsgLog where operationType=:operationType and ledgerDate=:ledgerDate");
		paramsList.add("operationType");
		paramsList.add("ledgerDate");
		
		valuesList.add(operationType);
		valuesList.add(ledgerDate);
		
		if(operationType>WorkFlowSteps.fxRate){
			strBuilder.append("  and ( ");
			for(int i=0;i<ports.length;i++){
				strBuilder.append("portfolio=:portnum"+i);
				paramsList.add("portnum"+i);
				valuesList.add(ports[i]);
				if(i<ports.length-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		
		strBuilder.append(" order by status,ingore asc,logid desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.getPageHqlData(strBuilder.toString(), params, values, false, pages);
	}
	
	@Override
	public Page<MsgLog> loadPageHelperMsgLog(Page<MsgLog> page, String[] ports, Date ledgerDate, int operationType) {
		return page;
	}
	
	@Override
	public List<WfStatus> loadMsgLogMarketTotalNum(String[] portfolios, Date ledgerDate) {
		/*
		 *	以源为连接，查询该投资组合中，配置的源的信息 
		 */
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select operationType,status,count(*) as num");
		strBuilder.append(" from MsgLog where operationType in (1,2,3) ");
		
		strBuilder.append(" and ledgerDate=:ledgerDate and ingore is false ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and source.sourceID in (select distinct ineterfaceSource.sourceID from PortfolioInterfaceMap where ");
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		strBuilder.append(" group by operationType,status");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<WfStatus> wfStatusList=new ArrayList<WfStatus>();
		List<Object[]> resultList=this.executeHQL(strBuilder.toString(), params, values);
		for(Object[] obj:resultList){
			WfStatus wf=new WfStatus();
			wf.setOperation(Integer.valueOf(obj[0].toString()));
			wf.setStatus(Boolean.valueOf(obj[1].toString()));
			wf.setTotalnum(Integer.valueOf(obj[2].toString()));
			wfStatusList.add(wf);
		}
		return wfStatusList;
	}
	
	@Override
	public List<WfStatus> loadMsgLoTradeTotalNum(String[] portfolios, Date ledgerDate) {
		/*
		 *	以源为连接，查询该投资组合中，配置的源的信息 
		 */
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select operationType,status,count(*) as num");
		strBuilder.append(" from MsgLog where operationType in (4,5,6,7,8,9) ");
		
		strBuilder.append(" and ledgerDate=:ledgerDate and ingore is false ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and (");
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		strBuilder.append(" group by operationType,status");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<WfStatus> wfStatusList=new ArrayList<WfStatus>();
		List<Object[]> resultList=this.executeHQL(strBuilder.toString(), params, values);
		for(Object[] obj:resultList){
			WfStatus wf=new WfStatus();
			wf.setOperation(Integer.valueOf(obj[0].toString()));
			wf.setStatus(Boolean.valueOf(obj[1].toString()));
			wf.setTotalnum(Integer.valueOf(obj[2].toString()));
			wfStatusList.add(wf);
		}
		return wfStatusList;
	}
	
	@Override
	public List<WfFailedData> loadMsgLogMarketList(String[] portfolios, int operationStep, Date ledgerDate) {
		/*
		 *	以源为连接，查询该投资组合中，配置的源的信息 
		 */
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select new com.jixu.mvc.control.model.WfFailedData(logid,source.sourceDesc,'-','-',errorinfo,msg,users.username,operationTime,ingore) ");
		strBuilder.append(" from MsgLog where operationType=:operationType ");
		paramsList.add("operationType");
		valuesList.add(operationStep);
		
		strBuilder.append(" and ledgerDate=:ledgerDate and status is false ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and source.sourceID in (select distinct ineterfaceSource.sourceID from PortfolioInterfaceMap where ");
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		strBuilder.append(" order by operationTime desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
	
		List<WfFailedData> dataList=this.executeHQL(strBuilder.toString(), params, values);;
		
		StringBuilder strBuilder2=new StringBuilder();
		strBuilder2.append("select new com.jixu.mvc.control.model.WfFailedData(ml.logid,'手工执行','-','-',ml.errorinfo,ml.msg,users.username,ml.operationTime,ml.ingore) ");
		strBuilder2.append(" from MsgLog ml, Users users where ml.users.userid=users.userid and ml.operationType=:operationType ");
		paramsList.add("operationType");
		valuesList.add(operationStep);
		
		strBuilder2.append(" and ml.ledgerDate=:ledgerDate and ml.status is false ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder2.append(" and (");
		for(int i=0;i<portfolios.length;i++){
			strBuilder2.append("ml.portfolio=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder2.append(" or ");
			}
		}
		strBuilder2.append(")");
		strBuilder2.append(" order by ml.operationTime desc");
		List<WfFailedData> dataList2=this.executeHQL(strBuilder2.toString(), params, values);;
		
		dataList.addAll(dataList2);
		return dataList;
	}
	

	@Override
	public String loadMsg(long logid) {
		String hql="from MsgLog where logid=:logid";
		String[] params={"logid"};
		Object[] values={logid};
		List<MsgLog> list=this.executeHQL(hql, params, values);
		if(list.size()>0){
			MsgLog msglog=list.get(0);
			return msglog.getMsg();
		}else{
			return null;
		}
	}
}
