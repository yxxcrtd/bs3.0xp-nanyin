package com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface ProcessProductTradeMapper {
	
	/**
	 * 获取根据检查日期获取所有的流程中产品交易信息
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectProcessProductTradeList(Map<String,Object> map);
}
