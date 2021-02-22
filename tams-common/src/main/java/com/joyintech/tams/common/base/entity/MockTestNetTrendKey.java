package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class MockTestNetTrendKey extends BaseEntity {
    /**
     * 模拟编号
     */
    private String mockId;

    /**
     * 日期
     */
    private Date cpriceDate;

    /**
     * 取得 模拟编号
     * @return 模拟编号
     */
    public String getMockId() {
        return mockId;
    }

    /**
     * 设置 模拟编号
     * @param mockId 模拟编号
     */
    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getCpriceDate() {
        return cpriceDate;
    }

    /**
     * 设置 日期
     * @param cpriceDate 日期
     */
    public void setCpriceDate(Date cpriceDate) {
        this.cpriceDate = cpriceDate;
    }
}