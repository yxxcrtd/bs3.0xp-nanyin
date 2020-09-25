package com.joyintech.tams.invest.product.valuation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.product.valuation.dao.FinProductValuation002Dao;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuationDetail001Dto;

@Service
public class FinProductValuation002Service {

	@Autowired
	private FinProductValuation002Dao finProductValuation002Dao;

	/**
	 * 查询估值明细
	 * 
	 * @param pvalueid
	 * @return
	 */
	public List<FinProductValuationDetail001Dto> selectValuationDetail(String pvalueid, String vltype) {
		return finProductValuation002Dao.selectValuationDetail(pvalueid, vltype);
	}

	/**
	 * 估值结果
	 * 
	 * @param valueid
	 * @return
	 */
	public FinProductValuation001Dto selectResultInfo(String valueid) {
		return finProductValuation002Dao.selectResultInfo(valueid);
	}

}
