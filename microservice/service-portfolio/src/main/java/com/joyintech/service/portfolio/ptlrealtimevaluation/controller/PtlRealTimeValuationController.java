/*
 * 文件名：PtlRealTimeValuationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlrealtimevaluation.controller;

import com.joyintech.service.portfolio.ptlrealtimevaluation.service.PtlRealTimeValuationService;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationParameter;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.calcindicator.IndicatorName;
import com.joyintech.tams.invest.common.dto.mock.calcindicator.MockCalcIndicatorParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 组合实时估值服务
 * 
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see PtlRealTimeValuationController
 * @since 1.0
 */
@RestController
@RequestMapping("/PtlRealTimeValuation")
public class PtlRealTimeValuationController {

    /**
     * PtlRealTimeValuationService
     */
    @Autowired
    private PtlRealTimeValuationService ptlRealTimeValuationService;

    /**
     * 计算指标-主方法
     * 
     * @param param 条件
     * @return 指标计算结果Map
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("calcIndicator")
    public Map<IndicatorName, Object> calcIndicator(@RequestBody MockCalcIndicatorParameter param)
        throws Exception {
        return ptlRealTimeValuationService.calcIndicator(param.getMockPtlPortfolioDto(),
            param.getMockPtlSecValuationDtoList(), param.getMockPtlPortfolioValuationDtoList(),
            param.getMockPtlCashPositionDto(), param.getMockAssetInfoDtoList(),
            param.getExchargeQuoteList(), param.getIndicatorNameList(), param.getCalcDate());
    }

    /**
     * 计算区间指标-主方法
     * 
     * @param param 条件
     * @return 指标计算结果Map
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("calcPeriodIndicator")
    public Map<IndicatorName, Object> calcPeriodIndicator(@RequestBody MockCalcIndicatorParameter param)
        throws Exception {
        return ptlRealTimeValuationService.calcPeriodIndicator(param.getMockPtlPortfolioDto(),
            param.getMockPtlSecValuationDtoList(), param.getMockPtlPortfolioValuationDtoList(),
            param.getMockPtlCashPositionDto(), param.getMockAssetInfoDtoList(),
            param.getExchargeQuoteList(), param.getIndicatorNameList(), param.getCalcDateStart(),
            param.getCalcDateEnd());
    }

    /**
     * 估值主方法 <功能详细描述>
     * 
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("valuationMain")
    public MockCalcAssetValuationResult valuationMain(@RequestBody MockCalcAssetValuationParameter param)
        throws Exception {
        return ptlRealTimeValuationService.valuationMain(param.getMockAssetInfoDtoList(),
            param.getMockPtlPortfolioDto(), param.getMockPtlSecValuationDtoList(),
            param.getMockPtlPortfolioValuationDtoList(), param.getMockPtlFeePositionDtoList(),
            param.getAssetTradeList(), param.getLiabilitiesTradeList(), param.getFeeTradeList(),
            param.getAssetTdayPlusNdayTradeList(), param.getLiabilitiesTdayPlusNdayTradeList(),
            param.getMockPtlCashPositionMap(), param.getMockStartDate(), param.getMockEndDate(),
            param.getBaseDate(), param.getMstExchargeQuoteList(), param.getFinancingCost(),
            param.getReinvestmentRate(), param.getCompositeRate(), param.getSingleAssetChanging(),
            param.getCashChanging(), param.getLiabilitiesChanging());
    }
}
