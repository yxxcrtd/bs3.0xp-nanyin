package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class Prdbokstl extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账号
     */
    private String acctNo;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 投资权限
     * @return
     */
    private String tzPower;
    
	/**
	 * @return the tzPower
	 */
	public String getTzPower() {
		return tzPower;
	}

	/**
	 * @param tzPower the tzPower to set
	 */
	public void setTzPower(String tzPower) {
		this.tzPower = tzPower;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

   
}