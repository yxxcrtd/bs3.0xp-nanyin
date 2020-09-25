package com.joyintech.service.report.poc.directtransaction.futuresordermanage.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.joyintech.service.report.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;

@Component
public interface FuturesOrderMenegeMapper {

	FuturesOrderMenegeDto search(Map<String, String> map );
}
