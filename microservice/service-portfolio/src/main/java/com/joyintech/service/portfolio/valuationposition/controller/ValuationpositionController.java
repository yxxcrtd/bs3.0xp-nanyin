package com.joyintech.service.portfolio.valuationposition.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.portfolio.valuationposition.service.ValuationpositionService;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlValuationPositionDto;

/**
 * 估值头寸服务
 * 
 * @author wl
 * @version 1.0 2020年4月13日
 * @see ValuationpositionController
 * @since 1.0
 */
@RestController
@RequestMapping("/valuationposition")
public class ValuationpositionController {
    

    /**
     * 注入service
     */
    @Autowired
    private ValuationpositionService valuationpositionService;
    
    /**
     * 查询估值头寸所有数据
     * 参数：portfolioId： 组合代码（String）
     * 参数：cdate： 计算日期（String yyyy-MM-dd）
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPtlValuationPositionAllData")
    public MockPtlValuationPositionDto selectPtlValuationPositionAllData(@RequestBody Map<String, Object> param)
        throws Exception {
        return valuationpositionService.selectPtlValuationPositionAllData(param);
    }

    /**
     * 组合资产分析查询
     * 参数：portfolioId： 组合代码（String）
     * 参数：cdate： 计算日期（String yyyy-MM-dd）
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPtlSecValuationData")
    public List<MockPtlSecValuationDto> selectPtlSecValuationData(@RequestBody Map<String, Object> param)
        throws Exception {
        return valuationpositionService.selectPtlSecValuationData(param);
    }

    /**
     * 组合估值查询
     * 参数：portfolioId： 组合代码（String）
     * 参数：cdate： 计算日期（String yyyy-MM-dd）
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPtlPortfolioValuationData")
    public List<MockPtlPortfolioValuationDto> selectPtlPortfolioValuationData(@RequestBody Map<String, Object> param)
        throws Exception {
        return valuationpositionService.selectPtlPortfolioValuationData(param);
    }

    /**
     * 组合费用头寸查询
     * 参数：portfolioId： 组合代码（String）
     * 参数：cdate： 计算日期（String yyyy-MM-dd）
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPtlFeePositionData")
    public List<MockPtlFeePositionDto> selectPtlFeePositionData(@RequestBody Map<String, Object> param)
        throws Exception {
        return valuationpositionService.selectPtlFeePositionData(param);
    }

    /**
     * 组合现金查询
     * 参数：portfolioId： 组合代码（String）
     * 参数：cdate： 计算日期（String yyyy-MM-dd）
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectPtlCashPositionData")
    public List<MockPtlCashPositionDto> selectPtlCashPositionData(@RequestBody Map<String, Object> param)
        throws Exception {
        return valuationpositionService.selectPtlCashPositionData(param);
    }
}
