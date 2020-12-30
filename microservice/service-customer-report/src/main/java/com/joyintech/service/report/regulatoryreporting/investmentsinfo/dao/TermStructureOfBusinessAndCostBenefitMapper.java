package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.InvestmentBusinessDetailsDto;

/**  
 * @Description 大型银行报表 表四 表内外投资业务期限结构及成本收益表
 * @ClassName   TermStructureOfBusinessAndCostBenefit  
 * @Date        2019年8月7日 
 * @Author      yuluan  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Repository
public interface TermStructureOfBusinessAndCostBenefitMapper {
	
	List<InvestmentBusinessDetailsDto> selectClosedProductBalance(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectOpenProductBalance(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectRProductAvgRate(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectNProductAvgRate(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectAssetBalance(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectAssetRate(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectNSAssetBalance(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectNSAssetRate(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectIncurDebtsBalance(@Param("endDate")Date endDate);
	List<InvestmentBusinessDetailsDto> selectIncurDebtsRate(@Param("endDate")Date endDate);
	
	
}
