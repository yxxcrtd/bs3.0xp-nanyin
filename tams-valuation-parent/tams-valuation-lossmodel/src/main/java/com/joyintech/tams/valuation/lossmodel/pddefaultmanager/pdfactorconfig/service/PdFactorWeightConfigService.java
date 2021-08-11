package com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.service;

import java.util.Map;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto.PdFactorWeightConfigDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
@Transactional
public class PdFactorWeightConfigService extends CommonExportService<PdFactorWeightConfigDto> implements BaseProcessService<PdFactorWeightConfigDto>{
	
	
	
	@Override
	public void doComplete(PdFactorWeightConfigDto entity, OperationType operationType) {
		String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(operationType)) {
            action = "deleteWeight";
        } else if (OperationType.NEW.equals(operationType)) {
            action = "insertWeight";
        } else {
            action = "updateWeight";
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ModPdFactorConfigInfo/" + action, entity,
                Integer.class);
		
	}
	
	
	@Override
	public void doInsert(PdFactorWeightConfigDto entity, OperationType type) {

	}

	@Override
	public void doUpdate(PdFactorWeightConfigDto entity) {

	}

	@Override
	public void doDelete(String workflowId) {

	}
	
	
	/**
	 * 查找最新的权重配置维护信息
	 * @return
	 */
	public PdFactorWeightConfigDto findLastWeightInfo(Map<String, Object> map) {
		return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                + "/ModPdFactorConfigInfo/findLastWeightInfo",map, PdFactorWeightConfigDto.class);
	}
	
	
}
