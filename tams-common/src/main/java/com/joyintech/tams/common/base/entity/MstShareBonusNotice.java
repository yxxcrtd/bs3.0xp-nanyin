package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstShareBonusNotice extends MstShareBonusNoticeKey {
    /**
     * 除权除息日
     */
    private Date delDate;

    /**
     * 派息日
     */
    private Date devidentDate;

    /**
     * 红股上市日
     */
    private Date redshareListingDate;

    /**
     * 每股送转
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal perDevidentAmt;

    /**
     * 每股派息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal perDevidentCash;

    /**
     * 取得 除权除息日
     * @return 除权除息日
     */
    public Date getDelDate() {
        return delDate;
    }

    /**
     * 设置 除权除息日
     * @param delDate 除权除息日
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
     * 取得 红股上市日
     * @return 红股上市日
     */
    public Date getRedshareListingDate() {
        return redshareListingDate;
    }

    /**
     * 设置 红股上市日
     * @param redshareListingDate 红股上市日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRedshareListingDate(Date redshareListingDate) {
        this.redshareListingDate = redshareListingDate;
    }

    /**
     * 取得 每股送转
     * @return 每股送转
     */
    public BigDecimal getPerDevidentAmt() {
        return perDevidentAmt;
    }

    /**
     * 设置 每股送转
     * @param perDevidentAmt 每股送转
     */
    public void setPerDevidentAmt(BigDecimal perDevidentAmt) {
        this.perDevidentAmt = perDevidentAmt;
    }

    /**
     * 取得 每股派息
     * @return 每股派息
     */
    public BigDecimal getPerDevidentCash() {
        return perDevidentCash;
    }

    /**
     * 设置 每股派息
     * @param perDevidentCash 每股派息
     */
    public void setPerDevidentCash(BigDecimal perDevidentCash) {
        this.perDevidentCash = perDevidentCash;
    }
}