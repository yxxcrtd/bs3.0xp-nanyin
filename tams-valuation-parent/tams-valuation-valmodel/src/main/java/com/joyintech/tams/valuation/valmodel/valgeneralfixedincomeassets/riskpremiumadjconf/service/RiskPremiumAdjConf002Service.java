package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产减值管理
 * @author xutong
 * @createTime 2021-06-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RiskPremiumAdjConf002Service extends CommonExportService<RiskPremiumAdjConf002Dto> implements BaseProcessService<RiskPremiumAdjConf002Dto> {

    @Override
    public void doComplete(RiskPremiumAdjConf002Dto assetImpairmentManagement002Dto, OperationType operationType) {
        restTemplate.postForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/RiskPremiumAdjConf002/doComplete/" + operationType.name(), assetImpairmentManagement002Dto,
                AjaxResult.class);
    }

    /**
     * 主键查询
     * @param configId
     * @return
     */
    public RiskPremiumAdjConf002Dto findByPrimaryKey(String seqNo) {
        return restTemplate.getForObject(
                ServiceApplication.VAL_MODEL_SERVICE_NAME+"/RiskPremiumAdjConf001/findByPrimaryKey?seqNo=" + seqNo,
                RiskPremiumAdjConf002Dto.class);
    }




}
