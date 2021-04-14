package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileOrgNameManagementService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
//	@Autowired
//	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		RskAccessListDto dto = JsonUtils.json2Object(businessData, RskAccessListDto.class);
	    //2、处理数据字典
		List<RskAccessListDetailDto> rskAccessListDetailList = dto.getRskAccessListDetailList();
		for (RskAccessListDetailDto vo : rskAccessListDetailList) {
			if(DictCodeListExt2.名单操作方向_移出名单.getValue().equals(vo.getOperatePs())) {
				vo.setOperatePs(DictCodeListExt2.名单操作方向_移出名单.getCodeName());
			}else {
				vo.setOperatePs(DictCodeListExt2.名单操作方向_移入名单.getCodeName());
			}
			
		}
	  
	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
