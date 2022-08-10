package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepReportCheck extends BaseEntity {
    /**
     * 报表数据校验主键
     */
    private String reportCheckId;

    /**
     * 报表ID
     */
    private String repId;

    /**
     * 报表类别
     */
    private String reportType;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 检查日期
     */
    private Date checkDate;

    /**
     * null
     */
    private Date startDate;

    /**
     * null
     */
    private Date endDate;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 文件编号
     */
    private String fileNo;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 报表数据校验主键
     * @return 报表数据校验主键
     */
    public String getReportCheckId() {
        return reportCheckId;
    }

    /**
     * 设置 报表数据校验主键
     * @param reportCheckId 报表数据校验主键
     */
    public void setReportCheckId(String reportCheckId) {
        this.reportCheckId = reportCheckId == null ? null : reportCheckId.trim();
    }

    /**
     * 取得 报表ID
     * @return 报表ID
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表ID
     * @param repId 报表ID
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 报表类别
     * @return 报表类别
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置 报表类别
     * @param reportType 报表类别
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * 设置 报表名称
     * @param reportName 报表名称
     */
    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 检查日期
     * @return 检查日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置 检查日期
     * @param checkDate 检查日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 null
     * @param startDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 null
     * @param endDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 错误信息
     * @return 错误信息
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 设置 错误信息
     * @param errormsg 错误信息
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }

    /**
     * 取得 文件编号
     * @return 文件编号
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置 文件编号
     * @param fileNo 文件编号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
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