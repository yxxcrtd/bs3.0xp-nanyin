package com.jixu.adapter.func.advance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;

@Component("CalFuncGenDataKeySeq")
public class GenDataKeySeq extends AdditionFunc{
	
	@Autowired
	private InterfaceAdapterDao interfaceAdapterDao;
	
	private String funcname="生成18位主键";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "主键起始字符,序列名称 (TD2019010100006001)      样例:TB,TRADE_ID_SEQUENCE";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		String newValue=genConvertFormula(inputValue, colcfgMap);
		String[] results=newValue.split(",");
		String key=results[0];
		String seq=results[1];
		// 日期
        String date="";
		
		if(date.equals("")) {
			date=DateUtil.convertD2YYYYMMDD(new Date());
		}
		
		long curID=interfaceAdapterDao.genDataKeySeq(seq);
		String sequenceResult="";
		if(!key.equals("")) {
	        String formatter = "%0" + 8 + "d";
	        sequenceResult = String.format(formatter, curID);
	        // 前缀
	        sequenceResult = key + date + sequenceResult;
		}else {
			sequenceResult = String.valueOf(curID);
		}
		return sequenceResult;
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
