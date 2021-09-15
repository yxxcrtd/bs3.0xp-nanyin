package com.joyintech.service.report.poc.directtransaction.exbiddingsec.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.directtransaction.exbiddingsec.dto.ExBiddingSecDto;


@Repository
public interface ExBiddingSecMapper {

	ExBiddingSecDto search(Map<String, String> map);

}
