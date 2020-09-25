package com.joyintech.tams.invest.product.nolimit.nonstandardavailablecredit.dto;

 
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;
/**
 * 非标可用额度
 * 
 * @author dongzhikang
 * @version 1.0 2019年12月10日
 * @see
 * @since 1.0
 */
public class NonStandardAvailableCredit001Dto extends AssetPlanInfo{
	
	/*
	 * 获取非标限额
	 * 
	 */
	 @JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal nonStandardLimit;
 
	/*
	* 当前非标比例
	* 
	*/
    @JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal nonStandardRatio;	
	/*
	 * 当前非标总资产
	 * 
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal cuNonStandardTotalAssets;
	
	/*
	 * 当前非标总可用限额
	 * 
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal cuNonStandardTotalLimit;
	
	/*
	 * 计划资产总量
	 * 
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalPlannedAssets;
	
	/*
	 * 当前总资产
	 * 
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal cutotalAssets;
	/*
	 * 计划非标总可用限额
	 * 
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal planNonStandTotalLimit;
	
	/*
	 * 查询日期
	 * 
	 */
	private Date curveDate;
	
	 /**
     * 设置查询日期
     *
     * @param  
     * @return 
     */
	public Date getCurveDate() {
		return curveDate;
	}
	
	 /**
     * 根据格式化字符串格式化日期
     *
     * @param date 格式化前日期
     * @return 格式化后日期字符串（yyyy-MM-dd）
     */
    public  String getCurveDateStr() {
        return DateUtil.format(this.getCurveDate());
    }

    /**
     * 设置 查询日
     * @param vdate 查询日
     */
	public void setCurveDate(Date curveDate) {
		this.curveDate = curveDate;
	}
	 /**
     * 获取 当前总资产
     * @param cuNonStandardTotalAssets 当前总资产
     */
	public BigDecimal getCuNonStandardTotalAssets() {
		return cuNonStandardTotalAssets;
	}
	 /**
     * 设置 当前总资产
     * @param cuNonStandardTotalAssets 当前总资产
     */
	public void setCuNonStandardTotalAssets(BigDecimal cuNonStandardTotalAssets) {
		this.cuNonStandardTotalAssets = cuNonStandardTotalAssets;
	}
	 /**
     * 获取 非标总可用限额
     * @param cuNonStandardTotalLimit  非标总可用限额
     */
	public BigDecimal getCuNonStandardTotalLimit() {
		return cuNonStandardTotalLimit;
	}
	 /**
     * 设置 非标总可用限额
     * @param cuNonStandardTotalLimit  非标总可用限额
     */
	public void setCuNonStandardTotalLimit(BigDecimal cuNonStandardTotalLimit) {
		this.cuNonStandardTotalLimit = cuNonStandardTotalLimit;
	}
	/**
     * 获取计划资产总量
     * @param cuNonStandardTotalLimit  非标总可用限额
     */
	public BigDecimal getTotalPlannedAssets() {
		return totalPlannedAssets;
	}
	/**
     * 获取计划资产总量
     * @param totalPlannedAssets  计划资产总量
     */
	public void setTotalPlannedAssets(BigDecimal totalPlannedAssets) {
		this.totalPlannedAssets = totalPlannedAssets;
	}
	/**
     * 获取当前总资产
     * @param cutotalAssets  当前总资产
     */
	public BigDecimal getCutotalAssets() {
		return cutotalAssets;
	}
	/**
     * 设置当前总资产
     * @param cutotalAssets  当前总资产
     */
	public void setCutotalAssets(BigDecimal cutotalAssets) {
		this.cutotalAssets = cutotalAssets;
	}
	/**
     * 获取计划非标总可用限额
     * @param planNonStandTotalLimit  计划非标总可用限额
     */
	public BigDecimal getPlanNonStandTotalLimit() {
		return planNonStandTotalLimit;
	}
	/**
     * 获取计划非标总可用限额
     * @param planNonStandTotalLimit  计划非标总可用限额
     */
	public void setPlanNonStandTotalLimit(BigDecimal planNonStandTotalLimit) {
		this.planNonStandTotalLimit = planNonStandTotalLimit;
	}

    /**
     * 获取非标限额
     * @param nonStandardLimit 非标限额
     */
	public BigDecimal getNonStandardLimit() {
		return nonStandardLimit;
	}
	 /**
     * 设置非标限额
     * @param nonStandardLimit 非标限额
     */
	public void setNonStandardLimit(BigDecimal nonStandardLimit) {
		this.nonStandardLimit = nonStandardLimit;
	}
	 /**
     * 获取非标比例
     * @param nonStandardRatio 非标比例
     */
	public BigDecimal getNonStandardRatio() {
		return nonStandardRatio;
	}

    /**
     * 设置非标比例  
     * @param nonStandardRatio   非标比例
     */
	public void setNonStandardRatio(BigDecimal nonStandardRatio) {
		this.nonStandardRatio = nonStandardRatio;
	}

	 
	 
 
	 
}
