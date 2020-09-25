package com.jixu.adapter.func.group;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.util.RoundUtil;


@Component("itfGroupAverage")
public class GroupAverage extends AdditionFunc{
	private String funcname="分组平均值";
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService ineterfaceBeanService;
	
	public int getFuncType() {
		return InterfaceConfig.groupAdvanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "求平均值";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public boolean filterField(Object field, String compareField){
		return false;
	}
	
	 @Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData) throws Exception {
		double totalVal=0;
		for(HashMap<String, Object> dataHM:sourceData){
			double value=Double.parseDouble(dataHM.get(key).toString());
			totalVal=totalVal+value;
		}
		double average=RoundUtil.roundMoney2Dec(totalVal/sourceData.size());
		return String.valueOf(average);
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
