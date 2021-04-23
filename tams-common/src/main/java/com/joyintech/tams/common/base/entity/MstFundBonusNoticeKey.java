package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstFundBonusNoticeKey extends BaseEntity {
    /**
     * 股票代码，市场代码
     */
    private String fundId;

    /**
     * 权益登记日
     */
    private Date regDate;

    /**
     * 取得 股票代码，市场代码
     * @return 股票代码，市场代码
     */
    public String getFundId() {
        return fundId;
    }

    /**
     * 设置 股票代码，市场代码
     * @param fundId 股票代码，市场代码
     */
    public void setFundId(String fundId) {
        this.fundId = fundId == null ? null : fundId.trim();
    }

    /**
     * 取得 权益登记日
     * @return 权益登记日
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置 权益登记日
     * @param regDate 权益登记日
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}