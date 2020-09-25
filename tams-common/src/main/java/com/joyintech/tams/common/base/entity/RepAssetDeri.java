package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepAssetDeri extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 名义本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repNomCapi;

    /**
     * 标的类别
     */
    private String repDeriType;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 名义本金
     * @return 名义本金
     */
    public BigDecimal getRepNomCapi() {
        return repNomCapi;
    }

    /**
     * 设置 名义本金
     * @param repNomCapi 名义本金
     */
    public void setRepNomCapi(BigDecimal repNomCapi) {
        this.repNomCapi = repNomCapi;
    }

    /**
     * 取得 标的类别
     * @return 标的类别
     */
    public String getRepDeriType() {
        return repDeriType;
    }

    /**
     * 设置 标的类别
     * @param repDeriType 标的类别
     */
    public void setRepDeriType(String repDeriType) {
        this.repDeriType = repDeriType == null ? null : repDeriType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}