package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.Broker;


@Service("calFuncBrokerBySeatNumber")
public class CalFuncBrokerBySeatNumber extends AdditionValFunc{
	@Autowired
	private BrokerDao brokerDao;
	
	private String funcname="证券席位号获取券商";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据证券席位号获取券商	样例:[]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String seatNumber=genConvertFormula(inputValue, colcfgMap);
		Broker br=brokerDao.loadBrokerBySeatNumber(seatNumber);
		if(br!=null){
			return br.getBrokerID();
		}else{
			throw new InterfaceException("找不到证券席位号对应的券商");
		}
	}
	
	public boolean filterField(Object field, String compareField){
		return false;
	}
	
	@Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
