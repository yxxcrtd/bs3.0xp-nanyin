package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepFtpDetail extends BaseEntity {
	 /**
     * 主键
     */
    private String id;

    /**
     * 披露产品编号
     */
    private String prodId;

    /**
     * 披露报表名称
     */
    private String reportId;

    /**
     * 报告期开始日
     */
    private Date statrDate;

    /**
     * 报告期结束日
     */
    private Date endDate;

    /**
     * 报表类别
     */
    private String reportCategory;
    
    /**
     * null
     */
    private String revealId;

    /**

    /**
     * 取得 主键
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 披露产品编号
     * @return 披露产品编号
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 披露产品编号
     * @param prodId 披露产品编号
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 披露报表名称
     * @return 披露报表名称
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * 设置 披露报表名称
     * @param reportId 披露报表名称
     */
    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    /**
     * 取得 报告期开始日
     * @return 报告期开始日
     */
    public Date getStatrDate() {
        return statrDate;
    }

    /**
     * 设置 报告期开始日
     * @param statrDate 报告期开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStatrDate(Date statrDate) {
        this.statrDate = statrDate;
    }

    /**
     * 取得 报告期结束日
     * @return 报告期结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 报告期结束日
     * @param endDate 报告期结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 取得 null
     * @return null
     */
    public String getRevealId() {
        return revealId;
    }

    /**
     * 设置 null
     * @param revealId null
     */
    public void setRevealId(String revealId) {
        this.revealId = revealId == null ? null : revealId.trim();
    }
}