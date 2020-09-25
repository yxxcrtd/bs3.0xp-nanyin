package com.jixu.adapter.func.advance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;

@Component("calFuncNextBusinessDay")
public class CalFuncNextBusinessDay extends AdditionFunc{
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	private String funcname="第N个工作日(字符型)";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "计算某日期之后的第N个工作日.	\r\n	样例: 日期,N天,假期";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	//[交易日],天数,假期ID
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws ScriptException{
		String[] arrs=inputValue.split(",");
		String columnName=getColumName(arrs[0]);
		String tradeDateStr=colcfgMap.get(columnName).toString();
		Date tradeDate=DateUtil.convertStr2Date(tradeDateStr);
		int days=Integer.valueOf(arrs[1]);
		String holidaySD=arrs[2];
		List<Date> holidayList=holidayLSDao.loadHolidayList(holidaySD);
		Date ledgerDate=DateUtil.getNextNBusinessDays(tradeDate, days, holidayList);
		return DateUtil.convertD2YYYYMMDD(ledgerDate);
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
