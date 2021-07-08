package com.joyintech.tams.invest.batch.mstbondvaluationtotal.dao;

import java.util.Date;
import java.util.Map;

public interface MstBondValuationTotalMapper {

	void deleteByDate(Map<String, Object> param);
	
	/**
	 * 银行间债券优先取中债
	 * @param param
	 */
	void insertByDate(Map<String, Object> param);
	
	/**
	 * 银行间债券根据托管机构汇总行情信息
	 * @param date
	 */
	void insertByDateAndRegist(Map<String, Object> param);
}
