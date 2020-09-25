package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAstOffbeat extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 所属国家或地区
     */
    private String repCountryArea;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 资产价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAssetValue;

    /**
     * 资产收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAssetYield;

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
     * 取得 所属国家或地区
     * @return 所属国家或地区
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区
     * @param repCountryArea 所属国家或地区
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getRepVdate() {
        return repVdate;
    }

    /**
     * 设置 起息日
     * @param repVdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepVdate(Date repVdate) {
        this.repVdate = repVdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepMdate() {
        return repMdate;
    }

    /**
     * 设置 到期日
     * @param repMdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepMdate(Date repMdate) {
        this.repMdate = repMdate;
    }

    /**
     * 取得 资产价值
     * @return 资产价值
     */
    public BigDecimal getRepAssetValue() {
        return repAssetValue;
    }

    /**
     * 设置 资产价值
     * @param repAssetValue 资产价值
     */
    public void setRepAssetValue(BigDecimal repAssetValue) {
        this.repAssetValue = repAssetValue;
    }

    /**
     * 取得 资产收益率
     * @return 资产收益率
     */
    public BigDecimal getRepAssetYield() {
        return repAssetYield;
    }

    /**
     * 设置 资产收益率
     * @param repAssetYield 资产收益率
     */
    public void setRepAssetYield(BigDecimal repAssetYield) {
        this.repAssetYield = repAssetYield;
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