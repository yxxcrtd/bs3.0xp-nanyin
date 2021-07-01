package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.risk.rskauthoritymanage.dto.RskAuthorityManageDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

@Service
public class MobileRskAuthorityManageService {
	@Autowired
    private MobileApprovalService mobileApprovalService;
//	@Autowired
//	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		RskAuthorityManageDto dto = JsonUtils.json2Object(businessData, RskAuthorityManageDto.class);
	    //2、处理数据字典
		dto.setRolePos(dto.getRolePosStr());

		if(null!=dto.getSelectedItem()&&dto.getSelectedItem().size()>0) {
			for (RskAuthorityManageDto rskAuthorityManageDto : dto.getSelectedItem()) {
				rskAuthorityManageDto.setAssetSecType(SysDataUtil.getNameFromDataDict(DictCode.授权类型二级, rskAuthorityManageDto.getAssetSecType()));
				rskAuthorityManageDto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, rskAuthorityManageDto.getTradeMarket()));
				rskAuthorityManageDto.setAssetType(SysDataUtil.getNameFromDataDict(DictCode.授权类型一级, rskAuthorityManageDto.getAssetType()));
				String tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.交易方向, rskAuthorityManageDto.getTradeDirection());
				if (!StringUtil.isNotEmpty(tradeDirectionStr) && StringUtil.equals(dto.getAssetType(), "17")) {
					tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.互换方式, rskAuthorityManageDto.getTradeDirection());
				}
				if (!StringUtil.isNotEmpty(tradeDirectionStr)) {
					tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, rskAuthorityManageDto.getTradeDirection());
				}
				rskAuthorityManageDto.setTradeDirection(tradeDirectionStr);

			}

		}
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
