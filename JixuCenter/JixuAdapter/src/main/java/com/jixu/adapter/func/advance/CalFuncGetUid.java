package com.jixu.adapter.func.advance;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.common.type.InterfaceConfig;

@Component("calFuncGetUid")
public class CalFuncGetUid extends AdditionFunc{

	private String funcname="获取UUID";
	
	@Resource(name="interfaceAdapterDao")
    protected InterfaceAdapterDao interfaceSourceDao;
	
	@Override
	public String getFuncName() {
		// 返回适配器名称
		return funcname;
	}

	@Override
	public int getFuncType() {
		// 返回功能类型
		return InterfaceConfig.advanceType;
	}

	@Override
	public boolean validateFiled(Object field) {
		// 校验主体
		return true;
	}

	@Override
	public Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception {
		// TODO 获取UUID
		String result = funcGUid();
		return result;
	}

	@Override
	public String fundUsefulDesc() {
		// 随机获取一段具有唯一标识的字符串，作为UUID
		return "随机获取一段具有唯一标识的字符串作为UUID";
	}

	@Override
	public boolean filterField(Object field, String compareField) throws Exception {
		return false;
	}

	@Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData) throws Exception {
		return null;
	}
	
	/**
	 * 获取UUID   add by wangqidong
	 */
	
	private String funcGUid(){
		List<Object> result = interfaceSourceDao.getFGuid();
		String result1=result.toString();
		char ach='[';
		char bch=']';
		int apoint=result1.indexOf(ach);
		int bpoint=result1.indexOf(bch);
		if(apoint>=0 && bpoint>apoint){
			result1=result1.substring(apoint+1, bpoint);
		}
		return result1;
	}
}
