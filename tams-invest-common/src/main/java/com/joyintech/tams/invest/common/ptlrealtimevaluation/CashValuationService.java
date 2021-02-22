/*
 * 文件名：BondAssetValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.ptlrealtimevaluation;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.assetvaluation.BaseAssetValuationService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 现金估值处理
 *
 * @author admin
 * @version 1.0 2020年4月17日
 * @see CashValuationService
 * @since 1.0
 */
public class CashValuationService {

    /**
     * 现金估值处理
     *
     * @param t_N现金变化_内部
     * @param 组合资产估值
     * @param 组合现金
     * @param 融资成本
     * @param 再投资收益率
     * @param 组合基本信息dto
     * @param 汇率行情
     * @param 测算开始日
     * @param 测算结束日
     * @param 本币
     * @return
     */
    public static MockCalcAssetValuationResult excute(Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                                                      Map<String, Map<String, MockPtlSecValuationDto>> 组合资产估值,
                                                      Map<String, BigDecimal> 组合现金,
                                                      BigDecimal 融资成本,
                                                      BigDecimal 再投资收益率,
                                                      MockPtlPortfolioDto 组合基本信息dto,
                                                      List<MockMstExchargeQuoteDto> 汇率行情,
                                                      String 测算开始日, String 测算结束日,
                                                      String 本币) {
        MockCalcAssetValuationResult 产品估值方法返回 = new MockCalcAssetValuationResult();

        Map<String, BigDecimal> 组合现金_处理后 = new HashMap<String, BigDecimal>();
        组合现金_处理后.put(本币, BigDecimal.ZERO);
        List<MockPtlSecValuationDto> 组合资产估值列表 = new ArrayList<>();
        //T+N现金变化以及组合现金DTO中币种去重后合并成 变量.币种列表
        List<MockCashChangingKey> list = t_N现金变化_内部.keySet().stream().collect(Collectors.toList());
        List<String> ccyList = 组合现金.keySet().stream().collect(Collectors.toList());
        list.forEach(x -> {
            ccyList.add(x.getCcy());
        });
        //币种去重
        List<String> ccyListD = ccyList.stream().distinct().collect(Collectors.toList());
        for (String ccy : ccyListD) {
            Date 测算日期 = DateUtil.parse(测算开始日);

            BigDecimal 再投现金 = BigDecimal.ZERO;
            if (StringUtil.isNotEmpty(组合现金.get(ccy))) {
                再投现金 = 组合现金.get(ccy);
            }
            BigDecimal 融资金额 = BigDecimal.ZERO;
            BigDecimal 保证金额 = BigDecimal.ZERO;
            MockPtlSecValuationDto 再投昨日头寸 = new MockPtlSecValuationDto();
            MockPtlSecValuationDto 融资昨日头寸 = new MockPtlSecValuationDto();
            MockPtlSecValuationDto 保证金昨日头寸 = new MockPtlSecValuationDto();
            if (组合资产估值 != null) {
                if (组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_再投) != null) {
                    再投昨日头寸 = 组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_再投).get(RealTimeValCommonUtil.投资目的);
                }
                if (组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_融资) != null) {
                    融资昨日头寸 = 组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_融资).get(RealTimeValCommonUtil.投资目的);
                }
                if (组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_保证金) != null) {
                    保证金昨日头寸 = 组合资产估值.get(ccy + RealTimeValCommonUtil.现金类型_保证金).get(RealTimeValCommonUtil.投资目的);
                }
            }

            while (DateUtil.compareDate(DateUtil.format(测算日期), 测算结束日) <= 0) {
                BigDecimal 再投当日变化 = t_N现金变化_内部.get(new MockCashChangingKey(测算日期,
                        RealTimeValCommonUtil.现金类型_再投, ccy));
                MockPtlSecValuationDto 再投组合资产估值 = new MockPtlSecValuationDto();
                if ((再投当日变化 != null && BigDecimal.ZERO.compareTo(再投当日变化) != 0)
                        ||(再投现金!=null&&BigDecimal.ZERO.compareTo(再投现金) != 0&&DateUtil.compareDate(DateUtil.format(测算日期), 测算开始日)==0)
                    || StringUtil.isNotEmpty(再投昨日头寸.getDscCostAmt())) {
                    再投组合资产估值.setPortfolioId(组合基本信息dto.getPortfolioId());
                    再投组合资产估值.setCcy(ccy);
                    再投组合资产估值.setInvAim(RealTimeValCommonUtil.投资目的);
                    再投组合资产估值.setFinprodId(ccy + RealTimeValCommonUtil.现金类型_再投);
                    再投组合资产估值.setSecAcctId(RealTimeValCommonUtil.投资目的);
                    再投组合资产估值.setCdate(测算日期);
                    再投组合资产估值.setTgAssetType(RealTimeValCommonUtil.投管资产类别_现金);
                    再投组合资产估值.setAssetLiabilitiesMark(RealTimeValCommonUtil.资产负债标识_资产);
                    BigDecimal 再投昨日头寸DscCostAmt=再投昨日头寸.getDscCostAmt();//昨日头寸成本
                    BigDecimal 再投昨日头寸InTdyIntincexp=再投昨日头寸.getTdyIntincexp();//昨日头寸应计利息
                    if(再投昨日头寸.getAssetLiabilitiesMark()!=null && 再投昨日头寸.getAssetLiabilitiesMark().equals(RealTimeValCommonUtil.资产负债标识_负债)){
                        再投昨日头寸DscCostAmt=再投昨日头寸DscCostAmt.negate();
                        再投昨日头寸InTdyIntincexp=再投昨日头寸.getTdyIntincexp().negate();
                    }

                    BigDecimal 摊销总成本 = ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(再投现金, 再投当日变化),
                            再投昨日头寸DscCostAmt);
                	if(DateUtil.compareDate(DateUtil.format(测算日期), 测算开始日)!=0){
                		摊销总成本 = ArithmeticUtil.doAdd(再投当日变化,
                                再投昨日头寸DscCostAmt);
                	}
                    再投组合资产估值.setDscCostAmt(摊销总成本);
                    BigDecimal 当日应计利息 = null;
                    if (BigDecimal.ZERO.compareTo(摊销总成本) < 0) {
                        当日应计利息 = ArithmeticUtil.doAdd(再投昨日头寸InTdyIntincexp, ArithmeticUtil.divide(
                                ArithmeticUtil.multiply(摊销总成本, 再投资收益率), 365, RealTimeValCommonUtil.AMOUNT_SCALE
                        ));
                    } else {
                        当日应计利息 = ArithmeticUtil.doAdd(再投昨日头寸InTdyIntincexp, ArithmeticUtil.divide(
                                ArithmeticUtil.multiply(摊销总成本, 融资成本), 365, RealTimeValCommonUtil.AMOUNT_SCALE
                        ));
                    }
                    再投组合资产估值.setTdyIntincexp(当日应计利息);
                    再投组合资产估值.setYield(ArithmeticUtil.divide(再投资收益率, 融资成本, RealTimeValCommonUtil.AMOUNT_SCALE));


                    if (BigDecimal.ZERO.compareTo(摊销总成本) > 0) {
                        再投组合资产估值.setAssetLiabilitiesMark(RealTimeValCommonUtil.资产负债标识_负债);
                        再投组合资产估值.setDscCostAmt(再投组合资产估值.getDscCostAmt().negate());
                        再投组合资产估值.setTdyIntincexp(再投组合资产估值.getTdyIntincexp().negate());
                    }
                    //调用【资产价值计算】（变量.再投现金组合资产估值,入参.本币，入参.汇率行情）
                    BaseAssetValuationService.资产价值计算(再投组合资产估值, 本币, 汇率行情);
                    组合资产估值列表.add(再投组合资产估值);
                    再投昨日头寸 = 再投组合资产估值;
                }

                BigDecimal 融资当日变化 = t_N现金变化_内部.get(new MockCashChangingKey(测算日期,
                        RealTimeValCommonUtil.现金类型_融资, ccy));
                MockPtlSecValuationDto 融资组合资产估值 = new MockPtlSecValuationDto();
                if ((融资当日变化 != null && BigDecimal.ZERO.compareTo(融资当日变化) != 0)
                    || StringUtil.isNotEmpty(融资昨日头寸.getDscCostAmt())) {

                    融资组合资产估值.setPortfolioId(组合基本信息dto.getPortfolioId());
                    融资组合资产估值.setCcy(ccy);
                    融资组合资产估值.setInvAim(RealTimeValCommonUtil.投资目的);
                    融资组合资产估值.setFinprodId(ccy + RealTimeValCommonUtil.现金类型_融资);
                    融资组合资产估值.setSecAcctId(RealTimeValCommonUtil.投资目的);
                    融资组合资产估值.setCdate(测算日期);
                    融资组合资产估值.setTgAssetType(RealTimeValCommonUtil.投管资产类别_现金);
                    融资组合资产估值.setAssetLiabilitiesMark(RealTimeValCommonUtil.资产负债标识_负债);
                    BigDecimal 摊销总成本 = ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(融资金额, 融资当日变化),
                            融资昨日头寸.getDscCostAmt());
                    融资组合资产估值.setDscCostAmt(摊销总成本);
                    BigDecimal 当日应计利息 = ArithmeticUtil.doAdd(融资昨日头寸.getTdyIntincexp(), ArithmeticUtil.divide(
                            ArithmeticUtil.multiply(摊销总成本, 融资成本), 365, RealTimeValCommonUtil.AMOUNT_SCALE
                    ));
                    融资组合资产估值.setTdyIntincexp(当日应计利息);
                    融资组合资产估值.setYield(融资成本);
                    //调用【资产价值计算】（变量.再投现金组合资产估值,入参.本币，入参.汇率行情）
                    BaseAssetValuationService.资产价值计算(融资组合资产估值, 本币, 汇率行情);
                    组合资产估值列表.add(融资组合资产估值);
                    融资昨日头寸 = 融资组合资产估值;
                }

                BigDecimal 保证金当日变化 = t_N现金变化_内部.get(new MockCashChangingKey(测算日期,
                        RealTimeValCommonUtil.现金类型_保证金, ccy));
                MockPtlSecValuationDto 保证金组合资产估值 = new MockPtlSecValuationDto();
                if ((保证金当日变化 != null && BigDecimal.ZERO.compareTo(保证金当日变化) != 0)
                    || StringUtil.isNotEmpty(保证金昨日头寸.getDscCostAmt())) {

                    保证金组合资产估值.setPortfolioId(组合基本信息dto.getPortfolioId());
                    保证金组合资产估值.setCcy(ccy);
                    保证金组合资产估值.setInvAim(RealTimeValCommonUtil.投资目的);
                    保证金组合资产估值.setFinprodId(ccy + RealTimeValCommonUtil.现金类型_保证金);
                    保证金组合资产估值.setSecAcctId(RealTimeValCommonUtil.投资目的);
                    保证金组合资产估值.setCdate(测算日期);
                    保证金组合资产估值.setTgAssetType(RealTimeValCommonUtil.投管资产类别_现金);
                    保证金组合资产估值.setAssetLiabilitiesMark(RealTimeValCommonUtil.资产负债标识_资产);
                    BigDecimal 摊销总成本 = ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(保证金额, 保证金当日变化),
                            保证金昨日头寸.getDscCostAmt());
                    保证金组合资产估值.setDscCostAmt(摊销总成本);
                    保证金组合资产估值.setTdyIntincexp(BigDecimal.ZERO);
                    保证金组合资产估值.setYield(BigDecimal.ZERO);
                    //调用【资产价值计算】（变量.再投现金组合资产估值,入参.本币，入参.汇率行情）
                    BaseAssetValuationService.资产价值计算(保证金组合资产估值, 本币, 汇率行情);
                    组合资产估值列表.add(保证金组合资产估值);
                    保证金昨日头寸 = 保证金组合资产估值;
                }
                if (DateUtil.compareDate(测算日期, DateUtil.parse(测算开始日)) == 0) {
                    BigDecimal rate = RealTimeValCommonUtil.汇率获取(ccy,本币,汇率行情);
                    组合现金_处理后.put(本币, ArithmeticUtil.doAdd(组合现金_处理后.get(本币),ArithmeticUtil.multiply(
                            ArithmeticUtil.subtract(
                                    (StringUtil.equals(再投组合资产估值.getAssetLiabilitiesMark(),RealTimeValCommonUtil.资产负债标识_负债)?
                                            再投组合资产估值.getDscCostAmt().negate():再投组合资产估值.getDscCostAmt()), 融资组合资产估值.getDscCostAmt()),
                            rate).setScale(RealTimeValCommonUtil.AMOUNT_SCALE)));
                }
                测算日期 = DateUtil.addDay(测算日期, 1);
            }
        }
        产品估值方法返回.setMockPtlSecValuationDtoList(组合资产估值列表);
        产品估值方法返回.setMockPtlCashPositionMap(组合现金_处理后);
        return 产品估值方法返回;
    }
}
