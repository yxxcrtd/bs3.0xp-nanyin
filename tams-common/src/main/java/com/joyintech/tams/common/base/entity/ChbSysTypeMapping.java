package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysTypeMapping extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 类别1
     */
    private String type1;

    /**
     * 类别明细1
     */
    private String typeDetail1;

    /**
     * 类别2
     */
    private String type2;

    /**
     * 类别明细2
     */
    private String typeDetail2;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 机构代码
     */
    private String orgCode;

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
     * 取得 类别1
     * @return 类别1
     */
    public String getType1() {
        return type1;
    }

    /**
     * 设置 类别1
     * @param type1 类别1
     */
    public void setType1(String type1) {
        this.type1 = type1 == null ? null : type1.trim();
    }

    /**
     * 取得 类别明细1
     * @return 类别明细1
     */
    public String getTypeDetail1() {
        return typeDetail1;
    }

    /**
     * 设置 类别明细1
     * @param typeDetail1 类别明细1
     */
    public void setTypeDetail1(String typeDetail1) {
        this.typeDetail1 = typeDetail1 == null ? null : typeDetail1.trim();
    }

    /**
     * 取得 类别2
     * @return 类别2
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 类别2
     * @param type2 类别2
     */
    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    /**
     * 取得 类别明细2
     * @return 类别明细2
     */
    public String getTypeDetail2() {
        return typeDetail2;
    }

    /**
     * 设置 类别明细2
     * @param typeDetail2 类别明细2
     */
    public void setTypeDetail2(String typeDetail2) {
        this.typeDetail2 = typeDetail2 == null ? null : typeDetail2.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
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
}