package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.BokBooksetRe;


/**
 * 子账套信息Dto
 */
public class BokBooksetSonDto extends BokBooksetRe{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 子账套代码
	 */
	private String subBokBooksetId;
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;
    /**
     * 账套当前日期
     */
    private Date booksetDate;
	/**
	 * 子账套代码
	 */
	public String getSubBokBooksetId() {
		return subBokBooksetId;
	}
	/**
	 * 子账套代码
	 */
	public void setSubBokBooksetId(String subBokBooksetId) {
		this.subBokBooksetId = subBokBooksetId;
	}
    /**
     * 金融产品代码
     */
	public String getFinprodId() {
		return finprodId;
	}
    /**
     * 金融产品代码
     */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
    /**
     * 金融产品类型
     */
	public String getFinprodType() {
		return finprodType;
	}
    /**
     * 金融产品类型
     */
	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}
	public Date getBooksetDate() {
		return booksetDate;
	}
	public void setBooksetDate(Date booksetDate) {
		this.booksetDate = booksetDate;
	}

}
