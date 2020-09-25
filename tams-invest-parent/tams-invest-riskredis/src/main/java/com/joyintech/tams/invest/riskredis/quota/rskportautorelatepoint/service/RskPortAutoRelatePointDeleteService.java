package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service;

import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDeleteDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
@Service
public class RskPortAutoRelatePointDeleteService extends CommonExportService<RskPortAutoRelatePointDeleteDto>
implements BaseProcessService<RskPortAutoRelatePointDeleteDto> {

	@Override
	public void doComplete(RskPortAutoRelatePointDeleteDto entity, OperationType type) {
		String action = StringUtil.EMPTY;
		if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
		restTemplate.postForObject(
	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskPortAutoRelatePoint/" + action, entity,
	            Integer.class);
		
	}

}
