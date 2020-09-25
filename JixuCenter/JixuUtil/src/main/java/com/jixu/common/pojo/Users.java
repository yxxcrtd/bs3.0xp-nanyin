package com.jixu.common.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		Users
 * @author  		Pan Wei 
 * @Date 			2016年9月15日 下午10:29:12 	
 * @Description		[用户表，记录了用户的基本信息]  
 */
@Entity 
@Table(name="JXU_Users") 
public class Users extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -1377130489126590600L;
	
	private int userid;
	private String username;
	private String userpwd;
	private Date lastLoginTime;
	private String lastLoginIP;
	private UserRole userRole;
	private String realname;
	private String phonenum;
	private String emailAddress;
	private boolean active;
	private Date lastUpdateTime;
	private Set<UserOpRuleMap> userOpRuleMap;
	
	public Users() {
	}
	
	public Users(int userid) {
		this.setUserid(userid);
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_Users_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="Userid")
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="userpwd")
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	
	@Column(name="lastLoginTime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
	public Set<UserOpRuleMap> getUserOpRuleMap() {
		return userOpRuleMap;
	}
	public void setUserOpRuleMap(Set<UserOpRuleMap> userOpRuleMap) {
		this.userOpRuleMap = userOpRuleMap;
	}
	
	@Column(name="LastLoginIP")
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RoleID")
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Column(name="realname")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name="phonenum")
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	@Column(name="emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name="Active",columnDefinition="integer",length=1)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name="LastUpdateTime")
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
