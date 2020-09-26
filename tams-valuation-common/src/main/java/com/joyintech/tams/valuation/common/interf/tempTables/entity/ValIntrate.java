package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValIntrate extends BaseEntity {
    /**
     * 利率代码
     */
    private String intRateId;

    /**
     * 利率名称
     */
    private String intRateName;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 发布日
     */
    private Date relaeseDate;

    /**
     * 利率值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 取得 利率代码
     * @return 利率代码
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 利率代码
     * @param intRateId 利率代码
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 利率名称
     * @return 利率名称
     */
    public String getIntRateName() {
        return intRateName;
    }

    /**
     * 设置 利率名称
     * @param intRateName 利率名称
     */
    public void setIntRateName(String intRateName) {
        this.intRateName = intRateName == null ? null : intRateName.trim();
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
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
     * 取得 发布日
     * @return 发布日
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }

    /**
     * 设置 发布日
     * @param relaeseDate 发布日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }

    /**
     * 取得 利率值
     * @return 利率值
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 利率值
     * @param intRate 利率值
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}