package com.jixu.adapter.dao.intf;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.AdapterUniqueRun;

public interface AdapterUniqueRunDao extends CommFuncDAO{
	
	public void saveAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun);
	
	public void updateAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun);
	
	public void deleteAdapterUniqueRun(AdapterUniqueRun adapterUniqueRun);
	
}
