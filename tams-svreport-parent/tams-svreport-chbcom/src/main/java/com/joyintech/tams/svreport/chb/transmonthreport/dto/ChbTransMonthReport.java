package com.joyintech.tams.svreport.chb.transmonthreport.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.report.common.base.annotate.TableName;
@TableName("CHB_TRANS_MONTH_REPORT")
public class ChbTransMonthReport  extends BaseEntity implements  Cloneable{
	 /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    private String reporttype;

    /**
     * 开始日期
     */
    private Date startdate;

    /**
     * 结束日期
     */
    private Date enddate;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;
    /**
     * 月报明细数据
     */
    private List<ChbTransMonthReportDetail> reportDetailList;
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getIssuePartyId() {
		return issuePartyId;
	}
	public void setIssuePartyId(String issuePartyId) {
		this.issuePartyId = issuePartyId;
	}
	public String getIssuePartyName() {
		return issuePartyName;
	}
	public void setIssuePartyName(String issuePartyName) {
		this.issuePartyName = issuePartyName;
	}
	public List<ChbTransMonthReportDetail> getReportDetailList() {
		return reportDetailList;
	}
	public void setReportDetailList(List<ChbTransMonthReportDetail> reportDetailList) {
		this.reportDetailList = reportDetailList;
	}
    
}
