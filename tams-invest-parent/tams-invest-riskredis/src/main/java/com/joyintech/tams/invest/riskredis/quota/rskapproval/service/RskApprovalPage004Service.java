package com.joyintech.tams.invest.riskredis.quota.rskapproval.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskapproval.dto.RskApproval004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 实占批量审批
 * 
 * @author chaowei
 * @version 1.0 2020年05月20日
 * @see RskApprovalPage004Service
 * @since 1.0
 */
@Service("RskApprovalPage004Service")
public class RskApprovalPage004Service extends RskApprovalService<RskApproval004Dto> implements BaseProcessService<RskApproval004Dto> {

    @Override
    public void doComplete(RskApproval004Dto dto, OperationType type) {
        for(Map tmp : dto.getRsklist()) {
            RskOccupyInfo info =new RskOccupyInfo();
            info.setUpdateUser(dto.getUpdateUser());
            info.setUpdateTime(dto.getUpdateTime());
            info.setEventId((String)tmp.get("eventId"));

            this.restTemplate.postForObject(
                    ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskApproval/approvalOcp", info, Integer.class);
        }
              //将审批结果 通知给交易系统
        sendApprovalResultToExchange(dto.getOrderID());
    }
}
