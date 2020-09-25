package com.jixu.adapter.dao.impl;

import org.springframework.stereotype.Repository;

import com.jixu.adapter.dao.intf.InterfaceParamsLastMaxDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.InterfaceParamsLastMax;

@Repository("interfaceParamsLastMaxDao")
public class InterfaceParamsLastMaxDaoImpl extends CommFuncDAOImpl implements InterfaceParamsLastMaxDao{
	@Override
	public InterfaceParamsLastMax getLastRecord(int cfgID) {
		String hql="from InterfaceParamsLastMax where cfgID=:cfgID order by id desc";
		return (InterfaceParamsLastMax)this.executeHQLNo1Record(hql, "cfgID", cfgID);
	}
}
