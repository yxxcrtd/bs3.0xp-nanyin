package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbRegTimePoints extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 报表代码
     */
    private String reportCode;

    /**
     * 细分类型
     */
    private String typeDetail;

    /**
     * 报送时点类型
     */
    private String submitTimetype;

    /**
     * 报送时点
     */
    private String submitRequire3;

    /**
     * 推算方式
     */
    private String submitRequire2;

    /**
     * 报送要求
     */
    private String submitRequire;

    /**
     * 行内报送时点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal submitPointBond;

    /**
     * 中债报送时点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal submitPointBank;

    /**
     * 信息修改报送时点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal updateSubmitPoint;

    /**
     * 信息删除报送时点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deleteSubmingPoint;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

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
     * 取得 报表代码
     * @return 报表代码
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * 设置 报表代码
     * @param reportCode 报表代码
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode == null ? null : reportCode.trim();
    }

    /**
     * 取得 细分类型
     * @return 细分类型
     */
    public String getTypeDetail() {
        return typeDetail;
    }

    /**
     * 设置 细分类型
     * @param typeDetail 细分类型
     */
    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail == null ? null : typeDetail.trim();
    }

    /**
     * 取得 报送时点类型
     * @return 报送时点类型
     */
    public String getSubmitTimetype() {
        return submitTimetype;
    }

    /**
     * 设置 报送时点类型
     * @param submitTimetype 报送时点类型
     */
    public void setSubmitTimetype(String submitTimetype) {
        this.submitTimetype = submitTimetype == null ? null : submitTimetype.trim();
    }

    /**
     * 取得 报送时点
     * @return 报送时点
     */
    public String getSubmitRequire3() {
        return submitRequire3;
    }

    /**
     * 设置 报送时点
     * @param submitRequire3 报送时点
     */
    public void setSubmitRequire3(String submitRequire3) {
        this.submitRequire3 = submitRequire3 == null ? null : submitRequire3.trim();
    }

    /**
     * 取得 推算方式
     * @return 推算方式
     */
    public String getSubmitRequire2() {
        return submitRequire2;
    }

    /**
     * 设置 推算方式
     * @param submitRequire2 推算方式
     */
    public void setSubmitRequire2(String submitRequire2) {
        this.submitRequire2 = submitRequire2 == null ? null : submitRequire2.trim();
    }

    /**
     * 取得 报送要求
     * @return 报送要求
     */
    public String getSubmitRequire() {
        return submitRequire;
    }

    /**
     * 设置 报送要求
     * @param submitRequire 报送要求
     */
    public void setSubmitRequire(String submitRequire) {
        this.submitRequire = submitRequire == null ? null : submitRequire.trim();
    }

    /**
     * 取得 行内报送时点
     * @return 行内报送时点
     */
    public BigDecimal getSubmitPointBond() {
        return submitPointBond;
    }

    /**
     * 设置 行内报送时点
     * @param submitPointBond 行内报送时点
     */
    public void setSubmitPointBond(BigDecimal submitPointBond) {
        this.submitPointBond = submitPointBond;
    }

    /**
     * 取得 中债报送时点
     * @return 中债报送时点
     */
    public BigDecimal getSubmitPointBank() {
        return submitPointBank;
    }

    /**
     * 设置 中债报送时点
     * @param submitPointBank 中债报送时点
     */
    public void setSubmitPointBank(BigDecimal submitPointBank) {
        this.submitPointBank = submitPointBank;
    }

    /**
     * 取得 信息修改报送时点
     * @return 信息修改报送时点
     */
    public BigDecimal getUpdateSubmitPoint() {
        return updateSubmitPoint;
    }

    /**
     * 设置 信息修改报送时点
     * @param updateSubmitPoint 信息修改报送时点
     */
    public void setUpdateSubmitPoint(BigDecimal updateSubmitPoint) {
        this.updateSubmitPoint = updateSubmitPoint;
    }

    /**
     * 取得 信息删除报送时点
     * @return 信息删除报送时点
     */
    public BigDecimal getDeleteSubmingPoint() {
        return deleteSubmingPoint;
    }

    /**
     * 设置 信息删除报送时点
     * @param deleteSubmingPoint 信息删除报送时点
     */
    public void setDeleteSubmingPoint(BigDecimal deleteSubmingPoint) {
        this.deleteSubmingPoint = deleteSubmingPoint;
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
}