package com.joyintech.service.product.nolimit.common.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ProDesignAmtDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4723725569410781871L;

	private String proDesignid;
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalAmt;
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal tdyAssetValue;
	public String getProDesignid() {
		return proDesignid;
	}
	public void setProDesignid(String proDesignid) {
		this.proDesignid = proDesignid;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public BigDecimal getTdyAssetValue() {
		return tdyAssetValue;
	}
	public void setTdyAssetValue(BigDecimal tdyAssetValue) {
		this.tdyAssetValue = tdyAssetValue;
	}
	
	
	
}
