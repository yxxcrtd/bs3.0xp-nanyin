package com.joyintech.service.bankuplink.cfets.dto;


/**
 * 通用报文头</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-17
 */
// @ApiModel(value="报文头",description="报文头")
public class StandardHeaderDto extends StandardTrailerDto{

	// @ApiModelProperty(hidden = true, name="beginString",value="8域-报文版本号",example="IMIX.2.0")
	private String beginString="IMIX.2.0";
	
	// @ApiModelProperty(hidden = true, name="bodyLength",value="9域-报文长度",example="1")
	private Integer bodyLength;
	
	// @ApiModelProperty(required=true,name="msgType",value="35域-报文类型", example="S")
	private String msgType;
	
	// @ApiModelProperty(hidden = true, name="senderCompID",value="49域-机构21位ID",example="")
	private String senderCompID;
	
	// @ApiModelProperty(hidden = true, name="senderSubID",value="50域-API用户ID",example="")
	private String senderSubID;
	
	// @ApiModelProperty(hidden = true, name="targetCompID",value="56域-报文接收方标识(CFETS-TRADING-INFI-1:现券,CFETS-TRADING-INFI-2:回购,CFETS-TRADING-INFI-3:衍生品)",example="")
	private String targetCompID;
	
	// @ApiModelProperty(hidden = true, name="targetSubID",value="57域-报文接收方子标识(NDM:协商交易子系统,QDM-ESP:报价驱动子系统-做市报价,QDM:请求报价和指标性报价,ODM:订单驱动子系统)",example="")
	private String targetSubID;
	
	// @ApiModelProperty(required=true,name="msgSeqNum",value="34域-报文序列号",example="24")
	private Integer msgSeqNum;

	// @ApiModelProperty(hidden = true,name="sendingTime",value="52域-发送时间,格式:YYYYMMDD-HH:MM:SS.sss",example="20070820-12:40:00.000")
	private String sendingTime;

	
	public String getBeginString() {
		return beginString;
	}

	public void setBeginString(String beginString) {
		this.beginString = beginString;
	}

	public Integer getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(Integer bodyLength) {
		this.bodyLength = bodyLength;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getMsgSeqNum() {
		return msgSeqNum;
	}

	public void setMsgSeqNum(Integer msgSeqNum) {
		this.msgSeqNum = msgSeqNum;
	}

	public String getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(String sendingTime) {
		this.sendingTime = sendingTime;
	}

	public String getSenderCompID() {
		return senderCompID;
	}

	public void setSenderCompID(String senderCompID) {
		this.senderCompID = senderCompID;
	}

	public String getSenderSubID() {
		return senderSubID;
	}

	public void setSenderSubID(String senderSubID) {
		this.senderSubID = senderSubID;
	}

	public String getTargetCompID() {
		return targetCompID;
	}

	public void setTargetCompID(String targetCompID) {
		this.targetCompID = targetCompID;
	}

	public String getTargetSubID() {
		return targetSubID;
	}

	public void setTargetSubID(String targetSubID) {
		this.targetSubID = targetSubID;
	}
	
}
