package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstFeeinfo extends BaseEntity {
    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 关联范围，产品、通道、其他
     */
    private String applyType;

    /**
     * 费用类型，托管费、管理费等
     */
    private String feeType;

    /**
     * 计费基数，存续本金、资产规模
     */
    private String basicBill;

    /**
     * 计算基础，A/360，A/365
     */
    private String basis;

    /**
     * 收费类型，计提费用，不规则费用
     */
    private String chargeType;

    /**
     * 机构类型
     */
    private String orgType;

    /**
     * 机构二级类型
     */
    private String orgType2;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 费用代码
     * @return 费用代码
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用代码
     * @param feeId 费用代码
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 费用名称
     * @return 费用名称
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 费用名称
     * @param feeName 费用名称
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName == null ? null : feeName.trim();
    }

    /**
     * 取得 关联范围，产品、通道、其他
     * @return 关联范围，产品、通道、其他
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * 设置 关联范围，产品、通道、其他
     * @param applyType 关联范围，产品、通道、其他
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    /**
     * 取得 费用类型，托管费、管理费等
     * @return 费用类型，托管费、管理费等
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型，托管费、管理费等
     * @param feeType 费用类型，托管费、管理费等
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 计费基数，存续本金、资产规模
     * @return 计费基数，存续本金、资产规模
     */
    public String getBasicBill() {
        return basicBill;
    }

    /**
     * 设置 计费基数，存续本金、资产规模
     * @param basicBill 计费基数，存续本金、资产规模
     */
    public void setBasicBill(String basicBill) {
        this.basicBill = basicBill == null ? null : basicBill.trim();
    }

    /**
     * 取得 计算基础，A/360，A/365
     * @return 计算基础，A/360，A/365
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计算基础，A/360，A/365
     * @param basis 计算基础，A/360，A/365
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 收费类型，计提费用，不规则费用
     * @return 收费类型，计提费用，不规则费用
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 设置 收费类型，计提费用，不规则费用
     * @param chargeType 收费类型，计提费用，不规则费用
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置 机构类型
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * 取得 机构二级类型
     * @return 机构二级类型
     */
    public String getOrgType2() {
        return orgType2;
    }

    /**
     * 设置 机构二级类型
     * @param orgType2 机构二级类型
     */
    public void setOrgType2(String orgType2) {
        this.orgType2 = orgType2 == null ? null : orgType2.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}