package com.joyintech.tams.valuation.batch.positionmake.dto;

/**
 * 基础科目性质对应费用类型
 * 
 * @author Administrator
 *
 */
public class SubjectNatureFeeTypeInfo {

	/**
	 * 费用类型
	 */
	private String feeTypeId;

	/**
	 * 基础科目性质
	 */
	private String baseSubjectNature;
	
	/**
	 * 费用场所
	 */
	private String feePlace;

	public String getFeeTypeId() {
		return feeTypeId;
	}

	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}

	public String getBaseSubjectNature() {
		return baseSubjectNature;
	}

	public void setBaseSubjectNature(String baseSubjectNature) {
		this.baseSubjectNature = baseSubjectNature;
	}

	public String getFeePlace() {
		return feePlace;
	}

	public void setFeePlace(String feePlace) {
		this.feePlace = feePlace;
	}

}
