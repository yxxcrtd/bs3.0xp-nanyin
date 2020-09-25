package com.joyintech.service.valuation.transformation.dto;

import java.util.List;

import com.joyintech.service.valuation.datatransplant.dto.TemplateCheckDto;

public class ResultData {
	
	private String batchSeqno;
	
	private String rstCode;
	
	private String modelType;
	
	private List<TemplateCheckDto> checkList;
	
//	private List<BalanceTransplantDto>  balanceList;

	/**
	 * @return the checkList
	 */
	public List<TemplateCheckDto> getCheckList() {
		return checkList;
	}

	/**
	 * @param checkList the checkList to set
	 */
	public void setCheckList(List<TemplateCheckDto> checkList) {
		this.checkList = checkList;
	}

//	/**
//	 * @return the balanceList
//	 */
//	public List<BalanceTransplantDto> getBalanceList() {
//		return balanceList;
//	}
//
//	/**
//	 * @param balanceList the balanceList to set
//	 */
//	public void setBalanceList(List<BalanceTransplantDto> balanceList) {
//		this.balanceList = balanceList;
//	}

	/**
	 * @return the rstCode
	 */
	public String getRstCode() {
		return rstCode;
	}

	/**
	 * @param rstCode the rstCode to set
	 */
	public void setRstCode(String rstCode) {
		this.rstCode = rstCode;
	}

	/**
	 * @return the modelType
	 */
	public String getModelType() {
		return modelType;
	}

	/**
	 * @param modelType the modelType to set
	 */
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	/**
	 * @return the batchSeqno
	 */
	public String getBatchSeqno() {
		return batchSeqno;
	}

	/**
	 * @param batchSeqno the batchSeqno to set
	 */
	public void setBatchSeqno(String batchSeqno) {
		this.batchSeqno = batchSeqno;
	}
	
	
	
	

}
