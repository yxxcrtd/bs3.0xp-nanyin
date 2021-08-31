package com.joyintech.tams.valuation.common.dto.modelDto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.FinProduct;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产基本信息DTO
 * @author zhangfei
 * @date 2021年7月5日11:41:04
 *
 */
public class ModAssetInfoDto extends FinProduct {
	
	private static final long serialVersionUID = 1L;

    /**
     * 转股代码
     */
    private String conFinprodId;
    
    /**
     * 转股起始日
     */
    private Date conVdate;
    
    /**
     * 转股价格
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal conPrice;
    
	/**
	 * 证券品种
	 */
	private String secType;
	/**
	 * 证券细分类
	 */
    private String secTypeDetail;

	/**
    * 发行人是否持有金融牌照
    */
    private String issueFinLicence;
    
    /**
     * 融资人是否持有金融牌照
     */
    private String financierFinLicence;
    
    /**
     * 担保人是否持有金融牌照
     */
    private String sponsorFinLicence;
    
    /**
     * 实际授信主体是否持有金融牌照
     */
    private String actualCreditFinLicence;

	public String getConFinprodId() {
		return conFinprodId;
	}

	public void setConFinprodId(String conFinprodId) {
		this.conFinprodId = conFinprodId;
	}

	public Date getConVdate() {
		return conVdate;
	}

	public void setConVdate(Date conVdate) {
		this.conVdate = conVdate;
	}

	public BigDecimal getConPrice() {
		return conPrice;
	}

	public void setConPrice(BigDecimal conPrice) {
		this.conPrice = conPrice;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getSecTypeDetail() {
		return secTypeDetail;
	}

	public void setSecTypeDetail(String secTypeDetail) {
		this.secTypeDetail = secTypeDetail;
	}

	public String getIssueFinLicence() {
		return issueFinLicence;
	}

	public void setIssueFinLicence(String issueFinLicence) {
		this.issueFinLicence = issueFinLicence;
	}

	public String getFinancierFinLicence() {
		return financierFinLicence;
	}

	public void setFinancierFinLicence(String financierFinLicence) {
		this.financierFinLicence = financierFinLicence;
	}

	public String getSponsorFinLicence() {
		return sponsorFinLicence;
	}

	public void setSponsorFinLicence(String sponsorFinLicence) {
		this.sponsorFinLicence = sponsorFinLicence;
	}

	public String getActualCreditFinLicence() {
		return actualCreditFinLicence;
	}

	public void setActualCreditFinLicence(String actualCreditFinLicence) {
		this.actualCreditFinLicence = actualCreditFinLicence;
	}
}
