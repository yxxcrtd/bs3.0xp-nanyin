package com.joyintech.tams.common.base.entity;

public class ChbPrdBaseinfoDetailWithBLOBs extends ChbPrdBaseinfoDetail {
    /**
     * 境外托管机构国别
     */
    private String outDepCtry;

    /**
     * 境外托管机构名称
     */
    private String outDepName;

    /**
     * 业绩比较基准说明
     */
    private String yieldRemark;

    /**
     * 无规律开放说明
     */
    private String ruleComment;

    /**
     * 开放期业务说明
     */
    private String openBussRemark;

    /**
     * 备注
     */
    private String remark;

    /**
     * 投资资产种类及比例
     */
    private String invTypeRat;

    /**
     * 产品销售地区
     */
    private String saleArea;

    /**
     * 收益率分档情况说明
     */
    private String yieldComment;

    /**
     * 取得 境外托管机构国别
     * @return 境外托管机构国别
     */
    public String getOutDepCtry() {
        return outDepCtry;
    }

    /**
     * 设置 境外托管机构国别
     * @param outDepCtry 境外托管机构国别
     */
    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry == null ? null : outDepCtry.trim();
    }

    /**
     * 取得 境外托管机构名称
     * @return 境外托管机构名称
     */
    public String getOutDepName() {
        return outDepName;
    }

    /**
     * 设置 境外托管机构名称
     * @param outDepName 境外托管机构名称
     */
    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName == null ? null : outDepName.trim();
    }

    /**
     * 取得 业绩比较基准说明
     * @return 业绩比较基准说明
     */
    public String getYieldRemark() {
        return yieldRemark;
    }

    /**
     * 设置 业绩比较基准说明
     * @param yieldRemark 业绩比较基准说明
     */
    public void setYieldRemark(String yieldRemark) {
        this.yieldRemark = yieldRemark == null ? null : yieldRemark.trim();
    }

    /**
     * 取得 无规律开放说明
     * @return 无规律开放说明
     */
    public String getRuleComment() {
        return ruleComment;
    }

    /**
     * 设置 无规律开放说明
     * @param ruleComment 无规律开放说明
     */
    public void setRuleComment(String ruleComment) {
        this.ruleComment = ruleComment == null ? null : ruleComment.trim();
    }

    /**
     * 取得 开放期业务说明
     * @return 开放期业务说明
     */
    public String getOpenBussRemark() {
        return openBussRemark;
    }

    /**
     * 设置 开放期业务说明
     * @param openBussRemark 开放期业务说明
     */
    public void setOpenBussRemark(String openBussRemark) {
        this.openBussRemark = openBussRemark == null ? null : openBussRemark.trim();
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

    /**
     * 取得 投资资产种类及比例
     * @return 投资资产种类及比例
     */
    public String getInvTypeRat() {
        return invTypeRat;
    }

    /**
     * 设置 投资资产种类及比例
     * @param invTypeRat 投资资产种类及比例
     */
    public void setInvTypeRat(String invTypeRat) {
        this.invTypeRat = invTypeRat == null ? null : invTypeRat.trim();
    }

    /**
     * 取得 产品销售地区
     * @return 产品销售地区
     */
    public String getSaleArea() {
        return saleArea;
    }

    /**
     * 设置 产品销售地区
     * @param saleArea 产品销售地区
     */
    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    /**
     * 取得 收益率分档情况说明
     * @return 收益率分档情况说明
     */
    public String getYieldComment() {
        return yieldComment;
    }

    /**
     * 设置 收益率分档情况说明
     * @param yieldComment 收益率分档情况说明
     */
    public void setYieldComment(String yieldComment) {
        this.yieldComment = yieldComment == null ? null : yieldComment.trim();
    }
}