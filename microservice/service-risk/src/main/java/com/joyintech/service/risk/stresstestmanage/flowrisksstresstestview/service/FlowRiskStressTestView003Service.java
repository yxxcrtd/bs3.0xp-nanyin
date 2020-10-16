/*
 * 文件名：FlowRiskStressTestView003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView003Mapper;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView003Dto;
import com.joyintech.tams.common.base.entity.SttScene;


/**
 * 〈现金流详细〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年8月30日
 * @see FlowRiskStressTestView003Service
 * @since 1.0
 */
@Service("backFlowRiskStressTestView003Service")
public class FlowRiskStressTestView003Service extends BaseService<FlowRiskStressTestView003Dto> {
    
    @Autowired
    private FlowRiskStressTestView003Mapper flowRiskStressTestView003Mapper;
    
    /** 根据条件查询现金流详细 */
    public List<FlowRiskStressTestView003Dto> getCashFlowDetail(Map<String, Object> paramMap){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<FlowRiskStressTestView003Dto> dataList = flowRiskStressTestView003Mapper.getCashFlowDetail(paramMap);
        DynamicDataSourceHolder.clearDataSource();
        return dataList;
    }
    
}
