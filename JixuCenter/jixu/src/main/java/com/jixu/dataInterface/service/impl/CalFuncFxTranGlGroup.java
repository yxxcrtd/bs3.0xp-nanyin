package com.jixu.dataInterface.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.InterfaceConfig;
import com.jixu.dao.intf.CoaaGroupDao;
import com.jixu.dataInterface.service.AdditionValFunc;


@Service("calFuncFxTranGlGroup")
public class CalFuncFxTranGlGroup extends AdditionValFunc{
	
	@Autowired
	private CoaaGroupDao coaaGroupDao;
	
	private String funcname="获取换汇对应的总账组";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "目前只支持换汇总账组配置成 XXXUSD/HKD.	样例:[多方货币],[空方货币]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String currencys=genConvertFormula(inputValue, colcfgMap);
		String[] curs=currencys.split(",");
		String longcur=curs[0];
		String shortcur=curs[1];
		String cursStr=longcur+"/"+shortcur;
		int id=coaaGroupDao.loadFxTranCoaaGroup(cursStr).getGroupNum();
		return String.valueOf(id);
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
