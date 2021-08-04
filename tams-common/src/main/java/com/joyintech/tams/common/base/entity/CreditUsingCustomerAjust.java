package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class CreditUsingCustomerAjust extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 授信分项代码
     */
    private String creditMstId;

    /**
     * 调整类型,扣减，释放
     */
    private String ajustType;

    /**
     * 占用日期
     */
    private Date cdate;

    /**
     * 释放日期
     */
    private Date rdate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 调整额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 授信分项代码
     * @return 授信分项代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信分项代码
     * @param creditMstId 授信分项代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 调整类型,扣减，释放
     * @return 调整类型,扣减，释放
     */
    public String getAjustType() {
        return ajustType;
    }

    /**
     * 设置 调整类型,扣减，释放
     * @param ajustType 调整类型,扣减，释放
     */
    public void setAjustType(String ajustType) {
        this.ajustType = ajustType == null ? null : ajustType.trim();
    }

    /**
     * 取得 占用日期
     * @return 占用日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 占用日期
     * @param cdate 占用日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 释放日期
     * @return 释放日期
     */
    public Date getRdate() {
        return rdate;
    }

    /**
     * 设置 释放日期
     * @param rdate 释放日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 调整额度
     * @return 调整额度
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 调整额度
     * @param amount 调整额度
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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