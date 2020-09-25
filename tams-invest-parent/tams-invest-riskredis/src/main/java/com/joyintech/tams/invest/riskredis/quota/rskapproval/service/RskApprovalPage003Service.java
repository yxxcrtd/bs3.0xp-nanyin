package com.joyintech.tams.invest.riskredis.quota.rskapproval.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 模糊委托审批
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalPage003Service
 * @since 1.0
 */
@Service("RskApprovalPage003Service")
public class RskApprovalPage003Service extends RskApprovalService<RskApproval003Dto> implements BaseProcessService<RskApproval003Dto> {

    @Override
    public void doComplete(RskApproval003Dto dto, OperationType type) {
        simpleRestTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/approvalDimEtr", dto,
            Integer.class);
        // 通知资管审批通过
        send(dto.getEventId(), "RSK008", dto.getSysCode() + GlobalConstant.UNDER_LINE + "RskApproval003");
    }
}
