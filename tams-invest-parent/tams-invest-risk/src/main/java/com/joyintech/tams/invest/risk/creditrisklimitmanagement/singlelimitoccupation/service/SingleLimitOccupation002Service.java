package com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.controller.SingleLimitOccupation001Controller;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
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
public class SingleLimitOccupation002Service extends CommonExportService<SingleLimitOccupationDto>
		implements BaseProcessService<SingleLimitOccupationDto> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(SingleLimitOccupationDto entity, OperationType type) {
		 String action = StringUtil.EMPTY;
	        if (OperationType.DELETE.equals(type)) {
	            action = "delete";
	        }
	        else if (OperationType.NEW.equals(type)) {
	        	saveEntity(entity);
	            action = "insert";
	        }
	        else {
	        	saveEntity(entity);
	            action = "update";
	        }
	        restTemplate.postForObject(
	            ServiceApplication.RISK_SERVICE_NAME + "/SingleLimitOccupation/" + action, entity,
	            Integer.class);

	}

	
	private void saveEntity(SingleLimitOccupationDto entity) {
		entity.setCreditRuleType(DictCodeListExt3.额度规则类型_单笔资产.getValue());
		Map<String, Object> map = new HashMap<String, Object>();
		//根据资产代码获取资产分类
		map.put("FINPROD_ID", entity.getFinprodId());
        FinProductType finProductType = restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/SingleLimitOccupation/getFinProductType",
                map, FinProductType.class);
        if (finProductType != null) {
        	//获取资产分类
        	entity.setAstType1(finProductType.getType8());
        	entity.setAstType2(finProductType.getType9());
        	entity.setAstType3(finProductType.getType10());
        	entity.setAstType4(finProductType.getType11());
        	entity.setAstType5(finProductType.getType12());
        }
	}
	
	 /**
     * 根据授权额度编号查询额度信息
     * @param seqno 额度编号
     * @return 额度信息
     */
   public SingleLimitOccupationDto getBussinessEntityByIndId(String creditRuleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("creditRuleId", creditRuleId);
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/SingleLimitOccupation/getBussinessEntityByIndId",
            map, SingleLimitOccupationDto.class);
    }
   
}
