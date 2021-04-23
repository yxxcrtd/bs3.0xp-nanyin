package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ValTableBas extends ValTableBasKey {
    /**
     * 元素名称
     */
    private String itemName;

    /**
     * 元素类型
     */
    private String itemType;

    /**
     * 元素金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal itemAmt;

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
     * 取得 元素金额
     * @return 元素金额
     */
    public BigDecimal getItemAmt() {
        return itemAmt;
    }

    /**
     * 设置 元素金额
     * @param itemAmt 元素金额
     */
    public void setItemAmt(BigDecimal itemAmt) {
        this.itemAmt = itemAmt;
    }
}