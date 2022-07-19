package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class DisProdAdjust extends BaseEntity {
    /**
     * 产品调整编号，主键
     */
    private String prodAdjustId;

    /**
     * 子产品代码
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String prodRegistNo;

    /**
     * 是否有代销机构，数据字典，是否
     */
    private String isSalesAgency;

    /**
     * 此产品要素调整（补充性）描述文字1
     */
    private String prodAdjRemark;

    /**
     * 此产品要素调整（补充性）描述文字2
     */
    private String prodAdjRemark2;

    /**
     * 调整要素
     */
    private String adjField;

    /**
     * 调整前
     */
    private String preAdjust;

    /**
     * 调整后
     */
    private String afterAdjust;

    /**
     * 生效时间
     */
    private Date effDate;

    /**
     * 调整此要素（补充说明文1）
     */
    private String fieldAdjRemark;

    /**
     * 调整此要素（补充说明文2）
     */
    private String fieldAdjRemark2;

    /**
     * 报告发布日期
     */
    private Date rptIssueDate;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 取得 产品调整编号，主键
     * @return 产品调整编号，主键
     */
    public String getProdAdjustId() {
        return prodAdjustId;
    }

    /**
     * 设置 产品调整编号，主键
     * @param prodAdjustId 产品调整编号，主键
     */
    public void setProdAdjustId(String prodAdjustId) {
        this.prodAdjustId = prodAdjustId == null ? null : prodAdjustId.trim();
    }

    /**
     * 取得 子产品代码
     * @return 子产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 子产品代码
     * @param prodId 子产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistNo() {
        return prodRegistNo;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistNo 产品登记编码
     */
    public void setProdRegistNo(String prodRegistNo) {
        this.prodRegistNo = prodRegistNo == null ? null : prodRegistNo.trim();
    }

    /**
     * 取得 是否有代销机构，数据字典，是否
     * @return 是否有代销机构，数据字典，是否
     */
    public String getIsSalesAgency() {
        return isSalesAgency;
    }

    /**
     * 设置 是否有代销机构，数据字典，是否
     * @param isSalesAgency 是否有代销机构，数据字典，是否
     */
    public void setIsSalesAgency(String isSalesAgency) {
        this.isSalesAgency = isSalesAgency == null ? null : isSalesAgency.trim();
    }

    /**
     * 取得 此产品要素调整（补充性）描述文字1
     * @return 此产品要素调整（补充性）描述文字1
     */
    public String getProdAdjRemark() {
        return prodAdjRemark;
    }

    /**
     * 设置 此产品要素调整（补充性）描述文字1
     * @param prodAdjRemark 此产品要素调整（补充性）描述文字1
     */
    public void setProdAdjRemark(String prodAdjRemark) {
        this.prodAdjRemark = prodAdjRemark == null ? null : prodAdjRemark.trim();
    }

    /**
     * 取得 此产品要素调整（补充性）描述文字2
     * @return 此产品要素调整（补充性）描述文字2
     */
    public String getProdAdjRemark2() {
        return prodAdjRemark2;
    }

    /**
     * 设置 此产品要素调整（补充性）描述文字2
     * @param prodAdjRemark2 此产品要素调整（补充性）描述文字2
     */
    public void setProdAdjRemark2(String prodAdjRemark2) {
        this.prodAdjRemark2 = prodAdjRemark2 == null ? null : prodAdjRemark2.trim();
    }

    /**
     * 取得 调整要素
     * @return 调整要素
     */
    public String getAdjField() {
        return adjField;
    }

    /**
     * 设置 调整要素
     * @param adjField 调整要素
     */
    public void setAdjField(String adjField) {
        this.adjField = adjField == null ? null : adjField.trim();
    }

    /**
     * 取得 调整前
     * @return 调整前
     */
    public String getPreAdjust() {
        return preAdjust;
    }

    /**
     * 设置 调整前
     * @param preAdjust 调整前
     */
    public void setPreAdjust(String preAdjust) {
        this.preAdjust = preAdjust == null ? null : preAdjust.trim();
    }

    /**
     * 取得 调整后
     * @return 调整后
     */
    public String getAfterAdjust() {
        return afterAdjust;
    }

    /**
     * 设置 调整后
     * @param afterAdjust 调整后
     */
    public void setAfterAdjust(String afterAdjust) {
        this.afterAdjust = afterAdjust == null ? null : afterAdjust.trim();
    }

    /**
     * 取得 生效时间
     * @return 生效时间
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效时间
     * @param effDate 生效时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 调整此要素（补充说明文1）
     * @return 调整此要素（补充说明文1）
     */
    public String getFieldAdjRemark() {
        return fieldAdjRemark;
    }

    /**
     * 设置 调整此要素（补充说明文1）
     * @param fieldAdjRemark 调整此要素（补充说明文1）
     */
    public void setFieldAdjRemark(String fieldAdjRemark) {
        this.fieldAdjRemark = fieldAdjRemark == null ? null : fieldAdjRemark.trim();
    }

    /**
     * 取得 调整此要素（补充说明文2）
     * @return 调整此要素（补充说明文2）
     */
    public String getFieldAdjRemark2() {
        return fieldAdjRemark2;
    }

    /**
     * 设置 调整此要素（补充说明文2）
     * @param fieldAdjRemark2 调整此要素（补充说明文2）
     */
    public void setFieldAdjRemark2(String fieldAdjRemark2) {
        this.fieldAdjRemark2 = fieldAdjRemark2 == null ? null : fieldAdjRemark2.trim();
    }

    /**
     * 取得 报告发布日期
     * @return 报告发布日期
     */
    public Date getRptIssueDate() {
        return rptIssueDate;
    }

    /**
     * 设置 报告发布日期
     * @param rptIssueDate 报告发布日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRptIssueDate(Date rptIssueDate) {
        this.rptIssueDate = rptIssueDate;
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