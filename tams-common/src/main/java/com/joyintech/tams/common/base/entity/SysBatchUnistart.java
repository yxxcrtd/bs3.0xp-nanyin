package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SysBatchUnistart extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 账套组
     */
    private String prdGroupSeqNo;

    /**
     * 业务开始日期
     */
    private Date busiStartDate;

    /**
     * 业务结束日期
     */
    private Date busiEndDate;

    /**
     * 批处理开始时间
     */
    private Date startTime;

    /**
     * 批处理结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 任务名称
     * @return 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 任务名称
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * 取得 账套组
     * @return 账套组
     */
    public String getPrdGroupSeqNo() {
        return prdGroupSeqNo;
    }

    /**
     * 设置 账套组
     * @param prdGroupSeqNo 账套组
     */
    public void setPrdGroupSeqNo(String prdGroupSeqNo) {
        this.prdGroupSeqNo = prdGroupSeqNo == null ? null : prdGroupSeqNo.trim();
    }

    /**
     * 取得 业务开始日期
     * @return 业务开始日期
     */
    public Date getBusiStartDate() {
        return busiStartDate;
    }

    /**
     * 设置 业务开始日期
     * @param busiStartDate 业务开始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBusiStartDate(Date busiStartDate) {
        this.busiStartDate = busiStartDate;
    }

    /**
     * 取得 业务结束日期
     * @return 业务结束日期
     */
    public Date getBusiEndDate() {
        return busiEndDate;
    }

    /**
     * 设置 业务结束日期
     * @param busiEndDate 业务结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBusiEndDate(Date busiEndDate) {
        this.busiEndDate = busiEndDate;
    }

    /**
     * 取得 批处理开始时间
     * @return 批处理开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 批处理开始时间
     * @param startTime 批处理开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 取得 批处理结束时间
     * @return 批处理结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 批处理结束时间
     * @param endTime 批处理结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}