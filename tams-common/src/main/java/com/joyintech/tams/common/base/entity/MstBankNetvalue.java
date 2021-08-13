package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class MstBankNetvalue extends BaseEntity {
    /**
     * 主键
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal seqno;

    /**
     * 主体代码，对应市场机构表customer_id
     */
    private String customerId;

    /**
     * 净资产（亿元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netValue;

    /**
     * 更新时间
     */
    private Date effDate;

    /**
     * 状态 E:有效 D:失效
     */
    private String valStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 主键
     * @return 主键
     */
    public BigDecimal getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键
     * @param seqno 主键
     */
    public void setSeqno(BigDecimal seqno) {
        this.seqno = seqno;
    }

    /**
     * 取得 主体代码，对应市场机构表customer_id
     * @return 主体代码，对应市场机构表customer_id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 主体代码，对应市场机构表customer_id
     * @param customerId 主体代码，对应市场机构表customer_id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 净资产（亿元）
     * @return 净资产（亿元）
     */
    public BigDecimal getNetValue() {
        return netValue;
    }

    /**
     * 设置 净资产（亿元）
     * @param netValue 净资产（亿元）
     */
    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 更新时间
     * @param effDate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 状态 E:有效 D:失效
     * @return 状态 E:有效 D:失效
     */
    public String getValStatus() {
        return valStatus;
    }

    /**
     * 设置 状态 E:有效 D:失效
     * @param valStatus 状态 E:有效 D:失效
     */
    public void setValStatus(String valStatus) {
        this.valStatus = valStatus == null ? null : valStatus.trim();
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