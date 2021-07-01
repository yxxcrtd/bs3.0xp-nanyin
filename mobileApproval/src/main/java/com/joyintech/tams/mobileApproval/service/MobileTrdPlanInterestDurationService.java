package com.joyintech.tams.mobileApproval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.FinAssetInfo;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.trdplanintduration.dto.TrdPlanInterestDurationProcessDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdPlanInterestDurationService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdPlanInterestDurationProcessDto dto = JsonUtils.json2Object(businessData, TrdPlanInterestDurationProcessDto.class);
	    //2、处理数据字典
		FinAssetInfo finAssetInfo = dto.getFinAssetInfo();
		if(finAssetInfo!=null) {
			finAssetInfo.setType6(dto.getType6String());
			finAssetInfo.setType7(dto.getType7String());
			finAssetInfo.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, finAssetInfo.getCcy()));
		}
		dto.setPaBussType(dto.getPaBussTypeString());
		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
		dto.setIsClean(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIsClean()));
		
		List<FinAssetIntInfo> finAssetIntInfoList = dto.getFinAssetIntInfoList();
		for (FinAssetIntInfo vo : finAssetIntInfoList) {
			vo.setCalcRule(vo.getCalcRuleString());
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
