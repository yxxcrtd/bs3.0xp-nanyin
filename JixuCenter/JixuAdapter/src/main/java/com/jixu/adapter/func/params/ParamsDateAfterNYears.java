package com.jixu.adapter.func.params;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;


@Component
public class ParamsDateAfterNYears extends AdditionFunc{
	private String funcname="系统日期前后N个月";
	
	public int getFuncType() {
		return InterfaceConfig.paramsType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "样例: -1";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		Date nowDate=new Date();
		Date today=DateUtil.convert2YYYYMMDD(nowDate);
		Date targetDate=DateUtil.getNextNDays(today, Integer.valueOf(inputValue));
		String dateStr=DateUtil.convertD2YYYY_MM_DD(targetDate);
		return "to_date('"+dateStr+"','yyyy-mm-dd')";
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
