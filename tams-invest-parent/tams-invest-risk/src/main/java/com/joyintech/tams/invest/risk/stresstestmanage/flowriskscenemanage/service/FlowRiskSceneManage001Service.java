/*
 * 文件名：FlowRiskSceneManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.tams.common.base.pojo.SceneBatchOperationPojo;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 情景管理-查询管理页面对应服务层
 * @author Jiaoxujin
 * @version 1.0 2019年8月24日
 * @see FlowRiskSceneManage001Service
 * @since 1.0
 */
@Service
public class FlowRiskSceneManage001Service extends CommonExportService<FlowRiskSceneManage001Dto> 
                    implements BaseProcessService<FlowRiskSceneManage001Dto> {
    /**
     * 情景分页查询
     * 
     * @param parameter
     * @return
     * @see
     * @since 1.0
     */
    public Page<FlowRiskSceneManage001Dto> selectForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage001/findPage", param,
            FlowRiskSceneManage001Dto.class);
    }

    /**
     * 批量or单个情景启用停用 
     */
    public Integer updateScenesEffStatus(FlowRiskSceneManage001Dto dto) {
        SceneBatchOperationPojo inputBean = new SceneBatchOperationPojo();
        inputBean.setEffStatus(dto.getEffStatus());
       // List<String> sceneIdList = dto.getSelectedItem().stream().map(p -> p.getSceneId()).collect(Collectors.toList());
        inputBean.setSceneIds(dto.getSceneId());

        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage001/updateScenesEffStatus",
            inputBean, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FlowRiskSceneManage001Dto entity, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
            restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage001/deleteScene", entity,
                Integer.class);
        }
    }

    /**
     * 情景搜索页面中的情景编码/名称输入框的自动匹配搜索
     */
    public List<SelectOptionDto> fuzzySelectScene(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage/fuzzySelectScene",
            param, SelectOptionDto.class);
    }

    /** 
     * 查询所有情景，无分页 
     */
    public List<SttScene> getAllSceneList() {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage/getAllSceneList",
            null, SttScene.class);
    }

    /** 
     * 情景自动匹配完成
     */
    public List<SuggestDto> getSceneSuggestList(Map<String, Object> paramMap) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage/getSceneSuggestList",
            paramMap, SuggestDto.class);
    }

    /** 
     * 根据条件查询情景列表，无分页 
     */
    public List<SttScene> querycheck(@RequestBody Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/FlowRiskSceneManage/querycheck", param,
            SttScene.class);
    }
 
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/flowriskscenemanage/conf/flowriskscenemanage001_config.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FlowRiskSceneManage001Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
          return selectForPage(selectParam);
    }
}
