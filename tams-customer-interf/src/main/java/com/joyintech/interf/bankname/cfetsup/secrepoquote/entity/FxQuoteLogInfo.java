package com.joyintech.interf.bankname.cfetsup.secrepoquote.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 报文日志记录实体
 * @author wot_zhaokang_zy
 *
 */
public class FxQuoteLogInfo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2205914943029727889L;
	/**
	 * 流水号
	 */
	private String id;
	/**
	 * 报价流水号
	 */
	private String  pseqno;
	/**
	 * 报价编号
	 */
	private String quoteid;
	/**
	 * 发送类型 0发送 1接收
	 */
	private String sendtype;
	/**
	 * 报价类型0 现券买卖对话报价 1 回购对话报价 2 现券买卖请求报价
	 */
	private String quotetype;
	
	/**
	 * 报文内容
	 */
	private String message;
	/**
	 * 
	 */
	private String creatorid;
	/**
	 * 
	 */
	private Timestamp createdtime;
	/**
	 * 
	 */
	private String lastmodifierid;
	/**
	 * 
	 */
	private Timestamp lastmodifiedtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPseqno() {
		return pseqno;
	}
	public void setPseqno(String pseqno) {
		this.pseqno = pseqno;
	}
	public String getQuoteid() {
		return quoteid;
	}
	public void setQuoteid(String quoteid) {
		this.quoteid = quoteid;
	}
	public String getSendtype() {
		return sendtype;
	}
	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}
	public String getQuotetype() {
		return quotetype;
	}
	public void setQuotetype(String quotetype) {
		this.quotetype = quotetype;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}

	public String getLastmodifierid() {
		return lastmodifierid;
	}
	public void setLastmodifierid(String lastmodifierid) {
		this.lastmodifierid = lastmodifierid;
	}
	public Timestamp getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}
	public Timestamp getLastmodifiedtime() {
		return lastmodifiedtime;
	}
	public void setLastmodifiedtime(Timestamp lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	
	

}
