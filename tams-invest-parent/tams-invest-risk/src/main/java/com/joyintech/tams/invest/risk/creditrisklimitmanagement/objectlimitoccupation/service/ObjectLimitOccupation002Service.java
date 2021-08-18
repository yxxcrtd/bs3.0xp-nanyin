package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.controller.SingleLimitOccupation001Controller;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 单笔资产额度校验规则
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupation001Controller
 * @since 1.0
 */
@Service
public class ObjectLimitOccupation002Service extends CommonExportService<ObjectLimitOccupationDto>
		implements BaseProcessService<ObjectLimitOccupationDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(ObjectLimitOccupationDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	            action = "insert";
	            setEntity(entity);
	        }
	        else {
	            action = "update";
	            setEntity(entity);
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitOccupation/" + action, entity,
	            Integer.class);

	}

	private void setEntity(ObjectLimitOccupationDto entity) {
		//查询授信额度管理
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", entity.getCustomerId());
		param.put("creditType", entity.getCreditType());
		param.put("term", entity.getTerm());
		param.put("termUnit", entity.getTermUnit());
		List<CreditLines> list = selectCreditLine(param);
		if (list != null && list.size() > 0) {
			entity.setCreditMstId(list.get(0).getCreditMstId());
		}
	}
	/**
	 * 查询授信额度管理 <功能详细描述>
	 * 
	 * @param parameters
	 *            parameters
	 * @return CreditLines
	 * @see
	 * @since 1.0
	 */
	public List<CreditLines> selectCreditLine(Map<String, Object> param) {
		return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/ObjectLimitOccupation/selectCreditLine",
				param, CreditLines.class);
	}
   
}
