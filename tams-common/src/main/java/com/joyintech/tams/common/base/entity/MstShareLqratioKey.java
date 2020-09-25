package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class MstShareLqratioKey extends BaseEntity {
    /**
     * 股票代码，市场代码
     */
    private String sharesId;

    /**
     * 估值日期
     */
    private Date valDate;

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
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }
}