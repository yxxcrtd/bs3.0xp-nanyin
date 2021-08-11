package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @Description:前瞻因子配置Dto
 * @auther: yuzj
 * @date: 2021/7/16
 * @param: 
 * @return: 
 */
public class ModPdFactorConfigDto {
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	private String seqNo;
	/**
	 * 配置类型
	 */
	private String configType;
	/**
	 * 年份
	 */
	private String vouchYear;
	/**
	 * 季度
	 */
	private String vouchQuarter;
	
	/**
	 * 乐观因子
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal optimisticFactor; 

	/**
	 * 基础因子
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal basicFactor;

	/**
	 * 悲观因子
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal pessimismFactor;


	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getVouchYear() {
		return vouchYear;
	}

	public void setVouchYear(String vouchYear) {
		this.vouchYear = vouchYear;
	}

	public String getVouchQuarter() {
		return vouchQuarter;
	}

	public void setVouchQuarter(String vouchQuarter) {
		this.vouchQuarter = vouchQuarter;
	}

	public BigDecimal getOptimisticFactor() {
		return optimisticFactor;
	}

	public void setOptimisticFactor(BigDecimal optimisticFactor) {
		this.optimisticFactor = optimisticFactor;
	}

	public BigDecimal getBasicFactor() {
		return basicFactor;
	}

	public void setBasicFactor(BigDecimal basicFactor) {
		this.basicFactor = basicFactor;
	}

	public BigDecimal getPessimismFactor() {
		return pessimismFactor;
	}

	public void setPessimismFactor(BigDecimal pessimismFactor) {
		this.pessimismFactor = pessimismFactor;
	}
}
