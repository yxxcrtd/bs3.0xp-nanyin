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
import java.util.Arrays;
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

/**
 * 期权类资产估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see OptionAssetValuationService
 * @since 1.0
 */
public class OptionAssetValuationService extends BaseAssetValuationService {

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

            List<MockTrdProductDealDto> 存续期交易列表 = null;
            交易处理(存续期交易列表, 资产端交易map.get(测算日期), 测算日期, 组合资产估值,
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
    *
    * T+N交易处理
    *
    * @param 资产端t_N交易列表
    * @param 基准日期
    * @param 资产基本信息
    * @param t_N单资产变化
    * @param t_N现金变化
    * @param 摊销重算map
    * @see
    * @since 1.0
    */
   public static void 交易处理T_N(List<MockTrdProductDealDto> 资产端t_N交易列表, String 基准日期, MockAssetInfoDto 资产基本信息,
                                  Map<String, MockSingleAssetChanging> t_N单资产变化, Map<MockCashChangingKey, BigDecimal> t_N现金变化,
                                  Map<Date, Boolean> 摊销重算map) {
       // 需要看外汇交易方向
       FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();
       
       资产端t_N交易列表.forEach(entity -> {
           // 交易方向 in（"收息到账","还本到账","到期","卖出","提前终止","收取","逾期返售"，"到期行权","提前行权","放弃行权"）
           List<String> 交易方向List = Arrays.asList(DictCodeList.交易方向_收息到账.getValue(), DictCodeList.交易方向_还本到账.getValue(),
                   DictCodeList.交易方向_到期.getValue(), DictCodeList.交易方向_卖出.getValue(), DictCodeList.交易方向_提前终止.getValue(),
                   DictCodeList.交易方向_收取.getValue(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue(),
                   DictCodeListExt1.交易方向_到期行权.getValue(), DictCodeListExt1.交易方向_提前行权.getValue(),
                   DictCodeListExt1.交易方向_放弃行权.getValue());
           if (交易方向List.contains(entity.getPs()) || StringUtil.equals(finTradeProduct.getExcPs(), DictCodeList.交易方向_卖出.getValue())) {
               MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
               
               if (StringUtil.equals(DictCodeListExt1.交易方向_到期行权.getValue(), entity.getPs())
                   || StringUtil.equals(DictCodeListExt1.交易方向_提前行权.getValue(), entity.getPs())) {
                   // 数据库中收取的交易 状态存值为 01
                   if (StringUtil.equals(entity.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())||
                       StringUtil.equals(entity.getPsPayment(), "01")) {
                       资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt().negate());
                   }
                   else {
                       资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt());
                   }
               }
               else {
                   资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt().negate());
               }
               
               RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化, DateUtil.format(entity.getSettleDate()), 资产变动信息);
               RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, entity.getCashProcessedOrNot(),
                       new MockCashChangingKey(entity.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, entity.getCcy()),
                       entity.getTradeAmt());
           } else {
               MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
               资产变动信息.setDelayPayAmt(entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt());
               RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化, DateUtil.format(entity.getSettleDate()), 资产变动信息);
               RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, entity.getCashProcessedOrNot(),
                       new MockCashChangingKey(entity.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, entity.getCcy()),
                       entity.getTradeAmt() == null ? BigDecimal.ZERO : entity.getTradeAmt().negate());
           }
       });
   }

    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表,
                            String 测算日期,
                            MockPtlSecValuationDto 组合资产估值, String 基准日期, MockAssetInfoDto 资产基本信息,
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                            Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部, List<BokTradeOrder> 交易排序规则) {
        
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();
        //按照交易方向排序  
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
                交易排序规则, DictCodeListExt3.业务类型_场外期权.getValue()).getAssetTradeList();
        for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {

            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                BigDecimal 交易本金 = mockTrdProductDealDto.getFpriceAmt();
                BigDecimal 交易金额 = mockTrdProductDealDto.getTradeAmt();

                //外汇交易方向
                if (StringUtil.equals(finTradeProduct.getExcPs(), DictCodeList.交易方向_卖出.getValue())) {
                    交易本金 = mockTrdProductDealDto.getFpriceAmt().negate();
                    交易金额 = mockTrdProductDealDto.getTradeAmt().negate();
                }
                组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), 交易本金));

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {

                    组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),
                            交易金额));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt().negate());
            }

            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_到期行权.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_提前行权.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt1.交易方向_放弃行权.getValue())) {
                组合资产估值.setDscCostAmt(new BigDecimal(0));
                组合资产估值.setTdyFloatIngpl(new BigDecimal(0));
                BigDecimal 金额 = mockTrdProductDealDto.getTradeAmt().negate();
                // 数据库中收取的交易 状态存值为 01
                if (StringUtil.equals(mockTrdProductDealDto.getPsPayment(), DictCodeListExt3.收付方向_收取.getValue())||
                        StringUtil.equals(mockTrdProductDealDto.getPsPayment(), "01")) {
                    金额 = mockTrdProductDealDto.getTradeAmt();
                }
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {

                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(),
                            金额));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        金额);

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
    }

}
