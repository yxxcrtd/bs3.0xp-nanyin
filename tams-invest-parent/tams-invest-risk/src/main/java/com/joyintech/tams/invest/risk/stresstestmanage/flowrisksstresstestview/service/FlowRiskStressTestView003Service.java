/*
 * 文件名：FlowRiskStressTestView003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView003Dto;


/**
 * 〈查询现金流详细〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年8月30日
 * @see FlowRiskStressTestView003Service
 * @since 1.0
 */
@Service
public class FlowRiskStressTestView003Service extends CommonExportService<FlowRiskStressTestView003Dto> {
    
    public List<FlowRiskStressTestView003Dto> getCashFlowDetail(@RequestBody Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.RISK_SERVICE_NAME +
            "/FlowRiskStressTestView003/getCashFlowDetail",param,
            FlowRiskStressTestView003Dto.class);
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/flowrisksstresstestview/conf/FlowRiskStressTestView003_config.xml");
    }
    
    @Override
    public Page<FlowRiskStressTestView003Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
    	Page<FlowRiskStressTestView003Dto> page=new Page<FlowRiskStressTestView003Dto>();
    	page.setRoot(getCashFlowDetail(selectParam));
    	return page;
    }  
}
