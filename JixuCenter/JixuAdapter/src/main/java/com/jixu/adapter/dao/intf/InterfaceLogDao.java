package com.jixu.adapter.dao.intf;

import java.util.Date;
import java.util.List;

import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.InterfaceLog;

public interface InterfaceLogDao extends CommFuncDAO{

	public void createInterfaceLog(InterfaceLog ilog);
	
	public void updateInterfaceLog(InterfaceLog ilog);
	
	public List<InterfaceLog> getLatestLog();
	
	public Pages<InterfaceLog> getLatestErrorMsg(int sourceID,Date startDate, Date endDate,Pages<InterfaceLog> pages);
	
	public InterfaceLog getLatestLog(int sourceID, Date exeucteTime);
}
