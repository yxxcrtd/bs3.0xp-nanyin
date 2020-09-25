package com.jixu.adapter.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.adapter.control.service.ExecuteJixuAdapter;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.ResultContainer;

@Service("executeJixuAdapter")
public class ExecuteJixuAdapterImpl implements ExecuteJixuAdapter{
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Resource(name="expAssembleOutput")
	private ExpAssembleOutput exportAdapter;
	
	@Resource(name="interafaceDataCenter")
	private DataServiceCenter importAdapter;
	
	@Override
	public ResultContainer executeAdapter(int adapterID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultContainer executeAdapter(int adapterID, HashMap<String, Object> outParams) {
		ResultContainer container=new ResultContainer();
		
		InterfaceSource source=interfaceSourceDao.loadSource(adapterID);
		if(source==null) {
			container.setErrorMsg(false, "适配器不存在: "+adapterID);
		}else {
			if(source.getSourceType()==InterfaceSourceType.exportType) {
				container=exportAdapter.exeSQLFetchResult(adapterID, outParams);
			}else {
				container=importAdapter.importData(-99, adapterID, new Date(), 0, new ArrayList<List<Object>>(), outParams);
			}
		}
		return container;
	}
}
