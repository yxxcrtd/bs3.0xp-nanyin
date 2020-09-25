package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class CreditLinesPre extends BaseEntity {
    /**
     * 预占用授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 已占用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;

    /**
     * 批复生效日
     */
    private Date vdate;

    /**
     * 批复到期日
     */
    private Date mdate;

    /**
     * 主体客户编号（是否占用机构编号）
     */
    private String parentCustomerId;
    
    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 预占用授信额度代码
     * @return 预占用授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 预占用授信额度代码
     * @param seqno 预占用授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 授信配置代码
     * @return 授信配置代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信配置代码
     * @param creditMstId 授信配置代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     * @return 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     * @param customerId 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 已占用额度
     * @return 已占用额度
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * 设置 已占用额度
     * @param hapCreditAmt 已占用额度
     */
    public void setCreditAmt(BigDecimal hapCreditAmt) {
        this.creditAmt = hapCreditAmt;
    }

    /**
     * 取得 批复生效日
     * @return 批复生效日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 批复生效日
     * @param vdate 批复生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 批复到期日
     * @return 批复到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 批复到期日
     * @param mdate 批复到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 主体客户编号（是否占用机构编号）
     * @return 主体客户编号（是否占用机构编号）
     */
    public String getParentCustomerId() {
        return parentCustomerId;
    }

    /**
     * 设置 主体客户编号（是否占用机构编号）
     * @param parentCustomerId 主体客户编号（是否占用机构编号）
     */
    public void setParentCustomerId(String parentCustomerId) {
        this.parentCustomerId = parentCustomerId == null ? null : parentCustomerId.trim();
    }

    
    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}