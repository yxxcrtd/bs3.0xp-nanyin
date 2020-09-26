/*
 * 文件名：AssetCommonInvRateAdjustDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;

/**
 * 利率调整列表对应的业务类，利率调整（利率型）用
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月18日
 * @see AssetCommonInvRateAdjustDto
 * @since 1.0
 */
public class AssetCommonInvRateAdjustDto extends FinCashCalcRule{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 平台日
     */
    private Date nowSystemDate;

    
    /**
     * 获取 nowSystemDate
     * @return nowSystemDate.
     */
    public Date getNowSystemDate() {
        return nowSystemDate;
    }

    
    /**
     * 设置 nowSystemDate
     * @param nowSystemDate nowSystemDate
     */
    public void setNowSystemDate(Date nowSystemDate) {
        this.nowSystemDate = nowSystemDate;
    }
    
    

}
