package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscgenfiledetail.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

public class InfoDisclosureReadAndGenEntity {
	/**
     * 流水
     */
    private String id;

    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 用于标识报表与作业批次的关系
     */
    private String jobSeq;

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 开始时间
     */
    private Date endDate;

    /**
     * 结束时间
     */
    private Date startDate;

    /**
     * 执行日期
     */
    private Date execDate;

    /**
     * 最新执行时间
     */
    private Date lastexecDate;

    /**
     * 日志类型
     */
    private String flag;

    /**
     * 执行状态
     */
    private String execDetail;

    /**
     * 执行状态
     */
    private String execStatus;

    /**
     * null
     */
    private String execResult;

    /**
     * null
     */
    private String execFlag;
    /**
     * 规则id
     */
    private String ruleId;
    /**
     * 报表类型
     */
    private String reportCategory;
    
    
    /**
     * 是否与产品有关
     */
    private String  isProdRelated;
    /**
     * 取得 流水
     * @return 流水
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 流水
     * @param id 流水
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 作业编号
     * @return 作业编号
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * 设置 作业编号
     * @param jobId 作业编号
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * 取得 用于标识报表与作业批次的关系
     * @return 用于标识报表与作业批次的关系
     */
    public String getJobSeq() {
        return jobSeq;
    }

    /**
     * 设置 用于标识报表与作业批次的关系
     * @param jobSeq 用于标识报表与作业批次的关系
     */
    public void setJobSeq(String jobSeq) {
        this.jobSeq = jobSeq == null ? null : jobSeq.trim();
    }

    /**
     * 取得 作业名称
     * @return 作业名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置 作业名称
     * @param jobName 作业名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 开始时间
     * @param endDate 开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 结束时间
     * @return 结束时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 结束时间
     * @param startDate 结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 执行日期
     * @return 执行日期
     */
    public Date getExecDate() {
        return execDate;
    }

    /**
     * 设置 执行日期
     * @param execDate 执行日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    /**
     * 取得 最新执行时间
     * @return 最新执行时间
     */
    public Date getLastexecDate() {
        return lastexecDate;
    }

    /**
     * 设置 最新执行时间
     * @param lastexecDate 最新执行时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLastexecDate(Date lastexecDate) {
        this.lastexecDate = lastexecDate;
    }

    /**
     * 取得 日志类型
     * @return 日志类型
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 日志类型
     * @param flag 日志类型
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * 取得 执行状态
     * @return 执行状态
     */
    public String getExecDetail() {
        return execDetail;
    }

    /**
     * 设置 执行状态
     * @param execDetail 执行状态
     */
    public void setExecDetail(String execDetail) {
        this.execDetail = execDetail == null ? null : execDetail.trim();
    }

    /**
     * 取得 执行状态
     * @return 执行状态
     */
    public String getExecStatus() {
        return execStatus;
    }

    /**
     * 设置 执行状态
     * @param execStatus 执行状态
     */
    public void setExecStatus(String execStatus) {
        this.execStatus = execStatus == null ? null : execStatus.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getExecResult() {
        return execResult;
    }

    /**
     * 设置 null
     * @param execResult null
     */
    public void setExecResult(String execResult) {
        this.execResult = execResult == null ? null : execResult.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getExecFlag() {
        return execFlag;
    }

    /**
     * 设置 null
     * @param execFlag null
     */
    public void setExecFlag(String execFlag) {
        this.execFlag = execFlag == null ? null : execFlag.trim();
    }

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public String getIsProdRelated() {
		return isProdRelated;
	}

	public void setIsProdRelated(String isProdRelated) {
		this.isProdRelated = isProdRelated;
	}

	
    
}
