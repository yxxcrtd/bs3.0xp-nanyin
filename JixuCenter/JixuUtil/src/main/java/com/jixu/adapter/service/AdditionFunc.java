package com.jixu.adapter.service;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.util.RoundUtil;
/** 
 * @ClassName 		ValidateFunc
 * @author  		Pan Wei 
 * @Date 			2016年12月3日 下午3:41:02 	
 * @Description		[]  
 */

public abstract class AdditionFunc {

	private ScriptEngineManager scriptmanager = new ScriptEngineManager();
	
	/**
	 * 功能名称
	 * @return
	 */
	public abstract String getFuncName();
	
	/**
	 * 必须填写功能类型	
	 *  1. 基础类型,用于校验字段 InterfaceConfig.baseType
	 *  2. 增值功能，用于复杂运算 InterfaceConfig.advanceType 
	 * @return
	 */
	public abstract int getFuncType();
	
	/**
	 * 校验主体,检验是否符合规范
	 * @param field
	 * @return
	 */
	public abstract boolean validateFiled(Object field);

	/**
	 * 重新计算字段值
	 * @param inputValue	传入的值
	 * @param colcfgMap		同一行，前面列的值
	 * @return
	 * @throws Exception
	 */
	public abstract Object calFunc(String inputValue, HashMap<String, Object> colcfgMap) throws Exception;
	
	/**
	 * 功能模块的使用说明
	 * @return
	 */
	public abstract String fundUsefulDesc();
	
	public String genConvertFormula(String inputValue, HashMap<String, Object> colcfgMap){
		char ach='[';
		char bch=']';
		int apoint=0;
		int bpoint=0;
		StringBuilder newFormula=new StringBuilder();
		while(true){
			apoint=inputValue.indexOf(ach);
			bpoint=inputValue.indexOf(bch);
			if(apoint>=0 && bpoint>apoint){
				newFormula.append(inputValue.subSequence(0, apoint));
				newFormula.append(colcfgMap.get(inputValue.subSequence(apoint+1, bpoint)));
			}else{
				newFormula.append(inputValue);
			}
			
			if(bpoint==inputValue.length()-1 || apoint==-1 || bpoint==-1)
				break;
			
			inputValue=inputValue.substring(bpoint+1);
		}
		
		return newFormula.toString();
	}
	
	/**
	 * 通过录入的公式计算结果
	 * @param newInputValue
	 * @return
	 */
	protected double genResultByFormula(String newInputValue, int roundNum) throws ScriptException{
		try{
			ScriptEngine engine = scriptmanager.getEngineByName("js");
			Object ob=engine.eval(newInputValue);
			return RoundUtil.roundMoney(Double.parseDouble(ob.toString()),roundNum);
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 通过录入的公式计算结果
	 * @param newInputValue
	 * @return
	 */
	protected Object genExeScrit(String newInputValue) throws ScriptException{
		ScriptEngine engine = scriptmanager.getEngineByName("js");
		return engine.eval(newInputValue);
		
	}	
	public abstract boolean filterField(Object field, String compareField) throws Exception;
	
	public abstract String groupCal(String key, List<HashMap<String, Object>> sourceData) throws Exception;;
	
	public List<HashMap<String, Object>> genGroupNewSourceData(List<HashMap<String, Object>> sourceHMList, String funcParams, List<InterfaceColConfig> groupFuncClassList) throws Exception{
		return null;
	}
	
	/**
	 * 把[abc]转为abc
	 * @param inputValue
	 * @return
	 */
	protected String getColumName(String inputValue){
		char ach='[';
		char bch=']';
		int apoint=inputValue.indexOf(ach);
		int bpoint=inputValue.indexOf(bch);
		if(apoint>=0 && bpoint>apoint){
			inputValue=inputValue.substring(apoint+1, bpoint);
		}
		return inputValue;
	}
}
