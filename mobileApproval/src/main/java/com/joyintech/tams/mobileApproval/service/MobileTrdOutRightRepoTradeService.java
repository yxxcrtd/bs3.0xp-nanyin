package com.joyintech.tams.mobileApproval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade002Dto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdOutRightRepoTradeService {
	
	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdOutRightRepoTrade002Dto dto = JsonUtils.json2Object(businessData, TrdOutRightRepoTrade002Dto.class);
	    //2、处理数据字典
		dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
		dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, dto.getPs()));
		dto.setTermType(SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, dto.getTermType()));
		dto.setDealCalId(mobileApprovalMapper.getCalendarName(dto.getDealCalId()));
		dto.setSecAcctId(mobileApprovalMapper.getSecAcctName(dto.getSecAcctId()));
		dto.setCalcModel(SysDataUtil.getNameFromDataDict(DictCode.计算模式, dto.getCalcModel()));
		dto.setmSettleMethod(SysDataUtil.getNameFromDataDict(DictCode.结算方式, dto.getmSettleMethod()));
		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));

		dto.setSettleSpeed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpeed()));
		dto.setfSettleMethod(SysDataUtil.getNameFromDataDict(DictCode.结算方式, dto.getfSettleMethod()));

		//通道

		if(dto.getChlId()!=null) {
			dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
		}

		//交易对手
		TrdInvestPersonInfo002Dto trdOutRightRepoPersonInfo = dto.getTrdOutRightRepoPersonInfo();
		if(trdOutRightRepoPersonInfo!=null) {
			trdOutRightRepoPersonInfo.setCounterId(mobileApprovalMapper.getCounterName(trdOutRightRepoPersonInfo.getCounterId()));
		}
		
		//指令信息
		MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
		
		//风险试算信息
		MobileUtils.dealRiskTrialRetDto(dto.getRiskTrialRetDto());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}

}
