package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ProductBaseInfo extends BaseEntity {
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

    /**
     * 取得 产品设计编号
     * @return 产品设计编号
     */
    public String getPrdDesignId() {
        return prdDesignId;
    }

    /**
     * 设置 产品设计编号
     * @param prdDesignId 产品设计编号
     */
    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId == null ? null : prdDesignId.trim();
    }

    /**
     * 取得 产品需求编号
     * @return 产品需求编号
     */
    public String getRequirementId() {
        return requirementId;
    }

    /**
     * 设置 产品需求编号
     * @param requirementId 产品需求编号
     */
    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
    }

    /**
     * 取得 产品设计系列名称
     * @return 产品设计系列名称
     */
    public String getPrdDesignSerialName() {
        return prdDesignSerialName;
    }

    /**
     * 设置 产品设计系列名称
     * @param prdDesignSerialName 产品设计系列名称
     */
    public void setPrdDesignSerialName(String prdDesignSerialName) {
        this.prdDesignSerialName = prdDesignSerialName == null ? null : prdDesignSerialName.trim();
    }

    /**
     * 取得 产品基本目标
     * @return 产品基本目标
     */
    public String getPrdBaseGoal() {
        return prdBaseGoal;
    }

    /**
     * 设置 产品基本目标
     * @param prdBaseGoal 产品基本目标
     */
    public void setPrdBaseGoal(String prdBaseGoal) {
        this.prdBaseGoal = prdBaseGoal == null ? null : prdBaseGoal.trim();
    }

    /**
     * 取得 产品特点
     * @return 产品特点
     */
    public String getPrdCharater() {
        return prdCharater;
    }

    /**
     * 设置 产品特点
     * @param prdCharater 产品特点
     */
    public void setPrdCharater(String prdCharater) {
        this.prdCharater = prdCharater == null ? null : prdCharater.trim();
    }

    /**
     * 取得 募集方式,公募，私募
     * @return 募集方式,公募，私募
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式,公募，私募
     * @param raiseType 募集方式,公募，私募
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 投资性质,固定收益类、权益类、商品及金融衍生品、混合类
     * @return 投资性质,固定收益类、权益类、商品及金融衍生品、混合类
     */
    public String getInvestNature() {
        return investNature;
    }

    /**
     * 设置 投资性质,固定收益类、权益类、商品及金融衍生品、混合类
     * @param investNature 投资性质,固定收益类、权益类、商品及金融衍生品、混合类
     */
    public void setInvestNature(String investNature) {
        this.investNature = investNature == null ? null : investNature.trim();
    }

    /**
     * 取得 产品运作模式,封闭式净值型、封闭式非净值型、开放式净值型、开放式非净值型
     * @return 产品运作模式,封闭式净值型、封闭式非净值型、开放式净值型、开放式非净值型
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 产品运作模式,封闭式净值型、封闭式非净值型、开放式净值型、开放式非净值型
     * @param operateMode 产品运作模式,封闭式净值型、封闭式非净值型、开放式净值型、开放式非净值型
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 目标客户类型,私人银行客户专属、机构客户专属、一般个人客户、金融同业专属、高资产净值客户
     * @return 目标客户类型,私人银行客户专属、机构客户专属、一般个人客户、金融同业专属、高资产净值客户
     */
    public String getTarCustomerType() {
        return tarCustomerType;
    }

    /**
     * 设置 目标客户类型,私人银行客户专属、机构客户专属、一般个人客户、金融同业专属、高资产净值客户
     * @param tarCustomerType 目标客户类型,私人银行客户专属、机构客户专属、一般个人客户、金融同业专属、高资产净值客户
     */
    public void setTarCustomerType(String tarCustomerType) {
        this.tarCustomerType = tarCustomerType == null ? null : tarCustomerType.trim();
    }

    /**
     * 取得 目标投资者
     * @return 目标投资者
     */
    public String getTarInvestor() {
        return tarInvestor;
    }

    /**
     * 设置 目标投资者
     * @param tarInvestor 目标投资者
     */
    public void setTarInvestor(String tarInvestor) {
        this.tarInvestor = tarInvestor == null ? null : tarInvestor.trim();
    }

    /**
     * 取得 产品风险等级,PR1、PR2、PR3、 PR4 、PR5
     * @return 产品风险等级,PR1、PR2、PR3、 PR4 、PR5
     */
    public String getRskLevel() {
        return rskLevel;
    }

    /**
     * 设置 产品风险等级,PR1、PR2、PR3、 PR4 、PR5
     * @param rskLevel 产品风险等级,PR1、PR2、PR3、 PR4 、PR5
     */
    public void setRskLevel(String rskLevel) {
        this.rskLevel = rskLevel == null ? null : rskLevel.trim();
    }

    /**
     * 取得 开放期描述
     * @return 开放期描述
     */
    public String getOpenDescribe() {
        return openDescribe;
    }

    /**
     * 设置 开放期描述
     * @param openDescribe 开放期描述
     */
    public void setOpenDescribe(String openDescribe) {
        this.openDescribe = openDescribe == null ? null : openDescribe.trim();
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public String getPrdTerm() {
        return prdTerm;
    }

    /**
     * 设置 产品期限
     * @param prdTerm 产品期限
     */
    public void setPrdTerm(String prdTerm) {
        this.prdTerm = prdTerm == null ? null : prdTerm.trim();
    }

    /**
     * 取得 计划发行时间
     * @return 计划发行时间
     */
    public String getPlanIssueDate() {
        return planIssueDate;
    }

    /**
     * 设置 计划发行时间
     * @param planIssueDate 计划发行时间
     */
    public void setPlanIssueDate(String planIssueDate) {
        this.planIssueDate = planIssueDate == null ? null : planIssueDate.trim();
    }

    /**
     * 取得 计划发行量
     * @return 计划发行量
     */
    public String getPlanIssueScale() {
        return planIssueScale;
    }

    /**
     * 设置 计划发行量
     * @param planIssueScale 计划发行量
     */
    public void setPlanIssueScale(String planIssueScale) {
        this.planIssueScale = planIssueScale == null ? null : planIssueScale.trim();
    }

    /**
     * 取得 销售地区
     * @return 销售地区
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 设置 销售地区
     * @param saleArea 销售地区
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    /**
     * 取得 业绩比较基准
     * @return 业绩比较基准
     */
    public String getBaseRuleInfo() {
        return baseRuleInfo;
    }

    /**
     * 设置 业绩比较基准
     * @param baseRuleInfo 业绩比较基准
     */
    public void setBaseRuleInfo(String baseRuleInfo) {
        this.baseRuleInfo = baseRuleInfo == null ? null : baseRuleInfo.trim();
    }

    /**
     * 取得 产品费率
     * @return 产品费率
     */
    public String getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 产品费率
     * @param feeRate 产品费率
     */
    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate == null ? null : feeRate.trim();
    }

    /**
     * 取得 认购起点
     * @return 认购起点
     */
    public String getToffStart() {
        return toffStart;
    }

    /**
     * 设置 认购起点
     * @param toffStart 认购起点
     */
    public void setToffStart(String toffStart) {
        this.toffStart = toffStart == null ? null : toffStart.trim();
    }

    /**
     * 取得 巨额赎回比例
     * @return 巨额赎回比例
     */
    public String getHugeRedRatio() {
        return hugeRedRatio;
    }

    /**
     * 设置 巨额赎回比例
     * @param hugeRedRatio 巨额赎回比例
     */
    public void setHugeRedRatio(String hugeRedRatio) {
        this.hugeRedRatio = hugeRedRatio == null ? null : hugeRedRatio.trim();
    }

    /**
     * 取得 投资资产及比例情况
     * @return 投资资产及比例情况
     */
    public String getInvAssetRate() {
        return invAssetRate;
    }

    /**
     * 设置 投资资产及比例情况
     * @param invAssetRate 投资资产及比例情况
     */
    public void setInvAssetRate(String invAssetRate) {
        this.invAssetRate = invAssetRate == null ? null : invAssetRate.trim();
    }

    /**
     * 取得 意向投资策略
     * @return 意向投资策略
     */
    public String getIntenInvStrategy() {
        return intenInvStrategy;
    }

    /**
     * 设置 意向投资策略
     * @param intenInvStrategy 意向投资策略
     */
    public void setIntenInvStrategy(String intenInvStrategy) {
        this.intenInvStrategy = intenInvStrategy == null ? null : intenInvStrategy.trim();
    }

    /**
     * 取得 其他
     * @return 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置 其他
     * @param other 其他
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    /**
     * 取得 数据状态，有效，撤销
     * @return 数据状态，有效，撤销
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态，有效，撤销
     * @param dataStatus 数据状态，有效，撤销
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }
}