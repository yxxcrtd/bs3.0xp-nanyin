package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.period.trdrepoduration.dto.TrdRepoDuration002Dto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileTrdRepoDurationService {
	
	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdRepoDuration002Dto dto = JsonUtils.json2Object(businessData, TrdRepoDuration002Dto.class);
	    //2、处理数据字典
		dto.setFinprodType2(dto.getFinprodType2String());
		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
		dto.setCounterId(dto.getCounterName());
		dto.setType6(dto.getType6String());
		dto.setTradeMarket(dto.getTradeMarketString());
		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
		dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
		dto.setTermTypeReback(SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, dto.getTermTypeReback()));
		dto.setCounterIdex(dto.getCounterNamex());

		//查询质押券信息
		dto.setRepoInfoList(mobileApprovalMapper.findRepoInfo(dto.getFinprodId()));


		//指令信息
		MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
