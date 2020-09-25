package com.joyintech.tams.invest.settle.ordernewbuild.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.settle.ordernewbuild.dto.OrderNewBuild002Dto;

/**
* @author yaozhenguo
* @version 1.0 2019年11月1日 上午10:56:30
* @ClassName OrderNewBuild002Validator
* @Description 
*/
@Component
public class OrderNewBuild002Validator implements BeanValidator{

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		OrderNewBuild002Dto dto = (OrderNewBuild002Dto) value;
		//收款账号和付款账号不能相同
		if(StringUtil.isNotEmpty(dto.getMyAcctNo()) && StringUtil.isNotEmpty(dto.getCounterAcctNo())) {
			if(StringUtil.equals(dto.getMyAcctNo(), dto.getCounterAcctNo())) {
				validList.add(new ValidMessage("counterAcctNo",getMessage("OrderNewBuild002.acctNo")));
			}
		}
		//生成计划验证
		if(StringUtil.isEmptyList(dto.getBussTrdTransferOrder())) {
			validList.add(new ValidMessage(getMessage("FW_E_0006", getMessage("OrderNewBuild001.fee.order"))));
		}
		return validList;
	}
	
}
