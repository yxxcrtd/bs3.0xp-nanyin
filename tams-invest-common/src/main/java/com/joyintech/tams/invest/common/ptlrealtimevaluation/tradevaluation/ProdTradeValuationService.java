/*
 * 文件名：BondAssetValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.ptlrealtimevaluation.tradevaluation;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.mock.portfolio.*;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * 产品交易 估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see ProdTradeValuationService
 * @since 1.0
 */
public class ProdTradeValuationService {

    /**
     * 产品交易处理
     *
     * @param 组合估值
     * @param 组合基本信息dto
     * @param 负债端交易列表
     * @param 负债端交易列表T_N
     * @param T_N负债变化
     * @param 测算开始日
     * @param 测算结束日
     * @param 基准日期
     * @return 产品估值方法返回值
     */
    public static MockCalcAssetValuationResult excuteTrade(MockPtlPortfolioValuationDto 组合估值, MockPtlPortfolioDto 组合基本信息dto,
                                                           List<MockTrdProductDealDto> 负债端交易列表,
                                                           List<MockTrdProductDealDto> 负债端交易列表T_N,
                                                           Map<String, LiabilitiesChanging> T_N负债变化,
                                                           String 测算开始日,
                                                           String 测算结束日,
                                                           String 基准日期) {
        List<MockPtlPortfolioValuationDto> 组合估值列表_处理后 = new ArrayList<>();
        //数据准备
        Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部 = new HashMap<>();
        Map<String, LiabilitiesChanging> T_N负债变化_内部 = new HashMap<>();
        BeanUtil.copyPropertiesIgnoreNull(T_N负债变化_内部, T_N负债变化);
        //T+N 交易处理
        产品T_N交易处理(负债端交易列表T_N, 组合估值, 基准日期, T_N负债变化_内部, t_N现金变化_内部);

        // 负债交易列表
        Map<String, List<MockTrdProductDealDto>> 负债端交易map = new HashMap<String, List<MockTrdProductDealDto>>();
        if (负债端交易map != null && 负债端交易列表 != null && !负债端交易列表.isEmpty()) {
            负债端交易列表.forEach(entity -> {
                if (entity != null && entity.getTradeDate() != null) {
                    String tradeDate = DateUtil.format(entity.getTradeDate());
                    if (负债端交易map.get(tradeDate) == null) {
                        List<MockTrdProductDealDto> 单日负债端交易列表 = new ArrayList<MockTrdProductDealDto>();
                        单日负债端交易列表.add(entity);
                        负债端交易map.put(tradeDate, 单日负债端交易列表);
                    } else {
                        负债端交易map.get(tradeDate).add(entity);
                    }
                }
            });
        }
        String 测算日期 = 测算开始日;
        MockPtlPortfolioValuationDto 组合估值_处理后 = new MockPtlPortfolioValuationDto();
        组合估值_处理后.setPortfolioId(组合估值.getPortfolioId());
        组合估值_处理后.setCcy(组合估值.getCcy());
        组合估值_处理后.setCdate(DateUtil.parse(测算日期));
        组合估值_处理后.setTdyShareAmt(组合估值.getTdyShareAmt());
        组合估值_处理后.setDelayPayAmt(组合估值.getDelayPayAmt());
        组合估值_处理后.setTdyUnpaidIntincexp(组合估值.getTdyUnpaidIntincexp());
        while (DateUtil.compareDate(测算日期, 测算结束日) <= 0) {
            //1.T+N负债变化处理
            LiabilitiesChanging 负债变动信息 = T_N负债变化_内部.get(测算日期);
            T_N负债变化_内部.remove(测算日期);
            if (StringUtil.isNotEmpty(负债变动信息)) {
                组合估值_处理后.setTdyShareAmt(ArithmeticUtil.doAdd(组合估值_处理后.getTdyShareAmt(), 负债变动信息.getActualCapital()));
                组合估值_处理后.setTdyUnpaidIntincexp(ArithmeticUtil.doAdd(组合估值_处理后.getTdyUnpaidIntincexp(), 负债变动信息.getCustomProfit()));
                组合估值_处理后.setDelayPayAmt(ArithmeticUtil.doAdd(组合估值_处理后.getDelayPayAmt(), 负债变动信息.getCashFund()));
            }

            //2.交易处理
            产品交易头寸处理(负债端交易map.get(测算日期), 组合估值_处理后, 基准日期, T_N负债变化_内部, t_N现金变化_内部);

            MockPtlPortfolioValuationDto 组合估值_处理后_copy = new MockPtlPortfolioValuationDto();
            BeanUtil.copyPropertiesIgnoreNull(组合估值_处理后_copy,组合估值_处理后);
            组合估值列表_处理后.add(组合估值_处理后_copy);

            测算日期 = DateUtil.addDay(测算日期, 1);
            组合估值_处理后.setCdate(DateUtil.parse(测算日期));
        }
        MockCalcAssetValuationResult mockCalcAssetValuationResult = new MockCalcAssetValuationResult();
        mockCalcAssetValuationResult.setMockPtlPortfolioValuationDtoList(组合估值列表_处理后);
        mockCalcAssetValuationResult.setCashChanging(t_N现金变化_内部);
        mockCalcAssetValuationResult.setLiabilitiesChanging(T_N负债变化_内部);
        return mockCalcAssetValuationResult;
    }

