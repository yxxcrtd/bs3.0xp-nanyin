package com.joyintech.tams.invest.product.nolimit.maxissuablesize.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

@Component
public class MaxIssuableSize001Validator implements BeanValidator{

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> errorList = new ArrayList<ValidMessage>();
		//校验操作
		return errorList;
	}

}
