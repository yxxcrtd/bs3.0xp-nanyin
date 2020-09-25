package com.joyintech.tams.common.base.entity;

public class BokSubjectDetail extends BokSubjectDetailKey {
    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 四级科目号
     */
    private String detailSubjectNo;

    /**
     * 四级科目号尾号
     */
    private String detailSubjectNoTail;

    /**
     * 是否计息
     */
    private String accIntFlag;

    /**
     * 是否允许透支
     */
    private String overdrawnFlag;

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }

    /**
     * 取得 四级科目号
     * @return 四级科目号
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 四级科目号
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
    }

    /**
     * 取得 四级科目号尾号
     * @return 四级科目号尾号
     */
    public String getDetailSubjectNoTail() {
        return detailSubjectNoTail;
    }

    /**
     * 设置 四级科目号尾号
     * @param detailSubjectNoTail 四级科目号尾号
     */
    public void setDetailSubjectNoTail(String detailSubjectNoTail) {
        this.detailSubjectNoTail = detailSubjectNoTail == null ? null : detailSubjectNoTail.trim();
    }

    /**
     * 取得 是否计息
     * @return 是否计息
     */
    public String getAccIntFlag() {
        return accIntFlag;
    }

    /**
     * 设置 是否计息
     * @param accIntFlag 是否计息
     */
    public void setAccIntFlag(String accIntFlag) {
        this.accIntFlag = accIntFlag == null ? null : accIntFlag.trim();
    }

    /**
     * 取得 是否允许透支
     * @return 是否允许透支
     */
    public String getOverdrawnFlag() {
        return overdrawnFlag;
    }

    /**
     * 设置 是否允许透支
     * @param overdrawnFlag 是否允许透支
     */
    public void setOverdrawnFlag(String overdrawnFlag) {
        this.overdrawnFlag = overdrawnFlag == null ? null : overdrawnFlag.trim();
    }
}