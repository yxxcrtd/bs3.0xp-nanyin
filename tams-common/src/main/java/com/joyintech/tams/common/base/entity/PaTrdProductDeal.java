package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PaTrdProductDeal extends BaseEntity {
    /**
     * ????
     */
    private String tradeId;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryRate;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actRate;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryInt;

    /**
     * ????
     */
    private String basis;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainPrinAmt;

    /**
     * ??
     */
    private String branch;

    /**
     * ????????????????????????????????????????
     */
    private String paBussType;

    /**
     * ????????????????
     */
    private String deptCode;

    /**
     * ??/????
     */
    private String backLendDirection;

    /**
     * ????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoryAmt;

    /**
     * ??????
     */
    private String orderBussType;

    /**
     * ????????
     */
    private String counterSecurityTrust;

    /**
     * ?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customersBeginNum;

    /**
     * ?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customersAddNum;

    /**
     * ?????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customersReduceNum;

    /**
     * ?????Y???N???
     */
    private String isBreak;

    /**
     * ???????????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeamt;

    /**
     * ??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yesterdayDebtValuation;

    /**
     * ??????????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal priceOutdebtValuation;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal aconShareAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bconShareAmt;

    /**
     * 取得 ????
     * @return ????
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 ????
     * @param tradeId ????
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getTheoryRate() {
        return theoryRate;
    }

    /**
     * 设置 ????
     * @param theoryRate ????
     */
    public void setTheoryRate(BigDecimal theoryRate) {
        this.theoryRate = theoryRate;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getActRate() {
        return actRate;
    }

    /**
     * 设置 ????
     * @param actRate ????
     */
    public void setActRate(BigDecimal actRate) {
        this.actRate = actRate;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getTheoryInt() {
        return theoryInt;
    }

    /**
     * 设置 ????
     * @param theoryInt ????
     */
    public void setTheoryInt(BigDecimal theoryInt) {
        this.theoryInt = theoryInt;
    }

    /**
     * 取得 ????
     * @return ????
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 ????
     * @param basis ????
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getRemainPrinAmt() {
        return remainPrinAmt;
    }

    /**
     * 设置 ????
     * @param remainPrinAmt ????
     */
    public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
        this.remainPrinAmt = remainPrinAmt;
    }

    /**
     * 取得 ??
     * @return ??
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 ??
     * @param branch ??
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * 取得 ????????????????????????????????????????
     * @return ????????????????????????????????????????
     */
    public String getPaBussType() {
        return paBussType;
    }

    /**
     * 设置 ????????????????????????????????????????
     * @param paBussType ????????????????????????????????????????
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType == null ? null : paBussType.trim();
    }

    /**
     * 取得 ????????????????
     * @return ????????????????
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 ????????????????
     * @param deptCode ????????????????
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 ??/????
     * @return ??/????
     */
    public String getBackLendDirection() {
        return backLendDirection;
    }

    /**
     * 设置 ??/????
     * @param backLendDirection ??/????
     */
    public void setBackLendDirection(String backLendDirection) {
        this.backLendDirection = backLendDirection == null ? null : backLendDirection.trim();
    }

    /**
     * 取得 ????
     * @return ????
     */
    public BigDecimal getTheoryAmt() {
        return theoryAmt;
    }

    /**
     * 设置 ????
     * @param theoryAmt ????
     */
    public void setTheoryAmt(BigDecimal theoryAmt) {
        this.theoryAmt = theoryAmt;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public String getOrderBussType() {
        return orderBussType;
    }

    /**
     * 设置 ??????
     * @param orderBussType ??????
     */
    public void setOrderBussType(String orderBussType) {
        this.orderBussType = orderBussType == null ? null : orderBussType.trim();
    }

    /**
     * 取得 ????????
     * @return ????????
     */
    public String getCounterSecurityTrust() {
        return counterSecurityTrust;
    }

    /**
     * 设置 ????????
     * @param counterSecurityTrust ????????
     */
    public void setCounterSecurityTrust(String counterSecurityTrust) {
        this.counterSecurityTrust = counterSecurityTrust == null ? null : counterSecurityTrust.trim();
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public BigDecimal getCustomersBeginNum() {
        return customersBeginNum;
    }

    /**
     * 设置 ?????
     * @param customersBeginNum ?????
     */
    public void setCustomersBeginNum(BigDecimal customersBeginNum) {
        this.customersBeginNum = customersBeginNum;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public BigDecimal getCustomersAddNum() {
        return customersAddNum;
    }

    /**
     * 设置 ?????
     * @param customersAddNum ?????
     */
    public void setCustomersAddNum(BigDecimal customersAddNum) {
        this.customersAddNum = customersAddNum;
    }

    /**
     * 取得 ?????
     * @return ?????
     */
    public BigDecimal getCustomersReduceNum() {
        return customersReduceNum;
    }

    /**
     * 设置 ?????
     * @param customersReduceNum ?????
     */
    public void setCustomersReduceNum(BigDecimal customersReduceNum) {
        this.customersReduceNum = customersReduceNum;
    }

    /**
     * 取得 ?????Y???N???
     * @return ?????Y???N???
     */
    public String getIsBreak() {
        return isBreak;
    }

    /**
     * 设置 ?????Y???N???
     * @param isBreak ?????Y???N???
     */
    public void setIsBreak(String isBreak) {
        this.isBreak = isBreak == null ? null : isBreak.trim();
    }

    /**
     * 取得 ???????????
     * @return ???????????
     */
    public BigDecimal getFeeamt() {
        return feeamt;
    }

    /**
     * 设置 ???????????
     * @param feeamt ???????????
     */
    public void setFeeamt(BigDecimal feeamt) {
        this.feeamt = feeamt;
    }

    /**
     * 取得 ??????
     * @return ??????
     */
    public BigDecimal getYesterdayDebtValuation() {
        return yesterdayDebtValuation;
    }

    /**
     * 设置 ??????
     * @param yesterdayDebtValuation ??????
     */
    public void setYesterdayDebtValuation(BigDecimal yesterdayDebtValuation) {
        this.yesterdayDebtValuation = yesterdayDebtValuation;
    }

    /**
     * 取得 ??????????
     * @return ??????????
     */
    public BigDecimal getPriceOutdebtValuation() {
        return priceOutdebtValuation;
    }

    /**
     * 设置 ??????????
     * @param priceOutdebtValuation ??????????
     */
    public void setPriceOutdebtValuation(BigDecimal priceOutdebtValuation) {
        this.priceOutdebtValuation = priceOutdebtValuation;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getAconShareAmt() {
        return aconShareAmt;
    }

    /**
     * 设置 null
     * @param aconShareAmt null
     */
    public void setAconShareAmt(BigDecimal aconShareAmt) {
        this.aconShareAmt = aconShareAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getBconShareAmt() {
        return bconShareAmt;
    }

    /**
     * 设置 null
     * @param bconShareAmt null
     */
    public void setBconShareAmt(BigDecimal bconShareAmt) {
        this.bconShareAmt = bconShareAmt;
    }
}