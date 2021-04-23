package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class TrdTempStasecFlow extends BaseEntity {
    /**
     * 入出库编号
     */
    private String seqno;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 入出库方向，入库、出库
     */
    private String ps;

    /**
     * 证券账户代码
     */
    private String secAcctId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 入出库编号
     * @return 入出库编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 入出库编号
     * @param seqno 入出库编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 入出库方向，入库、出库
     * @return 入出库方向，入库、出库
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 入出库方向，入库、出库
     * @param ps 入出库方向，入库、出库
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 证券账户代码
     * @return 证券账户代码
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券账户代码
     * @param secAcctId 证券账户代码
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
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