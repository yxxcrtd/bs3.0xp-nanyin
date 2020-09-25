package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepExpectedLog extends BaseEntity {
    /**
     * 流水
     */
    private String id;

    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 产品编号
     */
    private String prodid;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 结束日
     */
    private Date endDate;

    /**
     * 开始日
     */
    private Date startDate;

    /**
     * 披露截止日
     */
    private Date revealEndDate;

    /**
     * 报表类型(报表、集合)
     */
    private String repType;

    /**
     * 报表编号
     */
    private String repId;

    /**
     * 报表名称
     */
    private String repName;

    /**
     * 生成文件类型
     */
    private String fileType;

    /**
     * 执行状态
     */
    private String execStatus;

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
     * 取得 产品编号
     * @return 产品编号
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品编号
     * @param prodid 产品编号
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 结束日
     * @param endDate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 开始日
     * @param startDate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 披露截止日
     * @return 披露截止日
     */
    public Date getRevealEndDate() {
        return revealEndDate;
    }

    /**
     * 设置 披露截止日
     * @param revealEndDate 披露截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevealEndDate(Date revealEndDate) {
        this.revealEndDate = revealEndDate;
    }

    /**
     * 取得 报表类型(报表、集合)
     * @return 报表类型(报表、集合)
     */
    public String getRepType() {
        return repType;
    }

    /**
     * 设置 报表类型(报表、集合)
     * @param repType 报表类型(报表、集合)
     */
    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    /**
     * 取得 报表编号
     * @return 报表编号
     */
    public String getRepId() {
        return repId;
    }

    /**
     * 设置 报表编号
     * @param repId 报表编号
     */
    public void setRepId(String repId) {
        this.repId = repId == null ? null : repId.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getRepName() {
        return repName;
    }

    /**
     * 设置 报表名称
     * @param repName 报表名称
     */
    public void setRepName(String repName) {
        this.repName = repName == null ? null : repName.trim();
    }

    /**
     * 取得 生成文件类型
     * @return 生成文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 生成文件类型
     * @param fileType 生成文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
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
}