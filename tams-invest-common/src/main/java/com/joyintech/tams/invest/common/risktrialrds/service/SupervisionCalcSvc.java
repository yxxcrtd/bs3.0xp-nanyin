package com.joyintech.tams.invest.common.risktrialrds.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.risktrialrds.dao.SupervisionCalcDao;
import com.joyintech.tams.invest.common.risktrialrds.dto.IsupLogDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDetailDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.SupervisionParameterDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.SystemParam;
import com.joyintech.tams.invest.common.risktrialrds.util.Const;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import com.joyintech.tools.collectors.JoyinCollectors;


@Service("supervisionCalcSvcRds")
public class SupervisionCalcSvc {

	@Autowired
	private SupervisionCalcDao supervisionCalcDao;
	
	//----modify by 裴家俊 2019-03-22---------start----------
	public Object getIndicatorParamValue(String sql, SystemParam sysParam,String indType,String detailJson,String timePoint) throws Exception{
		String portfolioCodeStr=sysParam.getPortfolioCode();
		portfolioCodeStr=handlePortfolioCode(sql,portfolioCodeStr);
		int portLength=portfolioCodeStr.split(",").length;
		//对于组合数超过1000条的处理
		if(portLength>1000){
			int count = 1000;  
	        int len = portLength;  
	        int size = len % count;  
	        if (size == 0) {  
	            size = len / count;  
	        } else {  
	            size = (len / count) + 1;  
	        }  
	        List<Map<String, Object>> resultSet =new ArrayList<Map<String, Object>>();
	        Object retObj=null;
	        BigDecimal initVal=BigDecimal.ZERO;
		    for (int i = 0; i < size; i++) {  
		    	int fromIndex = i * count;  
		        int toIndex = Math.min(fromIndex + count, len);  
		        String portfStr=StringUtils.join(Arrays.copyOfRange(portfolioCodeStr.split(","),fromIndex, toIndex),",");
		        SystemParam sysParamMx = new SystemParam();
		        BeanUtil.copyPropertiesIgnoreNull(sysParamMx, sysParam);
		        if(portfStr.indexOf("(")!=-1&&portfStr.indexOf(")")==-1){
		        	sysParamMx.setPortfolioCode(portfStr+")");
		        }
		        else if(portfStr.indexOf("(")==-1&&portfStr.indexOf(")")!=-1){
		        	sysParamMx.setPortfolioCode("("+portfStr);
		        }
		        else{
		        	sysParamMx.setPortfolioCode("("+portfStr+")");
		        }
				Object eathRst=getEachIndicatorParamValue(sql,sysParamMx,indType,detailJson,timePoint);
				if(null!=eathRst){
					if(indType.indexOf(RiskUtil.LIMIT)!=-1&&!"03".equals(timePoint)){
						initVal=ArithmeticUtil.doAdd(new  BigDecimal(eathRst.toString()),initVal);
						retObj=initVal;
					}
					if(indType.indexOf(RiskUtil.LIMIT)!=-1&&"03".equals(timePoint)){
						if(eathRst instanceof List){
							 resultSet.addAll((List)eathRst);
						}else{
							initVal= ArithmeticUtil.doAdd(new  BigDecimal(eathRst.toString()),initVal);
							retObj=initVal;
						}
					}
				}
			}
		    if(indType.indexOf(RiskUtil.LIMIT)!=-1&&"03".equals(timePoint)&&resultSet.size()>0){
		    	resultSet=getAddRepeatAstCodeList(resultSet);//相同重复的合并
		    	return resultSet;
		    }
		    return retObj;
		}
		else{
			return getEachIndicatorParamValue(sql,sysParam,indType,detailJson,timePoint);
		}
		
	}
	//----add by 裴家俊 2019/03/27----start--------
	private String handlePortfolioCode(String sql,String portfolioCodeStr){
		if(!StringUtil.isNotEmpty(sql)||sql.indexOf("portfolioCode")>-1){
			return portfolioCodeStr;
		}
		return StringUtil.EMPTY;
	}
	//----add by 裴家俊 2019/03/27----end--------
	private Object getEachIndicatorParamValue(String sql, SystemParam sysParam,String indType,String detailJson,String timePoint){
		String sqlNew = sysParam.replaceForString(sql);
		List<Map<String, Object>> resultSet = supervisionCalcDao.selectOne(sqlNew);
		if(resultSet.size() > 0) {
			Map<String, Object> row0 = resultSet.get(0);
			if(row0==null){
				return null;
			}
			if(row0.keySet().size()>1&&"03".equals(timePoint)){
				if(indType.indexOf(RiskUtil.LIMIT)!=-1){
					return resultSet;
				}
			}
			if(resultSet.size()>=1){//针对多条记录下的
				if(indType.indexOf(RiskUtil.FORBID)!=-1){
					String retDou="";
					for(Map<String, Object> mapRes:resultSet){
						//---update by jjpei 20190812---禁投指标查询出两列，代码及名称
						if(mapRes.keySet().size()>1){
							Object comObj=StringUtil.isNotEmpty(mapRes.get(Const.keyValue))?mapRes.get(Const.keyValue).toString():null;
							Object comObjName=StringUtil.isNotEmpty(mapRes.get(Const.keyAstCode))?mapRes.get(Const.keyAstCode).toString():null;
							if (null!=comObj&&null!=comObjName) {
								retDou += comObj.toString() + "||"+comObjName.toString()+",";
							}
						}
						else {
							Object comObj = getFirstOrNull(mapRes);
							if (comObj != null) {
								retDou += comObj.toString() + ",";
							}
						}
						//---update by jjpei 20190812---禁投指标查询出两列，代码及名称
					}
					if(!retDou.equals("")){
						return retDou.substring(0, retDou.length()-1);
					}
				}
			}
			Object retObj=getFirstOrNull(row0);
			//return row0.get("V");
			return retObj;
		} else {
			return null;
			//return 0;//----update---裴家俊--------2018/01/29------没有记录返回0---------
		}
	}
	
