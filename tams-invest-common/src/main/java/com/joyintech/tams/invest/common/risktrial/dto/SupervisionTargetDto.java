package com.joyintech.tams.invest.common.risktrial.dto;


import java.util.Date;

import com.joyintech.tams.common.base.entity.RskIsupIndicator;

/**
 * 监督指标管理
 * @author 裴家俊
 * @date 2018年11月27日
 */
public class SupervisionTargetDto extends RskIsupIndicator {
    
	private static final long serialVersionUID = 1L;
	
	private String portfolioId;
	
    /**
     * 组合层次
     */
    private String portfolioLevel;
    /**
     * 有效开始时间
     */
    private Date startDate;
    /**
     * 有效结束时间
     */
    private Date endDate;
    
    
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 组合成分
     */
    private String portfolioMix;
    
	/**
	 * 组合包含有的组合
	 */
	private String portfConstId;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfConstId() {
		return portfConstId;
	}

	public void setPortfConstId(String portfConstId) {
		this.portfConstId = portfConstId;
	}

	public String getPortfolioLevel() {
		return portfolioLevel;
	}

	public void setPortfolioLevel(String portfolioLevel) {
		this.portfolioLevel = portfolioLevel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getPortfolioMix() {
		return portfolioMix;
	}

	public void setPortfolioMix(String portfolioMix) {
		this.portfolioMix = portfolioMix;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
