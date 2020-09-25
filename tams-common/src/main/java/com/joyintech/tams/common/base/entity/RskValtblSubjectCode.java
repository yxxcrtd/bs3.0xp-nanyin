package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskValtblSubjectCode extends BaseEntity {
    /**
     * 资产科目代码
     */
    private String subjectCode;

    /**
     * 资产科目名称
     */
    private String subjectName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 取得 资产科目代码
     * @return 资产科目代码
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * 设置 资产科目代码
     * @param subjectCode 资产科目代码
     */
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    /**
     * 取得 资产科目名称
     * @return 资产科目名称
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 资产科目名称
     * @param subjectName 资产科目名称
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}