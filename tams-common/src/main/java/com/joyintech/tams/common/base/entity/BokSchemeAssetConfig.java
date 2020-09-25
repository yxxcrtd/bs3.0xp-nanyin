package com.joyintech.tams.common.base.entity;

public class BokSchemeAssetConfig extends BokSchemeAssetConfigKey {
	/**
     * 录入方式
     */
    private String inputType;

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}