package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

@Component
public class CalKeyValueMap extends AdditionFunc{
	
	private String funcname="键值映射";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "键值映射	\r\n	样例: [A] | (key1:value1,key2:value2,key3:value3)";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	//[交易日],天数,假期ID
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		try {
			String inputLine=genConvertFormula(inputValue, colcfgMap);
			String[] arrs=inputLine.split("\\|");
			if(arrs.length!=2)
				return "";
			String sourcevalue=arrs[0].trim();
			String keyvalues=arrs[1].trim();
			keyvalues=keyvalues.substring(1, keyvalues.length()-1);
			String[] keyvalueArr=keyvalues.split(",");
			HashMap<String, String> maps=new HashMap<String, String>();
			for(String keyvalue:keyvalueArr) {
				String[] keys=keyvalue.split(":");
				if(keys.length!=2) {
					return "";
				}else {
					maps.put(keys[0], keys[1]);
				}
			}
			return maps.get(sourcevalue);
		}catch(Exception e) {
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
	
	public static void main(String[] args) {
		AdditionFunc aa=new CalKeyValueMap();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("A", "111");
		
		try {
			Object bbb=aa.calFunc("[A] | (111:222,222:333,333:444)", map);
			System.out.println(bbb.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
