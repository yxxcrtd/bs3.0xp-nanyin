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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.pojo.SceneBatchOperationPojo;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;


/**
 * 市场性风险场景管理
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage001Service
 * @since 1.0
 */
@Service
public class MarketRiskSceneManage001Service extends CommonExportService<MarketRiskSceneManage001Dto> 
                      implements BaseProcessService<MarketRiskSceneManage001Dto> {

    /**
     * 情景管理分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<MarketRiskSceneManage001Dto> selectForPage(Map<String, Object> param) {
        Page<MarketRiskSceneManage001Dto> page =  restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage001/findPage", param,
            MarketRiskSceneManage001Dto.class);
        return page;
    }
    
    public List<SelectOptionDto> fuzzySelectScene(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage001/fuzzySelectScene", 
            param, SelectOptionDto.class);
    }
    
    /**
     * 批量or多个情景启用停用
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Integer updateScenesEffStatus(MarketRiskSceneManage001Dto dto) {
        SceneBatchOperationPojo inputBean = new SceneBatchOperationPojo();
        inputBean.setEffStatus(dto.getEffStatus());
//        List<String> sceneIdList = dto.getSelectedItem().stream().map(p -> p.getSceneId()).collect(Collectors.toList());
        inputBean.setSceneIds(dto.getSceneId());
        
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage001/updateScenesEffStatus",
            inputBean, Integer.class);
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/marketriskscenemanage/conf/marketriskscenemanage001_config.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MarketRiskSceneManage001Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
          return selectForPage(selectParam);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MarketRiskSceneManage001Dto entity, OperationType type) {
        if(OperationType.DELETE.equals(type)){
            restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/MarketRiskSceneManage001/deleteScene",
                entity, Integer.class);
        }else if(OperationType.MODIFY.equals(type)){
        	updateScenesEffStatus(entity);
        }
        
    }

}
