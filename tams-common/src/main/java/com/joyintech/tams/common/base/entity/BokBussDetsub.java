package com.joyintech.tams.common.base.entity;

public class BokBussDetsub extends BokBussDetsubKey {
    /**
     * 四级科目名称
     */
    private String detailSubjectName;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 四级科目尾号，不含三级科目
     */
    private String detailSubjectNoTail;

    /**
     * 四级科目尾号名称
     */
    private String detailSubnameTail;

    /**
     * 取得 四级科目名称
     * @return 四级科目名称
     */
    public String getDetailSubjectName() {
        return detailSubjectName;
    }

    /**
     * 设置 四级科目名称
     * @param detailSubjectName 四级科目名称
     */
    public void setDetailSubjectName(String detailSubjectName) {
        this.detailSubjectName = detailSubjectName == null ? null : detailSubjectName.trim();
    }

    /**
     * 取得 上级科目号
     * @return 上级科目号
     */
    public String getFsubjectId() {
        return fsubjectId;
    }

    /**
     * 设置 上级科目号
     * @param fsubjectId 上级科目号
     */
    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId == null ? null : fsubjectId.trim();
    }

    /**
     * 取得 四级科目尾号，不含三级科目
     * @return 四级科目尾号，不含三级科目
     */
    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }

    /**
     * 设置 四级科目尾号，不含三级科目
     * @param detailSubjectNoTail 四级科目尾号，不含三级科目
     */
    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail == null ? null : detailSubjectNoTail.trim();
    }

    /**
     * 取得 四级科目尾号名称
     * @return 四级科目尾号名称
     */
    public String getDetailSubnameTail() {
        return detailSubnameTail;
    }

    /**
     * 设置 四级科目尾号名称
     * @param detailSubnameTail 四级科目尾号名称
     */
    public void setDetailSubnameTail(String detailSubnameTail) {
        this.detailSubnameTail = detailSubnameTail == null ? null : detailSubnameTail.trim();
    }
}