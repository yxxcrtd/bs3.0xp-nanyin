package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEqutypeMonreport extends BaseEntity {
    /**
     * 业务流水号
     */
    private String seqno;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 月报权益人类别
     */
    private String monthHolderType;

    /**
     * 权益人类别
     */
    private String holderType;

    /**
     * 取得 业务流水号
     * @return 业务流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 业务流水号
     * @param seqno 业务流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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
     * 取得 月报权益人类别
     * @return 月报权益人类别
     */
    public String getMonthHolderType() {
        return monthHolderType;
    }

    /**
     * 设置 月报权益人类别
     * @param monthHolderType 月报权益人类别
     */
    public void setMonthHolderType(String monthHolderType) {
        this.monthHolderType = monthHolderType == null ? null : monthHolderType.trim();
    }

    /**
     * 取得 权益人类别
     * @return 权益人类别
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 权益人类别
     * @param holderType 权益人类别
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }
}