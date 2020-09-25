package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="ADPT_FileBackupRecord") 
public class FileBackupRecord {
	private int id;
	
	/**
	 * 适配器编号
	 */
	private int sourceID;
	
	/**
	 * 开始时间
	 */
	private Date actionTime;
	
	/**
	 * 源文件地址
	 */
	private String fromFilePath;
	
	/**
	 * 目标文件地址
	 */
	private String toFilePath;
	

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_FILEBACKUP_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="sourceID")
	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	@Column(name="actionTime")
	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	@Column(name="fromFilePath")
	public String getFromFilePath() {
		return fromFilePath;
	}

	public void setFromFilePath(String fromFilePath) {
		this.fromFilePath = fromFilePath;
	}

	@Column(name="toFilePath")
	public String getToFilePath() {
		return toFilePath;
	}

	public void setToFilePath(String toFilePath) {
		this.toFilePath = toFilePath;
	}
}
