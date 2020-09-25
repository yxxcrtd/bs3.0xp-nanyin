package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RiskWeight extends BaseEntity {
    /**
     * 风险权数代码
     */
    private String seqno;

    /**
     * 一级分类
     */
    private String assetTypeOne;

    /**
     * 二级分类
     */
    private String assetTypeTwo;

    /**
     * 风险权数%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal riskWeight;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 风险权数代码
     * @return 风险权数代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 风险权数代码
     * @param seqno 风险权数代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 一级分类
     * @return 一级分类
     */
    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    /**
     * 设置 一级分类
     * @param assetTypeOne 一级分类
     */
    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne == null ? null : assetTypeOne.trim();
    }

    /**
     * 取得 二级分类
     * @return 二级分类
     */
    public String getAssetTypeTwo() {
        return assetTypeTwo;
    }

    /**
     * 设置 二级分类
     * @param assetTypeTwo 二级分类
     */
    public void setAssetTypeTwo(String assetTypeTwo) {
        this.assetTypeTwo = assetTypeTwo == null ? null : assetTypeTwo.trim();
    }

    /**
     * 取得 风险权数%
     * @return 风险权数%
     */
    public BigDecimal getRiskWeight() {
        return riskWeight;
    }

    /**
     * 设置 风险权数%
     * @param riskWeight 风险权数%
     */
    public void setRiskWeight(BigDecimal riskWeight) {
        this.riskWeight = riskWeight;
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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