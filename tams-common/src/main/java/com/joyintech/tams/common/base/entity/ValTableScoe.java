package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ValTableScoe extends ValTableScoeKey {
    /**
     * 元素名称
     */
    private String itemName;

    /**
     * 元素类型
     */
    private String itemType;

    /**
     * 元素金额1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal item1Amt;

    /**
     * 元素金额2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal item2Amt;

    /**
     * 元素金额3
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal item3Amt;

    /**
     * 元素金额4
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal item4Amt;

    /**
     * 取得 元素名称
     * @return 元素名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置 元素名称
     * @param itemName 元素名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 取得 元素类型
     * @return 元素类型
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置 元素类型
     * @param itemType 元素类型
     */
    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    /**
     * 取得 元素金额1
     * @return 元素金额1
     */
    public BigDecimal getItem1Amt() {
        return item1Amt;
    }

    /**
     * 设置 元素金额1
     * @param item1Amt 元素金额1
     */
    public void setItem1Amt(BigDecimal item1Amt) {
        this.item1Amt = item1Amt;
    }

    /**
     * 取得 元素金额2
     * @return 元素金额2
     */
    public BigDecimal getItem2Amt() {
        return item2Amt;
    }

    /**
     * 设置 元素金额2
     * @param item2Amt 元素金额2
     */
    public void setItem2Amt(BigDecimal item2Amt) {
        this.item2Amt = item2Amt;
    }

    /**
     * 取得 元素金额3
     * @return 元素金额3
     */
    public BigDecimal getItem3Amt() {
        return item3Amt;
    }

    /**
     * 设置 元素金额3
     * @param item3Amt 元素金额3
     */
    public void setItem3Amt(BigDecimal item3Amt) {
        this.item3Amt = item3Amt;
    }

    /**
     * 取得 元素金额4
     * @return 元素金额4
     */
    public BigDecimal getItem4Amt() {
        return item4Amt;
    }

    /**
     * 设置 元素金额4
     * @param item4Amt 元素金额4
     */
    public void setItem4Amt(BigDecimal item4Amt) {
        this.item4Amt = item4Amt;
    }
}