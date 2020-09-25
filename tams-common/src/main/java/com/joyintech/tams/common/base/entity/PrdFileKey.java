package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdFileKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 披露文档代码
     */
    private String revealFileId;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 披露文档代码
     * @return 披露文档代码
     */
    public String getRevealFileId() {
        return revealFileId;
    }

    /**
     * 设置 披露文档代码
     * @param revealFileId 披露文档代码
     */
    public void setRevealFileId(String revealFileId) {
        this.revealFileId = revealFileId == null ? null : revealFileId.trim();
    }
}