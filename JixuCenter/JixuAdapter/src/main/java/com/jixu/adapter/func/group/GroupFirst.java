package com.jixu.adapter.func.group;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceColConfig;


@Component("itfGroupFirst")
public class GroupFirst extends AdditionFunc{
	private String funcname="分组首行值";
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService ineterfaceBeanService;
	
	public int getFuncType() {
		return InterfaceConfig.groupAdvanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "获取第一行的值";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public boolean filterField(Object field, String compareField){
		return false;
	}
	
	 @Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData) throws Exception {
		String firstValue="";
		if(sourceData.size()>0){
			firstValue=sourceData.get(0).get(key).toString();
		}
		return String.valueOf(firstValue);
	}
	

	/**
	 * 生成分组计算后的数据集
	 * @param sourceHMList
	 * @param funcParams
	 * @param groupFuncClassList
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, Object>> genGroupNewSourceData(
			List<HashMap<String, Object>> sourceHMList, String funcParams,
			List<InterfaceColConfig> groupFuncClassList) throws Exception{
		return null;
	}
	
	@Override
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
