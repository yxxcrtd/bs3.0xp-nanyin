package com.joyintech.service.product.prdrisklevel.dto;

public class PrdRiskLevelDto extends PrdRiskLevel {

	private static final long serialVersionUID = -316125665583542204L;

	private String operateType;

	private String finProductName;

	private String riskLevelStr; // 风险等级str

	private String ajustRiskLevelStr; // 调整后风险等级str
	
	private String prdDesignId; // 产品设计编号

	public String getFinProductName() {
		return finProductName;
	}

	public void setFinProductName(String finProductName) {
		this.finProductName = finProductName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getRiskLevelStr() {
		return riskLevelStr;
	}

	public void setRiskLevelStr(String riskLevelStr) {
		this.riskLevelStr = riskLevelStr;
	}

	public String getAjustRiskLevelStr() {
		return ajustRiskLevelStr;
	}

	public void setAjustRiskLevelStr(String ajustRiskLevelStr) {
		this.ajustRiskLevelStr = ajustRiskLevelStr;
	}

	public String getPrdDesignId() {
		return prdDesignId;
	}

	public void setPrdDesignId(String prdDesignId) {
		this.prdDesignId = prdDesignId;
	}
}
