package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SysUnaccountantInfoDetail extends SysUnaccountantInfoDetailKey {
    /**
     * 对应,数据导入(VAL_INTERF_ACTIONLOG表-EXECTASK_SEQNO)，批处理（SYS_BATCHGRP_EXEC_INFO表SEQ_NO）（SYS_BATCHGRP_EXEC_INFO表SEQ_NO））
     */
    private String exectaskId;

    /**
     * 顺序
     */
    private Long orderNo;

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
     * 取得 对应,数据导入(VAL_INTERF_ACTIONLOG表-EXECTASK_SEQNO)，批处理（SYS_BATCHGRP_EXEC_INFO表SEQ_NO）（SYS_BATCHGRP_EXEC_INFO表SEQ_NO））
     * @return 对应,数据导入(VAL_INTERF_ACTIONLOG表-EXECTASK_SEQNO)，批处理（SYS_BATCHGRP_EXEC_INFO表SEQ_NO）（SYS_BATCHGRP_EXEC_INFO表SEQ_NO））
     */
    public String getExectaskId() {
        return exectaskId;
    }

    /**
     * 设置 对应,数据导入(VAL_INTERF_ACTIONLOG表-EXECTASK_SEQNO)，批处理（SYS_BATCHGRP_EXEC_INFO表SEQ_NO）（SYS_BATCHGRP_EXEC_INFO表SEQ_NO））
     * @param exectaskId 对应,数据导入(VAL_INTERF_ACTIONLOG表-EXECTASK_SEQNO)，批处理（SYS_BATCHGRP_EXEC_INFO表SEQ_NO）（SYS_BATCHGRP_EXEC_INFO表SEQ_NO））
     */
    public void setExectaskId(String exectaskId) {
        this.exectaskId = exectaskId == null ? null : exectaskId.trim();
    }

    /**
     * 取得 顺序
     * @return 顺序
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 顺序
     * @param orderNo 顺序
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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