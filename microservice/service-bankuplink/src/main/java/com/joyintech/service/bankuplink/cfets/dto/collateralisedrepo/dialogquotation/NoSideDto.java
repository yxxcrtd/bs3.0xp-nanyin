package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.math.BigDecimal;
import java.util.List;


// @ApiModel(value="552域",description="552域")
public class NoSideDto {

	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="2")
	private String side;
	
	// @ApiModelProperty(required=true,name="price",value="44域-净价",example="98.0000")
	private BigDecimal price;
	
	// @ApiModelProperty(required=true,name="tradeCashAmt",value="10312域-交易金额",example="3480000.25")
	private BigDecimal tradeCashAmt;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTradeCashAmt() {
		return tradeCashAmt;
	}

	public void setTradeCashAmt(BigDecimal tradeCashAmt) {
		this.tradeCashAmt = tradeCashAmt;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
