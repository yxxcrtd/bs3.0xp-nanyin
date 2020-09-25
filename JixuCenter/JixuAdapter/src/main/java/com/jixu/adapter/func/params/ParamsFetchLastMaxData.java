package com.jixu.adapter.func.params;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.adapter.dao.intf.InterfaceParamsLastMaxDao;
import com.jixu.adapter.model.ParamsSet;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.InterfaceException;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.util.DateUtil;
import com.orm.obj.InterfaceParamsLastMax;


@Component
public class ParamsFetchLastMaxData extends AdditionFunc{
	
	@Autowired
	private InterfaceParamsLastMaxDao interfaceParamsLastMaxDao;
	
	private String funcname="获取上一次执行的最大值";
	
	public int getFuncType() {
		return InterfaceConfig.paramsCycleType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "样例: 表名,字段名,数据类型(D:日期  N:数字 S:字符) | ValuationReport,UpdateTime,D";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception{
		//返回上一次执行的标记位
		InterfaceColConfig config=(InterfaceColConfig)colcfgMap.get(ParamsSet.interfaceColCfg);
		InterfaceParamsLastMax iplm=interfaceParamsLastMaxDao.getLastRecord(config.getId());
		
		String[] params=inputValue.split(",");
		if(params.length!=3){
			throw new InterfaceException(funcname + " | " + fundUsefulDesc());
		}
		
		String dataType=params[2].trim();
		
		if(dataType.toLowerCase().equals("d")){
			if(iplm==null){
				return DateUtil.convertStr2Date("19000101");
			}else{
				return iplm.getLastValDate();
			}
		}else if(dataType.toLowerCase().equals("n")){
			if(iplm==null){
				return -9999999;
			}else{
				return iplm.getLastValDouble();
			}
		}else{
			if(iplm==null){
				return "";
			}else{
				return iplm.getLastValStr();
			}
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
