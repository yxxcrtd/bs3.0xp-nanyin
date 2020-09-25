package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbEqutotDetSub extends ChbEqutotDetSubKey {
    /**
     * 产品代码
     */
    private String account;

    /**
     * 明细
     */
    private Long detail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 机构代码
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shares;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 明细
     * @return 明细
     */
    public Long getDetail() {
        return detail;
    }

    /**
     * 设置 明细
     * @param detail 明细
     */
    public void setDetail(Long detail) {
        this.detail = detail;
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

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置 机构代码
     * @param shares 机构代码
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }
}