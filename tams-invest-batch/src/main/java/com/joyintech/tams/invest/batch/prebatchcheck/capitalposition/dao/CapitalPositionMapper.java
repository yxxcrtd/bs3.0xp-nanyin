package com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

@Repository
public interface CapitalPositionMapper {
	/**
	 * 查询当天组合现金头寸为负的数据
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectCapitalPositionList(Map<String,Object> map);
}
