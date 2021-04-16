package com.joyintech.tams.invest.interf.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 展示日志结构表
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see TrdInterfDataImport001LogDto
 * @since 1.0
 */
public class TrdInterfDataImport001LogDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String seqno;

	private String busiDate;
	
	private Date startTime;
	
	private Date endTime;
	
	private String status;
	
	private String taskCname;
	
	private String eName;
	
	private String detailAction;
	
	private BigDecimal lastTime;
	
	
	private int orderno;
	
	
	

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getBusiDate() {
		return DateUtil.formatDateAddSplit(busiDate);
	}

	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

	

	public Date getStartTime() {
		return startTime;
	}
	
	public String getStartTimeFmt(){
		return DateUtil.format(startTime, "HH:mm:ss SSS");
	}
	
	public String getStartDateFmt(){
		return DateUtil.format(startTime,"yyyy-MM-dd");
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	
	public String getEndTimeFmt(){
		return DateUtil.format(endTime, "HH:mm:ss SSS");
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskCname() {
		return taskCname;
	}

	public void setTaskCname(String taskCname) {
		this.taskCname = taskCname;
	}

	public String getDetailAction() {
		return detailAction;
	}

	public void setDetailAction(String detailAction) {
		this.detailAction = detailAction;
	}

	public BigDecimal getLastTime() {
		return lastTime;
	}

	public void setLastTime(BigDecimal lastTime) {
		this.lastTime = lastTime;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

    /**
     * 获取 eName
     * @return eName.
     */
    public String geteName() {
        return eName;
    }

    /**
     * 设置 eName
     * @param eName eName
     */
    public void seteName(String eName) {
        this.eName = eName;
    }

}
