package com.jixu.adapter.control.service;

import java.util.List;

import com.orm.obj.Adapter;
import com.orm.obj.BasicDataSet;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.DataSource;
import com.orm.obj.UnionDataSet;

public interface AdapterControlService {
	
	public List<DataSource> loadDataSourceList(String connName,int connType);
	
	public DataSource loadDataSoureDetail(int id);
	
	public List<DataSource> loadDataSourceList();
	
	public List<BasicDataSet> loadBasicDataSetList();
	
	public List<BasicDataSet> loadBasicDataSetList(String dsName);
	
	public BasicDataSet loadBasicDataSetDetail(int id);
	
	public List<UnionDataSet> loadUnionDataSetList(String dsName);
	
	public List<UnionDataSet> loadUnionDataSetList();
	
	public UnionDataSet loadUnionDataSetDetail(int id);
	
	public List<Adapter> loadAdapterList(String name);
	
	public Adapter loadAdapterDetail(int id);
	
	public DataSetColTemp loadDataSetColTemp(int id);
	
	public void deleteDataSetColTemp(int id);
	
	public List<DataSetColTemp> loadDataSetColTempByDataSetID(int dataSetID);
}
