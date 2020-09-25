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
 * @ClassName 		UserOpRuleMap
 * @author  		Pan Wei 
 * @Date 			2016年9月15日 下午11:08:29 	
 * @Description		[用户对于工作流的操作权限，工作流分成行情操作、交易操作和基金会计三大权限]  
 */

@Entity 
@Table(name="JXU_UserOpRuleMap") 
public class UserOpRuleMap extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 8718531343724678725L;
	private int id;
	private Users users;
	private int operationRole;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_UserOpRuleMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	@Column(name="OperationRole")
	public int getOperationRole() {
		return operationRole;
	}
	public void setOperationRole(int operationRole) {
		this.operationRole = operationRole;
	}
}
