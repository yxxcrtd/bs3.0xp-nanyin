package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstFundMarketInfoKey extends BaseEntity {
    /**
     * 基金代码（市场代码）
     */
    private String fundCode;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 取得 基金代码（市场代码）
     * @return 基金代码（市场代码）
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 设置 基金代码（市场代码）
     * @param fundCode 基金代码（市场代码）
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     * @param valueDate 估值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
}