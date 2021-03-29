/*
 * 文件名：RskAuthorityManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see FlowRiskStressTestView002Service
 * @since 1.0
 */
@Service
public class FlowRiskStressTestView002Service  extends CommonExportService<FlowRiskStressTestView002Dto>
     {
   
   
    public Page<FlowRiskStressTestView002Dto> selectAll( @RequestBody Map<String, Object> param){
        

        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +
            "/FlowRiskStressTestView002/findPage",param,
            FlowRiskStressTestView002Dto.class);
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
       
        exportParam.config("/com/joyintech/tams/invest/risk/stresstestmanage/flowrisksstresstestview/conf/FlowRiskStressTestView002_config.xml");
    }


    @Override
    public Page<FlowRiskStressTestView002Dto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
    	return  selectAll(selectParam);
    }  
   
    
    /**
     * 根据测试代码得到测试明细信息
     * @param testId
     * @return
     */
    public FlowRiskStressTestView002Dto  queryDetByTestId(String testId){
    	Map<String, Object> param = new HashMap<>();
    	param.put("testId", testId);
    	return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME +
                "/FlowRiskStressTestView002/queryDetByTestId",param,
                FlowRiskStressTestView002Dto.class);
    }
}
