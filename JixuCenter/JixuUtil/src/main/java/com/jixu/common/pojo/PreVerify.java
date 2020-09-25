package com.jixu.common.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		PreVerify
 * @author  		Wang Zhongyuan 
 * @Date 			2017年9月22日 上午11:10:52 	
 * @Description		[该表记录了需要审核的操作。操作都会产生一条消息，如果该操作被配置成需要审核,该操作生成的消息编号则保存至该表。当审核通过时，再去msglog表激活该消息] 
 */

@Entity 
@Table(name="JXU_PreVerify") 
public class PreVerify {
	
	private int id;
	
	private Date operationDate;
	
	private int verifyconfigID;
	
	private String portfolio;
	
	private int submitterID;
	
	private int status;
	
	private long msglogid;
	
	private int verifyPersonID;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_PreVerify_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(name="portfolio")
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="submitterID")
	public int getSubmitterID() {
		return submitterID;
	}

	public void setSubmitterID(int submitterID) {
		this.submitterID = submitterID;
	}
	

	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="operationDate")
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	@Column(name="msglogid")
	public long getMsglogid() {
		return msglogid;
	}

	public void setMsglogid(long msglogid) {
		this.msglogid = msglogid;
	}

	@Column(name="verifyconfigID")
	public int getVerifyconfigID() {
		return verifyconfigID;
	}

	public void setVerifyconfigID(int verifyconfigID) {
		this.verifyconfigID = verifyconfigID;
	}

	@Column(name="verifyPersonID")
	public int getVerifyPersonID() {
		return verifyPersonID;
	}

	public void setVerifyPersonID(int verifyPersonID) {
		this.verifyPersonID = verifyPersonID;
	}
	
}
