package com.jixu.adapter.func.advance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;





import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncMMddyy2yyyyMMdd")
public class CalFuncMMddyy2yyyyMMdd extends AdditionFunc{
	private String funcname="转换日期";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "转换日期	样例:11/09/12,MM/dd/yy,yyyymmdd";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String[] results=newValue.split(",");
		String date1=results[0];
		String convert1=results[1];
		String convert2=results[2];
		SimpleDateFormat format=null;
		if(convert1.contains("MMM")){
			format=new SimpleDateFormat(convert1,Locale.ENGLISH);
		}else{
			format=new SimpleDateFormat(convert1);
		}
		Date date2=format.parse(date1);
		if(convert2.contains("MMM")){
			format=new SimpleDateFormat(convert2,Locale.ENGLISH);
		}else{
			format=new SimpleDateFormat(convert2);
		}
		String date3=format.format(date2);
		return date3;
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
