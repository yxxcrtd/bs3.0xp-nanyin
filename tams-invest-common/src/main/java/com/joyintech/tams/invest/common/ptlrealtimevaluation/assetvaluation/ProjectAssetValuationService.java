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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.InterestBasisDaysParameter;
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
 * 固收类资产估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see ProjectAssetValuationService
 * @since 1.0
 */
public class ProjectAssetValuationService extends BaseAssetValuationService {

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

        计划处理T_N(资产端t_N交易列表, T_N计划, 资产基本信息, 组合资产估值, 投资目的);
        交易处理T_N(资产端t_N交易列表, 基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 摊销重算map);

        String 测算日期 = 测算开始日;

        while (DateUtil.compareDate(测算日期, 测算结束日) <= 0) {
            组合资产估值.setCdate(DateUtil.parse(测算日期));
            资产变化处理T_N(t_N资产变化_内部, 测算日期, 组合资产估值);

            List<MockTrdProductDealDto> 存续期交易列表 = 存续期处理(现金流计划表map, 测算日期, 资产基本信息, 组合资产估值, 投资目的);
            交易处理(存续期交易列表, 资产端交易map.get(测算日期), 测算日期, 组合资产估值,
                    基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 交易排序规则);
            计提(资产基本信息, 组合资产估值, 测算日期);
//            摊销();
//            公允价值变动();
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

    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表,
                            String 测算日期,
                            MockPtlSecValuationDto 组合资产估值, String 基准日期, MockAssetInfoDto 资产基本信息,
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                            Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                            List<BokTradeOrder> 交易排序规则) {
//        //按照交易方向排序
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
//        List<MockTrdProductDealDto> 存续期交易列表_排序 = realTimeValCommonUtil.sortTrdDealDtoByPs(存续期交易列表,
//                交易排序规则, DictCodeListExt3.业务类型_债券交易.getValue()).getAssetTradeList();
        List<MockTrdProductDealDto> 存续期交易列表_排序 = RealTimeValCommonUtil
                .sortTrdDealDtoByPs(存续期交易列表, 交易排序规则, DictCodeListExt3.业务类型_债券交易.getValue()).getAssetTradeList();
//
        //存续期处理
        String  现金类型 = RealTimeValCommonUtil.现金类型_融资;
        if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_资产)) {
            现金类型 = RealTimeValCommonUtil.现金类型_再投;
        }
        FinProduct finProduct = 资产基本信息.getFinProduct();

        if (!StringUtil.isEmptyList(存续期交易列表_排序)) {
            for (MockTrdProductDealDto mockTrdProductDealDto : 存续期交易列表_排序) {
                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收息.getValue())) {
                    if (StringUtil.isNotEmpty(mockTrdProductDealDto.getUnitCprice())
                            && mockTrdProductDealDto.getUnitCprice().compareTo(BigDecimal.ZERO) > 0) {
                        组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getIntAmt()));
                        组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getIntAmt()));
                    } else {
                        组合资产估值.setTdyIntincexp(BigDecimal.ZERO);
                        组合资产估值.setAccrualInt(BigDecimal.ZERO);
                    }
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getFpriceAmt()));
                }
                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收息到账.getValue())) {
                    BigDecimal 冲减利息 = mockTrdProductDealDto.getTradeAmt();
//                    if (组合资产估值.getDelayPayAmt().compareTo(mockTrdProductDealDto.getTradeAmt()) > 0) {
//                        冲减利息 = mockTrdProductDealDto.getTradeAmt();
//                    } else {
//                        冲减利息 = 组合资产估值.getDelayPayAmt();
//                    }
//                    if (冲减利息.compareTo(BigDecimal.ZERO) < 0) {
//                        冲减利息 = BigDecimal.ZERO;
//                    }
                    if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                        MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                        资产变动信息.setDelayPayAmt(冲减利息.negate());
                        RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                    } else {
                        组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),冲减利息));
                    }
                }
                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_还本.getValue())) {

                    BigDecimal 摊销成本 = ArithmeticUtil.multiply(组合资产估值.getDscCostAmt(),
                            mockTrdProductDealDto.getUnitCprice());

                    组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), 摊销成本));
                    组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 摊销成本));
                    组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(),
                            ArithmeticUtil.multiply(组合资产估值.getShareAmt(), mockTrdProductDealDto.getUnitCprice())));
                    组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(), 摊销成本));
                    if (DateUtil.compareDate(DateUtil.parse(测算日期), finProduct.getMdate()) == 0) {
                        组合资产估值.setShareAmt(BigDecimal.ZERO);
                        组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                        组合资产估值.setAccrualCost(BigDecimal.ZERO);
                        组合资产估值.setDscCostAmt(BigDecimal.ZERO);
                    }
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getFpriceAmt()));
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_还本到账.getValue())) {
                    if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                        MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                        资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                        RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                    } else {
                        组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),mockTrdProductDealDto.getTradeAmt()));
                    }
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, DictCodeList.是否_否.getValue(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, 组合资产估值.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }
        }

        //交易处理
