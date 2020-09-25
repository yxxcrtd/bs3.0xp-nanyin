/*
 * 文件名：AssetInterestCalReset.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.calculator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;

/**
 * 资产区间生成（重新生成）
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月17日
 * @see AssetInterestCalReset
 * @since 1.0
 */
public class AssetInterestCalReset {
    
    /**
     * 资产区间计算
     * @param is  计划规则
     * @param scai 资产基本信息
     * @param holidayList 节假日列表
     * @param isFull 区间是否完整
     * @param srclisd 原始付息计划
     * @param sysdate 重新生成日期 （平台日）
     * @param <T> 付息区间传 InvScheduleDto
     *            等额本息区间传 InvRepayScheduleDto(废弃)
     * @param class1 需要返回的类型
     * @return 计划列表
     * @see
     * @since 1.0
     */

    public static <T extends FinCashPlan> List<T> cal(FinCashPayRule is, AssetCommonInfoDto scai, List<String> holidayList, 
                                           boolean isFull, List<T> srclisd, Date sysdate, Class<T> class1) {
        // 调用共通【资产区间计算】
        List<T> dictlist = null;
        dictlist = AssetInterestCal.cal(is, scai, holidayList, isFull, class1);
        return concat(srclisd, dictlist, sysdate, class1);
    }
    
    /**
     * 资产区间重新计算组合
     * @param srclisd 原始付息计划
     * @param dictlist 新付息计划
     * @param sysdate 重新生成日期 （平台日）
     * @param <T> 付息区间传 InvScheduleDto
     *            等额本息区间传 InvRepayScheduleDto(废弃)
     * @param class1 需要返回的类型
     * @return 计划列表
     * @see
     * @since 1.0
     */

    public static <T extends FinCashPlan> List<T> concat(List<T> srclisd, List<T> dictlist, Date sysdate, Class<T> class1) {
        // 调用共通【资产区间计算】
        List<T> res = new ArrayList<T>();
        boolean flag = false;
        if (srclisd != null && srclisd.size() >0) {
            for (T dto : srclisd) {
                if (dto.getMdate().compareTo(sysdate) <= 0) {
                    res.add(dto);
                }
                else if (dto.getMdate().compareTo(sysdate) > 0) {
                    Date tempDate = dto.getVdate();
                    for(T dto2 : dictlist) {
                        // if(【变量】计划调整前.实际开始日<=【变量】开始日 并且  【变量】计划调整前.实际结束日>【变量】开始日)
                        if (dto2.getVdate().compareTo(tempDate) <= 0 && dto2.getMdate().compareTo(tempDate) > 0) {
                            if (dto2.getMdate().compareTo(sysdate) < 0) {
                                res.add(dto);
                                tempDate = dto.getMdate();
                                continue;
                            }
                            flag = true;
                            dto2.setVdateTerm(dto.getVdateTerm());
                            dto2.setVdate(dto.getVdate());
                            res.add(dto2);
                        }
                        else if (dto2.getVdate().compareTo(dto.getVdate()) >= 0) {
                            res.add(dto2);
                        }
                    }
                    if(flag) {
                        break;
                    }
                }
                
            }
        } 
        else {
            res = dictlist;
        }
        return res;
    }
}
