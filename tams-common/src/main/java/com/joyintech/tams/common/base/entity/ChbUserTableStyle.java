package com.joyintech.tams.common.base.entity;

public class ChbUserTableStyle extends ChbUserTableStyleKey {
    /**
     * 列STYLE
     */
    private String columnStyle;

    /**
     * 取得 列STYLE
     * @return 列STYLE
     */
    public String getColumnStyle() {
        return columnStyle;
    }

    /**
     * 设置 列STYLE
     * @param columnStyle 列STYLE
     */
    public void setColumnStyle(String columnStyle) {
        this.columnStyle = columnStyle == null ? null : columnStyle.trim();
    }
}