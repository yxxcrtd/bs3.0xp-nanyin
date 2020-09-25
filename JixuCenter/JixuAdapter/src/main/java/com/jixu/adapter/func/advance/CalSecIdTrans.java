package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

@Component
public class CalSecIdTrans extends AdditionFunc{
	
	private String funcname="债券id转换";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "债券id转换，如 IB11111转换为11111.IB	\r\n	样例: [A])";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	//[交易日],天数,假期ID
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		try {
			String inputLine=genConvertFormula(inputValue, colcfgMap);
			String secid="";
			// 债券id
			if (inputLine.startsWith("IB")) {
				secid=inputLine.trim().replace("IB", "") + ".IB";
			} else if (inputLine.startsWith("SZ")) {
				secid=inputLine.trim().replace("SZ", "") + ".SZ";
			}else if (inputLine.startsWith("SH")) {
				secid=inputLine.trim().replace("SH", "") + ".SH";
			}else{
				secid="errid"+inputLine.trim();
			}
			return secid;
		}catch(Exception e){
			return "";
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
