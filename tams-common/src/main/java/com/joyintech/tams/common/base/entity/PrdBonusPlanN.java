package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PrdBonusPlanN extends BaseEntity {
    /**
     * 分红方案编号
     */
    private String bonusPlanId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分红方案，按单位分红、按总额分红、净值归一
     */
    private String bonuScalcFlag;

    /**
     * 收益分配基准日
     */
    private Date baseDate;

    /**
     * 权益登记日
     */
    private Date regDate;

    /**
     * 除权日
     */
    private Date delDate;

    /**
     * 分红日
     */
    private Date bonusDate;

    /**
     * 净值日
     */
    private Date vluRefDate;

    /**
     * 单位分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitBonusVlu;

    /**
     * 总分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * null
     */
    private String isNetMormalize;

    /**
     * 取得 分红方案编号
     * @return 分红方案编号
     */
    public String getBonusPlanId() {
        return bonusPlanId;
    }

    /**
     * 设置 分红方案编号
     * @param bonusPlanId 分红方案编号
     */
    public void setBonusPlanId(String bonusPlanId) {
        this.bonusPlanId = bonusPlanId == null ? null : bonusPlanId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分红方案，按单位分红、按总额分红、净值归一
     * @return 分红方案，按单位分红、按总额分红、净值归一
     */
    public String getBonuScalcFlag() {
        return bonuScalcFlag;
    }

    /**
     * 设置 分红方案，按单位分红、按总额分红、净值归一
     * @param bonuScalcFlag 分红方案，按单位分红、按总额分红、净值归一
     */
    public void setBonuScalcFlag(String bonuScalcFlag) {
        this.bonuScalcFlag = bonuScalcFlag == null ? null : bonuScalcFlag.trim();
    }

    /**
     * 取得 收益分配基准日
     * @return 收益分配基准日
     */
    public Date getBaseDate() {
        return baseDate;
    }

    /**
     * 设置 收益分配基准日
     * @param baseDate 收益分配基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日
     * @param regDate 权益登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

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
     * 取得 分红日
     * @return 分红日
     */
    public Date getBonusDate() {
        return bonusDate;
    }

    /**
     * 设置 分红日
     * @param bonusDate 分红日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusDate(Date bonusDate) {
        this.bonusDate = bonusDate;
    }

    /**
     * 取得 净值日
     * @return 净值日
     */
    public Date getVluRefDate() {
        return vluRefDate;
    }

    /**
     * 设置 净值日
     * @param vluRefDate 净值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVluRefDate(Date vluRefDate) {
        this.vluRefDate = vluRefDate;
    }

    /**
     * 取得 单位分红金额
     * @return 单位分红金额
     */
    public BigDecimal getUnitBonusVlu() {
        return unitBonusVlu;
    }

    /**
     * 设置 单位分红金额
     * @param unitBonusVlu 单位分红金额
     */
    public void setUnitBonusVlu(BigDecimal unitBonusVlu) {
        this.unitBonusVlu = unitBonusVlu;
    }

    /**
     * 取得 总分红金额
     * @return 总分红金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置 总分红金额
     * @param totalAmt 总分红金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
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

    /**
     * 取得 null
     * @return null
     */
    public String getIsNetMormalize() {
        return isNetMormalize;
    }

    /**
     * 设置 null
     * @param isNetMormalize null
     */
    public void setIsNetMormalize(String isNetMormalize) {
        this.isNetMormalize = isNetMormalize == null ? null : isNetMormalize.trim();
    }
}