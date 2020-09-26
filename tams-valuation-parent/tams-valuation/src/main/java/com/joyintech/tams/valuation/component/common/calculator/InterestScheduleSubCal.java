/*
 * 文件名：InterestScheduleSubCal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.calculator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.valuation.component.common.dto.AssetCommonInfoDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDetailDto;
import com.joyintech.tams.valuation.component.common.dto.FinCashPlanDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;

/**
 * 分段区间付息计划生成
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月29日
 * @see InterestScheduleSubCal
 * @since 1.0
 */
public class InterestScheduleSubCal {

    /**
     * 区间付息计划生成
     * @param lis 付息计划列表 （传入的list会被修改）
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
     * @param irr 利率重置规则
     * @param liy 利率报价列表
     * @param isr 付息规则
     * @param holidayList 节假日列表
     * @param papi 初始本金
     * @param classdetail 付息区间详细传 InvScheduleDetail.class
     * @param <T> 泛型
     * @return 付息计划
     * @throws ParseException 
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <T extends FinCashPlanDto> List<T> interestScheduleSubCalA(List<T> lis, AssetCommonInfoDto scai,
                                                       List<FinCashPlanDto> lirs, List<? extends FinCashCalcRule> liy, FinCashPayRule isr,
                                                       BigDecimal papi, Class<? extends FinCashPlanDetailDto> classdetail) throws ParseException {
        // 分段区间计算
        List<T> newLis = new ArrayList<T>();
        for (T is :lis) {
            List<? extends FinCashPlanDetailDto> lisd = InterestSubCal.cal(is, liy, lirs, scai, papi, classdetail);
            papi = papi.subtract(is.getRepayAmt());
            for (FinCashPlanDetailDto isd : lisd) {
                T finCashPlanDto = (T)new FinCashPlanDto();
                InterestSubAmtCal.cal(is, isd, isr, scai);
                BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto, isd);
                finCashPlanDto.setPayDate(is.getPayDate());
                finCashPlanDto.setRangeYield(ArithmeticUtil.multiply(isd.getYield(),new BigDecimal("100")));
                newLis.add(finCashPlanDto);
            }
        }
        //update by zhangnian  20191031 start
        //付息计划计算已经减去了还本利息 不需要重复减
        //减去区间内还本的利息
        /*for (FinCashPlanDto resultIs : newLis) {
          //还本利息
            BigDecimal intAmt = BigDecimal.ZERO;
            for(FinCashPlanDto ir :lirs){
                if (resultIs.getVdate().compareTo(ir.getMdate()) <= 0
                    && resultIs.getMdate().compareTo(ir.getMdate()) > 0) {
                    intAmt  = ArithmeticUtil.doAdd(12,null,intAmt,ir.getIntAmt());
                }
            }
            resultIs.setIntAmt(ArithmeticUtil.subtract(resultIs.getIntAmt(),intAmt,12,null));
        }*/
        //update by zhangnian  20191031 end
        return newLis;
    }
}