	/*
	 * 合并相同key的集合
	 */
	private List<Map<String, Object>> getAddRepeatAstCodeList(List<Map<String, Object>> oldList){ 
		HashMap<String,Map<String, Object>> tempMap = new HashMap<String,Map<String, Object>>(); 
		//去掉重复的key 
		for(Map<String, Object> oldDto : oldList){ 
			if(!StringUtil.isNotEmpty(oldDto.get(Const.keyAstCode))){
				continue;
			}
			String temp = oldDto.get(Const.keyAstCode).toString(); 
			if(tempMap.containsKey(temp)){ 
				Map<String, Object> newDto = new HashMap<String, Object>(); 
				//合并相同key的value 
				newDto.put(temp, ArithmeticUtil.doAdd(StringUtil.isNotEmpty(tempMap.get(temp).get(Const.keyValue))?new BigDecimal(tempMap.get(temp).get(Const.keyValue).toString()):BigDecimal.ZERO , StringUtil.isNotEmpty(oldDto.get(Const.keyValue))?new BigDecimal(oldDto.get(Const.keyValue).toString()):BigDecimal.ZERO)); 
				//HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖 
				tempMap.put(temp,newDto ); 
			}
			else{ 
				tempMap.put(temp,oldDto );
			} 
		} 
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		for(String temp:tempMap.keySet()){ 
			newList.add(tempMap.get(temp)); 
		} 
		return newList; 
	}
	//----modify by 裴家俊 2019-03-22---------end----------	
	
	/**
	 * 获取list中最大或最小的值
	 * @throws Exception 
	 */
   public Object getMaxMinValueByListRisk(String indType,String detailJson,List<Double> sortList) throws Exception{
	   ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> violationMap = mapper.readValue(detailJson, Map.class);
		Map<?, ?> vThreshold1Map = (Map<?, ?>)violationMap.get("threshold_1");
		String vThreshold1Op = (String)vThreshold1Map.get("op");
		boolean isAbs = vThreshold1Map.get("is_model")!=null?"01".equals((String)vThreshold1Map.get("is_model"))?true:false:false;
		if(indType.indexOf(RiskUtil.LIMIT)!=-1){
			if(sortList.size()>0){
				if(vThreshold1Op.equals(">")||vThreshold1Op.equals(">=")){
				    Double retDou = 0.0d;
				    if (isAbs) {
				        retDou = Collections.max(sortList.stream().map(item->Math.abs(item)).collect(Collectors.toList())); 
				        if (!sortList.contains(retDou)) {
				            BigDecimal retBd = new BigDecimal(Double.toString(retDou));  
				            retDou = ArithmeticUtil.multiply(retBd,new BigDecimal(-1)).doubleValue();
                        }
                    }else {
                        retDou = Collections.max(sortList);
                    }  
					return retDou;
				}
				else{
				    Double retDou = 0.0d; 
				    if (isAbs) {
                        retDou = Collections.min(sortList.stream().map(item->Math.abs(item)).collect(Collectors.toList()));
                        if (!sortList.contains(retDou)) {
                            BigDecimal retBd = new BigDecimal(Double.toString(retDou));  
                            retDou = ArithmeticUtil.multiply(retBd,new BigDecimal(-1)).doubleValue();
                        }
                    }else {
					    retDou = Collections.min(sortList);
                    }
					return retDou;
				}
			}
		}
		return null;
   }
   public void writeLog(IsupLogDto dto){
	   supervisionCalcDao.writeLog(dto);
   }
	
	public SupervisionParameterDto getIndicatorParam(String code, String granularity) {
		return supervisionCalcDao.getIndicatorParam(code, granularity);
	}
	
	public String getIndicatorParamType(String code) {
		return supervisionCalcDao.getIndicatorParamType(code);
	}
	
	public void deletePointSupervisionValue(RiskTrialRetDto value) {
		supervisionCalcDao.deletePointSupervisionValue(value);
	}
	public void deletePointValueWithoutInd(RiskTrialRetDto value) {
		supervisionCalcDao.deletePointValueWithoutInd(value);
	}
	public void addPointSupervisionValue(RiskTrialRetDto value) {
		supervisionCalcDao.addPointSupervisionValue(value);
	}
	
	public void deleteMxPointSupervisionValue(RiskTrialRetDetailDto value) {
		supervisionCalcDao.deleteMxPointSupervisionValue(value);
	}
	
	public void addMxPointSupervisionValue(RiskTrialRetDetailDto value) {
		supervisionCalcDao.addMxPointSupervisionValue(value);
	}
	
	public static <K,V> V getFirstOrNull(Map<K, V> map){
		V obj=null;
		for(Entry<K,V> entry:map.entrySet()){
			obj=entry.getValue();
			if(obj==null){
				break;
			}
		}
		return obj;
	}
}
