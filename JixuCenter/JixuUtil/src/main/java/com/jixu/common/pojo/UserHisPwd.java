package com.jixu.common.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.util.DateUtil;

/** 
 * @ClassName 		UserHisPwd
 * @author  		Wang zhongyuan 
 * @Date 			2017年6月27日 上午11:30:12 	
 * @Description		[用户的历史密码更改记录，用于确保N次历史密码不能重复]  
 */
@Entity 
@Table(name="JXU_UserHisPwd") 
public class UserHisPwd extends BasicEntity {
	private int id;
	private int userid;
	private Date pwdDate;
	private String hispwd;
	
	public UserHisPwd() {
		this.setPwdDate(DateUtil.convertStr2Date("20000101"));
	}
	
	public UserHisPwd(int id) {
		this.setId(id);
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_UserHisPwd_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="userid")
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Column(name="hispwd")
	public String getHispwd() {
		return hispwd;
	}
	public void setHispwd(String hispwd) {
		this.hispwd = hispwd;
	}
	
	@Column(name="pwdDate")
	public Date getPwdDate() {
		return pwdDate;
	}

	public void setPwdDate(Date pwdDate) {
		this.pwdDate = pwdDate;
	}
}
