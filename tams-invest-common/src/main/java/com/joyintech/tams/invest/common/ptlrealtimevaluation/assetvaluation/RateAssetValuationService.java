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

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
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
 * 利率互换资产估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see RateAssetValuationService
 * @since 1.0
 */
public class RateAssetValuationService extends BaseAssetValuationService {

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

        // 测算开始日
//        计划处理T_N(资产端t_N交易列表, T_N计划, 资产基本信息, 组合资产估值, 投资目的);
//        交易处理T_N(资产端t_N交易列表, 基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 摊销重算map);

        String 测算日期 = 测算开始日;

        while (DateUtil.compareDate(测算日期, 测算结束日) <= 0) {
            组合资产估值.setCdate(DateUtil.parse(测算日期));
            资产变化处理T_N(t_N资产变化_内部, 测算日期, 组合资产估值);

//            List<MockTrdProductDealDto> 存续期交易列表 = 存续期处理(现金流计划表map.get(测算日期), 测算日期, 资产基本信息, 组合资产估值, 投资目的,
//                    资产基本信息.getFinTradeProduct().getCcy());

            交易处理(new ArrayList<>(), 资产端交易map.get(测算日期), 测算日期, 组合资产估值,
                    基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部,交易排序规则);
            计提(资产基本信息, 组合资产估值, 测算日期);
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
     * @param 测算日期
     * @param 组合资产估值
     * @param 基准日期
     * @param 资产基本信息
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     */
    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表,
                            String 测算日期, MockPtlSecValuationDto 组合资产估值, String 基准日期, MockAssetInfoDto 资产基本信息,
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                            Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部, List<BokTradeOrder> 交易排序规则) {
        //按照交易方向排序
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
        List<MockTrdProductDealDto> 存续期交易列表_排序 =  RealTimeValCommonUtil.sortTrdDealDtoByPs(存续期交易列表,
                交易排序规则, DictCodeList.业务类型_利率互换交易.getValue()).getAssetTradeList();

        //存续期处理 
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();

        for (MockTrdProductDealDto mockTrdProductDealDto : 存续期交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收取.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_支付.getValue())) {
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) == 0) {
                    if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收取.getValue())) {
                        组合资产估值.setInTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getInTdyIntincexp(),
                                mockTrdProductDealDto.getIntAmt()));

                    } else {
                        组合资产估值.setOutTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getOutTdyIntincexp(),
                                mockTrdProductDealDto.getIntAmt()));
                    }
                } else {
                    MockSingleAssetChanging 资产变动 = new MockSingleAssetChanging();
                    if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收取.getValue())) {
                        资产变动.setInTdyIntincexp(ArithmeticUtil.subtract(资产变动.getInTdyIntincexp(),
                                mockTrdProductDealDto.getIntAmt()));

                    } else {
                        资产变动.setOutTdyIntincexp(ArithmeticUtil.subtract(资产变动.getOutTdyIntincexp(),
                                mockTrdProductDealDto.getIntAmt()));
                    }
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getPayDate()),
                            资产变动);
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, DictCodeList.是否_否.getValue(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收取.getValue()) ?
                                mockTrdProductDealDto.getTradeAmt() : mockTrdProductDealDto.getTradeAmt().negate());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())) {
                组合资产估值.setTdyFloatIngpl(new BigDecimal(0));
                组合资产估值.setShareAmt(new BigDecimal(0));
            }
        }

        //交易处理
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
                交易排序规则, DictCodeList.业务类型_利率互换交易.getValue()).getAssetTradeList();
        for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收取.getValue())) {
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) == 0) {
                    组合资产估值.setInTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getInTdyIntincexp(),
                            mockTrdProductDealDto.getIntAmt()));
                } else {
                    MockSingleAssetChanging 资产变动 = new MockSingleAssetChanging();
                    资产变动.setInTdyIntincexp(ArithmeticUtil.subtract(资产变动.getInTdyIntincexp(),
                            mockTrdProductDealDto.getIntAmt()));
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getPayDate()),
                            资产变动);
                }
                if (mockTrdProductDealDto.getShareAmt()!=null
                    &&new BigDecimal(0).compareTo(mockTrdProductDealDto.getShareAmt()) != 0) {
                    BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
                    组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(组合资产估值.getTdyFloatIngpl(), 公允价值变动));
                    组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }

            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_支付.getValue())) {
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) == 0) {
                    组合资产估值.setOutTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getOutTdyIntincexp(),
                            mockTrdProductDealDto.getIntAmt()));
                } else {
                    MockSingleAssetChanging 资产变动 = new MockSingleAssetChanging();
                    资产变动.setOutTdyIntincexp(ArithmeticUtil.subtract(资产变动.getOutTdyIntincexp(),
                            mockTrdProductDealDto.getIntAmt()));
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getPayDate()),
                            资产变动);
                }
                if (mockTrdProductDealDto.getShareAmt()!=null&&new BigDecimal(0).compareTo(mockTrdProductDealDto.getShareAmt()) != 0) {
                    BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
                    组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(组合资产估值.getTdyFloatIngpl(), 公允价值变动));
                    组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt().negate());
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
        
        Date 测算日期Date = DateUtil.parse(测算日期);
        if (DateUtil.compareDate(finTradeProduct.getVdate(), 测算日期Date) <= 0
            && DateUtil.compareDate(finTradeProduct.getMdate(), 测算日期Date) > 0) {
            //名义本金
            BigDecimal 数量 = finTradeProduct.getPrin();
            List<MockFinCashPlanDto> 收取FinCashInfoList = 资产基本信息.getMockFinCashPlanDtoList().stream().filter(
                    x-> StringUtil.equals(x.getFinCashInfo().getCashType2(), DictCodeListExt2.现金流二级类型_收取.getValue())
            ).collect(Collectors.toList());

            if(收取FinCashInfoList!=null&&收取FinCashInfoList.size()>0&&收取FinCashInfoList.get(0)!=null&&收取FinCashInfoList.get(0).getFinCashInfo()!=null){
                String 收取CashId = 收取FinCashInfoList.get(0).getFinCashInfo().getCashId();
                List<FinCashCalcRule> list = 资产基本信息.getFinCashCalcRuleList().stream().filter(
                        x -> DateUtil.compareDate(x.getEffDate(), DateUtil.parse(测算日期)) <= 0
                                && StringUtil.equals(x.getCashId(), 收取CashId)
                ).collect(Collectors.toList());
                int 天数 = 0;
                if (!StringUtil.isEmptyList(list)) {
                    FinCashCalcRule 收取端计算规则 = list.stream().max(Comparator.comparing(FinCashCalcRule::getEffDate)).get();
                    //根据计息基础找到对应的天数
                    天数 = RealTimeValCommonUtil.calcInterestBasisDays(new InterestBasisDaysParameter(收取端计算规则.getBasis()));
                    BigDecimal 收取端当日利息 = ArithmeticUtil.divide(ArithmeticUtil.multiply(数量, 收取端计算规则.getYield()),
                            天数, RealTimeValCommonUtil.AMOUNT_SCALE);
                    组合资产估值.setInTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getInTdyIntincexp(), 收取端当日利息));
                }
            }

            List<MockFinCashPlanDto> 支付FinCashInfoList = 资产基本信息.getMockFinCashPlanDtoList().stream().filter(
                    x-> StringUtil.equals(x.getFinCashInfo().getCashType2(), DictCodeListExt2.现金流二级类型_支付.getValue())
            ).collect(Collectors.toList());
            if(支付FinCashInfoList!=null&&支付FinCashInfoList.size()>0&&支付FinCashInfoList.get(0)!=null&&支付FinCashInfoList.get(0).getFinCashInfo()!=null){
                String 支付CashId = 支付FinCashInfoList.get(0).getFinCashInfo().getCashId();
                List<FinCashCalcRule> list = 资产基本信息.getFinCashCalcRuleList().stream().filter(
                        x -> DateUtil.compareDate(x.getEffDate(), DateUtil.parse(测算日期)) <= 0
                                && StringUtil.equals(x.getCashId(), 支付CashId)
                ).collect(Collectors.toList());
                if (!StringUtil.isEmptyList(list)) {
                    FinCashCalcRule 支付端计算规则 = list.stream().max(Comparator.comparing(FinCashCalcRule::getEffDate)).get();
                    int 天数 = RealTimeValCommonUtil.calcInterestBasisDays(new InterestBasisDaysParameter(支付端计算规则.getBasis()));
                    //根据计息基础找到对应的天数
                    BigDecimal 支付端当日利息 = ArithmeticUtil.divide(ArithmeticUtil.multiply(数量, 支付端计算规则.getYield()),
                            天数, RealTimeValCommonUtil.AMOUNT_SCALE);
                    组合资产估值.setOutTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getOutTdyIntincexp(), 支付端当日利息));
                }
            }

            组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getInTdyIntincexp(),组合资产估值.getOutTdyIntincexp()));
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

    /**
     * 存续期处理
     *
     * @param 现金流计划表
     * @param 测算日期
     * @param 资产基本信息
     * @param 组合资产估值
     * @param 投资目的
     * @param 币种

     * @return
     */
    public static List<MockTrdProductDealDto> 存续期处理(List<MockFinCashPlanDto> 现金流计划表, String 测算日期, MockAssetInfoDto 资产基本信息
            , MockPtlSecValuationDto 组合资产估值, String 投资目的, String 币种) {
        // 调用存续期交易生成
        List<MockTrdProductDealDto> 存续期交易列表 = new ArrayList<>();
        //遍历现金流计划
        if (!StringUtil.isEmptyList(现金流计划表)) {
            for (MockFinCashPlanDto mockFinCashPlanDto : 现金流计划表) {
                if (StringUtil.equals(DictCodeList.是否_否.getValue(), mockFinCashPlanDto.getPaidOrNot())) {
                    存续期交易列表.add(生成收息到账_还本到账交易(
                        mockFinCashPlanDto, 币种,组合资产估值, 资产基本信息));
                }
            }
        }
        if (DateUtil.compareDate(资产基本信息.getFinTradeProduct().getMdate(), DateUtil.parse(测算日期)) == 0) {
            存续期交易列表.add(生成到期交易(币种, 资产基本信息, 投资目的));
        }
        return 存续期交易列表;
    }

    /**
     * 生成到期交易
     *
     * @param 币种
     * @param 资产基本信息
     * @param 投资目的
     * @return
     */
    private static MockTrdProductDealDto 生成到期交易(String 币种, MockAssetInfoDto 资产基本信息, String 投资目的) {

        MockTrdProductDealDto mockTrdProductDealDto = new MockTrdProductDealDto();
        mockTrdProductDealDto.setTradeId(IDGenerator.generate(32));
        mockTrdProductDealDto.setFinprodId(资产基本信息.getFinTradeProduct().getFinprodId());
        mockTrdProductDealDto.setFinprodType2(资产基本信息.getFinTradeProduct().getFinprodType2());
        mockTrdProductDealDto.setTradeDate(资产基本信息.getFinTradeProduct().getMdate());
        mockTrdProductDealDto.setSettleDate(资产基本信息.getFinTradeProduct().getMdate());
        mockTrdProductDealDto.setCcy(币种);
        mockTrdProductDealDto.setInvAim(投资目的);
        mockTrdProductDealDto.setPs(DictCodeList.交易方向_到期.getValue());
        mockTrdProductDealDto.setIsClean(DictCodeList.是否_是.getValue());
        return mockTrdProductDealDto;
    }

    /**
     * 生成收息到账_还本到账交易
     *
     * @param 现金流计划拓展
     * @param 币种
     * @param 资产基本信息
     * @return
     */
    private static MockTrdProductDealDto 生成收息到账_还本到账交易(MockFinCashPlanDto 现金流计划拓展, String 币种, MockPtlSecValuationDto 组合资产估值,
                                                       MockAssetInfoDto 资产基本信息) {
        MockTrdProductDealDto mockTrdProductDealDto = new MockTrdProductDealDto();
        FinCashPlan 现金流计划 = 现金流计划拓展.getFinCashPlan();
        mockTrdProductDealDto.setTradeId(IDGenerator.generate(32));
        mockTrdProductDealDto.setFinprodId(资产基本信息.getFinTradeProduct().getFinprodId());
        mockTrdProductDealDto.setFinprodType2(资产基本信息.getFinTradeProduct().getFinprodType2());
        mockTrdProductDealDto.setBranch(Long.parseLong(DateUtil.format(现金流计划.getMdate(),"yyyyMMdd") + "02"));
        mockTrdProductDealDto.setVdate(现金流计划.getVdate());
        mockTrdProductDealDto.setMdate(现金流计划.getMdate());
        mockTrdProductDealDto.setTradeDate(现金流计划.getMdate());
        mockTrdProductDealDto.setSettleDate(现金流计划.getPayDate());
        BigDecimal 当日应计利息 = BigDecimal.ZERO;
        if(StringUtil.equals(现金流计划拓展.getFinCashInfo().getCashType2(),DictCodeListExt2.现金流二级类型_收取.getValue())){
            mockTrdProductDealDto.setPs(DictCodeList.交易方向_收取.getValue());
            当日应计利息 = 组合资产估值.getInTdyIntincexp();
        }
        if(StringUtil.equals(现金流计划拓展.getFinCashInfo().getCashType2(),DictCodeListExt2.现金流二级类型_支付.getValue())){
            mockTrdProductDealDto.setPs(DictCodeList.交易方向_支付.getValue());
            当日应计利息 = 组合资产估值.getOutTdyIntincexp();
        }
        mockTrdProductDealDto.setCcy(币种);
        mockTrdProductDealDto.setIntAmt(当日应计利息);
        mockTrdProductDealDto.setFpriceAmt(当日应计利息);
        mockTrdProductDealDto.setTradeAmt(当日应计利息);
        return mockTrdProductDealDto;
    }
}
