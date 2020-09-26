package com.joyintech.tams.valuation.common.sztinterf.dto;
/**
 * @author yanya
 * @create 2019-12-12 17:29
 */

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName SztValBalanceDto
 * @Description: 深圳通估值表实体类model
 * @Author yanya
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class SztValuationDto extends BaseEntity {

    private static final long serialVersionUID = 1488773660197170241L;

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
     * 科目编码
     */
    private String fKmbm;

    /**
     * 科目名称
     */
    private String fKmmc;

    /**
     * 行情价格
     */
    private BigDecimal fHqjg;

    /**
     * 行情标志
     */
    private String fHqbz;

    /**
     * 证券数量
     */
    private BigDecimal fZqsl;

    /**
     * 成本
     */
    private BigDecimal fZqcb;

    /**
     * 市值（总市值）
     */
    private BigDecimal fZqsz;

    /**
     * 估值增值
     */
    private BigDecimal fGzZz;

    /**
     * 成本占净值比例
     */
    private BigDecimal fCbJzBl;

    /**
     * 市值占净值比例
     */
    private BigDecimal fSzJzBl;

    /**
     * 是否明细类数据
     */
    private String fIsDetail;

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

    public String getfKmbm() {
        return fKmbm;
    }

    public void setfKmbm(String fKmbm) {
        this.fKmbm = fKmbm;
    }

    public String getfKmmc() {
        return fKmmc;
    }

    public void setfKmmc(String fKmmc) {
        this.fKmmc = fKmmc;
    }

    public BigDecimal getfHqjg() {
        return fHqjg;
    }

    public void setfHqjg(BigDecimal fHqjg) {
        this.fHqjg = fHqjg;
    }

    public String getfHqbz() {
        return fHqbz;
    }

    public void setfHqbz(String fHqbz) {
        this.fHqbz = fHqbz;
    }

    public BigDecimal getfZqsl() {
        return fZqsl;
    }

    public void setfZqsl(BigDecimal fZqsl) {
        this.fZqsl = fZqsl;
    }

    public BigDecimal getfZqcb() {
        return fZqcb;
    }

    public void setfZqcb(BigDecimal fZqcb) {
        this.fZqcb = fZqcb;
    }

    public BigDecimal getfZqsz() {
        return fZqsz;
    }

    public void setfZqsz(BigDecimal fZqsz) {
        this.fZqsz = fZqsz;
    }

    public BigDecimal getfGzZz() {
        return fGzZz;
    }

    public void setfGzZz(BigDecimal fGzZz) {
        this.fGzZz = fGzZz;
    }

    public BigDecimal getfCbJzBl() {
        return fCbJzBl;
    }

    public void setfCbJzBl(BigDecimal fCbJzBl) {
        this.fCbJzBl = fCbJzBl;
    }

    public BigDecimal getfSzJzBl() {
        return fSzJzBl;
    }

    public void setfSzJzBl(BigDecimal fSzJzBl) {
        this.fSzJzBl = fSzJzBl;
    }

    public String getfIsDetail() {
        return fIsDetail;
    }

    public void setfIsDetail(String fIsDetail) {
        this.fIsDetail = fIsDetail;
    }
}
