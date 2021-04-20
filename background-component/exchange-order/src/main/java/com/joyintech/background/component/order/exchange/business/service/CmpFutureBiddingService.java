package com.joyintech.background.component.order.exchange.business.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.joyintech.background.component.order.exchange.business.dto.EtrFutureBiddingDto;
import com.joyintech.background.component.order.exchange.common.dto.EtrParameterDto;
import com.joyintech.background.component.order.exchange.common.service.EtrSubComponentService;

@Component
public class CmpFutureBiddingService extends EtrSubComponentService<EtrFutureBiddingDto> {

	public BindingResult entrustCheck(@Validated EtrFutureBiddingDto dto, BindingResult result) {
		// 交易规则配置
		EtrParameterDto settings = settings();
		return result;
	}
}
