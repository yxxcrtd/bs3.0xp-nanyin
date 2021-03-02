package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto.InvestmentBusinessCounterDetailsDto;
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

	/**
	 *产品按期限统计季末余额
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectProductBalanceByInfo(Map<String,Object> map);

	/**
	 * 平均年化资金成本,公式一
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectProductAvgRateInfo1(Map<String,Object> map);
	/**
	 * 平均年化资金成本,公式二
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectProductAvgRateInfo2(Map<String,Object> map);

	/**
	 *资产按照期限统计穿透前期末余额
	 * @param endDate
	 * @return
	 */

	List<InvestmentBusinessDetailsDto> selectAssetBalanceByInfo(Map<String,Object> map);

	/**
	 * 资金运用的“'平均年化收益水平”公式一
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectAssetRateByInfo1(Map<String,Object> map);
	/**
	 * 资金运用的“'平均年化收益水平”公式二
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectAssetRateByInfo2(Map<String,Object> map);

	/**
	 *资产按照期限统计穿透前期末余额
	 * @param endDate
	 * @return
	 */

	List<InvestmentBusinessDetailsDto> selectNSAssetBalanceByInfo(Map<String,Object> map);

	/**
	 * 资金运用的“'平均年化收益水平”公式一
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectNSAssetRateByInfo1(Map<String,Object> map);
	/**
	 * 资金运用的“'平均年化收益水平”公式二
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectNSAssetRateByInfo2(Map<String,Object> map);

	List<InvestmentBusinessDetailsDto> selectIncurDebtsBalanceByInfo(Map<String,Object> map);
	List<InvestmentBusinessDetailsDto> selectIncurDebtsRateByInfo(Map<String,Object> map);

	/**
	 *表7中金融业按照所属三级行业类别进行分类
	 * @param endDate
	 * @return
	 */

	List<InvestmentBusinessDetailsDto> selectFinanceAssetBalanceByInfo(Map<String,Object> map);
	/**
	 * 表7中资金运用的“'平均年化收益水平”公式一
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectFinanceAssetRateByInfo1(Map<String,Object> map);
	/**
	 * 表7中资金运用的“'平均年化收益水平”公式二
	 * @param endDate
	 * @return
	 */
	List<InvestmentBusinessDetailsDto> selectFinanceAssetRateByInfo2(Map<String,Object> map);

	/**
	 * 表7-2表内外同业投资业务分机构类型交易对手（万元）
	 * @param map
	 * @return
	 */

	List<InvestmentBusinessCounterDetailsDto> selectCounterBalanceListByInfo(Map<String,Object> map);


}
