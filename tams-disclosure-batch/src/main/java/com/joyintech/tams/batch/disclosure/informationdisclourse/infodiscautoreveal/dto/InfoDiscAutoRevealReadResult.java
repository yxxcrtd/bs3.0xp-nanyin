package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoreveal.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.CustomDateDeserialize;

public class InfoDiscAutoRevealReadResult extends NeedOperateEntity {
	 /**
     * ID
     */
    private String id;

    /**
     * 报表编号
     */
    private String repId;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 报表类别
     */
    private String reportCategory;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品编号
     */
    private String prodid;

    /**
     * 结束日
     */
    private Date endDate;

    /**
     * 开始日
     */
    private Date startDate;

    /**
     * 生成文件名称
     */
    private String sysFilename;

    /**
     * 生成文件路径
     */
    private String sysFilepath;

    /**
     * 生成文件时间
     */
    private Date sysFilenameDate;

    /**
     * 人工生成路径
     */
    private String filepath;

    /**
     * 人工上传时间
     */
    private Date time;

    /**
     * 人工文件名称
     */
    private String filename;

    /**
     * 附件名称
     */
    private String annex;

    /**
     * 附件路径
     */
    private String annexPath;

    /**
     * 确认状态
     */
    private String confirmStatus;

    /**
     * 确认人
     */
    private String confirmUser;

    /**
     * 确认时间
     */
    private Date confirmDate;

    /**
     * 披露状态
     */
    private String revealStatus;

    /**
     * 披露时间
     */
    private Date revealDate;

    /**
     * 披露截止日
     */
    private Date revealEndDate;

    /**
     * 披露人
     */
    private String revealUser;

    /**
     * 是否自动披露
     */
    private String isReveal;

    /**
     * 是否自动确认
     */
    private String isConfirm;

    /**
     * 上传说明
     */
    private String detail;

    /**
     * 披露渠道
     */
    private String revealChannel;

    /**
     * 取得 ID
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 取得 报表类别
     * @return 报表类别
     */
    public String getReportCategory() {
        return reportCategory;
    }

    /**
     * 设置 报表类别
     * @param reportCategory 报表类别
     */
    public void setReportCategory(String reportCategory) {
        this.reportCategory = reportCategory == null ? null : reportCategory.trim();
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
     * 取得 生成文件名称
     * @return 生成文件名称
     */
    public String getSysFilename() {
        return sysFilename;
    }

    /**
     * 设置 生成文件名称
     * @param sysFilename 生成文件名称
     */
    public void setSysFilename(String sysFilename) {
        this.sysFilename = sysFilename == null ? null : sysFilename.trim();
    }

    /**
     * 取得 生成文件路径
     * @return 生成文件路径
     */
    public String getSysFilepath() {
        return sysFilepath;
    }

    /**
     * 设置 生成文件路径
     * @param sysFilepath 生成文件路径
     */
    public void setSysFilepath(String sysFilepath) {
        this.sysFilepath = sysFilepath == null ? null : sysFilepath.trim();
    }

    /**
     * 取得 生成文件时间
     * @return 生成文件时间
     */
    public Date getSysFilenameDate() {
        return sysFilenameDate;
    }

    /**
     * 设置 生成文件时间
     * @param sysFilenameDate 生成文件时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSysFilenameDate(Date sysFilenameDate) {
        this.sysFilenameDate = sysFilenameDate;
    }

    /**
     * 取得 人工生成路径
     * @return 人工生成路径
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * 设置 人工生成路径
     * @param filepath 人工生成路径
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    /**
     * 取得 人工上传时间
     * @return 人工上传时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置 人工上传时间
     * @param time 人工上传时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 取得 人工文件名称
     * @return 人工文件名称
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置 人工文件名称
     * @param filename 人工文件名称
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * 取得 附件名称
     * @return 附件名称
     */
    public String getAnnex() {
        return annex;
    }

    /**
     * 设置 附件名称
     * @param annex 附件名称
     */
    public void setAnnex(String annex) {
        this.annex = annex == null ? null : annex.trim();
    }

    /**
     * 取得 附件路径
     * @return 附件路径
     */
    public String getAnnexPath() {
        return annexPath;
    }

    /**
     * 设置 附件路径
     * @param annexPath 附件路径
     */
    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath == null ? null : annexPath.trim();
    }

    /**
     * 取得 确认状态
     * @return 确认状态
     */
    public String getConfirmStatus() {
        return confirmStatus;
    }

    /**
     * 设置 确认状态
     * @param confirmStatus 确认状态
     */
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus == null ? null : confirmStatus.trim();
    }

    /**
     * 取得 确认人
     * @return 确认人
     */
    public String getConfirmUser() {
        return confirmUser;
    }

    /**
     * 设置 确认人
     * @param confirmUser 确认人
     */
    public void setConfirmUser(String confirmUser) {
        this.confirmUser = confirmUser == null ? null : confirmUser.trim();
    }

    /**
     * 取得 确认时间
     * @return 确认时间
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置 确认时间
     * @param confirmDate 确认时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * 取得 披露状态
     * @return 披露状态
     */
    public String getRevealStatus() {
        return revealStatus;
    }

    /**
     * 设置 披露状态
     * @param revealStatus 披露状态
     */
    public void setRevealStatus(String revealStatus) {
        this.revealStatus = revealStatus == null ? null : revealStatus.trim();
    }

    /**
     * 取得 披露时间
     * @return 披露时间
     */
    public Date getRevealDate() {
        return revealDate;
    }

    /**
     * 设置 披露时间
     * @param revealDate 披露时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevealDate(Date revealDate) {
        this.revealDate = revealDate;
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
     * 取得 披露人
     * @return 披露人
     */
    public String getRevealUser() {
        return revealUser;
    }

    /**
     * 设置 披露人
     * @param revealUser 披露人
     */
    public void setRevealUser(String revealUser) {
        this.revealUser = revealUser == null ? null : revealUser.trim();
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
     * 取得 上传说明
     * @return 上传说明
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置 上传说明
     * @param detail 上传说明
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
}
