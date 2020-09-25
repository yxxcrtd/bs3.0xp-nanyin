package com.jixu.adapter.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.dao.intf.InterfaceLogDao;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.InterfaceLog;

@Repository("interfaceLogDao")
public class InterfaceLogDaoImpl extends CommFuncDAOImpl implements InterfaceLogDao{
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createInterfaceLog(InterfaceLog ilog) {
		this.saveEntityBean(ilog);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateInterfaceLog(InterfaceLog ilog) {
		this.updateEntityBean(ilog);
	}
	
	@Override
	public List<InterfaceLog> getLatestLog() {
		String hql="select il from InterfaceLog il where il.id in (select max(id) from InterfaceLog group by sourceID)";
		return this.executeHQL(hql);
	}

	@Override
	@Transactional
	public Pages<InterfaceLog> getLatestErrorMsg(int sourceID, Date startDate, Date endDate,Pages<InterfaceLog> pages) {
		StringBuilder sb=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		sb.append("select * from ADPT_InterfaceLog where sourceID=:sourceID ");
		paramsList.add("sourceID");
		valuesList.add(sourceID);
		if(startDate!=null){
			sb.append(" and endTime>=:startDate ");
			paramsList.add("startDate");
			valuesList.add(startDate);
		}
		
		if(endDate!=null){
			sb.append(" and endTime>=:endDate ");
			paramsList.add("endDate");
			valuesList.add(endDate);
		}
		
		sb.append(" order by id desc");
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.getPageSqlData(sb.toString(), params, values, false, pages, InterfaceLog.class);
	}
	
	@Override
	public InterfaceLog getLatestLog(int sourceID, Date exeucteTime) {
		String hql="from InterfaceLog where sourceID=:sourceID and startTime>=:exeucteTime order by id asc";
		String[] params= {"sourceID", "exeucteTime"};
		Object[] values= {sourceID, exeucteTime};
		return (InterfaceLog)this.executeHQLNo1Record(hql, params, values);
	}
}
