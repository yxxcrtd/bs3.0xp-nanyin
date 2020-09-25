package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		DataSource
 * @author  		Wang Zhongyuan 
 * @Date 			2018年12月5日 18:25
 * @Description		[数据源表]  
 */

@Entity 
@Table(name="ADPT_DataSource") 
public class DataSource {
	
	private int id;
	
	/**
	 * 连接名称
	 */
	private String connName;
	
	/**
	 * 连接描述
	 */
	private String connDesc;
	
	/**
	 * 连接类型
	 */
	private int connType;
	
	/**
	 * 主机地址
	 */
	private String hostAddress;
	
	/**
	 * 数据库名称
	 */
	private String dbName;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户密码
	 */
	private String userPassword;
	
	
	/**
	 * 获取 id
	 */
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_DataSource_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 连接名称
	 */
	@Column(name="connName")
	public String getConnName() {
		return connName;
	}

	public void setConnName(String connName) {
		this.connName = connName;
	}

	/**
	 * 连接类型
	 */
	@Column(name="connType")
	public int getConnType() {
		return connType;
	}

	public void setConnType(int connType) {
		this.connType = connType;
	}

	/**
	 * 主机地址
	 */
	@Column(name="hostAddress")
	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	/**
	 * 数据库名称
	 */
	@Column(name="dbName")
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * 用户名称
	 */
	@Column(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 用户密码
	 */
	@Column(name="userPassword")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 连接描述
	 */
	@Column(name="connDesc")
	public String getConnDesc() {
		return connDesc;
	}

	public void setConnDesc(String connDesc) {
		this.connDesc = connDesc;
	}
}
