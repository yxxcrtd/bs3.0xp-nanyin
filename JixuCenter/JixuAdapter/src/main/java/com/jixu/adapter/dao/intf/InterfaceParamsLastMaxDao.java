package com.jixu.adapter.dao.intf;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.InterfaceParamsLastMax;

public interface InterfaceParamsLastMaxDao extends CommFuncDAO{
	/**
	 * 获取最新的一条记录
	 * @param cfgID
	 * @return
	 */
	public InterfaceParamsLastMax getLastRecord(int cfgID);
}
