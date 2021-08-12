package com.joyintech.tams.invest.risk.creditrisklimitmanagement.linemonitordaily.dto;


public class ObjLineMonitorDailyDto extends LineMonitorDailyDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//业务类型
	private String busiType;
	
	//审批编号
	private String approId;
	
	//资产名称
	private String assetName;

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getApproId() {
		return approId;
	}

	public void setApproId(String approId) {
		this.approId = approId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}


}
