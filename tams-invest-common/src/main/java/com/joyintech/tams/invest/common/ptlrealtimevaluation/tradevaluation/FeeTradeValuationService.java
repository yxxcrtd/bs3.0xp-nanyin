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
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.mock.trade.FeeTradeKey;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.*;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * 费用交易 估值 〈功能详细描述〉
 *
 * @author yjm
 * @version 1.0 2020年4月17日
 * @see FeeTradeValuationService
 * @since 1.0
 */
public class FeeTradeValuationService {


    /**
     * 费用交易处理
     *
     * @param 组合费用头寸扩展列表
     * @param 组合基本信息dto
     * @param 费用交易列表
     * @param 测算开始日
     * @param 测算结束日
     * @param 基准日期
     * @param 本币
     * @param 汇率行情列表
     * @return 产品估值方法返回值
     */
    public static MockCalcAssetValuationResult excute(List<MockPtlFeePositionDto> 组合费用头寸扩展列表, MockPtlPortfolioDto 组合基本信息dto,
                                                      List<MockTrdProductDealDto> 费用交易列表, String 测算开始日, String 测算结束日, String 基准日期,
                                                      String 本币, List<MockMstExchargeQuoteDto> 汇率行情列表
    ) {
        List<MockPtlFeePositionDto> 组合费用头寸扩展列表_处理后 = new ArrayList<>();
        //数据准备
        Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部 = new HashMap<>();
        //list2map
        Map<FeeTradeKey, MockPtlFeePositionDto> 组合费用头寸Map = new HashMap<>();
        for (MockPtlFeePositionDto mockPtlFeePositionDto : 组合费用头寸扩展列表) {
            FeeTradeKey feeTradeKey = new FeeTradeKey(mockPtlFeePositionDto.getPtlFeeType(),
                    mockPtlFeePositionDto.getoCcy());
            MockPtlFeePositionDto mockPtlFeePositionDto1 = 组合费用头寸Map.get(feeTradeKey);
            if (mockPtlFeePositionDto1 == null) {
                mockPtlFeePositionDto1 = mockPtlFeePositionDto;
            }
            组合费用头寸Map.put(feeTradeKey, mockPtlFeePositionDto1);
        }

        Map<FeeTradeKey, List<MockTrdProductDealDto>> 费用交易列表Map = new HashMap<>();
        for (MockTrdProductDealDto mockTrdProductDealDto : 费用交易列表) {
            FeeTradeKey feeTradeKey = new FeeTradeKey(mockTrdProductDealDto.getFinprodId(),
                    mockTrdProductDealDto.getCcy());
            List<MockTrdProductDealDto> list = 费用交易列表Map.get(feeTradeKey);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(mockTrdProductDealDto);
            费用交易列表Map.put(feeTradeKey, list);
        }
        List<FeeTradeKey> 已处理费用 = new ArrayList<FeeTradeKey>();

        //循环交易
        费用交易列表Map.entrySet().forEach(entry -> {
            MockPtlFeePositionDto 昨日费用头寸 = 组合费用头寸Map.get(entry.getKey());
            已处理费用.add(entry.getKey());
            String 测算日期 = 测算开始日;
            while(DateUtil.compareDate(测算日期,测算结束日)<=0){
                MockPtlFeePositionDto 当日费用头寸 = new MockPtlFeePositionDto();
                当日费用头寸.setPtlFeeType(entry.getKey().getFeeType());
                当日费用头寸.setCdate(DateUtil.parse(测算日期));
                if(昨日费用头寸!=null)
                    当日费用头寸.setTdyIntervalPayFee(昨日费用头寸.getTdyIntervalPayFee());
                当日费用头寸.setoCcy(entry.getKey().getCcy());
                for (MockTrdProductDealDto mockTrdProductDealDto : entry.getValue()) {
                    if(DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(),DateUtil.parse(测算日期))!=0)
                        continue;
                    if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_付费.getValue())
                            || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_产品付费.getValue())
                            || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_税费缴税.getValue())) {
                        当日费用头寸.setTdyIntervalPayFee(ArithmeticUtil.subtract(当日费用头寸.getTdyIntervalPayFee(),
                                mockTrdProductDealDto.getTradeAmt()));

                        RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                                new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                                        RealTimeValCommonUtil.现金类型_再投, 当日费用头寸.getoCcy()
                                ), mockTrdProductDealDto.getTradeAmt());
                    }
                    if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_费用计提.getValue())) {
                        当日费用头寸.setTdyIntervalPayFee(ArithmeticUtil.doAdd(当日费用头寸.getTdyIntervalPayFee(),
                                mockTrdProductDealDto.getTradeAmt()));
                    }//昨日头寸+当日交易费用
                    if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_回补.getValue())) {
                        当日费用头寸.setTdyIntervalPayFee(ArithmeticUtil.subtract(当日费用头寸.getTdyIntervalPayFee(),
                                mockTrdProductDealDto.getTradeAmt()));
                    }
                }
                // 资产价值处理
                BigDecimal 汇率 = new BigDecimal(1);
                if (!StringUtil.equals(当日费用头寸.getoCcy(), 本币)) {
                    汇率 =RealTimeValCommonUtil.汇率获取(当日费用头寸.getoCcy(),本币,汇率行情列表);
                }
                //资产规模
                当日费用头寸.setAssetValue(ArithmeticUtil.multiply(当日费用头寸.getTdyIntervalPayFee(), 汇率));
                组合费用头寸扩展列表_处理后.add(当日费用头寸);
                if(昨日费用头寸==null){
                    昨日费用头寸 = new MockPtlFeePositionDto();
                }
                BeanUtil.copyPropertiesIgnoreNull(昨日费用头寸,当日费用头寸);
                测算日期 = DateUtil.addDay(测算日期,1);
            }

        });

        组合费用头寸Map.entrySet().forEach(entry -> {
            FeeTradeKey feeTradeKey = entry.getKey();
            MockPtlFeePositionDto 昨日费用头寸 = entry.getValue();
            if(!已处理费用.contains(feeTradeKey)){
                String 测算日期 = 测算开始日;
                while(DateUtil.compareDate(测算日期,测算结束日)<=0) {
                    MockPtlFeePositionDto 当日费用头寸 = new MockPtlFeePositionDto();
                    BeanUtil.copyPropertiesIgnoreNull(当日费用头寸, 昨日费用头寸);
                    当日费用头寸.setCdate(DateUtil.parse(测算日期));

                    // 资产价值处理
                    BigDecimal 汇率 = new BigDecimal(1);
                    if (!StringUtil.equals(当日费用头寸.getoCcy(), 本币)) {
                    /*汇率 =RealTimeValCommonUtil.调用汇率行情(本币, 当日费用头寸.getoCcy(),
                            汇率行情列表, RealTimeValCommonUtil.RATE_SCALE);*/
                    }
                    //资产规模？
                    当日费用头寸.setAssetValue(ArithmeticUtil.multiply(当日费用头寸.getTdyIntervalPayFee(), 汇率));
                    组合费用头寸扩展列表_处理后.add(当日费用头寸);
                    if (昨日费用头寸 == null) {
                        昨日费用头寸 = new MockPtlFeePositionDto();
                    }
                    BeanUtil.copyPropertiesIgnoreNull(昨日费用头寸, 当日费用头寸);
                    测算日期 = DateUtil.addDay(测算日期, 1);
                }
            }
        });

        MockCalcAssetValuationResult mockCalcAssetValuationResult = new MockCalcAssetValuationResult();
        mockCalcAssetValuationResult.setMockPtlFeePositionDtoList(组合费用头寸扩展列表_处理后);
        mockCalcAssetValuationResult.setCashChanging(t_N现金变化_内部);

        return mockCalcAssetValuationResult;
    }

}
