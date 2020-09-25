/*
 * 文件名：AveInterestScheduleCal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 等额本息付息计划生成 和重新生成
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月15日
 * @see AveInterestScheduleCal
 * @since 1.0
 */
public class AveInterestScheduleCal {

    /**
     * 付息计划生成
     * @param scai 资产基本信息
                                                                                   资产代码
                                                                                    起息日
                                                                                    到期日
                                                                                    计息基础
                                                                                    利率类型
                                                                                    初始利率（或称票面利率）
                                                                                    币种
                                                                                    资产类型
                                                                                    本金
                                                                                    交易日历
     * @param lirs  还本计划列表
     * @param lyield 利率报价列表
     * @param isr 付息规则
     * @param holidayList 节假日列表
     * @param amt 初始金额
     * @param sysDate 重新生成日期 （平台日）
     * @param class1 付息区间传 AveInvRepayScheduleDto.class
     * @param <T> 泛型
     * @return  等额本息付息计划
     * @see
     * @since 1.0
     */
    public static <T extends FinCashPlanDto> List<T> interestScheduleCal(AssetCommonInfoDto scai, List<T> lirs,
                                                       List<? extends FinCashCalcRule> lyield, FinCashPayRule isr,
                                                       List<String> holidayList, BigDecimal amt, Date sysDate,
                                                       Class<T> class1) {
        //还本周期
        String intpayCycle = isr.getPayCycle();
        BigDecimal cycle = BigDecimal.ZERO;
        // 一体化对应etliu start
//        if (DictCodeList.支付频率_付息频率_每月等额还本.getValue().equals(intpayCycle)) {
//            cycle = new BigDecimal(12);
//        }
//        else if (DictCodeList.支付频率_付息频率_每季等额还本.getValue().equals(intpayCycle)) {
//            cycle = new BigDecimal(4);
//        }
//        else if (DictCodeList.支付频率_付息频率_每季等额还本.getValue().equals(intpayCycle)) {
//            cycle = new BigDecimal(2);
//        }
//        else if (DictCodeList.支付频率_付息频率_每年等额还本.getValue().equals(intpayCycle)) {
//            cycle = new BigDecimal(1);
//        }
        if (DictCodeList.频率_每月等额还本.getValue().equals(intpayCycle)) {
            cycle = new BigDecimal(12);
        }
        else if (DictCodeList.频率_每季等额还本.getValue().equals(intpayCycle)) {
            cycle = new BigDecimal(4);
        }
        else if (DictCodeList.频率_每半年等额还本.getValue().equals(intpayCycle)) {
            cycle = new BigDecimal(2);
        }
        else if (DictCodeList.频率_每年等额还本.getValue().equals(intpayCycle)) {
            cycle = new BigDecimal(1);
        }
     // 一体化对应etliu end

        // 利率 = 【入参】报价信息列表.get(0).利率
        BigDecimal rate = lyield.get(0).getYield();
        if(rate.compareTo(BigDecimal.ZERO) == 0) {
            return lirs;
        }
        int n = 0;
        for (FinCashPlanDto airsd : lirs) {
            for (int i = n ;i <= lyield.size() -1; i++) {
                FinCashCalcRule irq = lyield.get(i);
                if(irq.getEffDate().compareTo(airsd.getVdate()) > 0) {
                    break;
                }
                if(irq.getEffDate().compareTo(airsd.getVdate()) == 0) {
                    airsd.setReset(true);
                    rate = irq.getYield();
                }
                n++;
            }
            airsd.setNewRate(rate.divide(cycle, 12, RoundingMode.HALF_UP));
        }
        // 期数 = 1
        int term = 1;
        // 计算本金 = 【入参】初始本金
        BigDecimal amtcal = amt;
        // 当期本金 = 初始本金
        BigDecimal amtnow = amt;
        // 总期数 = 还本计划列表的条数
        int termTotal = lirs.size();
        // 是否重置状态= false
        boolean isreset = false;
        for (int i = 0; i < lirs.size(); i++ ) {
            FinCashPlanDto airsd = lirs.get(i);
            // 还本计划.是否重置 = true || 【变量】是否重置状态 = true）
            if (airsd.isReset() || isreset) {
                // 计算本金 =【变量】当期本金
                amtcal = amtnow;
                // 总期数 = 【变量】总期数 - i
                termTotal = termTotal - term + 1;
                // 期数 = 1
                term = 1;
            }
            // 还本计划.应计利息
            // 【变量】还本计划.应计利息 = 【变量】计算本金*【变量】还本计划.利率*((1+【变量】还本计划.利率)^【变量】总期数
            //    - (1+【变量】还本计划.利率)^(【变量】期数 - 1)) ÷((1+【变量】还本计划.利率)^【变量】总期数 -1)

            // (1+【变量】还本计划.利率)
            BigDecimal ratetemp = airsd.getNewRate().add(BigDecimal.ONE);
            BigDecimal res = amtcal.multiply(airsd.getNewRate());
            res = res.multiply(ratetemp.pow(termTotal).subtract(ratetemp.pow(term-1)));
            res = res.divide(ratetemp.pow(termTotal).subtract(BigDecimal.ONE),12,RoundingMode.HALF_UP);
            airsd.setIntAmt(res);
            // 还本计划.按期还本金额
            // 【变量】还本计划.按期还本金额 = 【变量】计算本金*【变量】还本计划.利率*(1+【变量】还本计划.利率)^(【变量】期数 -1)
            // ÷((1+【变量】还本计划.利率)^【变量】总期数 -1)
            res = amtcal.multiply(airsd.getNewRate());
            res = res.multiply(ratetemp.pow(term-1));
            res = res.divide(ratetemp.pow(termTotal).subtract(BigDecimal.ONE),12,RoundingMode.HALF_UP);
            term++;
            // 还本计划.按期还本金额 = 【变量】当期本金
            airsd.setPrinAmt(res);
            airsd.setCurPrin(amtnow);
            amtnow = amtnow.subtract(airsd.getPrinAmt());
            airsd.setCashAmt((airsd.getPrinAmt().add(airsd.getIntAmt())));
            airsd.setIsPayInt(DictCodeList.是否_是.getValue());
        }
        return lirs;
    }

    /**
     * 等额本息付息计划重新生成
     * @param is  计划规则
     * @param scai 资产基本信息
     * @param holidayList 节假日列表
     * @param srclisd 原始付息计划
     * @param sysdate 重新生成日期 （平台日）
     * @param <T> 等额本息区间传 InvRepayScheduleDto
     * @param class1 需要返回的类型
     * @return 计划列表
     * @see
     * @since 1.0
     */

    public static <T extends FinCashPlanDto> List<T> resetCal(List<? extends FinCashCalcRule> lyield, List<T> srclisd,
                                                                 FinCashPayRule is, AssetCommonInfoDto scai, List<String> holidayList, Date sysdate, Class<T> class1) {
        // 调用共通【资产区间计算】
        List<T> dictlist = interestScheduleCal(scai, srclisd, lyield, is, holidayList, scai.getAmount(), sysdate, class1);
        return AssetInterestCalReset.concat(srclisd, dictlist, sysdate, class1);
    }
}
