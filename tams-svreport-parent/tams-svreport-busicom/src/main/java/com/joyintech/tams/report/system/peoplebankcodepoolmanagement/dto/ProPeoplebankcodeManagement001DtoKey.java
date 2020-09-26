package com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto;

import com.joyintech.tams.report.common.base.TableRow;

public class ProPeoplebankcodeManagement001DtoKey extends TableRow{
	
	/**
	 * excel 列
	 */
	private String excelNum;
	
    /**
     * 内部产品代码
     */
    private String insideProdId;

    /**
     * 代码类型
     */
    private String codeType;

    /**
     * 内部产品代码
     * @return INSIDE_PROD_ID 内部产品代码
     */
    public String getInsideProdId() {
        return insideProdId;
    }

    /**
     * 内部产品代码
     * @param insideProdId 内部产品代码
     */
    public void setInsideProdId(String insideProdId) {
        this.insideProdId = insideProdId == null ? null : insideProdId.trim();
    }

    /**
     * 代码类型
     * @return CODE_TYPE 代码类型
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * 代码类型
     * @param codeType 代码类型
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

	public String getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}
    
    
}