/*
 * 
 */
package com.joyintech.service.disclosure.information.manage.workmonitor.dto;

import com.joyintech.framework.annotation.PageId;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guozhongbao
 * @version 1.0 2018年11月12日
 * @see ReportWorkMonitorNew001Dto
 * @since 1.0
 */
@PageId("ReportWorkMonitor001")
public class ReportWorkMonitorNew001Dto extends ReportWorkMonitor002Dto{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    
//    private String execDate;
    private String spendTime;
    private String lastexecDate;
    private String jobSeq;
//	public String getExecDate() {
//		return execDate;
//	}
//	public void setExecDate(String execDate) {
//		this.execDate = execDate;
//	}
	public String getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(String spendTime) {
		this.spendTime = spendTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLastexecDate() {
		return lastexecDate;
	}
	public void setLastexecDate(String lastexecDate) {
		this.lastexecDate = lastexecDate;
	}
	public String getJobSeq() {
		return jobSeq;
	}
	public void setJobSeq(String jobSeq) {
		this.jobSeq = jobSeq;
	}
	
}
