package com.joyintech.tams.invest.asset.sppi.dto;

import com.joyintech.tams.common.base.entity.SppiImportData;

public class SppiImportDto extends SppiImportData{
	
	/**
     * excel 列
     */
    private String excelNum;
    
    /**
     * 资产类型<br />
     * 债券类资产、项目类资产、计划类利率型资产
     */
    private String assetType;
    
    /**
     * 数据来源
     */
    private String dataSource;

	public String getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
    
    
}
