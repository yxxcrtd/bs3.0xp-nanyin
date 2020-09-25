package com.jixu.adapter.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.adapter.dao.intf.InterfaceMsgColMapDao;
import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.AdapterMsgColMap;

/**
 * @ClassName 		InterfaceMsgColMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("interfaceMsgColMapDao")
public class InterfaceMsgColMapDaoImpl extends CommFuncDAOImpl implements InterfaceMsgColMapDao{
	
	@Override
	public List<InterfaceMsgColMap> loadInterfaceMsgColMap(int sourceID) {
		String hql="from InterfaceMsgColMap where interfaceSource.sourceID=:sourceID";
		String[] params={"sourceID"};
		Object[] values={sourceID};
		return this.executeHQL(hql,params,values);
	}
	
	@Override
	public List<AdapterMsgColMap> loadAdapterMsgColMap(int adapterID) {
		String hql="from AdapterMsgColMap where adapterID=:adapterID";
		String[] params={"adapterID"};
		Object[] values={adapterID};
		return this.executeHQL(hql,params,values);
	}

	@Override
	public boolean checkCfgColExistInMap(int cfgID) {
		String hql="select count(*) from InterfaceMsgColMap where colConfig.id=:cfgID";
		int nums=this.exeFetchCount(hql, "cfgID", cfgID);
		if(nums>0)
			return true;
		else
			return false;
	}
}
