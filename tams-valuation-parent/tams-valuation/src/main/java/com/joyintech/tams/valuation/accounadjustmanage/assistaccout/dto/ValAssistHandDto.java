package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto;

/**
 * @author  殷亚
 * @description
 * @date 2022-1-12 16:17
 */
public class ValAssistHandDto  {
	/**
	 * 辅助核算项编码
	 */
	private String handCode;
	/**
	 * 辅助核算项名称
	 */
	private String handName;
	/**
	 * 启用状态
	 */
	private String status;



	public String getHandCode() {
		return handCode;
	}

	public void setHandCode(String handCode) {
		this.handCode = handCode;
	}

	public String getHandName() {
		return handName;
	}

	public void setHandName(String handName) {
		this.handName = handName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
