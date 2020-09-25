package com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

public class ValBondQuotationInfoDto extends MstBondValuationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 395899940154269458L;
	/**
	 * 金融产品简称
	 */
	private String finprodAbbr;

	/**
	 * 债券简称
	 * 
	 * @return String
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 债券简称
	 * 
	 * @param finprodAbbr
	 *            finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	 /**
     * 导入日
     */
    private Date importDay;
    /**
     * 获取 importDay
     * @return importDay.
     */
    public Date getImportDay() {
        return importDay;
    }
    
    /**
     * 设置 importDay
     * @param importDay importDay
     */
    public void setImportDay(Date importDay) {
        this.importDay = importDay;
    }
}
