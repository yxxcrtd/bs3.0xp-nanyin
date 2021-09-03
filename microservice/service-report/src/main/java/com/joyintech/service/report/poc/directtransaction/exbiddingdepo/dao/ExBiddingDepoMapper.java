package com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dao;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.poc.directtransaction.exbiddingdepo.dto.ExBiddingDepoDto;

@Repository
public interface ExBiddingDepoMapper {

	ExBiddingDepoDto search(Map<String, String> map);
}
