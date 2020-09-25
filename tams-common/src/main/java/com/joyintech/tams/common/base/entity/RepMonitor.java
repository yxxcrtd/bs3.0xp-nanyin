package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepMonitor extends BaseEntity {
    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 运行状态
     */
    private String jobStatus;

    /**
     * 产品名称
     */
    private String proId;

    /**
     * 产品编号
     */
    private String proName;

    /**
     * 上一次运行时间
     */
    private Date endDate;

    /**
     * 下一次运行时间
     */
    private Date nextDate;

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
     * 取得 运行状态
     * @return 运行状态
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置 运行状态
     * @param jobStatus 运行状态
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProId() {
        return proId;
    }

    /**
     * 设置 产品名称
     * @param proId 产品名称
     */
    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    /**
     * 取得 产品编号
     * @return 产品编号
     */
    public String getProName() {
        return proName;
    }

    /**
     * 设置 产品编号
     * @param proName 产品编号
     */
    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    /**
     * 取得 上一次运行时间
     * @return 上一次运行时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 上一次运行时间
     * @param endDate 上一次运行时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 下一次运行时间
     * @return 下一次运行时间
     */
    public Date getNextDate() {
        return nextDate;
    }

    /**
     * 设置 下一次运行时间
     * @param nextDate 下一次运行时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }
}