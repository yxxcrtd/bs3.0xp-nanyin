package com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class FinProductFeaAna001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7498393343772328265L;

    /**
     * 产品需求编号
     */
    private String requirementId;
    /**
     * 产品形成依据
     */
    private String requirementBasis;
    /**
     * 需求发起时间
     */
    private Date requirementDate;
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
     * 可行性状态(分析结论)
     */
    private String feasibilityStatus;
    /**
     * 可行需求分类
     */
    private String feasibilityType;
    /**
     * 有效状态
     */
    private String effectStatus;

    /**
     * 可行性分析结论
     */
    private String feasibilityResult;

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

    public Date getRequirementDate() {
        return requirementDate;
    }

    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
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

    public String getFeasibilityStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.可行性状态, feasibilityStatus);
    }

    public void setFeasibilityStatus(String feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    public String getFeasibilityType() {
        return feasibilityType;
    }

    public String getFeasibilityTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.可行需求分类,feasibilityType);
    }

    public void setFeasibilityType(String feasibilityType) {
        this.feasibilityType = feasibilityType;
    }

    public String getEffectStatus() {
        return effectStatus;
    }

    public void setEffectStatus(String effectStatus) {
        this.effectStatus = effectStatus;
    }

    public String getFeasibilityResult() {
        return feasibilityResult;
    }

    public void setFeasibilityResult(String feasibilityResult) {
        this.feasibilityResult = feasibilityResult;
    }
}
