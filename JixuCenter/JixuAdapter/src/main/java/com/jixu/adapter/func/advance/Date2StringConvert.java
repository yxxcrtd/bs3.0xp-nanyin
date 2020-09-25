package com.jixu.adapter.func.advance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;





import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncDate2StringConvert")
public class Date2StringConvert extends AdditionFunc{
	private String funcname="日期转字符";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "转换日期	样例:[SourceDate],yyyy-mm-dd";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	@Override
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String[] params=inputValue.split(",");
		//日期不能转为字符型，不能用通用的genConvertFormula(inputValue, colcfgMap)来处理字符
		String param1=params[0].replace("[", "");
		param1=param1.replace("]", "");
		
		Date reParseDate=(Date)colcfgMap.get(param1);
		
		String convert1=params[1];
		SimpleDateFormat format=null;
		if(convert1.contains("MMM")){
			format=new SimpleDateFormat(convert1,Locale.ENGLISH);
		}else{
			format=new SimpleDateFormat(convert1);
		}
		return format.format(reParseDate);
		
		
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
