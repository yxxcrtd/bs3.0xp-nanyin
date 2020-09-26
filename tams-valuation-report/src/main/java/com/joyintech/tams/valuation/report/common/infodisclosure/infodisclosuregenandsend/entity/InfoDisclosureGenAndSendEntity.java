package com.joyintech.tams.valuation.report.common.infodisclosure.infodisclosuregenandsend.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

public class InfoDisclosureGenAndSendEntity {
	/**
     * 流水
     */
    private String id;

    /**
     * 作业编号
     */
    private String jobId;

    /**
     * 用于标识报表与作业批次的关系
     */
    private String jobSeq;

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
     * 披露渠道
     */
    private String revealChannel;

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
     * 是否自动确认
     */
    private String isConfirm;

    /**
     * 是否自动披露
     */
    private String isReveal;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板目录
     */
    private String templatePath;

    /**
     * null
     */
    private String templateName;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 报表状态
     */
    private String reportType;

    /**
     * null
     */
    private Long repOrder;

    /**
     * null
     */
    private String reportCategory;
    /**
     * fileName
     */
    private String fileName;
    /**
     * filePathSeqno
     */
    private String FilePathSeqno;
    /**
     * localPath
     */
    private String localPath;
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
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 设置 模板编号
     * @param templateId 模板编号
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    /**
     * 取得 模板目录
     * @return 模板目录
     */
    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * 设置 模板目录
     * @param templatePath 模板目录
     */
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath == null ? null : templatePath.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置 null
     * @param templateName null
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
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
     * 取得 报表状态
     * @return 报表状态
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置 报表状态
     * @param reportType 报表状态
     */
    public void setReportType(String reportType) {
        this.reportType = reportType == null ? null : reportType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getRepOrder() {
        return repOrder;
    }

    /**
     * 设置 null
     * @param repOrder null
     */
    public void setRepOrder(Long repOrder) {
        this.repOrder = repOrder;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getReportCategory() {
        return reportCategory;
    }

    /**
     * 设置 null
     * @param reportCategory null
     */
    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory == null ? null : reportCategory.trim();
    }
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
     * 取得 用于标识报表与作业批次的关系
     * @return 用于标识报表与作业批次的关系
     */
    public String getJobSeq() {
        return jobSeq;
    }

    /**
     * 设置 用于标识报表与作业批次的关系
     * @param jobSeq 用于标识报表与作业批次的关系
     */
    public void setJobSeq(String jobSeq) {
        this.jobSeq = jobSeq == null ? null : jobSeq.trim();
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
     * 取得 披露渠道
     * @return 披露渠道
     */
    public String getRevealChannel() {
        return revealChannel;
    }

    /**
     * 设置 披露渠道
     * @param revealChannel 披露渠道
     */
    public void setRevealChannel(String revealChannel) {
        this.revealChannel = revealChannel == null ? null : revealChannel.trim();
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
     * 取得 是否自动确认
     * @return 是否自动确认
     */
    public String getIsConfirm() {
        return isConfirm;
    }

    /**
     * 设置 是否自动确认
     * @param isConfirm 是否自动确认
     */
    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm == null ? null : isConfirm.trim();
    }

    /**
     * 取得 是否自动披露
     * @return 是否自动披露
     */
    public String getIsReveal() {
        return isReveal;
    }

    /**
     * 设置 是否自动披露
     * @param isReveal 是否自动披露
     */
    public void setIsReveal(String isReveal) {
        this.isReveal = isReveal == null ? null : isReveal.trim();
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePathSeqno() {
		return FilePathSeqno;
	}

	public void setFilePathSeqno(String filePathSeqno) {
		FilePathSeqno = filePathSeqno;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
    
}
