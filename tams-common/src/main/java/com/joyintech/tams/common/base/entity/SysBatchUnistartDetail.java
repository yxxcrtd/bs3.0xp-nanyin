package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class SysBatchUnistartDetail extends SysBatchUnistartDetailKey {
    /**
     * 系统编码
     */
    private String systemCode;

    /**
     * 账套组
     */
    private String prdGroupSeqNo;

    /**
     * 任务组
     */
    private String batchgrpSeqNo;

    /**
     * 业务日期
     */
    private Date busiDate;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 系统编码
     * @return 系统编码
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 设置 系统编码
     * @param systemCode 系统编码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
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
     * 取得 任务组
     * @return 任务组
     */
    public String getBatchgrpSeqNo() {
        return batchgrpSeqNo;
    }

    /**
     * 设置 任务组
     * @param batchgrpSeqNo 任务组
     */
    public void setBatchgrpSeqNo(String batchgrpSeqNo) {
        this.batchgrpSeqNo = batchgrpSeqNo == null ? null : batchgrpSeqNo.trim();
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