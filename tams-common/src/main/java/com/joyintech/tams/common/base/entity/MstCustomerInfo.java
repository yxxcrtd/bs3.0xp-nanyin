package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCustomerInfo extends BaseEntity {
    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户简称
     */
    private String customerAbbr;

    /**
     * 客户类型，企业法人、自然人
     */
    private String customerType;

    /**
     * 是否发行人
     */
    private String isIssuer;

    /**
     * 是否管理人
     */
    private String isAssteManager;

    /**
     * 是否托管人
     */
    private String isTruster;

    /**
     * 是否承销商
     */
    private String isSaler;

    /**
     * 是否融资人
     */
    private String isFinancier;

    /**
     * 是否担保人
     */
    private String isGuarantee;

    /**
     * 是否评级机构
     */
    private String isRatingAgencies;

    /**
     * null
     */
    private String isPledgor;

    /**
     * null
     */
    private String isDepositBank;

    /**
     * null
     */
    private String isInvestAdviser;

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置 客户名称
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * 取得 客户简称
     * @return 客户简称
     */
    public String getCustomerAbbr() {
        return customerAbbr;
    }

    /**
     * 设置 客户简称
     * @param customerAbbr 客户简称
     */
    public void setCustomerAbbr(String customerAbbr) {
        this.customerAbbr = customerAbbr == null ? null : customerAbbr.trim();
    }

    /**
     * 取得 客户类型，企业法人、自然人
     * @return 客户类型，企业法人、自然人
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置 客户类型，企业法人、自然人
     * @param customerType 客户类型，企业法人、自然人
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 取得 是否发行人
     * @return 是否发行人
     */
    public String getIsIssuer() {
        return isIssuer;
    }

    /**
     * 设置 是否发行人
     * @param isIssuer 是否发行人
     */
    public void setIsIssuer(String isIssuer) {
        this.isIssuer = isIssuer == null ? null : isIssuer.trim();
    }

    /**
     * 取得 是否管理人
     * @return 是否管理人
     */
    public String getIsAssteManager() {
        return isAssteManager;
    }

    /**
     * 设置 是否管理人
     * @param isAssteManager 是否管理人
     */
    public void setIsAssteManager(String isAssteManager) {
        this.isAssteManager = isAssteManager == null ? null : isAssteManager.trim();
    }

    /**
     * 取得 是否托管人
     * @return 是否托管人
     */
    public String getIsTruster() {
        return isTruster;
    }

    /**
     * 设置 是否托管人
     * @param isTruster 是否托管人
     */
    public void setIsTruster(String isTruster) {
        this.isTruster = isTruster == null ? null : isTruster.trim();
    }

    /**
     * 取得 是否承销商
     * @return 是否承销商
     */
    public String getIsSaler() {
        return isSaler;
    }

    /**
     * 设置 是否承销商
     * @param isSaler 是否承销商
     */
    public void setIsSaler(String isSaler) {
        this.isSaler = isSaler == null ? null : isSaler.trim();
    }

    /**
     * 取得 是否融资人
     * @return 是否融资人
     */
    public String getIsFinancier() {
        return isFinancier;
    }

    /**
     * 设置 是否融资人
     * @param isFinancier 是否融资人
     */
    public void setIsFinancier(String isFinancier) {
        this.isFinancier = isFinancier == null ? null : isFinancier.trim();
    }

    /**
     * 取得 是否担保人
     * @return 是否担保人
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置 是否担保人
     * @param isGuarantee 是否担保人
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee == null ? null : isGuarantee.trim();
    }

    /**
     * 取得 是否评级机构
     * @return 是否评级机构
     */
    public String getIsRatingAgencies() {
        return isRatingAgencies;
    }

    /**
     * 设置 是否评级机构
     * @param isRatingAgencies 是否评级机构
     */
    public void setIsRatingAgencies(String isRatingAgencies) {
        this.isRatingAgencies = isRatingAgencies == null ? null : isRatingAgencies.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsPledgor() {
        return isPledgor;
    }

    /**
     * 设置 null
     * @param isPledgor null
     */
    public void setIsPledgor(String isPledgor) {
        this.isPledgor = isPledgor == null ? null : isPledgor.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsDepositBank() {
        return isDepositBank;
    }

    /**
     * 设置 null
     * @param isDepositBank null
     */
    public void setIsDepositBank(String isDepositBank) {
        this.isDepositBank = isDepositBank == null ? null : isDepositBank.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsInvestAdviser() {
        return isInvestAdviser;
    }

    /**
     * 设置 null
     * @param isInvestAdviser null
     */
    public void setIsInvestAdviser(String isInvestAdviser) {
        this.isInvestAdviser = isInvestAdviser == null ? null : isInvestAdviser.trim();
    }
}