//        List<MockTrdProductDealDto> 资产端交易列表_排序 = realTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
//                交易排序规则, DictCodeListExt3.业务类型_债券交易.getValue()).getAssetTradeList();
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil
                .sortTrdDealDtoByPs(资产端交易列表, 交易排序规则, DictCodeListExt3.业务类型_债券交易.getValue()).getAssetTradeList();

        if (!StringUtil.isEmptyList(资产端交易列表_排序)) {
            for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {
                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收息到账.getValue())) {
                    BigDecimal 冲减利息 = mockTrdProductDealDto.getTradeAmt();
//                            冲减利息 = null;
//                    if (组合资产估值.getDelayPayAmt().compareTo(mockTrdProductDealDto.getTradeAmt()) > 0) {
//                        冲减利息 = mockTrdProductDealDto.getTradeAmt();
//                    } else {
//                        冲减利息 =组合资产估值.getDelayPayAmt()
//                    }
//                    if (冲减利息.compareTo(BigDecimal.ZERO) < 0) {
//                        冲减利息 = BigDecimal.ZERO;
//                    }
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),冲减利息));
                    RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                            new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                            mockTrdProductDealDto.getTradeAmt());
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_还本到账.getValue())) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),mockTrdProductDealDto.getTradeAmt()));
                    RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                            new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                            mockTrdProductDealDto.getTradeAmt());
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                    买入(mockTrdProductDealDto, 组合资产估值, 基准日期, t_N单资产变化_内部, t_N现金变化_内部, mockTrdProductDealDto.getSettleDate());
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_卖出.getValue())
                    ||StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_提前支取.getValue())) {
                    BigDecimal 成本 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            ArithmeticUtil.divide(组合资产估值.getAccrualCost(), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
                    BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));

                    组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), 成本));
                    组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(组合资产估值.getTdyFloatIngpl(), 公允价值变动));
                    组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(),
                            mockTrdProductDealDto.getShareAmt()));

                    BigDecimal 利息总额 = mockTrdProductDealDto.getIntAmt();
                    if (!StringUtil.isNotEmpty(利息总额) && 利息总额.compareTo(BigDecimal.ZERO) == 0) {
                        利息总额 = BigDecimal.ZERO;
                    }
                    组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), 利息总额));
                    组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 成本));
                    组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(),
                            mockTrdProductDealDto.getShareAmt()));
                    组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), 利息总额));

                    if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                        组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(),
                                mockTrdProductDealDto.getTradeAmt()));
                        MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                        资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                        RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                    }
                    RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                            new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                    RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                            mockTrdProductDealDto.getTradeAmt());
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_应收款项转出.getValue())) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doSub(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));// 清算款
                    RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                            new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                    RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                            mockTrdProductDealDto.getTradeAmt());
                }

                if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_应收款项转入.getValue())) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));// 清算款
                    RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                            new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                    RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                            mockTrdProductDealDto.getTradeAmt().negate());
                }
            }
        }

    }

    /**
     * 计提
     *
     * @param 资产基本信息
     * @param 组合资产估值
     * @param 测算日期
     */
    public static void 计提(MockAssetInfoDto 资产基本信息, MockPtlSecValuationDto 组合资产估值, String 测算日期) {
        //现金流计算规则
        if(资产基本信息.getMockFinCashPlanDtoList()==null){
            return;
        }
        List<FinCashInfo> finCashInfoList = 资产基本信息.getMockFinCashPlanDtoList().stream()
                .map(MockFinCashPlanDto::getFinCashInfo).collect(Collectors.toList());
        if(finCashInfoList==null){
            return;
        }
        List<String> cashIdList = finCashInfoList.stream()
                .map(FinCashInfo::getCashId).collect(Collectors.toList());
        if(cashIdList==null){
            return;
        }
        List<FinCashCalcRule> list = 资产基本信息.getFinCashCalcRuleList().stream().filter(x ->
                DateUtil.compareDate(x.getEffDate(), DateUtil.parse(测算日期)) <= 0
                && cashIdList.contains(x.getCashId())).collect(Collectors.toList());

        FinCashCalcRule  finCashCalcRule = list.stream().max(
                Comparator.comparing(FinCashCalcRule::getEffDate)).get();
        if(finCashCalcRule==null){
            return;
        }
        BigDecimal 最新利率 = finCashCalcRule.getYield();
        int 计算天数 = RealTimeValCommonUtil.calcInterestBasisDays(new InterestBasisDaysParameter(finCashCalcRule.getBasis()));
        BigDecimal 当日利息 = ArithmeticUtil.divide(ArithmeticUtil.multiply(组合资产估值.getAccrualAmt(),最新利率),
                计算天数, RealTimeValCommonUtil.AMOUNT_SCALE);

        组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), 当日利息));
        组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), 当日利息));
    }

}
