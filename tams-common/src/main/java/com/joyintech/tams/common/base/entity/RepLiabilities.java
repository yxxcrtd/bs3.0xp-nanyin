package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepLiabilities extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 起息日
     */
    private Date repValueDate;

    /**
     * 到期日
     */
    private Date repExpiringDate;

    /**
     * 对手方
     */
    private String repCounterparty;

    /**
     * 年利率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAnnualInterestRate;

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
    private BigDecimal repBuyBackMoney;

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
    public Date getRepValueDate() {
        return repValueDate;
    }

    /**
     * 设置 起息日
     * @param repValueDate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepValueDate(Date repValueDate) {
        this.repValueDate = repValueDate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepExpiringDate() {
        return repExpiringDate;
    }

    /**
     * 设置 到期日
     * @param repExpiringDate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepExpiringDate(Date repExpiringDate) {
        this.repExpiringDate = repExpiringDate;
    }

    /**
     * 取得 对手方
     * @return 对手方
     */
    public String getRepCounterparty() {
        return repCounterparty;
    }

    /**
     * 设置 对手方
     * @param repCounterparty 对手方
     */
    public void setRepCounterparty(String repCounterparty) {
        this.repCounterparty = repCounterparty == null ? null : repCounterparty.trim();
    }

    /**
     * 取得 年利率%
     * @return 年利率%
     */
    public BigDecimal getRepAnnualInterestRate() {
        return repAnnualInterestRate;
    }

    /**
     * 设置 年利率%
     * @param repAnnualInterestRate 年利率%
     */
    public void setRepAnnualInterestRate(BigDecimal repAnnualInterestRate) {
        this.repAnnualInterestRate = repAnnualInterestRate;
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
    public BigDecimal getRepBuyBackMoney() {
        return repBuyBackMoney;
    }

    /**
     * 设置 回购标的金额
     * @param repBuyBackMoney 回购标的金额
     */
    public void setRepBuyBackMoney(BigDecimal repBuyBackMoney) {
        this.repBuyBackMoney = repBuyBackMoney;
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