/*
 * 文件名：RegTimePoints001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.common.common.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉
 * 报送时点管理查询Dto
 * @author tangliang
 * @version 1.0 2017年9月12日
 * @see ChbDeadLineDto
 * @since 1.0
 */
//@ValidLogic(targetClass = RegTimePoints001Validator.class)
public class ChbDeadLineDto extends BaseEntity{
    
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6031392917204989204L;
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
     * 报送要求说明
     */
    private String submitRequire;

    /**
     * 工作流id
     */
    private String workflowId;
    /**
     * 行内报送时点要求 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal submitPointBond;

    /**
     * 中债报送时点要求 
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
     * 部门代码
     */
    private String deptCode;

    /**
     * elementId
     */
    private String elementId;

    /**
     * 获取 机构代码
     * @return orgCode.
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 获取 报表代码
     * @return reportCode.
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     * 设置 报表代码
     * @param reportCode reportCode
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     * 获取 细分类型
     * @return typeDetail.
     */
    public String getTypeDetail() {
        return typeDetail;
    }

    /**
     * 设置 细分类型
     * @param typeDetail typeDetail
     */
    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    /**
     * 获取  报送要求说明
     * @return submitRequire.
     */
    public String getSubmitRequire() {
        return submitRequire;
    }

    /**
     * 设置 报送要求说明
     * @param submitRequire submitRequire
     */
    public void setSubmitRequire(String submitRequire) {
        this.submitRequire = submitRequire;
    }

    /**
     * 获取 工作流id
     * @return workflowId.
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流id
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 取得 行内报送时点
     * @return submitPointBond
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
     * @return submitPointBank
     */
    public BigDecimal getSubmitPointBank() {
        return submitPointBank;
    }

    /**
     * 设置 中债报送时点
     * @param submitPointBank 中债报送时点null
     */
    public void setSubmitPointBank(BigDecimal submitPointBank) {
        this.submitPointBank = submitPointBank;
    }

    /**
     * 取得 信息修改报送时点
     * @return updateSubmitPoint
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
     * @return deleteSubmingPoint
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
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 获取 报送时点类型
     * @return submitTimetype.
     */
    public String getSubmitTimetype() {
        return submitTimetype;
    }

    /**
     * 设置 报送时点类型
     * @param submitTimetype 报送时点类型
     */
    public void setSubmitTimetype(String submitTimetype) {
        this.submitTimetype = submitTimetype;
    }

    /**
     * 获取 报送时点
     * @return submitRequire3.
     */
    public String getSubmitRequire3() {
        return submitRequire3;
    }

    /**
     * 设置 报送时点
     * @param submitRequire3 报送时点
     */
    public void setSubmitRequire3(String submitRequire3) {
        this.submitRequire3 = submitRequire3;
    }

    /**
     * 获取 推算方式
     * @return submitRequire2.
     */
    public String getSubmitRequire2() {
        return submitRequire2;
    }

    /**
     * 设置 推算方式
     * @param submitRequire2 推算方式
     */
    public void setSubmitRequire2(String submitRequire2) {
        this.submitRequire2 = submitRequire2;
    }

    /**
     * 获取 部门代码
     * @return deptCode.
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode deptCode
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

}
