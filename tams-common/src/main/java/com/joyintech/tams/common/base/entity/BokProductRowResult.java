package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokProductRowResult extends BaseEntity {
    /**
     * null
     */
    private String compareId;

    /**
     * null
     */
    private String prodId;

    /**
     * null
     */
    private String schemeId;

    /**
     * null
     */
    private Date compareDate;

    /**
     * null
     */
    private String compareType;

    /**
     * null
     */
    private Long compareRowno;

    /**
     * null
     */
    private String mySubjectId;

    /**
     * null
     */
    private String mySubjectName;

    /**
     * null
     */
    private String othersideSubjectId;

    /**
     * null
     */
    private String othersideSubjectName;

    /**
     * null
     */
    private String remark;

    /**
     * 取得 null
     * @return null
     */
    public String getCompareId() {
        return compareId;
    }

    /**
     * 设置 null
     * @param compareId null
     */
    public void setCompareId(String compareId) {
        this.compareId = compareId == null ? null : compareId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 null
     * @param prodId null
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

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
    public Date getCompareDate() {
        return compareDate;
    }

    /**
     * 设置 null
     * @param compareDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCompareDate(Date compareDate) {
        this.compareDate = compareDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCompareType() {
        return compareType;
    }

    /**
     * 设置 null
     * @param compareType null
     */
    public void setCompareType(String compareType) {
        this.compareType = compareType == null ? null : compareType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getCompareRowno() {
        return compareRowno;
    }

    /**
     * 设置 null
     * @param compareRowno null
     */
    public void setCompareRowno(Long compareRowno) {
        this.compareRowno = compareRowno;
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
    public String getMySubjectName() {
        return mySubjectName;
    }

    /**
     * 设置 null
     * @param mySubjectName null
     */
    public void setMySubjectName(String mySubjectName) {
        this.mySubjectName = mySubjectName == null ? null : mySubjectName.trim();
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

    /**
     * 取得 null
     * @return null
     */
    public String getOthersideSubjectName() {
        return othersideSubjectName;
    }

    /**
     * 设置 null
     * @param othersideSubjectName null
     */
    public void setOthersideSubjectName(String othersideSubjectName) {
        this.othersideSubjectName = othersideSubjectName == null ? null : othersideSubjectName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 null
     * @param remark null
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}