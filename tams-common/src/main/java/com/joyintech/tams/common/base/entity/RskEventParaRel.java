package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.util.BigDecimalSerializer;

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
     * 自定义值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal customVal;

    /**
     * 公式
     */
    private String formulaVal;

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

    public BigDecimal getCustomVal() {
        return customVal;
    }

    public void setCustomVal(BigDecimal customVal) {
        this.customVal = customVal;
    }

    /**
     * 取得 公式
     * @return 公式
     */
    public String getFormulaVal() {
        return formulaVal;
    }

    /**
     * 设置 公式
     * @param formulaVal 公式
     */
    public void setFormulaVal(String formulaVal) {
        this.formulaVal = formulaVal == null ? null : formulaVal.trim();
    }
}