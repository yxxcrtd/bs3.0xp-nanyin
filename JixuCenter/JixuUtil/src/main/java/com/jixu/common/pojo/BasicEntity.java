package com.jixu.common.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BasicEntity{
	
	private Timestamp createDate;
	
	private String createUser;
	
	private String createDept;
	
	private Timestamp updateDate;
	
	private String updateUser;
	

	@Column(name="CREATE_TIME")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name="CREATE_USER", length=50)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name="UPDATE_TIME")
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name="UPDATE_USER", length=50)
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name="CREATE_DEPT", length=50)
	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
}
