package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.util.List;


// @ApiModel(value="453域",description="453域")
public class NoPartyIDDto {
	
	// @ApiModelProperty(required=true,name="partyID",value="448域-发起方交易账户 21 位码",example="")
	private String partyID;
	
	// @ApiModelProperty(required=true,name="partyRole",value="452域-1-发起方",example="1")
	private Integer partyRole;
	
	// @ApiModelProperty(required=true,name="noPartySubIDs",value="802域-重复组",example="")
	private List<NoPartySubIDDto> noPartySubIDs;

	public String getPartyID() {
		return partyID;
	}

	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}

	public Integer getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(Integer partyRole) {
		this.partyRole = partyRole;
	}

	public List<NoPartySubIDDto> getNoPartySubIDs() {
		return noPartySubIDs;
	}

	public void setNoPartySubIDs(List<NoPartySubIDDto> noPartySubIDs) {
		this.noPartySubIDs = noPartySubIDs;
	}

}
