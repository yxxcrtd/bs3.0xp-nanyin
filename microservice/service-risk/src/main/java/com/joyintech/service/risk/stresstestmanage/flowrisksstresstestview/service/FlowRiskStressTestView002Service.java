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
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView002Dto;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView002Mapper;

/**
 * 
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @since 1.0
 */
@Service("backFlowRiskStressTestView002Service")
public class FlowRiskStressTestView002Service extends BaseService<FlowRiskStressTestView002Dto>{

    @Autowired
    private FlowRiskStressTestView002Mapper FlowRiskStressTestView002Mapper;
  
    
    public Page<FlowRiskStressTestView002Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Page<FlowRiskStressTestView002Dto> PAGE= super.queryForPage(page, length, () -> FlowRiskStressTestView002Mapper.queryForPage(pageParam));
        DynamicDataSourceHolder.clearDataSource();
        return PAGE;
    }
    
    /**
     * 根据测试代码得到测试明细信息
     * @param pageParam
     * @return
     */
    public FlowRiskStressTestView002Dto queryDetByTestId(Map<String, Object> pageParam){
    	DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
    	FlowRiskStressTestView002Dto dto= FlowRiskStressTestView002Mapper.queryDetByTestId(pageParam);
    	DynamicDataSourceHolder.clearDataSource();
    	return dto;
    }
 
}
