package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name="IssuerNameRecord") 
public class IssuerNameRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private int id;
	
	/**
	 * 生效时间
	 */
	private Date activedate;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 关联issuer表主键
	 */
	private int issuerID;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivedate() {
		return activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIssuerID() {
		return issuerID;
	}

	public void setIssuerID(int issuerID) {
		this.issuerID = issuerID;
	}
}
