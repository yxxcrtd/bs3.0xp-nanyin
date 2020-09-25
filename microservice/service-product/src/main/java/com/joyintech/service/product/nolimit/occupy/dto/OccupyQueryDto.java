package com.joyintech.service.product.nolimit.occupy.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.product.nolimit.common.dto.ProDesignAmtDto;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 非标占比查询
 */
public class OccupyQueryDto extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询日
	 */
	private Date queryDay;
	
	/**
	 * 查询口径
	 */
	private String queryCaliber;
	
	/**
	 * 当前非标总资产
	 */
	private BigDecimal nolimitTotalAssets;
	
	/**
	 * 当前总资产规模
	 */
	private BigDecimal totalAssetsScale;
	
	/**
	 * 业务上限规模
	 */
	private BigDecimal upperLimitScale;
	
	/**
	 * 当前非标总可用限额
	 */
	private BigDecimal availableTotalAssets;
	
	/**
	 * 产品系列
	 */
	private String prodSeries;
	
	/**
	 * 产品设计系列名称
	 */
	private String prodSeriesName;
	
	/**
	 * 存量规模
	 */
	private BigDecimal stockScale;
	
	/**
	 * 存量非标占比
	 */
	private BigDecimal stockProportion;
	
	/**
	 * 1月到期分布
	 */
	private BigDecimal distribution1;
	/**
	 * 2月到期分布
	 */
	private BigDecimal distribution2;
	/**
	 * 3月到期分布
	 */
	private BigDecimal distribution3;
	/**
	 *4月到期分布
	 */
	private BigDecimal distribution4;
	/**
	 * 5月到期分布
	 */
	private BigDecimal distribution5;
	/**
	 * 6月到期分布
	 */
	private BigDecimal distribution6;
	/**
	 * 7月到期分布
	 */
	private BigDecimal distribution7;
	/**
	 * 8月到期分布
	 */
	private BigDecimal distribution8;
	/**
	 * 9月到期分布
	 */
	private BigDecimal distribution9;
	/**
	 * 10月到期分布
	 */
	private BigDecimal distribution10;
	/**
	 * 11月到期分布
	 */
	private BigDecimal distribution11;
	/**
	 * 12月到期分布
	 */
	private BigDecimal distribution12;

	public Date getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(Date queryDay) {
		this.queryDay = queryDay;
	}

	public String getQueryCaliber() {
		return queryCaliber;
	}

	public void setQueryCaliber(String queryCaliber) {
		this.queryCaliber = queryCaliber;
	}

	public BigDecimal getNolimitTotalAssets() {
		return nolimitTotalAssets;
	}

	public void setNolimitTotalAssets(BigDecimal nolimitTotalAssets) {
		this.nolimitTotalAssets = nolimitTotalAssets;
	}

	public BigDecimal getTotalAssetsScale() {
		return totalAssetsScale;
	}

	public void setTotalAssetsScale(BigDecimal totalAssetsScale) {
		this.totalAssetsScale = totalAssetsScale;
	}

	public BigDecimal getUpperLimitScale() {
		return upperLimitScale;
	}

	public void setUpperLimitScale(BigDecimal upperLimitScale) {
		this.upperLimitScale = upperLimitScale;
	}

	public BigDecimal getAvailableTotalAssets() {
		return availableTotalAssets;
	}

	public void setAvailableTotalAssets(BigDecimal availableTotalAssets) {
		this.availableTotalAssets = availableTotalAssets;
	}

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public BigDecimal getStockScale() {
		return stockScale;
	}

	public void setStockScale(BigDecimal stockScale) {
		this.stockScale = stockScale;
	}

	public BigDecimal getStockProportion() {
		return stockProportion;
	}

	public void setStockProportion(BigDecimal stockProportion) {
		this.stockProportion = stockProportion;
	}

	public BigDecimal getDistribution1() {
		return distribution1;
	}

	public void setDistribution1(BigDecimal distribution1) {
		this.distribution1 = distribution1;
	}

	public BigDecimal getDistribution2() {
		return distribution2;
	}

	public void setDistribution2(BigDecimal distribution2) {
		this.distribution2 = distribution2;
	}

	public BigDecimal getDistribution3() {
		return distribution3;
	}

	public void setDistribution3(BigDecimal distribution3) {
		this.distribution3 = distribution3;
	}

	public BigDecimal getDistribution4() {
		return distribution4;
	}

	public void setDistribution4(BigDecimal distribution4) {
		this.distribution4 = distribution4;
	}

	public BigDecimal getDistribution5() {
		return distribution5;
	}

	public void setDistribution5(BigDecimal distribution5) {
		this.distribution5 = distribution5;
	}

	public BigDecimal getDistribution6() {
		return distribution6;
	}

	public void setDistribution6(BigDecimal distribution6) {
		this.distribution6 = distribution6;
	}

	public BigDecimal getDistribution7() {
		return distribution7;
	}

	public void setDistribution7(BigDecimal distribution7) {
		this.distribution7 = distribution7;
	}

	public BigDecimal getDistribution8() {
		return distribution8;
	}

	public void setDistribution8(BigDecimal distribution8) {
		this.distribution8 = distribution8;
	}

	public BigDecimal getDistribution9() {
		return distribution9;
	}

	public void setDistribution9(BigDecimal distribution9) {
		this.distribution9 = distribution9;
	}

	public BigDecimal getDistribution10() {
		return distribution10;
	}

	public void setDistribution10(BigDecimal distribution10) {
		this.distribution10 = distribution10;
	}

	public BigDecimal getDistribution11() {
		return distribution11;
	}

	public void setDistribution11(BigDecimal distribution11) {
		this.distribution11 = distribution11;
	}

	public BigDecimal getDistribution12() {
		return distribution12;
	}

	public void setDistribution12(BigDecimal distribution12) {
		this.distribution12 = distribution12;
	}

	public String getProdSeriesName() {
		return prodSeriesName;
	}

	public void setProdSeriesName(String prodSeriesName) {
		this.prodSeriesName = prodSeriesName;
	}
	
	
}
