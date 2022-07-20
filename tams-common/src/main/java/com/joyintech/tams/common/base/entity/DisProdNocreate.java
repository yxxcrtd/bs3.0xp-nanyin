package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisProdNocreate extends BaseEntity {
    /**
     * 产品登记编码
     */
    private String prodRegistNo;

    /**
     * 不成立原因
     */
    private String nocreateReason;

    /**
     * 是否有代销机构，数据字典，是否
     */
    private String isSalesAgency;

    /**
     * 报表发布日期
     */
    private Date reportIssueDate;

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
     * 取得 不成立原因
     * @return 不成立原因
     */
    public String getNocreateReason() {
        return nocreateReason;
    }

    /**
     * 设置 不成立原因
     * @param nocreateReason 不成立原因
     */
    public void setNocreateReason(String nocreateReason) {
        this.nocreateReason = nocreateReason == null ? null : nocreateReason.trim();
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
     * 取得 报表发布日期
     * @return 报表发布日期
     */
    public Date getReportIssueDate() {
        return reportIssueDate;
    }

    /**
     * 设置 报表发布日期
     * @param reportIssueDate 报表发布日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReportIssueDate(Date reportIssueDate) {
        this.reportIssueDate = reportIssueDate;
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