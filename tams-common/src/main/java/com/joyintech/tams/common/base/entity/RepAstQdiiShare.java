package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepAstQdiiShare extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 所属国家或地区
     */
    private String repCountryArea;

    /**
     * 股票/基金代码
     */
    private String repShrsFundId;

    /**
     * 发行机构
     */
    private String repIssueParty;

    /**
     * 行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 所属国家或地区
     * @return 所属国家或地区
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区
     * @param repCountryArea 所属国家或地区
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 股票/基金代码
     * @return 股票/基金代码
     */
    public String getRepShrsFundId() {
        return repShrsFundId;
    }

    /**
     * 设置 股票/基金代码
     * @param repShrsFundId 股票/基金代码
     */
    public void setRepShrsFundId(String repShrsFundId) {
        this.repShrsFundId = repShrsFundId == null ? null : repShrsFundId.trim();
    }

    /**
     * 取得 发行机构
     * @return 发行机构
     */
    public String getRepIssueParty() {
        return repIssueParty;
    }

    /**
     * 设置 发行机构
     * @param repIssueParty 发行机构
     */
    public void setRepIssueParty(String repIssueParty) {
        this.repIssueParty = repIssueParty == null ? null : repIssueParty.trim();
    }

    /**
     * 取得 行业
     * @return 行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 行业
     * @param repIndustry 行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 行业二级分类
     * @param repIndustryLevel2 行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}