package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstExponentQuotationKey extends BaseEntity {
    /**
     * 行情代码
     */
    private String marketId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 取得 行情代码
     * @return 行情代码
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * 设置 行情代码
     * @param marketId 行情代码
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}