package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;


// @ApiModel(value="887域",description="887域")
public class NoUnderlyingStipDto {

	// @ApiModelProperty(required=true,name="underlyingStipType",value="888域-折算比例",example="HAIRCUT")
	private String underlyingStipType;
	
	// @ApiModelProperty(required=true,name="underlyingStipValue",value="889域-折算比例值",example="91.12")
	private String underlyingStipValue;

	public String getUnderlyingStipType() {
		return underlyingStipType;
	}

	public void setUnderlyingStipType(String underlyingStipType) {
		this.underlyingStipType = underlyingStipType;
	}

	public String getUnderlyingStipValue() {
		return underlyingStipValue;
	}

	public void setUnderlyingStipValue(String underlyingStipValue) {
		this.underlyingStipValue = underlyingStipValue;
	}
	
}
