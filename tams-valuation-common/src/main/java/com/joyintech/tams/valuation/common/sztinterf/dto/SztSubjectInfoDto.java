package com.joyintech.tams.valuation.common.sztinterf.dto;
/**
 * @author yanya
 * @create 2019-12-12 17:54
 */

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName SztSubjectBalanceDto
 * @Description: 深圳通科目信息实体类model
 * @Author yanya
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class SztSubjectInfoDto extends BaseEntity {

    private static final long serialVersionUID = -6535281994772669308L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 报表类型
     */
    private String reportType;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 组合外部代码
     */
    private String fundId;

    /**
     * 公司代码
     */
    private String deptCode;

    /**
     * 证书代码
     */
    private String certId;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 报文状态
     */
    private String xmlStatus;

    /**
     * 接口类型
     */
    private String interType;

    /**
     * 业务流水号
     */
    private String serialNo;

    /**
     * 科目代码
     */
    private String fAcctCode;

    /**
     * 科目名称
     */
    private String fAcctName;

    /**
     * 科目等级
     */
    private String fAcctLevel;

    /**
     * 上级科目代码
     */
    private String fAcctParent;

    /**
     * 科目明细
     */
    private String fAcctDetail;

    /**
     * 科目类型
     */
    private String fAcctClass;

    /**
     * 余额方向
     */
    private String fbaldc;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getXmlStatus() {
        return xmlStatus;
    }

    public void setXmlStatus(String xmlStatus) {
        this.xmlStatus = xmlStatus;
    }

    public String getInterType() {
        return interType;
    }

    public void setInterType(String interType) {
        this.interType = interType;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getfAcctCode() {
        return fAcctCode;
    }

    public void setfAcctCode(String fAcctCode) {
        this.fAcctCode = fAcctCode;
    }

    public String getfAcctName() {
        return fAcctName;
    }

    public void setfAcctName(String fAcctName) {
        this.fAcctName = fAcctName;
    }

    public String getfAcctLevel() {
        return fAcctLevel;
    }

    public void setfAcctLevel(String fAcctLevel) {
        this.fAcctLevel = fAcctLevel;
    }

    public String getfAcctParent() {
        return fAcctParent;
    }

    public void setfAcctParent(String fAcctParent) {
        this.fAcctParent = fAcctParent;
    }

    public String getfAcctDetail() {
        return fAcctDetail;
    }

    public void setfAcctDetail(String fAcctDetail) {
        this.fAcctDetail = fAcctDetail;
    }

    public String getfAcctClass() {
        return fAcctClass;
    }

    public void setfAcctClass(String fAcctClass) {
        this.fAcctClass = fAcctClass;
    }

    public String getFbaldc() {
        return fbaldc;
    }

    public void setFbaldc(String fbaldc) {
        this.fbaldc = fbaldc;
    }
}
