package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.adapter.types.AdapterRunStatus;
import com.jixu.common.util.DateUtil;

@Entity 
@Table(name="ADPT_InterfaceLog") 
public class InterfaceLog {
	private int id;
	
	/**
	 * 适配器编号
	 */
	private int sourceID;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 是否成功
	 */
	private boolean resultFlag;
	
	/**
	 * 成功数量
	 */
	private int successNum;
	
	/**
	 * 失败数量
	 */
	private int failedNum;
	
	/**
	 * 删除的数量
	 */
	private int delRecords;
	
	/**
	 * 执行用户
	 */
	private int exeUser;
	
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	/**
	 * 当前状态	运行中，停止
	 */
	private int curStatus;
	
	/**
	 * 外部传入参数 
	 */
	private String outParams;
	
	/**
	 * 记录信息
	 */
	private String recordInfo;
	
	public InterfaceLog() {
		// TODO Auto-generated constructor stub
	}

	public InterfaceLog(int userID, int sourceID) {
		this.setSourceID(sourceID);
		this.setExeUser(userID);
		this.setResultFlag(false);
		this.setErrorMsg("");
		this.setCurStatus(AdapterRunStatus.running);
		this.setStartTime(new Date());
		this.setEndTime(DateUtil.convertStr2Date("20000101"));
	}

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ADPT_InterfaceLog_ID_SEQ", allocationSize=1, initialValue=1)
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

	@Column(name="startTime")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="endTime")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="resultFlag", length=1, columnDefinition="int default 0")
	public boolean isResultFlag() {
		return resultFlag;
	}

	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
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

	@Column(name="exeUser")
	public int getExeUser() {
		return exeUser;
	}

	public void setExeUser(int exeUser) {
		this.exeUser = exeUser;
	}

	@Column(name="errorMsg", length=2000)
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name="curStatus")
	public int getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(int curStatus) {
		this.curStatus = curStatus;
	}

	@Column(name="delRecords")
	public int getDelRecords() {
		return delRecords;
	}

	public void setDelRecords(int delRecords) {
		this.delRecords = delRecords;
	}

	@Column(name="OUTPARAMS")
	public String getOutParams() {
		return outParams;
	}

	public void setOutParams(String outParams) {
		this.outParams = outParams;
	}

	@Column(name="RECORDINFO")
	public String getRecordInfo() {
		return recordInfo;
	}

	public void setRecordInfo(String recordInfo) {
		this.recordInfo = recordInfo;
	}
}
