package com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditmanagement.ceassetgroupmanagement.dto.CeAssetGroupManagement001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

@Service
public class CeAssetGroupManagement005Service extends CommonExportService<CeAssetGroupManagement001Dto>
        implements BaseProcessService<CeAssetGroupManagement001Dto> {
    @Override
    public void doComplete(CeAssetGroupManagement001Dto ceAssetGroupManagement001Dto, OperationType operationType) {
        restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/CeAssetGroupManagement/ceAssetDetaDeleteInsert" , ceAssetGroupManagement001Dto,
                Integer.class);
    }
}
