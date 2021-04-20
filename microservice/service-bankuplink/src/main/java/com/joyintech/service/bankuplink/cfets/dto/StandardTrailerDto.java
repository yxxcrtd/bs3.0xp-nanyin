package com.joyintech.service.bankuplink.cfets.dto;


/**
 * 通用报文尾</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-17
 */
// @ApiModel(value="报文尾",description="报文尾")
public class StandardTrailerDto {

	// @ApiModelProperty(required=false,name="signatureLength",value="93域-数字签名长度(不可加密)",example="IMIX.2.0")
	private String signatureLength;
	
	// @ApiModelProperty(required=false,name="signature",value="89域-数字签名(不可加密)",example="1")
	private Integer signature;
	
	// @ApiModelProperty(required=true,name="msgSeqNum",value="10域-校验和,报文的最末域(不可加密)",example="50")
	private Integer checkSum;

	public String getSignatureLength() {
		return signatureLength;
	}

	public void setSignatureLength(String signatureLength) {
		this.signatureLength = signatureLength;
	}

	public Integer getSignature() {
		return signature;
	}

	public void setSignature(Integer signature) {
		this.signature = signature;
	}

	public Integer getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(Integer checkSum) {
		this.checkSum = checkSum;
	}
	
}
