package com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepoDto;

@Component
public interface ExBiddingDepoMapper {

	ExBiddingDepoDto search(Map<String, String> map);
}
