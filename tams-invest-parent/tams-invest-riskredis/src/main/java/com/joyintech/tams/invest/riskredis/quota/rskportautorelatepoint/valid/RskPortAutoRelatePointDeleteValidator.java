package com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.valid;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.dto.RskPortAutoRelatePointDeleteDto;
import com.joyintech.tams.invest.riskredis.quota.rskportautorelatepoint.service.RskPortAutoRelatePoint002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
@Component
public class RskPortAutoRelatePointDeleteValidator implements BeanValidator {

	@Autowired
	private RskPortAutoRelatePoint002Service rskPortAutoRelatePoint002Service;

	@Override
	public List<ValidMessage> valid(Object value) {
		RskPortAutoRelatePointDeleteDto dto = (RskPortAutoRelatePointDeleteDto)value;
		List<ValidMessage> resultList = new ArrayList<ValidMessage>();
		if(StringUtil.equals(dto.getOperateType(), "delete")){
			if(!StringUtil.isEmptyList(dto.getSelectedItem())){
				for(RskPortAutoRelatePointDeleteDto e:dto.getSelectedItem()){
					if (!StringUtil.isNotEmpty(rskPortAutoRelatePoint002Service.getRelInfoByRuleId(e.getRuleId(),e.getIndId()))) {
						resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
					}
				}
			}
		}
		return resultList;
	}

}
