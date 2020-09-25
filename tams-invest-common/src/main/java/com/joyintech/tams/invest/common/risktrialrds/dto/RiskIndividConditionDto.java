/*
 * 文件名：RiskTrialDayTimeResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.risktrialrds.dto;

import com.joyintech.tams.common.base.entity.RskIndividCondition;

import java.math.BigDecimal;

/**
 * 个性化条件设置DTO
 *
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskIndividConditionDto
 * @since 1.0
 */
public class RiskIndividConditionDto extends RskIndividCondition {

    /**
     * 资产净值
     */
    private BigDecimal assetCprice;
    /**
     * 单位净值
     */
    private BigDecimal unitCprice;

    public BigDecimal getAssetCprice() {
        return assetCprice;
    }

    public void setAssetCprice(BigDecimal assetCprice) {
        this.assetCprice = assetCprice;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }
}
