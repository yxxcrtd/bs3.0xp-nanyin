/*
 * 文件名：InvRateQuoKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.dto.calc;

import com.joyintech.framework.base.BaseEntity;

/**
 * 利率报价
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRateQuoKey
 * @since 1.0
 */
public class InvRateQuoKey extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 报价代码
     */
    private String yieldId;

    /**
     * 取得 报价代码
     * 
     * @return 报价代码
     */
    public String getYieldId() {
        return yieldId;
    }

    /**
     * 设置 报价代码
     * 
     * @param yieldId 报价代码
     */
    public void setYieldId(String yieldId) {
        this.yieldId = yieldId == null ? null : yieldId.trim();
    }
}
