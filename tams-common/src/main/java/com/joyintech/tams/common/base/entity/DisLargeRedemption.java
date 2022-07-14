package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisLargeRedemption extends BaseEntity {
    /**
     * 产品登记编码
     */
    private String prodRegistNo;

    /**
     * 子产品代码
     */
    private String prodId;

    /**
     * 是否有代销机构，数据字典，是否
     */
    private String isSalesAgency;

    /**
     * 优化起始时间
     */
    private Date dealDate;

    /**
     * 触发的情形说明文字
     */
    private String triggerRemark;

    /**
     * 拒绝赎回说明（文字）
     */
    private String rejectRemark;

    /**
     * 报告发布日期
     */
    private Date rptIssueDate;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistNo() {
        return prodRegistNo;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistNo 产品登记编码
     */
    public void setProdRegistNo(String prodRegistNo) {
        this.prodRegistNo = prodRegistNo == null ? null : prodRegistNo.trim();
    }

    /**
     * 取得 子产品代码
     * @return 子产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 子产品代码
     * @param prodId 子产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 是否有代销机构，数据字典，是否
     * @return 是否有代销机构，数据字典，是否
     */
    public String getIsSalesAgency() {
        return isSalesAgency;
    }

    /**
     * 设置 是否有代销机构，数据字典，是否
     * @param isSalesAgency 是否有代销机构，数据字典，是否
     */
    public void setIsSalesAgency(String isSalesAgency) {
        this.isSalesAgency = isSalesAgency == null ? null : isSalesAgency.trim();
    }

    /**
     * 取得 优化起始时间
     * @return 优化起始时间
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 优化起始时间
     * @param dealDate 优化起始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    /**
     * 取得 触发的情形说明文字
     * @return 触发的情形说明文字
     */
    public String getTriggerRemark() {
        return triggerRemark;
    }

    /**
     * 设置 触发的情形说明文字
     * @param triggerRemark 触发的情形说明文字
     */
    public void setTriggerRemark(String triggerRemark) {
        this.triggerRemark = triggerRemark == null ? null : triggerRemark.trim();
    }

    /**
     * 取得 拒绝赎回说明（文字）
     * @return 拒绝赎回说明（文字）
     */
    public String getRejectRemark() {
        return rejectRemark;
    }

    /**
     * 设置 拒绝赎回说明（文字）
     * @param rejectRemark 拒绝赎回说明（文字）
     */
    public void setRejectRemark(String rejectRemark) {
        this.rejectRemark = rejectRemark == null ? null : rejectRemark.trim();
    }

    /**
     * 取得 报告发布日期
     * @return 报告发布日期
     */
    public Date getRptIssueDate() {
        return rptIssueDate;
    }

    /**
     * 设置 报告发布日期
     * @param rptIssueDate 报告发布日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRptIssueDate(Date rptIssueDate) {
        this.rptIssueDate = rptIssueDate;
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态
     * @param dataStatus 数据状态
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }
}