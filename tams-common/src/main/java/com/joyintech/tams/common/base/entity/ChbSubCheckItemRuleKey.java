package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class ChbSubCheckItemRuleKey extends BaseEntity {
    /**
     * 分类编号
     */
    private String classId;

    /**
     * 项目自检规则序列号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal itemRuleNo;

    /**
     * 取得 分类编号
     * @return 分类编号
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 设置 分类编号
     * @param classId 分类编号
     */
    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    /**
     * 取得 项目自检规则序列号
     * @return 项目自检规则序列号
     */
    public BigDecimal getItemRuleNo() {
        return itemRuleNo;
    }

    /**
     * 设置 项目自检规则序列号
     * @param itemRuleNo 项目自检规则序列号
     */
    public void setItemRuleNo(BigDecimal itemRuleNo) {
        this.itemRuleNo = itemRuleNo;
    }
}