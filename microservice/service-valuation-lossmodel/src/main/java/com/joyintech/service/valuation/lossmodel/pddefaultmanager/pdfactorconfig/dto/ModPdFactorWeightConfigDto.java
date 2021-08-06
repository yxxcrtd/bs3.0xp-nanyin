package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

public class ModPdFactorWeightConfigDto extends BaseEntity {
	
	private static final long serialVersionUID = 0L;
	
	/**
	 * 唯一标识
	 */
	private String seqno;
	/**
	 * 乐观权重
	 */
	private BigDecimal optimisticWeight;
	/**
	 * 基础权重
	 */
	private BigDecimal basicWeight;
	/**
	 * 悲观权重
	 */
	private BigDecimal pessimismWeight;
	
	 
    
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	
	public BigDecimal getOptimisticWeight() {
		return optimisticWeight;
	}

	public void setOptimisticWeight(BigDecimal optimisticWeight) {
		this.optimisticWeight = optimisticWeight;
	}
	
	
	public BigDecimal getBasicWeight() {
		return basicWeight;
	}

	public void setBasicWeight(BigDecimal basicWeight) {
		this.basicWeight = basicWeight;
	}
	
	public BigDecimal getPessimismWeight() {
		return pessimismWeight;
	}

	public void setPessimismWeight(BigDecimal pessimismWeight) {
		this.pessimismWeight = pessimismWeight;
	}
	
}
