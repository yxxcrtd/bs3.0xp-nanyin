package com.joyintech.service.product.rddesgin.productelementdes.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 产品要素设计查询   需求dto
 *
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see Productelementdes001Dto
 * @since 1.0
 */
public class Productelementdes001Dto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    /**
     *数据状态（有效状态）
     */
    private  String dataStatus;
    /**
     *产品需求编号
     */

    private  String requirementId;
    /**
     *产品形成依据
     */
    private  String requirementBasis;
   /**
    * 需求发起时间
   */
   private Date requiremenDate;
    /**
     * 目标客户
     */
   private String tarCustomer;
    /**
     * 规模
     */
    private String prdScale;
    /**
     * 期限
     */
    private String prdPeriod;
    /**
     * 收益率
     */
    private String prdRate;
    /**
     * 产品详细需求
     */
    private String prdRequirementDetial;
    /**
     * 需求发起端
     */
    private String requirementSource;
    /**
     * 可行性状态（分析状态）
     */
    private String feasibilityStatus;
    /**
     * 可行需求分类
     */
    private String feasibilitType;
    /**
     * 上报状态
     */
    private String reportStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 工作流状态
     */
    private String workFlowId;
    /**
     * 操作
     */
    private String operateType;
    private  String elementId;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDataStatus() {
        return dataStatus;
    }



    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }


    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementBasis() {
        return requirementBasis;
    }

    public void setRequirementBasis(String requirementBasis) {
        this.requirementBasis = requirementBasis;
    }

    public Date getRequiremenDate() {
        return requiremenDate;
    }

    public void setRequiremenDate(Date requiremenDate) {
        this.requiremenDate = requiremenDate;
    }

    public String getTarCustomer() {
        return tarCustomer;
    }

    public void setTarCustomer(String tarCustomer) {
        this.tarCustomer = tarCustomer;
    }

    public String getPrdScale() {
        return prdScale;
    }

    public void setPrdScale(String prdScale) {
        this.prdScale = prdScale;
    }

    public String getPrdPeriod() {
        return prdPeriod;
    }

    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod;
    }

    public String getPrdRate() {
        return prdRate;
    }

    public void setPrdRate(String prdRate) {
        this.prdRate = prdRate;
    }

    public String getPrdRequirementDetial() {
        return prdRequirementDetial;
    }

    public void setPrdRequirementDetial(String prdRequirementDetial) {
        this.prdRequirementDetial = prdRequirementDetial;
    }

    public String getRequirementSource() {
        return requirementSource;
    }


    public void setRequirementSource(String requirementSource) {
        this.requirementSource = requirementSource;
    }

    public String getFeasibilityStatus() {
        return feasibilityStatus;
    }


    public void setFeasibilityStatus(String feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    public String getFeasibilitType() {
        return feasibilitType;
    }


    public void setFeasibilitType(String feasibilitType) {
        this.feasibilitType = feasibilitType;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


}
