package com.joyintech.tams.disclosure.busicommon.common.dto;

import com.joyintech.framework.base.BaseEntity;

public class FinProductTypeKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }
}