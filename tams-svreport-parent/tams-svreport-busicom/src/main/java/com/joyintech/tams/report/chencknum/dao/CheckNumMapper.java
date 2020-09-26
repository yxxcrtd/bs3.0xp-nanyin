package com.joyintech.tams.report.chencknum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.chencknum.dto.TotalAmountInfoDto;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2017年9月16日
 * @see EquityInfo001Mapper
 * @since 1.0
 */
@Repository
public interface CheckNumMapper {
	List<TotalAmountInfoDto> queryTotalAmountInfo(TotalAmountInfoDto totalAmountInfoDto);

	int insertTotalAmountInfo(TotalAmountInfoDto totalAmountInfoDto);

	int updateTotalAmountInfo(TotalAmountInfoDto totalAmountInfoDto);
	
	long getSendCount(@Param("reportDate")String reportDate);

	long getSendHoldCount(@Param("reportDate")String reportDate);

	void deltotalAmountInfoDto(TotalAmountInfoDto totalAmountInfoDto);

}
