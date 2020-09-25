package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSchemeSubjectConfigKey extends BaseEntity {
    /**
     * null
     */
    private String schemeId;

    /**
     * null
     */
    private String mySubjectId;

    /**
     * null
     */
    private String othersideSubjectId;

    /**
     * 取得 null
     * @return null
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 null
     * @param schemeId null
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getMySubjectId() {
        return mySubjectId;
    }

    /**
     * 设置 null
     * @param mySubjectId null
     */
    public void setMySubjectId(String mySubjectId) {
        this.mySubjectId = mySubjectId == null ? null : mySubjectId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOthersideSubjectId() {
        return othersideSubjectId;
    }

    /**
     * 设置 null
     * @param othersideSubjectId null
     */
    public void setOthersideSubjectId(String othersideSubjectId) {
        this.othersideSubjectId = othersideSubjectId == null ? null : othersideSubjectId.trim();
    }
}