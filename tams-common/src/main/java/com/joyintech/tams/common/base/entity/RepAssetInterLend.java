package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAssetInterLend extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 对手方
     */
    private String repTradeTarget;

    /**
     * 年利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRateYear;

    /**
     * 计息基础
     */
    private String repBasis;

    /**
     * 回购标的类别
     */
    private String repTargetType;

    /**
     * 回购标的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repTargetAmt;

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
     * 取得 对手方
     * @return 对手方
     */
    public String getRepTradeTarget() {
        return repTradeTarget;
    }

    /**
     * 设置 对手方
     * @param repTradeTarget 对手方
     */
    public void setRepTradeTarget(String repTradeTarget) {
        this.repTradeTarget = repTradeTarget == null ? null : repTradeTarget.trim();
    }

    /**
     * 取得 年利率
     * @return 年利率
     */
    public BigDecimal getRepRateYear() {
        return repRateYear;
    }

    /**
     * 设置 年利率
     * @param repRateYear 年利率
     */
    public void setRepRateYear(BigDecimal repRateYear) {
        this.repRateYear = repRateYear;
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getRepBasis() {
        return repBasis;
    }

    /**
     * 设置 计息基础
     * @param repBasis 计息基础
     */
    public void setRepBasis(String repBasis) {
        this.repBasis = repBasis == null ? null : repBasis.trim();
    }

    /**
     * 取得 回购标的类别
     * @return 回购标的类别
     */
    public String getRepTargetType() {
        return repTargetType;
    }

    /**
     * 设置 回购标的类别
     * @param repTargetType 回购标的类别
     */
    public void setRepTargetType(String repTargetType) {
        this.repTargetType = repTargetType == null ? null : repTargetType.trim();
    }

    /**
     * 取得 回购标的金额
     * @return 回购标的金额
     */
    public BigDecimal getRepTargetAmt() {
        return repTargetAmt;
    }

    /**
     * 设置 回购标的金额
     * @param repTargetAmt 回购标的金额
     */
    public void setRepTargetAmt(BigDecimal repTargetAmt) {
        this.repTargetAmt = repTargetAmt;
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