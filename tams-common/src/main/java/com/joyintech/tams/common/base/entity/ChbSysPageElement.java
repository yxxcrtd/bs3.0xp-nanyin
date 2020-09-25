package com.joyintech.tams.common.base.entity;

public class ChbSysPageElement extends ChbSysPageElementKey {
    /**
     * 要素类型
     */
    private String elementType;

    /**
     * 迁移前画面代码
     */
    private String transferPageId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 要素类型
     * @return 要素类型
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * 设置 要素类型
     * @param elementType 要素类型
     */
    public void setElementType(String elementType) {
        this.elementType = elementType == null ? null : elementType.trim();
    }

    /**
     * 取得 迁移前画面代码
     * @return 迁移前画面代码
     */
    public String getTransferPageId() {
        return transferPageId;
    }

    /**
     * 设置 迁移前画面代码
     * @param transferPageId 迁移前画面代码
     */
    public void setTransferPageId(String transferPageId) {
        this.transferPageId = transferPageId == null ? null : transferPageId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}