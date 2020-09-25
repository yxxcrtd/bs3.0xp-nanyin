package com.joyintech.tams.invest.settle.ordernewbuild.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild001Dto;

/**
* @author yaozhenguo
* @version 1.0 2019年11月1日 上午10:56:30
* @ClassName OrderNewBuild002Validator
* @Description 
*/
@Component
public class OrderNewBuild001Validator implements BeanValidator{

	@Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		OrderNewBuild001Dto dto = (OrderNewBuild001Dto) value;
		//撤销状态不可再撤销
		if(StringUtil.equals(dto.getOrderStatus(), DictCodeListExt1.指令状态_撤销.getValue())) {
			validList.add(new ValidMessage(getMessage("OrderNewBuild001.revoke.orderStatus")));
		}
		//发送成功不能撤销
		if(StringUtil.equals(dto.getSendStatus(), DictCodeList.发送状态_发送成功.getValue())) {
			validList.add(new ValidMessage(getMessage("OrderNewBuild001.revoke.sendStatus")));
		}
		
		//流程验证
		WflValidInputParameter wflValidInputParameterIssue = new WflValidInputParameter();
        wflValidInputParameterIssue.setPageId(dto.getPageId());
        wflValidInputParameterIssue.setWorkflowId(dto.getWorkflowId());
        wflValidInputParameterIssue.setBusinessDataPk1(dto.getActOrderId());
        if (mstWorkflowCommonService.isInWflProcess(wflValidInputParameterIssue)) {
            validList.add(new ValidMessage(getMessage("OrderNewBuild001_E_001",
                "")));
        }
		return validList;
	}
	
}
