package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class CreditLinesPierceKey extends BaseEntity {
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 估值日期
     */
    private Date cdate;

    /**
     * 组合代码
     */
    private String customerId;

    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 组合代码
     * @param ptlId 组合代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

}