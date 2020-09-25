package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SysUnaccountantInfo extends BaseEntity {
    /**
     * 一键做账主键-本批次ID
     */
    private String seqNo;

    /**
     * 一键做账任务名称，默认为（用户名+"一键做账"+时间）
     */
    private String taskName;

    /**
     * 账套参数（对应SYS_BATCHGRP_EXEC_INFO产品组）
     */
    private String prdGroupSeqNo;

    /**
     * 执行时长
     */
    private Long lastTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 业务日期
     */
    private Date busiDate;

    /**
     * 备注			

     */
    private String remark;

    /**
     * 取得 一键做账主键-本批次ID
     * @return 一键做账主键-本批次ID
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 一键做账主键-本批次ID
     * @param seqNo 一键做账主键-本批次ID
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 一键做账任务名称，默认为（用户名+"一键做账"+时间）
     * @return 一键做账任务名称，默认为（用户名+"一键做账"+时间）
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 一键做账任务名称，默认为（用户名+"一键做账"+时间）
     * @param taskName 一键做账任务名称，默认为（用户名+"一键做账"+时间）
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    /**
     * 取得 账套参数（对应SYS_BATCHGRP_EXEC_INFO产品组）
     * @return 账套参数（对应SYS_BATCHGRP_EXEC_INFO产品组）
     */
    public String getPrdGroupSeqNo() {
        return prdGroupSeqNo;
    }

    /**
     * 设置 账套参数（对应SYS_BATCHGRP_EXEC_INFO产品组）
     * @param prdGroupSeqNo 账套参数（对应SYS_BATCHGRP_EXEC_INFO产品组）
     */
    public void setPrdGroupSeqNo(String prdGroupSeqNo) {
        this.prdGroupSeqNo = prdGroupSeqNo == null ? null : prdGroupSeqNo.trim();
    }

    /**
     * 取得 执行时长
     * @return 执行时长
     */
    public Long getLastTime() {
        return lastTime;
    }

    /**
     * 设置 执行时长
     * @param lastTime 执行时长
     */
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 开始时间
     * @param startTime 开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 取得 结束时间
     * @return 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 结束时间
     * @param endTime 结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 业务日期
     * @return 业务日期
     */
    public Date getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     * @param busiDate 业务日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
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