package com.joyintech.tams.settle.common.dto;

import java.math.BigDecimal;

/**
 * 基金信息DTO
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see FundInfoDto
 * @since 1.0
 */
public class FundInfoDto {
    
	// 金融产品代码
	private String finprodId;
	
	// 分类1
	private String type1;
	
	// 分类7
	private String type7;
	
	// 面值
	private BigDecimal faceValue;
	
	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType7() {
		return type7;
	}

	public void setType7(String type7) {
		this.type7 = type7;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
}
