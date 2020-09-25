/*
 * 文件名：FlowRiskSceneManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttSceneFtrtips;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssets001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManageListDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 情景管理流程
 * @author Jiaoxujin
 * @version 1.0 2019年8月24日
 * @see FlowRiskSceneManage002Service
 * @since 1.0
 */
@Service
public class FlowRiskSceneManage002Service extends CommonExportService<FlowRiskSceneManage002Dto> 
                    implements BaseProcessService<FlowRiskSceneManage002Dto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FlowRiskSceneManage002Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/" + action, entity,
            Integer.class);
    }

    /**
     * 获取产品指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryIndexTypePro(){
        Map<String,Object> param = new HashMap<>();
        param.put("paramCode", DictCode.情景风险分类.getValue());
        param.put("paramValue", DictCodeListRsk.情景风险分类_流动性风险.getValue());
        param.put("indType", DictCodeListRsk.压力测试指标类型_产品指标.getValue());
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryIndexByType",
            param, FlowRiskSceneManage002Dto.class);
    }
    
    /**
     * 获取承压指标值
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> queryIndexTypeStr(){
        Map<String,Object> param = new HashMap<>();
        param.put("paramCode", DictCode.情景风险分类.getValue());
        param.put("paramValue", DictCodeListRsk.情景风险分类_流动性风险.getValue());
        param.put("indType", DictCodeListRsk.压力测试指标类型_承压指标.getValue());
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryIndexByType",
            param, FlowRiskSceneManage002Dto.class);
    }
    /**
     * 判断情景名称的唯一性
     * <一句话功能简述>
     * <功能详细描述>
     * @param sceneName
     * @return
     * @see
     * @since 1.0
     */
    public int queryScentNameCount(String sceneName){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneName", sceneName);
        List<FlowRiskSceneManage002Dto> list = restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryScentNameCount",
            param, FlowRiskSceneManage002Dto.class);
        return list.size();
    }
    /**
     * 根据情景代码得到情景信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param sceneName
     * @return
     * @see
     * @since 1.0
     */
    public FlowRiskSceneManage002Dto querySceneById(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/querySceneById",
            param, FlowRiskSceneManage002Dto.class);
    }
    /**
     * 查找情景下的二级和适用产品
     * @param sceneId
     * @return
     */
    public FlowRiskSceneManage002Dto queryScentType2AndMode(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryScentType2AndMode",
            param, FlowRiskSceneManage002Dto.class);
    }
    
    /**
     * 根据情景代码查找关联因子
     * <一句话功能简述>
     * <功能详细描述>
     * @param sceneId
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManageListDto> queryFtrBySceId(String sceneId, String tipsValue){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        param.put("tipsValue", tipsValue);
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryFtrBySceId",
            param, FlowRiskSceneManageListDto.class);
    }
    
    /**
     * 查询流动性风险的假设情景下的因子
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManageListDto> queryFlowSttFactor(){
        Map<String,Object> param = new HashMap<>();
        param.put("rskType", DictCodeListRsk.风险因子管理_风险分类_流动性风险.getValue());
        param.put("rskType2", DictCodeListRsk.风险因子管理_流动性风险因子分类_产品规模变化.getValue());
        param.put("sceneId", "SceneModel001");
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryFlowSttFactor",
            param, FlowRiskSceneManageListDto.class);
    }
    /**
     * 查询资产变现能力的因子
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManageListDto> queryFlowSttFactor2(){
        Map<String,Object> param = new HashMap<>();
        param.put("rskType", DictCodeListRsk.风险因子管理_风险分类_流动性风险.getValue());
        param.put("rskType2", DictCodeListRsk.风险因子管理_流动性风险因子分类_资产变现能力.getValue());
        param.put("sceneId", "SceneModel001");
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryFlowSttFactor2",
            param, FlowRiskSceneManageListDto.class);
    }
    /**
     * 流动性情景模板
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public FlowRiskSceneManage002Dto selectSceneTmpl(){
        Map<String,Object> param = new HashMap<>();
        param.put("riskType", DictCode.情景风险分类.getValue());
        param.put("sceneType", DictCode.情景类型.getValue());
        param.put("riskTypeCode", DictCodeListRsk.情景风险分类_流动性风险.getValue());
        param.put("sceneTypeCode", DictCodeListRsk.情景类型_假设情景.getValue());
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/selectSceneTmpl",
            param, FlowRiskSceneManage002Dto.class);
    }
    /**
     * 根据模型代码和指标类型查找模板关联指标（产品指标/承压指标）
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<FlowRiskSceneManage002Dto> selectTmplIndByType(String modelId, String indType){
        Map<String,Object> param = new HashMap<>();
        param.put("modelId", modelId);
        param.put("indType", indType);
        param.put("sttBusiType", DictCodeListRsk.压力测试业务类型_压测模板.getValue());
        return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/selectTmplIndByType",
            param, FlowRiskSceneManage002Dto.class);
    }
    /**
     * 查询申赎净流出基础选择值
     * <一句话功能简述>
     * <功能详细描述>
     * @param ftrId
     * @return
     * @see
     * @since 1.0
     */
    public SttSceneFtrtips queryNetOutflow(String sceneId){
        Map<String,Object> param = new HashMap<>();
        param.put("sceneId", sceneId);
        param.put("dictCode", DictCode.申赎净流出基础选择.getValue());
        return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage002/queryNetOutflow",
            param, SttSceneFtrtips.class);
        
    }
    

}
