package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PaFinProductBuyoutRateKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 生效日期
     */
    private Date effDate;

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
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}