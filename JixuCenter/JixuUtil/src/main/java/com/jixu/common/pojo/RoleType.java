package com.jixu.common.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		RoleType
 * @author  		Pan Wei 
 * @Date 			2016年12月31日 下午11:45:11 	
 * @Description		[User,UserRole,UserRoleGroup,UserRoleGroupMap,RoleType,RoleTypeRoleGroupMap五张表组成了整个权限控制系统。
 					创建UserRole(用户权限),一个User指定一个UserRole。同时创建多个UserRoleGroup(角色组),一个用户权限可以匹配多个角色组。
 					RoleType表包括了所有的操作,每个角色组通过UserRoleGroupTypeMap表可以匹配多个操作权限。这样用户就可以和操作权限匹配起来了。
 					系统拥有的绝大部分操作,该表的数据是动态读取所有control层里每个action定义了name的操作。例如:在证券查询界面,key是/market/smf.do,
 					value是证券主文件-查询。权限配置页面会读取这张表所有数据]  
 */

@Entity 
@Table(name="JXU_RoleType") 
public class RoleType implements Serializable{

	private static final long serialVersionUID = -6572233182931181250L;
	private int roleID;
	private String roleName;
	private String roleURLPath;
	
	public RoleType(int roleID) {
		this.roleID=roleID;
	}
	
	public RoleType() {
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_RoleType_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="RoleID")
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	@Column(name="RoleName")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name="RoleURLPath")
	public String getRoleURLPath() {
		return roleURLPath;
	}
	public void setRoleURLPath(String roleURLPath) {
		this.roleURLPath = roleURLPath;
	}
	
	
}
