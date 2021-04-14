package com.joyintech.tams.mobileApproval.service;

import java.util.List;

import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.portfolio.ptlcashflow.dto.PtlCashFlowDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobilePtlCashFlowService {
	
	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;
	
	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		PtlCashFlowDto dto = JsonUtils.json2Object(businessData, PtlCashFlowDto.class);
	    //2、处理数据字典
		dto.setPortfolioId(dto.getPortfolioName());
		dto.setPs(dto.getPsStr());
		dto.setCcy(dto.getCcyStr());
		dto.setFinprodType2(dto.getFinprodType2Str());
		dto.setCustomCashType(dto.getCustomCashTypeStr());
		dto.setCounterId(mobileApprovalMapper.getCounterName(dto.getCounterId()));
		
		//指令信息
		List<BussTrdTransferOrderBase> bussTrdTransferOrder = dto.getBussTrdTransferOrder();
		for (BussTrdTransferOrderBase vo : bussTrdTransferOrder) {
			vo.setCcy(vo.getCcyText());
			vo.setPayType(vo.getPayTypeText());
			if(StringUtil.isNotEmpty(vo.getIsNeedSend())&&("Y".equals(vo.getIsNeedSend())||"N".equals(vo.getIsNeedSend())))
				vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, vo.getIsNeedSend()));
		//	vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
		}
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
