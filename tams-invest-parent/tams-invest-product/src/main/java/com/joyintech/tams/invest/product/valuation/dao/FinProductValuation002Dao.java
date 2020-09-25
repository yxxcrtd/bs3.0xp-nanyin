package com.joyintech.tams.invest.product.valuation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuationDetail001Dto;

public interface FinProductValuation002Dao {

	/**
	 * 查询估值明细
	 * 
	 * @param pvalueid
	 * @return
	 */
	List<FinProductValuationDetail001Dto> selectValuationDetail(@Param("pvalueid") String pvalueid,
			@Param("vltype") String vltype);

	/**
	 * 估值结果
	 * 
	 * @param valueid
	 */
	FinProductValuation001Dto selectResultInfo(String valueid);
}
