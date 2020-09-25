package com.jixu.adapter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;

/**
 * @ClassName 		InterfaceColConfigDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("interfaceColConfigDao")
public class InterfaceColConfigDaoImpl extends CommFuncDAOImpl implements InterfaceColConfigDao{
	
	@Override
	public List<InterfaceColConfig> loadInterfaceColConfig(int sourceID) {
		String hql="from InterfaceColConfig where interfacesource.sourceID=:sourceID order by orderNum, id asc";
		String[] params={"sourceID"};
		Object[] values={sourceID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<InterfaceColConfig> loadExpTemplateBasicAdvanceCfg(int sourceID) {
		String hql="from InterfaceColConfig where interfacesource.sourceID=:sourceID and colType in (1,2,6,7) order by orderNum, id asc";
		String[] params={"sourceID"};
		Object[] values={sourceID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<InterfaceColConfig> loadCfgOrderByTypeAndID(int sourceID, int[] colTypes) {
		StringBuilder sb=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		sb.append("from InterfaceColConfig where interfacesource.sourceID=:sourceID");
		paramsList.add("sourceID");
		valuesList.add(sourceID);
		
		sb.append(" and (");
		
		for(int i=0;i<colTypes.length;i++){
			sb.append("colType=:colType"+i);
			paramsList.add("colType"+i);
			valuesList.add(colTypes[i]);
			if(i<colTypes.length-1){
				sb.append(" or ");
			}
		}
		
		sb.append(" ) order by orderNum,id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<InterfaceColConfig> colConfigList=this.executeHQL(sb.toString(), params, values);
		return colConfigList;
	}

	@Override
	public List<InterfaceColConfig> loadCycleMaxParam(int sourceID) {
		String hql="from InterfaceColConfig where interfacesource.sourceID=:sourceID and colType=:cycleType order by orderNum, id asc";
		String[] params={"sourceID","cycleType"};
		Object[] values={sourceID, InterfaceConfig.paramsCycleType};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<InterfaceColConfig> loadCfgOrderByTypeAndColno(int sourceID, int[] colTypes) {
		StringBuilder sb=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		sb.append("from InterfaceColConfig where interfacesource.sourceID=:sourceID");
		paramsList.add("sourceID");
		valuesList.add(sourceID);
		
		sb.append(" and (");
		
		for(int i=0;i<colTypes.length;i++){
			sb.append("colType=:colType"+i);
			paramsList.add("colType"+i);
			valuesList.add(colTypes[i]);
			if(i<colTypes.length-1){
				sb.append(" or ");
			}
		}
		
		sb.append(" ) order by colNO,id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<InterfaceColConfig> colConfigList=this.executeHQL(sb.toString(), params, values);
		return colConfigList;
	}
}
