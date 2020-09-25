package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.InterfaceException;


@Component("calFunReturnAssetID")
public class CalFunReturnAssetID extends AdditionFunc{
	
	private String funcname="返回自定义资产ID";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "返回自定义资产ID    样例:[债券,货币基金]|[108,17]|[债券] 返回 结果:108 ";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception {
		HashMap<String, String> map=new HashMap<String, String>();
		
		String assetStr = genConvertFormula(inputValue, colcfgMap);
		String[] str=assetStr.split("\\|");
		String[] assetNames=str[0].split(",");
		String[] assetIDs=str[1].split(",");
		if(assetNames.length==assetIDs.length){
			for (int i = 0; i < assetIDs.length; i++) {
				map.put(assetNames[i], assetIDs[i]);
			}
			String assetName =str[2];
			return map.get(assetName);
		}else{
			throw new InterfaceException("类型和ID传入的数量不一致");
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
