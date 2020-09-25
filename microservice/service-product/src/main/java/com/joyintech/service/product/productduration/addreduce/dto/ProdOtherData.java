package com.joyintech.service.product.productduration.addreduce.dto;

import java.math.BigDecimal;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: code
 * @Package com.joyintech.product.common.dto
 * @Description: TODO
 * @date Date : 2018年09月13日 11:38
 */
public class ProdOtherData {

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 分行
     */
    private String entrusterId;

    /**
     * 分行
     */
    private String entrusterName;

    /**
     * 单位净值小数位
     */
    private BigDecimal unitvalueDec;

    /**
     * 单位净值尾数处理方式
     */
    private String unitvalueMode;

    /**
     * 投资组合
     */
    private String ptlId;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 实际到期日
     */
    private String prdMdate;

    /**
     * 起息日
     */
    private String prdVdate;
    
    /**
     * 运作方式
     */
    private String operationType;
    
    /**
     * 处理模式
     */
    private String dealMode;

    public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	/**
     * 获取 saleDepartment
     *
     * @return saleDepartment
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 shortName
     *
     * @return shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置 shortName
     *
     * @param shortName shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 unitvalueDec
     *
     * @return unitvalueDec
     */
    public BigDecimal getUnitvalueDec() {
        return unitvalueDec;
    }

    /**
     * 设置 unitvalueDec
     *
     * @param unitvalueDec unitvalueDec
     */
    public void setUnitvalueDec(BigDecimal unitvalueDec) {
        this.unitvalueDec = unitvalueDec;
    }

    /**
     * 获取 unitvalueMode
     *
     * @return unitvalueMode
     */
    public String getUnitvalueMode() {
        return unitvalueMode;
    }

    /**
     * 设置 unitvalueMode
     *
     * @param unitvalueMode unitvalueMode
     */
    public void setUnitvalueMode(String unitvalueMode) {
        this.unitvalueMode = unitvalueMode;
    }

    /**
     * 获取 ptlId
     *
     * @return ptlId
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     *
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 capiIncomeFeature
     *
     * @return capiIncomeFeature
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 capiIncomeFeature
     *
     * @param capiIncomeFeature capiIncomeFeature
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 获取 prdMdate
     *
     * @return prdMdate
     */
    public String getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 prdMdate
     *
     * @param prdMdate prdMdate
     */
    public void setPrdMdate(String prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 获取 prdVdate
     *
     * @return prdVdate
     */
    public String getPrdVdate() {
        return prdVdate;
    }

    /**
     * 设置 prdVdate
     *
     * @param prdVdate prdVdate
     */
    public void setPrdVdate(String prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 获取 prodRegistCode
     *
     * @return prodRegistCode
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 prodRegistCode
     *
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    /**
     * 获取 isLay
     *
     * @return isLay
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     *
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 layType
     *
     * @return layType
     */
    public String getLayType() {
        return layType;
    }

    /**
     * 设置 layType
     *
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    /**
     * 获取 entrusterId
     *
     * @return entrusterId
     */
    public String getEntrusterId() {
        return entrusterId;
    }

    /**
     * 设置 entrusterId
     *
     * @param entrusterId entrusterId
     */
    public void setEntrusterId(String entrusterId) {
        this.entrusterId = entrusterId;
    }

    /**
     * 获取 entrusterName
     *
     * @return entrusterName
     */
    public String getEntrusterName() {
        return entrusterName;
    }

    /**
     * 设置 entrusterName
     *
     * @param entrusterName entrusterName
     */
    public void setEntrusterName(String entrusterName) {
        this.entrusterName = entrusterName;
    }

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
    
}
