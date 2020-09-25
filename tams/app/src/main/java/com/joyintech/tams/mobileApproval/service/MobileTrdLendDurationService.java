package com.joyintech.tams.mobileApproval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdLendDurationService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdLendDuration001Dto dto = JsonUtils.json2Object(businessData, TrdLendDuration001Dto.class);
	    //2、处理数据字典
		dto.setPortfolioId(mobileApprovalMapper.getPortfolioName(dto.getPortfolioId()));
		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
		dto.setPsStr(dto.getPingAnCodeStr());
		dto.setCounterId(mobileApprovalMapper.getCounterName(dto.getCounterId()));

		//指令信息
		MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);


	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");

	}
}
