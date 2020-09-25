package com.joyintech.tams.common.base.entity;

public class ChbSubCheckItemConvert extends ChbSubCheckItemConvertKey {
    /**
     * 项目编号
     */
    private String itemCode;

    /**
     * 取得 项目编号
     * @return 项目编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 项目编号
     * @param itemCode 项目编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }
}