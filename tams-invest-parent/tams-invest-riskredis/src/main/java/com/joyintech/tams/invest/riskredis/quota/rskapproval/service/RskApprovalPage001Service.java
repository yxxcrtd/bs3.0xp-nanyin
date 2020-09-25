package com.joyintech.tams.invest.riskredis.quota.rskapproval.service;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 模糊委托审批
 * 
 * @author zhangrui1
 * @version 1.0 2019年10月14日
 * @see RskApprovalPage001Service
 * @since 1.0
 */
@Service("RskApprovalPage001Service")
public class RskApprovalPage001Service extends RskApprovalService<RskApproval001Dto> implements BaseProcessService<RskApproval001Dto> {

    @Override
    public void doComplete(RskApproval001Dto dto, OperationType type) {
        simpleRestTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/approvalPreOcp", dto,
            Integer.class);
        // 系统代码拼接画面ID
        // 通知资管审批通过
        send(dto.getEventId(), "RSK007", dto.getSysCode() + GlobalConstant.UNDER_LINE + "RskApproval001");
    }
}
