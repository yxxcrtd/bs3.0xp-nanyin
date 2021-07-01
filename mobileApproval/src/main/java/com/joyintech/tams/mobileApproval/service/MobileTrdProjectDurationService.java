package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.FinAssetInfo;
import com.joyintech.tams.invest.period.trdprojduration.dto.TrdProjectDurationProcessDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileTrdProjectDurationService {
	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdProjectDurationProcessDto dto = JsonUtils.json2Object(businessData, TrdProjectDurationProcessDto.class);
	    //2、处理数据字典
		FinAssetInfo finAssetInfo = dto.getFinAssetInfo();
		if(finAssetInfo!=null) {
			finAssetInfo.setType6(dto.getType6String());
			finAssetInfo.setType7(dto.getType7String());
			finAssetInfo.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, finAssetInfo.getCcy()));

		}
		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
		dto.setIsClean(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIsClean()));

		//设置付费周期
		if(dto.getFinAssetIntInfoList().size()>0) {
			for (FinAssetIntInfo finAssetIntInfo : dto.getFinAssetIntInfoList()) {
				finAssetIntInfo.setCalcRule(SysDataUtil.getNameFromDataDict(DictCode.周期单位, finAssetIntInfo.getCalcRule()));
			}
		}




		//项目类资产到期,项目类资产到期撤销不显示“累计还本未付利息”、“区间未付利息”、“实际区间未付利息”
		if(StringUtil.isNotEmpty(dto.getPaBussTypeString())&&"项目类资产到期".equals(dto.getPaBussTypeString())){
			dto.setPayUnpaidintAmt(null);
			for(FinAssetIntInfo finAssetIntInfo:dto.getFinAssetIntInfoList()){
				finAssetIntInfo.setRepaymentUnpayInterest(null);
				finAssetIntInfo.setActrRepaymentUnpayInterest(null);
			}
		}else{
			dto.setBussTrdTransferOrder(null);
		}

		//指令信息
		if(null!=dto.getBussTrdTransferOrder()&&dto.getBussTrdTransferOrder().size()>0)
			MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
