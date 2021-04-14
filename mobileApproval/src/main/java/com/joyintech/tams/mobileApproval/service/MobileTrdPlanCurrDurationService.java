package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.period.trdplancurrduration.dto.TrdPlanCurrDuration001Dto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdPlanCurrDurationService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdPlanCurrDuration001Dto dto = JsonUtils.json2Object(businessData, TrdPlanCurrDuration001Dto.class);
	    //2、处理数据字典
		dto.setBonusMode(dto.getBonusModeStr());
		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));

		//新建现金分红存续期交易不显示指令信息
		if(StringUtil.isNotEmpty(dto.getBonusMode())&&"现金分红".equals(dto.getBonusMode()))
			dto.setBussTrdTransferOrder(null);

		//指令信息
		if(null!=dto.getBussTrdTransferOrder())
			MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}

}
