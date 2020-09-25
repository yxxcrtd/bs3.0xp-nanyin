package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dataInterface.service.AdditionValFunc;
import com.orm.obj.BkInfo;


@Service("calFuncGetBkInfoID")
public class CalFuncGetBkInfoID extends AdditionValFunc{
	
	private String funcname="根据银行名称获取银行ID";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "根据银行名称, 获取银行ID";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String name=genConvertFormula(inputValue, colcfgMap);;
		BkInfo bf=bkInfoDao.loadloadBkInfoIDByName(name);
		String bkID="";
		if(bf!=null){
			bkID=bf.getBankID();
		}
		return bkID;
		
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
