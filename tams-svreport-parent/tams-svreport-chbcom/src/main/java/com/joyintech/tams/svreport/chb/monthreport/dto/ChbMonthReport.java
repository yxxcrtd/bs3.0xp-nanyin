package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 描述:CHB_MONTH_REPORT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
@TableName("CHB_MONTH_REPORT")
public class ChbMonthReport extends BaseEntity implements  Cloneable{
    /**
     * null
     */
    private String reportuuid;

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
     * 报表频率
     */
    private String reportfrequ;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 返回信息
     */
    private String submitFeedbackStatus;

    /**
     * 有效状态
     */
    private String status;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 产品id
     */
    private String prodid;
    /**
     * 产品id
     */
    private List<String> prodids;
    
    /**
     * 月报明细数据
     */
    private List<ChbMonthReportDetail> reportDetailList;
    /**
     * 不需要删除的主表外键 留痕时重新跑批不删除上次已报送 
     */
    private String nodelreportuuid;
    
    /**
     * 未报送uuid删除时用 
     */
    private String wbsreportuuid;
    
    private String intamttype;
    
    private String cleanamttype;
    
    
    private String monthReport;
    
    // add by leipeng 联社模式改造
    private String orgName;
    
    public String getOrgName() {
    	SysInstitution dtp = (SysInstitution) CommonUtil.queryCode(GlobalConstant.SYS_INSTITUTION_KEY,orgCode);
		
		return null!=dtp?dtp.getInstitutionName():(null==orgCode?"汇总项":"未知机构");
	}

	/**
     * null
     * @return REPORTUUID null
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * null
     * @param reportuuid null
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 期数
     * @return TERMID 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @return REPORTTYPE 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @param reporttype 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 开始日期
     * @return STARTDATE 开始日期
     */
    public Date getStartdate() {
        return startdate;
    }
    public Date getStartdateBefore() {
        return startdate==null?null:DateUtil.addDay(startdate, -1);
    }
    /**
     * 开始日期
     * @param startdate 开始日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 结束日期
     * @return ENDDATE 结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 结束日期
     * @param enddate 结束日期
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 发行机构名称
     * @return ISSUE_PARTY_NAME 发行机构名称
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 发行机构名称
     * @param issuePartyName 发行机构名称
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName == null ? null : issuePartyName.trim();
    }

    /**
     * 报表频率
     * @return REPORTFREQU 报表频率
     */
    public String getReportfrequ() {
        return reportfrequ;
    }

    /**
     * 报表频率
     * @param reportfrequ 报表频率
     */
    public void setReportfrequ(String reportfrequ) {
        this.reportfrequ = reportfrequ == null ? null : reportfrequ.trim();
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 中债报送截止日
     * @return CHB_SUBMIT_DEADLINE 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 行内报送截止日
     * @return SUBMIT_DEADLINE 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 返回信息
     * @return SUBMIT_FEEDBACK_STATUS 返回信息
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 返回信息
     * @param submitFeedbackStatus 返回信息
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 有效状态
     * @return STATUS 有效状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 有效状态
     * @param status 有效状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 报送状态
     * @return SEND_STATUS 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 机构代码
     * @return ORD_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param ORG_CODE 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }
    
	public String getStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
	}
	
	public String getSendStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
	}
	
	public String getSubmitFeedbackStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.getSubmitFeedbackStatus());
	}
	
	public String getTermname(){
		if(termid==null) return null;
		return termid.substring(0, 4)+"年"+termid.substring(4, 6)+"月";
	}
	
	public String getReportname(){
		return DictCodeListExt2.报表名称_银行月度统计表.getCodeName()+"_"+this.getTermname();
		
	}
	public String getG06Reportname(){
		return "G06非保本理财业务月度统计表"+"_"+this.getTermname();
		
	}
	
    public String getProdid() {
        return prodid;
    }
    
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

	public List<String> getProdids() {
		return prodids;
	}

	public void setProdids(List<String> prodids) {
		this.prodids = prodids;
	}

    public List<ChbMonthReportDetail> getReportDetailList() {
        return reportDetailList;
    }

    public void setReportDetailList(List<ChbMonthReportDetail> reportDetailList) {
        this.reportDetailList = reportDetailList;
    }
    
    public String getNodelreportuuid() {
		return nodelreportuuid;
	}

	public void setNodelreportuuid(String nodelreportuuid) {
		this.nodelreportuuid = nodelreportuuid;
	}

	public String getWbsreportuuid() {
		return wbsreportuuid;
	}

	public void setWbsreportuuid(String wbsreportuuid) {
		this.wbsreportuuid = wbsreportuuid;
	}
   public String getIntamttype() {
		return intamttype;
	}

	public void setIntamttype(String intamttype) {
		this.intamttype = intamttype;
	}
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		Object object = super.clone();
		return object;
	}
	
	public String getCleanamttype() {
		return cleanamttype;
	}

	public void setCleanamttype(String cleanamttype) {
		this.cleanamttype = cleanamttype;
	}

	public String getMonthReport() {
		return monthReport;
	}

	public void setMonthReport(String monthReport) {
		this.monthReport = monthReport;
	}

}