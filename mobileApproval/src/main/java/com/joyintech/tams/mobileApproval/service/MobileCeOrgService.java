package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.invest.risk.creditmanagement.ceorg.dto.CreditMstInfoDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MobileCeOrgService {
	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;
	@Autowired(required = false)
    @Qualifier("loadBalancedRestTemplate")
    protected JoyinRestTemplate restTemplate;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		CreditLinesOrgDto dto = JsonUtils.json2Object(businessData, CreditLinesOrgDto.class);
	    //2、处理数据字典
		dto.setCustomerId(mobileApprovalMapper.getCustomerName(dto.getCustomerId()));

		//授权品种list
		List<CreditMstInfoDto> dtoList = mobileApprovalMapper.getTreeCeOrgQuote("01");
		Map<String, CreditMstInfoDto> map = new HashedMap<String, CreditMstInfoDto>();
		Map<String, CreditMstInfoDto> map2 = new HashedMap<String, CreditMstInfoDto>();
		for (CreditMstInfoDto vo : dtoList) {
			map.put(vo.getCreditMstId(), vo);
			map2.put(vo.getCreditQuoteTypeCode(), vo);
		}
		List<CreditLines> itemList = dto.getCreditQuoteItem();
		for (CreditLines vo : itemList) {
			String pid = map.get(vo.getCreditMstId()).getUpperCreditQuoteTypeCode();
			if(!"root".contentEquals(pid)) {
				vo.setPersonalField(map2.get(pid).getCreditMstId());
			}
		}

		//设置品种名称
		if(dto.getCreditQuoteItem().size()>0){
			for(CreditLines creditLines:dto.getCreditQuoteItem()){
				creditLines.setCreditMstId(mobileApprovalMapper.queryCreditMstId(creditLines.getCreditMstId()));
			}
		}

	    //组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}
}
