package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.pojo.Users;

/** 
 * @ClassName 		NewFundContent
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午12:32:31 	
 * @Description		[流程式建立新基金]  
 */

@Entity 
@Table(name="JXS_NewFund") 
public class NewFund extends BasicEntity {
	
	private int id;
	private String contentName;
	private int client;
	private int newFundType;
	private boolean executeFlag;
	private Date createTime;
	private Date updateTime;
	private Users users;
	private String portnum;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="NewFund_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ContentName")
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	
	@Column(name="ClientID")
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	
	@Column(name="NewFundType")
	public int getNewFundType() {
		return newFundType;
	}
	public void setNewFundType(int newFundType) {
		this.newFundType = newFundType;
	}
	
	@Column(name="ExecuteFlag",columnDefinition="integer",length=1)
	public boolean isExecuteFlag() {
		return executeFlag;
	}
	public void setExecuteFlag(boolean executeFlag) {
		this.executeFlag = executeFlag;
	}
	
	@Column(name="CreateTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="PpdateTime")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CreateUserID")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}
	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
}
