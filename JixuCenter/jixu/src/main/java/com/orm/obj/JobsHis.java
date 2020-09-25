package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		JobsHis
 * @author  		Pan Wei 
 * @Date 			2016年10月25日 下午11:24:38 	
 * @Description		[Job运行的日志表,用于在工作流界面展示某天job运行的状况]  
 */

@Entity
@Table(name = "JXJ_JOBSHIS")
public class JobsHis extends BasicEntity {
	private int id;
	private int jobID;
	private PortfolioHdr portfolio;
	private Date ledgerDate;
	private boolean status;
	private String failedDesc;
	private Date operationTime;
	private int userID;
	
	/**
	 * 
	 */
	public JobsHis() {
		this.setOperationTime(new Date());
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JobsHis_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="jobID")
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@Column(name="ledgerDate")
	public Date getLedgerDate() {
		return ledgerDate;
	}
	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	
	@Column(name="status",columnDefinition="integer",length=1)
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Column(name="failedDesc")
	public String getFailedDesc() {
		return failedDesc;
	}
	public void setFailedDesc(String failedDesc) {
		this.failedDesc = failedDesc;
	}

	@Column(name="OperationTime")
	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name="UserID")
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
