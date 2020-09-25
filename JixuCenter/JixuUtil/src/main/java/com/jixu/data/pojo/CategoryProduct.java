package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		CategoryProduct
 * @author  		Pan Wei 
 * @Date 			2018年1月29日 下午11:41:03 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="CategoryProduct") 
public class CategoryProduct implements Serializable {

	private static final long serialVersionUID = 2810061926887773994L;

	private int id;
	
	/**
	 * 连接category表
	 */
	private int categoryID;
	
	/**
	 * 分类名
	 */
	private String productName;
	
	/**
	 * 对应securityMaster中的指数证券的secid
	 */
	private String indexID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="categoryID")
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	@Column(name="productName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name="indexID")
	public String getIndexID() {
		return indexID;
	}

	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
}
