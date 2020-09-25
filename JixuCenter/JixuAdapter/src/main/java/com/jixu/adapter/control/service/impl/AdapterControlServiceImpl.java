package com.jixu.adapter.control.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.control.service.AdapterControlService;
import com.jixu.adapter.dao.intf.AdapterDao;
import com.jixu.adapter.service.export.ExportDataSource;
import com.orm.obj.Adapter;
import com.orm.obj.BasicDataSet;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.DataSource;
import com.orm.obj.UnionDataSet;

@Service("adapterService")
public class AdapterControlServiceImpl implements AdapterControlService {

	@Resource(name="adapterDao")
	private AdapterDao adapterDao;
	
	@Resource(name="exportDataSource")
	private ExportDataSource exportSource;
	
	@Override
	public List<DataSource> loadDataSourceList(String connName, int connType) {
		return adapterDao.loadDataSourceList(connName, connType);
	}
	
	@Override
	public List<DataSource> loadDataSourceList() {
		return adapterDao.loadDataSourceList();
	}
	
	@Override
	public List<BasicDataSet> loadBasicDataSetList() {
		return adapterDao.loadBasicDataSetList();
	}
	
	@Override
	public List<BasicDataSet> loadBasicDataSetList(String dsName) {
		return adapterDao.loadBasicDataSetList(dsName);
	}

	@Override
	public List<UnionDataSet> loadUnionDataSetList(String dsName) {
		return adapterDao.loadUnionDataSetList(dsName);
	}
	
	@Override
	public List<UnionDataSet> loadUnionDataSetList() {
		return adapterDao.loadUnionDataSetList();
	}

	@Override
	public List<Adapter> loadAdapterList(String name) {
		return adapterDao.loadAdapterList(name);
	}

	@Override
	public DataSource loadDataSoureDetail(int id) {
		return adapterDao.loadEntity(DataSource.class, id);
	}

	@Override
	public BasicDataSet loadBasicDataSetDetail(int id) {
		return adapterDao.loadEntity(BasicDataSet.class, id);
	}

	@Override
	public UnionDataSet loadUnionDataSetDetail(int id) {
		return adapterDao.loadEntity(UnionDataSet.class, id);
	}

	@Override
	public Adapter loadAdapterDetail(int id) {
		return adapterDao.loadEntity(Adapter.class, id);
	}
	
	@Override
	public DataSetColTemp loadDataSetColTemp(int id) {
		return adapterDao.loadEntity(DataSetColTemp.class, id);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteDataSetColTemp(int id) {
		DataSetColTemp dst=loadDataSetColTemp(id);
		adapterDao.deleteEntity(dst);;
	}
	
	@Override
	public List<DataSetColTemp> loadDataSetColTempByDataSetID(int dataSetID) {
		return adapterDao.loadDataSetColTempByDataSetID(dataSetID);
	}
}
