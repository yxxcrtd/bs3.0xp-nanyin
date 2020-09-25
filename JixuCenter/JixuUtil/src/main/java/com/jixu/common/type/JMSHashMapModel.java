package com.jixu.common.type;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class JMSHashMapModel  implements Serializable{
	private static final long serialVersionUID = -664984101453212922L;
	private int userid;
	private Date CreateTime;
	private HashMap content;
	
	
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
	public HashMap getContent() {
		return content;
	}
	public void setContent(HashMap content) {
		this.content = content;
	}
	
}
