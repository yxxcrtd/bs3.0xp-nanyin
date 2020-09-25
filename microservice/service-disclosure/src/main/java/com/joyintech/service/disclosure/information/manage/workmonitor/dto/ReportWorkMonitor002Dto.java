/*
 * 
 */
package com.joyintech.service.disclosure.information.manage.workmonitor.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.RepInstallLogDetail;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author guozhongbao
 * @version 1.0 2018年12月5日
 * @see ReportWorkMonitor002Dto
 * @since 1.0
 */
@PageId("ReportWorkMonitor002")
public class ReportWorkMonitor002Dto extends RepInstallLogDetail{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private String reportDate;
    private String execStatusName;
    private String startExecDate;
//    private String endExecDate;
    private String execResult;
    private String execResultExplain;
    private String jobName;
    private String repTypeName;
    @SuppressWarnings("unused")
	private String revealEndDateStr;
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getStartExecDate() {
		return startExecDate;
	}
	public void setStartExecDate(String startExecDate) {
		this.startExecDate = startExecDate;
	}
//	public String getEndExecDate() {
//		return endExecDate;
//	}
//	public void setEndExecDate(String endExecDate) {
//		this.endExecDate = endExecDate;
//	}
	public String getExecResult() {
		return execResult;
	}
	public void setExecResult(String execResult) {
		this.execResult = execResult;
	}
	public String getExecResultExplain() {
		return execResultExplain;
	}
	public void setExecResultExplain(String execResultExplain) {
		this.execResultExplain = execResultExplain;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getExecStatusName() {
		return execStatusName;
	}
	public void setExecStatusName(String execStatusName) {
		this.execStatusName = execStatusName;
	}
	public String getRepTypeName() {
		return repTypeName;
	}
	public void setRepTypeName(String repTypeName) {
		this.repTypeName = repTypeName;
	}
	public String getRevealEndDateStr() {
		return DateUtil.format(this.getRevealEndDate(),"yyyy-MM-dd");
	}
	public void setRevealEndDateStr(String revealEndDateStr) {
		this.revealEndDateStr = revealEndDateStr;
	}
}
