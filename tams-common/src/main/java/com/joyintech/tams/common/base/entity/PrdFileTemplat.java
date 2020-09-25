package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdFileTemplat extends BaseEntity {
    /**
     * 披露文档代码
     */
    private String revealFileId;

    /**
     * 文件模板类型，01 产品成立公告（横表）、02 产品成立公告（竖表）、03 产品到期公告（横表）、04 产品到期公告（竖表）、05 产品说明书
     */
    private String fileType;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 取得 文件模板类型，01 产品成立公告（横表）、02 产品成立公告（竖表）、03 产品到期公告（横表）、04 产品到期公告（竖表）、05 产品说明书
     * @return 文件模板类型，01 产品成立公告（横表）、02 产品成立公告（竖表）、03 产品到期公告（横表）、04 产品到期公告（竖表）、05 产品说明书
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件模板类型，01 产品成立公告（横表）、02 产品成立公告（竖表）、03 产品到期公告（横表）、04 产品到期公告（竖表）、05 产品说明书
     * @param fileType 文件模板类型，01 产品成立公告（横表）、02 产品成立公告（竖表）、03 产品到期公告（横表）、04 产品到期公告（竖表）、05 产品说明书
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
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