package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;


// @ApiModel(value="802域",description="802域")
public class NoPartySubIDDto {
	
	// @ApiModelProperty(required=true,name="partyID",value="523域",example="")
	private String partySubID;
	
	// @ApiModelProperty(required=true,name="partySubIDType",value="803域-2-交易员ID",example="2")
	private Integer partySubIDType;

	public String getPartySubID() {
		return partySubID;
	}

	public void setPartySubID(String partySubID) {
		this.partySubID = partySubID;
	}

	public Integer getPartySubIDType() {
		return partySubIDType;
	}

	public void setPartySubIDType(Integer partySubIDType) {
		this.partySubIDType = partySubIDType;
	}
	
}
