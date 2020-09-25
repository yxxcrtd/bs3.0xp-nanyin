package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.AssetTypes;


@Service("getIssueTypeID")
public class GetIssueTypeID extends AdditionFunc{
	
	private String funcname="获取证券类型代码";
	
	public int getFuncType() {
		return InterfaceConfig.advanceType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "通过名字获取证券类型代码    样例:[债券]";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) {
		String issueType = genConvertFormula(inputValue, colcfgMap);
		HashMap<String, String> issueTypeMap=AssetTypes.getPortAsset(false);
		String id="";
		for (String assettype : issueTypeMap.keySet()) {
			if(issueTypeMap.get(assettype).equals(issueType)){
				id=assettype;
				break;
			}
		}
		return id;
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
