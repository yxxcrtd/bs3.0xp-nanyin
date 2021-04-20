package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation;


// @ApiModel(value="78域",description="重复组次数数")
public class NoAllocDto {
	
	// @ApiModelProperty(required=false,name="allocAccount",value="79域-投资产品 21位码",example="")
	private String allocAccount;
	
	// @ApiModelProperty(required=false,name="individualAllocID",value="467域-分账序号",example="ALAAAABBBB000000000001")
	private String individualAllocID;
	
	// @ApiModelProperty(required=false,name="allocQty",value="80域-分账券面总额",example="200000")
	private String allocQty;

	public String getAllocAccount() {
		return allocAccount;
	}

	public void setAllocAccount(String allocAccount) {
		this.allocAccount = allocAccount;
	}

	public String getIndividualAllocID() {
		return individualAllocID;
	}

	public void setIndividualAllocID(String individualAllocID) {
		this.individualAllocID = individualAllocID;
	}

	public String getAllocQty() {
		return allocQty;
	}

	public void setAllocQty(String allocQty) {
		this.allocQty = allocQty;
	}

}
