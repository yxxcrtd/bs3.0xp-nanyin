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
 * @ClassName 		WorkFlowFactory
 * @author  		Pan Wei 
 * @Date 			2016年9月15日 下午10:26:02 	
 * @Description		[记录工作流做帐的一些日志内容，用于配合工作流页面的展示]  
 */

@Entity 
@Table(name="JXJ_WorkFlowFactory") 
public class WorkFlowFactory extends BasicEntity {
	private int id;
	private PortfolioHdr portfolio;
	private Date ledgerDate;
	private int wfStep;
	private Date startTime;
	private int successNum;
	private int failedNum;
	private Date endTime;
	private int status;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="WorkFlowFactory_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	@Column(name="wfStep")
	public int getWfStep() {
		return wfStep;
	}
	public void setWfStep(int wfStep) {
		this.wfStep = wfStep;
	}
	
	@Column(name="startTime")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="successNum")
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	
	@Column(name="failedNum")
	public int getFailedNum() {
		return failedNum;
	}
	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}
	
	@Column(name="endTime")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
