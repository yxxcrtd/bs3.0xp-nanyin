package com.joyintech.service.report.poc.directtransaction.futuresordermanage.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.directtransaction.futuresordermanage.dto.FuturesOrderMenegeDto;

@Repository
public interface FuturesOrderMenegeMapper {

	FuturesOrderMenegeDto search(Map<String, String> map );
}
