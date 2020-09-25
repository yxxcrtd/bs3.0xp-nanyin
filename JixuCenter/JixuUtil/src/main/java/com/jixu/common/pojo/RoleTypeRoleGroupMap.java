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
 * @ClassName 		UserRoleGroupTypeMap
 * @author  		Pan Wei 
 * @Date 			2017年1月3日 下午11:10:40 	
 * @Description		[User,UserRole,UserRoleGroup,UserRoleGroupMap,RoleType,RoleTypeRoleGroupMap五张表组成了整个权限控制系统。
 					创建UserRole(用户权限),一个User指定一个UserRole。同时创建多个UserRoleGroup(角色组),一个用户权限可以匹配多个角色组。
 					RoleType表包括了所有的操作,每个角色组通过UserRoleGroupTypeMap表可以匹配多个操作权限。这样用户就可以和操作权限匹配起来了]  
 */

@Entity 
@Table(name="JXU_RoleTypeRoleGroupMap") 
public class RoleTypeRoleGroupMap extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -491087434724060569L;
	private int id;
	private UserRoleGroup roleGroup;
	private RoleType roleType;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_RTRGroupMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GroupID")
	public UserRoleGroup getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(UserRoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RoleID")
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
