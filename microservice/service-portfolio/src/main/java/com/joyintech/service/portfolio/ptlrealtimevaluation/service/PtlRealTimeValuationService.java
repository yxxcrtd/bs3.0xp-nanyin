/*
 * 文件名：PtlRealTimeValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlrealtimevaluation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.calcindicator.IndicatorName;
import com.joyintech.tams.invest.common.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValIndicatorCalcUtil;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValMainService;

/**
 * 组合实时估值服务Service
 *
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see PtlRealTimeValuationService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class PtlRealTimeValuationService extends BaseService {

    /**
     * 计算指标-主方法
     *
     * @param mockPtlPortfolioDto 组合信息
     * @param mockPtlSecValuationDtoList 组合资产估值列表
     * @param mockPtlPortfolioValuationDtoList 组合估值列表
     * @param mockPtlCashPositionDto 组合剩余现金
     * @param mockAssetInfoDtoList 资产基本信息列表
     * @param exchargeQuoteList 汇率行情列表
     * @param indicatorNameList 指标名称列表
     * @param calcDate 计算日期
     * @return 指标计算结果Map
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Map<IndicatorName, Object> calcIndicator(MockPtlPortfolioDto mockPtlPortfolioDto,
                                                    List<MockPtlSecValuationDto> mockPtlSecValuationDtoList,
                                                    List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList,
                                                    MockPtlCashPositionDto mockPtlCashPositionDto,
                                                    List<MockAssetInfoDto> mockAssetInfoDtoList,
                                                    List<MockMstExchargeQuoteDto> exchargeQuoteList,
                                                    List<IndicatorName> indicatorNameList,
                                                    Date calcDate) {
        return RealTimeValIndicatorCalcUtil.calcIndicator(mockPtlPortfolioDto,
            mockPtlSecValuationDtoList, mockPtlPortfolioValuationDtoList, mockPtlCashPositionDto,
            mockAssetInfoDtoList, exchargeQuoteList, indicatorNameList, calcDate);
    }

    /**
     * 计算区间指标-主方法
     *
     * @param mockPtlPortfolioDto 组合信息
     * @param mockPtlSecValuationDtoList 组合资产估值列表
     * @param mockPtlPortfolioValuationDtoList 组合估值列表
     * @param mockPtlCashPositionDto 组合剩余现金
     * @param mockAssetInfoDtoList 资产基本信息列表
     * @param exchargeQuoteList 汇率行情列表
     * @param indicatorNameList 指标名称列表
     * @param calcDateStart 计算日期起始日
     * @param calcDateEnd 计算日期终止日
     * @return 指标计算结果Map
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Map<IndicatorName, Object> calcPeriodIndicator(MockPtlPortfolioDto mockPtlPortfolioDto,
                                                          List<MockPtlSecValuationDto> mockPtlSecValuationDtoList,
                                                          List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList,
                                                          MockPtlCashPositionDto mockPtlCashPositionDto,
                                                          List<MockAssetInfoDto> mockAssetInfoDtoList,
                                                          List<MockMstExchargeQuoteDto> exchargeQuoteList,
                                                          List<IndicatorName> indicatorNameList,
                                                          Date calcDateStart, Date calcDateEnd) {
        return RealTimeValIndicatorCalcUtil.calcPeriodIndicator(mockPtlPortfolioDto,
            mockPtlSecValuationDtoList, mockPtlPortfolioValuationDtoList, mockPtlCashPositionDto,
            mockAssetInfoDtoList, exchargeQuoteList, indicatorNameList, calcDateStart,
            calcDateEnd);
    }

    /**
     * @param mockAssetInfoDtoList 资产基本信息列表 数据结构：List<资产基本信息DTO> 参考【数据结构定义】定义
     * @param mockPtlPortfolioDto 组合基本信息DTO 数据结构：参考【数据结构定义】定义
     * @param mockPtlSecValuationDtoList 组合资产估值列表 数据结构：List<组合资产估值扩展Entity>
     * @param mockPtlPortfolioValuationDtoList 组合估值列表 数据结构：List<组合估值DTO> 参考【数据结构定义】定义
     * @param mockPtlFeePositionDtoList 组合费用头寸扩展列表 数据结构：List<组合费用头寸Entity>
     * @param assetTradeList 资产端交易列表 数据结构：List<金融产品交易扩展>
     * @param liabilitiesTradeList 负债端交易列表 数据结构：List<金融产品交易扩展>
     * @param feeTradeList 费用交易列表 数据结构：List<金融产品交易扩展>
     * @param assetTdayPlusNdayTradeList 资产端T+N交易列表 数据结构：List<金融产品交易扩展> 说明：查询对象交易日<平台日 且 交割日>= 平台日
     * @param liabilitiesTdayPlusNdayTradeList 负债端T+N交易列表 数据结构：List<金融产品交易扩展> 说明：查询对象交易日<平台日 且
     *            交割日>= 平台日
     * @param mockPtlCashPositionMap 组合现金DTO 数据结构：参考【数据结构定义】定义 说明：不含本次传入金融产品交易 对现金的影响
     * @param mockStartDate 测算开始日
     * @param mockEndDate 测算结束日
     * @param baseDate 基准日期
     * @param mstExchargeQuoteList 汇率行情列表
     * @param financingCost 融资成本
     * @param reinvestmentRate 再投收益率
     * @param compositeRate 综合费率
     * @param T_N资产变化 T+N资产变化
     * @param T_N现金变化 T+N现金变化
     * @param T_N负债变化 T+N负债变化
     */
    public MockCalcAssetValuationResult valuationMain(List<MockAssetInfoDto> mockAssetInfoDtoList,
                                                      MockPtlPortfolioDto mockPtlPortfolioDto,
                                                      List<MockPtlSecValuationDto> mockPtlSecValuationDtoList,
                                                      List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList,
                                                      List<MockPtlFeePositionDto> mockPtlFeePositionDtoList,
                                                      List<MockTrdProductDealDto> assetTradeList,
                                                      List<MockTrdProductDealDto> liabilitiesTradeList,
                                                      List<MockTrdProductDealDto> feeTradeList,
                                                      List<MockTrdProductDealDto> assetTdayPlusNdayTradeList,
                                                      List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList,
                                                      Map<String, BigDecimal> mockPtlCashPositionMap,
                                                      String mockStartDate, String mockEndDate,
                                                      String baseDate,
                                                      List<MockMstExchargeQuoteDto> mstExchargeQuoteList,
                                                      BigDecimal financingCost,
                                                      BigDecimal reinvestmentRate,
                                                      BigDecimal compositeRate,
                                                      Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> T_N资产变化,
                                                      Map<MockCashChangingKey, BigDecimal> T_N现金变化,
                                                      Map<String, LiabilitiesChanging> T_N负债变化) {
        // 参数若为null，初始化
        if (mockAssetInfoDtoList == null) {
            mockAssetInfoDtoList = new ArrayList<>();
        }
        if (mockPtlSecValuationDtoList == null) {
            mockPtlSecValuationDtoList = new ArrayList<>();
        }
        if (mockPtlPortfolioValuationDtoList == null) {
            mockPtlPortfolioValuationDtoList = new ArrayList<>();
        }
        if (mockPtlFeePositionDtoList == null) {
            mockPtlFeePositionDtoList = new ArrayList<>();
        }
        if (assetTradeList == null) {
            assetTradeList = new ArrayList<>();
        }
        if (liabilitiesTradeList == null) {
            liabilitiesTradeList = new ArrayList<>();
        }
        if (feeTradeList == null) {
            feeTradeList = new ArrayList<>();
        }
        if (assetTdayPlusNdayTradeList == null) {
            assetTdayPlusNdayTradeList = new ArrayList<>();
        }
        if (liabilitiesTdayPlusNdayTradeList == null) {
            liabilitiesTdayPlusNdayTradeList = new ArrayList<>();
        }
        if (mockPtlCashPositionMap == null) {
            mockPtlCashPositionMap = new HashMap<>();
        }
        if (mstExchargeQuoteList == null) {
            mstExchargeQuoteList = new ArrayList<>();
        }
        if (T_N资产变化 == null) {
            T_N资产变化 = new HashMap<>();
        }
        if (T_N现金变化 == null) {
            T_N现金变化 = new HashMap<>();
        }
        if (T_N负债变化 == null) {
            T_N负债变化 = new HashMap<>();
        }
        
        // 为保证入参的原始完整性 不直接对入参操作
        List<MockPtlSecValuationDto> 组合资产估值列表 = new ArrayList<MockPtlSecValuationDto>();
        组合资产估值列表.addAll(mockPtlSecValuationDtoList);
        List<MockPtlPortfolioValuationDto> 组合估值列表 = new ArrayList<MockPtlPortfolioValuationDto>();
        组合估值列表.addAll(mockPtlPortfolioValuationDtoList);
        List<MockPtlFeePositionDto> 组合费用头寸扩展列表 = new ArrayList<MockPtlFeePositionDto>();
        组合费用头寸扩展列表.addAll(mockPtlFeePositionDtoList);
        List<MockTrdProductDealDto> 资产端交易列表 = new ArrayList<MockTrdProductDealDto>();
        资产端交易列表.addAll(assetTradeList);
        List<MockTrdProductDealDto> t_N资产端交易列表 = new ArrayList<MockTrdProductDealDto>();
        t_N资产端交易列表.addAll(assetTdayPlusNdayTradeList);

        MockCalcAssetValuationResult result = RealTimeValMainService.execute(mockAssetInfoDtoList, mockPtlPortfolioDto, 组合资产估值列表,
            组合估值列表, 组合费用头寸扩展列表, 资产端交易列表, liabilitiesTradeList, feeTradeList, t_N资产端交易列表,
            liabilitiesTdayPlusNdayTradeList, mockPtlCashPositionMap, mockStartDate, mockEndDate,
            baseDate, mstExchargeQuoteList, financingCost, reinvestmentRate, compositeRate,
            T_N资产变化, T_N现金变化, T_N负债变化);

        // 变量.处理后组合估值DTO列表.add（入参.组合估值列表） 并按日期升序排序
        List<MockPtlPortfolioValuationDto> 最终结果估值列表 = new ArrayList<>();
//        最终结果估值列表.addAll(mockPtlPortfolioValuationDtoList);
        最终结果估值列表.addAll(result.getMockPtlPortfolioValuationDtoList());
        result.setMockPtlPortfolioValuationDtoList(最终结果估值列表);
        
        return result;
    }

}
