package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinStdsecConverseRadio extends FinStdsecConverseRadioKey {
    /**
     * 结束适用日
     */
    private Date mdate;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scale;

    /**
     * 折合标准券，每100券面折合标准券金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cvnperhundred;

    /**
     * 取得 结束适用日
     * @return 结束适用日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结束适用日
     * @param mdate 结束适用日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置 折算比例
     * @param scale 折算比例
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    /**
     * 取得 折合标准券，每100券面折合标准券金额
     * @return 折合标准券，每100券面折合标准券金额
     */
    public BigDecimal getCvnperhundred() {
        return cvnperhundred;
    }

    /**
     * 设置 折合标准券，每100券面折合标准券金额
     * @param cvnperhundred 折合标准券，每100券面折合标准券金额
     */
    public void setCvnperhundred(BigDecimal cvnperhundred) {
        this.cvnperhundred = cvnperhundred;
    }
}