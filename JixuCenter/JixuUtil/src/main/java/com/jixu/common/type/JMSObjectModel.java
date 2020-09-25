package com.jixu.common.type;

import java.io.Serializable;
import java.util.Date;

public class JMSObjectModel implements Serializable{
	
	private static final long serialVersionUID = -6079977287779409542L;
	private int userid;
	private String header;
	private Date sendTime;
	private Object content;
	private String portfolioCode;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public String getPortfolioCode() {
		return portfolioCode;
	}
	public void setPortfolioCode(String portfolioCode) {
		this.portfolioCode = portfolioCode;
	}
}
