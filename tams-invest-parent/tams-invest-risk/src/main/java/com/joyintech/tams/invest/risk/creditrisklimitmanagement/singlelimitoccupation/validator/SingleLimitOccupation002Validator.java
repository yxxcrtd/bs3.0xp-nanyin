/*
 * 文件名：CreditRightsManagementValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

@Component
public class SingleLimitOccupation002Validator implements BeanValidator{
	
	
	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();

		return errorList;
	}

}
