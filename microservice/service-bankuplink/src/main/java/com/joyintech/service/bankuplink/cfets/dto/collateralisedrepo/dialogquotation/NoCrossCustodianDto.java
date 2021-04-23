package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;


// @ApiModel(value="12048域",description="12048域")
public class NoCrossCustodianDto {

	// @ApiModelProperty(required=true,name="crossCustodianInstitutionName",value="12049域-跨托管机构名称(3:中债登)",example="3")
	private String crossCustodianInstitutionName;
	
	// @ApiModelProperty(required=true,name="crossCustodianTradeAmt",value="12050域-跨托管交易金额",example="20000.01")
	private String crossCustodianTradeAmt;

	public String getCrossCustodianInstitutionName() {
		return crossCustodianInstitutionName;
	}

	public void setCrossCustodianInstitutionName(String crossCustodianInstitutionName) {
		this.crossCustodianInstitutionName = crossCustodianInstitutionName;
	}

	public String getCrossCustodianTradeAmt() {
		return crossCustodianTradeAmt;
	}

	public void setCrossCustodianTradeAmt(String crossCustodianTradeAmt) {
		this.crossCustodianTradeAmt = crossCustodianTradeAmt;
	}
	
}
