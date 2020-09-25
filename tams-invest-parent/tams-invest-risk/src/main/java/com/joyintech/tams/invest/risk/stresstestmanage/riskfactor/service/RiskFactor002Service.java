/*
 * 文件名：RiskFactor002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor002Service
 * @since 1.0
 */
@Service
public class RiskFactor002Service extends CommonExportService<RiskFactor001Dto> 
        implements BaseProcessService<RiskFactor001Dto>{

    /**
     * 停用启用审批完成后调用方法用于更改状态
     */
    @Override
    public void doComplete(RiskFactor001Dto entity, OperationType type) {
        String action ="update";
      
       entity.setEffstatus1(entity.getEffstatus1().equals("01")?"99":"01");
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/RiskFactor001/" + action, entity,
            Integer.class);
        
    }

  

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public RiskFactor001Dto checkIsExistDetail(String workflowId) {
          ProcessMark processMark = getBusinessInfo(workflowId);
        return getBussinessEntity(processMark);
    }

}
