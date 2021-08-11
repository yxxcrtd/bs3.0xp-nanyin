package com.joyintech.tams.valuation.common.dto.modelDto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 曲线行情信息对象DTO
 * @author zhangfei
 *
 */
public class ModYieldCurveInfoDto extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 曲线日期
	 */
	public Date curveDate;
	/**
	 * 曲线代码
	 */
	public String curveCode;
	/**
	 * 关键年限点
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	public BigDecimal keyPoint;
	
	/**
	 * 收益率
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal yield;
	/**
	 * 曲线类型
	 */
	private String curveType;


	public ModYieldCurveInfoDto() {
		super();
	}
	public ModYieldCurveInfoDto(Date curveDate, String curveCode, BigDecimal keyPoint, BigDecimal yield,
                                String curveType) {
		super();
		this.curveDate = curveDate;
		this.curveCode = curveCode;
		this.keyPoint = keyPoint;
		this.yield = yield;
		this.curveType = curveType;
	}
	public Date getCurveDate() {
		return curveDate;
	}
	public String getCurveCode() {
		return curveCode;
	}
	public BigDecimal getKeyPoint() {
		return keyPoint;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public String getCurveType() {
		return curveType;
	}
	public void setCurveDate(Date curveDate) {
		this.curveDate = curveDate;
	}
	public void setCurveCode(String curveCode) {
		this.curveCode = curveCode;
	}
	public void setKeyPoint(BigDecimal keyPoint) {
		this.keyPoint = keyPoint;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	public void setCurveType(String curveType) {
		this.curveType = curveType;
	}
	
}
