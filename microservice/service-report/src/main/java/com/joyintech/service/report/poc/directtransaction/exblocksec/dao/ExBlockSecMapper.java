package com.joyintech.service.report.poc.directtransaction.exblocksec.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.directtransaction.exblocksec.dto.ExBlockSecDto;

import java.util.Map;

@Repository
public interface ExBlockSecMapper {

	ExBlockSecDto search(Map<String, String> map);
}
