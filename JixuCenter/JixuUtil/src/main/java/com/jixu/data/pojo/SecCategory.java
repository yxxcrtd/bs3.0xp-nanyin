package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		BlocksMaster
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午11:00:55 	
 * @Description		[板块概念, 末级的ID,对外提供外键] 
 *  
 * @return  
 */

@Entity 
@Table(name="SecCategory") 
public class SecCategory implements Serializable{

	private static final long serialVersionUID = -6232793895062293118L;

	/**
	 * 系统内部ID
	 */
	private int secCategoryID;
	
	/**
	 * 板块概念名称
	 */
	private String secCategoryName;
	
	/**
	 * 概念类型
	 */
	private int secCategoryType;

	/**
	 * 是在第几层级
	 */
	private int groupLevel;
	
	/**
	 * 是否是末级
	 */
	private boolean finalFlag;
	
	/**
	 * 上级板块概念ID
	 */
	private int fatherID;
	
	/**
	 * 映射Category表的ID
	 */
	private int sourceID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="secCategoryID")
	public int getSecCategoryID() {
		return secCategoryID;
	}

	public void setSecCategoryID(int secCategoryID) {
		this.secCategoryID = secCategoryID;
	}

	@Column(name="secCategoryName")
	public String getSecCategoryName() {
		return secCategoryName;
	}

	public void setSecCategoryName(String secCategoryName) {
		this.secCategoryName = secCategoryName;
	}
	
	@Column(name="secCategoryType")
	public int getSecCategoryType() {
		return secCategoryType;
	}

	public void setSecCategoryType(int secCategoryType) {
		this.secCategoryType = secCategoryType;
	}
	
	
	@Column(name="groupLevel")
	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

	@Column(name="finalFlag")
	public boolean isFinalFlag() {
		return finalFlag;
	}

	public void setFinalFlag(boolean finalFlag) {
		this.finalFlag = finalFlag;
	}

	@Column(name="fatherID")
	public int getFatherID() {
		return fatherID;
	}

	public void setFatherID(int fatherID) {
		this.fatherID = fatherID;
	}

	@Column(name="sourceID")
	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}
}
