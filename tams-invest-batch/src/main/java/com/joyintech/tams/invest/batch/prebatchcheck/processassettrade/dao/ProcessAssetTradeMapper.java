package com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ProcessAssetTradeMapper {
	
	List<SysPrebatchResultDetail> selectProcessAssetTradeList(Map<String,Object> map);
}
