/*
 * 文件名：RateResetCal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.calculator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.service.InstanceOfClass;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;

import java.math.BigDecimal;
import java.util.*;

/**
 * 资产端利率重置
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月18日
 * @see RateResetCal
 * @since 1.0
 */
public class RateResetCal {

    /**
     * 资产端利率重置
     * @param lis 付息计划列表 （按照付息开始日升序排序）
     * @param isr 付息规则
     * @param irr 利率重置规则
     * @param lyield 基准利率列表 （按照生效日升序排序） 
     * @param scai 资产基本信息
     * @param createType 处理方式：0."全量生成"，1."增量生成"，不传入时按"全量生成"方式
     * @param <T> 泛型
     * @param class1 需要返回的类型
     * @return 报价列表 （按照生效日升序排序）
     * @see
     * @since 1.0
     */
    public static <T extends FinCashCalcRule> List<T> cal(List<? extends FinCashPlanDto> lis, FinCashPayRule isr, FinRateInfo irr, List<T> lyield,
                                                          AssetCommonInfoDto scai, int createType, List<String> holidayList, Class<T> class1) {
        // 返回结果
        List<T> result = new ArrayList<T>();
        // 初始报价处理
        if (createType == 0) {
            T iy = InstanceOfClass.createInstance(class1);
            iy.setEffDate(scai.getVdate());
            iy.setYield(scai.getRate());
            iy.setFinprodId(scai.getFinprodId());
            iy.setIsInitial(DictCodeList.是否_是.getValue());
            result.add(iy);
        }
        
        // 重置规则生成报价信息
        // 生效时点 = 当期生效 && 生效时期 = 计息期有效
        if(StringUtil.equals(DictCodeList.重置类型_基准利率调整规则_付息后下一计息期生效.getValue(), irr.getResetType())){
                for (int i = lis.size() - 1; i >= 0; i-- ) {
                    @SuppressWarnings("unchecked")
                    ArrayList<T> lyieldCopy =  (ArrayList<T>)((ArrayList<T>)lyield).clone();
                    FinCashPlanDto is = lis.get(i);
//                    T iy = null;
//                    if (is.getActVdate().compareTo(scai.getVdate()) == 0) {
//                        continue;
//                    }
                    for (int j = lyieldCopy.size() - 1; j >= 0; j-- ) {
                        T yield = lyieldCopy.get(j);
                        if (
                            /*yield.getStartDate().compareTo(is.getActVdate()) >= 0
                            && */
                            yield.getEffDate().compareTo(is.getMdate()) < 0) {
                            T iy = null;
                            iy = getYield(irr, yield, is.getMdate(), scai, class1);
                            result.add(iy);
                            lyieldCopy.remove(j);
                            break;
                        } 
                        else if (yield.getEffDate().compareTo(is.getMdate()) >= 0) {
                            lyieldCopy.remove(j);
                        }
                    }
//                    if (iy != null) {
//                        result.add(iy);
//                    }
                }
            }else if (DictCodeList.重置类型_基准利率调整规则_基准利率变动后固定间隔生效.getValue().equals(irr.getResetType())) {
                        @SuppressWarnings("unchecked")
                        ArrayList<T> lyieldCopy = (ArrayList<T>)((ArrayList<T>)lyield).clone();
                        // 最大付息截止日
                        Date mdate = lis.get(lis.size()-1).getMdate();
                        for (int j = lyieldCopy.size() - 1; j >= 0; j-- ) {
                            T yield = lyieldCopy.get(j);
                            if (yield.getEffDate().compareTo(scai.getVdate()) > 0
                                && yield.getEffDate().compareTo(mdate) < 0) {
                                Integer ObserveBefDay = Integer.parseInt(irr.getObserveBefDay().toString());
                                if(StringUtil.equals(DictCodeList.提前观察单位_延后时间单位_自然日.getValue(), irr.getObserveBefUnit())){
                                    result.add(getYield(irr, yield, CalendarUtil.calendarcal(yield.getEffDate(), 
                                        Calendar.DATE, ObserveBefDay), scai, class1));
                                }
                                else if (DictCodeList.提前观察单位_延后时间单位_自然月.getValue().equals(irr.getObserveBefUnit())) {
                                    result.add(getYield(irr, yield, CalendarUtil.calendarcal(yield.getEffDate(), 
                                        Calendar.MONTH, ObserveBefDay), scai, class1));
                                }
                                else {
                                    Date date = DateUtil.parse(AdjustedTradeDayCal.calculationTradingDay(
                                        DateUtil.format(yield.getEffDate()), ObserveBefDay,
                                        holidayList, DictCodeList.是否_是.getValue()));
                                    result.add(getYield(irr, yield, date, scai, class1));
                                }
                            } 
                    }
        }
        
        // 排序
        Collections.sort(result, new Comparator<T>(){
            @Override
            public int compare(T o1, T o2) {
                return o1.getEffDate().compareTo(o2.getEffDate());
            }
            
        });
        
        return result;
    }

    /**
     * 生成利率信息
     * @param irr 利率规则
     * @param yield 利率
     * @param date 生效日 
     * @param scai 资产基本信息
     * @param <T> 泛型
     * @param class1 需要返回的类型
     * @return 利率
     * @see
     * @since 1.0
     */
    private static <T extends FinCashCalcRule> T getYield(FinRateInfo irr, FinCashCalcRule yield, Date date, AssetCommonInfoDto scai, Class<T> class1) {
        
        T res = InstanceOfClass.createInstance(class1);;
        // 资产代码
        res.setFinprodId(scai.getFinprodId());
        BigDecimal baseRate = yield.getYield();
        // 基准利率 * 利率重置规则.系数
        if (irr.getCoefficient() != null) {
            baseRate = baseRate.multiply(irr.getCoefficient());
        }
        // +利率重置规则.利差
        if (irr.getSpreadRate() != null) {
            baseRate = baseRate.add(irr.getSpreadRate());
        }
        // 利率上限
        if (irr.getHighestRate() != null && baseRate.compareTo(irr.getHighestRate()) > 0) {
            baseRate = irr.getHighestRate();
        }
        // 利率下限
        if (irr.getLowestRate() != null && baseRate.compareTo(irr.getLowestRate()) < 0) {
            baseRate = irr.getLowestRate();
        }
        //res.setBasicRateId(yield.getBasicRateId());
        //TODO  取行情代码的基准利率
        res.setYield(yield.getYield());
        //res.setEffDate(yield.getEffDate());
        res.setEffDate(date);
//        res.setRelaeseDate(date);
        res.setYield(baseRate);
        res.setIsInitial(DictCodeList.是否_否.getValue());
        return res;
    }
}
