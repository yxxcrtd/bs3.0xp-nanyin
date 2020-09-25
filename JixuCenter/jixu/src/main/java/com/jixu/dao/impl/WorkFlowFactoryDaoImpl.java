package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.types.WorkFlowStatus;
import com.jixu.dao.intf.WorkFlowFactoryDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.WorkFlowFactory;

/**
 * @ClassName 		WorkFlowFactoryDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("workFlowFactoryDao")
public class WorkFlowFactoryDaoImpl extends CommFuncDAOImpl implements WorkFlowFactoryDao{

	@Override
	public List<WorkFlowFactory> loadWorkFlow(String portnum, Date ledgerDate) {
		String hql="from WorkFlowFactory where portfolio.id=:portnum and ledgerDate=:ledgerDate";
		String[] params={"portnum","ledgerDate"};
		Object[] values={portnum, ledgerDate};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<WorkFlowFactory> loadWorkFlowWithStep(String[] portfolios, Date ledgerDate, int wfStep) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from WorkFlowFactory where ");
		
		strBuilder.append(" ledgerDate=:ledgerDate ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and ");
		
		strBuilder.append(" wfStep=:wfStep ");
		paramsList.add("wfStep");
		valuesList.add(wfStep);
		
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<Integer> loadWorkFlowJobStatus(String[] portfolios, Date ledgerDate,int status) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select wfStep from WorkFlowFactory where ");
		
		strBuilder.append(" ledgerDate=:ledgerDate ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and ");
		if(status==WorkFlowStatus.finish)
			strBuilder.append(" status!="+WorkFlowStatus.finish);
		else
			strBuilder.append(" status="+WorkFlowStatus.finish);
		
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<WorkFlowFactory> loadCompleteWorkFlowAfterDay(
			String[] portfolios, Date ledgerDate) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from WorkFlowFactory where ");
		
		strBuilder.append(" ledgerDate>=:ledgerDate ");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and status="+WorkFlowStatus.finish);
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(") order by ledgerDate desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	public void updateWorkFlowStatusToOpen(String portnum, Date ledgerDate) {
		String hql="update WorkFlowFactory set status=:status where portfolio.id=:portnum and ledgerDate>=:ledgerDate";
		String[] params={"portnum","ledgerDate","status"};
		Object[] values={portnum,ledgerDate,WorkFlowStatus.process};
		this.deleteHql(hql, params, values);
	}
}
