package com.orm.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

@Entity 
@Table(name="ADPT_EXPFILE_SEQ") 
public class ExpFileSeq extends BasicEntity{
	
	/**
	 * 适配器ID
	 */
	private int sourceID;
	
	private int latestnum;
	
	/**
	 * 开始时间
	 */
	private int startnum;
	
	/**
	 * 最大值
	 */
	private int maxnum;
	
	/**
	 * 规则类型
	 */
	private String rolltype;
	
	@Id
	@Column(name="SOURCEID")
	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	@Column(name="LATESTNUM")
	public int getLatestnum() {
		return latestnum;
	}

	public void setLatestnum(int latestnum) {
		this.latestnum = latestnum;
	}

	@Column(name="STARTNUM")
	public int getStartnum() {
		return startnum;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	@Column(name="MAXNUM")
	public int getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}

	@Column(name="ROLLTYPE")
	public String getRolltype() {
		return rolltype;
	}

	public void setRolltype(String rolltype) {
		this.rolltype = rolltype;
	}
}
