package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Map;

/**
 * 账套信息初始化其他参数Dto
 * 
 * @author 吴洁
 * @version 1.0 2018年12月20日
 * @see OtherParamsDto
 * @since 1.0
 */
public class OtherParamsDto {

	private String tmp_booksetId;// 模板账套Id

	private String valuationTemplate;// 估值表模板

	private String prdValSche;// 产品估值方案

	private Map<String, String> sysParams;// 系统参数

	/**
	 * @return the tmp_booksetId
	 */
	public String getTmp_booksetId() {
		return tmp_booksetId;
	}

	/**
	 * @param tmp_booksetId
	 *            the tmp_booksetId to set
	 */
	public void setTmp_booksetId(String tmp_booksetId) {
		this.tmp_booksetId = tmp_booksetId;
	}

	/**
	 * @return the valuationTemplate
	 */
	public String getValuationTemplate() {
		return valuationTemplate;
	}

	/**
	 * @param valuationTemplate
	 *            the valuationTemplate to set
	 */
	public void setValuationTemplate(String valuationTemplate) {
		this.valuationTemplate = valuationTemplate;
	}

	/**
	 * @return the prdValSche
	 */
	public String getPrdValSche() {
		return prdValSche;
	}

	/**
	 * @param prdValSche
	 *            the prdValSche to set
	 */
	public void setPrdValSche(String prdValSche) {
		this.prdValSche = prdValSche;
	}

	public Map<String, String> getSysParams() {
		return sysParams;
	}

	public void setSysParams(Map<String, String> sysParams) {
		this.sysParams = sysParams;
	}

}
