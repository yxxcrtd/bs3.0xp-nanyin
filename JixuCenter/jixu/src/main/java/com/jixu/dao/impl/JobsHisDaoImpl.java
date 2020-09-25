package com.jixu.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.common.tool.JixuUserUtil;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.jixu.mvc.control.model.WfStatus;
import com.orm.obj.JobsHis;

/**
 * @ClassName 		JobsHisDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("jobsHisDao")
public class JobsHisDaoImpl extends CommFuncDAOImpl implements JobsHisDao{
	
	@Override
	public List<JobsHis> loadErrorJobsHis(String[] ports, Date ledgrDate, List<Integer> jobsList) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from JobsHis where ledgerDate=:ledgrDate and status is false");
		paramsList.add("ledgrDate");
		valuesList.add(ledgrDate);
		
		strBuilder.append("  and ( ");
		
		
		for(int i=0;i<ports.length;i++){
			strBuilder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(ports[i]);
			if(i<ports.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		
		if(jobsList.size()>0){
			strBuilder.append("  and ( ");
			
			for(int i=0;i<jobsList.size();i++){
				strBuilder.append("jobID=:jobID"+i);
				paramsList.add("jobID"+i);
				valuesList.add(jobsList.get(i));
				if(i<jobsList.size()-1){
					strBuilder.append(" or ");
				}
			}
			strBuilder.append(")");
		}
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	
	@Override
	public List<JobsHis> loadJobsHis(String[] portfolios,Date ledgerDate) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("from JobsHis where ledgerDate=:ledgerDate");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append("  and ( ");
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("portfolio.id=:portfolio"+i);
			paramsList.add("portfolio"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(")");
		strBuilder.append(" order by status asc,id desc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		return this.executeHQL(strBuilder.toString(),params,values);
	}
	
	@Override
	public List<WfStatus> loadJobsTotalNum(String[] portfolios, Date ledgerDate){
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		strBuilder.append("select  wjm.workflowStep, jh.status, count(*) as num ");
		strBuilder.append(" from JobsHis jh,WorkflowJobMap wjm ");
		
		strBuilder.append(" where jh.jobID=wjm.jobID and jh.ledgerDate=:ledgerDate");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		strBuilder.append(" and (");
		
		for(int i=0;i<portfolios.length;i++){
			strBuilder.append("jh.portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portfolios[i]);
			if(i<portfolios.length-1){
				strBuilder.append(" or ");
			}
		}
		strBuilder.append(") group by wjm.workflowStep, jh.status");
		
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
	public void deleteJobsHist(String portnum, Date ledgerDate, int jobID) {
		if(JixuUserUtil.isJoyinTablesFlag())
			return;
		
		String hql="delete from JobsHis where portfolio.id=:portnum and ledgerDate=:ledgerDate and jobID=:jobID";
		String[] params={"portnum","ledgerDate","jobID"};
		Object[] values={portnum,ledgerDate,jobID};
		this.deleteHql(hql, params, values);
	}
	
	public void deleteJobsHists(String[] portnums, Date ledgerDate, int[] jobIDs) {
		if(JixuUserUtil.isJoyinTablesFlag())
			return;
		
		StringBuilder builder=new StringBuilder();
		builder.append("delete from JobsHis where (");
		
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		for(int i=0;i<portnums.length;i++){
			builder.append("portfolio.id=:portnum"+i);
			paramsList.add("portnum"+i);
			valuesList.add(portnums[i]);
			if(i<portnums.length-1){
				builder.append(" or ");
			}
		}
		
		builder.append(") and ledgerDate=:ledgerDate and (");
		paramsList.add("ledgerDate");
		valuesList.add(ledgerDate);
		
		for(int i=0;i<jobIDs.length;i++){
			builder.append("jobID=:jobID"+i);
			paramsList.add("jobID"+i);
			valuesList.add(jobIDs[i]);
			if(i<jobIDs.length-1){
				builder.append(" or ");
			}
		}
		builder.append(")");
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		this.deleteHql(builder.toString(), params, values);
	}
	
	@Override
	public JobsHis loadJobsHist(String portnum, Date ledgerDate, int jobID) {
		String hql="from JobsHis where portfolio.id=:portnum and ledgerDate=:ledgerDate and jobID=:jobID";
		String[] params={"portnum","ledgerDate","jobID"};
		Object[] values={portnum,ledgerDate,jobID};
		
		List<JobsHis> jobsHisList=this.executeHQL(hql, params, values);
		if(jobsHisList.size()>0){
			return jobsHisList.get(0);
		}else{
			return null;
		}
	}
}
