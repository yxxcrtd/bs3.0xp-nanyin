package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.InterfaceException;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.ManagerGroupDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.ManagerGroup;


@Service("calFuncManageGroupByPortName")
public class CalFuncManageGroupByPortName extends AdditionValFunc{
	
	@Autowired
	private ManagerGroupDao managerGroupDao;
	
	private String funcname="根据投资组合获取基金经理";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据投资组合获取基金经理	样例:[]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String portName=genConvertFormula(inputValue, colcfgMap);
		ManagerGroup mg=managerGroupDao.loadGroupNameByPortName(portName);
		if(mg.getId()!=0){
			return String.valueOf(mg.getId());
		}else{
			throw new InterfaceException("找不到投资组合对应的基金经理");
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
