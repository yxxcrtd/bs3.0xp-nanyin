package com.joyintech.tams.mobileApproval.service;

import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trdbondduration.dto.TrdBondDuration002ProcessDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdBondDurationService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
//	@Autowired
//	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdBondDuration002ProcessDto dto = JsonUtils.json2Object(businessData, TrdBondDuration002ProcessDto.class);
	    //2、处理数据字典
		dto.setPaBussType(dto.getPaBussTypeString());
		dto.setIsClean(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIsClean()));
		//债券存续期中间付息不显示 收款账户网点
		if("债券中间收息".equals(dto.getPaBussTypeString())){
			for(BussTrdTransferOrderDto bussTrdTransferOrderDto:dto.getBussTrdTransferOrder()){
				bussTrdTransferOrderDto.setRecAcctPoint(null);
			}

		}



		//指令信息
		MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
