package com.joyintech.tams.valuation.common.sztinterf.dto;
/**
 * @author yanya
 * @create 2019-12-12 18:32
 */

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName SztSubjectBalanceDto
 * @Description: 深圳通客户余额实体类model
 * @Author yanya
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class SztSubjectBalanceDto extends BaseEntity {


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
     * 币种代码
     */
    private String fCurCode;

    /**
     * 期初余额(原币)
     */
    private BigDecimal fStartBal;

    /**
     * 本期借方发生额(原币)
     */
    private BigDecimal fDebit;

    /**
     * 本期贷方发生额(原币)
     */
    private BigDecimal fCredit;

    /**
     * 期末余额(原币)
     */
    private BigDecimal fEndBal;

    /**
     * 期初余额(本位币)
     */
    private BigDecimal fBStartBal;

    /**
     * 本期借方发生额(本位币)
     */
    private BigDecimal fBDebit;

    /**
     * 本期贷方发生额(本位币)
     */
    private BigDecimal fBCredit;

    /**
     * 期末余额(本位币)
     */
    private BigDecimal fBEndBal;

    /**
     * 期初余额(数量)
     */
    private BigDecimal fAStartBal;

    /**
     * 本期借方发生额(数量)
     */
    private BigDecimal fADebit;

    /**
     * 本期贷方发生额(数量)
     */
    private BigDecimal fACredit;

    /**
     * 期末余额(数量)
     */
    private BigDecimal fAEndBal;

    /**
     * 是否最明细科目
     */
    private String fIsDetail;

    /**
     * 借方累计发生额
     */
    private BigDecimal fJToltalAmount;

    /**
     * 贷方累计发生额
     */
    private BigDecimal fDTotalAmount;

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

    public String getfCurCode() {
        return fCurCode;
    }

    public void setfCurCode(String fCurCode) {
        this.fCurCode = fCurCode;
    }

    public BigDecimal getfStartBal() {
        return fStartBal;
    }

    public void setfStartBal(BigDecimal fStartBal) {
        this.fStartBal = fStartBal;
    }

    public BigDecimal getfDebit() {
        return fDebit;
    }

    public void setfDebit(BigDecimal fDebit) {
        this.fDebit = fDebit;
    }

    public BigDecimal getfCredit() {
        return fCredit;
    }

    public void setfCredit(BigDecimal fCredit) {
        this.fCredit = fCredit;
    }

    public BigDecimal getfEndBal() {
        return fEndBal;
    }

    public void setfEndBal(BigDecimal fEndBal) {
        this.fEndBal = fEndBal;
    }

    public BigDecimal getfBStartBal() {
        return fBStartBal;
    }

    public void setfBStartBal(BigDecimal fBStartBal) {
        this.fBStartBal = fBStartBal;
    }

    public BigDecimal getfBDebit() {
        return fBDebit;
    }

    public void setfBDebit(BigDecimal fBDebit) {
        this.fBDebit = fBDebit;
    }

    public BigDecimal getfBCredit() {
        return fBCredit;
    }

    public void setfBCredit(BigDecimal fBCredit) {
        this.fBCredit = fBCredit;
    }

    public BigDecimal getfBEndBal() {
        return fBEndBal;
    }

    public void setfBEndBal(BigDecimal fBEndBal) {
        this.fBEndBal = fBEndBal;
    }

    public BigDecimal getfAStartBal() {
        return fAStartBal;
    }

    public void setfAStartBal(BigDecimal fAStartBal) {
        this.fAStartBal = fAStartBal;
    }

    public BigDecimal getfADebit() {
        return fADebit;
    }

    public void setfADebit(BigDecimal fADebit) {
        this.fADebit = fADebit;
    }

    public BigDecimal getfACredit() {
        return fACredit;
    }

    public void setfACredit(BigDecimal fACredit) {
        this.fACredit = fACredit;
    }

    public BigDecimal getfAEndBal() {
        return fAEndBal;
    }

    public void setfAEndBal(BigDecimal fAEndBal) {
        this.fAEndBal = fAEndBal;
    }

    public String getfIsDetail() {
        return fIsDetail;
    }

    public void setfIsDetail(String fIsDetail) {
        this.fIsDetail = fIsDetail;
    }

    public BigDecimal getfJToltalAmount() {
        return fJToltalAmount;
    }

    public void setfJToltalAmount(BigDecimal fJToltalAmount) {
        this.fJToltalAmount = fJToltalAmount;
    }

    public BigDecimal getfDTotalAmount() {
        return fDTotalAmount;
    }

    public void setfDTotalAmount(BigDecimal fDTotalAmount) {
        this.fDTotalAmount = fDTotalAmount;
    }
}
