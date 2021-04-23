package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.util.List;


// @ApiModel(value="711域",description="711域")
public class NoUnderlyingDto {

	// @ApiModelProperty(required=true,name="underlyingSymbol",value="311域-质押券名称(组件必传域,无值时传输“-”)",example="")
	private String underlyingSymbol;
	
	// @ApiModelProperty(required=true,name="underlyingSecurityID",value="309域-质押券代码)",example="10004")
	private String underlyingSecurityID;
	
	// @ApiModelProperty(required=true,name="underlyingQty",value="879域-质押券券面总额)",example="100")
	private String underlyingQty;
	
	// @ApiModelProperty(required=true,name="underlyingQty",value="887域)",example="")
	private List<NoUnderlyingStipDto> noUnderlyingStips;

	public String getUnderlyingSymbol() {
		return underlyingSymbol;
	}

	public void setUnderlyingSymbol(String underlyingSymbol) {
		this.underlyingSymbol = underlyingSymbol;
	}

	public String getUnderlyingSecurityID() {
		return underlyingSecurityID;
	}

	public void setUnderlyingSecurityID(String underlyingSecurityID) {
		this.underlyingSecurityID = underlyingSecurityID;
	}

	public String getUnderlyingQty() {
		return underlyingQty;
	}

	public void setUnderlyingQty(String underlyingQty) {
		this.underlyingQty = underlyingQty;
	}

	public List<NoUnderlyingStipDto> getNoUnderlyingStips() {
		return noUnderlyingStips;
	}

	public void setNoUnderlyingStips(List<NoUnderlyingStipDto> noUnderlyingStips) {
		this.noUnderlyingStips = noUnderlyingStips;
	}
	
}
