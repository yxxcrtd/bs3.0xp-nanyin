package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MockTestRskKey extends BaseEntity {
    /**
     * 模拟编号
     */
    private String mockId;

    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 指标代码
     */
    private String indId;

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
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }
}