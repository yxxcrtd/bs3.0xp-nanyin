package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstSharePriceKey extends BaseEntity {
    /**
     * 股票代码，市场代码
     */
    private String sharesId;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 取得 股票代码，市场代码
     * @return 股票代码，市场代码
     */
    public String getSharesId() {
        return sharesId;
    }

    /**
     * 设置 股票代码，市场代码
     * @param sharesId 股票代码，市场代码
     */
    public void setSharesId(String sharesId) {
        this.sharesId = sharesId == null ? null : sharesId.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 交易日
     * @param dealDate 交易日
     */
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
}