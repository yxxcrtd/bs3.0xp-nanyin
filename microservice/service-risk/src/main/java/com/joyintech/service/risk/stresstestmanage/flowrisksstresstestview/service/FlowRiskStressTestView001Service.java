/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView001Dto;
import com.joyintech.tams.common.base.entity.SttScene;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView001Mapper;

/**
 * 
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service("backFlowRiskStressTestView001Service")
public class FlowRiskStressTestView001Service extends BaseService<FlowRiskStressTestView001Dto>{

    @Autowired
    private FlowRiskStressTestView001Mapper FlowRiskStressTestView001Mapper;
  
    
    public Page<FlowRiskStressTestView001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Page<FlowRiskStressTestView001Dto> PAGE= super.queryForPage(page, length, () -> FlowRiskStressTestView001Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }
    
    public List<SelectOptionDto> selectTestListMap(Map<String, Object> pageParam ){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> LIST=FlowRiskStressTestView001Mapper.queryTestList(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
    public List<FlowRiskStressTestView001Dto> initPage(Map<String, Object> pageParam ){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskStressTestView001Dto> LIST=FlowRiskStressTestView001Mapper.initPage(pageParam);
        DynamicDataSourceHolder.clearDataSource();
        return LIST;
    }
    /**  根据情景名称模糊查询情景基本信息 */
    public List<SttScene>  fuzzyQueryBySceneNameOrId(String sceneStr) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SttScene> dataList = FlowRiskStressTestView001Mapper.fuzzyQueryBySceneNameOrId(sceneStr);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
}
