package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation;


// @ApiModel(value="232域",description="重复组个数，该场景取值固定为 1")
public class NoStipulationDto {
	
	// @ApiModelProperty(required=true,name="stipulationType",value="233域-行权收益率",example="StrikeYield")
	private String stipulationType;
	
	// @ApiModelProperty(required=true,name="stipulationValue",value="234域-行权收益率值",example="4.3500")
	private String stipulationValue;

	public String getStipulationType() {
		return stipulationType;
	}

	public void setStipulationType(String stipulationType) {
		this.stipulationType = stipulationType;
	}

	public String getStipulationValue() {
		return stipulationValue;
	}

	public void setStipulationValue(String stipulationValue) {
		this.stipulationValue = stipulationValue;
	}

}
