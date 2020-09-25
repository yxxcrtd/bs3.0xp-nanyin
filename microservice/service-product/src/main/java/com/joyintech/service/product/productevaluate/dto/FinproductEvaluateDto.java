package com.joyintech.service.product.productevaluate.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * @author guyunfei
 * @see
 * @since
 */
public class FinproductEvaluateDto extends BaseEntity {

    private static final long serialVersionUID = 6729711883655641908L;

    /**
     * 产品代码
     */
    private String finprodId;
    /**
     * 产品名称
     */
    private String finprodName;
    /**
     * 评价日
     */
    private Date mdate;

    /**
     * 产品设计编号
     */
    private String prdDesignId;
    /**
     * 产品需求编号
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
     * 产品特点
     */
    private String prdCharater;
    /**
     * 募集方式,公募，私募
     */
    private String raiseType;
    /**
     * 投资性质,固定收益类、权益类、商品及金融衍生品、混合类
     */
    private String investNature;
    /**
     * 产品运作模式,封闭式净值型、封闭式非净值型、开放式净值型、开放式非净值型
     */
    private String operateMode;
    /**
     * 目标客户类型,私人银行客户专属、机构客户专属、一般个人客户、金融同业专属、高资产净值客户
     */
    private String tarCustomerType;
    /**
     * 目标投资者
     */
    private String tarInvestor;
    /**
     * 产品风险等级,PR1、PR2、PR3、 PR4 、PR5
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
     * 其他
     */
    private String other;
    /**
     * 数据状态，有效，撤销
     */
    private String dataStatus;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}
