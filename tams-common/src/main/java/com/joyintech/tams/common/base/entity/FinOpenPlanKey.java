package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinOpenPlanKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 开放类型，申购开放、赎回开放、申赎开放
     */
    private String openType;

    /**
     * 募集结束日
     */
    private Date openDateEnd;

    /**
     * 取得 现金流代码
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 开放类型，申购开放、赎回开放、申赎开放
     * @return 开放类型，申购开放、赎回开放、申赎开放
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 开放类型，申购开放、赎回开放、申赎开放
     * @param openType 开放类型，申购开放、赎回开放、申赎开放
     */
    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getOpenDateEnd() {
        return openDateEnd;
    }

    /**
     * 设置 募集结束日
     * @param openDateEnd 募集结束日
     */
    public void setOpenDateEnd(Date openDateEnd) {
        this.openDateEnd = openDateEnd;
    }
}