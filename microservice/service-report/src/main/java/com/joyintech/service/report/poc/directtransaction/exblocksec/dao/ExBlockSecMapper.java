package com.joyintech.service.report.poc.directtransaction.exblocksec.dao;

import org.springframework.stereotype.Component;

import com.joyintech.service.report.poc.directtransaction.exblocksec.dto.ExBlockSecDto;

import java.util.Map;

@Component
public interface ExBlockSecMapper {

	ExBlockSecDto search(Map<String, String> map);
}
