package com.joyintech.service.product.rddesgin.productelementdes.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.sql.Timestamp;

/**
 * 产品要素设计查询      要素信息dto
 *
 * @author jiangyunlong
 * @version 1.0 2019年9月25日
 * @see Productelementdes002Dto
 * @since 1.0
 */
public class Productelementdes002Dto extends BaseEntity implements ProcessEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -8291666227730929705L;
    /**
     *数据状态（有效状态）
     */
    private  String dataStatus;
    /**
     *产品设计编号
     */

    private  String  prdDesignId;
    /**
     * 需求编号
     */
    private String requirementId;
    /**
     * 产品设计系列名称
     */

    private String prdDesignSerialName;
    /**
     * 产品基本目标
     */

    private String prdBaseGoal;
    /**
     *产品特点
     */

    private String prdCharater;
    /**
     *募集方式
     */

    private String raiseType;
    /**
     * 投资性质
     */

  private String investNature;
    /**
     * 产品运作模式
     */

    private String operateMode;
    /**
     * 目标客户类型
     */

    private String tarCustomerType;
    /**
     * 目标投资者
     */

    private String tarInvestor;
    /**
     * 产品风险等级
     */

    private String rskLevel;
    /**
     * 开放期描述
     */

    private String openDescribe;
    /**
     * 产品期限
     */

    private String prdTerm;
    /**
     * 计划发行时间
     */

    private String planIssueDate;
    /**
     * 计划发行量
     */

    private String planIssueScale;
    /**
     * 销售地区
     */

    private String saleArea;
    /**
     * 业绩比较基准
     */

    private String baseRuleInfo;
    /**
     * 产品费率
     */

    private String feeRate;
    /**
     * 认购起点
     */

    private String toffStart;
    /**
     * 巨额赎回比例
     */
    private String hugeRedRatio;
    /**
     * 投资资产及比例情况
     */
    private String invAssetRate;
    /**
     * 意向投资策略
     */
    private String intenInvStrategy;
    /**
    /**
     * 其他
     */

    private String other;
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


    public String getPrdDesignSerialName() {
        return prdDesignSerialName;
    }

    public void setPrdDesignSerialName(String prdDesignSerialName) {
        this.prdDesignSerialName = prdDesignSerialName;
    }

    public String getPrdBaseGoal() {
        return prdBaseGoal;
    }

    public void setPrdBaseGoal(String prdBaseGoal) {
        this.prdBaseGoal = prdBaseGoal;
    }

    public String getPrdCharater() {
        return prdCharater;
    }

    public void setPrdCharater(String prdCharater) {
        this.prdCharater = prdCharater;
    }

    public String getRaiseType() {
        return raiseType;
    }


    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getInvestNature() {
        return investNature;
    }


    public void setInvestNature(String investNature) {
        this.investNature = investNature;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getTarCustomerType() {
        return tarCustomerType;
    }


    public void setTarCustomerType(String tarCustomerType) {
        this.tarCustomerType = tarCustomerType;
    }

    public String getTarInvestor() {
        return tarInvestor;
    }


    public void setTarInvestor(String tarInvestor) {
        this.tarInvestor = tarInvestor;
    }

    public String getRskLevel() {
        return rskLevel;
    }

    public void setRskLevel(String rskLevel) {
        this.rskLevel = rskLevel;
    }

    public String getOpenDescribe() {
        return openDescribe;
    }

    public void setOpenDescribe(String openDescribe) {
        this.openDescribe = openDescribe;
    }

    public String getPrdTerm() {
        return prdTerm;
    }

    public void setPrdTerm(String prdTerm) {
        this.prdTerm = prdTerm;
    }

    public String getPlanIssueDate() {
        return planIssueDate;
    }

    public void setPlanIssueDate(String planIssueDate) {
        this.planIssueDate = planIssueDate;
    }

    public String getPlanIssueScale() {
        return planIssueScale;
    }

    public void setPlanIssueScale(String planIssueScale) {
        this.planIssueScale = planIssueScale;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    public String getBaseRuleInfo() {
        return baseRuleInfo;
    }

    public void setBaseRuleInfo(String baseRuleInfo) {
        this.baseRuleInfo = baseRuleInfo;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getToffStart() {
        return toffStart;
    }

    public void setToffStart(String toffStart) {
        this.toffStart = toffStart;
    }

    public String getHugeRedRatio() {
        return hugeRedRatio;
    }

    public void setHugeRedRatio(String hugeRedRatio) {
        this.hugeRedRatio = hugeRedRatio;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getPrdDesignId() {
        return prdDesignId;
    }

    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getInvAssetRate() {
        return invAssetRate;
    }

    public void setInvAssetRate(String invAssetRate) {
        this.invAssetRate = invAssetRate;
    }

    public String getIntenInvStrategy() {
        return intenInvStrategy;
    }

    public void setIntenInvStrategy(String intenInvStrategy) {
        this.intenInvStrategy = intenInvStrategy;
    }
}
