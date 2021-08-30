package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ModStockdivitrateConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 资产类型
     */
    private String secTypeModel;

    /**
     * 资产细分类
     */
    private String secDetailTypeModel;

    /**
     * 预期股息增长率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal stockIntrate;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @return 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @param finprodId 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getSecTypeModel() {
        return secTypeModel;
    }

    /**
     * 设置 资产类型
     * @param secTypeModel 资产类型
     */
    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel == null ? null : secTypeModel.trim();
    }

    /**
     * 取得 资产细分类
     * @return 资产细分类
     */
    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    /**
     * 设置 资产细分类
     * @param secDetailTypeModel 资产细分类
     */
    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel == null ? null : secDetailTypeModel.trim();
    }

    /**
     * 取得 预期股息增长率
     * @return 预期股息增长率
     */
    public BigDecimal getStockIntrate() {
        return stockIntrate;
    }

    /**
     * 设置 预期股息增长率
     * @param stockIntrate 预期股息增长率
     */
    public void setStockIntrate(BigDecimal stockIntrate) {
        this.stockIntrate = stockIntrate;
    }
}