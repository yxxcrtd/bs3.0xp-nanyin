package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="PortPerformance") 
public class PortPerformance implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String portnum;

	/**
	 * 指数内部ID
	 */
	private int securityMasterID;
	
	/**
	 * 指数百分比
	 */
	private int indexPercentage;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="SecurityMasterID")
	public int getSecurityMasterID() {
		return securityMasterID;
	}

	public void setSecurityMasterID(int securityMasterID) {
		this.securityMasterID = securityMasterID;
	}

	@Column(name="indexPercentage")
	public int getIndexPercentage() {
		return indexPercentage;
	}

	public void setIndexPercentage(int indexPercentage) {
		this.indexPercentage = indexPercentage;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
}
