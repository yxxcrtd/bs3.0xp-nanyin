package com.joyintech.tams.invest.product.nolimit.maxissuablesize.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.NonStandardMaint;

public class MaxIssuableSize001Dto extends NonStandardMaint{
	
	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 当前最大可发行规模
	 */
	private BigDecimal maxIssuableSize;
	
	/**
	 * 当前非标总资产
	 */
	private BigDecimal nonStandardTotalAsset;
	
	/**
	 * 非标限额
	 */	
	private BigDecimal nonStandardLimitAsset;
	
	/**
	 * 整体非标比例上限
	 */
	private BigDecimal totalNonStandardLimit;
	
	/**
	 * 当前整体非标总可用限额
	 */
	private BigDecimal totalNonStandardLimitAsset;
	
	/**
	 * 查询日
	 */
	private Date queryDate;

	/**
	 * 获取当前最大可发行规模
	 * @return
	 */
	public BigDecimal getMaxIssuableSize() {
		return maxIssuableSize;
	}

	/**
	 * 设置当前最大可发行规模
	 * @param maxIssuableSize
	 */
	public void setMaxIssuableSize(BigDecimal maxIssuableSize) {
		this.maxIssuableSize = maxIssuableSize;
	}

	/**
	 * 获得非标总资产
	 * @return
	 */
	public BigDecimal getNonStandardTotalAsset() {
		return nonStandardTotalAsset;
	}

	/**
	 * 设置非标总资产
	 * @param nonStandardTotalAsset
	 */
	public void setNonStandardTotalAsset(BigDecimal nonStandardTotalAsset) {
		this.nonStandardTotalAsset = nonStandardTotalAsset;
	}

	/**
	 * 获得非标限额
	 * @return
	 */
	public BigDecimal getNonStandardLimitAsset() {
		return nonStandardLimitAsset;
	}

	/**
	 * 设置非标限额
	 * @param nonStandardLimitAsset
	 */
	public void setNonStandardLimitAsset(BigDecimal nonStandardLimitAsset) {
		this.nonStandardLimitAsset = nonStandardLimitAsset;
	}

	/**
	 * 获得整体非标比例上限
	 * @return
	 */
	public BigDecimal getTotalNonStandardLimit() {
		return totalNonStandardLimit;
	}

	/**
	 * 设置整体非标比例上限
	 * @param totalNonStandardLimit
	 */
	public void setTotalNonStandardLimit(BigDecimal totalNonStandardLimit) {
		this.totalNonStandardLimit = totalNonStandardLimit;
	}

	/**
	 * 获得当前整体非标总可用限额
	 * @return
	 */
	public BigDecimal getTotalNonStandardLimitAsset() {
		return totalNonStandardLimitAsset;
	}

	/**
	 * 设置当前整体非标总可用限额
	 * @param totalNonStandardLimitAsset
	 */
	public void setTotalNonStandardLimitAsset(BigDecimal totalNonStandardLimitAsset) {
		this.totalNonStandardLimitAsset = totalNonStandardLimitAsset;
	}

	/**
	 * 获得查询日
	 * @return
	 */
	public Date getQueryDate() {
		return queryDate;
	}

	/**
	 * 设置查询日
	 * @param queryDate
	 */
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}

}
