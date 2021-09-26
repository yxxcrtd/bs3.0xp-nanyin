package com.joyintech.tams.valuation.batch.prebatchcheck.valuationscheme.dao;

import com.joyintech.tams.valuation.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


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
