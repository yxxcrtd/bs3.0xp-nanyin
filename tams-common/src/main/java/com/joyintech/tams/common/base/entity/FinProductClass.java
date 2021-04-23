package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinProductClass extends BaseEntity {
    /**
     * 流水号
     */
    private String optsn;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 资产类型名称
     */
    private String assetTypeName;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 机构核算分类
     */
    private String orgClass;

    /**
     * 机构核算分类名称
     */
    private String orgClassName;

    /**
     * 核算分类
     */
    private String accountingClass;

    /**
     * 核算分类名称
     */
    private String accountingClassName;

    /**
     * 税务分类
     */
    private String taxClass;

    /**
     * 税务分类名称
     */
    private String taxClassName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 收益类型名称
     */
    private String profitTypeName;

    /**
     * 场内基金类型
     */
    private String trmFundType;

    /**
     * 场内基金类型名称
     */
    private String trmFundTypeName;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getOptsn() {
        return optsn;
    }

    /**
     * 设置 流水号
     * @param optsn 流水号
     */
    public void setOptsn(String optsn) {
        this.optsn = optsn == null ? null : optsn.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型
     * @param assetType 资产类型
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产类型名称
     * @return 资产类型名称
     */
    public String getAssetTypeName() {
        return assetTypeName;
    }

    /**
     * 设置 资产类型名称
     * @param assetTypeName 资产类型名称
     */
    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName == null ? null : assetTypeName.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
    }

    /**
     * 取得 机构核算分类
     * @return 机构核算分类
     */
    public String getOrgClass() {
        return orgClass;
    }

    /**
     * 设置 机构核算分类
     * @param orgClass 机构核算分类
     */
    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass == null ? null : orgClass.trim();
    }

    /**
     * 取得 机构核算分类名称
     * @return 机构核算分类名称
     */
    public String getOrgClassName() {
        return orgClassName;
    }

    /**
     * 设置 机构核算分类名称
     * @param orgClassName 机构核算分类名称
     */
    public void setOrgClassName(String orgClassName) {
        this.orgClassName = orgClassName == null ? null : orgClassName.trim();
    }

    /**
     * 取得 核算分类
     * @return 核算分类
     */
    public String getAccountingClass() {
        return accountingClass;
    }

    /**
     * 设置 核算分类
     * @param accountingClass 核算分类
     */
    public void setAccountingClass(String accountingClass) {
        this.accountingClass = accountingClass == null ? null : accountingClass.trim();
    }

    /**
     * 取得 核算分类名称
     * @return 核算分类名称
     */
    public String getAccountingClassName() {
        return accountingClassName;
    }

    /**
     * 设置 核算分类名称
     * @param accountingClassName 核算分类名称
     */
    public void setAccountingClassName(String accountingClassName) {
        this.accountingClassName = accountingClassName == null ? null : accountingClassName.trim();
    }

    /**
     * 取得 税务分类
     * @return 税务分类
     */
    public String getTaxClass() {
        return taxClass;
    }

    /**
     * 设置 税务分类
     * @param taxClass 税务分类
     */
    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass == null ? null : taxClass.trim();
    }

    /**
     * 取得 税务分类名称
     * @return 税务分类名称
     */
    public String getTaxClassName() {
        return taxClassName;
    }

    /**
     * 设置 税务分类名称
     * @param taxClassName 税务分类名称
     */
    public void setTaxClassName(String taxClassName) {
        this.taxClassName = taxClassName == null ? null : taxClassName.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 收益类型名称
     * @return 收益类型名称
     */
    public String getProfitTypeName() {
        return profitTypeName;
    }

    /**
     * 设置 收益类型名称
     * @param profitTypeName 收益类型名称
     */
    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName == null ? null : profitTypeName.trim();
    }

    /**
     * 取得 场内基金类型
     * @return 场内基金类型
     */
    public String getTrmFundType() {
        return trmFundType;
    }

    /**
     * 设置 场内基金类型
     * @param trmFundType 场内基金类型
     */
    public void setTrmFundType(String trmFundType) {
        this.trmFundType = trmFundType == null ? null : trmFundType.trim();
    }

    /**
     * 取得 场内基金类型名称
     * @return 场内基金类型名称
     */
    public String getTrmFundTypeName() {
        return trmFundTypeName;
    }

    /**
     * 设置 场内基金类型名称
     * @param trmFundTypeName 场内基金类型名称
     */
    public void setTrmFundTypeName(String trmFundTypeName) {
        this.trmFundTypeName = trmFundTypeName == null ? null : trmFundTypeName.trim();
    }
}