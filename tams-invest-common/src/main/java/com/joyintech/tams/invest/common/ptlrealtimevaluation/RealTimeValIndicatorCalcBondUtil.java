/*
 * 文件名：RealTimeValIndicatorCalcBondUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.ptlrealtimevaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;

/**
 * 组合实时估值-计算指标工具类-债券相关
 * 
 * @author etliu
 * @version 1.0 2020年4月20日
 * @see RealTimeValIndicatorCalcBondUtil
 * @since 1.0
 */
public class RealTimeValIndicatorCalcBondUtil {
    
    /**
     * 说明：计算计算日以后付息日的剩余期限和现金流的值，不包含计算日当天<br>
     * 备注：<br>
     * 
     * @param cDate 计算日
     * @param list_interScheduleBond 债券的付息区间列表，其中到期本息和已经计算过
     * @param payFreq 付息频率
     * @return 第一个元素为现金流的值，第二个元素为现金流剩余期限
     */
    public static double[][] encashYearsAndCashFlow(Date cDate,
                                                    List<MockFinCashPlanDto> list_interScheduleBond,
                                                    Long payFreq) {

        // 剩余年限
        List<Double> list_encashYears = new ArrayList<Double>();
        // 剩余现金
        List<Double> list_cashFlow = new ArrayList<Double>();

        try {

            // 未起息
            if (list_interScheduleBond.get(0).getFinCashPlan().getVdate().compareTo(cDate) > 0) {
                // cDate设为起息日
                cDate = list_interScheduleBond.get(0).getFinCashPlan().getVdate();
            }
            // 已到期
            else if (list_interScheduleBond.get(
                list_interScheduleBond.size() - 1).getFinCashPlan().getMdate().compareTo(
                    cDate) < 0) {
                return new double[][] {{0}, {0}};
            }
            // 存续期
            else {

            }
            // 循环付息区间,找到cDate位于哪一个付息区间
            int index = 0;
            // 起息日当天
            if (list_interScheduleBond.get(0).getFinCashPlan().getVdate().compareTo(cDate) == 0) {
                // cDate设为起息日
                index = 0;
            }
            else {
                for (int i = 0; i < list_interScheduleBond.size(); i++ ) {
                    FinCashPlan interScheduleBond = list_interScheduleBond.get(i).getFinCashPlan();
                    // 如果付息理论起始日小于cDate 并且 付息理论结束日大于cDate
                    if (cDate.compareTo(interScheduleBond.getVdateTerm()) >=

                    0 && cDate.compareTo(interScheduleBond.getMdateTerm()) < 0) {
                        index = i;
                        break;
                    }
                }
            }

            // 计算付息截止日和cDate之间的年化期限
            double encashYearsHead = 0;
            double encashYearsMiddle = 0;
            double encashYearsEnd = 0;

            // 对第一个付息周期加上该周期剩余天数除以该周期实际天数
            FinCashPlan interScheduleBondHead = list_interScheduleBond.get(index).getFinCashPlan();
            encashYearsHead = (double)DateUtil.getBetweenDayNumber(cDate,
                interScheduleBondHead.getMdate()) / (double)DateUtil.getBetweenDayNumber(
                    interScheduleBondHead.getVdateTerm(), interScheduleBondHead.getMdateTerm())
                              / payFreq;

            if (list_interScheduleBond.size() >= index + 2) {
                // 对最后一个付息周期加上该周期已过天数除以该周期实际天数
                FinCashPlan interScheduleBondEnd = list_interScheduleBond.get(
                    list_interScheduleBond.size() - 1).getFinCashPlan();
                encashYearsEnd = (double)DateUtil.getBetweenDayNumber

                (interScheduleBondEnd.getVdate(), interScheduleBondEnd.getMdate())
                                 / (double)DateUtil.getBetweenDayNumber(
                                     interScheduleBondEnd.getVdateTerm(),
                                     interScheduleBondEnd.getMdateTerm())
                                 / payFreq;
                if (list_interScheduleBond.size() >= index + 3) {
                    // 中间的付息周期直接加上1 / 付息频率
                    encashYearsMiddle = 1.0 / payFreq;
                }

            }

            // 从cDate位于的付息区间开始循环
            for (int i = index; i < list_interScheduleBond.size(); i++ ) {
                double encashYears = 0;
                if (i == index) {
                    encashYears = encashYearsHead;
                }
                else if (i == list_interScheduleBond.size() - 1) {
                    encashYears = encashYearsHead + encashYearsMiddle * (i - index - 1)
                                  + encashYearsEnd;
                }
                else {
                    encashYears = encashYearsHead + encashYearsMiddle * (i - index);
                }
                list_encashYears.add(encashYears);
                list_cashFlow.add(
                    list_interScheduleBond.get(i).getFinCashPlan().getCashAmt().doubleValue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // 拼接返回值
        double[][] douRes = new double[2][];
        douRes[0] = new double[list_encashYears.size()];
        douRes[1] = new double[list_cashFlow.size()];
        for (int i = 0; i < list_encashYears.size(); i++ ) {
            douRes[0][i] = list_cashFlow.get(i);
            douRes[1][i] = list_encashYears.get(i);
        }

        return douRes;
    }

    /**
     * 根据未来现金流计算修正久期
     * 
     * @param ytm 到期收益率
     * @param fwdCashflow 未来现金流
     * @param fwdEncashYear 剩余年限
     * @param irPayFreq 付息频率
     * @return 修正久期
     */
    public static double durationByFwdCashflow(double ytm, double[] fwdCashflow,
                                               double[] fwdEncashYear, Long irPayFreq) {
        double[] result = {0, 0, 0};
        try {
            double irPayFreqDouble = irPayFreq;
            // 区间的年化收益率
            double yieldForFreq = ytm / irPayFreqDouble;
            double full_price = priceByYtm(fwdCashflow, fwdEncashYear, ytm, irPayFreq);
            // 麦氏久期
            double M_duration = 0;
            // 凸性
            double convexity = 0;
            // 对未来的现金流进行汇总处理
            for (int i = 0; i < fwdEncashYear.length; i++ ) {
                M_duration += fwdEncashYear[i] * fwdCashflow[i]
                              / Math.pow(1 + yieldForFreq, fwdEncashYear[i] * irPayFreqDouble)
                              / full_price;
                convexity += 1 / Math.pow(1 + yieldForFreq, 2)
                             * (fwdEncashYear[i] * (fwdEncashYear[i] + 1 / irPayFreqDouble)
                                * fwdCashflow[i] / Math.pow(1 + yieldForFreq,

                                    fwdEncashYear[i] * irPayFreqDouble));
            }
            double A_duration = M_duration / (1 + yieldForFreq);
            result[0] = M_duration;
            result[1] = A_duration;
            result[2] = convexity / full_price;

        }
        catch (Exception e) {
        }
        return result[1];
    }

    /**
     * 说明：通过到期收益率计算全价,需要未来现金流及对应的年化剩余期限<br>
     * 备注：参考公式：PV = Σ FPi/(1+Y/f)^(Ni*f)<br>
     * 其中：PV是全价，FPi是远期现金流，f是年付息次数<br>
     * Ni是计算日到到远期现金流之间的年化剩余期限<br>
     * 如果剩余付息周期大于1个，按复利计算；否则按单利计算<br>
     * 
     * @param fwdCashflow 未来付息日的现金流
     * @param fwdEncashYear 未来剩余期限
     * @param ytm 到期收益率
     * @param payFreq 年付息次数
     * @return 全价
     */
    private static double priceByYtm(double[] fwdCashflow, double[] fwdEncashYear, double ytm,
                                     Long payFreq) {
        double totalDiscount = 0;
        // 最后一个付息周期
        if (fwdCashflow.length == 1) {
            totalDiscount = fwdCashflow[0] / (1 + ytm * fwdEncashYear[0]);
        }
        // 非最后一个付息周期
        else {
            List<Double> listDiscount = new ArrayList<Double>();
            // 现金流折现
            for (int i = 0; i < fwdCashflow.length; i++ ) {
                double discount = fwdCashflow[i]
                                  / Math.pow(1 + ytm / payFreq, fwdEncashYear[i] * payFreq);
                listDiscount.add(discount);
            }
            // 折现后的现金流累加得到价格
            totalDiscount = sumDoubleArray(listDiscount);
        }
        return totalDiscount;
    }

    /**
     * 计算数组中所有元素之和
     * 
     * @param list 待计算的数组
     * @return 数组中所有元素之和
     */
    private static double sumDoubleArray(List<Double> list) {
        double sum = 0;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++ ) {
                sum += list.get(i);
            }
        }
        return sum;
    }
}
