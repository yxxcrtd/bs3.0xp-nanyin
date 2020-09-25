package com.joyintech.tams.poc.directtransaction.futuresordermanage.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;

public class FuturesOrderMenegeDto extends EtrEntrust {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7130132337707222323L;
	private String abbr;
	 @JsonSerialize(using=BigDecimalSerializer.class)
	 private BigDecimal entrustprice;
	    
	 @JsonSerialize(using=BigDecimalSerializer.class)
	 private BigDecimal entrustamunt;
	    
	    
	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	/**
     * 取得 null
     * @return null
     */
    public BigDecimal getEntrustprice() {
        return entrustprice;
    }

    /**
     * 设置 null
     * @param entrustprice null
     */
    public void setEntrustprice(BigDecimal entrustprice) {
        this.entrustprice = entrustprice;
    }
    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getEntrustamunt() {
        return entrustamunt;
    }

    /**
     * 设置 null
     * @param entrustamunt null
     */
    public void setEntrustamunt(BigDecimal entrustamunt) {
        this.entrustamunt = entrustamunt;
    }
}
