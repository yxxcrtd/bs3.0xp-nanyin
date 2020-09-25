package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskFaciDeduceRuleKey extends BaseEntity {
    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;

    /**
     * 额度类型
     */
    private String amountType;

    /**
     * 取得 金融产品类型（投管分类）
     * @return 金融产品类型（投管分类）
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类）
     * @param finprodType2 金融产品类型（投管分类）
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 额度类型
     * @return 额度类型
     */
    public String getAmountType() {
        return amountType;
    }

    /**
     * 设置 额度类型
     * @param amountType 额度类型
     */
    public void setAmountType(String amountType) {
        this.amountType = amountType == null ? null : amountType.trim();
    }
}