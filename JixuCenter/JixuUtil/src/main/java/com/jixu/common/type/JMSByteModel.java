package com.jixu.common.type;

import java.io.Serializable;
import java.util.Date;

public class JMSByteModel  implements Serializable{
	private static final long serialVersionUID = -3368058901300775182L;
	private int userid;
	private Date CreateTime;
	private String content;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
