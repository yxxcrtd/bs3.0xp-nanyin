/*
 * 文件名：BondAssetValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.ptlrealtimevaluation.assetvaluation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;

/**
 * 外汇远期资产估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see WtForwexAssetValuationService
 * @since 1.0
 */
public class WtForwexAssetValuationService extends BaseAssetValuationService {

    /**
     * @param 资产基本信息
     * @param 投资目的
     * @param 组合基本信息dto
     * @param 组合资产估值
     * @param 资产端交易列表
     * @param 资产端t_N交易列表
     * @param 测算开始日
     * @param 测算结束日
     * @param 基准日期
     * @param 汇率行情列表
     * @param t_N现金变化_内部
     * @param 摊销重算map
     * @param 行情map
     * @param 现金流计划表map
     * @param T_N计划
     * @param 资产端交易map
     * @param 交易排序规则
     * @return 产品估值方法返回
     */
    public static MockCalcAssetValuationResult assetValuationProcess(MockAssetInfoDto 资产基本信息, String 投资目的, MockPtlPortfolioDto 组合基本信息dto,
                                                                     MockPtlSecValuationDto 组合资产估值, List<MockTrdProductDealDto> 资产端交易列表,
                                                                     List<MockTrdProductDealDto> 资产端t_N交易列表,
                                                                     String 测算开始日, String 测算结束日, String 基准日期,
                                                                     List<MockMstExchargeQuoteDto> 汇率行情列表, Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                                                                     Map<String, MockSingleAssetChanging> t_N资产变化_内部, Map<Date, Boolean> 摊销重算map,
                                                                     Map<String, MockQuotationInfoDto> 行情map,
                                                                     Map<String, List<MockFinCashPlanDto>> 现金流计划表map,
                                                                     List<MockFinCashPlanDto> T_N计划,
                                                                     Map<String, List<MockTrdProductDealDto>> 资产端交易map, List<BokTradeOrder> 交易排序规则) {

        MockCalcAssetValuationResult 产品估值方法返回 = new MockCalcAssetValuationResult();
        List<MockPtlSecValuationDto> mockPtlSecValuationDtoList = new ArrayList<>();

        String 测算日期 = 测算开始日;

        while (DateUtil.compareDate(测算日期, 测算结束日) <= 0) {
            组合资产估值.setCdate(DateUtil.parse(测算日期));
            资产变化处理T_N(t_N资产变化_内部, 测算日期, 组合资产估值);

            List<MockTrdProductDealDto> 存续期交易列表 = null;
            交易处理(存续期交易列表, 资产端交易map.get(测算日期), 组合资产估值,
                    基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 交易排序规则);
//            计提();
//            摊销();
            公允价值变动(组合资产估值, 资产基本信息, 测算日期);
            资产价值计算(组合资产估值, 组合基本信息dto.getCcy(), 汇率行情列表);
            MockPtlSecValuationDto 组合资产估值_处理后 = new MockPtlSecValuationDto();
            BeanUtil.copyPropertiesIgnoreNull(组合资产估值_处理后, 组合资产估值);
            mockPtlSecValuationDtoList.add(组合资产估值_处理后);

            测算日期 = DateUtil.addDay(测算日期, 1);
        }
        产品估值方法返回.setMockPtlSecValuationDtoList(mockPtlSecValuationDtoList);
        产品估值方法返回.setSingleAssetChanging(t_N资产变化_内部);
        产品估值方法返回.setCashChanging(t_N现金变化_内部);
        return 产品估值方法返回;
    }

    /**
     * 交易处理
     *
     * @param 存续期交易列表
     * @param 资产端交易列表
     * @param 组合资产估值
     * @param 基准日期
     * @param 资产基本信息
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     * @param 交易排序规则
     */
    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表,
                            MockPtlSecValuationDto 组合资产估值, String 基准日期, MockAssetInfoDto 资产基本信息,
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                            Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部, List<BokTradeOrder> 交易排序规则) {
        //按照交易方向排序 
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
                交易排序规则, DictCodeList.业务类型_外汇远期交易.getValue()).getAssetTradeList();

        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();

        //循环资产端交易列表
        for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                
                组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                //外汇交易方向
                BigDecimal 交易本金 = mockTrdProductDealDto.getPrinAmt();
//                BigDecimal 交易金额 = mockTrdProductDealDto.getTradeAmt();
                BigDecimal 交易金额 = mockTrdProductDealDto.getFpriceAmt();
                if (StringUtil.equals(finTradeProduct.getExcPs(), DictCodeList.交易方向_卖出.getValue())) {
                    交易本金 = mockTrdProductDealDto.getPrinAmt().negate();
//                    交易金额 = mockTrdProductDealDto.getTradeAmt().negate();
                    交易金额 = mockTrdProductDealDto.getFpriceAmt().negate();
                }
//                组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), 交易本金));
//                组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(), 交易金额));
//                组合资产估值.setDelayPayAmtCcy(mockTrdProductDealDto.getCcy());
                组合资产估值.setShareAmt(BigDecimal.ZERO);
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())) {
                //外汇交易方向
                BigDecimal 交易本金 = mockTrdProductDealDto.getPrinAmt();
//                BigDecimal 交易金额 = mockTrdProductDealDto.getTradeAmt();
                BigDecimal 交易金额 = mockTrdProductDealDto.getFpriceAmt();
                if (StringUtil.equals(finTradeProduct.getExcPs(), DictCodeList.交易方向_卖出.getValue())) {
                    交易本金 = mockTrdProductDealDto.getPrinAmt().negate();
//                    交易金额 = mockTrdProductDealDto.getTradeAmt().negate();
                    交易金额 = mockTrdProductDealDto.getFpriceAmt().negate();
                }
//                组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), 交易本金));
//                组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), 交易金额));
//                组合资产估值.setDelayPayAmtCcy(mockTrdProductDealDto.getCcy());
                组合资产估值.setShareAmt(BigDecimal.ZERO);

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, 组合资产估值.getCcy()),
                        交易本金);

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        交易金额.negate());
            }

        }
    }

    /**
     * 公允价值变动
     *
     * @param 组合资产估值
     * @param 资产基本信息
     * @param 日期
     */
    public static void 公允价值变动(MockPtlSecValuationDto 组合资产估值, MockAssetInfoDto 资产基本信息, String 日期) {
        BigDecimal 市价 = null;
        for (MockQuotationInfoDto x : 资产基本信息.getMockQuotationInfoDtoList()) {
            if (DateUtil.compareDate(x.getQuotationDate(), DateUtil.parse(日期)) == 0) {
                市价 = x.getMarketPrice();
            }
        }
        if (StringUtil.isNotEmpty(市价)) {
            组合资产估值.setTdyFloatIngpl(市价);
        }
        if (!StringUtil.isNotEmpty(组合资产估值.getDscCostAmt()) ||
                BigDecimal.ZERO.compareTo(组合资产估值.getDscCostAmt()) == 0) {
            组合资产估值.setTdyFloatIngpl(BigDecimal.ZERO);
        }
    }
}
