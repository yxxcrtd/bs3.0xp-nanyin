package com.joyintech.tams.invest.riskredis.quota.redismgr.validate;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.riskredis.quota.redismgr.dto.RskRedisParamConfigDto;
import com.joyintech.tams.invest.riskredis.quota.redismgr.service.RskRedisParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RskRedisParamConfigValidator implements BeanValidator {

	@Autowired
	private RskRedisParamConfigService rskRedisParamConfig001Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		List<ValidMessage> resultList = new ArrayList<>();
		RskRedisParamConfigDto dto = (RskRedisParamConfigDto) value;
		return resultList;
	}
}
