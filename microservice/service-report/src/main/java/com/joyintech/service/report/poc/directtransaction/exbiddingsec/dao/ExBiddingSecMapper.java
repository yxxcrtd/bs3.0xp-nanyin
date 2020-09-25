package com.joyintech.service.report.poc.directtransaction.exbiddingsec.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.joyintech.service.report.poc.directtransaction.exbiddingsec.dto.ExBiddingSecDto;


@Component
public interface ExBiddingSecMapper {

	ExBiddingSecDto search(Map<String, String> map);

}
