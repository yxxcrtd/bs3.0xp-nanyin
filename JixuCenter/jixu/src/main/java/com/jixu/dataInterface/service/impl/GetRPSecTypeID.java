package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.RPSecTypeDao;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.RPSecType;


@Service("getRPSecTypeID")
public class GetRPSecTypeID extends AdditionValFunc{
	
	@Autowired
	private RPSecTypeDao rpSecTypeDao;
	
	private String funcname="获取行业代码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取行业代码    样例:[普通股],1 (1代表资产 2代表行业) ";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String rpSecType = genConvertFormula(inputValue, colcfgMap);
		String[] str=rpSecType.split(",");
		String typename=str[0];
		int tablecode=Integer.parseInt(str[1]); 
		String id = "";
		List<RPSecType> list = rpSecTypeDao.getRPSecTypename(typename,tablecode);
		if (list.size() > 0){
			id = Integer.toString(list.get(0).getId());
		}
		return id;
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
