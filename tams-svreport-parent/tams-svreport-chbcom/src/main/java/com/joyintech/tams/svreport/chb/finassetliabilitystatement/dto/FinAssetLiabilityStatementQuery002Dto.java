package com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;

/**
 * 理财产品资产负债表查询Dto
 * 
 * @author WanShiwei
 * @version 2.1.6 2021年4月20日
 * @see FinAssetLiabilityStatementQuery002Dto
 * @since 2.1.6
 */
public class FinAssetLiabilityStatementQuery002Dto extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 报告期
     */
    private String reportDate;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 获取 产品代码
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 产品名称
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 报告期
     * @return reportDate.
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * 设置 报告期
	 * @param reportDate 报告期
	 */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

}
