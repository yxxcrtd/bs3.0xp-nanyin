package com.jixu.common.pojo;


import java.io.Serializable;

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


/** 
 * @ClassName 		CheckConfig
 * @author  		Wang zhongyuan 
 * @Date 			2017年9月20日 上午11:30:12 	
 * @Description		[审核配置表,决定哪些消息将被审核]  
 */
@Entity 
@Table(name="JXU_VerifyConfig") 
public class VerifyConfig extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3306676862347605109L;
	private int id;
	private UserRoleGroup roleGroup;
	private String name;
	private String msgHeader;
	
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_VerifyConfig_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupID")
	public UserRoleGroup getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(UserRoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}
	
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="msgHeader")
	public String getMsgHeader() {
		return msgHeader;
	}
	public void setMsgHeader(String msgHeader) {
		this.msgHeader = msgHeader;
	}
}
