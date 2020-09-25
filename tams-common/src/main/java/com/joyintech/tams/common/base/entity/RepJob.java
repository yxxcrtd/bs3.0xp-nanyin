package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepJob extends BaseEntity {
    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 时间样式类型
     */
    private String loopDateType;

    /**
     * 时间样式值
     */
    private String loopDateValue;

    /**
     * 时间样式描述
     */
    private String loopDateDetail;

    /**
     * 作业开始日
     */
    private String startDate;

    /**
     * 作业结束日
     */
    private String endDate;

    /**
     * 规则编号
     */
    private String ruleId;

    /**
     * null
     */
    private String isprodrelated;

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
     * 取得 时间样式类型
     * @return 时间样式类型
     */
    public String getLoopDateType() {
        return loopDateType;
    }

    /**
     * 设置 时间样式类型
     * @param loopDateType 时间样式类型
     */
    public void setLoopDateType(String loopDateType) {
        this.loopDateType = loopDateType == null ? null : loopDateType.trim();
    }

    /**
     * 取得 时间样式值
     * @return 时间样式值
     */
    public String getLoopDateValue() {
        return loopDateValue;
    }

    /**
     * 设置 时间样式值
     * @param loopDateValue 时间样式值
     */
    public void setLoopDateValue(String loopDateValue) {
        this.loopDateValue = loopDateValue == null ? null : loopDateValue.trim();
    }

    /**
     * 取得 时间样式描述
     * @return 时间样式描述
     */
    public String getLoopDateDetail() {
        return loopDateDetail;
    }

    /**
     * 设置 时间样式描述
     * @param loopDateDetail 时间样式描述
     */
    public void setLoopDateDetail(String loopDateDetail) {
        this.loopDateDetail = loopDateDetail == null ? null : loopDateDetail.trim();
    }

    /**
     * 取得 作业开始日
     * @return 作业开始日
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置 作业开始日
     * @param startDate 作业开始日
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     * 取得 作业结束日
     * @return 作业结束日
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置 作业结束日
     * @param endDate 作业结束日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     * 取得 规则编号
     * @return 规则编号
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则编号
     * @param ruleId 规则编号
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsprodrelated() {
        return isprodrelated;
    }

    /**
     * 设置 null
     * @param isprodrelated null
     */
    public void setIsprodrelated(String isprodrelated) {
        this.isprodrelated = isprodrelated == null ? null : isprodrelated.trim();
    }
}