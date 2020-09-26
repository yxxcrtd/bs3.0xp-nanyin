package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao.ValNetValueConfirm004Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 净值修改service
 * 
 * @author hechuan
 *
 */
@Service
public class ValNetValueConfirm004Service extends CommonExportService<ValNetValueConfirm004Dto> implements BaseProcessService<ValNetValueConfirm004Dto> {

	
	@Autowired
    private ValNetValueConfirm004Mapper valNetValueConfirm004Mapper;
	

	@Override
	public void doComplete(ValNetValueConfirm004Dto entity, OperationType type) {
		entity.setOperateType(type.toString());
		if(entity != null){
			//查询估值表余额表状态表附属表条数
			int count = valNetValueConfirm004Mapper.queryNetValueCount(entity);
			if(count > 0){
				valNetValueConfirm004Mapper.updateNetValue(entity);
			}else{
				valNetValueConfirm004Mapper.insertNetValue(entity);
			}
		}
	}
    
	
    
}
