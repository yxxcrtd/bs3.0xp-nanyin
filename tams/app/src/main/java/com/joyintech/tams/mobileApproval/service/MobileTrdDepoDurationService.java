package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trddepoduration.dto.TrdDepoDuration001Dto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdDepoDurationService {
	
	@Autowired
    private MobileApprovalService mobileApprovalService;

	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdDepoDuration001Dto dto = JsonUtils.json2Object(businessData, TrdDepoDuration001Dto.class);
	    //2、处理数据字典

		dto.setAppPingAnCodeSin(dto.getPingAnCodeSin());
		dto.setAppPingAnCodeSin(SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, dto.getPingAnCodeSin()));

		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
		dto.setAssetType(dto.getAssetTypeStr());
		dto.setCashType(dto.getPingAnCodeStr());
		dto.setChlId(dto.getChannelName());
		dto.setIsClean(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIsClean()));
		
		//指令信息
		for (BussTrdTransferOrderDto vo : dto.getBussTrdTransferOrder()) {
			vo.setCcy(vo.getCcyText());
			vo.setPayType(vo.getPayTypeText());
			if(StringUtil.isNotEmpty(vo.getIsNeedSend())&&("Y".equals(vo.getIsNeedSend())||"N".equals(vo.getIsNeedSend())))
				vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, vo.getIsNeedSend()));
			vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
			if(StringUtil.isNotEmpty(dto.getAppPingAnCodeSin())&&"存款到期".equals(dto.getAppPingAnCodeSin())){
	//			String actionType = mobileApprovalMapper.selectActionType(dto.getWorkflowId());
//				if("revoke".equals(actionType)){
					vo.setRecAcctPoint(null);
//				}
			}
		}
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
