/*
 * 文件名：IntervalDateSplit.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.calculator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;

import java.util.*;


/**
 * 资产日期区间分割
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月16日
 * @see IntervalDateSplit
 * @since 1.0
 */
public class IntervalDateSplit {

    /**
     * 资产日期区间分割
     * 
     * @param vDate 起息日
     * @param mDate 到期日
     * @param fDate 首次付息日
     * @param calcMethod 推算方法
     * @param cycle 周期
     * @param cycleUnit 周期单位 （Calendar.YEAR。。。）
     * @param isFull 区间是否完整 默认false
     * @return 区间节点列表 类型：区间节点日 Date 理论节点日 0 实际节点日 1
     * @see
     * @since 1.0
     */
    public static List<Date[]> cal(Date vDate, Date mDate, Date fDate, String calcMethod,
                                   int cycle, int cycleUnit, boolean isFull) {
        // 返回结果
        List<Date[]> result = new ArrayList<Date[]>();
        if (cycle == 0) {
            result.add(new Date[]{vDate, vDate});
            result.add(new Date[]{mDate, mDate});
            return result;
        }
        // 计算开始日
        Date calfromDate = null;
        // 一体化对应etliu
//      if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(), calcMethod)) {
        if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(), calcMethod)) {
            calfromDate = vDate;
            result.add(new Date[] {vDate, vDate});
            int order = 1;
            Date tempDate = calfromDate;
            // 向后推算到结束日
            while (tempDate.compareTo(mDate) < 0) {
                tempDate = CalendarUtil.calendarcal(calfromDate, cycleUnit, cycle * order++ );
                if (tempDate.compareTo(mDate) <= 0) {
                    result.add(new Date[] {tempDate, tempDate});
                }
                else {
                    if (isFull) {
                        result.add(new Date[] {tempDate, tempDate});
                    }
                    else {
                        result.add(new Date[] {tempDate, mDate});
                    }
                    break;
                }
            }
        }
        // 一体化对应etliu
//      else if (DictCodeList.推算方法_到期日向前.getValue().equals(calcMethod)) {
        else if (DictCodeList.推算方式_到期日向前.getValue().equals(calcMethod)) {
            calfromDate = mDate;
            result.add(new Date[] {mDate, mDate});
            int order = 1;
            Date tempDate = calfromDate;
            // 向前推算到开始日
            while (tempDate.compareTo(vDate) > 0) {
                tempDate = CalendarUtil.calendarcal(calfromDate, cycleUnit, -cycle * order++ );
                if (tempDate.compareTo(vDate) >= 0) {
                    result.add(new Date[] {tempDate, tempDate});
                }
                else {
                    result.add(new Date[] {tempDate, vDate});
                    break;
                }
            }
        }
        else if (DictCodeList.推算方式_首次支付日.getValue().equals(calcMethod)) {
            calfromDate = fDate;
            //存在有交易的债券 此时vdate > 首次付息日 需要从 vdate 开始生成
            if(vDate.compareTo(fDate) <= 0){
                result.add(new Date[] {fDate, fDate});
                result.add(new Date[] {CalendarUtil.calendarcal(calfromDate, cycleUnit, cycle * -1 ), vDate});
            } else {
                result.add(new Date[] {vDate, vDate});
            }
            int order = 1;
            Date tempDate = calfromDate;
            // 向后推算到结束日
            while (tempDate.compareTo(mDate) < 0) {
                tempDate = CalendarUtil.calendarcal(calfromDate, cycleUnit, cycle * order++ );
                if (tempDate.compareTo(mDate) <= 0) {
                    if(tempDate.compareTo(vDate) <= 0){
                        continue;
                    }
                    result.add(new Date[] {tempDate, tempDate});
                }
                else {
                    if (isFull) {
                        result.add(new Date[] {tempDate, tempDate});
                    }
                    else {
                        result.add(new Date[] {tempDate, mDate});
                    }
                    break;
                }
            }
        }else{
            //TODO
            //首次开放日、首次分红日需要追加
        }
        

        // 排序
        Collections.sort(result, new Comparator<Date[]>() {

            @Override
            public int compare(Date[] o1, Date[] o2) {
                return o1[1].compareTo(o2[1]);
            }

        });

        return result;
    }
}
