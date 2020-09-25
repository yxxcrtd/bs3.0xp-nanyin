package com.jixu.adapter.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.dao.intf.AdapterUniqueRunDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.AdapterUniqueRun;

@Repository("adapterUniqueRunDao")
public class AdapterUniqueRunDaoImpl extends CommFuncDAOImpl implements AdapterUniqueRunDao{
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void saveAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun) {
		this.saveEntityBean(adapterUniqueRun);
		
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun) {
		this.updateEntityBean(adapterUniqueRun);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun) {
		this.deleteEntity(adapterUniqueRun);
	}
}
