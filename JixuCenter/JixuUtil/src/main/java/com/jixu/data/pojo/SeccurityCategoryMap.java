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
@Table(name="SeccurityCategoryMap") 
public class SeccurityCategoryMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	/**
	 * 关联SecurityMaster的ID
	 */
	private int securityMasterID;
	
	/**
	 * 生效时间
	 */
	private Date activedate;
	
	/**
	 * 资产分类
	 */
	private int categoryProductID;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="securityMasterID")
	public int getSecurityMasterID() {
		return securityMasterID;
	}

	public void setSecurityMasterID(int securityMasterID) {
		this.securityMasterID = securityMasterID;
	}

	@Column(name="activedate")
	public Date getActivedate() {
		return activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	@Column(name="categoryProductID")
	public int getCategoryProductID() {
		return categoryProductID;
	}

	public void setCategoryProductID(int categoryProductID) {
		this.categoryProductID = categoryProductID;
	}

}
