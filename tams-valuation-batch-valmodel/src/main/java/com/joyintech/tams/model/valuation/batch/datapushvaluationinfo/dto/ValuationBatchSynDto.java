package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 估值行情信息DTO
 * @author zhangfei
 *
 */
public class ValuationBatchSynDto extends NeedOperateEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资产代码
	 */
	private String finprodId;
	 /**
     * 估值日
     */
    private Date valueDate;
    
    /**
     * 估值来源，中债、中证等
     */
    private String valueSource;
    /**
     * 市价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal price;

    /**
     * 市价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netprice;

	public String getFinprodId() {
		return finprodId;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public String getValueSource() {
		return valueSource;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getNetprice() {
		return netprice;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public void setValueSource(String valueSource) {
		this.valueSource = valueSource;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setNetprice(BigDecimal netprice) {
		this.netprice = netprice;
	}
	
    
    
	
}
