package com.joyintech.service.portfolio.valuationposition.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.portfolio.valuationposition.controller.ValuationpositionController;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;

/**
 * 估值头寸服务
 * 
 * @author wl
 * @version 1.0 2020年4月13日
 * @see ValuationpositionController
 * @since 1.0
 */
@Repository
public interface ValuationpositionMapper {

    /**
     * 组合资产分析查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<MockPtlSecValuationDto> selectPtlSecValuationData(Map<String, Object> map);

    /**
     * 组合估值查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<MockPtlPortfolioValuationDto> selectPtlPortfolioValuationData(Map<String, Object> map);

    /**
     * 组合费用头寸查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<MockPtlFeePositionDto> selectPtlFeePositionData(Map<String, Object> map);

    /**
     * 组合现金查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<MockPtlCashPositionDto> selectPtlCashPositionData(Map<String, Object> map);

}
