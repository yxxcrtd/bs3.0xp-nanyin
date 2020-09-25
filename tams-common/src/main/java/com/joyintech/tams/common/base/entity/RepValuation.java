package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepValuation extends RepValuationKey {
    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 单位估值（净价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repUnitNetVal;

    /**
     * 单位估值（全价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repUnitFullVal;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 估值登记日
     */
    private Date repValRegDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 单位估值（净价）
     * @return 单位估值（净价）
     */
    public BigDecimal getRepUnitNetVal() {
        return repUnitNetVal;
    }

    /**
     * 设置 单位估值（净价）
     * @param repUnitNetVal 单位估值（净价）
     */
    public void setRepUnitNetVal(BigDecimal repUnitNetVal) {
        this.repUnitNetVal = repUnitNetVal;
    }

    /**
     * 取得 单位估值（全价）
     * @return 单位估值（全价）
     */
    public BigDecimal getRepUnitFullVal() {
        return repUnitFullVal;
    }

    /**
     * 设置 单位估值（全价）
     * @param repUnitFullVal 单位估值（全价）
     */
    public void setRepUnitFullVal(BigDecimal repUnitFullVal) {
        this.repUnitFullVal = repUnitFullVal;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

    /**
     * 取得 估值登记日
     * @return 估值登记日
     */
    public Date getRepValRegDate() {
        return repValRegDate;
    }

    /**
     * 设置 估值登记日
     * @param repValRegDate 估值登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepValRegDate(Date repValRegDate) {
        this.repValRegDate = repValRegDate;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }
}