package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class FinSubscriptionInfoKey extends BaseEntity {
    /**
     * 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    private String finprodId;

    /**
     * 机构方名称
     */
    private String institutionName;

    /**
     * 取得 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     * @return 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     * @param finprodId 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 机构方名称
     * @return 机构方名称
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * 设置 机构方名称
     * @param institutionName 机构方名称
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }
}