package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinRateInfoStructure extends FinRateInfoStructureKey {
    /**
     * 结构类型
     */
    private String structureType;

    /**
     * 高障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highHindPrice;

    /**
     * 低障碍价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowHindPrice;

    /**
     * 高行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highExercPrice;

    /**
     * 低行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowExercPrice;

    /**
     * 执行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exePrice;

    /**
     * 敲出价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal knockPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 行权价基准日
     */
    private Date execBaseDate;

    /**
     * 取得 结构类型
     * @return 结构类型
     */
    public String getStructureType() {
        return structureType;
    }

    /**
     * 设置 结构类型
     * @param structureType 结构类型
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType == null ? null : structureType.trim();
    }

    /**
     * 取得 高障碍价
     * @return 高障碍价
     */
    public BigDecimal getHighHindPrice() {
        return highHindPrice;
    }

    /**
     * 设置 高障碍价
     * @param highHindPrice 高障碍价
     */
    public void setHighHindPrice(BigDecimal highHindPrice) {
        this.highHindPrice = highHindPrice;
    }

    /**
     * 取得 低障碍价
     * @return 低障碍价
     */
    public BigDecimal getLowHindPrice() {
        return lowHindPrice;
    }

    /**
     * 设置 低障碍价
     * @param lowHindPrice 低障碍价
     */
    public void setLowHindPrice(BigDecimal lowHindPrice) {
        this.lowHindPrice = lowHindPrice;
    }

    /**
     * 取得 高行权价
     * @return 高行权价
     */
    public BigDecimal getHighExercPrice() {
        return highExercPrice;
    }

    /**
     * 设置 高行权价
     * @param highExercPrice 高行权价
     */
    public void setHighExercPrice(BigDecimal highExercPrice) {
        this.highExercPrice = highExercPrice;
    }

    /**
     * 取得 低行权价
     * @return 低行权价
     */
    public BigDecimal getLowExercPrice() {
        return lowExercPrice;
    }

    /**
     * 设置 低行权价
     * @param lowExercPrice 低行权价
     */
    public void setLowExercPrice(BigDecimal lowExercPrice) {
        this.lowExercPrice = lowExercPrice;
    }

    /**
     * 取得 执行价格
     * @return 执行价格
     */
    public BigDecimal getExePrice() {
        return exePrice;
    }

    /**
     * 设置 执行价格
     * @param exePrice 执行价格
     */
    public void setExePrice(BigDecimal exePrice) {
        this.exePrice = exePrice;
    }

    /**
     * 取得 敲出价格
     * @return 敲出价格
     */
    public BigDecimal getKnockPrice() {
        return knockPrice;
    }

    /**
     * 设置 敲出价格
     * @param knockPrice 敲出价格
     */
    public void setKnockPrice(BigDecimal knockPrice) {
        this.knockPrice = knockPrice;
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
     * 取得 行权价基准日
     * @return 行权价基准日
     */
    public Date getExecBaseDate() {
        return execBaseDate;
    }

    /**
     * 设置 行权价基准日
     * @param execBaseDate 行权价基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExecBaseDate(Date execBaseDate) {
        this.execBaseDate = execBaseDate;
    }
}