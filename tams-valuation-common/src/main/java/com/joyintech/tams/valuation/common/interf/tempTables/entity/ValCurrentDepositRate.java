package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValCurrentDepositRate extends BaseEntity {
    /**
     * 资金账户账号
     */
    private String acctId;

    /**
     * 资金账户户名
     */
    private String acctName;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 重置类型
     */
    private String resetType;

    /**
     * 重置频率
     */
    private String resetFrequency;

    /**
     * 首次重置日
     */
    private Date firstResetDate;

    /**
     * 指定重置日期
     */
    private String appointResetDate;

    /**
     * 提前观察数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal obsCycleNumber;

    /**
     * 提前观察单位
     */
    private String obsCycleUnit;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMax;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMin;

    /**
     * 基准类型
     */
    private String linkRateType;

    /**
     * 基准利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal linkRate;

    /**
     * 基准利率编号
     */
    private String intRateId;

    /**
     * 计息基础
     */
    private String chargeBase;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 取得 资金账户账号
     * @return 资金账户账号
     */
    public String getAcctId() {
        return acctId;
    }

    /**
     * 设置 资金账户账号
     * @param acctId 资金账户账号
     */
    public void setAcctId(String acctId) {
        this.acctId = acctId == null ? null : acctId.trim();
    }

    /**
     * 取得 资金账户户名
     * @return 资金账户户名
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金账户户名
     * @param acctName 资金账户户名
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
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
     * 取得 重置类型
     * @return 重置类型
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * 设置 重置类型
     * @param resetType 重置类型
     */
    public void setResetType(String resetType) {
        this.resetType = resetType == null ? null : resetType.trim();
    }

    /**
     * 取得 重置频率
     * @return 重置频率
     */
    public String getResetFrequency() {
        return resetFrequency;
    }

    /**
     * 设置 重置频率
     * @param resetFrequency 重置频率
     */
    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency == null ? null : resetFrequency.trim();
    }

    /**
     * 取得 首次重置日
     * @return 首次重置日
     */
    public Date getFirstResetDate() {
        return firstResetDate;
    }

    /**
     * 设置 首次重置日
     * @param firstResetDate 首次重置日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstResetDate(Date firstResetDate) {
        this.firstResetDate = firstResetDate;
    }

    /**
     * 取得 指定重置日期
     * @return 指定重置日期
     */
    public String getAppointResetDate() {
        return appointResetDate;
    }

    /**
     * 设置 指定重置日期
     * @param appointResetDate 指定重置日期
     */
    public void setAppointResetDate(String appointResetDate) {
        this.appointResetDate = appointResetDate == null ? null : appointResetDate.trim();
    }

    /**
     * 取得 提前观察数量
     * @return 提前观察数量
     */
    public BigDecimal getObsCycleNumber() {
        return obsCycleNumber;
    }

    /**
     * 设置 提前观察数量
     * @param obsCycleNumber 提前观察数量
     */
    public void setObsCycleNumber(BigDecimal obsCycleNumber) {
        this.obsCycleNumber = obsCycleNumber;
    }

    /**
     * 取得 提前观察单位
     * @return 提前观察单位
     */
    public String getObsCycleUnit() {
        return obsCycleUnit;
    }

    /**
     * 设置 提前观察单位
     * @param obsCycleUnit 提前观察单位
     */
    public void setObsCycleUnit(String obsCycleUnit) {
        this.obsCycleUnit = obsCycleUnit == null ? null : obsCycleUnit.trim();
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 初始利率
     * @param intRate 初始利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 系数
     * @param ratio 系数
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getRateMax() {
        return rateMax;
    }

    /**
     * 设置 利率上限
     * @param rateMax 利率上限
     */
    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getRateMin() {
        return rateMin;
    }

    /**
     * 设置 利率下限
     * @param rateMin 利率下限
     */
    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    /**
     * 取得 基准类型
     * @return 基准类型
     */
    public String getLinkRateType() {
        return linkRateType;
    }

    /**
     * 设置 基准类型
     * @param linkRateType 基准类型
     */
    public void setLinkRateType(String linkRateType) {
        this.linkRateType = linkRateType == null ? null : linkRateType.trim();
    }

    /**
     * 取得 基准利率
     * @return 基准利率
     */
    public BigDecimal getLinkRate() {
        return linkRate;
    }

    /**
     * 设置 基准利率
     * @param linkRate 基准利率
     */
    public void setLinkRate(BigDecimal linkRate) {
        this.linkRate = linkRate;
    }

    /**
     * 取得 基准利率编号
     * @return 基准利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 基准利率编号
     * @param intRateId 基准利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getChargeBase() {
        return chargeBase;
    }

    /**
     * 设置 计息基础
     * @param chargeBase 计息基础
     */
    public void setChargeBase(String chargeBase) {
        this.chargeBase = chargeBase == null ? null : chargeBase.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }
}