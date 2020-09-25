package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class OpicsParam extends OpicsParamKey {
    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 项目值(中文)
     */
    private String itemValueCn;

    /**
     * 项目值(英文)
     */
    private String itemValueEn;

    /**
     * 排序
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orders;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 项目名称
     * @return 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置 项目名称
     * @param itemName 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 取得 项目值(中文)
     * @return 项目值(中文)
     */
    public String getItemValueCn() {
        return itemValueCn;
    }

    /**
     * 设置 项目值(中文)
     * @param itemValueCn 项目值(中文)
     */
    public void setItemValueCn(String itemValueCn) {
        this.itemValueCn = itemValueCn == null ? null : itemValueCn.trim();
    }

    /**
     * 取得 项目值(英文)
     * @return 项目值(英文)
     */
    public String getItemValueEn() {
        return itemValueEn;
    }

    /**
     * 设置 项目值(英文)
     * @param itemValueEn 项目值(英文)
     */
    public void setItemValueEn(String itemValueEn) {
        this.itemValueEn = itemValueEn == null ? null : itemValueEn.trim();
    }

    /**
     * 取得 排序
     * @return 排序
     */
    public BigDecimal getOrders() {
        return orders;
    }

    /**
     * 设置 排序
     * @param orders 排序
     */
    public void setOrders(BigDecimal orders) {
        this.orders = orders;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}