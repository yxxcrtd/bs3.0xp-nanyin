package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ProductRequirement extends BaseEntity {
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
     * 需求发起端,产品端，投资端，客户端
     */
    private String requirementSource;

    /**
     * 数据状态，有效，撤销
     */
    private String dataStatus;

    /**
     * 是否已发起初评
     */
    private String reviewYesno;

    /**
     * 需求发起人
     */
    private String requirementUser;

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
     * 取得 产品形成依据
     * @return 产品形成依据
     */
    public String getRequirementBasis() {
        return requirementBasis;
    }

    /**
     * 设置 产品形成依据
     * @param requirementBasis 产品形成依据
     */
    public void setRequirementBasis(String requirementBasis) {
        this.requirementBasis = requirementBasis == null ? null : requirementBasis.trim();
    }

    /**
     * 取得 需求发起时间
     * @return 需求发起时间
     */
    public Date getRequirementDate() {
        return requirementDate;
    }

    /**
     * 设置 需求发起时间
     * @param requirementDate 需求发起时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRequirementDate(Date requirementDate) {
        this.requirementDate = requirementDate;
    }

    /**
     * 取得 目标客户
     * @return 目标客户
     */
    public String getTarCustomer() {
        return tarCustomer;
    }

    /**
     * 设置 目标客户
     * @param tarCustomer 目标客户
     */
    public void setTarCustomer(String tarCustomer) {
        this.tarCustomer = tarCustomer == null ? null : tarCustomer.trim();
    }

    /**
     * 取得 规模
     * @return 规模
     */
    public String getPrdScale() {
        return prdScale;
    }

    /**
     * 设置 规模
     * @param prdScale 规模
     */
    public void setPrdScale(String prdScale) {
        this.prdScale = prdScale == null ? null : prdScale.trim();
    }

    /**
     * 取得 期限
     * @return 期限
     */
    public String getPrdPeriod() {
        return prdPeriod;
    }

    /**
     * 设置 期限
     * @param prdPeriod 期限
     */
    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod == null ? null : prdPeriod.trim();
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public String getPrdRate() {
        return prdRate;
    }

    /**
     * 设置 收益率
     * @param prdRate 收益率
     */
    public void setPrdRate(String prdRate) {
        this.prdRate = prdRate == null ? null : prdRate.trim();
    }

    /**
     * 取得 产品详细需求
     * @return 产品详细需求
     */
    public String getPrdRequirementDetial() {
        return prdRequirementDetial;
    }

    /**
     * 设置 产品详细需求
     * @param prdRequirementDetial 产品详细需求
     */
    public void setPrdRequirementDetial(String prdRequirementDetial) {
        this.prdRequirementDetial = prdRequirementDetial == null ? null : prdRequirementDetial.trim();
    }

    /**
     * 取得 需求发起端,产品端，投资端，客户端
     * @return 需求发起端,产品端，投资端，客户端
     */
    public String getRequirementSource() {
        return requirementSource;
    }

    /**
     * 设置 需求发起端,产品端，投资端，客户端
     * @param requirementSource 需求发起端,产品端，投资端，客户端
     */
    public void setRequirementSource(String requirementSource) {
        this.requirementSource = requirementSource == null ? null : requirementSource.trim();
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

    /**
     * 取得 是否已发起初评
     * @return 是否已发起初评
     */
    public String getReviewYesno() {
        return reviewYesno;
    }

    /**
     * 设置 是否已发起初评
     * @param reviewYesno 是否已发起初评
     */
    public void setReviewYesno(String reviewYesno) {
        this.reviewYesno = reviewYesno == null ? null : reviewYesno.trim();
    }

    /**
     * 取得 需求发起人
     * @return 需求发起人
     */
    public String getRequirementUser() {
        return requirementUser;
    }

    /**
     * 设置 需求发起人
     * @param requirementUser 需求发起人
     */
    public void setRequirementUser(String requirementUser) {
        this.requirementUser = requirementUser == null ? null : requirementUser.trim();
    }
}