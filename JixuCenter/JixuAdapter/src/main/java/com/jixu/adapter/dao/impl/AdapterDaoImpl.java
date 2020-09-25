package com.jixu.adapter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.adapter.dao.intf.AdapterDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.Adapter;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.BasicDataSet;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.DataSource;
import com.orm.obj.UnionDataSet;

/**
 * @ClassName 		AdapterDaoImpl
 * @author  		Wang Zhongyuan
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("adapterDao")
public class AdapterDaoImpl extends CommFuncDAOImpl implements AdapterDao{

	@Override
	public List<DataSource> loadDataSourceList(String connName, int connType) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from DataSource where 1=1 ");
		if(connName!=null&&!connName.equals("")){
			strBuilder.append("and connName=:connName ");
			paramsList.add("connName");
			valuesList.add(connName);
		}
		if(connType!=0){
			strBuilder.append("and connType=:connType ");
			paramsList.add("connType");
			valuesList.add(connType);
		}
		strBuilder.append("order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<DataSource> loadDataSourceList() {
		String hql="from DataSource ";
		return this.executeHQL(hql);
	}
	

	@Override
	public List<BasicDataSet> loadBasicDataSetList(String dsName) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from BasicDataSet where 1=1 ");
		if(dsName!=null&&!dsName.equals("")){
			strBuilder.append("and name=:dsName ");
			paramsList.add("dsName");
			valuesList.add(dsName);
		}
		strBuilder.append("order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<BasicDataSet> loadBasicDataSetList() {
		String hql="from BasicDataSet";
		return this.executeHQL(hql);
	}

	@Override
	public List<UnionDataSet> loadUnionDataSetList(String dsName) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from UnionDataSet where 1=1 ");
		if(dsName!=null&&!dsName.equals("")){
			strBuilder.append("and name=:dsName ");
			paramsList.add("dsName");
			valuesList.add(dsName);
		}
		strBuilder.append("order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<UnionDataSet> loadUnionDataSetList() {
		String hql="from UnionDataSet";
		return this.executeHQL(hql);
	}

	@Override
	public List<Adapter> loadAdapterList(String name) {
		StringBuilder strBuilder=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		strBuilder.append("from Adapter where 1=1 ");
		if(name!=null&&!name.equals("")){
			strBuilder.append("and name=:name ");
			paramsList.add("name");
			valuesList.add(name);
		}
		strBuilder.append("order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		return this.executeHQL(strBuilder.toString(), params, values);
	}
	
	@Override
	public List<AdapterColConfig> loadAdapterColConfig(int dataSetID, int[] colTypes) {
		StringBuilder sb=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		sb.append("from AdapterColConfig where dataSetID=:dataSetID");
		paramsList.add("dataSetID");
		valuesList.add(dataSetID);
		
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
		
		List<AdapterColConfig> colConfigList=this.executeHQL(sb.toString(), params, values);
		return colConfigList;
	}
	
	@Override
	public List<DataSetColTemp> loadDataSetColTemp(int dataSetID, int[] colTypes) {
		StringBuilder sb=new StringBuilder();
		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		
		sb.append("from DataSetColTemp where dataSetID=:dataSetID");
		paramsList.add("dataSetID");
		valuesList.add(dataSetID);
		
		sb.append(" and (");
		
		for(int i=0;i<colTypes.length;i++){
			sb.append("colType=:colType"+i);
			paramsList.add("colType"+i);
			valuesList.add(colTypes[i]);
			if(i<colTypes.length-1){
				sb.append(" or ");
			}
		}
		
		sb.append(" ) order by id asc");
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		List<DataSetColTemp> colConfigList=this.executeHQL(sb.toString(), params, values);
		return colConfigList;
	}
	
	@Override
	public List<DataSetColTemp> loadDataSetColTempByDataSetID(int dataSetID) {
		String hql="from DataSetColTemp where dataSetID=:dataSetID order by id asc";
		String[] params={"dataSetID"};
		Object[] values={dataSetID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<AdapterColConfig> loadAdapterColConfig(int dataSetID) {
		String hql="from AdapterColConfig where dataSetID=:dataSetID order by orderNum, id asc";
		String[] params={"dataSetID"};
		Object[] values={dataSetID};
		return this.executeHQL(hql,params,values);
	}
	
}
