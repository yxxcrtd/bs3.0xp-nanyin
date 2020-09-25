package com.joyintech.tams.common.base.entity;

public class BokFinProductValuationPara extends BokFinProductValuationParaKey {
    /**
     * 行权摊销日设置
     */
    private String exeDateType;

    /**
     * 取得 行权摊销日设置
     * @return 行权摊销日设置
     */
    public String getExeDateType() {
        return exeDateType;
    }

    /**
     * 设置 行权摊销日设置
     * @param exeDateType 行权摊销日设置
     */
    public void setExeDateType(String exeDateType) {
        this.exeDateType = exeDateType == null ? null : exeDateType.trim();
    }
}