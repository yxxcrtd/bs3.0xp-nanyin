package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class RskEventParaRel extends RskEventParaRelKey {
    /**
     * 关系类型（加减乘除等）
     */
    private String relType;

    /**
     * 关系对象（数量、价格、金额）
     */
    private String relItem;

    /**
     * 备注
     */
    private String remark;

    /**
     * null
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal customVal;

    /**
     * 取得 关系类型（加减乘除等）
     * @return 关系类型（加减乘除等）
     */
    public String getRelType() {
        return relType;
    }

    /**
     * 设置 关系类型（加减乘除等）
     * @param relType 关系类型（加减乘除等）
     */
    public void setRelType(String relType) {
        this.relType = relType == null ? null : relType.trim();
    }

    /**
     * 取得 关系对象（数量、价格、金额）
     * @return 关系对象（数量、价格、金额）
     */
    public String getRelItem() {
        return relItem;
    }

    /**
     * 设置 关系对象（数量、价格、金额）
     * @param relItem 关系对象（数量、价格、金额）
     */
    public void setRelItem(String relItem) {
        this.relItem = relItem == null ? null : relItem.trim();
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

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getCustomVal() {
        return customVal;
    }

    /**
     * 设置 null
     * @param customVal null
     */
    public void setCustomVal(BigDecimal customVal) {
        this.customVal = customVal;
    }
}