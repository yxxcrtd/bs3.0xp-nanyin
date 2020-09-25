/*
 * 文件名：MarketRiskSceneManage003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.common.base.entity.SttSceneFactor;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage003Dto;

/**
 * 市场性风险情景详细
 * @author 汪海
 * @version 1.0 2019年9月20日
 * @see MarketRiskSceneManage003Service
 * @since 1.0
 */
@Service
public class MarketRiskSceneManage003Service extends CommonExportService<MarketRiskSceneManage003Dto> {
	
    
    /**
     * 取情景id的序列值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getNewSceneId(){
    	return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + 
    			"/MarketRiskSceneManage003/getNewSceneId", null, String.class);
    }
	
    public List<MarketRiskSceneManage003Dto> queryFactorInfoList(Map<String, Object> param){
    	
    	String origin = (String) param.get("origin");
    	//判断是否是为新建预览还是管理页面查看详细
    	if("manage".equals(origin)){		//从情景管理页面查看向详细
    		Map<String, Object> queryParam = new HashMap<>();
    		String sceneId = (String) param.get("sceneId");
    		queryParam.put("sceneId", sceneId);
    		MarketRiskSceneManage002Dto manage002Dto =  restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/querySceneById",
            		queryParam, MarketRiskSceneManage002Dto.class);
    		//查询出数据状态为未准备的情景因子
    		queryParam.put("mixCode", "SCENE_FACTOR_MIX");
			List<MarketRiskSceneManage003Dto> unreadyFtrValueList = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + 
	    			"/MarketRiskSceneManage003/queryUnreadyFactorList",
	    			queryParam, MarketRiskSceneManage003Dto.class);
			if(unreadyFtrValueList!=null && unreadyFtrValueList.size() > 0){
				Set<String> ftrIdSet = new HashSet<String>();		//数据为准备的因子集合
				Map<String, MarketRiskSceneManage003Dto>  unreadyFtrValueMap= new HashMap<>();
				for(MarketRiskSceneManage003Dto sceneFtrDto : unreadyFtrValueList){
					unreadyFtrValueMap.put(sceneFtrDto.getFtrIdAndType(), sceneFtrDto);
					if(sceneFtrDto.isMixFtr()){
						String[] ftrIdArr = sceneFtrDto.getFtrId().split("-");
						Collections.addAll(ftrIdSet, ftrIdArr);
					}else{
						ftrIdSet.add(sceneFtrDto.getFtrId());
					}
				}
				String ftrIdStr = ftrIdSet.stream().collect(Collectors.joining(","));
				queryParam.put("ftrIdStr", ftrIdStr);
				queryParam.put("startDate", DateUtil.format(manage002Dto.getStartDate()));
				queryParam.put("endDate", DateUtil.format(manage002Dto.getEndDate()));
				List<MarketRiskSceneManage003Dto> mrsm003DtoListAfter = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage003/executeProcedure",
	            		queryParam, MarketRiskSceneManage003Dto.class);
				
				//重新保存标签表
				if(mrsm003DtoListAfter!=null && mrsm003DtoListAfter.size()>0){
					List<SttSceneFactor> scFactorsList = new ArrayList<SttSceneFactor>();
					for(MarketRiskSceneManage003Dto mrsm003DtoItem : mrsm003DtoListAfter){
						//满足条件的生成标签数据
						if(StringUtil.isNotEmpty(mrsm003DtoItem.getChangeValue()) && !mrsm003DtoItem.getChangeValue().equals("数据未准备")){ 		//如果能计算出变化值
							MarketRiskSceneManage003Dto unreadyFtrDto = unreadyFtrValueMap.get(mrsm003DtoItem.getFtrIdAndType());
							if(unreadyFtrDto != null){
								//存入情景因子变化值
								SttSceneFactor sttFac = new SttSceneFactor();
					        	sttFac.setSceneFtrId(unreadyFtrDto.getSceneFtrId());//情景因子代码   
					        	sttFac.setFtrValue(mrsm003DtoItem.getChangeValue());
					        	scFactorsList.add(sttFac);
							}
							
						}
					}
					
					if(scFactorsList != null && scFactorsList.size() > 0){
						Map<String, Object> updateParam = new HashMap<>();
						updateParam.put("updateListJsonStr", JSON.toJSONString(scFactorsList));
						restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage003/updateSceneFactor",
								updateParam, Integer.class);
					}
					
				}
			}
			
			//查询详细数据
			queryParam.put("ftrRskType", DictCode.风险因子管理_市场风险因子分类.getValue());
			queryParam.put("ftrRskType2", DictCode.因子二级分类.getValue());
			queryParam.put("paramDateType", DictCodeListRsk.参数类型_日期.getValue());
			queryParam.put("paramNumberType", DictCodeListRsk.参数类型_数值.getValue());
			queryParam.put("startDateCode", "SCENE_FACTOR_START_DATE");
			queryParam.put("endDateCode", "SCENE_FACTOR_END_DATE");
			queryParam.put("daysCode", "SCENE_FACTOR_DAYS");
			queryParam.put("gradeCode", DictCode.压测用债券评级结果.getValue());
			//TOOD:从数据库库查询数据
			List<MarketRiskSceneManage003Dto> mrsm003DtoList = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage003/queryFactorInfoList",
            		queryParam, MarketRiskSceneManage003Dto.class);
			return mrsm003DtoList;
    	}else{
    		Map<String, Object> procedureParam = new HashMap<>();
    		//
    		String dtoStr = JSONObject.toJSONString(param.get("entity"));
    		MarketRiskSceneManage002Dto dto = JSONObject.parseObject(dtoStr, new TypeReference<MarketRiskSceneManage002Dto>(){});
    		//拼接出因子查询条件，用于查询所有匹配的因子
            String type2Str = "(";
            for(MarketRiskSceneFactorTypeDto facTypeDto : dto.getFtrTypeList()){
            	type2Str += "'"+facTypeDto.getFtrType2Code()+"',";
            }
            type2Str = type2Str.substring(0,type2Str.length() - 1) + ')';
            procedureParam.put("ftrType2Str", type2Str);
			List<MarketRiskSceneManage002Dto> factorList = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage003/queryFactorList",
					procedureParam, MarketRiskSceneManage002Dto.class);
			Set<String> ftrIdSet = new HashSet<>();
			if(factorList != null && factorList.size()>0){
				for(MarketRiskSceneManage002Dto item : factorList){
					ftrIdSet.add(item.getFtrId());
				}
			}
			String ftrIdStr = ftrIdSet.stream().collect(Collectors.joining(","));
			
			procedureParam.put("ftrIdStr", ftrIdStr);
			procedureParam.put("startDate", DateUtil.format(dto.getStartDate()));
			procedureParam.put("endDate", DateUtil.format(dto.getEndDate()));
			List<MarketRiskSceneManage003Dto> mrsm003DtoListAfter = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage003/executeProcedure",
					procedureParam, MarketRiskSceneManage003Dto.class);
    		return mrsm003DtoListAfter;
    	}
    	
    	
    	
//    	List<MarketRiskSceneManage003Dto> list = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + 
//    			"/MarketRiskSceneManage003/saveAndqueryFactorInfoList",
//                param, MarketRiskSceneManage003Dto.class);
//    	return list;
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/marketriskscenemanage/conf/marketriskscenemanage003_config.xml").dataSource(DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MarketRiskSceneManage003Dto> selectPageData(Map<String, Object> selectParam) {
    	String str = (String) selectParam.get("str");
    	List<MarketRiskSceneManage003Dto> list = JSONObject.parseArray(str, MarketRiskSceneManage003Dto.class);
    	Page<MarketRiskSceneManage003Dto> page= new Page<>();
    	page.setRoot(list);
//          return selectForPage(selectParam);
    	return page;
    }
    
}
