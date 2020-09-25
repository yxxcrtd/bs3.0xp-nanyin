/*
 * 文件名：AveInterestCal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.calculator;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
/**
 * 等额本息区间划分
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月15日
 * @see AveInterestCal
 * @since 1.0
 */
public class AveInterestCal {

    /**
     * 等额本息区间划分
     * @param is  计划规则
     * @param scai 资产基本信息
     * @param holidayList 节假日列表
     * @param lirs 还本计划
     * @param sysDate 
     * @param <T> 泛型
     * @param class1 需要返回的类型
     * @return 计划列表
     * @see
     * @since 1.0
     */
    public static <T extends FinCashPlan> List<T> cal(FinCashPayRule is, AssetCommonInfoDto scai, 
                                           List<String> holidayList, List<T> lirs, 
                                           Date sysDate, Class<T> class1) {
        List<T> isres = AssetInterestCalReset.cal(is, scai, holidayList, false, lirs, sysDate, class1);
        return isres;
    }
    
}
