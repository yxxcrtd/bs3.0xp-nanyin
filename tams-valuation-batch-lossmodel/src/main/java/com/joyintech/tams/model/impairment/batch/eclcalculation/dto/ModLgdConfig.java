package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class ModLgdConfig extends BaseEntity {
    /**
     * 序列号
     */
    private String seqno;

    /**
     * 资产类型(数据字典 证券品种_模型)
     */
    private String assetType;

    /**
     * 资产细分类(数据字典 证券细分类_模型)
     */
    private String assetDetailType;

    /**
     * 是否含有抵质押物
     */
    private String isContainPledge;

    /**
     * LGD(%)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal lgd;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序列号
     * @param seqno 序列号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 资产类型(数据字典 证券品种_模型)
     * @return 资产类型(数据字典 证券品种_模型)
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型(数据字典 证券品种_模型)
     * @param assetType 资产类型(数据字典 证券品种_模型)
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产细分类(数据字典 证券细分类_模型)
     * @return 资产细分类(数据字典 证券细分类_模型)
     */
    public String getAssetDetailType() {
        return assetDetailType;
    }

    /**
     * 设置 资产细分类(数据字典 证券细分类_模型)
     * @param assetDetailType 资产细分类(数据字典 证券细分类_模型)
     */
    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType == null ? null : assetDetailType.trim();
    }

    /**
     * 取得 是否含有抵质押物
     * @return 是否含有抵质押物
     */
    public String getIsContainPledge() {
        return isContainPledge;
    }

    /**
     * 设置 是否含有抵质押物
     * @param isContainPledge 是否含有抵质押物
     */
    public void setIsContainPledge(String isContainPledge) {
        this.isContainPledge = isContainPledge == null ? null : isContainPledge.trim();
    }

    /**
     * 取得 LGD(%)
     * @return LGD(%)
     */
    public BigDecimal getLgd() {
        return lgd;
    }

    /**
     * 设置 LGD(%)
     * @param lgd LGD(%)
     */
    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }
}