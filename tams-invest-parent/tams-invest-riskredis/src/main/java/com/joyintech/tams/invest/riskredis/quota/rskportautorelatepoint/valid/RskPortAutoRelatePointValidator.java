package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDto;

@Component
public class RskPortAutoRelatePointValidator implements BeanValidator {
	
	@Override
	public List<ValidMessage> valid(Object value) {
		RskPortAutoRelatePointDto dto = (RskPortAutoRelatePointDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        
		return resultList;
	}

}
