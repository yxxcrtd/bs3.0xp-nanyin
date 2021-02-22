package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MockTestTaaKey extends BaseEntity {
    /**
     * 模拟编号
     */
    private String mockId;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 是否测算前
     */
    private String isMock;

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
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 是否测算前
     * @return 是否测算前
     */
    public String getIsMock() {
        return isMock;
    }

    /**
     * 设置 是否测算前
     * @param isMock 是否测算前
     */
    public void setIsMock(String isMock) {
        this.isMock = isMock == null ? null : isMock.trim();
    }
}