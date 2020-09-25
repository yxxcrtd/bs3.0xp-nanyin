package com.jixu.adapter.func.advance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;





import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;


@Component("calFuncString2DateConvert2")
public class String2DateConvert2 extends AdditionFunc{
	private String funcname="字符转日期(包含null)";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "转换日期	样例:[SourceDate],yyyy/mm/dd";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String[] results=newValue.split(",");
		String date1=results[0];
		String convert1=results[1];
		SimpleDateFormat format=null;
		if(date1!=null&&!date1.equals("")){
			if(convert1.contains("MMM")){
				format=new SimpleDateFormat(convert1,Locale.ENGLISH);
			}else{
				format=new SimpleDateFormat(convert1);
			}
			Date date2=format.parse(date1);
			return date2;
		}else{
			return null;
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
