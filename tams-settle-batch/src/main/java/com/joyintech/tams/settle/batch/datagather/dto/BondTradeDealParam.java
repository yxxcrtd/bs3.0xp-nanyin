package com.joyintech.tams.settle.batch.datagather.dto;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;

/**
 * 债券收益率计算参数
 * @author 何阳阳
 * @date 2020/7/25
 * @see BondTradeDealParam
 * @since 1.0
 */
public class BondTradeDealParam extends TrdFinProductDealTotal {
    /**
     * 计算类型
     */
    private String calculateField;

    public String getCalculateField() {
        return calculateField;
    }

    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }
}
