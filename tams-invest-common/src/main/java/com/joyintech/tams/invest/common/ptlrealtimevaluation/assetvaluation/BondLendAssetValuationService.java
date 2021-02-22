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

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
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

import java.math.BigDecimal;
import java.util.*;

/**
 * 债券借贷估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see BondLendAssetValuationService
 * @since 1.0
 */
public class BondLendAssetValuationService extends BaseAssetValuationService {

    /**
     * @param 资产基本信息
     * @param 投资目的
     * @param 组合基本信息dto
     * @param 组合资产估值
     * @param 资产端交易列表
     * @param 资产端t_N交易列表
     * @param t_N单资产变化
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

            try{
                组合资产估值.setCdate(DateUtil.parse(测算日期));
                资产变化处理T_N(t_N资产变化_内部, 测算日期, 组合资产估值);

                List<MockTrdProductDealDto> 存续期交易列表 = generate交易类标的存续期交易(测算日期,资产基本信息,组合资产估值,资产端交易map.get(测算日期));

                交易处理(存续期交易列表, 资产端交易map.get(测算日期), 组合资产估值,
                        基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 交易排序规则, 测算日期);
                计提(资产基本信息, 组合资产估值, 测算日期);
    //            摊销();
    //            公允价值变动();
                资产价值计算(组合资产估值, 组合基本信息dto.getCcy(), 汇率行情列表);
                MockPtlSecValuationDto 组合资产估值_处理后 = new MockPtlSecValuationDto();
                BeanUtil.copyPropertiesIgnoreNull(组合资产估值_处理后, 组合资产估值);
                mockPtlSecValuationDtoList.add(组合资产估值_处理后);

                测算日期 = DateUtil.addDay(测算日期, 1);
            }catch (Exception e){
                getLogger().error(测算日期+"债券借贷估值测算报错==========",e);
                产品估值方法返回.setMockPtlSecValuationDtoList(mockPtlSecValuationDtoList);
                产品估值方法返回.setSingleAssetChanging(t_N资产变化_内部);
                产品估值方法返回.setCashChanging(t_N现金变化_内部);
                return 产品估值方法返回;
            }
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
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部, Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                            List<BokTradeOrder> 交易排序规则, String 测算日期) {

        //按照交易方向排序 
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
        List<MockTrdProductDealDto> 存续期交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(存续期交易列表,
                交易排序规则, DictCodeList.业务类型_债券借贷交易.getValue()).getAssetTradeList();

        String 现金类型 = RealTimeValCommonUtil.现金类型_融资;
        if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_资产)) {
            现金类型 = RealTimeValCommonUtil.现金类型_再投;
        }
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();

        //存续期处理
        for (MockTrdProductDealDto mockTrdProductDealDto : 存续期交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())) {
                组合资产估值.setShareAmt(BigDecimal.ZERO);
                组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                组合资产估值.setDscCostAmt(BigDecimal.ZERO);
                组合资产估值.setAccrualCost(BigDecimal.ZERO);
                组合资产估值.setTdyIntincexp(BigDecimal.ZERO);
                组合资产估值.setAccrualInt(BigDecimal.ZERO);
            }
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, DictCodeList.是否_否.getValue(),
                    new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                    mockTrdProductDealDto.getTradeAmt());
        }
        //交易处理
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
                交易排序规则, DictCodeList.业务类型_债券借贷交易.getValue()).getAssetTradeList();
        for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
//                组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getPrinAmt()));
                组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getIntAmt()));
                组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), mockTrdProductDealDto.getPrinAmt()));
                组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
                组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getIntAmt()));

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {

                    组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),
                            mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }
                BigDecimal 交易金额 = mockTrdProductDealDto.getTradeAmt();
                if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_资产)) {
                    交易金额 = mockTrdProductDealDto.getTradeAmt().negate();
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, 组合资产估值.getCcy()),
                        交易金额);
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_卖出.getValue())) {

                BigDecimal 成本 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                        ArithmeticUtil.divide(组合资产估值.getAccrualCost(), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
                BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                        ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));

//                组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getPrinAmt()));
                组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(),
                        mockTrdProductDealDto.getShareAmt()));

                BigDecimal 利息总额 = mockTrdProductDealDto.getIntAmt();

                if (!StringUtil.isNotEmpty(组合资产估值.getAccrualAmt()) ||
                        组合资产估值.getAccrualAmt().compareTo(BigDecimal.ZERO) == 0) {
                    利息总额 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            ArithmeticUtil.divide(组合资产估值.getAccrualInt(), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
                }
                组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), 利息总额));
                组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(),
                        mockTrdProductDealDto.getShareAmt()));
                组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 成本));
                组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), 利息总额));

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(),
                            mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_提前终止.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue())) {
                组合资产估值.setDscCostAmt(BigDecimal.ZERO);
                组合资产估值.setShareAmt(BigDecimal.ZERO);
                组合资产估值.setTdyIntincexp(BigDecimal.ZERO);

                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(组合资产估值.getAccrualAmt().negate());
                    资产变动信息.setAccrualCost(组合资产估值.getAccrualCost().negate());
                    资产变动信息.setAccrualInterest(组合资产估值.getAccrualInt().negate());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                    组合资产估值.setAccrualCost(BigDecimal.ZERO);
                    组合资产估值.setAccrualInt(BigDecimal.ZERO);
                }

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
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
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();
        Date 开始日 = finTradeProduct.getVdate();
        Date 结束日 = finTradeProduct.getMdate();

        if (DateUtil.compareDate(开始日, DateUtil.parse(测算日期)) <= 0
                && DateUtil.compareDate(结束日, DateUtil.parse(测算日期)) >= 0) {
            BigDecimal 折算后到期本息和 = ArithmeticUtil.divide(ArithmeticUtil.multiply(组合资产估值.getAccrualAmt(), finTradeProduct.getmTradeAmt()),
                    finTradeProduct.getPrin(), RealTimeValCommonUtil.AMOUNT_SCALE);
            BigDecimal 利息总额 = ArithmeticUtil.subtract(折算后到期本息和,  组合资产估值.getShareAmt()//组合资产估值.getAccrualCost()
            );

            Long 计提天数 = DateUtil.getBetweenDayNumber(开始日,结束日);

            if (Long.valueOf(DateUtil.getBetweenDayNumber(结束日, DateUtil.parse(测算日期))).
                    equals(new Long(1))) {
                组合资产估值.setTdyIntincexp(利息总额);
                组合资产估值.setAccrualInt(利息总额);
            } else {
                BigDecimal 当日利息 = ArithmeticUtil.divide(利息总额, 计提天数.intValue(), RealTimeValCommonUtil.AMOUNT_SCALE);
                组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), 当日利息));
                组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), 当日利息));
            }
        }
    }
}
