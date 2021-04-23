package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstFundBonusNotice extends MstFundBonusNoticeKey {
    /**
     * 除权日
     */
    private Date delDate;

    /**
     * 派息日
     */
    private Date devidentDate;

    /**
     * 单位分红净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusUnitValue;

    /**
     * 取得 除权日
     * @return 除权日
     */
    public Date getDelDate() {
        return delDate;
    }

    /**
     * 设置 除权日
     * @param delDate 除权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    /**
     * 取得 派息日
     * @return 派息日
     */
    public Date getDevidentDate() {
        return devidentDate;
    }

    /**
     * 设置 派息日
     * @param devidentDate 派息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDevidentDate(Date devidentDate) {
        this.devidentDate = devidentDate;
    }

    /**
     * 取得 单位分红净值
     * @return 单位分红净值
     */
    public BigDecimal getUnitBonusUnitValue() {
        return unitBonusUnitValue;
    }

    /**
     * 设置 单位分红净值
     * @param unitBonusUnitValue 单位分红净值
     */
    public void setUnitBonusUnitValue(BigDecimal unitBonusUnitValue) {
        this.unitBonusUnitValue = unitBonusUnitValue;
    }
}