package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokSchemeInfoDetail extends BaseEntity {
    /**
     * null
     */
    private String schId;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal errorNumber;

    /**
     * null
     */
    private Long myStartRowNo;

    /**
     * null
     */
    private Long othersideStartRowNo;

    /**
     * null
     */
    private Long myEndRowNo;

    /**
     * null
     */
    private Long othersideEndRowNo;

    /**
     * 取得 null
     * @return null
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 null
     * @param schId null
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getErrorNumber() {
        return errorNumber;
    }

    /**
     * 设置 null
     * @param errorNumber null
     */
    public void setErrorNumber(BigDecimal errorNumber) {
        this.errorNumber = errorNumber;
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getMyStartRowNo() {
        return myStartRowNo;
    }

    /**
     * 设置 null
     * @param myStartRowNo null
     */
    public void setMyStartRowNo(Long myStartRowNo) {
        this.myStartRowNo = myStartRowNo;
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getOthersideStartRowNo() {
        return othersideStartRowNo;
    }

    /**
     * 设置 null
     * @param othersideStartRowNo null
     */
    public void setOthersideStartRowNo(Long othersideStartRowNo) {
        this.othersideStartRowNo = othersideStartRowNo;
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getMyEndRowNo() {
        return myEndRowNo;
    }

    /**
     * 设置 null
     * @param myEndRowNo null
     */
    public void setMyEndRowNo(Long myEndRowNo) {
        this.myEndRowNo = myEndRowNo;
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getOthersideEndRowNo() {
        return othersideEndRowNo;
    }

    /**
     * 设置 null
     * @param othersideEndRowNo null
     */
    public void setOthersideEndRowNo(Long othersideEndRowNo) {
        this.othersideEndRowNo = othersideEndRowNo;
    }
}