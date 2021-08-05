package com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

/**
 * 估值方法方案 mapper
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Repository
public interface ValuationSchemeMapper {
	/**
	 * 获取未配置估值方法方案的信息
	 * @param map
	 * @return
	 */
	List<SysPrebatchResultDetail> selectValuationSchemeList(Map<String,Object> map);
}