    /**
     * 产品交易头寸处理
     *
     * @param 负债端交易列表
     * @param 组合估值_处理后
     * @param 基准日期
     * @param t_n负债变化_内部
     * @param t_n现金变化_内部
     */
    private static void 产品交易头寸处理(List<MockTrdProductDealDto> 负债端交易列表,
                                 MockPtlPortfolioValuationDto 组合估值_处理后,
                                 String 基准日期, Map<String, LiabilitiesChanging> t_n负债变化_内部,
                                 Map<MockCashChangingKey, BigDecimal> t_n现金变化_内部) {
        //负债端交易列表循环
        if(负债端交易列表==null){
            return;
        }
        for (MockTrdProductDealDto mockTrdProductDealDto : 负债端交易列表) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品认购.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品申购.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_份额强增.getValue())) {
                组合估值_处理后.setTdyShareAmt(ArithmeticUtil.doAdd(组合估值_处理后.getTdyShareAmt(), mockTrdProductDealDto.getShareAmt()));
                
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) !=0) {
                    组合估值_处理后.setDelayPayAmt(ArithmeticUtil.doAdd(组合估值_处理后.getDelayPayAmt(),
                            mockTrdProductDealDto.getTradeAmt()));
                    LiabilitiesChanging 负债变动信息 = new LiabilitiesChanging();
                    负债变动信息.setCashFund(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.负债变化赋值T_N(t_n负债变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 负债变动信息);

                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_n现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());

            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品到期.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品赎回.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_份额强减.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品提前赎回.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_提前终止.getValue())) {
                组合估值_处理后.setTdyShareAmt(ArithmeticUtil.subtract(组合估值_处理后.getTdyShareAmt(), mockTrdProductDealDto.getShareAmt()));
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) !=0) {
                    组合估值_处理后.setDelayPayAmt(ArithmeticUtil.subtract(组合估值_处理后.getDelayPayAmt(),
                            mockTrdProductDealDto.getTradeAmt()));
                    LiabilitiesChanging 负债变动信息 = new LiabilitiesChanging();
                    负债变动信息.setCashFund(mockTrdProductDealDto.getTradeAmt());
                    RealTimeValCommonUtil.负债变化赋值T_N(t_n负债变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 负债变动信息);

                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_n现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt().negate());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品除权登记.getValue())) {
                组合估值_处理后.setTdyUnpaidIntincexp(ArithmeticUtil.doAdd(组合估值_处理后.getTdyUnpaidIntincexp(),
                        mockTrdProductDealDto.getFpriceAmt()));
                组合估值_处理后.setTotBounsAmt(mockTrdProductDealDto.getFpriceAmt());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品现金分红.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_赎回收益确认.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品到期分红.getValue())) {
                组合估值_处理后.setTdyUnpaidIntincexp(ArithmeticUtil.subtract(组合估值_处理后.getTdyUnpaidIntincexp(),
                        mockTrdProductDealDto.getTradeAmt()));
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) !=0) {
                    组合估值_处理后.setDelayPayAmt(ArithmeticUtil.subtract(组合估值_处理后.getDelayPayAmt(),
                            mockTrdProductDealDto.getTradeAmt()));
                    LiabilitiesChanging 负债变动信息 = new LiabilitiesChanging();
                    负债变动信息.setCashFund(mockTrdProductDealDto.getTradeAmt());
                    RealTimeValCommonUtil.负债变化赋值T_N(t_n负债变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 负债变动信息);

                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_n现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt().negate());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品红利转投.getValue())) {
                组合估值_处理后.setTdyUnpaidIntincexp(ArithmeticUtil.subtract(组合估值_处理后.getTdyUnpaidIntincexp(),
                        mockTrdProductDealDto.getFpriceAmt()));
                组合估值_处理后.setTdyShareAmt(ArithmeticUtil.doAdd(组合估值_处理后.getTdyShareAmt(),
                        mockTrdProductDealDto.getShareAmt()));
            }
        }


    }

    /**
     * 产品T_N交易处理
     *
     * @param 负债端交易列表T_N
     * @param 组合估值
     * @param 基准日期
     * @param t_n负债变化_内部
     * @param t_n现金变化_内部
     */
    private static void 产品T_N交易处理(List<MockTrdProductDealDto> 负债端交易列表T_N, MockPtlPortfolioValuationDto 组合估值,
                                  String 基准日期, Map<String, LiabilitiesChanging> t_n负债变化_内部,
                                  Map<MockCashChangingKey, BigDecimal> t_n现金变化_内部) {
        //循环负债端交易列表T_N
        for (MockTrdProductDealDto mockTrdProductDealDto : 负债端交易列表T_N) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品认购.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品申购.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_份额强增.getValue())) {

                LiabilitiesChanging 负债变动信息 = new LiabilitiesChanging();
                负债变动信息.setCashFund(mockTrdProductDealDto.getTradeAmt().negate());
                RealTimeValCommonUtil.负债变化赋值T_N(t_n负债变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 负债变动信息);

                RealTimeValCommonUtil.现金变化赋值T_N(t_n现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());

            } else {
                LiabilitiesChanging 负债变动信息 = new LiabilitiesChanging();
                负债变动信息.setCashFund(mockTrdProductDealDto.getTradeAmt());
                RealTimeValCommonUtil.负债变化赋值T_N(t_n负债变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 负债变动信息);

                RealTimeValCommonUtil.现金变化赋值T_N(t_n现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                        mockTrdProductDealDto.getTradeAmt().negate());

            }
        }

    }

}
