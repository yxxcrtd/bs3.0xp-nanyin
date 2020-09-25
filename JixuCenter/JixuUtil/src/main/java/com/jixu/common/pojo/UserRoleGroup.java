package com.jixu.common.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		UserRoleGroup
 * @author  		Pan Wei 
 * @Date 			2017年1月3日 下午5:06:52 	
 * @Description		[User,UserRole,UserRoleGroup,UserRoleGroupMap,RoleType,RoleTypeRoleGroupMap五张表组成了整个权限控制系统。
 					创建UserRole(用户权限),一个User指定一个UserRole。同时创建多个UserRoleGroup(角色组),一个用户权限可以匹配多个角色组。
 					RoleType表包括了所有的操作,每个角色组通过UserRoleGroupTypeMap表可以匹配多个操作权限。这样用户就可以和操作权限匹配起来了]  
 */

@Entity 
@Table(name="JXU_UserRoleGroup") 
public class UserRoleGroup extends BasicEntity implements Serializable{
	
	private static final long serialVersionUID = -1596668898533814942L;

	private int groupID;
	
	private String groupName;
	
	private Set<RoleTypeRoleGroupMap> roleTypeRoleGroupMap;
	
	private Set<VerifyConfig> verifyConfig;

	public UserRoleGroup(int groupID) {
		this.groupID=groupID;
	}
	
	public UserRoleGroup() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_UserRoleGroup_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="GroupID")
	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	@Column(name="GroupName")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(mappedBy = "roleGroup", cascade = CascadeType.REMOVE)
	public Set<RoleTypeRoleGroupMap> getRoleTypeRoleGroupMap() {
		return roleTypeRoleGroupMap;
	}

	public void setRoleTypeRoleGroupMap(
			Set<RoleTypeRoleGroupMap> roleTypeRoleGroupMap) {
		this.roleTypeRoleGroupMap = roleTypeRoleGroupMap;
	}
	
	//审核
	@OneToMany(mappedBy = "roleGroup", cascade = CascadeType.REMOVE)
	public Set<VerifyConfig> getVerifyConfig() {
		return verifyConfig;
	}

	public void setVerifyConfig(
			Set<VerifyConfig> verifyConfig) {
		this.verifyConfig = verifyConfig;
	}
}
