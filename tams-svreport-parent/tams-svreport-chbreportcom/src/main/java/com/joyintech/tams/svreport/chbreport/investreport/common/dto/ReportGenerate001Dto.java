package com.joyintech.tams.svreport.chbreport.investreport.common.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * @ClassName ReportGenerate001Dto
 * @Description: 非标投资运行报告查询dto
 * @Author yanya
 * @Date 2021-05-06
 * @Version V1.0
 **/
public class ReportGenerate001Dto extends BaseEntity {

    private static final long serialVersionUID = 2518169843617957866L;
    /**
     * 报告id
     */
    private String repId;
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 公告披露日期或报表日期
     */
    private String revealDate;

    /**
     * 报表类型
     */
    private String reportType;

    /**
     * 产品名称+代码
     */
    private String  prodIdName;

    /**
     * 产品名称
     */
    private String  prodName;

    /**
     * 生成时间
     */
    private String  createTimeFmt;

    /**
     * 报表时间格式化
     */
    private String endDateFmt;

    /**
     * 选中的表格数据
     */
    private String datas;

    /**
     * 年份
     */
    private String year;

    /**
     * 确认状态
     */
    private String confirmStatus;

    /**
     * 生成状态
     */
    private String createStatus;


    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getProdIdName() {
        return prodIdName;
    }

    public void setProdIdName(String prodIdName) {
        this.prodIdName = prodIdName;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getCreateTimeFmt() {
        if(StringUtil.isNotEmpty(super.getCreateTime())) {
            return DateUtil.format(super.getCreateTime(), "yyyy年MM月dd日HH点mm分");
        }
        return "";
    }

    public void setCreateTimeFmt(String createTimeFmt) {
        this.createTimeFmt = createTimeFmt;
    }

    public String getEndDateFmt() {
        if(StringUtil.isNotEmpty(this.getEndDate())) {
            return DateUtil.format(DateUtil.parse(this.getEndDate()), DateUtil.DEFAULT_DATE_FORMAT_CN);
        }
        return "";
    }

    public void setEndDateFmt(String endDateFmt) {
        this.endDateFmt = endDateFmt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }


    public String getRevealDate() {
        return revealDate;
    }

    public void setRevealDate(String revealDate) {
        this.revealDate = revealDate;
    }
}
