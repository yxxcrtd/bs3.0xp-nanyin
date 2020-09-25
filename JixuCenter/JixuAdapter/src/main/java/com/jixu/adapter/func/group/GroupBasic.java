package com.jixu.adapter.func.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.pojo.InterfaceColConfig;


@Component("itfGroupBasic")
public class GroupBasic extends AdditionFunc{
	private String funcname="分组";
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService ineterfaceBeanService;
	
	public int getFuncType() {
		return InterfaceConfig.groupType;
	}
	
	public boolean validateFiled(Object field) {
		return true;
	}
	
	public String fundUsefulDesc() {
		return "以某N个字段为基础分组。举例:A,B,C列以E和F为基准分组: A,B,C | E,F";
	}
	
	public String getFuncName() {
		return funcname;
	}
	
	public boolean filterField(Object field, String compareField){
		return false;
	}
	
	@Override
	public String groupCal(String key, List<HashMap<String, Object>> sourceData) {
		// TODO Auto-generated method stub
		return null;
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
		HashMap<String, List<HashMap<String, Object>>> groupSource=new HashMap<String, List<HashMap<String,Object>>>();
		funcParams=funcParams.split("\\|")[1];
		String[] groupby=funcParams.split(",");

		//这个循环是以funcParams参数的|之后部分为key,生成一个hash表数据集
		for(HashMap<String, Object> sourceHM:sourceHMList){
			String key="";
			//生成以什么值group by。既生成key
			for(String keytmp:groupby){
				//为了防止值为空,反过来查询后spilit的length出错
				keytmp=getColumName(keytmp);
				if(sourceHM.containsKey(keytmp)){
					key=key+",.,"+sourceHM.get(keytmp) + " ";
				}else{
					return null;
				}
			}
			key=key.substring(3, key.length());
			
			if(groupSource.containsKey(key)){
				groupSource.get(key).add(sourceHM);
			}else{
				List<HashMap<String, Object>> valuesList=new ArrayList<HashMap<String, Object>>();
				valuesList.add(sourceHM);
				groupSource.put(key, valuesList);
			}
		}
		
		List<HashMap<String, Object>> resultList=new ArrayList<HashMap<String,Object>>();
		
		AdditionFunc func=null;
		
		Iterator<Entry<String, List<HashMap<String, Object>>>> entryList=groupSource.entrySet().iterator();
		while(entryList.hasNext()){
			HashMap<String, Object> sourceLineHM=new HashMap<String, Object>();
			Entry<String, List<HashMap<String, Object>>> entry=entryList.next();
			String key=entry.getKey();
			String[] keyValues=key.split(",.,");
			//把group by的key放入最终数据集
			for(int i=0;i<groupby.length;i++){
				sourceLineHM.put(getColumName(groupby[i]), keyValues[i].trim());
			}
			
			/**
			 * 根据配置来重新计算每列的值
			 */
			List<HashMap<String, Object>> values=entry.getValue();
			for(InterfaceColConfig colcfg:groupFuncClassList){
				func=ineterfaceBeanService.getAdditionFuncBean(colcfg.getFuncClass());
				String newValue=func.groupCal(getColumName(colcfg.getFuncParams()), values);
				sourceLineHM.put(colcfg.getColname(), newValue);
			}
			
			resultList.add(sourceLineHM);
		}
		
		return resultList;
	}
	
	@Override
	public String calFunc(String inputValue, HashMap<String, Object> colcfgMap)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
