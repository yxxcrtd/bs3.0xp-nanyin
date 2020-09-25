package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinReduceShareInfoKey extends BaseEntity {
    /**
     * 金融产品代码，组装规则：金融产品类型（投管分类） _ 市场代码 _通道代码，如重复，则加4位流水后缀（0001开始）
     */
    private String finprodId;

    /**
     * 减持日期
     */
    private Date reduceDate;

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
     * 取得 减持日期
     * @return 减持日期
     */
    public Date getReduceDate() {
        return reduceDate;
    }

    /**
     * 设置 减持日期
     * @param reduceDate 减持日期
     */
    public void setReduceDate(Date reduceDate) {
        this.reduceDate = reduceDate;
    }
}