package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokFeeTableSetKey extends BaseEntity {
    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 费用类型代码
     */
    private String feeTypeId;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    
    /**
     * 记账金额元素
     */    
    private String  moneyElement;

    public String getMoneyElement() {
		return moneyElement;
	}

	public void setMoneyElement(String moneyElement) {
		this.moneyElement = moneyElement;
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
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 费用类型代码
     * @return 费用类型代码
     */
    public String getFeeTypeId() {
        return feeTypeId;
    }

    /**
     * 设置 费用类型代码
     * @param feeTypeId 费用类型代码
     */
    public void setFeeTypeId(String feeTypeId) {
        this.feeTypeId = feeTypeId == null ? null : feeTypeId.trim();
    }

    /**
     * 取得 基础科目性质
     * @return 基础科目性质
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 基础科目性质
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature == null ? null : baseSubjectNature.trim();
    }
}