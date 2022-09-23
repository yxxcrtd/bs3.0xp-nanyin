package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

public class DisProdFeediscount extends BaseEntity {
    /**
     * 优惠编号，主键
     */
    private String prodDiscountId;

    /**
     * 子产品代码
     */
    @NotEmpty(fieldName = "子产品代码")
    private String prodId;

    /**
     * 产品登记编码
     */
    @NotEmpty(fieldName = "产品登记编码")
    private String prodRegistNo;

    /**
     * 是否有代销机构，数据字典，是否
     */
    private String isSalesAgency;

    /**
     * 此产品优惠调整（补充性）描述文字
     */
    private String prodDisRemark;

    /**
     * 费用名称
     */
    @NotEmpty(fieldName = "费用名称")
    private String feename;

    /**
     * 优惠前费率
     */
    private String preFeerate;

    /**
     * 优惠后费率
     */
    private String afterFeerate;

    /**
     * 优化起始时间
     */
    private String startDate;

    /**
     * 优惠截至时间
     */
    private String endDate;

    /**
     * 本次优惠前费率
     */
    private String thisPreFeerate;

    /**
     * 本次优惠后费率
     */
    private String thisAfterFeerate;

    /**
     * 本次优惠结束后费率
     */
    private String thisEndFeerate;

    /**
     * 对此费率优惠调整（补充性）描述文字
     */
    private String feeDisRemark;

    /**
     * 报告发布日期
     */
    @NotEmpty(fieldName = "报告发布日期")
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
     * 取得 优惠编号，主键
     * @return 优惠编号，主键
     */
    public String getProdDiscountId() {
        return prodDiscountId;
    }

    /**
     * 设置 优惠编号，主键
     * @param prodDiscountId 优惠编号，主键
     */
    public void setProdDiscountId(String prodDiscountId) {
        this.prodDiscountId = prodDiscountId == null ? null : prodDiscountId.trim();
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
     * 取得 此产品优惠调整（补充性）描述文字
     * @return 此产品优惠调整（补充性）描述文字
     */
    public String getProdDisRemark() {
        return prodDisRemark;
    }

    /**
     * 设置 此产品优惠调整（补充性）描述文字
     * @param prodDisRemark 此产品优惠调整（补充性）描述文字
     */
    public void setProdDisRemark(String prodDisRemark) {
        this.prodDisRemark = prodDisRemark == null ? null : prodDisRemark.trim();
    }

    /**
     * 取得 费用名称
     * @return 费用名称
     */
    public String getFeename() {
        return feename;
    }

    /**
     * 设置 费用名称
     * @param feename 费用名称
     */
    public void setFeename(String feename) {
        this.feename = feename == null ? null : feename.trim();
    }

    /**
     * 取得 优惠前费率
     * @return 优惠前费率
     */
    public String getPreFeerate() {
        return preFeerate;
    }

    /**
     * 设置 优惠前费率
     * @param preFeerate 优惠前费率
     */
    public void setPreFeerate(String preFeerate) {
        this.preFeerate = preFeerate;
    }

    /**
     * 取得 优惠后费率
     * @return 优惠后费率
     */
    public String getAfterFeerate() {
        return afterFeerate;
    }

    /**
     * 设置 优惠后费率
     * @param afterFeerate 优惠后费率
     */
    public void setAfterFeerate(String afterFeerate) {
        this.afterFeerate = afterFeerate;
    }

    /**
     * 取得 优化起始时间
     * @return 优化起始时间
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置 优化起始时间
     * @param startDate 优化起始时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 优惠截至时间
     * @return 优惠截至时间
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置 优惠截至时间
     * @param endDate 优惠截至时间
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 本次优惠前费率
     * @return 本次优惠前费率
     */
    public String getThisPreFeerate() {
        return thisPreFeerate;
    }

    /**
     * 设置 本次优惠前费率
     * @param thisPreFeerate 本次优惠前费率
     */
    public void setThisPreFeerate(String thisPreFeerate) {
        this.thisPreFeerate = thisPreFeerate;
    }

    /**
     * 取得 本次优惠后费率
     * @return 本次优惠后费率
     */
    public String getThisAfterFeerate() {
        return thisAfterFeerate;
    }

    /**
     * 设置 本次优惠后费率
     * @param thisAfterFeerate 本次优惠后费率
     */
    public void setThisAfterFeerate(String thisAfterFeerate) {
        this.thisAfterFeerate = thisAfterFeerate;
    }

    /**
     * 取得 本次优惠结束后费率
     * @return 本次优惠结束后费率
     */
    public String getThisEndFeerate() {
        return thisEndFeerate;
    }

    /**
     * 设置 本次优惠结束后费率
     * @param thisEndFeerate 本次优惠结束后费率
     */
    public void setThisEndFeerate(String thisEndFeerate) {
        this.thisEndFeerate = thisEndFeerate;
    }

    /**
     * 取得 对此费率优惠调整（补充性）描述文字
     * @return 对此费率优惠调整（补充性）描述文字
     */
    public String getFeeDisRemark() {
        return feeDisRemark;
    }

    /**
     * 设置 对此费率优惠调整（补充性）描述文字
     * @param feeDisRemark 对此费率优惠调整（补充性）描述文字
     */
    public void setFeeDisRemark(String feeDisRemark) {
        this.feeDisRemark = feeDisRemark == null ? null : feeDisRemark.trim();
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