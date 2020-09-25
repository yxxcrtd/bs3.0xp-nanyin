/*
 * 文件名：MarketRiskSceneManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;


/**
 * 市场性风险新增情景
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage002Service
 * @since 1.0
 */
@Service
public class MarketRiskSceneManage002Service extends CommonExportService<MarketRiskSceneManage002Dto> 
                      implements BaseProcessService<MarketRiskSceneManage002Dto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MarketRiskSceneManage002Dto entity, OperationType type) {
    	Iterator<MarketRiskSceneFactorTypeDto> iterator = entity.getFtrTypeList().iterator();
    	while(iterator.hasNext()){
    		MarketRiskSceneFactorTypeDto item = iterator.next();
    		if(!item.isFtrChecked()){
    			iterator.remove();
    		}
    	}
    	String action = StringUtil.EMPTY;
        if(OperationType.NEW.equals(type)){
            action = "insert";
        }else{
            action = "update";
        }

        if(MultilevelDataAuthorityUtil.isEnabled()){

            String institutionCode = MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser());
            entity.setInstitutionCode(institutionCode);
        }
        
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/"+action,
            entity, Integer.class);
    }
    
    /**
     * 根据情景代码得到情景信息
     * <功能详细描述>
     * @param sceneName
     * @return
     * @see
     * @since 1.0
     */
    public MarketRiskSceneManage002Dto querySceneById(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/querySceneById",
            param, MarketRiskSceneManage002Dto.class);
    }
    
    /**
     * 判断情景名称的唯一性
     * <功能详细描述>
     * @param sceneName
     * @return
     * @see
     * @since 1.0
     */
    public int queryScentNameCount(String sceneName, String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneName", sceneName);
        param.put("sceneId", sceneId);
        List<MarketRiskSceneManage002Dto> list = 
            restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/queryScentNameCount",
            param, MarketRiskSceneManage002Dto.class);
        return list.size();
    }
    
    /**
     * 市场性情景模板
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public MarketRiskSceneManage002Dto selectSceneTmpl(){
        Map<String,Object> param = new HashMap<>();
        param.put("isTmpl", DictCodeList.是否_是.getValue());
        param.put("riskType", DictCode.情景风险分类.getValue());
        param.put("sceneType", DictCode.情景类型.getValue());
        param.put("riskTypeCode", DictCodeListRsk.情景风险分类_市场性风险.getValue());
        param.put("sceneTypeCode", DictCodeListRsk.情景类型_历史情景.getValue());
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/selectSceneTmpl",
            param, MarketRiskSceneManage002Dto.class);
    }

    /**
     * 根据模型代码和指标类型查找模板关联指标（产品指标/承压指标）
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MarketRiskSceneManage002Dto> selectTmplIndByType(String modelId, String indType){
        Map<String,Object> param = new HashMap<>();
        param.put("modelId", modelId);
        param.put("indType", indType);
        List<MarketRiskSceneManage002Dto> list = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/selectTmplIndByType",
            param, MarketRiskSceneManage002Dto.class);
        return list;
    }
    
    /**
     * 获取产品指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MarketRiskSceneManage002Dto> queryIndexTypePro(){
        Map<String,Object> param = new HashMap<>();
        param.put("paramCode", DictCode.情景风险分类.getValue());
        param.put("paramValue", DictCodeListRsk.情景风险分类_市场性风险.getValue());
        param.put("indType", DictCodeListRsk.压力测试指标类型_产品指标.getValue());
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/queryIndexByType",
            param, MarketRiskSceneManage002Dto.class);
    }
    
    /**
     * 获取承压指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<MarketRiskSceneManage002Dto> queryIndexTypeStr(){
        Map<String,Object> param = new HashMap<>();
        param.put("paramCode", DictCode.情景风险分类.getValue());
        param.put("paramValue", DictCodeListRsk.情景风险分类_市场性风险.getValue());
        param.put("indType", DictCodeListRsk.压力测试指标类型_承压指标.getValue());
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/queryIndexByType",
            param, MarketRiskSceneManage002Dto.class);
    }
    
    
    /**
     * 根据情景情景id查找关联因子列表
     * <功能详细描述>
     * @param sceneId
     * @return
     * @see
     * @since 1.0
     */
    public List<MarketRiskSceneFactorTypeDto> queryFactorTypeList(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        param.put("ftrType", DictCode.风险因子管理_市场风险因子分类.getValue());
        param.put("ftrType2", DictCode.因子二级分类.getValue());
        List<MarketRiskSceneFactorTypeDto> list = restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage002/queryFactorTypeList",param, 
            MarketRiskSceneFactorTypeDto.class);
        return list;
    }
    
}
