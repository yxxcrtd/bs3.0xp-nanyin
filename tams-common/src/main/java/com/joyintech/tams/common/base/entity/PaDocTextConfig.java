package com.joyintech.tams.common.base.entity;

public class PaDocTextConfig extends PaDocTextConfigKey {
    /**
     * 文本值
     */
    private String value;

    /**
     * 取得 文本值
     * @return 文本值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 文本值
     * @param value 文本值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}