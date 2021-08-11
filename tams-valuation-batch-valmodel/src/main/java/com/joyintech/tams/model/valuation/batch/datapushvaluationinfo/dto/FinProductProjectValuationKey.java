package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class FinProductProjectValuationKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 估值来源，专家法、模型法
     */
    private String valueSource;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 估值来源，专家法、模型法
     * @return 估值来源，专家法、模型法
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 估值来源，专家法、模型法
     * @param valueSource 估值来源，专家法、模型法
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource == null ? null : valueSource.trim();
    }
}