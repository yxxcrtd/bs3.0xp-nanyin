package com.joyintech.tams.invest.common.risktrialrds.dto;

import java.util.Date;

public class Task {

	private String portfolioId;//组合与组合包代码
	private SupervisionTargetDto point;
	private Date date;
    private String institutionCode;//机构名称

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public SupervisionTargetDto getPoint() {
		return point;
	}
	public void setPoint(SupervisionTargetDto point) {
		this.point = point;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
