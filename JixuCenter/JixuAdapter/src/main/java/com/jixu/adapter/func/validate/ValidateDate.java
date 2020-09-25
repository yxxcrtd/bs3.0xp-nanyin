package com.jixu.adapter.func.validate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		ValidateDateFunc
 * @author  		Pan Wei 
 * @Date 			2016年12月3日 下午5:10:22 	
 * @Description		[]  
 */

@Component("validateDateFunc")
public class ValidateDate extends AdditionFunc{
	
	private String funcname="校验日期";
	
	public int getFuncType() {
		return InterfaceConfig.baseType;
	}
	
	public boolean validateFiled(Object field) {
		Date checkDate=DateUtil.convertStr2Date(field.toString());
		if(checkDate==null){
			return false;
		}else{
			return true;
		}
	}
	
	public String fundUsefulDesc() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap){
		
		return null;
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
