package com.joyintech.tams.valuation.common.sztinterf.dao;

import com.joyintech.tams.valuation.common.sztinterf.dto.SztSubjectBalanceDto;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztSubjectInfoDto;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztValuationDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SztInterfCommonMapper {

	/**
	 * 获取业务流水号
	 * @return
	 */
	String getSerialNo();

	/**
	 * 查询科目明细
	 * @param prodId
	 * @return
	 */
	List<SztSubjectInfoDto> getSubjetInfo(Map<String,Object> params);

	/**
	 * 查询估值明细
	 * @param prodId
	 * @param cdate
	 * @return
	 */
	List<SztValuationDto> getBookValuation(Map<String,Object> params);

	/**
	 * 查询科目余额表明细
	 * @param prodId
	 * @param cdate
	 * @return
	 */
	List<SztSubjectBalanceDto> getSubjectBalalce(Map<String,Object> params);

}
